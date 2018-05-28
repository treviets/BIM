package com.redsun.utils;

public interface RedSunURLs {

	
	static String getRootURL(){
		return "/redsun-service";
		
	}
	static String getDocURL(){
		return "/redsun-document";
		
	}
	static String getBPMNURL(){
		return "/redsun-bpmn";
	}

	static String getSocialURL(){
		return "/redsun-social";
	}

	static String getHRMURL(){
		return "/redsun-hrm";
	}
	
	// Activity
	public final String ACTIVITIES_URL_GET_ALL = getRootURL() + "/activities/list";
	public final String ACTIVITIES_URL_GET_BY_PROJECT = getRootURL() + "/activities/getbyproject";
	// to use for option in task...
	public final String ACTIVITIES_URL_ALL = getRootURL() + "/activities/listall";
	public final String ACTIVITIES_URL_ADD = getRootURL() + "/activities/add";
	public final String ACTIVITIES_URL_DELETE = getRootURL() + "/activities/delete/";
	public final String ACTIVITIES_URL_GETBYID = getRootURL() + "/activities/getbyid/";
	public final String ACTIVITIES_URL_UPDATE = getRootURL() + "/activities/update/";
	public final String ACTIVITIES_URL_PRICE_SERVICE_INSERT = getRootURL() + "/activitypricesservice/insert";
	public final String ACTIVITIES_URL_PRICE_SERVICE_UPDATE = getRootURL() + "/activitypricesservice/update/";
	public final String ACTIVITIES_URL_PRICE_SERVICE_DELETE = getRootURL() + "/activitypricesservice/delete/";
	public final String ACTIVITIES_URL_PRICE_SERVICE_GET_BY_ID = getRootURL() + "/activitypricesservice/getbyid/";
	public final String ACTIVITIES_URL_PRICE_SERVICE_FILTER = getRootURL() + "/activitypricesservice/list/page/filter/";
	
	public final String BILL_LINE_URL_SERVICE_INSERT = getRootURL() + "/billlinesservice/insert";
	public final String BILL_LINE_URL_SERVICE_UPDATE = getRootURL() + "/billlinesservice/update/";
	public final String BILL_LINE_URL_SERVICE_DELETE = getRootURL() + "/billlinesservice/delete/";
	public final String BILL_LINE_URL_SERVICE_GET_BY_ID = getRootURL() + "/billlinesservice/getbyid/";
	public final String BILL_LINE_URL_SERVICE_FILTER = getRootURL() + "/billlinesservice/list/page/filter/";
	
	public final String BILL_URL_SERVICE_INSERT = getRootURL() + "/bills-service/insert";
	public final String BILL_URL_SERVICE_UPDATE = getRootURL() + "/bills-service/update/";
	public final String BILL_URL_SERVICE_DELETE = getRootURL() + "/bills-service/delete/";
	public final String BILL_URL_SERVICE_GET_BY_ID = getRootURL() + "/bills-service/getbyid/";
	public final String BILL_URL_SERVICE_FILTER = getRootURL() + "/bills-service/list/page/filter/";
	public final String BILL_URL_SERVICE_FILTER_EXTENDS = getRootURL() + "/bills-service/list-extend/page/filter/";
	
	public final String CLIENT_URL_SERVICE_INSERT = getRootURL() + "/clientsservice/insert";
	public final String CLIENT_URL_SERVICE_UPDATE = getRootURL() + "/clientsservice/update/";
	public final String CLIENT_URL_SERVICE_DELETE = getRootURL() + "/clientsservice/delete/";
	public final String CLIENT_URL_SERVICE_GET_BY_ID = getRootURL() + "/clientsservice/getbyid/";
	public final String CLIENT_URL_SERVICE_LIST_ALL = getRootURL() + "/clientsservice/listall";
	public final String CLIENT_URL_SERVICE_FILTER = getRootURL() + "/clientsservice/list/page/filter/";
	
	public final String COMMANDS_URL_SERVICE_INSERT = getRootURL() + "/commands-service/insert";
	public final String COMMANDS_URL_SERVICE_UPDATE = getRootURL() + "/commands-service/update/";
	public final String COMMANDS_URL_SERVICE_DELETE = getRootURL() + "/commands-service/delete/";
	public final String COMMANDS_URL_SERVICE_GET_BY_ID = getRootURL() + "/commands-service/getbyid/";
	public final String COMMANDS_URL_SERVICE_FILTER = getRootURL() + "/commands-service/list/page/filter/";
	public final String COMMANDS_URL_SERVICE_FILTER_EXTENDS = getRootURL() + "/commands-service/list-extend/page/filter/";
	
	public final String CONFIG_URL_SERVICE_INSERT = getRootURL() + "/configurations-service/add";
	public final String COMMANDS_URL_SERVICE_GET_BY_PROJECT_ID = getRootURL() + "/configurations-service/getbyproject/";
	
	public final String CRON_URL_SERVICE_INSERT = getRootURL() + "/cronexecutionsservice/insert";
	public final String CRON_URL_SERVICE_UPDATE = getRootURL() + "/cronexecutionsservice/update/";
	public final String CRON_URL_SERVICE_DELETE = getRootURL() + "/cronexecutionsservice/delete/";
	public final String CRON_URL_SERVICE_GET_BY_ID = getRootURL() + "/cronexecutionsservice/getbyid/";
	public final String CRON_URL_SERVICE_FILTER = getRootURL() + "/cronexecutionsservice/list/page/filter/";
	
	public final String DELIVERY_URL_SERVICE_INSERT = getRootURL() + "/deliverymodesservice/insert";
	public final String DELIVERY_URL_SERVICE_UPDATE = getRootURL() + "/deliverymodesservice/update/";
	public final String DELIVERY_URL_SERVICE_DELETE = getRootURL() + "/deliverymodesservice/delete/";
	public final String DELIVERY_URL_SERVICE_GET_BY_ID = getRootURL() + "/deliverymodesservice/getbyid/";
	public final String DELIVERY_URL_SERVICE_FILTER = getRootURL() + "/deliverymodesservice/list/page/filter/";
	
	
	public final String HEALTH_URL_SERVICE_INSERT = getRootURL() + "/healthsservice/insert";
	public final String HEALTH_URL_SERVICE_UPDATE = getRootURL() + "/healthsservice/update/";
	public final String HEALTH_URL_SERVICE_DELETE = getRootURL() + "/healthsservice/delete/";
	public final String HEALTH_URL_SERVICE_GET_BY_ID = getRootURL() + "/healthsservice/getbyid/";
	public final String HEALTH_URL_SERVICE_FILTER = getRootURL() + "/healthsservice/list/page/filter/";
	
	// project_reources
	public final String PROJECT_ROSOURCES_URL_ALL = getRootURL() + "/projectresources/listall";
	// Comment
	public final String COMMENT_URL_GET_BY_TASKID = getRootURL() + "/commentservice/getbytaskid/";
	// Document
	public final String DOCUMENT_LIST = getRootURL() + "/documentsservice/list/";
	public final String DOCUMENT_BY_TASKID = getRootURL() + "/documentsservice/getbytaskid/";
	// DocumentVersions
	public final String DOCUMENT_VERSION_URL_ADD = getRootURL() + "/documentversionsservice/add";
	public final String DOCUMENT_VERSION_URL_DOWNLOAD = getRootURL() + "/documentversionsservice/download/";
	public final String DOCUMENT_VERSION_URL_DELETE = getRootURL() + "/documentversionsservice/delete/";

	// Task
	public final String TASK_URL_GET_ALL = getRootURL() + "/tasks/list";
	public final String TASK_URL_GET_ALL_FOR_ONE_PROJECT = getRootURL() + "/tasks/listforoneproject";
	public final String TASK_URL_GETBYID = getRootURL() + "/tasks/getbyid/";
	public final String TASK_URL_ADD = getRootURL() + "/tasks/add";
	public final String TASK_URL_UPDATE = getRootURL() + "/tasks/update/";
	public final String TASK_URL_DELETE = getRootURL() + "/tasks/delete/";
	public final String TASK_URL_IMPORT = getRootURL() + "/tasks/import-task";
	
	public final String TEAMS_URL_ADD = getRootURL() + "/teamsservice/insert";
	public final String TEAMS_URL_UPDATE = getRootURL() + "/teamsservice/update/";
	public final String TEAMS_URL_DELETE = getRootURL() + "/teamsservice/delete/";
	public final String TEAMS_URL_GETBYID = getRootURL() + "/teamsservice/getbyid/";
	public final String TEAMS_URL_FILTER = getRootURL() + "/teamsservice/list/page/filter/";
	
	public final String TERMS_URL_ADD = getRootURL() + "/termsservice/insert";
	public final String TERMS_URL_UPDATE = getRootURL() + "/termsservice/update/";
	public final String TERMS_URL_DELETE = getRootURL() + "/termsservice/delete/";
	public final String TERMS_URL_GETBYID = getRootURL() + "/termsservice/getbyid/";
	public final String TERMS_URL_FILTER = getRootURL() + "/termsservice/list/page/filter/";
	
	public final String TRENS_URL_ADD = getRootURL() + "/trendsservice/insert";
	public final String TRENS_URL_UPDATE = getRootURL() + "/trendsservice/update/";
	public final String TRENS_URL_DELETE = getRootURL() + "/trendsservice/delete/";
	public final String TRENS_URL_GETBYID = getRootURL() + "/trendsservice/getbyid/";
	public final String TRENS_URL_FILTER = getRootURL() + "/trendsservice/list/page/filter/";
	
	public final String VERSION_URL_ADD = getRootURL() + "/versionsservice/insert";
	public final String VERSION_URL_UPDATE = getRootURL() + "/versionsservice/update/";
	public final String VERSION_URL_DELETE = getRootURL() + "/versionsservice/delete/";
	public final String VERSION_URL_GETBYID = getRootURL() + "/versionsservice/getbyid/";
	public final String VERSION_URL_FILTER = getRootURL() + "/versionsservice/list/page/filter/";
	
	public final String WORKFLOWS_URL_ADD = getRootURL() + "/workflowsservice/insert";
	public final String WORKFLOWS_URL_UPDATE = getRootURL() + "/workflowsservice/update/";
	public final String WORKFLOWS_URL_DELETE = getRootURL() + "/workflowsservice/delete/";
	public final String WORKFLOWS_URL_GETBYID = getRootURL() + "/workflowsservice/getbyid/";
	public final String WORKFLOWS_URL_FILTER = getRootURL() + "/workflowsservice/list/page/filter/";
	
	// Task Equipment
	public final String TASK_EQUIPMENT_ADD = getRootURL() + "/taskequipmentservice/add";
	public final String TASK_EQUIPMENT_UPDATE = getRootURL() + "/taskequipmentservice/update/";
	public final String TASK_EQUIPMENT_DELETE = getRootURL() + "/taskequipmentservice/delete/";
	public final String TASK_EQUIPMENT_GET_BY_TASK = getRootURL() + "/taskequipmentservice/getbytask/";

	public final String TASK_EQUIPMENT_TRACKING_ADD = getRootURL() + "/taskequipmenttracking/add";
	
	public final String EQUIPMENT_URL_SERVICE_LIST_ALL = getRootURL() + "/equipmentservice/listall/";
	public final String EQUIPMENT_URL_SERVICE_LIST_PAGE = getRootURL() + "/equipmentservice/list/page/";
	public final String EQUIPMENT_URL_SERVICE_FILTER = getRootURL() + "/equipmentservice/filterequipment/";
	
	public final String EQUIPMENT_URL_SERVICE_INSERT = getRootURL() + "/equipmentservice/add";
	public final String EQUIPMENT_URL_SERVICE_UPDATE = getRootURL() + "/equipmentservice/update/";
	public final String EQUIPMENT_URL_SERVICE_DELETE = getRootURL() + "/equipmentservice/delete/";
	public final String EQUIPMENT_URL_SERVICE_COUNT = getRootURL() + "/equipmentservice/count/";
	public final String EQUIPMENT_URL_SERVICE_GET_BY_ID = getRootURL() + "/equipmentservice/getbyid/";
	public final String EQUIPMENT_URL_SERVICE_IMPORT = getRootURL() + "/equipmentservice/import-equipment";
	public final String EQUIPMENT_URL_SERVICE_CHECK_EXIST = getRootURL() + "/equipmentservice/checkexist/";
	
	
	public final String MATERIAL_URL_SERVICE_INSERT = getRootURL() + "/materialservice/add";
	public final String MATERIAL_URL_SERVICE_UPDATE = getRootURL() + "/materialservice/update/";
	public final String MATERIAL_URL_SERVICE_DELETE = getRootURL() + "/materialservice/delete/";
	public final String MATERIAL_URL_SERVICE_COUNT = getRootURL() + "/materialservice/count/";
	public final String MATERIAL_URL_SERVICE_GET_BY_ID = getRootURL() + "/materialservice/getbyid/";
	public final String MATERIAL_URL_SERVICE_IMPORT = getRootURL()  + "/materialservice/import-material";
	public final String MATERIAL_URL_SERVICE_CHECK_EXIST = getRootURL() + "/materialservice/checkexist/";
	public final String MATERIAL_URL_SERVICE_LIST_PAGE = getRootURL() + "/materialservice/list/page/";
	public final String MATERIAL_URL_SERVICE_LIST_ALL = getRootURL() + "/materialservice/listall/";
	public final String MATERIAL_URL_SERVICE_FILTER = getRootURL() + "/materialservice/filtermaterial/";
	
	public final String MILESTONE_URL_SERVICE_INSERT = getRootURL() + "/milestonesservice/insert";
	public final String MILESTONE_URL_SERVICE_UPDATE = getRootURL() + "/milestonesservice/update/";
	public final String MILESTONE_URL_SERVICE_DELETE = getRootURL() + "/milestonesservice/delete/";
	public final String MILESTONE_URL_SERVICE_GET_BY_ID = getRootURL() + "/milestonesservice/getbyid/";
	public final String MILESTONE_URL_SERVICE_FILTER = getRootURL() + "/milestonesservice/list/page/filter/";
	public final String MILESTONE_URL_SERVICE_LIST = getRootURL() + "/milestonesservice/list/";
	
	public final String OVERALL_PROGRESSES_URL_SERVICE_INSERT = getRootURL() + "/overallprogressesservice/insert";
	public final String OVERALL_PROGRESSES_URL_SERVICE_UPDATE = getRootURL() + "/overallprogressesservice/update/";
	public final String OVERALL_PROGRESSES_URL_SERVICE_DELETE = getRootURL() + "/overallprogressesservice/delete/";
	public final String OVERALL_PROGRESSES_URL_SERVICE_GET_BY_ID = getRootURL() + "/overallprogressesservice/getbyid/";
	public final String OVERALL_PROGRESSES_URL_SERVICE_FILTER = getRootURL() + "/overallprogressesservice/list/page/filter/";
	
	public final String PAYMENT_DEPLAY_URL_SERVICE_INSERT = getRootURL() + "/paymentdelaysservice/insert";
	public final String PAYMENT_DEPLAY_URL_SERVICE_UPDATE = getRootURL() + "/paymentdelaysservice/update/";
	public final String PAYMENT_DEPLAY_URL_SERVICE_DELETE = getRootURL() + "/paymentdelaysservice/delete/";
	public final String PAYMENT_DEPLAY_URL_SERVICE_GET_BY_ID = getRootURL() + "/paymentdelaysservice/getbyid/";
	public final String PAYMENT_DEPLAY_URL_SERVICE_FILTER = getRootURL() + "/paymentdelaysservice/list/page/filter/";
	public final String PAYMENT_DEPLAY_URL_SERVICE_LIST_ALL = getRootURL() + "/paymentdelaysservice/getall/";
	
	public final String PAYMENT_MODE_URL_SERVICE_INSERT = getRootURL() + "/paymentmodesservice/insert";
	public final String PAYMENT_MODE_URL_SERVICE_UPDATE = getRootURL() + "/paymentmodesservice/update/";
	public final String PAYMENT_MODE_URL_SERVICE_DELETE = getRootURL() + "/paymentmodesservice/delete/";
	public final String PAYMENT_MODE_URL_SERVICE_GET_BY_ID = getRootURL() + "/paymentmodesservice/getbyid/";
	public final String PAYMENT_MODE_URL_SERVICE_FILTER = getRootURL() + "/paymentmodesservice/list/page/filter/";
	
	public final String PAYMENT_URL_SERVICE_INSERT = getRootURL() + "/payments-service/insert";
	public final String PAYMENT_URL_SERVICE_UPDATE = getRootURL() + "/payments-service/update/";
	public final String PAYMENT_URL_SERVICE_DELETE = getRootURL() + "/payments-service/delete/";
	public final String PAYMENT_URL_SERVICE_GET_BY_ID = getRootURL() + "/payments-service/getbyid/";
	public final String PAYMENT_URL_SERVICE_FILTER = getRootURL() + "/payments-service/list/page/filter/";
	public final String PAYMENT_URL_SERVICE_FILTER_EXTENDS = getRootURL() + "/payments-service/list-extend/page/filter/";
	
	public final String PLANNING_MODE_URL_SERVICE_INSERT = getRootURL() + "/planningmodesservice/insert";
	public final String PLANNING_MODE_URL_SERVICE_UPDATE = getRootURL() + "/planningmodesservice/update/";
	public final String PLANNING_MODE_URL_SERVICE_DELETE = getRootURL() + "/planningmodesservice/delete/";
	public final String PLANNING_MODE_URL_SERVICE_GET_BY_ID = getRootURL() + "/planningmodesservice/getbyid/";
	public final String PLANNING_MODE_URL_SERVICE_FILTER = getRootURL() + "/planningmodesservice/list/page/filter/";
	
	public final String PRODUCT_URL_SERVICE_INSERT = getRootURL() + "/productsservice/insert";
	public final String PRODUCT_URL_SERVICE_UPDATE = getRootURL() + "/productsservice/update/";
	public final String PRODUCT_URL_SERVICE_DELETE = getRootURL() + "/productsservice/delete/";
	public final String PRODUCT_URL_SERVICE_GET_BY_ID = getRootURL() + "/productsservice/getbyid/";
	public final String PRODUCT_URL_SERVICE_FILTER = getRootURL() + "/productsservice/list/page/filter/";
	
	public final String PROFILE_URL_SERVICE_INSERT = getRootURL() + "/profilesservice/insert";
	public final String PROFILE_URL_SERVICE_UPDATE = getRootURL() + "/profilesservice/update/";
	public final String PROFILE_URL_SERVICE_DELETE = getRootURL() + "/profilesservice/delete/";
	public final String PROFILE_URL_SERVICE_GET_BY_ID = getRootURL() + "/profilesservice/getbyid/";
	public final String PROFILE_URL_SERVICE_FILTER = getRootURL() + "/profilesservice/list/page/filter/";
	
	// Task material
	public final String TASK_MATERIAL_ADD = getRootURL() + "/taskmaterialservice/add";
	public final String TASK_MATERIAL_UPDATE = getRootURL() + "/taskmaterialservice/update/";
	public final String TASK_MATERIAL_GET_BY_TASK = getRootURL() + "/taskmaterialservice/getbytask/";
	public final String TASK_MATERIAL_UPDATE_PLANNING = getRootURL() + "/taskmaterialservice/updateplanning/";
	public final String TASK_MATERIAL_DELETE = getRootURL() + "/taskmaterialservice/delete/";
	
	public final String TASK_MATERIAL_TRACKING_ADD = getRootURL() + "/taskmaterialtracking/add";
	
	// Calendar
	public final String CALENDAR_URL_GET_ALL = getRootURL() + "/calendar/list";
	public final String CALENDAR_URL_ADD = getRootURL() + "/calendar/add";

	// Projects
	public final String PROJECTS_URL_GETBYID = getRootURL() + "/projects/getbyid/";
	public final String PROJECTS_URL_GETBYNAME = getRootURL() + "/projects/getbyname/";
	public final String PROJECTS_URL_GET_BY_RESOURCE = getRootURL()+ "/projects/list/by-resource/";
	public final String PROJECTS_URL_GETBY_PROJECTID = getRootURL() + "/projects/getbyprojectid/";
	public final String PROJECTS_URL_GET_CATEGORIES = getRootURL() + "/projects/categories";
	public final String PROJECTS_URL_GET_GANTTCHART_DATA = getRootURL() + "/projects/ganttchart/data";
	public final String PROJECTS_URL_GET_ALL = getRootURL() + "/projects/list";
	public final String DESIGN_PROJECTS_URL_GET_ALL = getRootURL() + "/projects/design/list";
	public final String PROJECTS_URL_ADD = getRootURL() + "/projects/add";
	public final String DESIGN_PROJECTS_URL_ADD = getRootURL() + "/projects/design/add";
	public final String PROJECTS_URL_UPDATE = getRootURL() + "/projects/update/";
	public final String PROJECTS_URL_DELETE = getRootURL() + "/projects/delete/";
	public final String PROJECTS_URL_GET_PRO_LABOR_BASELINE = getRootURL() + "/projectbaseline/list/labor";
	public final String PROJECTS_URL_INSERTBATCH_PRO_LABOR_BASELINE = getRootURL() + "/projectbaseline/insertbatch/labor";
	public final String PROJECTS_URL_INSERTBATCH_PRO_MATERIAL_BASELINE = getRootURL() + "/projectbaseline/insertbatch/material";
	public final String PROJECTS_URL_INSERTBATCH_PRO_EQUIPMENT_BASELINE = getRootURL() + "/projectbaseline/insertbatch/equipment";
	public final String PROJECTS_URL_INSERTBATCH_PRO_TASK_BASELINE = getRootURL() + "/projectbaseline/insertbatch/task";
	public final String PROJECTS_URL_INSERTBATCH_PRO_TASK_RESOURCE_BASELINE = getRootURL() + "/projectbaseline/insertbatch/taskresource";
	public final String PROJECTS_URL_INSERTBATCH_PRO_TASK_MATERIAL_BASELINE = getRootURL() + "/projectbaseline/insertbatch/taskmaterial";
	public final String PROJECTS_URL_INSERTBATCH_PRO_TASK_EQUIPMENT_BASELINE = getRootURL() + "/projectbaseline/insertbatch/taskequipment";
	public final String PROJECTS_URL_GET_BASELINE = getRootURL() + "/projectbaseline/list/baseline";
	//social URL for project
	public final String SOCIAL_CHATTING_CREATE = getSocialURL() + "/chatting/create";
	public final String SOCIAL_CHATTING_LIST_BY_GROUP = getSocialURL() + "/chatting/list-for-group";
	public final String SOCIAL_FILE_SHARING_CREATE = getSocialURL() + "/file-sharing/create";
	public final String SOCIAL_FILE_SHARING_GET_BY_PROJECT = getSocialURL() + "/file-sharing/list-for-project";
	public final String SOCIAL_FILE_SHARING_DELETE = getSocialURL() + "/file-sharing/delete";
	public final String SOCIAL_GROUP_CREATE = getSocialURL() + "/chatting/group/create";
	public final String SOCIAL_GROUP_LIST = getSocialURL() + "/chatting/group/list";
	public final String SOCIAL_GROUP_LIST_BY_USERNAME = getSocialURL() + "/chatting/group/list/by-user";
	public final String SOCIAL_GROUP_JOIN = getSocialURL() + "/chatting/group/join";
	
	// ProjectEquipments
	public final String PROJECT_EQUIPMENTS_URL_GET_ALL = getRootURL() + "/projectequipmentservice/getbyproject/";
	public final String PROJECT_EQUIPMENTS_URL_ADD = getRootURL() + "/projectequipmentservice/add";
	public final String PROJECT_EQUIPMENTS_URL_SERVICE_FILTER = getRootURL() + "/projectequipmentservice/filterprojectequipment/";
	public final String PROJECT_EQUIPMENTS_URL_SERVICE_UPDATE = getRootURL() + "/projectequipmentservice/update/";
	public final String PROJECT_EQUIPMENTS_URL_SERVICE_DELETE = getRootURL() + "/projectequipmentservice/delete/";
	// ProjectMaterials
	public final String PROJECT_MATERIALS_URL_GET_ALL = getRootURL() + "/projectmaterialservice/getbyproject/";
	public final String PROJECT_MATERIALS_URL_ADD = getRootURL() + "/projectmaterialservice/add/";
	public final String PROJECT_MATERIALS_URL_SERVICE_FILTER = getRootURL() + "/projectmaterialservice/filterprojectmaterial/";
	public final String PROJECT_MATERIALS_URL_SERVICE_UPDATE = getRootURL() + "/projectmaterialservice/update/";
	public final String PROJECT_MATERIALS_URL_SERVICE_DELETE = getRootURL() + "/projectmaterialservice/delete/";
	
	public final String PROJECT_RESOURCES_URL_SERVICE_ADD = getRootURL() + "/projectresourceservice/add";
	public final String PROJECT_RESOURCES_URL_SERVICE_BY_PROJECT = getRootURL() + "/projectresourceservice/getbyproject/";
	public final String PROJECT_RESOURCES_URL_SERVICE_FILTER = getRootURL() + "/projectresourceservice/filterprojectresource/";
	public final String PROJECT_RESOURCES_URL_SERVICE_GET_ALL = getRootURL() + "/projectresourceservice/getallprojectresources/";
	public final String PROJECT_RESOURCES_URL_SERVICE_UPDATE = getRootURL() + "/projectresourceservice/update/";
	public final String PROJECT_RESOURCES_URL_SERVICE_DELETE = getRootURL() + "/projectresourceservice/delete/";
	
	public final String PROVIDER_URL_SERVICE_ADD = getRootURL() + "/providersservice/insert";
	public final String PROVIDER_URL_SERVICE_UPDATE = getRootURL() + "/providersservice/update/";
	public final String PROVIDER_URL_SERVICE_DELETE = getRootURL() + "/providersservice/delete/";
	public final String PROVIDER_URL_SERVICE_FILTER = getRootURL() + "/providersservice/list/page/filter/";
	public final String PROVIDER_URL_SERVICE_GET_BY_ID = getRootURL() + "/providersservice/getbyid/";
	
	public final String QUALITIES_URL_SERVICE_ADD = getRootURL() + "/qualitiesservice/insert";
	public final String QUALITIES_URL_SERVICE_UPDATE = getRootURL() + "/qualitiesservice/update/";
	public final String QUALITIES_URL_SERVICE_DELETE = getRootURL() + "/qualitiesservice/delete/";
	public final String QUALITIES_URL_SERVICE_FILTER = getRootURL() + "/qualitiesservice/list/page/filter/";
	public final String QUALITIES_URL_SERVICE_GET_BY_ID = getRootURL() + "/qualitiesservice/getbyid/";
	
	public final String QUOTATIONS_URL_SERVICE_ADD = getRootURL() + "/quotations-service/insert";
	public final String QUOTATIONS_URL_SERVICE_UPDATE = getRootURL() + "/quotations-service/update/";
	public final String QUOTATIONS_URL_SERVICE_DELETE = getRootURL() + "/quotations-service/delete/";
	public final String QUOTATIONS_URL_SERVICE_GET_BY_ID = getRootURL() + "/quotations-service/getbyid/";
	public final String QUOTATIONS_URL_SERVICE_LIST = getRootURL() + "/quotations-service/list/page/filter/";
	public final String QUOTATIONS_URL_SERVICE_FILTER = getRootURL() + "/quotations-service/list-extend/page/filter/";
	
	public final String RECIPIENTS_URL_SERVICE_ADD = getRootURL() + "/recipientsservice/insert";
	public final String RECIPIENTS_URL_SERVICE_UPDATE = getRootURL() + "/recipientsservice/update/";
	public final String RECIPIENTS_URL_SERVICE_DELETE = getRootURL() + "/recipientsservice/delete/";
	public final String RECIPIENTS_URL_SERVICE_GET_BY_ID = getRootURL() + "/recipientsservice/getbyid/";
	public final String RECIPIENTS_URL_SERVICE_FILTER = getRootURL() + "/recipientsservice/list/page/filter/";
	
	public final String REFEREN_CABLES_URL_SERVICE_ADD = getRootURL() + "/referencablesservice/insert";
	public final String REFEREN_CABLES_URL_SERVICE_UPDATE = getRootURL() + "/referencablesservice/update/";
	public final String REFEREN_CABLES_URL_SERVICE_DELETE = getRootURL() + "/referencablesservice/delete/";
	public final String REFEREN_CABLES_URL_SERVICE_GET_BY_ID = getRootURL() + "/referencablesservice/getbyid/";
	public final String REFEREN_CABLES_URL_SERVICE_FILTER = getRootURL() + "/referencablesservice/list/page/filter/";
	
	// Call4Tender
	public final String CALL_FOR_TENDER_LIST = getRootURL() + "/call-for-tenders-service/list/";
	public final String CALL_FOR_TENDER_GET = getRootURL() + "/call-for-tenders-service/get/";
	public final String CALL_FOR_TENDER_UPDATE = getRootURL() + "/call-for-tenders-service/update/";
	public final String CALL_FOR_TENDER_INSERT = getRootURL() + "/call-for-tenders-service/insert/";
	public final String CALL_FOR_TENDER_DELETE = getRootURL() + "/call-for-tenders-service/delete/";

	// Tenders
	public final String TENDERS_LIST = getRootURL() + "/tenders-service/list/";
	public final String TENDERS_GET = getRootURL() + "/tenders-service/get/";
	public final String TENDERS_INSERT = getRootURL() + "/tenders-service/insert/";
	public final String TENDERS_UPDATE = getRootURL() + "/tenders-service/update/";
	public final String TENDERS_DELETE = getRootURL() + "/tenders-service/delete/";

	// Attachment
	public final String ATTACHMENTS_GET_BY_REF_ID = getRootURL() + "/attachments-service/getByRefId/";
	public final String ATTACHMENTS_INSERT = getRootURL() + "/attachments-service/insert/";
	public final String ATTACHMENTS_UPLOAD = getRootURL() + "/attachments-service/upload/";
	public final String ATTACHMENTS_DELETE = getRootURL() + "/attachments-service/delete/";
	public final String ATTACHMENTS_DOWNLOAD = getRootURL() + "/attachments-service/download/";

	// Risk
	public final String RISK_URL_ADD = getRootURL() + "/risks/add/";
	public final String RISK_URL_SELECT = getRootURL() + "/risks/";
	public final String RISK_URL_ALL_ONE_PROJECT = getRootURL() + "/risks/listforoneproject/";
	public final String RISK_URL_UPDATE = getRootURL() + "/risks/update/";
	public final String RISK_URL_GETBYID = getRootURL() + "/risks/getbyid/";
	public final String RISK_URL_GETBYNAME = getRootURL() + "/risks/getbyname/";
	public final String RISK_URL_DELETE = getRootURL() + "/risks/delete/";

	// Types
	public final String TYPES_URL_GET_ALL = getRootURL() + "/typesservice/listall";
	public final String TYPES_URL_GET_ALL_USE_FOR_TASK = getRootURL() + "/typesservice/all";
	public final String TYPES_URL_ALL = getRootURL() + "/typesservice/list/";
	
	// Units
	public final String UNIT_URL_GET_ALL = getRootURL() + "/units/list/";
	
	// Likelihood
	public final String LIKELIHOOD_URL_GET_ALL = getRootURL() + "/likelihoods/list/";
	public final String LIKELIHOOD_URL_INSERT = getRootURL() + "/likelihoodsservice/insert";
	public final String LIKELIHOOD_URL_UPDATE = getRootURL() + "/likelihoodsservice/update/";
	public final String LIKELIHOOD_URL_DELETE = getRootURL() + "/likelihoodsservice/delete/";
	public final String LIKELIHOOD_URL_GET_BY_ID = getRootURL() + "/likelihoodsservice/getbyid/";
	public final String LIKELIHOOD_URL_FILTER = getRootURL() + "/likelihoodsservice/list/page/filter/";
	// Assignment
	public final String TASKRESOURCES_URL_GET_BY_TASK = getRootURL() + "/taskresources-service/getbytask/";
	public final String TASKRESOURCES_URL_GET_BY_PROJECT = getRootURL() + "/taskresources-service/gettaskresourcesbyproject/";

	public final String TASKRESOURCES_URL_ADD = getRootURL() + "/taskresources-service/add";
	public final String TASKRESOURCES_URL_UPDATE = getRootURL() + "/taskresources-service/update/";
	public final String TASKRESOURCES_URL_DELETE = getRootURL() + "/taskresources-service/delete/";
	
	public final String TASKRESOURCES_URL_TRACKING_ADD = getRootURL() + "/taskresourcetracking/add";
	
	// Severities
	public final String SEVERITIES_URL_GET_ALL = getRootURL() + "/severities/list";

	// Criticalities
	public final String CRITICALITIES_URL_GET_ALL = getRootURL() + "/criticalities/list/";

	// Priorities
	public final String PRIORITY_URL_ADD = getRootURL() + "/priorities/add/";
	public final String PRIORITY_URL_SELECT = getRootURL() + "/priorities/";
	public final String PRIORITY_URL_UPDATE = getRootURL() + "/priorities/update/";
	public final String PRIORITY_URL_GETBYID = getRootURL() + "/priorities/getbyid/";
	public final String PRIORITY_URL_DELETE = getRootURL() + "/priorities/delete/";
	public final String PRIORITIES_URL_GET_ALL = getRootURL() + "/priorities/list/";

	// Statuses
	public final String STATUSES_URL_GET_ALL = getRootURL() + "/statusesservice/list";
	public final String STATUSES_URL_GET_BY_SCOPE = getRootURL() + "/statusesservice/getbyscope";

	// Organization
	public final String ORGANIZATION_URL_GET_ALL = getRootURL() + "/organizations/list";

	// Resources
	public final String RESOURCES_URL_LIST_ONPAGE = getRootURL() + "/resourcesservice/list/page/";
	public final String RESOURCES_URL_LIST_EXTERIOR = getRootURL() + "/resourcesservice/exterior/page/";
	public final String RESOURCES_URL_SERVICE_ADD = getRootURL() + "/resourcesservice/add";
	public final String RESOURCES_URL_SERVICE_UPDATE = getRootURL() + "/resourcesservice/update/";
	public final String RESOURCES_URL_SERVICE_DELETE = getRootURL() + "/resourcesservice/delete/";
	public final String RESOURCES_URL_SERVICE_GET_BY_ID = getRootURL() + "/resourcesservice/getbyid/";
	public final String RESOURCES_URL_SERVICE_LIST = getRootURL() + "/resourcesservice/list/";
	public final String RESOURCES_ALL_TYPE_URL_SERVICE_LIST = getRootURL() + "/resourcesservice/list-all-type/";
	public final String RESOURCES_URL_SERVICE_DELETE_LIST = getRootURL() + "/resourcesservice/deleted/list/";
	public final String RESOURCES_URL_SERVICE_RESTORE = getRootURL() + "/resourcesservice/restore/";
	public final String RESOURCES_URL_SERVICE_FILTER = getRootURL() + "/resourcesservice/filtermember/";
	public final String RESOURCES_URL_SERVICE_COUNT = getRootURL() + "/resourcesservice/count/";
	public final String RESOURCES_URL_SERVICE_IMPORT = getRootURL() + "/resourcesservice/import-resource";
	public final String RESOURCES_URL_SERVICE_FILTER_TITLE = getRootURL() + "/resourcesservice/filtertitle/";
	public final String RESOURCES_URL_SERVICE_CHECK_EXIST = getRootURL() + "/resourcesservice/checkexist/";
	
	public final String ROLE_URL_SERVICE_ADD = getRootURL() + "/roles-service/insert";
	public final String ROLE_URL_SERVICE_UPDATE = getRootURL() + "/roles-service/update/";
	public final String ROLE_URL_SERVICE_DELETE = getRootURL() + "/roles-service/delete/";
	public final String ROLE_URL_SERVICE_GET_BY_ID = getRootURL() + "/roles-service/getbyid/";
	public final String ROLE_URL_SERVICE_LIST = getRootURL() + "/roles-service/list/page/filter/";
	public final String ROLE_URL_SERVICE_FILTER = getRootURL() + "/roles-service/list-extend/page/filter/";
	public final String ROLE_URL_SERVICE_LIST_ALL = getRootURL() + "/roles-service/listall/";
	
	// reports
	public final String PM_RESOURCE_COST = getRootURL() + "/planningelement/list/";
	public final String PM_TASK_COST = getRootURL() + "/planningelement/list/taskcost/";
	public final String PM_COST_EXPENSES = getRootURL() + "/planningelement/list/costexpenses/";
	// Expense report
	public final String PM_EXPENSE_CALLANDTENDER = getRootURL() + "/reportexpense/listcallandtender/";
	public final String PM_EXPENSE_BILLANDPAYMENT = getRootURL() + "/reportexpense/listbillandpayment/";
	public final String PM_EXPENSE_QUOTATIONANDORDER = getRootURL() + "/reportexpense/listquotationandorder/";
	// Income report
	public final String PM_INCOME = getRootURL() + "/reportincome/list/";
	// materials
	public final String PM_MATERIAL_USAGE = getRootURL() + "/planningelement/list/materialusage/";
	public final String PM_MATERIAL_PROVIDED = getRootURL() + "/planningelement/list/materialprovied/";
	//labor
	public final String PM_LABOR = getRootURL() + "/planningelement/list/labor/";
	//equipment
	public final String PM_EQUIPMENT = getRootURL() + "/planningelement/list/equipment/";
	public final String PM_TIMESHEET = getRootURL() + "/planningelement/list/timesheet/";
	// document management
	public final String DIRECTORY_GET_ALL = getDocURL() + "/directory/list/";
	public final String DIRECTORY_GET_DEFAULT = getDocURL() + "/directory/listdefault";
	public final String DIRECTORY_URL_SERVICE_INSERT = getDocURL() + "/directory/insert";
	public final String DIRECTORY_URL_SERVICE_INSERT_DEF = getDocURL() + "/directory/insertdef";
	public final String DIRECTORY_URL_SERVICE_UPDATE = getDocURL() + "/directory/update/";
	public final String DIRECTORY_URL_SERVICE_UPDATE_DEF = getDocURL() + "/directory/updatedef/";
	public final String DIRECTORY_URL_SERVICE_UPDATE_TRASH = getDocURL() + "/directory/updatetrash/";
	public final String DIRECTORY_URL_SERVICE_UNDO_TRASH = getDocURL() + "/directory/undofromtrash/";
	public final String DIRECTORY_URL_SERVICE_DELETE = getDocURL() + "/directory/delete/";
	public final String DIRECTORY_URL_SERVICE_DELETE_DEF = getDocURL() + "/directory/deletedef/";
	public final String DIRECTORY_URL_SERVICE_GET_BY_ID = getDocURL() + "/directory/getbyid/";
	public final String DIRECTORY_URL_SERVICE_GET_DEF_BY_ID = getDocURL() + "/directory/getdefbyid/";
	public final String DIRECTORY_URL_SERVICE_LIST_FROM_TRASH = getDocURL() + "/directory/list/fromtrash/";
	
	public final String DIRECTORY_URL_SERVICE_CREATE_LIST = getDocURL() + "/directory/createlistdirectory/";
	public final String DIRECTORY_RESOURCE_URL_SERVICE_LIST_BY_RESOURCE_ID = getDocURL() + "/directoryresource/list/resourceassignedbyid/";
	
	public final String FILE_URL_SERVICE_UPLOAD = getDocURL() + "/files-service/upload";
	
	public final String FILE_URL_SERVICE_DOWNLOAD_FULLNAME = getDocURL() + "/files-service/download";
	public final String FILE_URL_SERVICE_DOWNLOAD_FARTHER = getDocURL() + "/files-service/download";
	
	public final String DOCUMENT_URL_SERVICE_INSERT = getDocURL() + "/document/insert";
	public final String DOCUMENT_URL_SERVICE_UPDATE = getDocURL() + "/document/update/";
	public final String DOCUMENT_URL_SERVICE_DELETE = getDocURL() + "/document/delete";
	public final String DOCUMENT_URL_SERVICE_GET_BY_ID = getDocURL() + "/document/getbyid/";
	
	public final String DOCUMENT_URL_SERVICE_ROOT_INSERT = getRootURL() + "/documentsservice/add";
	public final String DOCUMENT_URL_SERVICE_ROOT_UPDATE = getRootURL() + "/documentsservice/update/";
	public final String DOCUMENT_URL_SERVICE_ROOT_DELETE = getRootURL() + "/documentsservice/delete/";
	public final String DOCUMENT_URL_SERVICE_ROOT_GET_BY_ID = getRootURL() + "/documentsservice/getbydocumentid/";
	public final String DOCUMENT_URL_SERVICE_ROOT_FILTER = getRootURL() + "/documentsservice/list/page/filter/";
	
	public final String DOCUMENT_VERSION_URL_SERVICE_ROOT_GET_BY_DOC_ID = getRootURL() + "/documentversionsservice/getbydocumentid/";
	public final String DOCUMENT_VERSION_URL_SERVICE_ROOT_GET_BY_TASK_ID = getRootURL() + "/documentversionsservice/getbytaskid/";
	public final String DOCUMENT_VERSION_URL_SERVICE_ROOT_LIST = getRootURL() + "/documentversionsservice/list/";
	public final String DOCUMENT_VERSION_URL_SERVICE_ROOT_GET_BY_ID = getRootURL() + "/documentversionsservice/getbyid/";
	
	public final String DOCUMENT_GET_BY_FOLDER = getDocURL() + "/directory/doc/";
	public final String DOWNLOAD_DOCUMENT = getDocURL() + "/directory/download/";
	public final String DIRECTORY_RESOURCE = getDocURL() + "/directoryresource/insert";
	public final String DIRECTORY_MOVE_FOLDER_TO_TRASH = getDocURL() + "/directory/updatetrash/";

	public final String GET_ALL_PHASE = getBPMNURL() + "/phaseitem/list";
	public final String GET_PHASE_BY_ID = getBPMNURL() + "/phaseitem/list/";
	public final String UPDATE_PHASE = getBPMNURL() + "/phaseitem/update/";
	public final String GET_ALL_DOCUMENT = getBPMNURL() + "/phasedocument/list/project/";
	public final String GET_DOCUMENT_FOR_STEP = getBPMNURL() + "/document/list/";
	public final String ADD_PHASE_DOCUMENT = getBPMNURL() + "/phasedocument/add";
	public final String DELETE_PHASE_DOCUMENT = getBPMNURL() + "/phasedocument/delete";

	public final String GET_PHASE_DIAGRAM = getBPMNURL() + "/phasediagram/list/";
	public final String CREATE_PHASE_DIAGRAM = getBPMNURL() + "/phasediagram/add";
	public final String UPDATE_PHASE_DIAGRAM = getBPMNURL() + "/phasediagram/update/";
	public final String GET_PROJECT_RESOURCE = getRootURL() + "/resourcesservice/list/";
	
	public final String GET_SELECTED_RESOURCE = getBPMNURL() + "/resourcestep/list/";
	public final String GET_DETAIL_RESOURCE = getBPMNURL() + "/resourcestep/list/";
	
	public final String CREATE_SELECTED_RESOURCE = getBPMNURL() + "/resourcestep/add/";
	public final String DELETE_SELECTED_RESOURCE = getBPMNURL() + "/resourcestep/delete/";
	
	public final String GET_PROJECT_DOCUMENT = getBPMNURL() + "/documentstep/list/";
	public final String CREATE_PROJECT_DOCUMENT = getBPMNURL() + "/documentstep/add/";
	public final String DELETE_PROJECT_DOCUMENT = getBPMNURL() + "/documentstep/delete/";
	
	public final String GET_STANDARD_DIAGRAM = getBPMNURL() + "/standarddiagram/standard";
	public final String GET_CUSTOM_DIAGRAM = getBPMNURL() + "/standarddiagram/custom";
	public final String GET_STANDARD_PROCESS = getBPMNURL() + "/standardprocess/standard";
	public final String GET_CUSTOM_PROCESS = getBPMNURL() + "/standardprocess/custom";
	
	public final String EXPENSES_DETAIL_URL_SERVICE_ROOT_INSERT = getRootURL() + "/expense-details-service/insert";
	public final String EXPENSES_DETAIL_URL_SERVICE_ROOT_UPDATE = getRootURL() + "/expense-details-service/update/";
	public final String EXPENSES_DETAIL_URL_SERVICE_ROOT_DELETE = getRootURL() + "/expense-details-service/delete/";
	public final String EXPENSES_DETAIL_URL_SERVICE_ROOT_GET_BY_ID = getRootURL() + "/expense-details-service/getById/";
	public final String EXPENSES_DETAIL_URL_SERVICE_ROOT_LIST = getRootURL() + "/expense-details-service/list/";
	
	public final String EXPENSES_URL_SERVICE_ROOT_INSERT = getRootURL() + "/expenses-service/insert";
	public final String EXPENSES_URL_SERVICE_ROOT_UPDATE = getRootURL() + "/expenses-service/update/";
	public final String EXPENSES_URL_SERVICE_ROOT_DELETE = getRootURL() + "/expenses-service/delete/";
	public final String EXPENSES_URL_SERVICE_ROOT_GET_BY_ID = getRootURL() + "/expenses-service/getById/";
	public final String EXPENSES_URL_SERVICE_ROOT_LIST = getRootURL() + "/expenses-service/list/";
	
	public final String SELECTION_URL_SERVICE = getRootURL() + "/selections-service/";
	public final String SELECTION_URL_SERVICE_LIST_STATUS = getRootURL() + "/selections-service/list-statuses/{scope}";
	public final String SELECTION_URL_SERVICE_LIST_TYPES = getRootURL() + "/selections-service/list-types/{scope}";
	
	//file-sharing
	public final String FILE_SHARING_CREATE = getSocialURL() + "/file-sharing/create/";
	public final String FILE_SHARING_LIST_PROJECT = getSocialURL() + "/file-sharing/list-for-group/";
	public final String FILE_SHARING_DELETE = getSocialURL() + "/file-sharing/delete/";
	public final String FILE_SHARING_DOWNLOAD = getSocialURL() + "/file-sharing/download/";
	
	//hrm
	public final String STAFFS_URL_GET_ALL = getHRMURL() + "/staffservice/list/staffs/";
	public final String STAFFS_URL_GET_ALL_DEPARTMENT = getHRMURL() + "/departmentservice/list/departments/";
	public final String STAFFS_URL_GET_IMG = getHRMURL() + "/staffservice/download2/";
}
