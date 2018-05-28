package com.redsun.service.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.redsun.service.dao.PlanningElementDao;
import com.redsun.service.dao.mapper.CostExpensesPerMonthMapper;
import com.redsun.service.dao.mapper.MaterialUsageMapper;
import com.redsun.service.dao.mapper.ResourceCostMapper;
import com.redsun.service.dao.mapper.TaskCostPerMonthMapper;
import com.redsun.service.entities.PlanningElement;
import com.redsun.service.entities.TaskMaterials;



@Repository
public class PlanningElementDaoImpl implements PlanningElementDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	List<PlanningElement> listResourceCost = null;
	static private String SELECT_RESOURCE_COST_A =   "SELECT  rpe.code code,  rpe.resource_name, rpe.salary, sum(rpe.actual_time) as total_actually_time, 0 as total_planning_cost, sum(rpe.actually_cost) as total_actually_cost"
		       + " FROM resource_planning_elements as rpe"
		       + " WHERE (rpe.project_id = ? OR rpe.project_parent = ?) AND rpe.start_date >= ?::date AND rpe.end_date <= ?::date AND rpe.code is not null"
		       + " GROUP BY rpe.code,  rpe.resource_name, rpe.salary"
		       + " ORDER BY code";
	static private String SELECT_EQUIPMENT_COST_A = " SELECT  epe.code code,  epe.resource_name, epe.price, sum(epe.actual_time) as total_actually_time, 0 as total_planning_cost, sum(epe.actually_cost) as total_actually_cost"
		       + " FROM equipment_planning_elements as epe"
		       + " WHERE (epe.project_id = ? OR epe.project_parent = ?) AND epe.start_date >= ?::date AND epe.end_date <= ?::date AND epe.code is not null"
		       + " GROUP BY epe.code,  epe.resource_name,  epe.price"
		       + " ORDER BY code";
	static private String SELECT_RESOURCE_COST_P =   "SELECT distinct r.code code, r.full_name as resource_name, 0 as salary, t.id as total_actually_time, t.estimate_time::numeric * tr.salary * tr.quantity as total_planning_cost, 0 as total_actually_cost"
		       + " FROM tasks as t"
		       + " LEFT JOIN projects p ON t.project_id = p.id"
		       + " LEFT JOIN task_resource_daily_tracking tr ON t.id = tr.task_id"
		       + " LEFT JOIN resources r ON tr.resource_id = r.id"
		       + " WHERE (p.id = ? OR p.parent_id = ?) AND t.start_date >= ?::date AND t.end_date <= ?::date AND r.code is not null"
		       + " ORDER BY code";
	static private String SELECT_EQUIPMENT_COST_P =   "SELECT distinct r.code code, r.name as resource_name, 0 as salary, 0 as total_actually_time, t.estimate_time::numeric * tr.price * tr.quantity as total_planning_cost, 0 as total_actually_cost"
		       + " FROM tasks as t"
		       + " LEFT JOIN projects p ON t.project_id = p.id"
		       + " LEFT JOIN task_equipment_daily_tracking tr ON t.id = tr.task_id"
		       + " LEFT JOIN equipments r ON tr.equipment_id = r.id"
		       + " WHERE (p.id = ? OR p.parent_id = ?) AND t.start_date >= ?::date AND t.end_date <= ?::date AND r.code is not null"
		       + " ORDER BY code";
	static private String SELECT_COST_PLAN_PER_MONTH = "SELECT distinct tr.resource_id as project_id, t.id as task_id, EXTRACT(month from t.start_date) monthly, EXTRACT(year from t.start_date) yearly, t.estimate_time::numeric * tr.salary * tr.quantity as total_planning_cost, 0 as total_actually_cost"
			   + " FROM tasks as t INNER JOIN projects p ON t.project_id = p.id INNER JOIN task_resource_daily_tracking tr ON t.id = tr.task_id"
			   + " WHERE (p.id = ? OR p.parent_id = ?) AND EXTRACT(month from t.start_date) = EXTRACT(month from t.end_date)"
			   + " AND EXTRACT(year from t.start_date) = EXTRACT(year from t.end_date)"
			   + " AND EXTRACT(month from t.start_date) = ? AND EXTRACT(year from t.start_date) = ?"
			   + " UNION"
			   + " SELECT distinct te.equipment_id as project_id, t.id as task_id, EXTRACT(month from t.start_date) monthly, EXTRACT(year from t.start_date) yearly, t.estimate_time::numeric * te.price * te.quantity as total_planning_cost, 0 as total_actually_cost"
			   + " FROM tasks as t INNER JOIN projects p ON t.project_id = p.id INNER JOIN task_equipment_daily_tracking te ON t.id = te.task_id"
			   + " WHERE (p.id = ? OR p.parent_id = ?) AND EXTRACT(month from t.start_date) = EXTRACT(month from t.end_date)"
			   + " AND EXTRACT(year from t.start_date) = EXTRACT(year from t.end_date)"
			   + " AND EXTRACT(month from t.start_date) = ? AND EXTRACT(year from t.start_date) = ?"
			   + " UNION"
			   + " SELECT distinct tm.material_id as project_id, t.id as task_id, EXTRACT(month from t.start_date) monthly, EXTRACT(year from t.start_date) yearly, t.estimate_time::numeric * tm.price * tm.actual_material as total_planning_cost, 0 as total_actually_cost"
			   + " FROM tasks as t INNER JOIN projects p ON t.project_id = p.id INNER JOIN task_material_daily_tracking tm ON t.id = tm.task_id"
			   + " WHERE (p.id = ? OR p.parent_id = ?) AND EXTRACT(month from t.start_date) = EXTRACT(month from t.end_date)"
			   + " AND EXTRACT(year from t.start_date) = EXTRACT(year from t.end_date)"
			   + " AND EXTRACT(month from t.start_date) = ? AND EXTRACT(year from t.start_date) = ?";
	
	static private String SELECT_COST_PLAN_PER_MONTH_NO_TRACKING = "SELECT tr.resource_id as project_id, t.id as task_id, EXTRACT(month from t.start_date) monthly, EXTRACT(year from t.start_date) yearly, t.estimate_time::numeric * pr.salary * pr.quantity as total_planning_cost, 0 as total_actually_cost"
			   + " FROM tasks as t INNER JOIN projects p ON t.project_id = p.id INNER JOIN task_resources tr ON t.id = tr.task_id INNER JOIN project_resources pr ON pr.resource_id = tr.resource_id AND pr.project_id = tr.project_id"
			   + " WHERE t.id NOT IN (SELECT task_id FROM task_resource_daily_tracking) AND (p.id = ? OR p.parent_id = ?) AND EXTRACT(month from t.start_date) = EXTRACT(month from t.end_date)"
			   + " AND EXTRACT(year from t.start_date) = EXTRACT(year from t.end_date)"
			   + " AND EXTRACT(month from t.start_date) = ? AND EXTRACT(year from t.start_date) = ?"
			   + " UNION"
			   + " SELECT te.equipment_id as project_id, t.id as task_id, EXTRACT(month from t.start_date) monthly, EXTRACT(year from t.start_date) yearly, t.estimate_time::numeric * pe.price * pe.quantity as total_planning_cost, 0 as total_actually_cost"
			   + " FROM tasks as t INNER JOIN projects p ON t.project_id = p.id INNER JOIN task_equipments te ON t.id = te.task_id INNER JOIN project_equipments pe ON pe.equipment_id = te.equipment_id AND pe.project_id = te.project_id"
			   + " WHERE t.id NOT IN (SELECT task_id FROM task_equipment_daily_tracking) AND (p.id = ? OR p.parent_id = ?) AND EXTRACT(month from t.start_date) = EXTRACT(month from t.end_date)"
			   + " AND EXTRACT(year from t.start_date) = EXTRACT(year from t.end_date)"
			   + " AND EXTRACT(month from t.start_date) = ? AND EXTRACT(year from t.start_date) = ?"
			   + " UNION"
			   + " SELECT tm.material_id as project_id, t.id as task_id, EXTRACT(month from t.start_date) monthly, EXTRACT(year from t.start_date) yearly, tm.estimate_quantity::numeric * pm.price as total_planning_cost, 0 as total_actually_cost"
			   + " FROM tasks as t INNER JOIN projects p ON t.project_id = p.id INNER JOIN task_materials tm ON t.id = tm.task_id INNER JOIN project_materials pm ON pm.material_id = tm.material_id AND pm.project_id = tm.project_id"
			   + " WHERE t.id NOT IN (SELECT task_id FROM task_material_daily_tracking) AND (p.id = ? OR p.parent_id = ?) AND EXTRACT(month from t.start_date) = EXTRACT(month from t.end_date)"
			   + " AND EXTRACT(year from t.start_date) = EXTRACT(year from t.end_date)"
			   + " AND EXTRACT(month from t.start_date) = ? AND EXTRACT(year from t.start_date) = ?";
	
	static private String SELECT_COST_PLAN_ALL_IN_MONTH = "SELECT distinct tr.resource_id as project_id, t.id as task_id, EXTRACT(month from t.start_date) monthly, EXTRACT(year from t.start_date) yearly, t.estimate_time::numeric * tr.salary * tr.quantity as total_planning_cost, 0 as total_actually_cost"
			   + " FROM tasks as t INNER JOIN projects p ON t.project_id = p.id INNER JOIN task_resource_daily_tracking tr ON t.id = tr.task_id"
			   + " WHERE (p.id = ? OR p.parent_id = ?) AND EXTRACT(month from t.start_date) = EXTRACT(month from t.end_date)"
			   + " AND EXTRACT(year from t.start_date) = EXTRACT(year from t.end_date) AND EXTRACT(year from t.start_date) = ?"
			   + " UNION"
			   + " SELECT tr.resource_id as project_id, t.id as task_id, EXTRACT(month from t.start_date) monthly, EXTRACT(year from t.start_date) yearly, t.estimate_time::numeric * pr.salary * pr.quantity as total_planning_cost, 0 as total_actually_cost"
			   + " FROM tasks as t INNER JOIN projects p ON t.project_id = p.id INNER JOIN task_resources tr ON t.id = tr.task_id INNER JOIN project_resources pr ON pr.resource_id = tr.resource_id AND pr.project_id = tr.project_id"
			   + " WHERE t.id NOT IN (SELECT task_id FROM task_resource_daily_tracking) AND (p.id = ? OR p.parent_id = ?) AND EXTRACT(month from t.start_date) = EXTRACT(month from t.end_date)"
			   + " AND EXTRACT(year from t.start_date) = EXTRACT(year from t.end_date)"
			   + " AND EXTRACT(year from t.start_date) = ?"
			   + " ORDER BY monthly";
	
	static private String SELECT_COST_PLAN_ALL_MONTHS = "SELECT t.id, t.start_date, t.end_date, t.completed, t.estimate_time, sum(t.planning_cost) as planning_cost"
			   + " FROM(SELECT distinct t.id, t.start_date, t.end_date, t.completed, t.estimate_time, tr.salary * tr.quantity as planning_cost"
			   + " FROM tasks as t INNER JOIN projects p ON t.project_id = p.id INNER JOIN task_resource_daily_tracking tr ON t.id = tr.task_id"
			   + " WHERE (p.id = ? OR p.parent_id = ?) AND EXTRACT(month from t.start_date) <> EXTRACT(month from t.end_date)"
			   + " AND EXTRACT(year from t.start_date) = EXTRACT(year from t.end_date) AND EXTRACT(year from t.start_date) = ?) as t"
	 		   + " GROUP BY t.id, t.start_date, t.end_date, t.completed, t.estimate_time"
			   + " UNION"
	 		   + " SELECT t.id, t.start_date, t.end_date, t.completed, t.estimate_time, sum(pr.salary * tr.quantity) as planning_cost"
			   + " FROM tasks as t INNER JOIN projects p ON t.project_id = p.id INNER JOIN task_resources tr ON t.id = tr.task_id INNER JOIN project_resources pr ON pr.resource_id = tr.resource_id AND pr.project_id = tr.project_id"
	 		   + " WHERE t.id NOT IN (SELECT task_id FROM task_resource_daily_tracking) AND (p.id = ? OR p.parent_id = ?) AND EXTRACT(month from t.start_date) <> EXTRACT(month from t.end_date)"
			   + " AND EXTRACT(year from t.start_date) = EXTRACT(year from t.end_date) AND EXTRACT(year from t.start_date) =?"
	 		   + " GROUP BY t.id, t.start_date, t.end_date, t.completed, t.estimate_time";
	
	static private String SELECT_COST_ACTUAL_PER_MONTH = "SELECT 0 as project_id, 0 as task_id, ac.monthly, ac.yearly, 0 total_planning_cost, sum(ac.total_actually_cost) as total_actually_cost"
			   + " FROM(SELECT 0 as project_id, 0 as task_id, EXTRACT(month from trdt.date_log) monthly, EXTRACT(year from trdt.date_log) yearly, 0 total_planning_cost,"
			   + " (trdt.actual_time*trdt.salary + trdt.over_time * trdt.salary * trdt.coefficient)*trdt.quantity total_actually_cost"
			   + " FROM task_resource_daily_tracking trdt INNER JOIN projects p on p.id = trdt.project_id"
			   + " WHERE (trdt.project_id = ? OR p.parent_id = ?) AND EXTRACT(month from trdt.date_log) = ? AND EXTRACT(year from trdt.date_log) = ?"
			   + " UNION"
			   + " SELECT 0 as project_id, 0 as task_id, EXTRACT(month from tedt.date_log) monthly, EXTRACT(year from tedt.date_log) yearly, 0 total_planning_cost, tedt.actual_time*tedt.price*tedt.quantity total_actually_cost"
			   + " FROM task_equipment_daily_tracking tedt INNER JOIN projects p on p.id = tedt.project_id"
			   + " WHERE (tedt.project_id = ? OR p.parent_id = ?) AND EXTRACT(month from tedt.date_log) = ? AND EXTRACT(year from tedt.date_log) = ?"
			   + " UNION"
			   + " SELECT  0 as project_id, 0 as task_id, EXTRACT(month from tmdt.date_log) monthly, EXTRACT(year from tmdt.date_log) yearly, 0 total_planning_cost, tmdt.actual_material::numeric * tmdt.price AS total_actually_cost"
			   + " FROM task_material_daily_tracking as tmdt INNER JOIN projects p ON p.id = tmdt.project_id"
			   + " WHERE (p.id = ? OR p.parent_id = ?) AND EXTRACT(month from tmdt.date_log) = ? AND EXTRACT(year from tmdt.date_log) = ?) ac"
			   + " GROUP BY ac.monthly, ac.yearly"
	 		   + " ORDER BY ac.monthly, ac.yearly";
	
	static private String SELECT_COST_ACTUAL_ALL_MONTH = "SELECT 0 as project_id, 0 as task_id, ac.monthly, ac.yearly, 0 total_planning_cost, sum(ac.total_actually_cost) as total_actually_cost"
			   + " FROM(SELECT 0 as project_id, 0 as task_id, EXTRACT(month from trdt.date_log) monthly, EXTRACT(year from trdt.date_log) yearly, 0 total_planning_cost,"
			   + " (trdt.actual_time*trdt.salary + trdt.over_time * trdt.salary * trdt.coefficient)*trdt.quantity total_actually_cost"
			   + " FROM task_resource_daily_tracking trdt INNER JOIN projects p on p.id = trdt.project_id"
			   + " WHERE (trdt.project_id = ? OR p.parent_id = ?) AND EXTRACT(year from trdt.date_log) = ?"
			   + " UNION"
			   + " SELECT 0 as project_id, 0 as task_id, EXTRACT(month from tedt.date_log) monthly, EXTRACT(year from tedt.date_log) yearly, 0 total_planning_cost, tedt.actual_time*tedt.price*tedt.quantity total_actually_cost"
			   + " FROM task_equipment_daily_tracking tedt INNER JOIN projects p on p.id = tedt.project_id"
			   + " WHERE (tedt.project_id = ? OR p.parent_id = ?) AND EXTRACT(year from tedt.date_log) = ?"
			   + " UNION"
			   + " SELECT  0 as project_id, 0 as task_id, EXTRACT(month from tmdt.date_log) monthly, EXTRACT(year from tmdt.date_log) yearly, 0 total_planning_cost, tmdt.actual_material::numeric * tmdt.price AS total_actually_cost"
			   + " FROM task_material_daily_tracking as tmdt INNER JOIN projects p ON p.id = tmdt.project_id"
			   + " WHERE (p.id = ? OR p.parent_id = ?) AND EXTRACT(year from tmdt.date_log) = ?) ac"
			   + " GROUP BY ac.monthly, ac.yearly"
	 		   + " ORDER BY ac.monthly, ac.yearly";
	
	private static String SELECT_ALL_TASK_MATERIAL = "SELECT mp.code, mp.resource_name as name, mp.unit, mp.estimate_quantity"
			+ " FROM material_planning_elements mp"
			+ " WHERE mp.project_id = ?";
	
	private static Logger log = Logger.getLogger(PlanningElementDaoImpl.class);

	@Override
	public List<PlanningElement> listResourceCost(int projectId, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		List<PlanningElement> listEqCost = null;
		List<PlanningElement> listResourceCostP = null;
		try {
			listResourceCost = jdbcTemplate.query(SELECT_RESOURCE_COST_A, new Object[] { projectId, projectId, fromDate, toDate }, new ResourceCostMapper());
			listResourceCostP = jdbcTemplate.query(SELECT_RESOURCE_COST_P, new Object[] { projectId, projectId, fromDate, toDate }, new ResourceCostMapper());
			if(listResourceCost.size()>0){
				for(int i=0; i<listResourceCost.size(); i++){
					int costP = 0;
					if(listResourceCostP.size()>0)
						for(int j=0; j<listResourceCostP.size(); j++)
							if(listResourceCost.get(i).getCode().equals(listResourceCostP.get(j).getCode()))
								costP += listResourceCostP.get(j).getTotalPlanningCost();
							listResourceCost.get(i).setTotalPlanningCost(costP);
				}
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		try {
			listEqCost = jdbcTemplate.query(SELECT_EQUIPMENT_COST_A, new Object[] { projectId, projectId, fromDate, toDate }, new ResourceCostMapper());
			listResourceCostP = jdbcTemplate.query(SELECT_EQUIPMENT_COST_P, new Object[] { projectId, projectId, fromDate, toDate }, new ResourceCostMapper());
			if(listResourceCost.size()>0){
				for(int i=0; i<listResourceCost.size(); i++)
					if(listResourceCostP.size()>0)
						for(int j=0; j<listResourceCostP.size(); j++)
							if(listResourceCost.get(i).getCode().equals(listResourceCostP.get(j)))
								listResourceCost.get(i).setTotalPlanningCost(listResourceCostP.get(j).getTotalPlanningCost());
			}
			for(PlanningElement pe : listEqCost)
				listResourceCost.add(pe);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listResourceCost;
	}
	
	@Override
	public List<PlanningElement> listACostPerMonth(int projectId, int m, int y) {
		// TODO Auto-generated method stub
		List<PlanningElement> listCostExpenses = null;
		try {
			if(m == 0)
				listCostExpenses = jdbcTemplate.query(SELECT_COST_ACTUAL_ALL_MONTH, new Object[] { projectId, projectId, y, projectId, projectId, y, projectId, projectId, y }, new CostExpensesPerMonthMapper());
			else
				listCostExpenses = jdbcTemplate.query(SELECT_COST_ACTUAL_PER_MONTH, new Object[] { projectId, projectId, m, y, projectId, projectId, m, y, projectId, projectId, m, y }, new CostExpensesPerMonthMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listCostExpenses;
	}
	
	@Override
	public List<PlanningElement> listPCostPerMonth(int projectId, int m, int y) {
		// TODO Auto-generated method stub
		List<PlanningElement> listCostExpenses = null;
		try {
			if(m==0){
				listCostExpenses = jdbcTemplate.query(SELECT_COST_PLAN_ALL_IN_MONTH, new Object[] { projectId, projectId, y, projectId, projectId, y }, new CostExpensesPerMonthMapper());
				//List<PlanningElement> listPCostAllNoTrack = jdbcTemplate.query(SELECT_COST_PLAN_ALL_IN_MONTH_NO_TRACKING, new Object[] { projectId, projectId, y }, new CostExpensesPerMonthMapper());
				//for(int i=0; i<listPCostAllNoTrack.size(); i++)
					//listCostExpenses.add(listPCostAllNoTrack.get(i));
			}
			else{
				List<PlanningElement> listPCostNoTrack = jdbcTemplate.query(SELECT_COST_PLAN_PER_MONTH_NO_TRACKING, new Object[] { projectId, projectId, m, y }, new CostExpensesPerMonthMapper());;
				listCostExpenses = jdbcTemplate.query(SELECT_COST_PLAN_PER_MONTH, new Object[] { projectId, projectId, m, y, projectId, projectId, m, y, projectId, projectId, m, y }, new CostExpensesPerMonthMapper());
				for(int i=0; i<listPCostNoTrack.size(); i++)
					listCostExpenses.add(listPCostNoTrack.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listCostExpenses;
	}
	
	@Override
	public List<PlanningElement> listPCostPerMonth(int projectId, int y) {
		// TODO Auto-generated method stub
		List<PlanningElement> listPCostPerMonths = null;
		try {
			listPCostPerMonths = jdbcTemplate.query(SELECT_COST_PLAN_ALL_MONTHS, new Object[] { projectId, projectId, y, projectId, projectId, y }, new TaskCostPerMonthMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listPCostPerMonths;
	}
	
	@Override
	public List<PlanningElement> listMaterialUsage(int projectId) {
		// TODO Auto-generated method stub
		List<PlanningElement> listMaterial = null;
		try {
			listMaterial = jdbcTemplate.query(SELECT_ALL_TASK_MATERIAL, new Object[] { projectId}, new MaterialUsageMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listMaterial;
	}
}
