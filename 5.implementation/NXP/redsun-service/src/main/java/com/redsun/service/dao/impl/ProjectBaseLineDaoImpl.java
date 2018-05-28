package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.redsun.service.dao.ProjectBaseLineDao;
import com.redsun.service.dao.mapper.BaseLineMapper;
import com.redsun.service.dao.mapper.ProjectEquipmentsMapper;
import com.redsun.service.dao.mapper.ProjectMaterialsMapper;
import com.redsun.service.dao.mapper.ProjectResourcesMapper;
import com.redsun.service.dao.mapper.TaskEquipmentsMapper;
import com.redsun.service.dao.mapper.TaskMaterialsMapper;
import com.redsun.service.dao.mapper.TaskResouresMapper;
import com.redsun.service.dao.mapper.TaskRowMapper;
import com.redsun.service.entities.BaseLines;
import com.redsun.service.entities.ProjectEquipments;
import com.redsun.service.entities.ProjectMaterials;
import com.redsun.service.entities.ProjectResources;
import com.redsun.service.entities.Task;
import com.redsun.service.entities.TaskEquipments;
import com.redsun.service.entities.TaskMaterials;
import com.redsun.service.entities.TaskResources;



@Repository
public class ProjectBaseLineDaoImpl implements ProjectBaseLineDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static Logger log = Logger.getLogger(ProjectBaseLineDaoImpl.class);
	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] { 
			java.sql.Types.VARCHAR, // name
			java.sql.Types.INTEGER, //type
			java.sql.Types.DATE, // done_date
			java.sql.Types.VARCHAR, // description
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.INTEGER // client_id
	};
	private static String ADD_BASELINE = "INSERT INTO baselines (name, baseline_type, baseline_date, description, project_id, client_id) VALUES (?, ?, ?::date, ?, ?, ?)";
	private static String ADD_PROJECT_LABOR_BASELINE = "INSERT INTO project_resource_baselines (resource_id, salary, quantity, project_id, client_id, baseline_id) VALUES (?, ?, ?, ?, ?, ?)";
	private static String ADD_PROJECT_MATERIAL_BASELINE = "INSERT INTO project_material_baselines (project_id, material_id, quantity, net_price, price, client_id, baseline_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static String ADD_PROJECT_EQUIPMENT_BASELINE = "INSERT INTO project_equipment_baselines (project_id, equipment_id, quantity, net_price, price, client_id, baseline_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static String ADD_TASK_BASELINE = "INSERT INTO task_baselines("
            + "task_name, wbs, description, parent_id, id_affectation, responsible, "
            + "task_owner, start_date, end_date, estimate_time," 
            + "actually_time, project_id, status_id, priority_id, type_id, completed, comment, is_trash, client_id, baseline_id)"
            +" VALUES (?, ?, ?, ?, ?, ?," 
            +" ?, ?::date, ?::date, ?," 
            +"?, ?, ?, ?, ?, ?, ?, ?::bit, ?, ?)";
	private static String ADD_TASK_RESOURCE_BASELINE = "INSERT INTO task_resource_baselines (project_id, resource_id, quantity, warning, task_id, client_id, baseline_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static String ADD_TASK_MATERIAL_BASELINE = "INSERT INTO task_material_baselines (project_id, task_id, material_id, estimate_quantity, client_id, baseline_id ) VALUES (?, ?, ?, ?, ?, ?)";
	private static String ADD_TASK_EQUIPMENT_BASELINE = "INSERT INTO task_equipment_baselines (project_id, task_id, equipment_id, quantity, client_id, baseline_id) VALUES (?, ?, ?, ?, ?, ?)";
	private final static String SELECT_PRS_BY_PROJECTID = "SELECT pr.id, pr.project_id, pr.resource_id, pr.salary, pr.quantity, pr.client_id, '' as resource_code, '' as resource_name, '' as email"
			+ " FROM project_resources AS pr"
			+ " WHERE pr.project_id = ? AND pr.client_id = ?";
	private static String SELECT_PROJECT_MATERIAL = "SELECT 0 as id, pa.project_id, pa.material_id, '' as material_name, '' as unit, '' as description, pa.quantity, pa.net_price, pa.price, pa.client_id"
			+ " FROM project_materials AS pa"
			+ " WHERE pa.project_id = ? AND pa.client_id = ?";
	private static String SELECT_PROJECT_EQUIPMENT = "SELECT 0 as id, pe.project_id, pe.equipment_id, '' as unit, pe.quantity, pe.net_price, pe.price, pe.client_id, '' as equipment_name, '' as description"
			+ " FROM project_equipments AS pe"
			+ " WHERE pe.project_id = ? AND pe.client_id = ?";
	private static String SELECT_TASK = "SELECT 0 as id, task_name, wbs, description, parent_id, responsible, id_affectation, task_owner, start_date, end_date, create_date, update_date, estimate_time,"
        	+ " actually_time, project_id, status_id, priority_id, type_id, client_id, 0 as total_count, completed, comment, is_trash" 
			+ " FROM tasks"
			+ " WHERE project_id = ? AND client_id = ? AND is_trash = '0'";
	private static String SELECT_TASK_RESOURCES = "SELECT 0 as id, tr.task_id, tr.resource_id, 0 as salary, tr.quantity, tr.actual_work, tr.warning, tr.project_id, tr.client_id,"
			+ " '' as resource_code, '' as resource_name, '' as email_of_assignee" 
			+ " FROM task_resources AS tr"
			+ " WHERE tr.project_id=? AND tr.client_id = ?";
	private static String SELECT_TASK_MATERIAL = "SELECT 0 as id, ta.project_id, ta.task_id, ta.material_id, 0 as net_price, 0 as price, ta.estimate_quantity, ta.actual_quantity, ta.client_id," 
			+" '' as material_name, '' as description, '' as unit "
			+" FROM task_materials as ta "
			+" WHERE ta.project_id = ? AND ta.client_id = ?";
	private static String SELECT_TASK_EQUIPMENT = "SELECT 0 as id, te.project_id, te.task_id, te.equipment_id, te.quantity, te.actual_work, te.client_id, "
			+ "'' as equipment_name, '' as description,0 as net_price, 0 as price, '' as unit"
			+ " FROM task_equipments as te"
			+ " WHERE te.project_id = ? AND te.client_id = ?";
	private final static String SELECT_BASELINE_BY_TYPE = "SELECT id, name, baseline_date, description FROM baselines WHERE project_id = ? AND baseline_type = ? AND client_id = ?";
	private final static String SELECT_PRSBL_BY_PROJECTID = "SELECT pr.id, pr.project_id, pr.resource_id, pr.salary, pr.quantity, pr.client_id, r.code as resource_code, r.name as resource_name, '' as email"
			+ " FROM project_resource_baselines AS pr INNER JOIN resources r ON r.id = pr.resource_id"
			+ " WHERE pr.project_id = ? AND pr.client_id = ? AND pr.baseline_id = ?";
	
	@Override
	public List<BaseLines> getBaseLineByType(int projectId, int type, int clientId){
		List<BaseLines> listBaseLines = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(type);
		params.add(clientId);
		try {
			listBaseLines = jdbcTemplate.query(SELECT_BASELINE_BY_TYPE, params.toArray(), new BaseLineMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listBaseLines;
	}
	@Override
	public List<ProjectResources> getLaborBaseline(int projectId, int clientId, int baselineId) {
		// TODO Auto-generated method stub
		List<ProjectResources> listProjectResources = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		params.add(baselineId);
		try {
			listProjectResources = jdbcTemplate.query(SELECT_PRSBL_BY_PROJECTID, params.toArray(), new ProjectResourcesMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectResources;
	}
	@Override
	public List<ProjectResources> getProjectResourceByProjectId(int projectId, int clientId) {
		// TODO Auto-generated method stub
		List<ProjectResources> listProjectResources = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		try {
			listProjectResources = jdbcTemplate.query(SELECT_PRS_BY_PROJECTID, params.toArray(), new ProjectResourcesMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectResources;
	}
	@Override
	public void insertLaborBaseline(List<ProjectResources> projectResources) {
		// TODO Auto-generated method stub
		List<Object[]> parameters = new ArrayList<Object[]>();
        for (ProjectResources projectResource : projectResources) {
            parameters.add(new Object[] {projectResource.getResourceId(), projectResource.getSalary(), projectResource.getQuantity(), projectResource.getProjectId(), projectResource.getClientId(), projectResource.getBaselineId()});
        }
        jdbcTemplate.batchUpdate(ADD_PROJECT_LABOR_BASELINE, parameters);
	}
	@Override
	public List<ProjectMaterials> getProjectMaterialByProjectId(int projectId, int clientId) {
		// TODO Auto-generated method stub
		List<ProjectMaterials> listProjectMaterials = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		try {
			listProjectMaterials = jdbcTemplate.query(SELECT_PROJECT_MATERIAL, params.toArray(), new ProjectMaterialsMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectMaterials;
	}
	@Override
	public void insertMaterialBaseline(List<ProjectMaterials> projectMaterials) {
		// TODO Auto-generated method stub
		List<Object[]> parameters = new ArrayList<Object[]>();
        for (ProjectMaterials projectMaterial : projectMaterials) {
            parameters.add(new Object[] {projectMaterial.getProjectId(), projectMaterial.getMaterialId(), projectMaterial.getQuantity(), projectMaterial.getNetPrice(), projectMaterial.getPrice(), projectMaterial.getClientId(), projectMaterial.getBaselineId()});
        }
        jdbcTemplate.batchUpdate(ADD_PROJECT_MATERIAL_BASELINE, parameters);
	}
	@Override
	public List<ProjectEquipments> getProjectEquipmentByProjectId(int projectId, int clientId) {
		// TODO Auto-generated method stub
		List<ProjectEquipments> listProjectEquipments = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		try {
			listProjectEquipments = jdbcTemplate.query(SELECT_PROJECT_EQUIPMENT, params.toArray(), new ProjectEquipmentsMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectEquipments;
	}
	@Override
	public void insertEquipmentBaseline(List<ProjectEquipments> projectEquipments) {
		// TODO Auto-generated method stub
		List<Object[]> parameters = new ArrayList<Object[]>();
        for (ProjectEquipments projectEquipment : projectEquipments) {
            parameters.add(new Object[] {projectEquipment.getProjectId(), projectEquipment.getEquipmentId(), projectEquipment.getQuantity(), projectEquipment.getNetPrice(), projectEquipment.getPrice(), projectEquipment.getClientId(), projectEquipment.getBaselineId()});
        }
        jdbcTemplate.batchUpdate(ADD_PROJECT_EQUIPMENT_BASELINE, parameters);
	}
	@Override
	public List<Task> getProjectTaskByProjectId(int projectId, int clientId) {
		// TODO Auto-generated method stub
		List<Task> listProjectTasks = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		try {
			listProjectTasks = jdbcTemplate.query(SELECT_TASK, params.toArray(), new TaskRowMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectTasks;
	}
	@Override
	public void insertTaskBaseline(List<Task> tasks) {
		// TODO Auto-generated method stub
		List<Object[]> parameters = new ArrayList<Object[]>();
        for (Task task : tasks) {
            parameters.add(new Object[] {task.getTaskName(), task.getWbs(), task.getDescription(), task.getParentId(), task.getAffectationId(), task.getResponsible(), task.getTaskOwner(), task.getStartDate(), task.getEndDate(),
            		task.getEstimateTime(), task.getActuallyTime(),task.getProjectId(), task.getStatusId(), task.getPriorityId(), task.getTypeId(), task.getCompleted(), task.getComment(), task.getIsTrash(), task.getClientId(),task.getBaselineId()});
        }
        jdbcTemplate.batchUpdate(ADD_TASK_BASELINE, parameters);
	}
	@Override
	public List<TaskResources> getProjectTaskRSByProjectId(int projectId, int clientId) {
		// TODO Auto-generated method stub
		List<TaskResources> listProjectTaskRS = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		try {
			listProjectTaskRS = jdbcTemplate.query(SELECT_TASK_RESOURCES, params.toArray(), new TaskResouresMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectTaskRS;
	}
	@Override
	public void insertTaskResourceBaseline(List<TaskResources> taskResources) {
		// TODO Auto-generated method stub
		List<Object[]> parameters = new ArrayList<Object[]>();
        for (TaskResources taskRS : taskResources) {
            parameters.add(new Object[] {taskRS.getProjectId(), taskRS.getResourceId(), taskRS.getQuantity(), taskRS.getWarning(), taskRS.getTaskId(), taskRS.getClientId(),taskRS.getBaselineId()});
        }
        jdbcTemplate.batchUpdate(ADD_TASK_RESOURCE_BASELINE, parameters);
	}
	@Override
	public List<TaskMaterials> getProjectTaskMaterialByProjectId(int projectId, int clientId) {
		// TODO Auto-generated method stub
		List<TaskMaterials> listProjectTaskMaterials = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		try {
			listProjectTaskMaterials = jdbcTemplate.query(SELECT_TASK_MATERIAL, params.toArray(), new TaskMaterialsMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectTaskMaterials;
	}
	@Override
	public void insertTaskMaterialBaseline(List<TaskMaterials> taskMaterials) {
		// TODO Auto-generated method stub
		List<Object[]> parameters = new ArrayList<Object[]>();
        for (TaskMaterials taskMaterial : taskMaterials) {
            parameters.add(new Object[] {taskMaterial.getProjectId(), taskMaterial.getTaskId(), taskMaterial.getProjectMaterialId(), taskMaterial.getQuantity(), taskMaterial.getClientId(),taskMaterial.getBaselineId()});
        }
        jdbcTemplate.batchUpdate(ADD_TASK_MATERIAL_BASELINE, parameters);
	}
	@Override
	public List<TaskEquipments> getProjectTaskEquipmentByProjectId(int projectId, int clientId) {
		// TODO Auto-generated method stub
		List<TaskEquipments> listProjectTaskEquipments = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		try {
			listProjectTaskEquipments = jdbcTemplate.query(SELECT_TASK_EQUIPMENT, params.toArray(), new TaskEquipmentsMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectTaskEquipments;
	}
	@Override
	public void insertTaskEquipmentBaseline(List<TaskEquipments> taskEquipments) {
		// TODO Auto-generated method stub
		List<Object[]> parameters = new ArrayList<Object[]>();
        for (TaskEquipments taskEquipment : taskEquipments) {
            parameters.add(new Object[] {taskEquipment.getProjectId(), taskEquipment.getTaskId(), taskEquipment.getEquipmentId(), taskEquipment.getQuantity(), taskEquipment.getClientId(), taskEquipment.getBaselineId()});
        }
        jdbcTemplate.batchUpdate(ADD_TASK_EQUIPMENT_BASELINE, parameters);
	}
	private PreparedStatementCreator getPreparedStatementCreator(final BaseLines baseline) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(ADD_BASELINE, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(baseline));
		return psc ;
	}
	protected Object[] getValuesForInsert(final BaseLines baseline)  {
		return new Object[] {
				baseline.getName(), 
				baseline.getBaselineType(),
				baseline.getBaselineDate(), 
				baseline.getDescription(),
				baseline.getProjectId(),
				baseline.getClientId()
		};
	}
	@Override
	public int inserBaseLine(BaseLines baseline) {
		// TODO Auto-generated method stub
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		int result = jdbcTemplate.update(getPreparedStatementCreator(baseline), keyHolder);
		if (result != 1) {
			throw new RuntimeException("Unexpected return value after INSERT : " + result + " (1 expected) ");
		}
		
		Number key = keyHolder.getKey();
		if (key != null) {
			return key.intValue();
		}
		else {
			throw new RuntimeException("Cannot retrive generated key after INSERT : KeyHolder returns null");
		}
	}
}
