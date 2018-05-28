-- insert data into default_directories
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(1, 'BIM', null, 0, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(2, 'WIP', 'BIM/', 1, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name,trash) 
VALUES(3, 'Shared', 'BIM/', 1, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(4, 'Published', 'BIM/', 1, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(5, 'Received', 'BIM/', 1, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(6, 'Library', 'BIM/', 1, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(7, 'Temp', 'BIM/', 1, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(8, 'CAD', 'BIM/WIP/', 2, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(9, 'Export', 'BIM/WIP/', 2, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(10, 'Revit', 'BIM/WIP/', 2, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash)
VALUES(11, 'Others', 'BIM/WIP/CAD/', 8, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(12, 'Submission', 'BIM/WIP/CAD/', 8, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(13, 'Tender', 'BIM/WIP/CAD/', 8, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash)
VALUES(14, 'C_Construction', 'BIM/WIP/CAD/Others/', 11, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(15, 'H_Brochure', 'BIM/WIP/CAD/Others/', 11, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(16, 'K_Preliminary', 'BIM/WIP/CAD/Others/', 11, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(17, 'B_Building', 'BIM/WIP/CAD/Submission/', 12, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(18, 'P_Planning', 'BIM/WIP/CAD/Submission/', 12, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(19, 'S_Tenancy', 'BIM/WIP/CAD/Tender/', 13, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(20, 'T_Tender', 'BIM/WIP/CAD/Tender/', 13, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(21, 'W_CurtainWallingTender', 'BIM/WIP/CAD/Tender/', 13, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(22, '2D_Links', 'BIM/WIP/Revit/', 10, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(23, 'Modules', 'BIM/WIP/Revit/', 10, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(24, 'CAD', 'BIM/Shared/', 3, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(25, 'Co_ord', 'BIM/Shared/', 3, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(26, 'Export', 'BIM/Shared/', 3, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(27, 'Revit', 'BIM/Shared/', 3, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash)
VALUES(28, 'Others', 'BIM/Published/', 4, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(29, 'Submission', 'BIM/Published/', 4, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(30, 'Tender', 'BIM/Published/', 4, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash)
VALUES(31, 'C_Construction', 'BIM/Published/Others/', 28, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(32, 'H_Brochure', 'BIM/Published/Others/', 28, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(33, 'K_Preliminary', 'BIM/Published/Others/', 28, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(34, 'B_Building', 'BIM/Published/Submission/', 29, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(35, 'P_Planning', 'BIM/Published/Submission/', 29, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(36, 'S_Tenancy', 'BIM/Published/Tender/', 30, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(37, 'T_Tender', 'BIM/Published/Tender/', 30, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(38, 'W_CurtainWallingTender', 'BIM/Published/Tender/', 30, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(39, 'Families', 'BIM/Library/', 6, 0, 0, null, null, 'admin', 0);
INSERT INTO default_directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(40, 'Title Blocks', 'BIM/Library/', 6, 0, 0, null, null, 'admin', 0);


ALTER SEQUENCE default_directories_id_seq START WITH 41;
ALTER SEQUENCE default_directories_id_seq RESTART;

--insert data into directory
INSERT INTO directories(id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) 
VALUES(1, 'Projects', null, 0, 0, 3, null, null, 'admin', 0);
ALTER SEQUENCE directories_id_seq START WITH 2;
ALTER SEQUENCE directories_id_seq RESTART;

-- insert data into directory_resources
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(1, 1, 0, 1, 3, 'admin');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(2, 2, 0, 1, 3, 'admin');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(3, 3, 0, 1, 3, 'admin');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(4, 4, 0, 1, 3, 'admin');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(5, 5, 0, 1, 3, 'admin');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(6, 6, 0, 1, 3, 'admin');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(7, 7, 0, 1, 3, 'admin');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(8, 8, 0, 1, 3, 'admin');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(9, 9, 0, 1, 3, 'admin');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(10, 10, 0, 1, 3, 'admin');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(11, 2, 0, 1, 3, 'viewuser');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(12, 3, 0, 1, 3, 'viewuser');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(13, 4, 0, 1, 3, 'viewuser');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(14, 7, 0, 1, 3, 'updateuser');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(15, 9, 0, 1, 3, 'updateuser');
INSERT INTO directory_resources(id, directory_id, project_id, resource_id, client_id, user_name) 
VALUES(16, 10, 0, 1, 3, 'updateuser');
ALTER SEQUENCE directory_resources_id_seq START WITH 17;
ALTER SEQUENCE directory_resources_id_seq RESTART;

-- insert data into documents
INSERT INTO documents(id, name, location, project_id, client_id, ref_type, directory_id, mime_type, file_size, link, document_type, document_version_id, locked, description, user_name, create_date_time) 
VALUES(1, 'PlanningElementDaoImpl.java', 'BOD\HR', 0, 1, 'Directory', 2, '', 10, null, 0, 1, 0, 'kiểm tra file',  'admin', '2017-06-01');
INSERT INTO documents(id, name, location, project_id, client_id, ref_type, directory_id, mime_type, file_size, link, document_type, document_version_id, locked, description, user_name, create_date_time) 
VALUES(2, 'hien_thuc_quy_trinh_nghiep_vu_TENDER_1.0.docx', 'BOD\HR', 0, 1, 'Directory', 2, '', 105, null, 0, 1, 0, 'kiểm tra file',  'admin', '2017-06-02');
INSERT INTO documents(id, name, location, project_id, client_id, ref_type, directory_id, mime_type, file_size, link, document_type, document_version_id, locked, description, user_name, create_date_time) 
VALUES(3, 'Document.java', 'BOD\TENDER', 0, 1, 'Directory', 6, '', 3, null, 0, 1, 0, 'kiểm tra file',  'admin', '2017-06-02');
INSERT INTO documents(id, name, location, project_id, client_id, ref_type, directory_id, mime_type, file_size, link, document_type, document_version_id, locked, description, user_name, create_date_time) 
VALUES(4, 'juke box system.docx', 'BOD\TENDER', 0, 1, 'Directory', 6, '', 15, null, 0, 1, 0, 'kiểm tra file',  'admin', '2017-06-02');

ALTER SEQUENCE documents_id_seq START WITH 5;
ALTER SEQUENCE documents_id_seq RESTART;

