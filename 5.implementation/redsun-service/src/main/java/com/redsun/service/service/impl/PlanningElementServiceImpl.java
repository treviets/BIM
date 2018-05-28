package com.redsun.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.PMReportDao;
import com.redsun.service.dao.PlanningElementDao;
import com.redsun.service.entities.PlanningElement;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.TaskMaterials;
import com.redsun.service.service.PlanningElementService;
import com.redsun.service.utils.FunctionUtil;


@Service
public class PlanningElementServiceImpl implements PlanningElementService {

	@Autowired
	PlanningElementDao planningElementDao;
	@Autowired
	PMReportDao pmReportDao;

	@Override
	public Result listResourceCost(int projectId, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		final List<PlanningElement> listReCost = planningElementDao.listResourceCost(projectId, fromDate, toDate);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resourceCost", listReCost);
		return new Result(result, true);
	}

	@Override
	public Result listTaskCostByDuration(int projectId, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		final List<PlanningElement> listParentTaskCost = pmReportDao.getTaskParent(projectId, fromDate, toDate);
		final List<PlanningElement> listTaskCost = pmReportDao.calTaskCostByDuration(projectId, fromDate, toDate);
		int totalActualCost = 0;
		int totalPlanningCost = 0;
		if(listParentTaskCost.size()>0){
			for(int i=0; i<listParentTaskCost.size(); i++){
				totalActualCost = 0;
				totalPlanningCost = 0;
				for(int j=0; j<listTaskCost.size(); j++)
					if(listParentTaskCost.get(i).getTaskId()==listTaskCost.get(j).getTaskId()){
						List<PlanningElement> getSubTask = pmReportDao.getSubTask(listParentTaskCost.get(i).getWbs());
						if(getSubTask.size() == 0){
							totalActualCost += listTaskCost.get(j).getTotalActuallyCost();
							totalPlanningCost += listTaskCost.get(j).getTotalPlanningCost();
						}
					}
				listParentTaskCost.get(i).setTotalActuallyCost(totalActualCost);
				listParentTaskCost.get(i).setTotalPlanningCost(totalPlanningCost);
			}
			for(int i=0; i<listParentTaskCost.size(); i++){
				totalActualCost = listParentTaskCost.get(i).getTotalActuallyCost();
				totalPlanningCost = listParentTaskCost.get(i).getTotalPlanningCost();
				for(int j=0; j<listTaskCost.size(); j++)
					if(listParentTaskCost.get(i).getWbs().equals(listTaskCost.get(j).getParent_id())){
						totalActualCost += listTaskCost.get(j).getTotalActuallyCost();
						totalPlanningCost += listTaskCost.get(j).getTotalPlanningCost();
					}
				listParentTaskCost.get(i).setTotalActuallyCost(totalActualCost);
				listParentTaskCost.get(i).setTotalPlanningCost(totalPlanningCost);
			}
		}
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskCost", listParentTaskCost);
		return new Result(result, true);
	}

	@Override
	public Result listCostPerMonth(int projectId, int m, int y) {
		// TODO Auto-generated method stub
		final List<PlanningElement> listPCostExpenses = new ArrayList<PlanningElement>();
		final List<PlanningElement> listCostExpenses = planningElementDao.listACostPerMonth(projectId, m, y);
		//final List<PlanningElement> listACostExpenses = planningElementDao.listACostPerMonth(projectId, m, y);
		final List<PlanningElement> listPCostInMonth = planningElementDao.listPCostPerMonth(projectId, m, y);
		final List<PlanningElement> listPCostPerMonths = planningElementDao.listPCostPerMonth(projectId, y);
		final List<PlanningElement> listBCWP = pmReportDao.calBCWP(projectId, y);
		Calendar cal = Calendar.getInstance();
		int totalPCost = 0;
		if(m>0){
			for(int i=0; i<listPCostInMonth.size(); i++)
				totalPCost += listPCostInMonth.get(i).getTotalPlanningCost();
		}
		else if(m==0){
			if(listPCostInMonth.size()>0){
				int month = listPCostInMonth.get(0).getMonthly();
				totalPCost = listPCostInMonth.get(0).getTotalPlanningCost();
				PlanningElement pe = new PlanningElement();
				for(int i=1; i<listPCostInMonth.size(); i++){
					if(listPCostInMonth.get(i).getMonthly() == month)
						totalPCost += listPCostInMonth.get(i).getTotalPlanningCost();
					else{
						pe.setMonthly(month);
						pe.setTotalPlanningCost(totalPCost);
						listPCostExpenses.add(pe);
						pe = new PlanningElement();
						month = listPCostInMonth.get(i).getMonthly();
						totalPCost = listPCostInMonth.get(i).getTotalPlanningCost();
					}
				}
				pe.setMonthly(month);
				pe.setTotalPlanningCost(totalPCost);
				listPCostExpenses.add(pe);
			}
		}
		totalPCost = 0;
		int startMonth = 0;
		int startDay = 0;
		int endMonth = 0;
		int endDay = 0;
		for(int p=0; p<listPCostPerMonths.size(); p++){
			cal.setTime(listPCostPerMonths.get(p).getStartDate());
			startMonth = cal.get(Calendar.MONTH) + 1;
			startDay = cal.get(Calendar.DATE);
			cal.setTime(listPCostPerMonths.get(p).getEndDate());
			endMonth = cal.get(Calendar.MONTH) + 1;
			endDay = cal.get(Calendar.DATE);
			if(m>0){
				for(int k=startMonth; k<=endMonth; k++){
					if(k == startMonth){
						if(m == k){
							 totalPCost += (FunctionUtil.getDayInMonth(startMonth, y) - startDay + 1) * listPCostPerMonths.get(p).getPlanningCost();
						}
					}
					else if(k == endMonth){ 
							if(m == endMonth)
								totalPCost += endDay * listPCostPerMonths.get(p).getPlanningCost();
					}
					else{
						if(m==k)
							totalPCost += FunctionUtil.getDayInMonth(k, y)*listPCostPerMonths.get(p).getPlanningCost();
					}
				}
				if(listCostExpenses.size()>0)
					listCostExpenses.get(0).setTotalPlanningCost(totalPCost);
				else{
					PlanningElement pe = new PlanningElement();
					pe.setMonthly(m);
					pe.setTotalPlanningCost(totalPCost);
					pe.setTotalActuallyCost(0);
					listCostExpenses.add(pe);
				}
			}
			else if(m==0){
				//double remainPercent = 0.0;
				for(int k=startMonth; k<=endMonth; k++){
					totalPCost = 0;
					double percent = 0.0;
					if(k == startMonth){
						totalPCost += (FunctionUtil.getDayInMonth(startMonth, y) - startDay + 1) * listPCostPerMonths.get(p).getPlanningCost();
					}	
					else if(k == endMonth){ 
						totalPCost += endDay * listPCostPerMonths.get(p).getPlanningCost();
					}
					else{
						totalPCost += FunctionUtil.getDayInMonth(k, y)*listPCostPerMonths.get(p).getPlanningCost();
					}
					PlanningElement pe = new PlanningElement();
					pe.setMonthly(k);
					pe.setTotalPlanningCost(totalPCost);
					int flag = 0;
					for(int n=0; n<listPCostExpenses.size(); n++){
						if(listPCostExpenses.get(n).getMonthly()==k){
							totalPCost += listPCostExpenses.get(n).getTotalPlanningCost();
							listPCostExpenses.get(n).setTotalPlanningCost(totalPCost);
							flag = 1;
							break;
						}
					}
					if(flag == 0)
						listPCostExpenses.add(pe);
				}
				
			}
		}
		for(int i=0; i<listPCostExpenses.size(); i++){
			int flag = 0;
			for(int j=0; j<listCostExpenses.size(); j++){
				if(listPCostExpenses.get(i).getMonthly()==listCostExpenses.get(j).getMonthly()){
					listCostExpenses.get(j).setTotalPlanningCost(listCostExpenses.get(j).getTotalPlanningCost() + listPCostExpenses.get(i).getTotalPlanningCost());
					flag = 1;
					break;
				}	
			}
			if(flag==0)
				listCostExpenses.add(listPCostExpenses.get(i));
		}
		
		for(int i=0; i<listCostExpenses.size(); i++){
			double costBCWP = 0.0;
			for(int j=0; j<listBCWP.size(); j++){
				if(listBCWP.get(j).getMonthly()==listCostExpenses.get(i).getMonthly()){
					costBCWP += listBCWP.get(j).getTotalActuallyCost();
				}
			}
			listCostExpenses.get(i).setBCWP(costBCWP);
		}
		//SV, CV
		for(int i=0; i<listCostExpenses.size(); i++){
			listCostExpenses.get(i).setSV(listCostExpenses.get(i).getBCWP() - listCostExpenses.get(i).getTotalPlanningCost());
			listCostExpenses.get(i).setCV(listCostExpenses.get(i).getBCWP() - listCostExpenses.get(i).getTotalActuallyCost());
			if(listCostExpenses.get(i).getTotalActuallyCost() != 0)
				listCostExpenses.get(i).setCPI(listCostExpenses.get(i).getBCWP()/listCostExpenses.get(i).getTotalActuallyCost());
			if(listCostExpenses.get(i).getTotalPlanningCost() !=0 )
				listCostExpenses.get(i).setSPI(listCostExpenses.get(i).getBCWP()/listCostExpenses.get(i).getTotalPlanningCost());
		}
		
		Collections.sort(listCostExpenses, new Comparator<PlanningElement>() {

	        public int compare(PlanningElement o1, PlanningElement o2) {
	        	Integer p1 = o1.getMonthly();
	        	Integer p2 = o2.getMonthly();
	            return p1.compareTo(p2);
	        }
	    });
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listCostExpenses", listCostExpenses);
		return new Result(result, true);
	}

	@Override
	public Result listPCostPerMonth(int projectId, int m, int y) {
		// TODO Auto-generated method stub
		final List<PlanningElement> listCostExpenses = planningElementDao.listPCostPerMonth(projectId, m, y);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listPCostExpenses", listCostExpenses);
		return new Result(result, true);
	}

	@Override
	public Result listMaterialUsage(int projectId) {
		// TODO Auto-generated method stub
		final List<PlanningElement> listMaterials = pmReportDao.getMaterialInProject(projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listMaterials", listMaterials);
		return new Result(result, true);
	}

	@Override
	public Result getMaterialByDuration(int projectId, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		final List<PlanningElement> listMaterials = new ArrayList<PlanningElement>();
		final List<PlanningElement> listAllMaterials = pmReportDao.getMaterialByDuration(projectId, fromDate, toDate);
		final List<PlanningElement> listMaterialsParent = pmReportDao.getMaterialParentByDuration(projectId, fromDate, toDate);
		int flag = 0;
		for(int i=0; i<listAllMaterials.size(); i++){
			if(listAllMaterials.get(i).getParent_id() != null){
				flag = 0;
				for(int j=0; j<listMaterialsParent.size()&&flag==0; j++)
					if(listAllMaterials.get(i).getParent_id().equals(listMaterialsParent.get(j).getCode()))
						flag = 1;
				if(flag == 0)
					listMaterials.add(listAllMaterials.get(i));
			}
			else
				listMaterials.add(listAllMaterials.get(i));
		}
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listMaterials", listMaterials);
		return new Result(result, true);
	}

	@Override
	public Result getLaborInProject(int projectId) {
		// TODO Auto-generated method stub
		final List<PlanningElement> listLabors = pmReportDao.getLaborInProject(projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listLabors", listLabors);
		return new Result(result, true);
	}

	@Override
	public Result getEquipmentInProject(int projectId) {
		// TODO Auto-generated method stub
		final List<PlanningElement> listEquipments = pmReportDao.getEquipmentInProject(projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listEquipments", listEquipments);
		return new Result(result, true);
	}

	@Override
	public Result getTimeSheet(int projectId, int m, int y) {
		// TODO Auto-generated method stub
		final List<PlanningElement> listTimeSheet = pmReportDao.getTimeSheet(projectId, m, y);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listTimeSheet", listTimeSheet);
		return new Result(result, true);
	}
}
