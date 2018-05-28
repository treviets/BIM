package com.redsun.bpmn.dao.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.Gson;
import com.redsun.bpmn.dao.PhaseDiagramDao;
import com.redsun.bpmn.dao.mapper.PhaseDiagramRowMapper;
import com.redsun.bpmn.entities.DocumentStep;
import com.redsun.bpmn.entities.PhaseDiagram;
import com.redsun.bpmn.entities.PhaseItem;
import com.redsun.bpmn.entities.PhaseStep;
import com.redsun.bpmn.entities.ResourceStep;
/**
 * Phase DAO implementation 
 */
@Repository
public class PhaseDiagramDaoImpl implements PhaseDiagramDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private PhaseDiagramRowMapper phaseDiagramMapper;
    
    private final static String AUTO_INCREMENTED_COLUMN = "id";
    private final static int[] SQL_INSERT_TYPES = new int[] { 
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.VARCHAR, // active_step
			java.sql.Types.VARCHAR  // contain
	};
    
    public List<PhaseStep> listOfPhaseStep;
    
    private final static String SQL_INSERT = 
    	"insert into phase_diagram (project_id, active_step, contain) values (?, ?, ?)";

	private final static String SQL_SELECT_ALL = 
		"SELECT id, project_id, active_step, contain FROM phase_diagram ORDER BY id ASC";
	
	private final static String SQL_SELECT_ALL_BY_ID = 
		"SELECT id, project_id, active_step, contain FROM phase_diagram WHERE project_id = ?";
	
	private final static String SQL_UPDATE = 
		"update phase_diagram set active_step = ?, contain = ? WHERE project_id = ?";
	
	private final static String SQL_SELECT_STANDARD = 
		"SELECT id, project_id, active_step, contain FROM standard_diagram WHERE id = '-1'";
	
	@Autowired
	DocumentStepDaoImpl documentDao;
	
	@Autowired
	ResourceStepDaoImpl resourceDao;
	
	@Autowired
	PhaseItemDaoImpl phaseItemDao;
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final PhaseDiagram phaseDiagram) {
		return new Object[] { 
			phaseDiagram.getIdProject(), 
			phaseDiagram.getActiveStep(), 
			phaseDiagram.getContain()
		};
	}
		
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final PhaseDiagram phaseDiagram) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES);
		
		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(phaseDiagram));
		
		return psc;
	}
		
	// Prepare data for update 
	protected Object[] getValuesForUpdate(final PhaseDiagram phaseDiagram) {
		return new Object[] {		
				phaseDiagram.getActiveStep(), 
				phaseDiagram.getContain(),
				phaseDiagram.getIdProject()
		};
	}
	
	// Get all phase diagram from database		
	@Override
	public List<PhaseDiagram> getPhaseDiagram() {
		List<PhaseDiagram> result = jdbcTemplate.query(SQL_SELECT_ALL, phaseDiagramMapper);
		
		return result;
	}
	
	// Get phase diagram by project id
	@Override
	public List<PhaseDiagram> getPhaseDiagramById(int projectId) {
		Object[] primaryKey = new Object[] { projectId };
		List<PhaseDiagram> result = jdbcTemplate.query(SQL_SELECT_ALL_BY_ID, primaryKey, phaseDiagramMapper);
		
		return result;
	}
	
	// Get phase diagram by project id
	@Override
	public List<PhaseDiagram> getStandardDiagram(){
		List<PhaseDiagram> result = jdbcTemplate.query(SQL_SELECT_STANDARD, phaseDiagramMapper);
		
		return result;
	}

	// Update phase	directly into database
	@Override
	public int updatePhaseDiagram(PhaseDiagram phaseDiagram) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(phaseDiagram));
		
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		
		return phaseDiagram.getId();
	}
	
	// Clone new diagram for new project
	@Override
	public int clonePhaseDiagram(PhaseDiagram phaseDiagram) {
		KeyHolder keyHolder = new GeneratedKeyHolder();	
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(phaseDiagram), keyHolder);
	
		if (result != 1) {
			throw new RuntimeException("Unexpected return value after INSERT : " + result + " (1 expected) ");
		}
	
		Number key = keyHolder.getKey();
		if (key != null) {
			return key.intValue();
		} else {
			throw new RuntimeException("Cannot retrive generated key after INSERT : KeyHolder returns null");
		}
	}
	
	// Create new PhaseDiagram and new PhaseItem for new project
	@Override
	public Map<String, Object> createPhaseDiagram(PhaseDiagram phaseDiagram) {
		String strContain = prepareContainForInsert(phaseDiagram);
		
		// Prepare for sending email to resource
		Map<String, Object> emailMap = prepareForSendingEmail(listOfPhaseStep);
		
		// Create Phase Item
		PhaseItem phaseItem = new PhaseItem();
		int idProject = phaseDiagram.getIdProject();
		String activeStep = phaseDiagram.getActiveStep();
		
		phaseItem.setIdProject(idProject);
		phaseItem.setActiveStep(activeStep);
		phaseItem.setContain(strContain);
		phaseItemDao.create(phaseItem);

		// Update Phase Diagram
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(phaseDiagram));
		
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		
		return emailMap;
	}
	
	// Prepare contain field for inserting phase
	protected String prepareContainForInsert(final PhaseDiagram phaseDiagram) {
		List<PhaseStep> lstPhase = new ArrayList<PhaseStep>();
		
		String strResult = "";
		
		int projectId = phaseDiagram.getIdProject();
		String strContain = phaseDiagram.getContain();
		
		// Convert to XML
		Document document = convertStringToDocument(strContain);
		
		// Process Node
		NodeList processList = document.getElementsByTagName("process");
		Node processNode = processList.item(0);
		Element proEle = (Element) processNode;
		
		// Task Node
		NodeList taskList = proEle.getElementsByTagName("task");
		int taskLength = taskList.getLength();
		
		for (int i = 0; i < taskLength; i++) {
			PhaseStep phaseStep = new PhaseStep();
			Node taskNode = taskList.item(i);
			Element taskElement = (Element) taskNode;
			String parentNodeName = taskElement.getParentNode().getNodeName();
			
			if (parentNodeName != "subProcess") { // Not exist in sub process
				String stepId = taskElement.getAttribute("id");
	            String stepName = taskElement.getAttribute("name");
	            int index = i + 1;
	            
	            NodeList outgoingList = taskElement.getElementsByTagName("outgoing");
	            NodeList imcomingList = taskElement.getElementsByTagName("incoming");
	            List<String> lstIncoming = getListInOut(imcomingList);
	            List<String> lstOutgoing = getListInOut(outgoingList);
	            List<DocumentStep> docList = documentDao.getAllDocumentForStep(projectId, stepId);
	            List<ResourceStep> resourceList = resourceDao.getAllResourceForStep(projectId, stepId);
	                
	            phaseStep.setIndex(index);
	            phaseStep.setIdStep(stepId);
	            phaseStep.setResource(resourceList);
	            phaseStep.setDocument(docList);
	            phaseStep.setName(stepName);
	            phaseStep.setImcoming(lstIncoming);
	            phaseStep.setOutgoing(lstOutgoing);
	            
	            lstPhase.add(phaseStep);
			}
		}
		
		// Get all of Sub process
		NodeList subprocessList = proEle.getElementsByTagName("subProcess");

		// Loop 5 main steps to find their sub step base on its out coming
		setSubProcessForMainStep(lstPhase, taskList, subprocessList, projectId);
		
		// List of PhaseStep for prepare email
		listOfPhaseStep = lstPhase;
			
		// Result of List Phase => is converted to String and saved into database
		strResult = convertListPhaseToJsonForSaving(lstPhase);
		
		System.out.println(strResult);
		
		return strResult;
	}
	
	public Map<String, Object> prepareForSendingEmail(List<PhaseStep> lstPhase) {
		Map<String, Object> emailMap = new HashMap<>();
		
		for (int i = 0; i < lstPhase.size(); i++) {
			
			if (lstPhase.get(i).getSubSteps() == null) { // Don't have sub process
				
				List<ResourceStep> lstResource = lstPhase.get(i).getResource();
				if (lstResource.size() > 0) {
					List<String> lstMainEmail = convertResourceToString(lstResource);
					String stepName = lstPhase.get(i).getName();
					String infor = "You was assigned to " + stepName;
					
					// Push list of email and Content of email 
					emailMap.put(infor, lstMainEmail);
				}
			} else { // Has sub process level 1
				List<PhaseStep> subLevel1 = lstPhase.get(i).getSubSteps();
				for (int j = 0; j < subLevel1.size(); j++) {
					if  (subLevel1.get(j).getSubSteps() == null) {
						
						List<ResourceStep> lstResourceLevel1 = subLevel1.get(j).getResource();
						if (lstResourceLevel1.size() > 0) {
							List<String> lstLevel1Email = convertResourceToString(lstResourceLevel1);
							String mainStepName = lstPhase.get(i).getName();
							String subStepName = subLevel1.get(j).getName();
							String inforSubLevel1 = "You was assigned to " + mainStepName + " in Phase - " + subStepName;
							
							// Push list of email and Content of email 
							emailMap.put(inforSubLevel1, lstLevel1Email);
						}
					}
					else { // Has sub process level 2
						List<PhaseStep> subLevel2 = subLevel1.get(j).getSubSteps();
						for (int k = 0; k < subLevel2.size(); k++) {
							List<ResourceStep> lstResourceLevel2 = subLevel2.get(k).getResource();
							
							if (lstResourceLevel2.size() > 0) {
								List<String> lstLevel2Email = convertResourceToString(lstResourceLevel2);
								String mainStepName = lstPhase.get(i).getName();
								String subStepLevel1 = subLevel1.get(j).getName();
								String subStepLevel2 = subLevel2.get(k).getName();
								String inforSubLevel2 = "You was assigned to " + mainStepName + " in Phase - " + subStepLevel1 + " in Phase" + subStepLevel2;
								
								// Push list of email and Content of email 
								emailMap.put(inforSubLevel2, lstLevel2Email);
							}
						}
					}
				}
			}
		}

		return emailMap;
	}
	
	// Convert List of resource to String for sending email
	public List<String> convertResourceToString(List<ResourceStep> lstResource) {
		List<String> lstResult = new ArrayList<>();
		for (int i = 0; i < lstResource.size(); i++) {
			lstResult.add(lstResource.get(i).getEmailOfResponsible());
		}
		
		return lstResult;
	}
	
	/**
	 * Get all of Sub process and setting for Main Process
	 * @param lstPhase is list of Main process
	 * @param taskList is list of Task
	 * @param subprocessList is list of Sub Process
	 * @param projectId is index of project
	 */
	private void setSubProcessForMainStep(List<PhaseStep> lstPhase, NodeList taskList, NodeList subprocessList, int projectId) { 
		int index = 0;
		for (PhaseStep mainPhase : lstPhase) {
			List<String> outgoings = mainPhase.getOutgoing();
			for (String outgoing : outgoings) {
				// Call findNextNode to get next step
				List<Node> outComeNodes = findNextNode(outgoing, taskList, subprocessList);
				
				//if outgoing of main step equals to incoming of task, it is a next step of this main step
				for (Node node : outComeNodes) {
					if( node.getNodeName().equals("task")) {
						//parse this node to phase step object and set it into next object
						PhaseStep ps = convertNodeToObject(node, projectId);
						mainPhase.setNextStep(ps);
						mainPhase.setPreviousStep(null);
						if (index != 0) {
							mainPhase.setPreviousStep(lstPhase.get(index - 1).getIdStep());
						}
						ps.setPreviousStep(mainPhase.getIdStep());
						index++;	
					} else {
						//If outgoing of main step equals to incoming of subprocess, it is the sub steps of this main step
						//This case happens for the sub process
						//1: get all node task this sub process
						Element subElement = (Element) node;
						NodeList nodes = subElement.getElementsByTagName("task");
						// Must get task directly, not indirect
						String subProcessId = subElement.getAttribute("id");
						List<Node> nodeResults = new ArrayList<>();
						// If Tasks which have parent id equal id of subprocess. add to sub list
						for (int i = 0; i < nodes.getLength(); i++) {
							Node nodeTemp = nodes.item(i);
							Element element = (Element) nodeTemp;
							
							Node parentNode = element.getParentNode();
							Element parentElement = (Element) parentNode;
							String parentId = parentElement.getAttribute("id");
							
							if (parentId.equals(subProcessId)) {
								nodeResults.add(nodeTemp);
							}
						}
						//2: convert these node to phase step and add to subProcess list
						List<PhaseStep> subProcess =  new ArrayList<>();
						
						for(int i = 0; i < nodeResults.size(); i++) {
							PhaseStep ps = convertNodeToObject(nodes.item(i), projectId);
							ps.setIndex(i + 1);
							// Set parent step for each of sub
							ps.setParentStep(mainPhase.getIdStep());
							subProcess.add(ps);
							
						}
						
						mainPhase.setSubSteps(subProcess);
						if(subProcess != null && subProcess.size() > 0){
							setSubProcessForMainStep(subProcess, taskList, subprocessList, projectId);
						}
					}
				}
				
			}
		}
	}
	
	/**
	 * Find the next Node of input node based on an outgoing
	 * @param outgoing is outgoing of input Node
	 * @param taskList list of tasks for finding next node, which is Task
	 * @param subprocessList list of sub process for finding next node, which is Sub Process
	 * @return List of Node
	 */
	public List<Node> findNextNode(String outgoing, NodeList taskList, NodeList subprocessList) {
		List<Node> resultNodes = new ArrayList<>();
		// Get all of Task, if its incoming equal outgoing, return it parent
		for (int i = 0; i < taskList.getLength(); i++) {
			Node node = taskList.item(i);
			Element element = (Element) node;
			Node imcoming = element.getElementsByTagName("incoming").item(0);
			
			if (imcoming != null) {
				Element eleIncoming = (Element) imcoming;
				String incomingValue = eleIncoming.getTextContent();
				
				if (incomingValue.equals(outgoing)) {
					resultNodes.add(node);	
				}
			} 
		}
		
		// Get all of Sub Process, if its incoming equal outgoing, return it parent
		for (int i = 0; i < subprocessList.getLength(); i++) {
			Node subNode = subprocessList.item(i);
			Element element = (Element) subNode;
			Node imcoming = element.getElementsByTagName("incoming").item(0);
			
			if (imcoming != null) {
				Element eleIncoming = (Element) imcoming;
				String incomingValue = eleIncoming.getTextContent();
				if (incomingValue.equals(outgoing)) {	
					resultNodes.add(subNode);	
				}
			} 
		}
		
		return resultNodes;
	}
	
	/**
	 * Convert Node to Phase Step object
	 * @param node is input Node for converting
	 * @param projectId is index of Project
	 * @return Phase Step object
	 */
	private PhaseStep convertNodeToObject(Node node, int projectId) {
		Element taskElement = (Element) node;
		
		PhaseStep phaseStep = new PhaseStep();
		
		String stepId = taskElement.getAttribute("id");
        String stepName = taskElement.getAttribute("name");
        
        NodeList outgoingList = taskElement.getElementsByTagName("outgoing");
        NodeList imcomingList = taskElement.getElementsByTagName("incoming");
        
        List<String> lstIncoming = getListInOut(imcomingList);
        List<String> lstOutgoing = getListInOut(outgoingList);
        List<DocumentStep> docList = documentDao.getAllDocumentForStep(projectId, stepId);
        List<ResourceStep> resourceList = resourceDao.getAllResourceForStep(projectId, stepId);

        phaseStep.setIdStep(stepId);
        phaseStep.setResource(resourceList);
        phaseStep.setDocument(docList);
        phaseStep.setName(stepName);
        phaseStep.setImcoming(lstIncoming);
        phaseStep.setOutgoing(lstOutgoing);
        
        return phaseStep;
	}
	
	/**
	 * Convert List of PhaseStep to String for saving
	 * @param lstPhase is list of enter PhaseStep
	 * @return String of list of PhaseStep, which was converted
	 */
	public String convertListPhaseToJsonForSaving(List<PhaseStep> lstPhase) {
		String result = "";
		Gson gson = new Gson();
		
		result = gson.toJson(lstPhase);
		
		return result;
	}
	
	public boolean checkResourceBeforeSubmit(List<PhaseStep> lstPhase) {
		boolean isSave = true;
		
		int length = lstPhase.size();
		for (int i = 0; i < length; i++) {
			PhaseStep ps = lstPhase.get(i);
			if (ps.getContain() == "") {
				// Has not sub step
				if (ps.getResource().size() == 0) {
					isSave = false;
				}
			}
		}
		
		return isSave;
	}
	
	public List<PhaseStep> addSubToMainProcess(List<PhaseStep> lstPhaseStep, NodeList subProcess, int projectId) {
		// List of result
		List<PhaseStep> lstResult = lstPhaseStep;
		String connectionToMainprocess = "";
		int subProcessLength = subProcess.getLength();
		
		for (int j = 0; j < subProcessLength; j++) {
			Node subProcessNode = subProcess.item(j);
			Element subProcessEle = (Element) subProcessNode;
			
			NodeList incomingList = subProcessEle.getElementsByTagName("incoming");
			Node imcomeNode = incomingList.item(0);
			
			String imcomingValue = imcomeNode.getTextContent();
			connectionToMainprocess = imcomingValue;
			
			NodeList taskListInSub =  subProcessEle.getElementsByTagName("task");
			int lengthOfTaskInSubProcess = taskListInSub.getLength();
			
			// List phase for sub
			List<PhaseStep> lstPhaseInSub = new ArrayList<PhaseStep>();
			for (int k = 0; k < lengthOfTaskInSubProcess; k++) {	
				int index = k + 1;
				Node taskNodeInSub = taskListInSub.item(k);
				Element taskEle = (Element) taskNodeInSub;
				
				String stepId = taskEle.getAttribute("id");
	            String stepName = taskEle.getAttribute("name");
	    
	            NodeList outgoingList = taskEle.getElementsByTagName("outgoing");
	            List<String> lstOutgoing = getListInOut(outgoingList);
	      
	            NodeList imcomingList = taskEle.getElementsByTagName("incoming");
	            List<String> lstIncoming = getListInOut(imcomingList);
	            
	            List<DocumentStep> docList = documentDao.getAllDocumentForStep(projectId, stepId);
	            List<ResourceStep> resourceList = resourceDao.getAllResourceForStep(projectId, stepId);
	            
				PhaseStep phaseInSub = new PhaseStep();
    
	            phaseInSub.setIndex(index);
	            phaseInSub.setIdStep(stepId);
	            phaseInSub.setResource(resourceList);
	            phaseInSub.setDocument(docList);
	            phaseInSub.setName(stepName);
	            phaseInSub.setImcoming(lstIncoming);
	            phaseInSub.setOutgoing(lstOutgoing);
	            
				lstPhaseInSub.add(phaseInSub);
			}
			
			String strResultOfSubprocess = createContainToInsert(lstPhaseInSub);
			
	  
			String strIncoming = connectionToMainprocess;
			
			lstResult = attachContainToMainprocess(lstResult, strIncoming, strResultOfSubprocess);
		}
		
		
		return lstResult;
		
	}
	public List<PhaseStep> attachContainToMainprocess(List<PhaseStep> lstPhaseStep, String strIncoming, String lstSubProcess) {
		List<PhaseStep> lstResult = lstPhaseStep;
		
		for (int i = 0; i < lstResult.size(); i++) {
			List<String> lstOutcoming = lstResult.get(i).getOutgoing();
			for (int j = 0; j < lstOutcoming.size(); j++) {
				String strOutgoing = lstOutcoming.get(j);

				if (strOutgoing.equals(strIncoming)) {
					lstResult.get(i).setContain(lstSubProcess);
				}
			}
		}
		
		return lstResult;
		
	}
	
	
	// Convert Incoming and Outgoing to List
	public List<String> getListInOut(NodeList listOutgoing) {
		List<String> result = new ArrayList<String>();
		
		int length = listOutgoing.getLength();
		for (int i = 0; i < length; i++) {
			Node outgoingNode = listOutgoing.item(i);
			String outGoing = outgoingNode.getTextContent();
			
			result.add(outGoing);
		}
		return result;
	}
	
	
	// Convert List phase to contain
	public String createContainToInsert(List<PhaseStep> list) {
		String strContain = "";
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		
		int length = list.size();
		for (int i = 0; i < length; i++) {
			int index = list.get(i).getIndex();
			String name = list.get(i).getName();
			String contain = list.get(i).getContain();
			List<DocumentStep> document = list.get(i).getDocument();
			List<ResourceStep> resource = list.get(i).getResource();
//			List<String> lstIncoming = list.get(i).getIncoming();
//			List<String> lstOutgoing = list.get(i).getOutgoing();
			
  			String strTemp = createString(index, name, contain, document, resource);
  			sb.append(strTemp);
  			
			if (i  < length - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		strContain = sb.toString();
		
		return strContain;
	}
	
	public String createString(int index, String name, String contain, List<DocumentStep> document, List<ResourceStep> resource) {
		String result = "";

		StringBuffer sbPhase = new StringBuffer();
		sbPhase.append("{");
		
		sbPhase.append("\"index\"");
		sbPhase.append(":");
		sbPhase.append(index);
		sbPhase.append(",");
		
		sbPhase.append("\"name\"");
		sbPhase.append(":\"");
		sbPhase.append(name);
		sbPhase.append("\",");
		
		sbPhase.append("\"contain\"");
//		sbPhase.append(":\"");
		sbPhase.append(":");

		sbPhase.append(contain);
//		sbPhase.append("\",");
		sbPhase.append(",");
		
		sbPhase.append("\"document\"");
		sbPhase.append(":");
		String strDocument = createDocumentContain(document);
		sbPhase.append(strDocument);
		sbPhase.append(",");
		
		sbPhase.append("\"resource\"");
		sbPhase.append(":");
		String strResource = createResourceContain(resource);
		
		sbPhase.append(strResource);
		sbPhase.append("}");
		
		result = sbPhase.toString();
		
		return result;
	}
	

	
	public String createDocumentContain(List<DocumentStep> listDocument) {
		int size = listDocument.size();
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		
		for (int i = 0; i < size; i++) {
			sb.append("{");
			
			sb.append("\"docName\"");
			sb.append(":\"");
			sb.append(listDocument.get(i).getDocumentName());
			sb.append("\",");
			
			sb.append("\"docDescription\"");
			sb.append(":\"");
			sb.append(listDocument.get(i).getDocumentDescription());
			sb.append("\"");
			
			sb.append("}");
			
			if (i < size - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	public String createResourceContain(List<ResourceStep> resource) {
		int size = resource.size();
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		
		for (int i = 0; i < size; i++) {
			sb.append("{");
			
			sb.append("\"code\"");
			sb.append(":\"");
			sb.append(resource.get(i).getResourceCode());
			sb.append("\",");
			
			sb.append("\"email\"");
			sb.append(":\"");
			sb.append(resource.get(i).getEmailOfResponsible());
			sb.append("\"");
			sb.append("}");
			if (i < size - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	
	// Convert String to XML
	private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try  
        {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            return doc;
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;
    }
	
	
	// Prepare contain field for inserting phase
	protected String prepareContainForInsertBackup(final PhaseDiagram phaseDiagram) {
		List<PhaseStep> lstPhase = new ArrayList<PhaseStep>();
		
		String strResult = "";
		
		int projectId = phaseDiagram.getIdProject();
		String strContain = phaseDiagram.getContain();
		
		// Convert to XML
		Document document = convertStringToDocument(strContain);
		
		// Process Node
		NodeList processList = document.getElementsByTagName("process");
		int tempProcess = 0;
		Node processNode = processList.item(tempProcess);
		Element proEle = (Element) processNode;
		
		// Task Node
		NodeList taskList = proEle.getElementsByTagName("task");
		int taskLength = taskList.getLength();
			
			for (int i = 0; i < taskLength; i++) {
				PhaseStep phaseStep = new PhaseStep();
				Node taskNode = taskList.item(i);
	            if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) taskNode;
	                
	                String stepId = eElement.getAttribute("id");
	                String stepName = eElement.getAttribute("name");
	                int index = i + 1;
	                List<DocumentStep> docList = documentDao.getAllDocumentForStep(projectId, stepId);
	                List<ResourceStep> resourceList = resourceDao.getAllResourceForStep(projectId, stepId);
	                
	                phaseStep.setIndex(index);
	                phaseStep.setResource(resourceList);
	                phaseStep.setDocument(docList);
	                phaseStep.setName(stepName);
	            }
	            
	            lstPhase.add(phaseStep);
			}
			
			strResult = createContainToInsert(lstPhase);
			
			return strResult;
		}
		

}
