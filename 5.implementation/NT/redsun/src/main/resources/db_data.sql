INSERT INTO public.clients(name, address, phone, no, logo) VALUES ('Nam Xuân Phong', '© Copyright 2017 Redsun, Ltd, All rights reserved', '0989990017', '3', '/static/images/nxp.png');
INSERT INTO public.menus VALUES (1, 'Calendar', 'Calendar', '/redsun/calendars/', '002', 'glyphicon glyphicon-calendar', 1::bit);
INSERT INTO public.menus VALUES (2, 'HR', 'Human Resource', '/redsun/hr/', '008', 'glyphicon glyphicon-user', 1::bit);
INSERT INTO public.menus VALUES (3, 'WH', 'Warehouse Management', '/redsun/devices/', '011', 'glyphicon glyphicon-plus-sign', 1::bit);
INSERT INTO public.menus VALUES (4, 'Design', 'Design Management', '/redsun/design/', '005', 'glyphicon glyphicon glyphicon-picture', 1::bit);
INSERT INTO public.menus VALUES (5, 'PM', 'Construction Management', '/redsun/project/', '003', 'glyphicon glyphicon-dashboard', 1::bit);
INSERT INTO public.menus VALUES (6, 'Document', 'Document Management', '/redsun/directory/', '006', 'glyphicon glyphicon-folder-open', 1::bit);
INSERT INTO public.menus VALUES (7, 'System', 'System Management', '/redsun/system/', '010', 'glyphicon glyphicon-cog', 1::bit);
INSERT INTO public.menus VALUES (8, 'Reports', 'Reports', '/redsun/pm-report/', '009', 'glyphicon glyphicon-stats', 1::bit);
INSERT INTO public.menus VALUES (9, 'CRM', 'CRM', '/redsun/client/listpage/', '004', 'glyphicon glyphicon-log-out', 1::bit);
INSERT INTO public.menus VALUES (10, 'BPMN', 'BPMN', '/redsun/bpmn/', '001', 'glyphicon glyphicon-log-out', 1::bit);
INSERT INTO public.menus VALUES (11, 'Project Timeline', 'Project Timeline', '/redsun/project-timeline/', '007', 'glyphicon glyphicon-tower', 1::bit);
INSERT INTO public.menus VALUES (12, 'Social', 'Social Network', '/redsun/social/', '012', 'glyphicon glyphicon-link', 1::bit);

INSERT INTO public.permission("permissionId", name, description) VALUES (1, 'r', 'read');
INSERT INTO public.permission("permissionId", name, description) VALUES (2, 'c', 'create');
INSERT INTO public.permission("permissionId", name, description) VALUES (3, 'u', 'update');
INSERT INTO public.permission("permissionId", name, description) VALUES (4, 'd', 'delete');

INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 1, '1|2|3|4');
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 2, '1|2|3|4');
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 3, '1|2|3|4');
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 4, '1|2|3|4');
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 5, '1|2|3|4');
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 6, '1|2|3|4');
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 7, '1|2|3|4');
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 8, '1|2|3|4');
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 9, '1|2|3|4');	
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (2, 8, '1|2|3|4');	
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (3, 8, '1|2|3|4');
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 10, '1|2|3|4');		
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 11, '1|2|3|4');
INSERT INTO public.role_permission_menu(role_id, menu_id, permission)
	VALUES (1, 12, '1|2|3|4');
	
	
INSERT INTO public.roles(name, description)
	VALUES ('ROLE_ADMINISTRATOR', 'ROLE FOR ADMINISTRATORS');
INSERT INTO public.roles(name, description)
	VALUES ('ROLE_PM', 'ROLE FOR PMs');
INSERT INTO public.roles(name, description)
	VALUES ('ROLE_USER', 'ROLE FOR USERS');

INSERT INTO public.users(username, password, profile_id, client_id, status, create_by, create_date)
	VALUES ('admin', '202cb962ac59075b964b07152d234b70',1, 3, 1::bit, 'superadmin', 'now');
	
INSERT INTO public.users_roles(role_id, username, client_id, create_by, create_date)
	VALUES (1, 'admin', 3, 'superadmin', 'now');

	
	
	
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'billPermission', 'Bill');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'call4tenderPermission', 'Call for tender');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'documentsPermission', 'Documents');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'individualExpensePermission', 'Individual expense');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'membersPermisison', 'Members');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'orderPermission', 'Order');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'paymentPermission', 'Payment');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'projectPermission', 'Project');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'projectExpensePermission', 'Project expense');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'quotationPermission', 'Quotation');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'riskPermission', 'Risk');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'taskPermission', 'Task');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('project', 'tenderPermission', 'Tender');



INSERT INTO public.module_properties( name, item, item_title)VALUES ('document', 'uploadDoc', 'Upload Documents');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('document', 'role', 'Role');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('document', 'folder', 'Folder');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('document', 'assignFolder', 'Assign Folder');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('document', 'configureMail', 'Configure mail');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('document', 'downloadFile', 'Download Files');
INSERT INTO public.module_properties( name, item, item_title)VALUES ('document', 'deleteFile', 'Delete Files');
