package com.redsun.service.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.PMReportDao;
import com.redsun.service.dao.mapper.CostExpensesPerMonthMapper;
import com.redsun.service.dao.mapper.MaterialUsageMapper;
import com.redsun.service.dao.mapper.ResourceCostMapper;
import com.redsun.service.dao.mapper.TaskCostMapper;
import com.redsun.service.entities.PlanningElement;
import com.redsun.service.entities.TaskMaterials;




@Repository
public class PMReportDaoImpl implements PMReportDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private static Logger log = Logger.getLogger(PMReportDaoImpl.class);
	
	private final static String SELECT_TASK_PARENT ="SELECT t.id, t.task_name, t.wbs, t.parent_id, 0 as total_actually_cost, 0 total_planning_cost"
			   + " FROM tasks t"
			   + " WHERE t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date AND t.parent_id is null AND t.is_trash = '0'";
	private final static String SELECT_TASK_NOT_SUB ="SELECT t.id, t.task_name, t.wbs, t.parent_id, 0 as total_actually_cost, 0 total_planning_cost"
			   + " FROM tasks t INNER JOIN task_resource_daily_tracking tr ON t.id = tr.task_id"
			   + " WHERE t.parent_id like ?";
	private final static String SELECT_TASK_COST_A_DU = " SELECT ac.id, ac.task_name, ac.wbs, ac.parent_id, sum(ac.total_actually_cost) as total_actually_cost, 0 total_planning_cost"
				+ " FROM(SELECT t.id, t.task_name, t.wbs, t.parent_id, (tr.actual_time::numeric * tr.salary + tr.over_time::numeric * tr.salary * tr.coefficient) * tr.quantity::numeric as total_actually_cost, 0 total_planning_cost"
				+ " FROM tasks t INNER JOIN task_resource_daily_tracking tr ON t.id = tr.task_id"
				+ " WHERE t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date"
				+ " UNION"
				+ " SELECT t.id, t.task_name, t.wbs, t.parent_id, te.actual_time::numeric * te.price*te.quantity::numeric as total_actually_cost, 0 total_planning_cost"
				+ " FROM tasks t INNER JOIN task_equipment_daily_tracking te ON t.id = te.task_id"
				+ " WHERE t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date"
				+ " UNION"
				+ " SELECT t.id, t.task_name, t.wbs, t.parent_id, tm.actual_material::numeric * tm.price as total_actually_cost, 0 total_planning_cost"
				+ " FROM tasks t INNER JOIN task_material_daily_tracking tm ON t.id = tm.task_id"
				+ " WHERE t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date) ac"
				+ " GROUP BY ac.id, ac.task_name, ac.wbs, ac.parent_id";
	private final static String SELECT_TASK_COST_P_DU = "SELECT distinct t.id, t.task_name, tr.resource_id as wbs, t.parent_id, t.estimate_time*tr.quantity*trdt.salary as total_planning_cost, 0 as total_actually_cost"
				+ " FROM tasks t inner join task_resources tr on t.id = tr.task_id inner join task_resource_daily_tracking trdt on t.id = trdt.task_id and tr.resource_id = trdt.resource_id"
				+ " WHERE t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date"
				+ " UNION"
				+ " SELECT distinct t.id, t.task_name, tr.resource_id as wbs, t.parent_id, t.estimate_time*tr.quantity*pr.salary as total_planning_cost, 0 as total_actually_cost"
				+ " FROM tasks t inner join task_resources tr on t.id = tr.task_id inner join project_resources pr on tr.resource_id = pr.resource_id"
				+ " WHERE t.project_id = ? and tr.resource_id not in (select trdt.resource_id from task_resource_daily_tracking trdt)"
				+ " AND t.start_date >= ?::date AND t.end_date <= ?::date AND t.is_trash = '0'"
				+ " UNION"
				+ " SELECT distinct t.id, t.task_name, tr.resource_id as wbs, t.parent_id, t.estimate_time*tr.quantity*pr.salary, 0 as total_actually_cost"
				+ " FROM tasks t inner join task_resources tr on t.id = tr.task_id inner join project_resources pr on tr.resource_id = pr.resource_id"
				+ " WHERE t.project_id = ? and tr.task_id not in (select trdt.task_id from task_resource_daily_tracking trdt) AND t.start_date >= ?::date AND t.end_date <= ?::date AND t.is_trash = '0'"
				+ " UNION"
				+ " SELECT distinct t.id, t.task_name, te.equipment_id as wbs, t.parent_id, t.estimate_time*te.quantity*tedt.price as total_planning_cost, 0 as total_actually_cost"
				+ " FROM tasks t inner join task_equipments te on t.id = te.task_id inner join task_equipment_daily_tracking tedt on t.id = tedt.task_id and te.equipment_id = tedt.equipment_id"
				+ " WHERE t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date"
				+ " UNION"
				+ " SELECT distinct t.id, t.task_name, te.equipment_id as wbs, t.parent_id, t.estimate_time*te.quantity*pe.price as total_planning_cost, 0 as total_actually_cost"
				+ " FROM tasks t inner join task_equipments te on t.id = te.task_id inner join project_equipments pe on te.equipment_id = pe.equipment_id"
				+ " WHERE te.equipment_id not in (select tedt.equipment_id from task_equipment_daily_tracking tedt)"
				+ " AND t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date AND t.is_trash = '0'"
				+ " UNION"
				+ " SELECT distinct t.id, t.task_name, te.equipment_id as wbs, t.parent_id, t.estimate_time*te.quantity*pe.price, 0 as total_actually_cost"
				+ " FROM tasks t inner join task_equipments te on t.id = te.task_id inner join project_equipments pe on te.equipment_id = pe.equipment_id"
				+ " WHERE te.task_id not in (select tedt.task_id from task_equipment_daily_tracking tedt)"
				+ " AND t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date AND t.is_trash = '0'"
				+ " UNION"
				+ " SELECT distinct t.id, t.task_name, tm.material_id as wbs, t.parent_id, tm.estimate_quantity*tmdt.price as total_planning_cost, 0 as total_actually_cost"
				+ " FROM tasks t inner join task_materials tm on t.id = tm.task_id inner join task_material_daily_tracking tmdt on t.id = tmdt.task_id and tm.material_id = tmdt.material_id"
				+ " WHERE t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date"
				+ " UNION"
				+ " SELECT distinct t.id, t.task_name, tm.material_id as wbs, t.parent_id, tm.estimate_quantity*pm.price as total_planning_cost, 0 as total_actually_cost"
				+ " FROM tasks t inner join task_materials tm on t.id = tm.task_id inner join project_materials pm on tm.material_id = pm.material_id"
				+ " WHERE tm.material_id not in (select tmdt.material_id from task_material_daily_tracking tmdt)"
				+ " AND t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date AND t.is_trash = '0'"
				+ " UNION"
				+ " SELECT distinct t.id, t.task_name, tm.material_id as wbs, t.parent_id, tm.estimate_quantity*pm.price, 0 as total_actually_cost"
				+ " FROM tasks t inner join task_materials tm on t.id = tm.task_id inner join project_materials pm on tm.material_id = pm.material_id"
				+ " WHERE tm.task_id not in (select tmdt.task_id from task_material_daily_tracking tmdt)"
				+ " AND t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date AND t.is_trash = '0'";
	private final static String SELECT_BCWP = "SELECT 0 as project_id, 0 as task_id, monthly, 0 as yearly, 0 as total_planning_cost, sum(total_actually_cost) as total_actually_cost"
			   + " FROM (SELECT  0 as project_id, 0 as task_id, EXTRACT(month from tr.date_log) as monthly, 0 as yearly, 0 as total_planning_cost, tr.actual_time::numeric * tr.salary * tr.quantity::numeric AS total_actually_cost"
		       + " FROM task_resource_daily_tracking as tr INNER JOIN projects p ON p.id = tr.project_id"
		       + " WHERE (p.id = ? OR p.parent_id = ?) AND EXTRACT(year from tr.date_log) = ?"
		       + " UNION"
		       + " SELECT  0 as project_id, 0 as task_id, EXTRACT(month from te.date_log) as monthly, 0 as yearly, 0 as total_planning_cost, te.actual_time::numeric * te.price * te.quantity::numeric AS total_actually_cost"
		       + " FROM task_equipment_daily_tracking as te INNER JOIN projects p ON p.id = te.project_id"
		       + " WHERE (p.id = ? OR p.parent_id = ?) AND EXTRACT(year from te.date_log) = ?"
		       + " UNION"
		       + " SELECT  0 as project_id, 0 as task_id, EXTRACT(month from tm.date_log) as monthly, 0 as yearly, 0 as total_planning_cost, tm.actual_material::numeric * tm.price AS total_actually_cost"
		       + " FROM task_material_daily_tracking as tm INNER JOIN projects p ON p.id = tm.project_id"
		       + " WHERE (p.id = ? OR p.parent_id = ?) AND EXTRACT(year from tm.date_log) = ?) tr"
		       + " GROUP BY tr.monthly"	
		       + " ORDER BY tr.monthly";
	private static String SELECT_MATERIAL_IN_PROJECT = "SELECT m.code, m.material_name, m.unit_name, '' as task_name, null as start_date, null as end_date, '' as parent_id, sum(m.estimate_quantity) estimate_quantity, sum(m.actual_quantity) actual_quantity, m.net_price, m.price"
			   + " FROM(SELECT m.code, m.name as material_name, u.name as unit_name, tm.estimate_quantity, tm.actual_quantity, tmdt.net_price, tmdt.price"
			   + " FROM project_materials pm INNER JOIN materials m ON m.id = pm.material_id INNER JOIN task_materials tm ON pm.material_id = tm.material_id"
			   + " INNER JOIN units u ON m.unit = u.id INNER JOIN task_material_daily_tracking tmdt ON tm.task_id = tmdt.task_id and tm.material_id = tmdt.material_id INNER JOIN tasks t on t.id = tmdt.task_id"
			   + " WHERE pm.project_id = ? AND t.is_trash = '0'"
			   + " UNION"
			   + " SELECT m.code, m.name as material_name, u.name as unit_name, tm.estimate_quantity, tm.actual_quantity, pm.net_price, pm.price"
			   + " FROM project_materials pm LEFT JOIN materials m ON m.id = pm.material_id LEFT JOIN task_materials tm ON pm.material_id = tm.material_id"
			   + " LEFT JOIN units u ON m.unit = u.id"
			   + " WHERE pm.project_id = ? AND m.id  NOT IN ( SELECT tmdt.material_id FROM task_material_daily_tracking tmdt INNER JOIN tasks t ON t.id = tmdt.task_id WHERE t.is_trash = '0')) m"
			   + " GROUP BY m.code, m.material_name, m.unit_name, m.net_price, m.price"
			   + " ORDER BY m.material_name";
	private static String SELECT_MATERIAL_BY_DURATION = "SELECT m.code, m.name as material_name, tm.estimate_quantity, tm.actual_quantity, u.name as unit_name, t.task_name, t.start_date, t.end_date, t.parent_id, 0 as net_price, 0 as price"
			   + " FROM tasks t INNER JOIN task_materials tm ON t.id = tm.task_id INNER JOIN materials m ON m.id = tm.material_id INNER JOIN units u ON u.id = m.unit"
			   + " WHERE t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date AND t.is_trash = '0'"
			   + " ORDER BY t.wbs";
	private static String SELECT_MATERIAL_PARENT_BY_DURATION = "SELECT distinct t.wbs code, '' as material_name, 0 as estimate_quantity, 0 as actual_quantity, '' as unit_name, '' as task_name, null as start_date, null as end_date, '' as parent_id, 0 as net_price, 0 as price"
			   + " FROM tasks t INNER JOIN task_materials tm ON t.id = tm.task_id"
			   + " WHERE t.project_id = ? AND t.start_date >= ?::date AND t.end_date <= ?::date AND t.is_trash = '0'";
	private static String SELECT_LABOR_IN_PROJECT = "SELECT r.code, r.full_name as resource_name, r.total_actually_time, r.salary, 0 as total_planning_cost, 0 as total_actually_cost, null as start_date"
			   + " FROM(SELECT r.code, r.full_name, tr.quantity as total_actually_time, pr.salary, r.id"
			   + " FROM task_resources tr LEFT JOIN resources r on r.id = tr.resource_id LEFT JOIN project_resources pr on pr.resource_id = r.id"
			   + " WHERE pr.project_id = ? AND r.id  NOT IN (SELECT trdt.resource_id FROM task_resource_daily_tracking trdt INNER JOIN tasks t on t.id = trdt.task_id"
			   + " WHERE t.project_id = ? AND t.is_trash = '0')"
			   + " UNION"
			   + " SELECT r.code, r.full_name, trdt.quantity as total_actually_time, trdt.salary, r.id"
			   + " FROM project_resources pr INNER JOIN resources r on r.id = pr.resource_id INNER JOIN task_resource_daily_tracking trdt on pr.resource_id = trdt.resource_id"
			   + " AND pr.project_id = trdt.project_id INNER JOIN tasks t ON t.id = trdt.task_id"
			   + " WHERE pr.project_id = ? AND t.is_trash = '0') r"
			   + " ORDER BY r.full_name";
	private static String SELECT_EQUIPMENT_IN_PROJECT = "SELECT e.code, e.name as resource_name, e.quantity as total_actually_time, 0 as salary, e.net_price as total_planning_cost, e.price as total_actually_cost, null as start_date"
			   + " FROM(SELECT e.code, e.name, te.quantity, pe.net_price, pe.price"
			   + " FROM task_equipments te LEFT JOIN equipments e on e.id = te.equipment_id LEFT JOIN project_equipments pe on pe.equipment_id = e.id"
			   + " WHERE pe.project_id = ? AND e.id  NOT IN (SELECT tedt.equipment_id FROM task_equipment_daily_tracking tedt INNER JOIN tasks t on t.id = tedt.task_id"
			   + " WHERE t.project_id = ? AND t.is_trash = '0')"
			   + " UNION"
			   + " SELECT e.code, e.name, tedt.quantity, tedt.net_price, tedt.price"
			   + " FROM project_equipments pe INNER JOIN equipments e on e.id = pe.equipment_id INNER JOIN task_equipment_daily_tracking tedt on pe.equipment_id = tedt.equipment_id"
			   + " AND pe.project_id = tedt.project_id INNER JOIN tasks t ON t.id = tedt.task_id"
			   + " WHERE pe.project_id = ? AND t.is_trash = '0') e"
			   + " ORDER BY e.name";
	private static String SELECT_TIMESHEET_IN_PROJECT = "SELECT r.code, r.full_name as resource_name, trdt.date_log as start_date, sum(trdt.over_time) as salary, sum(trdt.actual_time) as total_actually_time, 0 as total_planning_cost, 0 as total_actually_cost"
			   + " FROM resources r INNER JOIN task_resource_daily_tracking trdt on r.id = trdt.resource_id"
			   + " WHERE trdt.project_id = ? AND EXTRACT(month from trdt.date_log) = ? AND EXTRACT(year from trdt.date_log) = ?"
			   + " GROUP BY r.code, r.full_name, trdt.date_log"
			   + " ORDER BY r.full_name, trdt.date_log";
	@Override
	public List<PlanningElement> getTaskParent(int projectId, String fromDate, String toDate) {
		List<PlanningElement> listTaskParent = null;
		try {
			listTaskParent = jdbcTemplate.query(SELECT_TASK_PARENT, new Object[] { projectId, fromDate, toDate }, new TaskCostMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listTaskParent;
	}
	@Override
	public List<PlanningElement> getSubTask(String wbs) {
		List<PlanningElement> listSubTask = null;
		try {
			listSubTask = jdbcTemplate.query(SELECT_TASK_NOT_SUB, new Object[] {"'" + wbs + "%'"}, new TaskCostMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listSubTask;
	}
	@Override
	public List<PlanningElement> calTaskCostByDuration(int projectId, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		List<PlanningElement> listTaskCostByDu = null;
		List<PlanningElement> listTaskCostP = null;
		try {
			listTaskCostByDu = jdbcTemplate.query(SELECT_TASK_COST_A_DU, new Object[] { projectId, fromDate, toDate, projectId, fromDate, toDate, projectId, fromDate, toDate }, new TaskCostMapper());
			listTaskCostP = jdbcTemplate.query(SELECT_TASK_COST_P_DU, new Object[] { projectId, fromDate, toDate, projectId, fromDate, toDate, projectId, fromDate, toDate, 
																					projectId, fromDate, toDate, projectId, fromDate, toDate, projectId, fromDate, toDate,
																					projectId, fromDate, toDate, projectId, fromDate, toDate, projectId, fromDate, toDate}, new TaskCostMapper());
			for(int i=0; i<listTaskCostP.size(); i++)
				listTaskCostByDu.add(listTaskCostP.get(i));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listTaskCostByDu;
	}
	@Override
	public List<PlanningElement> calBCWP(int projectId, int y) {
		List<PlanningElement> getBCWP = null;
		try {
			getBCWP = jdbcTemplate.query(SELECT_BCWP, new Object[] { projectId, projectId, y, projectId, projectId, y, projectId, projectId, y }, new CostExpensesPerMonthMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return getBCWP;
	}
	@Override
	public List<PlanningElement> getMaterialInProject(int projectId) {
		List<PlanningElement> listMaterial = null;
		try {
			listMaterial = jdbcTemplate.query(SELECT_MATERIAL_IN_PROJECT, new Object[] { projectId, projectId }, new MaterialUsageMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listMaterial;
	}
	@Override
	public List<PlanningElement> getMaterialByDuration(int projectId, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		List<PlanningElement> listMaterial = null;
		try {
			listMaterial = jdbcTemplate.query(SELECT_MATERIAL_BY_DURATION, new Object[] { projectId, fromDate, toDate }, new MaterialUsageMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listMaterial;
	}
	@Override
	public List<PlanningElement> getMaterialParentByDuration(int projectId, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		List<PlanningElement> listMaterialParent = null;
		try {
			listMaterialParent = jdbcTemplate.query(SELECT_MATERIAL_PARENT_BY_DURATION, new Object[] { projectId, fromDate, toDate }, new MaterialUsageMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listMaterialParent;
	}
	@Override
	public List<PlanningElement> getLaborInProject(int projectId) {
		// TODO Auto-generated method stub
		List<PlanningElement> listLabor = null;
		try {
			listLabor = jdbcTemplate.query(SELECT_LABOR_IN_PROJECT, new Object[] { projectId, projectId, projectId }, new ResourceCostMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listLabor;
	}
	@Override
	public List<PlanningElement> getEquipmentInProject(int projectId) {
		// TODO Auto-generated method stub
		List<PlanningElement> listEquipment = null;
		try {
			listEquipment = jdbcTemplate.query(SELECT_EQUIPMENT_IN_PROJECT, new Object[] { projectId, projectId, projectId }, new ResourceCostMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listEquipment;
	}
	@Override
	public List<PlanningElement> getTimeSheet(int projectId, int month, int year) {
		// TODO Auto-generated method stub
		List<PlanningElement> listTimeSheet = null;
		try {
			listTimeSheet = jdbcTemplate.query(SELECT_TIMESHEET_IN_PROJECT, new Object[] { projectId, month, year }, new ResourceCostMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listTimeSheet;
	}
	
}
