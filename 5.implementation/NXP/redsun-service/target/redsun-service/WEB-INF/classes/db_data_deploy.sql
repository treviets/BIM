DROP TABLE IF EXISTS expense_detail_types;
CREATE TABLE IF NOT EXISTS expense_detail_types (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  sort_order integer DEFAULT NULL,
  value01 decimal(8,2) DEFAULT NULL,
  value02 decimal(8,2) DEFAULT NULL,
  value03 decimal(8,2) DEFAULT NULL,
  unit01 varchar(20) DEFAULT NULL,
  unit02 varchar(20) DEFAULT NULL,
  unit03 varchar(20) DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  description text,
  individual bit(1) NULL DEFAULT B'0',
  project bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);
INSERT INTO expense_detail_types VALUES ('1', 'travel by car', '10', null, '0.54', null, 'km', 'Ã¢â€šÂ¬/km', null, '0', null, '1', '0',3);
INSERT INTO expense_detail_types VALUES ('2', 'regular mission car travel', '20', null, null, '0.54', 'days', 'km/day', 'Ã¢â€šÂ¬/km', '0', null, '1', '0',3);
INSERT INTO expense_detail_types VALUES ('3', 'lunch for guests', '30', null, null, null, 'guests', 'Ã¢â€šÂ¬/guest', null, '0', null, '1', '0',3);
INSERT INTO expense_detail_types VALUES ('4', 'justified expense', '40', null, null, null, null, null, null, '0', null, '1', '0',3);
INSERT INTO expense_detail_types VALUES ('5', 'detail', '50', null, null, null, 'units', 'Ã¢â€šÂ¬ per unit', null, '0', null, '0', '1',3);
ALTER SEQUENCE expense_detail_types_id_seq START WITH 6;
ALTER SEQUENCE expense_detail_types_id_seq RESTART;


DROP TABLE IF EXISTS types;
CREATE TABLE IF NOT EXISTS types (
  id SERIAL PRIMARY KEY,
  scope varchar(100) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  sort_order integer DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  id_workflow integer DEFAULT NULL,
  description text,
  id_planning_mode integer DEFAULT NULL,
  id_category integer DEFAULT NULL,
  client_id integer DEFAULT NULL
);

INSERT INTO types VALUES ('1', 'Risk', 'Cost', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('2', 'Risk', 'Time', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('3', 'Risk', 'Resource', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('132', 'Risk', 'Stake Holder', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('4', 'Issue', 'Technical issue', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('5', 'Issue', 'Process non conformity', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('6', 'Issue', 'Quality non conformity', '40', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('7', 'Issue', 'Process non appliability', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('8', 'Issue', 'Customer complaint', '90', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('9', 'Issue', 'Delay non respect', '50', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('10', 'Issue', 'Resource management issue', '70', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('12', 'Issue', 'Financial loss', '80', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('13', 'Message', 'ALERT', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('14', 'Message', 'WARNING', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('15', 'Message', 'INFO', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('16', 'Ticket', 'Incident', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('17', 'Ticket', 'Support / Assistance', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('18', 'Ticket', 'Anomaly / Bug', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('19', 'Activity', 'Development', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('20', 'Activity', 'Evolution', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('21', 'Activity', 'Management', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('22', 'Activity', 'Phase', '40', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('23', 'Milestone', 'Deliverable', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('24', 'Milestone', 'Incoming', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('25', 'Milestone', 'Key date', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('26', 'Activity', 'Task', '1', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('27', 'Action', 'Project', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('28', 'Action', 'Internal', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('29', 'Action', 'Customer', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('30', 'Meeting', 'Steering Committee', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('31', 'Meeting', 'Progress Metting', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('32', 'Meeting', 'Team meeting', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('33', 'Decision', 'Functional', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('34', 'Decision', 'Operational', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('35', 'Decision', 'Contractual', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('36', 'Decision', 'Strategic', '40', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('37', 'Question', 'Functional', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('38', 'Question', 'Technical', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('39', 'IndividualExpense', 'Expense report', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('40', 'ProjectExpense', 'Machine expense', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('41', 'ProjectExpense', 'Office expense', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('46', 'Payment', 'partial payment', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('47', 'Payment', 'final payment', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('48', 'Project', 'Fixed Unit Price', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('49', 'Project', 'Turn Key Project', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('54', 'Document', 'Pre-Contruction', '210', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('55', 'Document', 'Contruction', '220', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('56', 'Document', 'Closing', '230', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('68', 'Versioning', 'evolutive', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('69', 'Versioning', 'chronological', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('70', 'Versioning', 'sequential', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('71', 'Versioning', 'external', '40', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('72', 'Bill', 'Partial bill', '100', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('73', 'Bill', 'Final bill', '200', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('74', 'Bill', 'Complete bill', '300', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('75', 'Requirement', 'Functional', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('76', 'Requirement', 'Technical', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('77', 'Requirement', 'Security', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('78', 'Requirement', 'Regulatory', '40', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('79', 'TestCase', 'Requirement test', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('80', 'TestCase', 'Non regression', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('81', 'TestCase', 'Unit test', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('82', 'TestSession', 'Evolution test session', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('83', 'TestSession', 'Development test session', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('84', 'TestSession', 'Non regression test session', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('85', 'TestSession', 'Unitary case test session', '40', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('86', 'Opportunity', 'Contractual', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('87', 'Opportunity', 'Operational', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('88', 'Opportunity', 'Technical', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('89', 'Command', 'Fixe Price', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('90', 'Command', 'Per day', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('91', 'Quotation', 'Fixe Unit Price', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('92', 'Quotation', 'Turn Key Project', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('95', 'Command', 'Per month', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('96', 'Command', 'Per year', '40', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('97', 'Client', 'business prospect', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('98', 'Client', 'customer', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('99', 'Product', 'software', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('100', 'Product', 'service', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('101', 'Component', 'specific', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('102', 'Component', 'on the shelf', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('103', 'Provider', 'wholesaler', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('104', 'Provider', 'retailer', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('105', 'Provider', 'service provider', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('106', 'Provider', 'subcontractor', '40', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('107', 'Provider', 'central purchasing', '50', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('108', 'CallForTender', 'by mutual agreement', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('109', 'CallForTender', 'adapted procedure', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('110', 'CallForTender', 'open call for tender', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('111', 'CallForTender', 'restricted call for tender', '40', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('112', 'Tender', 'Fixed Unit Price', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('113', 'Tender', 'Turn Key Project', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('116', 'Organization', 'Department', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('117', 'Organization', 'Unit', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('118', 'Organization', 'Location', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('119', 'ProductVersion', 'Major Version', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('120', 'ProductVersion', 'Minor Version', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('121', 'ProductVersion', 'Patch', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('122', 'ComponentVersion', 'Major Version', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('123', 'ComponentVersion', 'Minor Version', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('124', 'ComponentVersion', 'Patch', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('125', 'Deliverable', 'document', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('126', 'Deliverable', 'software', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('127', 'Deliverable', 'hardware', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('128', 'Incoming', 'document', '10', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('129', 'Incoming', 'software', '20', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('130', 'Incoming', 'hardware', '30', null, '1', null, null, null, 3);
INSERT INTO types VALUES ('131', 'Client', 'System client', '10', null, '1', null, null, null, 3);
ALTER SEQUENCE types_id_seq START WITH 132;
ALTER SEQUENCE types_id_seq RESTART;


DROP TABLE IF EXISTS statuses;
CREATE TABLE IF NOT EXISTS statuses (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  scope varchar(20),
  color varchar(7) DEFAULT NULL,
  sort_order integer DEFAULT NULL,
  client_id integer DEFAULT NULL
);
INSERT INTO statuses VALUES ('1', 'recorded', '', '#ffa500', '100', 3);
INSERT INTO statuses VALUES ('2', 'qualified', '', '#87ceeb', '200', 3);
INSERT INTO statuses VALUES ('3', 'in progress', '', '#d2691e', '300', 3);
INSERT INTO statuses VALUES ('4', 'done', '', '#afeeee', '400', 3);
INSERT INTO statuses VALUES ('5', 'verified', '', '#32cd32', '500', 3);
INSERT INTO statuses VALUES ('6', 'delivered',  '', '#4169e1', '600', 3);
INSERT INTO statuses VALUES ('7', 'closed', '', '#c0c0c0', '700', 3);
INSERT INTO statuses VALUES ('8', 're-opened',  '', '#ff0000', '250', 3);
INSERT INTO statuses VALUES ('9', 'cancelled', '', '#c0c0c0', '999', 3);
INSERT INTO statuses VALUES ('10', 'assigned', '', '#8b4513', '275', 3);
INSERT INTO statuses VALUES ('11', 'accepted', '', '#a52a2a', '220', 3);
INSERT INTO statuses VALUES ('12', 'validated', '', '#98fb98', '650', 3);
INSERT INTO statuses VALUES ('13', 'prepared', '', '#d2691e', '290', 3);
INSERT INTO statuses VALUES ('14', 'copied', '', '#ffffff', '999', 3);

INSERT INTO statuses VALUES ('15', 'backlog', 'Task', '#d2691e', '300', 3);
INSERT INTO statuses VALUES ('16', 'to do', 'Task', '#d2691e', '290', 3);
INSERT INTO statuses VALUES ('17', 'in progress', 'Task', '#d2691e', '300', 3);
INSERT INTO statuses VALUES ('18', 'done', 'Task', '#afeeee', '400', 3);
INSERT INTO statuses VALUES ('19', 'block', 'Task', '#c0c0c0', '700', 3);
INSERT INTO statuses VALUES ('20', 're-opened', 'Task', '#ff0000', '250', 3);

INSERT INTO statuses VALUES ('21', 'backlog', 'Expense', '#ffa500', '100', 3);
INSERT INTO statuses VALUES ('22', 'to do', 'Expense', '#d2691e', '300', 3);
INSERT INTO statuses VALUES ('23', 'in progress', 'Expense', '#afeeee', '400', 3);
INSERT INTO statuses VALUES ('24', 'done', 'Expense', '#c0c0c0', '700', 3);
INSERT INTO statuses VALUES ('25', 'block', 'Expense', '#c0c0c0', '999', 3);
INSERT INTO statuses VALUES ('44', 're-opened', 'Expense', '#c0c0c0', '999', 3);

INSERT INTO statuses VALUES ('26', 'backlog', 'Document', '#d2691e', '300', 3);
INSERT INTO statuses VALUES ('27', 'to do', 'Document', '#d2691e', '290', 3);
INSERT INTO statuses VALUES ('28', 'in progress', 'Document', '#d2691e', '300', 3);
INSERT INTO statuses VALUES ('29', 'done', 'Document', '#afeeee', '400', 3);
INSERT INTO statuses VALUES ('30', 'block', 'Document', '#c0c0c0', '700', 3);
INSERT INTO statuses VALUES ('31', 're-opened', 'Document', '#ff0000', '250', 3);

INSERT INTO statuses VALUES ('32', 'backlog', 'Risk', '#d2691e', '300', 3);
INSERT INTO statuses VALUES ('33', 'to do', 'Risk', '#d2691e', '290', 3);
INSERT INTO statuses VALUES ('34', 'in progress', 'Risk', '#d2691e', '300', 3);
INSERT INTO statuses VALUES ('35', 'done', 'Risk', '#afeeee', '400', 3);
INSERT INTO statuses VALUES ('36', 'block', 'Risk', '#c0c0c0', '700', 3);
INSERT INTO statuses VALUES ('37', 're-opened', 'Risk', '#ff0000', '250', 3);

INSERT INTO statuses VALUES ('38', 'backlog', 'Project', '#d2691e', '300', 3);
INSERT INTO statuses VALUES ('39', 'to do', 'Project', '#d2691e', '290', 3);
INSERT INTO statuses VALUES ('40', 'in progress', 'Project', '#d2691e', '300', 3);
INSERT INTO statuses VALUES ('41', 'done', 'Project', '#afeeee', '400', 3);
INSERT INTO statuses VALUES ('42', 'block', 'Project', '#c0c0c0', '700', 3);
INSERT INTO statuses VALUES ('43', 're-opened', 'Project', '#ff0000', '250', 3);
ALTER SEQUENCE statuses_id_seq START WITH 45;
ALTER SEQUENCE statuses_id_seq RESTART;


DROP TABLE IF EXISTS roles;
CREATE TABLE IF NOT EXISTS roles (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  description text,
  sort_order integer DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  default_cost decimal(9,2) DEFAULT NULL,
  client_id integer DEFAULT NULL
);
INSERT INTO roles VALUES ('1', 'Level 1', 'Leader/Manager of the project', '10', '0', null, 3);
INSERT INTO roles VALUES ('2', 'Level 2', 'Responsible of specifications', '20', '0', null, 3);
INSERT INTO roles VALUES ('3', 'Level 3', 'Software developer', '30', '0', null, 3);
INSERT INTO roles VALUES ('4', 'Level 4', 'Technical expert', '40', '0', null, 3);
INSERT INTO roles VALUES ('5', 'Level 5', 'Non human resource', '50', '0', null, 3);
ALTER SEQUENCE roles_id_seq START WITH 6;
ALTER SEQUENCE roles_id_seq RESTART;

DROP TABLE IF EXISTS payment_delays;
CREATE TABLE IF NOT EXISTS payment_delays (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  days integer DEFAULT NULL,
  end_of_month bit(1) NULL DEFAULT B'0',
  sort_order integer DEFAULT '0',
  idle bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);
INSERT INTO payment_delays VALUES ('1', '15 days', '15', '0', '10', '0', 3);
INSERT INTO payment_delays VALUES ('2', '15 days end of month', '15', '1', '20', '0', 3);
INSERT INTO payment_delays VALUES ('3', '30 days', '30', '0', '30', '0', 3);
INSERT INTO payment_delays VALUES ('4', '30 days end of month', '30', '1', '40', '0', 3);
INSERT INTO payment_delays VALUES ('5', '45 days', '45', '0', '50', '0', 3);
INSERT INTO payment_delays VALUES ('6', '45 days end of month', '45', '1', '60', '0', 3);
INSERT INTO payment_delays VALUES ('7', '60 days', '60', '0', '70', '0', 3);
INSERT INTO payment_delays VALUES ('8', 'on order', '0', '0', '80', '0', 3);
ALTER SEQUENCE payment_delays_id_seq START WITH 9;
ALTER SEQUENCE payment_delays_id_seq RESTART;


DROP TABLE IF EXISTS severities;
CREATE TABLE IF NOT EXISTS severities (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  value integer DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  sort_order integer DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);
INSERT INTO severities VALUES ('1', 'Low', '1', '#32cd32', '10', '0', 3);
INSERT INTO severities VALUES ('2', 'Medium', '2', '#ffd700', '20', '0', 3);
INSERT INTO severities VALUES ('3', 'High', '4', '#ff0000', '30', '0', 3);
ALTER SEQUENCE severities_id_seq START WITH 4;
ALTER SEQUENCE severities_id_seq RESTART;



DROP TABLE IF EXISTS likelihoods;
CREATE TABLE IF NOT EXISTS likelihoods (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  value integer DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  sort_order integer DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  value_pct integer DEFAULT '0',
  client_id integer DEFAULT NULL
);
INSERT INTO likelihoods VALUES ('1', 'Low (10%)', '1', '#32cd32', '10', '0', '10', 3);
INSERT INTO likelihoods VALUES ('2', 'Medium (50%)', '2', '#ffd700', '20', '0', '50', 3);
INSERT INTO likelihoods VALUES ('3', 'High (90%)', '4', '#ff0000', '30', '0', '90', 3);
ALTER SEQUENCE likelihoods_id_seq START WITH 4;
ALTER SEQUENCE likelihoods_id_seq RESTART;



DROP TABLE IF EXISTS criticalities;
CREATE TABLE IF NOT EXISTS criticalities (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  value integer DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  sort_order integer DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);
INSERT INTO criticalities VALUES ('1', 'Low', '1', '#32cd32', '10', '0', 3);
INSERT INTO criticalities VALUES ('2', 'Medium', '2', '#ffd700', '20', '0', 3);
INSERT INTO criticalities VALUES ('3', 'High', '4', '#ff0000', '30', '0', 3);
INSERT INTO criticalities VALUES ('4', 'Critical', '8', '#000000', '40', '0', 3);
ALTER SEQUENCE criticalities_id_seq START WITH 5;
ALTER SEQUENCE criticalities_id_seq RESTART;


DROP TABLE IF EXISTS priorities;
CREATE TABLE IF NOT EXISTS priorities (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  value integer DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  sort_order integer DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);
INSERT INTO priorities VALUES ('1', 'Low priority', '1', '#32cd32', '40', '0', 3);
INSERT INTO priorities VALUES ('2', 'Medium priority', '2', '#ffd700', '30', '0', 3);
INSERT INTO priorities VALUES ('3', 'High priority', '4', '#ff0000', '20', '0', 3);
INSERT INTO priorities VALUES ('4', 'Critical priority', '8', '#000000', '10', '0', 3);
ALTER SEQUENCE priorities_id_seq START WITH 5;
ALTER SEQUENCE priorities_id_seq RESTART;



DROP TABLE IF EXISTS payment_modes;
CREATE TABLE IF NOT EXISTS payment_modes (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  sort_order integer DEFAULT '0',
  idle bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);
INSERT INTO payment_modes VALUES ('1', 'bank transfer', '10', '0');
INSERT INTO payment_modes VALUES ('2', 'cheque', '20', '0');
INSERT INTO payment_modes VALUES ('3', 'credit card', '30', '0');
INSERT INTO payment_modes VALUES ('4', 'virtual payment terminal', '40', '0');
INSERT INTO payment_modes VALUES ('5', 'paypal', '50', '0');
ALTER SEQUENCE payment_modes_id_seq START WITH 6;
ALTER SEQUENCE payment_modes_id_seq RESTART;


DROP TABLE IF EXISTS units;
CREATE TABLE IF NOT EXISTS units(
	id SERIAL PRIMARY KEY,
	name varchar(60),
	devices bit(1),
	client_id integer DEFAULT NULL
);
INSERT INTO units(name, client_id) VALUES ('m', 3);
INSERT INTO units(name, client_id) VALUES ('100m', 3);
INSERT INTO units(name, client_id) VALUES ('m2', 3);
INSERT INTO units(name, client_id) VALUES ('m3', 3);
INSERT INTO units(name, client_id) VALUES ('kg', 3);
INSERT INTO units(name, client_id) VALUES ('Tấn', 3);
INSERT INTO units(name, client_id) VALUES ('Cái', 3);
INSERT INTO units(name, client_id) VALUES ('Cây', 3);
INSERT INTO units(name, client_id) VALUES ('Chiếc', 3);
INSERT INTO units(name, client_id) VALUES ('Cuộn', 3);
INSERT INTO units(name, client_id) VALUES ('Ống', 3);
INSERT INTO units(name, client_id) VALUES ('Bình', 3);
INSERT INTO units(name, client_id) VALUES ('Bộ', 3);
INSERT INTO units(name, client_id) VALUES ('Lô', 3);
INSERT INTO units(name, client_id) VALUES ('Hệ', 3);



