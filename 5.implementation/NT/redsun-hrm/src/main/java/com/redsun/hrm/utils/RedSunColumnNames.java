package com.redsun.hrm.utils;

/**
 * This class is contain column name of database
 * Naming convention: [table name_column name] = [table name_column name]
 *
 * Created by HauLL on 2/10/2017.
 */
public interface RedSunColumnNames {

    // call_for_tenders
    String call_for_tenders = "call_for_tenders";
    String call_for_tenders_id = "id";
    String call_for_tenders_reference = "reference";
    String call_for_tenders_name = "name";
    String call_for_tenders_id_call_for_tender_type = "id_call_for_tender_type";
    String call_for_tenders_id_project = "id_project";
    String call_for_tenders_id_user = "id_user";
    String call_for_tenders_description = "description";
    String call_for_tenders_technical_requirements = "technical_requirements";
    String call_for_tenders_business_requirements = "business_requirements";
    String call_for_tenders_other_requirements = "other_requirements";
    String call_for_tenders_creation_date = "creation_date";
    String call_for_tenders_id_status = "id_status";
    String call_for_tenders_id_resource = "id_resource";
    String call_for_tenders_send_date_time = "send_date_time";
    String call_for_tenders_expected_tender_date_time = "expected_tender_date_time";
    String call_for_tenders_max_amount = "max_amount";
    String call_for_tenders_delivery_date = "delivery_date";
    String call_for_tenders_evaluation_max_value = "evaluation_max_value";
    String call_for_tenders_fix_value = "fix_value";
    String call_for_tenders_id_product = "id_product";
    String call_for_tenders_id_product_version = "id_product_version";
    String call_for_tenders_id_component = "id_component";
    String call_for_tenders_id_component_version = "id_component_version";
    String call_for_tenders_result = "result";
    String call_for_tenders_handled = "handled";
    String call_for_tenders_done = "done";
    String call_for_tenders_idle = "idle";
    String call_for_tenders_cancelled = "cancelled";
    String call_for_tenders_handled_date = "handled_date";
    String call_for_tenders_done_date = "done_date";
    String call_for_tenders_idle_date = "idle_date";
    String call_for_tenders_client_id = "client_id";
    String call_for_tenders_ext_col_count = "ext_col_count";


    //project
    String projects = "projects";
    String projects_id = "id";
    String projects_name = "name";
    String projects_description = "description";
    String projects_id_client = "id_client";
    String projects_project_code = "project_code";
    String projects_contract_code = "contract_code";
    String projects_color = "color";
    String projects_id_project = "id_project";
    String projects_idle = "idle";
    String projects_id_user = "id_user";
    String projects_done = "done";
    String projects_idle_date = "idle_date";
    String projects_done_date = "done_date";
    String projects_id_contact = "id_contact";
    String projects_sort_order = "sort_order";
    String projects_id_project_type = "id_project_type";
    String projects_code_type = "code_type";
    String projects_id_recipient = "id_recipient";
    String projects_payment_delay = "payment_delay";
    String projects_longitude = "longitude";
    String projects_latitude = "latitude";
    String projects_id_status = "id_status";
    String projects_id_health = "id_health";
    String projects_fix_planning = "fix_planning";
    String projects_client_code = "client_code";
    String projects_id_overall_progress = "id_overall_progress";
    String projects_cancelled = "cancelled";
    String projects_id_quality = "id_quality";
    String projects_id_trend = "id_trend";
    String projects_id_sponsor = "id_sponsor";
    String projects_creation_date = "creation_date";
    String projects_objectives = "objectives";
    String projects_id_resource = "id_resource";
    String projects_is_under_construction = "is_under_construction";
    String projects_last_update_date_time = "last_update_date_time";
    String projects_id_organization = "id_organization";
    String projects_organization_inherited = "organization_inherited";
    String projects_organization_elementary = "organization_elementary";
    String projects_id_category = "id_category";
    String projects_client_id = "client_id";

    //types
    String types = "types";
    String types_id = "id";
    String types_scope = "scope";
    String types_name = "name";
    String types_sort_order = "sort_order";
    String types_idle = "idle";
    String types_color = "color";
    String types_id_workflow = "id_workflow";
    String types_mandatory_description = "mandatory_description";
    String types_mandatory_result_on_done = "mandatory_result_on_done";
    String types_mandatory_resource_on_handled = "mandatory_resource_on_handled";
    String types_lock_handled = "lock_handled";
    String types_lock_done = "lock_done";
    String types_lock_idle = "lock_idle";
    String types_code = "code";
    String types_internal_data = "internal_data";
    String types_description = "description";
    String types_lock_cancelled = "lock_cancelled";
    String types_show_in_flash = "show_in_flash";
    String types_id_planning_mode = "id_planning_mode";
    String types_mandatory_resolution_on_done = "mandatory_resolution_on_done";
    String types_lock_solved = "lock_solved";
    String types_id_category = "id_category";
    String types_lock_no_left_on_done = "lock_no_left_on_done";
    String types_client_id = "client_id";

    //statuses
    String statuses = "statuses";
    String statuses_id = "id";
    String statuses_name = "name";
    String statuses_set_done_status = "set_done_status";
    String statuses_set_idle_status = "set_idle_status";
    String statuses_color = "color";
    String statuses_sort_order = "sort_order";
    String statuses_idle = "idle";
    String statuses_set_handled_status = "set_handled_status";
    String statuses_is_copy_status = "is_copy_status";
    String statuses_set_cancelled_status = "set_cancelled_status";
    String statuses_client_id = "client_id";

    //attachments
    String attachments = "attachments";
    String attachments_id = "id";
    String attachments_ref_type = "ref_type";
    String attachments_ref_id = "ref_id";
    String attachments_id_user = "id_user";
    String attachments_creation_date = "creation_date";
    String attachments_file_name = "file_name";
    String attachments_description = "description";
    String attachments_sub_directory = "sub_directory";
    String attachments_mime_type = "mime_type";
    String attachments_file_size = "file_size";
    String attachments_link = "link";
    String attachments_type = "type";
    String attachments_id_privacy = "id_privacy";
    String attachments_id_team = "id_team";
    String attachments_client_id = "client_id";
    String attachments_ext_col_count = "ext_col_count";

    //tenders
    String tenders = "tenders";
    String tenders_id = "id";
    String tenders_reference = "reference";
    String tenders_name = "name";
    String tenders_id_tender_type = "id_tender_type";
    String tenders_id_project = "id_project";
    String tenders_id_call_for_tender = "id_call_for_tender";
    String tenders_id_user = "id_user";
    String tenders_creation_date = "creation_date";
    String tenders_id_provider = "id_provider";
    String tenders_external_reference = "external_reference";
    String tenders_description = "description";
    String tenders_id_status = "id_status";
    String tenders_id_resource = "id_resource";
    String tenders_id_contact = "id_contact";
    String tenders_request_date_time = "request_date_time";
    String tenders_expected_tender_date_time = "expected_tender_date_time";
    String tenders_reception_date_time = "reception_date_time";
    String tenders_evaluation_value = "evaluation_value";
    String tenders_evaluation_rank = "evaluation_rank";
    String tenders_offer_validity_end_date = "offer_validity_end_date";
    String tenders_planned_amount = "planned_amount";
    String tenders_tax_pct = "tax_pct";
    String tenders_planned_tax_amount = "planned_tax_amount";
    String tenders_planned_full_amount = "planned_full_amount";
    String tenders_initial_amount = "initial_amount";
    String tenders_initial_tax_amount = "initial_tax_amount";
    String tenders_initial_full_amount = "initial_full_amount";
    String tenders_delivery_delay = "delivery_delay";
    String tenders_delivery_date = "delivery_date";
    String tenders_payment_condition = "payment_condition";
    String tenders_evaluation = "evaluation";
    String tenders_result= "result";
    String tenders_handled = "handled";
    String tenders_done = "done";
    String tenders_idle = "idle";
    String tenders_cancelled = "cancelled";
    String tenders_handled_date = "handled_date";
    String tenders_done_date = "done_date";
    String tenders_idle_date = "idle_date";
    String tenders_client_id = "client_id";
    String tenders_ext_col_count = "ext_col_count";

    // expenses
    String expenses = "expenses";
    String expenses_id = "id";
    String expenses_id_project = "id_project";
    String expenses_id_resource = "id_resource";
    String expenses_id_user = "id_user";
    String expenses_id_expense_type = "id_expense_type";
    String expenses_scope = "scope";
    String expenses_name = "name";
    String expenses_id_status = "id_status";
    String expenses_description = "description";
    String expenses_expense_planned_date = "expense_planned_date";
    String expenses_expense_real_date = "expense_real_date";
    String expenses_planned_amount = "planned_amount";
    String expenses_real_amount = "real_amount";
    String expenses_day = "day";
    String expenses_week = "week";
    String expenses_month = "month";
    String expenses_year = "year";
    String expenses_idle = "idle";
    String expenses_reference = "reference";
    String expenses_external_reference = "external_reference";
    String expenses_cancelled = "cancelled";
    String expenses_id_document = "id_document";
    String expenses_id_provider = "id_provider";
    String expenses_send_date = "send_date";
    String expenses_id_delivery_mode = "id_delivery_mode";
    String expenses_delivery_delay = "delivery_delay";
    String expenses_delivery_date = "delivery_date";
    String expenses_payment_condition = "payment_condition";
    String expenses_reception_date = "reception_date";
    String expenses_result = "result";
    String expenses_tax_pct = "tax_pct";
    String expenses_planned_full_amount = "planned_full_amount";
    String expenses_real_full_amount = "real_full_amount";
    String expenses_idle_date = "idle_date";
    String expenses_handled = "handled";
    String expenses_handled_date = "handled_date";
    String expenses_done = "done";
    String expenses_done_date = "done_date";
    String expenses_id_responsible = "id_responsible";
    String expenses_payment_done = "payment_done";
    String expenses_id_contact = "id_contact";
    String expenses_client_id = "client_id";


    // expense_details
    String expense_details = "expense_details";
    String expense_details_id = "id";
    String expense_details_id_project = "id_project";
    String expense_details_id_expense = "id_expense";
    String expense_details_expense_date = "expense_date";
    String expense_details_name = "name";
    String expense_details_id_expense_detail_type = "id_expense_detail_type";
    String expense_details_value01 = "value01";
    String expense_details_value02 = "value02";
    String expense_details_value03 = "value03";
    String expense_details_unit01 = "unit01";
    String expense_details_unit02 = "unit02";
    String expense_details_unit03 = "unit03";
    String expense_details_description = "description";
    String expense_details_amount = "amount";
    String expense_details_idle = "idle";
    String expense_details_external_reference = "external_reference";
    String expense_details_client_id = "client_id";
    String expense_details_ext_col_count = "ext_col_count";


    // expense_detail_types
    String expense_detail_types = "expense_detail_types";
    String expense_detail_types_id = "id";
    String expense_detail_types_name = "name";
    String expense_detail_types_sort_order = "sort_order";
    String expense_detail_types_value01 = "value01";
    String expense_detail_types_value02 = "value02";
    String expense_detail_types_value03 = "value03";
    String expense_detail_types_unit01 = "unit01";
    String expense_detail_types_unit02 = "unit02";
    String expense_detail_types_unit03 = "unit03";
    String expense_detail_types_idle = "idle";
    String expense_detail_types_description = "description";
    String expense_detail_types_individual = "individual";
    String expense_detail_types_project = "project";
    String expense_detail_types_client_id = "client_id";


    //resources
    String resources = "resources";
    String resources_id = "id";
    String resources_name = "name";
    String resources_full_name = "full_name";
    String resources_email = "email";
    String resources_description = "description";
    String resources_password = "password";
    String resources_id_profile = "id_profile";
    String resources_is_resource = "is_resource";
    String resources_is_user = "is_user";
    String resources_locked = "locked";
    String resources_idle = "idle";
    String resources_phone = "phone";
    String resources_mobile = "mobile";
    String resources_fax = "fax";
    String resources_id_team = "id_team";
    String resources_capacity = "capacity";
    String resources_address = "address";
    String resources_is_contact = "is_contact";
    String resources_id_client = "id_client";
    String resources_id_role = "id_role";
    String resources_is_ldap = "is_ldap";
    String resources_initials = "initials";
    String resources_designation = "designation";
    String resources_street = "street";
    String resources_complement = "complement";
    String resources_zip = "zip";
    String resources_city = "city";
    String resources_state = "state";
    String resources_country = "country";
    String resources_id_recipient = "id_recipient";
    String resources_login_try = "login_try";
    String resources_salt = "salt";
    String resources_crypto = "crypto";
    String resources_id_calendar_definition = "id_calendar_definition";
    String resources_cookie_hash = "cookie_hash";
    String resources_password_change_date = "password_change_date";
    String resources_api_key = "api_key";
    String resources_dont_receive_team_mails = "dont_receive_team_mails";
    String resources_function = "function";
    String resources_id_provider = "id_provider";
    String resources_id_organization = "id_organization";
    String resources_client_id = "client_id";
}




