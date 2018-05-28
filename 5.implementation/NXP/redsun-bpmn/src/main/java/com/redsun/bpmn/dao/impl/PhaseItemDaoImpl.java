package com.redsun.bpmn.dao.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.redsun.bpmn.dao.PhaseItemDao;
import com.redsun.bpmn.dao.mapper.PhaseItemRowMapper;
import com.redsun.bpmn.entities.PhaseItem;
import com.redsun.bpmn.entities.PhaseStep;
import com.redsun.bpmn.entities.ResourceStep;

/**
 * Phase DAO implementation 
 */
@Repository
public class PhaseItemDaoImpl implements PhaseItemDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private PhaseItemRowMapper phaseItemMapper;
    
    private final static String AUTO_INCREMENTED_COLUMN = "id";
    
    private final String BPMN_ACTION_TYPE_REJECT = "reject";
    private final String BPMN_ACTION_TYPE_SUBMIT = "submit";
    
    private final static int[] SQL_INSERT_TYPES = new int[] { 
			java.sql.Types.INTEGER,  // project_id
			java.sql.Types.VARCHAR,  // active_step
			java.sql.Types.VARCHAR  // contain
	};
    
    private final static String SQL_INSERT = "insert into phase (project_id, active_step, contain) values (?, ?, ?)";

	
	private final static String SQL_SELECT_ALL = 
		"SELECT id, project_id, active_step, contain FROM phase ORDER BY id DESC";
	
	private final static String SQL_SELECT_ALL_BY_ID = 
		"SELECT id, project_id, active_step, contain FROM phase WHERE project_id = ?";
	
	private final static String SQL_UPDATE = 
		"update phase set active_step = ?, contain = ? WHERE project_id = ?";
	
	private final static String SQL_UPDATE_PHASE_DIAGRAM = 
		"update phase_diagram set active_step = ? WHERE project_id = ?";
	
	@Override
	public List<PhaseItem> getAllPhaseItem() {
		List<PhaseItem> result = jdbcTemplate.query(SQL_SELECT_ALL, phaseItemMapper);
		return result;
	}

	@Override
	public List<PhaseItem> getAllPhaseItemById(int projectId) {
		Object[] primaryKey = new Object[] { projectId };
		List<PhaseItem> result = jdbcTemplate.query(SQL_SELECT_ALL_BY_ID, primaryKey, phaseItemMapper);
		return result;
	}
	
	@Override
	public Map<String, Object> update(PhaseItem phaseItem, String currentStepId, String actionType) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(phaseItem));

		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		
		List<PhaseStep> lstPhase = convertJsonToList(phaseItem.getContain());
		PhaseStep currentStep = getStepByStepId(lstPhase, currentStepId);
		
		//Default continue step will be a next step
		PhaseStep continueStep = currentStep.getNextStep();
		if (continueStep == null) {
			// If continue step equal null => current step is End of Sub step
			// Get parent step of current step
			continueStep = getNextStepOfCurrentStep(lstPhase, currentStepId);
		}
		if (actionType.equals(BPMN_ACTION_TYPE_REJECT)) {
			String previousStep = currentStep.getPreviousStep();
			continueStep = getStepByStepId(lstPhase, previousStep);
			if (continueStep == null) {
				// Get parent step of current Step
				continueStep = getPreviousStepOfCurrentStep(lstPhase, currentStepId);
				// Get previous step of Parent Step
			}
		}
		
		List<ResourceStep> lstResourceOfContinueStep = continueStep.getResource();
		List<ResourceStep> lstResourceOfCurrentStep = currentStep.getResource();
		
		// Convert List Resource to List String to send email
		List<String> lstEmailOfContinueStep = prepareEmailForSending(lstResourceOfContinueStep);
		List<String> lstEmailOfCurrentStep = prepareEmailForSending(lstResourceOfCurrentStep);
		
		jdbcTemplate.update(SQL_UPDATE_PHASE_DIAGRAM, new Object[]{phaseItem.getActiveStep(), phaseItem.getIdProject()});
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("id", phaseItem.getId());
		resultMap.put("currentEmails", lstEmailOfCurrentStep);
		resultMap.put("continueEmails", lstEmailOfContinueStep);
		
		return resultMap;
	}
	
	// Prepare Email for sending Email. Converting List Of Resource to List of Email
	public List<String> prepareEmailForSending(List<ResourceStep> lstResource) {
		List<String> lstResult = new ArrayList<>();
		for (int i = 0; i < lstResource.size(); i++) {
			lstResult.add(lstResource.get(i).getEmailOfResponsible());
		}
		
		return lstResult;
	}
	
	// Get Next Step of current step, If Next Step has sub process=> Get the fist Step of Sub process
	public PhaseStep getNextStepOfCurrentStep(List<PhaseStep> lstPhase, String currentStepId) {
		PhaseStep ps = new PhaseStep();
		
		PhaseStep currentStep = getStepByStepId(lstPhase, currentStepId);
		// Get Parent step Id => Get Parent Step
		String parentStepId = currentStep.getParentStep();
		PhaseStep parentOfCurrentStep = getStepByStepId(lstPhase, parentStepId);
		
		// Continue step is the first sub step of Parent Step 
		// Get the Next Step of Parent Step, then => Get the first step of SubProcess of Next Step. It is continue step
		PhaseStep nextStepOfParentStep = parentOfCurrentStep.getNextStep();
		String nextStepOfParentStepId = nextStepOfParentStep.getIdStep();
		PhaseStep nextStep = getStepByStepId(lstPhase, nextStepOfParentStepId);
		
		ps = getTheFistStepOfSubProcess(nextStep);
		
		return ps;
	}
	
	// Get previous PhaseStep of Step, when Reject function is activated
	public PhaseStep getPreviousStepOfCurrentStep(List<PhaseStep> lstPhase, String currentStepId) {
		PhaseStep ps = new PhaseStep();
		
		PhaseStep currentStep = getStepByStepId(lstPhase, currentStepId);
		// Get Parent step Id => Get Parent Step
		String parentStepId = currentStep.getParentStep();
		PhaseStep parentOfCurrentStep = getStepByStepId(lstPhase, parentStepId);
		
		// Continue step is the first sub step of Parent Step
		// Get the Next Step of Parent Step, then => Get the first step of SubProcess of Next Step. It is continue step
		String previousStepOfParentStepId = parentOfCurrentStep.getPreviousStep();
		
		PhaseStep previousStepOfParentStep = getStepByStepId(lstPhase, previousStepOfParentStepId);
		ps = getTheEndOfSubProcess(previousStepOfParentStep);
		
		return ps;
	}
	
	// Return the first PhaseStep in SubProcess of a PhaseStep
	public PhaseStep getTheFistStepOfSubProcess(PhaseStep phaseStep) {
		PhaseStep psResult = new PhaseStep();
		
		// Has not sub process => The result is the phaseStep entered
		if (phaseStep.getSubSteps() == null) {
			psResult = phaseStep;
		} else {
			// Has sub level 1 => The Result is the First Step of SubStep level 1
			List<PhaseStep> lstLevel1 = phaseStep.getSubSteps();
			psResult = lstLevel1.get(0);
			
			// Has sub level 2 => Result is The first Step of SubStep level 2
			if (lstLevel1.get(0).getSubSteps() != null) {
				psResult = lstLevel1.get(0).getSubSteps().get(0);
			}
		}
		
		return psResult;
	}
	
	// Return the End PhaseStep in previous SubProcess when "Reject" function is activated
	public PhaseStep getTheEndOfSubProcess(PhaseStep phaseStep) {
		PhaseStep psResult = new PhaseStep();
		// Has not sub process => The result is the phaseStep entered
		if (phaseStep.getSubSteps() == null) {
			psResult = phaseStep;
		} else {
			// Has sub level 1 => The Result is the First Step of SubStep level 1
			
			List<PhaseStep> lstLevel1 = phaseStep.getSubSteps();
			int sizeLevel1 = lstLevel1.size();
			psResult = lstLevel1.get(sizeLevel1 - 1);
					
			// Has sub level 2 => Result is The first Step of SubStep level 2
			if (lstLevel1.get(0).getSubSteps() != null) {
				int sizeLevel2 = lstLevel1.get(0).getSubSteps().size();
				psResult = lstLevel1.get(sizeLevel1 - 1).getSubSteps().get(sizeLevel2 - 1);
			}
		}
		
		return psResult;
	}
	
	// Get Object PhaseStep by Step Id
	public PhaseStep getStepByStepId(List<PhaseStep> lstPhase, String currentStepId) {
		PhaseStep ps = new PhaseStep();
		for (int i = 0; i < lstPhase.size(); i++) {
			// Check main step firstly
			if (lstPhase.get(i).getIdStep().equals(currentStepId)) {
				ps = lstPhase.get(i);
				break;
			}
			// Check sub step of Main step
			if (lstPhase.get(i).getSubSteps() == null) {
				if (lstPhase.get(i).getIdStep().equals(currentStepId)) {
					ps = lstPhase.get(i);
					break;
				}
			} else { // Has sub process level 1
				List<PhaseStep> subLevel1 = lstPhase.get(i).getSubSteps();
				for (int j = 0; j < subLevel1.size(); j++) {
					if  (subLevel1.get(j).getSubSteps() == null) {
						if (subLevel1.get(j).getIdStep().equals(currentStepId)) {
							ps = subLevel1.get(j);
							break;
						}
					}
					else { // Has sub process level 2
						List<PhaseStep> subLevel2 = subLevel1.get(j).getSubSteps();
						for (int k = 0; k < subLevel2.size(); k++) {
							if (subLevel2.get(k).getIdStep().equals(currentStepId)) {
								ps = subLevel2.get(k);
								break;
							}
						}
					}
				}
			}
		}
		
		return ps;
	}
	
	// Convert json to List of PhaseStep
	public List<PhaseStep> convertJsonToList(String contain) {
		 Gson gson = new Gson();
		  
		 Type type = new TypeToken<List<PhaseStep>>() {}.getType();
	     List<PhaseStep> lstPhase = gson.fromJson(contain, type);
	     
	     return lstPhase;
	}
	
	
	protected Object[] getValuesForUpdate(final PhaseItem phaseItem) {
		return new Object[] {		
				phaseItem.getActiveStep(), 
				phaseItem.getContain(),
				phaseItem.getIdProject()
				
		};
	}

	@Override
	public int create(PhaseItem phaseItem) {
		int projectId = phaseItem.getIdProject();
		Object[] primaryKey = new Object[] { projectId };
				
		List<PhaseItem> lstPhaseItem = jdbcTemplate.query(SQL_SELECT_ALL_BY_ID, primaryKey, phaseItemMapper);
		int length = lstPhaseItem.size();
		
		if (length == 0) {
			// Create New Phase Item
			KeyHolder keyHolder = new GeneratedKeyHolder();	
			
			int result = jdbcTemplate.update(getPreparedStatementCreator(phaseItem), keyHolder);
		
			if (result != 1) {
				throw new RuntimeException("Unexpected return value after INSERT : " + result + " (1 expected) ");
			}
		
			Number key = keyHolder.getKey();
			if (key != null) {
				return key.intValue();
			} else {
				throw new RuntimeException("Cannot retrive generated key after INSERT : KeyHolder returns null");
			}
		} else {
			// Update exits phase item
			int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(phaseItem));
			
			if (result != 0 && result != 1) {
				throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
			}
			
			return phaseItem.getIdProject();
		}	
	}

	protected Object[] getValuesForInsert(final PhaseItem phaseItem) {
		return new Object[] { 
				phaseItem.getIdProject(), 
				phaseItem.getActiveStep(),
				phaseItem.getContain()
		};
	}
		
	private PreparedStatementCreator getPreparedStatementCreator(final PhaseItem phaseItem) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES);
		
		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert( phaseItem));
		
		return psc;
	}

}
