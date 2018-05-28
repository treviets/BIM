--test project_resources
INSERT INTO project_resources(id, resource_id, salary, project_id, client_id) VALUES (1, 1, 10000000, 1, 1);
INSERT INTO project_resources(id, resource_id, salary, project_id, client_id) VALUES (2, 2, 100000, 1, 1);
INSERT INTO project_resources(id, resource_id, salary, project_id, client_id) VALUES (3, 3, 15000000, 1, 1);
INSERT INTO project_resources(id, resource_id, salary, project_id, client_id) VALUES (4, 4, 9000000, 1, 1);
INSERT INTO project_resources(id, resource_id, salary, project_id, client_id) VALUES (5, 5, 3000000, 1, 1);
ALTER SEQUENCE project_resources_id_seq START WITH 6;
ALTER SEQUENCE project_resources_id_seq RESTART;

INSERT INTO public.units(name, client_id) VALUES ('Chiếc', 1);
INSERT INTO public.units(name, client_id) VALUES ('Cái', 1);
INSERT INTO public.units(name, client_id) VALUES ('Bộ', 1);
INSERT INTO public.units(name, client_id) VALUES ('Lu´', 1);
INSERT INTO public.units(name, client_id) VALUES ('Mét', 1);
INSERT INTO public.units(name, client_id) VALUES ('Mét vuông', 1);
INSERT INTO public.units(name, client_id) VALUES ('Mét khối', 1);
INSERT INTO public.units(name, client_id) VALUES ('Kilograms', 1);
INSERT INTO public.units(name, client_id) VALUES ('Tấn', 1);
INSERT INTO public.units(name, client_id) VALUES ('100m', 1);
INSERT INTO public.units(name, client_id) VALUES ('Cuộn', 1);
INSERT INTO public.units(name, client_id) VALUES ('Ống', 1);
INSERT INTO public.units(name, client_id) VALUES ('Bình', 1);
INSERT INTO public.units(name, client_id) VALUES ('Cây', 1);




INSERT INTO projects(
            id, code, name, location, description, customer_id, parent_id, duration, work_sunday,
            start_date, end_date, closed_date, done_date, type_id, 
            how_many_day_payment_delay, status_id, creation_date, last_update_date_time, 
            client_id, update_by, amount)
    VALUES (1, 'P001', 'Luxury Building', 'Da Nang city', 'With France Style and Convenient', 1, 1, 24, '1'::bit, 
            '2017-03-03', '2017-03-26', '2017-03-03', '2017-03-03', 1, 
            1, 1, '2017-03-03', '2017-03-03', 
            1, 'admin', 2000000000);
INSERT INTO projects(
            id, code, name, location, description, customer_id, parent_id, 
            start_date, end_date, closed_date, done_date, type_id, 
            how_many_day_payment_delay, status_id, creation_date, last_update_date_time, 
            client_id, update_by, amount)
    VALUES (2, 'P002', 'Sai Gon Apartment', 'Go Vap District', 'Have park, pool, mini shop', 2, 2, 
            '2017-04-03', '2019-04-26', '2019-04-03', '2019-04-03', 2, 
            2, 1, '2017-04-03', '2017-04-03', 
            1, 'manager', 250000000);
INSERT INTO projects(
            id, code, name, location, description, customer_id, parent_id, 
            start_date, end_date, closed_date, done_date, type_id, 
            how_many_day_payment_delay, status_id, creation_date, last_update_date_time, 
            client_id, update_by, amount)
    VALUES (3, 'P003', 'Bach Ma Bridge', 'District 10', 'From District 5 to District 10', 1, 3, 
            '2017-12-03', '2018-12-26', '2018-12-03', '2018-12-03', 1, 
            3, 1, '2017-12-03', '2017-12-03', 
            1, 'user', 4000000000);
ALTER SEQUENCE projects_id_seq START WITH 4  ;
ALTER SEQUENCE projects_id_seq RESTART;     


--test clients
INSERT INTO clients(
            id, name, description, client_code, idle, payment_delay, tax, 
            designation, street, complement, zip, city, state, country, id_client_type, 
            payment_delay_end_of_month, num_tax, id_payment_delay, client_id)
    VALUES (1, 'Sunrise Riverside', 'description', '001', '1'::bit, 10, 0.0, 
            'designation', 'street', 'complement', 'zip', 'Ho Chi Minh', 'state', 'Viet Nam', 131,
            '1'::bit, 'num_tax', 1, 1);
INSERT INTO clients(
            id, name, description, client_code, idle, payment_delay, tax, 
            designation, street, complement, zip, city, state, country, id_client_type, 
            payment_delay_end_of_month, num_tax, id_payment_delay, client_id)
    VALUES (2, 'Viva Riverside', 'description2', '002', '1'::bit, 10, 0.0, 
            'designation2', 'street2', 'complement2', 'zip', 'Da Nang', 'state2', 'Viet Nam', 131,
            '1'::bit, 'num_tax2', 1, 1);
INSERT INTO clients(
            id, name, description, client_code, idle, payment_delay, tax, 
            designation, street, complement, zip, city, state, country, id_client_type, 
            payment_delay_end_of_month, num_tax, id_payment_delay, client_id)
    VALUES (3, 'Luxgarden', 'description3', '003', '1'::bit, 10, 0.0, 
            'designation3', 'street2', 'complement3', 'zip', 'Da Nang', 'state3', 'Viet Nam', 131,
            '1'::bit, 'num_tax3', 1, 1);
INSERT INTO clients(
            id, name, description, client_code, idle, payment_delay, tax, 
            designation, street, complement, zip, city, state, country, id_client_type, 
            payment_delay_end_of_month, num_tax, id_payment_delay, client_id)
    VALUES (4, 'Petroland', 'Customer 1', '003', '1'::bit, 10, 0.0, 
            'designation3', 'street2', 'complement3', 'zip', 'Da Nang', 'state3', 'Viet Nam', 98,
            '1'::bit, 'num_tax3', 1, 1);            
ALTER SEQUENCE clients_id_seq START WITH 5;
ALTER SEQUENCE clients_id_seq RESTART;               
--test tasks
INSERT INTO tasks(
            task_name, description, wbs, parent_id, id_affectation, responsible, 
            task_owner, start_date, end_date, create_date, update_date, estimate_time, 
            actually_time, project_id, status_id, priority_id, type_id, completed, comment, is_trash, client_id)

     VALUES ('San lắp mặt bằng',  'San lắp mặt bằng', 1, 0, 2, 1, 
            'admin', '2017-03-03', '2017-03-04', '2017-03-03', null, 5, 
            4, 1, 15, 2, 27, 30, 'Stating...', '0', 1);
INSERT INTO tasks(
            task_name, description, wbs, parent_id, id_affectation, responsible, 
            task_owner, start_date, end_date, create_date, update_date, estimate_time, 
            actually_time, project_id, status_id, priority_id, type_id, completed, comment, is_trash, client_id)
    VALUES ('Ä�á»• ná»�n',  'Ä�á»• ná»�n', 2, 0, 2, 2, 
            'admin', '2017-03-03', '2017-04-04', '2017-03-03', null, 5, 
            4, 1, 15, 2, 27, 30, 'Stating...', '1', 1);
INSERT INTO tasks(
            task_name, description, wbs, parent_id, id_affectation, responsible, 
            task_owner, start_date, end_date, create_date, update_date, estimate_time, 
            actually_time, project_id, status_id, priority_id, type_id, completed, comment, is_trash, client_id)

    VALUES ('Dá»�ng cá»«',  'Dá»�ng cá»«', 3, 1, 3, 3, 
            'admin', '2017-03-03', '2017-03-20', '2017-03-03', null, 5, 
            4, 1, 15, 2, 27, 30, 'Stating...', '0', 1);            


--test comment
INSERT INTO comments (id_task, content, comment_by, create_date_time) VALUES (1, 'Waiting...', 'admin', null);
INSERT INTO comments (id_task, content, comment_by, create_date_time) VALUES (2, 'Waiting...', 'admin', null);
INSERT INTO comments (id_task, content, comment_by, create_date_time) VALUES (1, 'Doing...', 'admin', null);


INSERT INTO types VALUES ('1', 'Risk', 'Contractual', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('2', 'Risk', 'Operational', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('3', 'Risk', 'Technical', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('4', 'Issue', 'Technical issue', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('5', 'Issue', 'Process non conformity', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('6', 'Issue', 'Quality non conformity', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('7', 'Issue', 'Process non appliability', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('8', 'Issue', 'Customer complaint', '90', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('9', 'Issue', 'Delay non respect', '50', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('10', 'Issue', 'Resource management issue', '70', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('12', 'Issue', 'Financial loss', '80', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('13', 'Message', 'ALERT', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('14', 'Message', 'WARNING', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('15', 'Message', 'INFO', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('16', 'Ticket', 'Incident', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('17', 'Ticket', 'Support / Assistance', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('18', 'Ticket', 'Anomaly / Bug', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('19', 'Activity', 'Development', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('20', 'Activity', 'Evolution', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('21', 'Activity', 'Management', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('22', 'Activity', 'Phase', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('23', 'Milestone', 'Deliverable', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('24', 'Milestone', 'Incoming', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('25', 'Milestone', 'Key date', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('26', 'Activity', 'Task', '1', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('27', 'Action', 'Project', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('28', 'Action', 'Internal', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('29', 'Action', 'Customer', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('30', 'Meeting', 'Steering Committee', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('31', 'Meeting', 'Progress Metting', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('32', 'Meeting', 'Team meeting', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('33', 'Decision', 'Functional', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('34', 'Decision', 'Operational', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('35', 'Decision', 'Contractual', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('36', 'Decision', 'Strategic', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('37', 'Question', 'Functional', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('38', 'Question', 'Technical', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('39', 'IndividualExpense', 'Expense report', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('40', 'ProjectExpense', 'Machine expense', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('41', 'ProjectExpense', 'Office expense', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('46', 'Payment', 'partial payment', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('47', 'Payment', 'final payment', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('48', 'Project', 'Fixed Price', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('49', 'Project', 'Time & Materials', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('50', 'Project', 'Capped Time & Materials', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('51', 'Project', 'Internal', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('52', 'Project', 'Administrative', '80', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('53', 'Project', 'Template', '90', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('54', 'Document', 'Need expression', '210', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('55', 'Document', 'General Specification', '220', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('56', 'Document', 'Detailed Specification', '230', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('57', 'Document', 'General Conception', '240', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('58', 'Document', 'Detail Conception', '250', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('59', 'Document', 'Test Plan', '260', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('60', 'Document', 'Installaton manual', '270', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('61', 'Document', 'Exploitation manual', '280', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('62', 'Document', 'User manual', '290', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('63', 'Document', 'Contract', '110', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('64', 'Document', 'Management', '120', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('65', 'Document', 'Meeting Review', '130', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('66', 'Document', 'Follow-up', '140', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('67', 'Document', 'Financial', '150', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('68', 'Versioning', 'evolutive', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('69', 'Versioning', 'chronological', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('70', 'Versioning', 'sequential', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('71', 'Versioning', 'external', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('72', 'Bill', 'Partial bill', '100', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('73', 'Bill', 'Final bill', '200', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('74', 'Bill', 'Complete bill', '300', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('75', 'Requirement', 'Functional', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('76', 'Requirement', 'Technical', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('77', 'Requirement', 'Security', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('78', 'Requirement', 'Regulatory', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('79', 'TestCase', 'Requirement test', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('80', 'TestCase', 'Non regression', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('81', 'TestCase', 'Unit test', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('82', 'TestSession', 'Evolution test session', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('83', 'TestSession', 'Development test session', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('84', 'TestSession', 'Non regression test session', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('85', 'TestSession', 'Unitary case test session', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('86', 'Opportunity', 'Contractual', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('87', 'Opportunity', 'Operational', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('88', 'Opportunity', 'Technical', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('89', 'Command', 'Fixe Price', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('90', 'Command', 'Per day', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('91', 'Quotation', 'Fixe Price', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('92', 'Quotation', 'Per day', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('93', 'Quotation', 'Per month', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('94', 'Quotation', 'Per year', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('95', 'Command', 'Per month', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('96', 'Command', 'Per year', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('97', 'Client', 'business prospect', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('98', 'Client', 'customer', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('99', 'Product', 'software', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('100', 'Product', 'service', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('101', 'Component', 'specific', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('102', 'Component', 'on the shelf', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('103', 'Provider', 'wholesaler', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('104', 'Provider', 'retailer', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('105', 'Provider', 'service provider', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('106', 'Provider', 'subcontractor', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('107', 'Provider', 'central purchasing', '50', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('108', 'CallForTender', 'by mutual agreement', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('109', 'CallForTender', 'adapted procedure', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('110', 'CallForTender', 'open call for tender', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('111', 'CallForTender', 'restricted call for tender', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('112', 'Tender', 'by mutual agreement', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('113', 'Tender', 'adapted procedure', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('114', 'Tender', 'open call for tender', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('115', 'Tender', 'restricted call for tender', '40', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('116', 'Organization', 'Department', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('117', 'Organization', 'Unit', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('118', 'Organization', 'Location', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('119', 'ProductVersion', 'Major Version', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('120', 'ProductVersion', 'Minor Version', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('121', 'ProductVersion', 'Patch', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('122', 'ComponentVersion', 'Major Version', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('123', 'ComponentVersion', 'Minor Version', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('124', 'ComponentVersion', 'Patch', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('125', 'Deliverable', 'document', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('126', 'Deliverable', 'software', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('127', 'Deliverable', 'hardware', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('128', 'Incoming', 'document', '10', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('129', 'Incoming', 'software', '20', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('130', 'Incoming', 'hardware', '30', null, '1', null, null, null, 1);
INSERT INTO types VALUES ('131', 'Client', 'System client', '10', null, '1', null, null, null, 1);
ALTER SEQUENCE types_id_seq START WITH 132;
ALTER SEQUENCE types_id_seq RESTART;

INSERT INTO statuses VALUES ('1', 'recorded', '', '#ffa500', '100', 1);
INSERT INTO statuses VALUES ('2', 'qualified', '', '#87ceeb', '200', 1);
INSERT INTO statuses VALUES ('3', 'in progress', '', '#d2691e', '300', 1);
INSERT INTO statuses VALUES ('4', 'done', '', '#afeeee', '400', 1);
INSERT INTO statuses VALUES ('5', 'verified', '', '#32cd32', '500', 1);
INSERT INTO statuses VALUES ('6', 'delivered',  '', '#4169e1', '600', 1);
INSERT INTO statuses VALUES ('7', 'closed', '', '#c0c0c0', '700', 1);
INSERT INTO statuses VALUES ('8', 're-opened',  '', '#ff0000', '250', 1);
INSERT INTO statuses VALUES ('9', 'cancelled', '', '#c0c0c0', '999', 1);
INSERT INTO statuses VALUES ('10', 'assigned', '', '#8b4513', '275', 1);
INSERT INTO statuses VALUES ('11', 'accepted', '', '#a52a2a', '220', 1);
INSERT INTO statuses VALUES ('12', 'validated', '', '#98fb98', '650', 1);
INSERT INTO statuses VALUES ('13', 'prepared', '', '#d2691e', '290', 1);
INSERT INTO statuses VALUES ('14', 'copied', '', '#ffffff', '999', 1);

INSERT INTO statuses VALUES ('15', 'backlog', 'Task', '#d2691e', '300', 1);
INSERT INTO statuses VALUES ('16', 'to do', 'Task', '#d2691e', '290', 1);
INSERT INTO statuses VALUES ('17', 'in progress', 'Task', '#d2691e', '300', 1);
INSERT INTO statuses VALUES ('18', 'done', 'Task', '#afeeee', '400', 1);
INSERT INTO statuses VALUES ('19', 'block', 'Task', '#c0c0c0', '700', 1);
INSERT INTO statuses VALUES ('20', 're-opened', 'Task', '#ff0000', '250', 1);

INSERT INTO statuses VALUES ('21', 'recorded', 'Expense', '#ffa500', '100', 1);
INSERT INTO statuses VALUES ('22', 'in progress', 'Expense', '#d2691e', '300', 1);
INSERT INTO statuses VALUES ('23', 'done', 'Expense', '#afeeee', '400', 1);
INSERT INTO statuses VALUES ('24', 'closed', 'Expense', '#c0c0c0', '700', 1);
INSERT INTO statuses VALUES ('25', 'cancelled', 'Expense', '#c0c0c0', '999', 1);

INSERT INTO statuses VALUES ('26', 'backlog', 'Document', '#d2691e', '300', 1);
INSERT INTO statuses VALUES ('27', 'to do', 'Document', '#d2691e', '290', 1);
INSERT INTO statuses VALUES ('28', 'in progress', 'Document', '#d2691e', '300', 1);
INSERT INTO statuses VALUES ('29', 'done', 'Document', '#afeeee', '400', 1);
INSERT INTO statuses VALUES ('30', 'block', 'Document', '#c0c0c0', '700', 1);
INSERT INTO statuses VALUES ('31', 're-opened', 'Document', '#ff0000', '250', 1);

INSERT INTO statuses VALUES ('32', 'backlog', 'Risk', '#d2691e', '300', 1);
INSERT INTO statuses VALUES ('33', 'to do', 'Risk', '#d2691e', '290', 1);
INSERT INTO statuses VALUES ('34', 'in progress', 'Risk', '#d2691e', '300', 1);
INSERT INTO statuses VALUES ('35', 'done', 'Risk', '#afeeee', '400', 1);
INSERT INTO statuses VALUES ('36', 'block', 'Risk', '#c0c0c0', '700', 1);
INSERT INTO statuses VALUES ('37', 're-opened', 'Risk', '#ff0000', '250', 1);

INSERT INTO statuses VALUES ('38', 'backlog', 'Project', '#d2691e', '300', 1);
INSERT INTO statuses VALUES ('39', 'to do', 'Project', '#d2691e', '290', 1);
INSERT INTO statuses VALUES ('40', 'in progress', 'Project', '#d2691e', '300', 1);
INSERT INTO statuses VALUES ('41', 'done', 'Project', '#afeeee', '400', 1);
INSERT INTO statuses VALUES ('42', 'block', 'Project', '#c0c0c0', '700', 1);
INSERT INTO statuses VALUES ('43', 're-opened', 'Project', '#ff0000', '250', 1);


ALTER SEQUENCE statuses_id_seq START WITH 21;
ALTER SEQUENCE statuses_id_seq RESTART;

INSERT INTO resources(code, title, id_card, name, full_name, email, description, phone, mobile,fax, address, street, zip, city, state, country, salary, is_resource, is_user, is_contact, id_client, client_id)
    VALUES ('NV001', 'Worker', 334722596, 'Worker', null, null, null, null, null, null, null, null, null, null, null, null, 20000000, 1::bit, 0::bit, 0::bit, null, 1);
INSERT INTO resources(code,  title, id_card, name, full_name, email, description, phone, mobile,fax, address, street, zip, city, state, country, salary, is_resource, is_user, is_contact, id_client, client_id)
    VALUES ('NV002', 'Ky su', 334569687, 'Hai Nam', 'Nguyen Hai Nam', 'caohongvu@gmail.com', 'This is description', '0834100400', '0909999999', null, 'Quan 8', 'Ta Quang Buu', null, 'Ho Chi Minh', null, 'Viet  Nam', 20000000, 1::bit, 0::bit, 0::bit, null, 1);
INSERT INTO resources(code, title, id_card,  name, full_name, email, description, phone, mobile,fax, address, street, zip, city, state, country, salary, is_resource, is_user, is_contact, id_client, client_id)
    VALUES ('NV003', 'Ky su', 334569677, 'Tan Phat', 'Tran Tan Phat', 'mytientran244@gmail.com', 'This is description', '0834100401', '0909999997', null, 'Quan 6', 'Kinh Duong Vuong', null, 'Ho Chi Minh', null, 'Viet Nam', 11000000, 1::bit, 0::bit, 0::bit, null, 1);
INSERT INTO resources(code, title, id_card,name, full_name, email, description, phone, mobile,fax, address, street, zip, city, state, country, salary, is_resource, is_user, is_contact, id_client, client_id)
    VALUES ('NV004',  'Chuyen vien ky thuat', 335626231, 'Thanh Nhan', 'Le Thanh Nhan', 'tmtien244@gmail.com', 'This is description', '0834100402', '0909999998', null, 'Quan 10', 'ThÃ nh ThÃ¡i', null, 'Ho Chi Minh', null, 'Viá»‡t Nam', 12000000, 1::bit, 0::bit, 0::bit, null, 1);
INSERT INTO resources(code, title, id_card, name, full_name, email, description, phone, mobile,fax, address, street, zip, city, state, country, salary, is_resource, is_user, is_contact, id_client, client_id)
    VALUES ('NV005', 'Chuyen vien ky thuat', 335626237, 'Huy Tai', 'Tran Huy Tai', 'user1@gmail.com', 'This is description', '0834100402', '0909999998', null, 'Quan 10', 'Dong Nai', null, 'Ho Chi Minh', null, 'Viet Nam', 12000000, 0::bit, 1::bit, 0::bit, null, 1);
INSERT INTO resources(code, title, id_card, name, full_name, email, description, phone, mobile,fax, address, street, zip, city, state, country, salary, is_resource, is_user, is_contact, id_client, client_id)
    VALUES ('NV007', 'ChuyÃªn viÃªn ká»¹ thuáº­t', 335626234, 'Táº¥n Sang', 'Nguyá»…n Táº¥n Sang', 'contact1@gmail.com', 'This is description', '0834100402', '0909999998', null, 'Quan 5', 'Nguyen Tri Phuong', null, 'Ho Chi Minh', null, 'Viet Nam', 12000000, 0::bit, 0::bit, 1::bit, 1, 1);
INSERT INTO resources(code, title, id_card, name, full_name, email, description, phone, mobile,fax, address, street, zip, city, state, country, salary, is_resource, is_user, is_contact, id_client, client_id)
    VALUES ('NV008', 'ChuyÃªn viÃªn ká»¹ thuáº­t', 335626239, 'HÃ¹ng Máº¡nh', 'LÃ½ HÃ¹ng Máº¡nh', 'contact1@gmail.com', 'This is description', '0834100402', '0909999998', null, 'Quan 5', 'Nguyen Chi Thanh', null, 'Ho Chi Minh', null, 'Viet Nam', 12000000, 0::bit, 0::bit, 1::bit, 1, 1);
INSERT INTO resources(code, title, id_card, name, full_name, email, description, phone, mobile,fax, address, street, zip, city, state, country, salary, is_resource, is_user, is_contact, id_client, client_id)
    VALUES ('NV009', 'Chuyen vien ky thuat', 335626230, 'Thinh Vuong', 'Doan Thinh Vuong', 'contact1@gmail.com', 'This is description', '0834100402', '0909999998', null, 'Quan 8', 'Hung Phu', null, 'Ho Chi Minh', null, 'Viet Nam', 12000000, 0::bit, 0::bit, 1::bit, 1, 1);    
ALTER SEQUENCE resources_id_seq START WITH 10;
ALTER SEQUENCE resources_id_seq RESTART;

INSERT INTO roles VALUES ('1', 'Level 1', 'Leader/Manager of the project', '10', '0', null, 1);
INSERT INTO roles VALUES ('2', 'Level 2', 'Responsible of specifications', '20', '0', null, 1);
INSERT INTO roles VALUES ('3', 'Level 3', 'Software developer', '30', '0', null, 1);
INSERT INTO roles VALUES ('4', 'Level 4', 'Technical expert', '40', '0', null, 1);
INSERT INTO roles VALUES ('5', 'Level 5', 'Non human resource', '50', '0', null, 1);
ALTER SEQUENCE roles_id_seq START WITH 6;
ALTER SEQUENCE roles_id_seq RESTART;

--test product_projects
INSERT INTO product_projects(
            id, id_project, id_product, start_date, end_date, idle, client_id)
    VALUES (1, 1, 1, null, null, 1::bit, 1);
INSERT INTO product_projects(
            id, id_project, id_product, start_date, end_date, idle, client_id)
    VALUES (2, 2, 1, null, null, 1::bit, 1);
INSERT INTO product_projects(
            id, id_project, id_product, start_date, end_date, idle, client_id)
    VALUES (3, 3, 1, null, null, 1::bit, 1);   
ALTER SEQUENCE product_projects_id_seq START WITH 4;
ALTER SEQUENCE product_projects_id_seq RESTART;

INSERT INTO payment_delays VALUES ('1', '15 days', '15', '0', '10', '0', 1);
INSERT INTO payment_delays VALUES ('2', '15 days end of month', '15', '1', '20', '0', 1);
INSERT INTO payment_delays VALUES ('3', '30 days', '30', '0', '30', '0', 1);
INSERT INTO payment_delays VALUES ('4', '30 days end of month', '30', '1', '40', '0', 1);
INSERT INTO payment_delays VALUES ('5', '45 days', '45', '0', '50', '0', 1);
INSERT INTO payment_delays VALUES ('6', '45 days end of month', '45', '1', '60', '0', 1);
INSERT INTO payment_delays VALUES ('7', '60 days', '60', '0', '70', '0', 1);
INSERT INTO payment_delays VALUES ('8', 'on order', '0', '0', '80', '0', 1);
ALTER SEQUENCE payment_delays_id_seq START WITH 9;
ALTER SEQUENCE payment_delays_id_seq RESTART;

	
INSERT INTO severities VALUES ('1', 'Low', '1', '#32cd32', '10', '0', 1);
INSERT INTO severities VALUES ('2', 'Medium', '2', '#ffd700', '20', '0', 1);
INSERT INTO severities VALUES ('3', 'High', '4', '#ff0000', '30', '0', 1);
ALTER SEQUENCE severities_id_seq START WITH 4;
ALTER SEQUENCE severities_id_seq RESTART;

INSERT INTO likelihoods VALUES ('1', 'Low (10%)', '1', '#32cd32', '10', '0', '10', 1);
INSERT INTO likelihoods VALUES ('2', 'Medium (50%)', '2', '#ffd700', '20', '0', '50', 1);
INSERT INTO likelihoods VALUES ('3', 'High (90%)', '4', '#ff0000', '30', '0', '90', 1);
ALTER SEQUENCE likelihoods_id_seq START WITH 4;
ALTER SEQUENCE likelihoods_id_seq RESTART;

INSERT INTO criticalities VALUES ('1', 'Low', '1', '#32cd32', '10', '0', 1);
INSERT INTO criticalities VALUES ('2', 'Medium', '2', '#ffd700', '20', '0', 1);
INSERT INTO criticalities VALUES ('3', 'High', '4', '#ff0000', '30', '0', 1);
INSERT INTO criticalities VALUES ('4', 'Critical', '8', '#000000', '40', '0', 1);
ALTER SEQUENCE criticalities_id_seq START WITH 5;
ALTER SEQUENCE criticalities_id_seq RESTART;


INSERT INTO priorities VALUES ('1', 'Low priority', '1', '#32cd32', '40', '0', 1);
INSERT INTO priorities VALUES ('2', 'Medium priority', '2', '#ffd700', '30', '0', 1);
INSERT INTO priorities VALUES ('3', 'High priority', '4', '#ff0000', '20', '0', 1);
INSERT INTO priorities VALUES ('4', 'Critical priority', '8', '#000000', '10', '0', 1);
ALTER SEQUENCE priorities_id_seq START WITH 5;
ALTER SEQUENCE priorities_id_seq RESTART;



INSERT INTO payment_modes VALUES ('1', 'bank transfer', '10', '0');
INSERT INTO payment_modes VALUES ('2', 'cheque', '20', '0');
INSERT INTO payment_modes VALUES ('3', 'credit card', '30', '0');
INSERT INTO payment_modes VALUES ('4', 'virtual payment terminal', '40', '0');
INSERT INTO payment_modes VALUES ('5', 'paypal', '50', '0');
ALTER SEQUENCE payment_modes_id_seq START WITH 6;
ALTER SEQUENCE payment_modes_id_seq RESTART;



--test documents
INSERT INTO documents(id,
            name, project_id, task_id, type_id, status_id, author_name, 
            file_name, description, create_date_time, client_id)
    VALUES (0, 'social file', 1, 1, 54, 26, 'admin', 
            '', '', '2017-04-27', 1);
INSERT INTO documents(
            name, project_id, task_id, type_id, status_id, author_name, 
            file_name, description, create_date_time, client_id)
    VALUES ('Báº£n váº½ máº¡ng lÆ°á»›i Ä‘iá»‡n', 1, 1, 54, 26, 'admin', 
            'tailieuvekithuat.pdf', 'Báº£n váº½ máº¡ng lÆ°á»›i Ä‘iá»‡n', '2017-04-27', 1);
INSERT INTO documents(
            name, project_id, task_id, type_id, status_id, author_name, 
            file_name, description, create_date_time, client_id)
    VALUES ('Há»‡ thá»‘ng Ä‘Æ°á»�ng á»‘ng nÆ°á»›c', 1, 2, 54, 26, 'admin', 
            'tailieuongnuoc.pdf', 'Há»‡ thá»‘ng Ä‘Æ°á»�ng á»‘ng nÆ°á»›c', '2017-04-27', 1);
INSERT INTO documents(
            name, project_id, task_id, type_id, status_id, author_name, 
            file_name, description, create_date_time, client_id)
    VALUES ('Trang trÃ­ ná»™i tháº¥t', 1, 3, 54, 26, 'admin', 
            'noithat.pdf', 'Trang trÃ­ ná»™i tháº¥t', '2017-04-27', 1);            

--test document-versions
INSERT INTO document_versions(
            name, project_id, version, link, file_size,
            create_date_time, update_date_time, document_id, author_name, 
            status_id, description, client_id)
    VALUES ('Báº£n váº½ máº¡ng lÆ°á»›i Ä‘iá»‡n cho táº§ng trá»‡t', 1, 1, '/static/document/tailieuchomachdientret.pdf', 1000, 
            '2017-04-27', '2017-04-27', 1, 'admin', 
            27, 'Báº£n váº½ máº¡ng lÆ°á»›i Ä‘iá»‡n táº§ng trá»‡t', 1);
INSERT INTO document_versions(
            name, project_id, version, link, file_size,
            create_date_time, update_date_time, document_id, author_name, 
            status_id, description, client_id)
    VALUES ('Báº£n váº½ máº¡ng lÆ°á»›i Ä‘iá»‡n cho táº§ng trá»‡t', 1, 2, '/static/document/tailieuchomachdientang1.pdf', 1000, 
            '2017-04-27', '2017-04-27', 1, 'admin', 
            27, 'Báº£n váº½ máº¡ng lÆ°á»›i Ä‘iá»‡n táº§ng 1', 1);            
INSERT INTO document_versions(
            name, project_id, version, link, file_size,
            create_date_time, update_date_time, document_id, author_name, 
            status_id, description, client_id)
    VALUES ('Há»‡ thá»‘ng Ä‘Æ°á»�ng á»‘ng nÆ°á»›c táº§ng trá»‡t', 1, 1, '/static/document/tailieuongnuoc.pdf', 1000, 
            '2017-04-27', '2017-04-27', 2, 'admin', 
            27, 'Há»‡ thá»‘ng Ä‘Æ°á»�ng á»‘ng nÆ°á»›c táº§ng 1', 1);

--test risks
INSERT INTO risks(
            project_id, name, description, type_id, cause, impact, severity_id, 
            likelihood_id, criticality_id, creation_date, status_id, planning_end_date, 
            actual_end_date, result, done_date, handled_date, priority_id, 
            impact_cost, update_date, update_by, client_id)
    VALUES (1, 'Trá»�i mÆ°a', 'MÆ°a Ä‘áº§u', 1, 'Dá»± bÃ¡o thá»�i tiáº¿t', 'áº¢nh hÆ°á»Ÿng Ä‘áº¿n cÃ´ng trÃ¬nh Ä‘ang xÃ¢y', 1, 
            1, 1, '2016-04-27', 32, '2016-04-27', 
            '2016-04-27', 'KhÃ´ng xáº£y ra', '2016-04-27', '2016-04-27', 1, 
            10000, '2016-04-27', 'admin', 1);
INSERT INTO risks(
             project_id, name, description, type_id, cause, impact, severity_id, 
            likelihood_id, criticality_id, creation_date, status_id, planning_end_date, 
            actual_end_date, result, done_date, handled_date, priority_id, 
            impact_cost, update_date, update_by, client_id)
    VALUES (2, 'Thiáº¿u cÃ´ng nhÃ¢n', 'Nghá»‰ Ä‘á»™t xuáº¥t', 1, 'Nghá»‰ Ä‘á»™t xuáº¥t', 'Trá»… háº¹n giao', 1, 
            1, 1, '2016-04-27', 32, '2016-04-27', 
            '2016-04-27', 'KhÃ´ng xáº£y ra', '2016-04-27', '2016-04-27', 1, 
            10000, '2016-04-27', 'admin', 1);



--test equipments
INSERT INTO equipments(id, code, name, unit, quantity, net_price, price, description, client_id) VALUES (1, 'T001', 'Xe cuốc', 2, 2, 80000, 100000, null, 1);
INSERT INTO equipments(id, code, name, unit, quantity, net_price, price, description, client_id) VALUES (2, 'T002', 'Máy xúc', 3, 4, 80000, 100000, null, 1);
INSERT INTO equipments(id, code, name, unit, quantity, net_price, price, description, client_id) VALUES (3, 'T003', 'Búa', 1, 8, 17000, 20000, null, 1);
INSERT INTO equipments(id, code, name, unit, quantity, net_price, price, description, client_id) VALUES (4, 'T004', 'Máy đổ bê tông', 4, 8, 80000, 100000, null, 1);
INSERT INTO equipments(id, code, name, unit, quantity, net_price, price, description, client_id) VALUES (5, 'T005', 'Máy nâng', 5, 8, 100000, 150000, null, 1);
INSERT INTO equipments(id, code, name, unit, quantity, net_price, price, description, client_id) VALUES (6, 'T006', 'Lu tĩnh', 6, 8, 50000, 80000, null, 1);
INSERT INTO equipments(id, code, name, unit, quantity, net_price, price, description, client_id) VALUES (7, 'T007', 'Lu rung', 4, 8, 50000, 70000, null, 1);
INSERT INTO equipments(id, code, name, unit, quantity, net_price, price, description, client_id) VALUES (8, 'T008', 'Cốt pha', 3, 80, 40000, 60000, null, 1);
INSERT INTO equipments(id, code, name, unit, quantity, net_price, price, description, client_id) VALUES (9, 'T009', 'Máy hàn', 3, 5, 50000, 70000, null, 1);
INSERT INTO equipments(id, code, name, unit, quantity, net_price, price, description, client_id) VALUES (10, 'T010', 'Máy nén khí', 3, 4, 70000, 100000, null, 1);

ALTER SEQUENCE equipments_id_seq START WITH 11;
ALTER SEQUENCE equipments_id_seq RESTART; 

--test materials
INSERT INTO materials(id, code, name, unit, quantity, net_price, price, description, client_id) VALUES (1, 'M001', 'Xi măng FiCo', 1, 40.0, 200000, 220000, null, 1);
INSERT INTO materials(id, code, name, unit, quantity, net_price, price, description,  client_id) VALUES (2, 'M002', 'Cát bê tông', 2, 500.0, 80000, 100000, null, 1);
INSERT INTO materials(id, code, name, unit, quantity, net_price, price, description,  client_id) VALUES (3, 'M003', 'Đá 5x7', 1, 7000.0, 10000, 150000, null, 1);
INSERT INTO materials(id, code, name, unit, quantity, net_price, price, description,  client_id) VALUES (4, 'M004', 'Đá hộc', 3, 1000.0, 100000, 130000, null, 1);
INSERT INTO materials(id, code, name, unit, quantity, net_price, price, description,  client_id) VALUES (5, 'M005', 'Gạch lớn', 2, 10000.0, 7000, 12000, null, 1);
INSERT INTO materials(id, code, name, unit, quantity, net_price, price, description,  client_id) VALUES (6, 'M006', 'Gạch 10x7', 3, 7000.0, 150000, 2000, null, 1);
INSERT INTO materials(id, code, name, unit, quantity, net_price, price, description,  client_id) VALUES (7, 'M007', 'Thép Pomina', 3, 50000.0, 100000, 120000, null, 1);
INSERT INTO materials(id, code, name, unit, quantity, net_price, price, description,  client_id) VALUES (8, 'M008', 'Thép Việt Nhật', 1, 7000.0, 150000, 180000, null, 1);
INSERT INTO materials(id, code, name, unit, quantity, net_price, price, description,  client_id) VALUES (9, 'M009', 'Cát san lấp', 1, 4000.0, 100000, 110000, null, 1);
INSERT INTO materials(id, code, name, unit, quantity, net_price, price, description,  client_id) VALUES (10, 'M010', 'Gạch lát', 1, 17000.0, 7000, 11000, null, 1);
ALTER SEQUENCE materials_id_seq START WITH 11;
ALTER SEQUENCE materials_id_seq RESTART; 
--test project_equipments
INSERT INTO project_equipments(id, project_id, equipment_id, net_price, price, client_id) VALUES (1, 1, 1, 80000, 100000, 1);
INSERT INTO project_equipments(id, project_id, equipment_id, net_price, price, client_id) VALUES (2, 1, 2, 80000, 100000, 1);
INSERT INTO project_equipments(id, project_id, equipment_id, net_price, price, client_id) VALUES (3, 1, 3, 18000, 20000, 1);
INSERT INTO project_equipments(id, project_id, equipment_id, net_price, price, client_id) VALUES (4, 1, 4, 70000, 100000, 1);
INSERT INTO project_equipments(id, project_id, equipment_id, net_price, price, client_id) VALUES (5, 1, 5, 100000, 150000, 1);
INSERT INTO project_equipments(id, project_id, equipment_id, net_price, price, client_id) VALUES (6, 1, 6, 50000, 80000, 1);
ALTER SEQUENCE project_equipments_id_seq START WITH 7;
ALTER SEQUENCE project_equipments_id_seq RESTART; 

--test project_materials
INSERT INTO project_materials(id, project_id, material_id, net_price, price, client_id) VALUES (1, 1, 1, 200000, 220000, 1);
INSERT INTO project_materials(id, project_id, material_id, net_price, price, client_id) VALUES (2, 1, 2, 80000, 100000, 1);
INSERT INTO project_materials(id, project_id, material_id, net_price, price, client_id) VALUES (3, 1, 3, 100000, 150000, 1);
INSERT INTO project_materials(id, project_id, material_id, net_price, price, client_id) VALUES (4, 1, 4, 100000, 130000, 1);
INSERT INTO project_materials(id, project_id, material_id, net_price, price, client_id) VALUES (5, 1, 5, 7000, 12000, 1);
INSERT INTO project_materials(id, project_id, material_id, net_price, price, client_id) VALUES (6, 1, 6, 110000, 150000, 1);

ALTER SEQUENCE project_materials_id_seq START WITH 7;
ALTER SEQUENCE project_materials_id_seq RESTART; 

--test task_equipments
INSERT INTO task_equipments(id, project_id, task_id, equipment_id, quantity, client_id) VALUES (1, 1, 1, 1, 2,  1);
INSERT INTO task_equipments(id, project_id, task_id, equipment_id, quantity, client_id) VALUES (2, 1, 1, 2, 4,  1);
INSERT INTO task_equipments(id, project_id, task_id, equipment_id, quantity, client_id) VALUES (3, 1, 1, 3, 7, 1);
ALTER SEQUENCE task_equipments_id_seq START WITH 4;
ALTER SEQUENCE task_equipments_id_seq RESTART; 

--test task_materials
INSERT INTO task_materials(id, project_id, task_id, material_id, estimate_quantity, client_id) VALUES (1, 1, 1, 1, 10.0, 1);
INSERT INTO task_materials(id, project_id, task_id, material_id, estimate_quantity, client_id) VALUES (2, 1, 1, 2, 50.0, 1);
INSERT INTO task_materials(id, project_id, task_id, material_id, estimate_quantity, client_id) VALUES (3, 1, 1, 3, 100.0, 1);
ALTER SEQUENCE task_materials_id_seq START WITH 4;
ALTER SEQUENCE task_materials_id_seq RESTART; 

--test task_resources
INSERT INTO task_resources(task_id, project_id, resource_id, client_id) VALUES (1, 1, 2, 1);
INSERT INTO task_resources(task_id, project_id, resource_id, client_id) VALUES (2, 1, 3, 1);
INSERT INTO task_resources(task_id, project_id, resource_id, client_id) VALUES (2, 1, 2, 1);
INSERT INTO task_resources(task_id, project_id, resource_id, client_id) VALUES (3, 1, 3, 1);
INSERT INTO task_resources(task_id, project_id, resource_id, client_id) VALUES (3, 1, 4, 1);

--test task_resource_daily_tracking
INSERT INTO task_resource_daily_tracking(task_id, project_id, actual_time, quantity, date_log, owner, resource_id, salary) VALUES (2, 1, 0, 1, '2017-03-31', 'admin', '10',10000000.00);
INSERT INTO task_resource_daily_tracking(task_id, project_id, actual_time, quantity, date_log, owner, resource_id, salary) VALUES (2, 1, 0, 2, '2017-03-31', 'admin', '11',100000.00);
INSERT INTO task_resource_daily_tracking(task_id, project_id, actual_time, quantity, date_log, owner, resource_id, salary) VALUES (2, 1, 0, 1, '2017-04-01', 'admin', '10',10000000.00);
INSERT INTO task_resource_daily_tracking(task_id, project_id, actual_time, quantity, date_log, owner, resource_id, salary) VALUES (2, 1, 0, 2, '2017-04-01', 'admin', '11',100000.00);

----test task_equipment_daily_tracking
INSERT INTO task_equipment_daily_tracking(task_id, project_id, actual_time, quantity, date_log, owner, equipment_id, price) VALUES (2, 1, 1, 1, '2017-03-31', 'admin', 1, 150000.00);
INSERT INTO task_equipment_daily_tracking(task_id, project_id, actual_time, quantity, date_log, owner, equipment_id, price) VALUES (2, 1, 1, 1, '2017-03-31', 'admin', 2, 80000.00);
INSERT INTO task_equipment_daily_tracking(task_id, project_id, actual_time, quantity, date_log, owner, equipment_id, price) VALUES (2, 1, 1, 1, '2017-04-01', 'admin', 1, 150000.00);
INSERT INTO task_equipment_daily_tracking(task_id, project_id, actual_time, quantity, date_log, owner, equipment_id, price) VALUES (2, 1, 1, 1, '2017-04-01', 'admin', 2, 80000.00);