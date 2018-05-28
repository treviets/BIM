package com.redsun.whm.utils;

/**
 * This class contains all custom query using in *DAOImpl
 * Naming convention: [Table name 1(_join_Table name 2_...)]_[action: get/list/insert/update/delete/create/search(_other explain)] = Query String
 * Created by HauLL on 3/3/2017.
 */
public interface RedSunQueryCollections {

    String CallForTenders_join_Projects_join_Types_join_Statuses_getAllByClientId =
            "SELECT "
                    + RedSunColumnNames.call_for_tenders + "." + RedSunColumnNames.call_for_tenders_id + " AS id, "
                    + RedSunColumnNames.projects + "." + RedSunColumnNames.projects_name + " AS project_name, "
                    + RedSunColumnNames.types + "." + RedSunColumnNames.types_name + " AS type_name, "
                    + RedSunColumnNames.call_for_tenders + "." + RedSunColumnNames.call_for_tenders_name + " AS tender_name, "
                    + RedSunColumnNames.statuses + "." + RedSunColumnNames.statuses_name + " AS status, "
                    + RedSunColumnNames.call_for_tenders + "." + RedSunColumnNames.call_for_tenders_done + " AS closed, "
                    + "count(*) over() AS "+ RedSunColumnNames.call_for_tenders_ext_col_count +
            " FROM "
                    + RedSunColumnNames.call_for_tenders + " JOIN " + RedSunColumnNames.projects
                        + " ON " + RedSunColumnNames.call_for_tenders + "." + RedSunColumnNames.call_for_tenders_id_project + " = " + RedSunColumnNames.projects + "." + RedSunColumnNames.projects_id
                    + " JOIN " + RedSunColumnNames.types
                        + " ON " + RedSunColumnNames.call_for_tenders + "." + RedSunColumnNames.call_for_tenders_id_call_for_tender_type + " = " + RedSunColumnNames.types + "." + RedSunColumnNames.types_id
                    + " JOIN " + RedSunColumnNames.statuses
                        + " ON " + RedSunColumnNames.call_for_tenders + "." + RedSunColumnNames.call_for_tenders_id_status + " = " + RedSunColumnNames.statuses + "." + RedSunColumnNames.statuses_id +
            " WHERE "
                    + RedSunColumnNames.call_for_tenders + "." + RedSunColumnNames.call_for_tenders_client_id + " = ? OR 1 = 1" +
            "ORDER BY tender_name ASC LIMIT ? OFFSET ?";


    String CallForTenders_getTenderByClientIdAndTenderId =
            "SELECT "
                    + RedSunColumnNames.call_for_tenders_id + ", "
                    + RedSunColumnNames.call_for_tenders_name + ", "
                    + RedSunColumnNames.call_for_tenders_reference + ", "
                    + RedSunColumnNames.call_for_tenders_id_user + ", "
                    + RedSunColumnNames.call_for_tenders_creation_date + ", "
                    + RedSunColumnNames.call_for_tenders_id_status + ", "
                    + RedSunColumnNames.call_for_tenders_id_resource + ", "
                    + RedSunColumnNames.call_for_tenders_send_date_time + ", "
                    + RedSunColumnNames.call_for_tenders_delivery_date + ", "
                    + RedSunColumnNames.call_for_tenders_evaluation_max_value + ", "
                    + RedSunColumnNames.call_for_tenders_fix_value + ", "
                    + RedSunColumnNames.call_for_tenders_id_product + ", "
                    + RedSunColumnNames.call_for_tenders_id_product_version + ", "
                    + RedSunColumnNames.call_for_tenders_id_component + ", "
                    + RedSunColumnNames.call_for_tenders_id_component_version + ", "
                    + RedSunColumnNames.call_for_tenders_result + ", "
                    + RedSunColumnNames.call_for_tenders_handled + ", "
                    + RedSunColumnNames.call_for_tenders_done + ", "
                    + RedSunColumnNames.call_for_tenders_idle + ", "
                    + RedSunColumnNames.call_for_tenders_cancelled + ", "
                    + RedSunColumnNames.call_for_tenders_handled_date + ", "
                    + RedSunColumnNames.call_for_tenders_done_date + ", "
                    + RedSunColumnNames.call_for_tenders_idle_date + ", "
                    + RedSunColumnNames.call_for_tenders_client_id + ", "
                    + RedSunColumnNames.call_for_tenders_id_call_for_tender_type + ", "
                    + RedSunColumnNames.call_for_tenders_id_project + ", "
                    + RedSunColumnNames.call_for_tenders_max_amount + ", "
                    + RedSunColumnNames.call_for_tenders_expected_tender_date_time + ", "
                    + RedSunColumnNames.call_for_tenders_description + ", "
                    + RedSunColumnNames.call_for_tenders_business_requirements + ", "
                    + RedSunColumnNames.call_for_tenders_technical_requirements + ", "
                    + RedSunColumnNames.call_for_tenders_other_requirements + ", "
                    + "0 AS "
                    + RedSunColumnNames.call_for_tenders_ext_col_count +

            " FROM "
                    + RedSunColumnNames.call_for_tenders +
            " WHERE ("
                    + RedSunColumnNames.call_for_tenders_client_id + " = ? OR 1 = 1) AND "
                    + RedSunColumnNames.call_for_tenders_id + " = ?";

    String Types_getTypeByScope =
            "SELECT "
                    + RedSunColumnNames.types_id + ", "
                    + RedSunColumnNames.types_name +
            " FROM "
                    + RedSunColumnNames.types +
            " WHERE "
                    + RedSunColumnNames.types_scope + " = ?";

    String CallForTenders_insertTender =
            "INSERT INTO " + RedSunColumnNames.call_for_tenders + " ( "
                    + RedSunColumnNames.call_for_tenders_reference + ", "
                    + RedSunColumnNames.call_for_tenders_name + ", "
                    + RedSunColumnNames.call_for_tenders_id_call_for_tender_type + ", "
                    + RedSunColumnNames.call_for_tenders_id_project + ", "
                    + RedSunColumnNames.call_for_tenders_id_user + ", "
                    + RedSunColumnNames.call_for_tenders_description + ", "
                    + RedSunColumnNames.call_for_tenders_technical_requirements + ", "
                    + RedSunColumnNames.call_for_tenders_business_requirements + ", "
                    + RedSunColumnNames.call_for_tenders_other_requirements + ", "
                    + RedSunColumnNames.call_for_tenders_creation_date + ", "
                    + RedSunColumnNames.call_for_tenders_id_status + ", "
                    + RedSunColumnNames.call_for_tenders_id_resource + ", "
                    + RedSunColumnNames.call_for_tenders_send_date_time + ", "
                    + RedSunColumnNames.call_for_tenders_expected_tender_date_time + ", "
                    + RedSunColumnNames.call_for_tenders_max_amount + ", "
                    + RedSunColumnNames.call_for_tenders_delivery_date + ", "
                    + RedSunColumnNames.call_for_tenders_evaluation_max_value + ", "
                    + RedSunColumnNames.call_for_tenders_fix_value + ", "
                    + RedSunColumnNames.call_for_tenders_id_product + ", "
                    + RedSunColumnNames.call_for_tenders_id_product_version + ", "
                    + RedSunColumnNames.call_for_tenders_id_component + ", "
                    + RedSunColumnNames.call_for_tenders_id_component_version + ", "
                    + RedSunColumnNames.call_for_tenders_result + ", "
                    + RedSunColumnNames.call_for_tenders_handled + ", "
                    + RedSunColumnNames.call_for_tenders_done + ", "
                    + RedSunColumnNames.call_for_tenders_idle + ", "
                    + RedSunColumnNames.call_for_tenders_cancelled + ", "
                    + RedSunColumnNames.call_for_tenders_handled_date + ", "
                    + RedSunColumnNames.call_for_tenders_done_date + ", "
                    + RedSunColumnNames.call_for_tenders_idle_date + ", "
                    + RedSunColumnNames.call_for_tenders_client_id + ")" +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?, ?, ?, ?, ?, ?::bit, ?::bit, ?::bit, ?::bit, ?, ?, ?, ?)";

    String CallForTenders_updateTender =
            "UPDATE " + RedSunColumnNames.call_for_tenders + " SET "
                    + RedSunColumnNames.call_for_tenders_reference + " = ?, "
                    + RedSunColumnNames.call_for_tenders_name + " = ?, "
                    + RedSunColumnNames.call_for_tenders_id_call_for_tender_type + " = ?, "
                    + RedSunColumnNames.call_for_tenders_id_project + " = ?, "
                    + RedSunColumnNames.call_for_tenders_id_user + " = ?, "
                    + RedSunColumnNames.call_for_tenders_description + " = ?, "
                    + RedSunColumnNames.call_for_tenders_technical_requirements + " = ?, "
                    + RedSunColumnNames.call_for_tenders_business_requirements + " = ?, "
                    + RedSunColumnNames.call_for_tenders_other_requirements + " = ?, "
                    + RedSunColumnNames.call_for_tenders_creation_date + " = ?, "
                    + RedSunColumnNames.call_for_tenders_id_status + " = ?, "
                    + RedSunColumnNames.call_for_tenders_id_resource + " = ?, "
                    + RedSunColumnNames.call_for_tenders_send_date_time + " = ?, "
                    + RedSunColumnNames.call_for_tenders_expected_tender_date_time + " = ?, "
                    + RedSunColumnNames.call_for_tenders_max_amount + " = ?, "
                    + RedSunColumnNames.call_for_tenders_delivery_date + " = ?, "
                    + RedSunColumnNames.call_for_tenders_evaluation_max_value + " = ?, "
                    + RedSunColumnNames.call_for_tenders_fix_value + " = ?::bit, "
                    + RedSunColumnNames.call_for_tenders_id_product + " = ?, "
                    + RedSunColumnNames.call_for_tenders_id_product_version + " = ?, "
                    + RedSunColumnNames.call_for_tenders_id_component + " = ?, "
                    + RedSunColumnNames.call_for_tenders_id_component_version + " = ?, "
                    + RedSunColumnNames.call_for_tenders_result + " = ?, "
                    + RedSunColumnNames.call_for_tenders_handled + " = ?::bit, "
                    + RedSunColumnNames.call_for_tenders_done + " = ?::bit, "
                    + RedSunColumnNames.call_for_tenders_idle + " = ?::bit, "
                    + RedSunColumnNames.call_for_tenders_cancelled + " = ?::bit, "
                    + RedSunColumnNames.call_for_tenders_handled_date + " = ?, "
                    + RedSunColumnNames.call_for_tenders_done_date + " = ?, "
                    + RedSunColumnNames.call_for_tenders_idle_date + " = ?, "
                    + RedSunColumnNames.call_for_tenders_client_id + " = ? " +
            "WHERE "
                    + RedSunColumnNames.call_for_tenders_id + " = ?";

    String CallForTenders_deleteTender =
            "DELETE FROM " + RedSunColumnNames.call_for_tenders + " WHERE " +  RedSunColumnNames.call_for_tenders_id + " = ?";

    String Attachments_getByRefId =
            "SELECT "
                    + RedSunColumnNames.attachments_id + ", "
                    + RedSunColumnNames.attachments_ref_id + ", "
                    + RedSunColumnNames.attachments_ref_type + ", "
                    + RedSunColumnNames.attachments_id_user + ", "
                    + RedSunColumnNames.attachments_creation_date + ", "
                    + RedSunColumnNames.attachments_file_name + ", "
                    + RedSunColumnNames.attachments_description + ", "
                    + RedSunColumnNames.attachments_sub_directory + ", "
                    + RedSunColumnNames.attachments_mime_type + ", "
                    + RedSunColumnNames.attachments_file_size + ", "
                    + RedSunColumnNames.attachments_link + ", "
                    + RedSunColumnNames.attachments_type + ", "
                    + RedSunColumnNames.attachments_id_privacy + ", "
                    + RedSunColumnNames.attachments_id_team + ", "
                    + RedSunColumnNames.attachments_client_id + ", "
                    + "0 AS " + RedSunColumnNames.attachments_ext_col_count +
            " FROM "
                    + RedSunColumnNames.attachments +
            " WHERE "
                    + RedSunColumnNames.attachments_ref_id + " = ?";


    String Tenders_join_Projects_join_Types_join_Statuses_getAll =
            "SELECT "
                    + RedSunColumnNames.tenders + "." + RedSunColumnNames.tenders_id + " AS id, "
                    + RedSunColumnNames.projects + "." + RedSunColumnNames.projects_name + " AS project_name, "
                    + RedSunColumnNames.types + "." + RedSunColumnNames.types_name + " AS type_name, "
                    + RedSunColumnNames.tenders + "." + RedSunColumnNames.tenders_name + " AS tender_name, "
                    + RedSunColumnNames.tenders + "." + RedSunColumnNames.tenders_evaluation_value + " AS evaluation_value, "
                    + RedSunColumnNames.tenders + "." + RedSunColumnNames.tenders_planned_amount + " AS planned_amount, "
                    + RedSunColumnNames.statuses + "." + RedSunColumnNames.statuses_name + " AS status, "
                    + RedSunColumnNames.tenders + "." + RedSunColumnNames.tenders_done + " AS closed, "
                    + "count(*) over() AS "+ RedSunColumnNames.tenders_ext_col_count +
            " FROM "
                    + RedSunColumnNames.tenders + " JOIN " + RedSunColumnNames.projects
                    + " ON " + RedSunColumnNames.tenders + "." + RedSunColumnNames.tenders_id_project + " = " + RedSunColumnNames.projects + "." + RedSunColumnNames.projects_id
                    + " JOIN " + RedSunColumnNames.types
                    + " ON " + RedSunColumnNames.tenders + "." + RedSunColumnNames.tenders_id_tender_type + " = " + RedSunColumnNames.types + "." + RedSunColumnNames.types_id
                    + " JOIN " + RedSunColumnNames.statuses
                    + " ON " + RedSunColumnNames.tenders + "." + RedSunColumnNames.tenders_id_status + " = " + RedSunColumnNames.statuses + "." + RedSunColumnNames.statuses_id
            + " ORDER BY tender_name ASC LIMIT ? OFFSET ?";

    String Expenses_join_Projects_join_ExpenseTypes_join_Resources_join_Statues_getAllByExpenseTypes =
            "SELECT "
                    + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_id + " AS id, "
                    + RedSunColumnNames.projects + "." + RedSunColumnNames.projects_name + " AS project, "
                    + RedSunColumnNames.expense_detail_types + "." + RedSunColumnNames.expense_detail_types_name + " AS type, "
                    + RedSunColumnNames.resources + "." + RedSunColumnNames.resources_name + " AS resource, "
                    + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_name + " AS name, "
                    + RedSunColumnNames.statuses + "." + RedSunColumnNames.statuses_name + " AS status, "
                    + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_done + " AS closed, "
                    + "count(*) over() AS "+ RedSunColumnNames.tenders_ext_col_count +
            " FROM "
                    + RedSunColumnNames.expenses + " JOIN " + RedSunColumnNames.projects
                    + " ON " + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_id_project + " = " + RedSunColumnNames.projects + "." + RedSunColumnNames.projects_id
                    + " JOIN " + RedSunColumnNames.expense_detail_types
                    + " ON " + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_id_expense_type + " = " + RedSunColumnNames.expense_detail_types + "." + RedSunColumnNames.expense_detail_types_id
                    + " JOIN " + RedSunColumnNames.resources
                    + " ON " + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_id_resource + " = " + RedSunColumnNames.resources + "." + RedSunColumnNames.resources_id
                    + " JOIN " + RedSunColumnNames.statuses
                    + " ON " + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_id_status + " = " + RedSunColumnNames.statuses + "." + RedSunColumnNames.statuses_id
            + " WHERE (" + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_client_id + " = ? OR 1 = 1) AND " + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_scope + " LIKE ?"
            + " ORDER BY project ASC LIMIT ? OFFSET ?";


    String Expenses_join_Projects_join_ExpenseTypes_join_Statues_getAllByExpenseTypes =
            "SELECT "
                    + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_id + " AS id, "
                    + RedSunColumnNames.projects + "." + RedSunColumnNames.projects_name + " AS project, "
                    + RedSunColumnNames.expense_detail_types + "." + RedSunColumnNames.expense_detail_types_name + " AS type, "
                    + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_name + " AS name, "
                    + RedSunColumnNames.statuses + "." + RedSunColumnNames.statuses_name + " AS status, "
                    + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_done + " AS closed, "
                    + "count(*) over() AS "+ RedSunColumnNames.tenders_ext_col_count +
                    " FROM "
                    + RedSunColumnNames.expenses + " JOIN " + RedSunColumnNames.projects
                    + " ON " + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_id_project + " = " + RedSunColumnNames.projects + "." + RedSunColumnNames.projects_id
                    + " JOIN " + RedSunColumnNames.expense_detail_types
                    + " ON " + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_id_expense_type + " = " + RedSunColumnNames.expense_detail_types + "." + RedSunColumnNames.expense_detail_types_id
                    + " JOIN " + RedSunColumnNames.statuses
                    + " ON " + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_id_status + " = " + RedSunColumnNames.statuses + "." + RedSunColumnNames.statuses_id
                    + " WHERE (" + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_client_id + " = ? OR 1 = 1) AND " + RedSunColumnNames.expenses + "." + RedSunColumnNames.expenses_scope + " LIKE ?"
                    + " ORDER BY project ASC LIMIT ? OFFSET ?";


    String ExpensesDetail_join_ExpenseTypes_getByExpensesId =
            "SELECT "
                + RedSunColumnNames.expense_details + "." + RedSunColumnNames.expense_details_id + " AS id, "
                + RedSunColumnNames.expense_details + "." + RedSunColumnNames.expense_details_expense_date + " AS date, "
                + RedSunColumnNames.expense_details + "." + RedSunColumnNames.expense_details_name + " AS name, "
                + RedSunColumnNames.expense_detail_types + "." + RedSunColumnNames.expense_detail_types_name + " AS type, "
                + RedSunColumnNames.expense_details + "." + RedSunColumnNames.expense_details_description + " AS detail, "
                + RedSunColumnNames.expense_details + "." + RedSunColumnNames.expense_details_amount + " AS amount, "
                + "count(*) over() AS "+ RedSunColumnNames.expense_details_ext_col_count +
            " FROM "
                + RedSunColumnNames.expense_details + " JOIN " +  RedSunColumnNames.expense_detail_types
                + " ON " + RedSunColumnNames.expense_details + "." + RedSunColumnNames.expense_details_id_expense_detail_type + " = "
                + RedSunColumnNames.expense_detail_types + "." + RedSunColumnNames.expense_detail_types_id
            + " WHERE (" + RedSunColumnNames.expense_details + "." + RedSunColumnNames.expense_details_client_id + " = ? OR 1 = 1) AND " + RedSunColumnNames.expense_details + "." + RedSunColumnNames.expense_details_id_expense + " = ?"
            + " ORDER BY name ASC LIMIT ? OFFSET ?";

}
