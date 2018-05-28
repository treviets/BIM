DROP view IF EXISTS planning_elements;
DROP view IF EXISTS resource_planning_elements;
DROP view IF EXISTS equipment_planning_elements;
DROP view IF EXISTS material_planning_elements;

DROP TABLE IF EXISTS expenses;
CREATE TABLE IF NOT EXISTS expenses (
  id SERIAL PRIMARY KEY,
  id_project integer DEFAULT NULL,
  id_resource integer DEFAULT NULL,
  id_user integer DEFAULT NULL,
  id_expense_type integer DEFAULT NULL,
  scope varchar(100) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  id_status integer DEFAULT NULL,
  description text,
  expense_planned_date date DEFAULT NULL,
  expense_real_date date DEFAULT NULL,
  planned_amount decimal(11,2) DEFAULT NULL,
  real_amount decimal(11,2) DEFAULT NULL,
  day varchar(8) DEFAULT NULL,
  week varchar(6) DEFAULT NULL,
  month varchar(6) DEFAULT NULL,
  year varchar(4) DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  reference varchar(100) DEFAULT NULL,
  external_reference varchar(100) DEFAULT NULL,
  cancelled bit(1) NULL DEFAULT B'0',
  id_document integer DEFAULT NULL,
  id_provider integer DEFAULT NULL,
  send_date date DEFAULT NULL,
  id_delivery_mode integer DEFAULT NULL,
  delivery_delay varchar(100) DEFAULT NULL,
  delivery_date date DEFAULT NULL,
  payment_condition varchar(100) DEFAULT NULL,
  reception_date date DEFAULT NULL,
  result text,
  tax_pct decimal(5,2) DEFAULT NULL,
  planned_full_amount decimal(11,2) DEFAULT '0.00',
  real_full_amount decimal(11,2) DEFAULT '0.00',
  idle_date date DEFAULT NULL,
  handled bit(1) NULL DEFAULT B'0',
  handled_date date DEFAULT NULL,
  done bit(1) NULL DEFAULT B'0',
  done_date date DEFAULT NULL,
  id_responsible integer DEFAULT NULL,
  payment_done bit(1) NULL DEFAULT B'0',
  id_contact integer DEFAULT NULL,
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS expense_details;
CREATE TABLE IF NOT EXISTS expense_details (
  id SERIAL PRIMARY KEY,
  id_project integer DEFAULT NULL,
  id_expense integer DEFAULT NULL,
  expense_date date DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  id_expense_detail_type integer DEFAULT NULL,
  value01 decimal(8,2) DEFAULT NULL,
  value02 decimal(8,2) DEFAULT NULL,
  value03 decimal(8,2) DEFAULT NULL,
  unit01 varchar(20) DEFAULT NULL,
  unit02 varchar(20) DEFAULT NULL,
  unit03 varchar(20) DEFAULT NULL,
  description varchar(4000) DEFAULT NULL,
  amount decimal(11,2) DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  external_reference varchar(100) DEFAULT NULL,
  client_id integer DEFAULT NULL
);

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


DROP TABLE IF EXISTS project_resources;
CREATE TABLE IF NOT EXISTS project_resources (
  id SERIAL PRIMARY KEY,
  resource_id integer DEFAULT NULL,
  salary decimal(12,2),
  quantity integer DEFAULT 1,
  project_id integer DEFAULT NULL,
  client_id integer DEFAULT NULL
);
DROP TABLE IF EXISTS projects;
CREATE TABLE IF NOT EXISTS projects (
  id SERIAL PRIMARY KEY,
  name varchar(100) NOT NULL,
  location varchar(120),
  description text DEFAULT NULL,
  customer_id integer DEFAULT NULL,
  code varchar(20) NOT NULL,
  parent_id integer DEFAULT NULL,
  start_date date DEFAULT NULL,
  end_date date DEFAULT NULL,
  duration integer,
  work_sunday bit(1) DEFAULT B'0',
  closed_date date DEFAULT NULL,
  done_date date DEFAULT NULL,
  type_id integer DEFAULT NULL,
  how_many_day_payment_delay varchar(25),
  status_id integer DEFAULT NULL,
  creation_date date DEFAULT NULL,
  last_update_date_time date DEFAULT NULL,
  client_id integer DEFAULT NULL,
  update_by varchar(50) DEFAULT NULL,
  amount decimal,
  project_type integer DEFAULT 1
);


DROP TABLE IF EXISTS equipments;
CREATE TABLE IF NOT EXISTS equipments (
  id SERIAL PRIMARY KEY,
  code varchar(20) NOT NULL UNIQUE,
  name varchar(100) NOT NULL,
  unit integer,
  quantity integer,
  net_price decimal(12,2),
  price decimal(12,2),
  description text DEFAULT NULL,
  client_id integer  
);

DROP TABLE IF EXISTS project_equipments;
CREATE TABLE IF NOT EXISTS project_equipments (
  id SERIAL PRIMARY KEY,
  project_id integer,
  equipment_id integer,
  quantity integer,
  net_price decimal(12,2),
  price decimal(12,2),
  client_id integer  
);

DROP TABLE IF EXISTS materials;
CREATE TABLE IF NOT EXISTS materials (
  id SERIAL PRIMARY KEY,
  code varchar(20) NOT NULL,
  name varchar(100) NOT NULL,
  unit integer,
  quantity decimal(12, 4),
  net_price decimal(12,2),
  price decimal(12,2),
  description text DEFAULT NULL,
  client_id integer  
);

DROP TABLE IF EXISTS project_materials;
CREATE TABLE IF NOT EXISTS project_materials (
  id SERIAL PRIMARY KEY,
  project_id integer,
  material_id integer,
  quantity decimal(12, 4),
  net_price decimal(12,2),
  price decimal(12,2),
  client_id integer  
);


DROP TABLE IF EXISTS clients;
CREATE TABLE IF NOT EXISTS clients (
  id SERIAL PRIMARY KEY,
  name varchar(100) NOT NULL,
  description text,
  client_code varchar(25) DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  payment_delay integer DEFAULT NULL,
  tax decimal(5,2) DEFAULT NULL,
  designation varchar(100) DEFAULT NULL,
  street varchar(100) DEFAULT NULL,
  complement varchar(100) DEFAULT NULL,
  zip varchar(50) DEFAULT NULL,
  city varchar(100) DEFAULT NULL,
  state varchar(100) DEFAULT NULL,
  country varchar(100) DEFAULT NULL,
  id_client_type integer DEFAULT 131,
  payment_delay_end_of_month bit(1) NULL DEFAULT B'0',
  num_tax varchar(100) DEFAULT NULL,
  id_payment_delay integer DEFAULT NULL,
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS providers;
CREATE TABLE IF NOT EXISTS providers (
  id SERIAL PRIMARY KEY,
  name varchar(100) NOT NULL,
  id_provider_type integer DEFAULT NULL,
  description text,
  provider_code varchar(25) DEFAULT NULL,
  id_payment_delay integer DEFAULT NULL,
  num_tax varchar(100) DEFAULT NULL,
  tax decimal(5,2) DEFAULT NULL,
  designation varchar(100) DEFAULT NULL,
  street varchar(100) DEFAULT NULL,
  complement varchar(100) DEFAULT NULL,
  zip varchar(100) DEFAULT NULL,
  city varchar(100) DEFAULT NULL,
  state varchar(100) DEFAULT NULL,
  country varchar(100) DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);

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

DROP TABLE IF EXISTS statuses;
CREATE TABLE IF NOT EXISTS statuses (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  scope varchar(20),
  color varchar(7) DEFAULT NULL,
  sort_order integer DEFAULT NULL,
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS resources;
CREATE TABLE IF NOT EXISTS resources (
  id SERIAL PRIMARY KEY,
  code varchar(100) NOT NULL UNIQUE,
  title varchar(500) DEFAULT NULL,
  id_card varchar(20) NOT NULL,
  name varchar(100) DEFAULT NULL,
  full_name varchar(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  description text,
  phone varchar(20) DEFAULT NULL,
  mobile varchar(20) DEFAULT NULL,
  fax varchar(20) DEFAULT NULL,
  address varchar(4000) DEFAULT NULL,
  street varchar(50) DEFAULT NULL,
  zip varchar(50) DEFAULT NULL,
  city varchar(50) DEFAULT NULL,
  state varchar(50) DEFAULT NULL,
  country varchar(50) DEFAULT NULL,
  salary decimal(12,2),
  is_resource bit(1) NULL DEFAULT B'0',
  is_user bit(1) NULL DEFAULT B'0',
  is_contact bit(1) NULL DEFAULT B'0',
  id_client integer DEFAULT NULL,
  is_trash bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS product_projects;
CREATE TABLE IF NOT EXISTS product_projects (
  id SERIAL PRIMARY KEY,
  id_project integer DEFAULT NULL,
  id_product integer DEFAULT NULL,
  start_date date DEFAULT NULL,
  end_date date DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);

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

DROP TABLE IF EXISTS payment_modes;
CREATE TABLE IF NOT EXISTS payment_modes (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  sort_order integer DEFAULT '0',
  idle bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS quotations;
CREATE TABLE IF NOT EXISTS quotations (
  id SERIAL PRIMARY KEY,
  id_project integer DEFAULT NULL,
  id_quotation_type integer DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  description text,
  creation_date date DEFAULT NULL,
  id_user integer DEFAULT NULL,
  id_status integer DEFAULT NULL,
  id_resource integer DEFAULT NULL,
  id_client integer DEFAULT NULL,
  id_contact integer DEFAULT NULL,
  additional_info text,
  initial_end_date date DEFAULT NULL,
  untaxed_amount decimal(12,2) DEFAULT NULL,
  initial_price_per_day_amount decimal(12,2) DEFAULT '0.00',
  initial_amount decimal(12,2) DEFAULT '0.00',
  comment text,
  idle bit(1) NULL DEFAULT B'0',
  done bit(1) NULL DEFAULT B'0',
  cancelled bit(1) NULL DEFAULT B'0',
  idle_date date DEFAULT NULL,
  done_date date DEFAULT NULL,
  handled bit(1) NULL DEFAULT B'0',
  handled_date date DEFAULT NULL,
  reference varchar(100) DEFAULT NULL,
  send_date date DEFAULT NULL,
  validity_end_date date DEFAULT NULL,
  id_activity_type integer DEFAULT NULL,
  result text,
  id_payment_delay integer DEFAULT NULL,
  tax decimal(5,2) DEFAULT NULL,
  full_amount decimal(12,2) DEFAULT NULL,
  id_delivery_mode integer DEFAULT NULL,
  id_likelihood integer DEFAULT NULL,
  planned_work decimal(12,2) DEFAULT '0.00',
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS commands;
CREATE TABLE IF NOT EXISTS commands (
  id SERIAL PRIMARY KEY,
  id_project integer DEFAULT NULL,
  id_command_type integer DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  description text,
  creation_date date DEFAULT NULL,
  id_user integer DEFAULT NULL,
  id_status integer DEFAULT NULL,
  id_resource integer DEFAULT NULL,
  additional_info text,
  external_reference varchar(100) DEFAULT NULL,
  id_activity integer DEFAULT NULL,
  initial_start_date date DEFAULT NULL,
  initial_end_date date DEFAULT NULL,
  validated_end_date date DEFAULT NULL,
  initial_work decimal(12,2) DEFAULT '0.00',
  initial_price_per_day_amount decimal(12,2) DEFAULT '0.00',
  untaxed_amount decimal(12,2) DEFAULT NULL,
  add_work decimal(12,2) DEFAULT '0.00',
  add_price_per_day_amount decimal(12,2) DEFAULT '0.00',
  add_untaxed_amount decimal(12,2) DEFAULT NULL,
  validated_work decimal(12,2) DEFAULT '0.00',
  validated_price_per_day_amount decimal(12,2) DEFAULT '0.00',
  total_untaxed_amount decimal(12,2) DEFAULT NULL,
  comment text,
  idle bit(1) NULL DEFAULT B'0',
  done bit(1) NULL DEFAULT B'0',
  cancelled bit(1) NULL DEFAULT B'0',
  idle_date date DEFAULT NULL,
  done_date date DEFAULT NULL,
  handled bit(1) NULL DEFAULT B'0',
  handled_date date DEFAULT NULL,
  reference varchar(100) DEFAULT NULL,
  validated_start_date date DEFAULT NULL,
  id_activity_type integer DEFAULT NULL,
  id_client integer DEFAULT NULL,
  id_contact integer DEFAULT NULL,
  id_payment_delay integer DEFAULT NULL,
  tax decimal(5,2) DEFAULT NULL,
  full_amount decimal(12,2) DEFAULT NULL,
  add_full_amount decimal(12,2) DEFAULT NULL,
  total_full_amount decimal(12,2) DEFAULT NULL,
  id_delivery_mode integer DEFAULT NULL,
  reception_date date DEFAULT NULL,
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS bills;
CREATE TABLE IF NOT EXISTS bills (
  id SERIAL PRIMARY KEY,
  id_bill_type integer DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  id_project integer DEFAULT NULL,
  id_client integer DEFAULT NULL,
  id_contact integer DEFAULT NULL,
  id_recipient integer DEFAULT NULL,
  billing_type varchar(10) DEFAULT NULL,
  description text,
  date date DEFAULT NULL,
  id_status integer DEFAULT NULL,
  done bit(1) NULL DEFAULT B'0',
  idle bit(1) NULL DEFAULT B'0',
  bill_id integer DEFAULT NULL,
  tax decimal(5,2) DEFAULT NULL,
  untaxed_amount decimal(12,2) DEFAULT NULL,
  full_amount decimal(12,2) DEFAULT NULL,
  cancelled bit(1) NULL DEFAULT B'0',
  id_activity_type integer DEFAULT NULL,
  reference varchar(100) DEFAULT NULL,
  payment_done bit(1) NULL DEFAULT B'0',
  payment_date date DEFAULT NULL,
  payment_amount decimal(11,2) DEFAULT NULL,
  id_payment_delay integer DEFAULT NULL,
  payment_due_date date DEFAULT NULL,
  id_delivery_mode integer DEFAULT NULL,
  id_resource integer DEFAULT NULL,
  id_user integer DEFAULT NULL,
  creation_date date DEFAULT NULL,
  payments_count integer DEFAULT '0',
  command_amount_pct integer DEFAULT '100',
  send_date date DEFAULT NULL,
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS payments;
CREATE TABLE IF NOT EXISTS payments (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  id_bill integer DEFAULT NULL,
  payment_date date DEFAULT NULL,
  id_payment_mode integer DEFAULT NULL,
  idle bit(1) NULL DEFAULT B'0',
  id_payment_type integer DEFAULT NULL,
  payment_amount decimal(11,2) DEFAULT NULL,
  payment_fee_amount decimal(11,2) DEFAULT NULL,
  payment_credit_amount decimal(11,2) DEFAULT NULL,
  description text,
  id_user integer DEFAULT NULL,
  creation_date date DEFAULT NULL,
  reference_bill varchar(100) DEFAULT NULL,
  id_client integer DEFAULT NULL,
  id_recipient integer DEFAULT NULL,
  bill_amount decimal(11,2) DEFAULT NULL,
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS teams;
CREATE TABLE IF NOT EXISTS teams (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  description text,
  idle bit(1) NULL DEFAULT B'0',
  id_resource integer DEFAULT NULL,
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS task_equipments;
CREATE TABLE IF NOT EXISTS task_equipments (
  id SERIAL PRIMARY KEY,
  project_id integer,
  task_id integer,
  equipment_id integer,
  quantity integer,
  actual_work integer NULL DEFAULT 0,
  client_id integer  
);
DROP TABLE IF EXISTS task_materials;
CREATE TABLE IF NOT EXISTS task_materials (
  id SERIAL PRIMARY KEY,
  project_id integer,
  task_id integer,
  material_id integer,
  estimate_quantity decimal(12, 4),
  actual_quantity decimal(12, 4),
  client_id integer  
);
DROP TABLE IF EXISTS tasks;
CREATE TABLE IF NOT EXISTS tasks(
   	id SERIAL PRIMARY KEY,
    task_name varchar(100) NOT NULL,
    wbs varchar(50),
    parent_id varchar(50),
    id_affectation integer,
    responsible integer,
    task_owner character varying,
	description text DEFAULT NULL,
    start_date date DEFAULT NULL,
    end_date date DEFAULT NULL,
    create_date date DEFAULT now(),
    update_date date DEFAULT NULL,
    estimate_time integer,
    actually_time integer,
	project_id integer DEFAULT NULL,
	status_id integer DEFAULT NULL,
	priority_id integer DEFAULT NULL,
  	type_id integer DEFAULT NULL,
  	completed integer,
  	comment text,
  	is_trash bit(1) NULL DEFAULT B'0',
   	client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS task_resources;
CREATE TABLE IF NOT EXISTS task_resources (
  id SERIAL PRIMARY KEY,
  task_id integer,
  project_id integer DEFAULT NULL,
  resource_id integer DEFAULT NULL,
  quantity integer DEFAULT 1,
  actual_work integer NULL DEFAULT 0,
  warning varchar(60) DEFAULT NULL,
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS comments;
CREATE TABLE IF NOT EXISTS comments (
  id SERIAL PRIMARY KEY,
  id_task integer,
  content text,
  comment_by varchar(50), 
  create_date_time date DEFAULT NULL
);

DROP TABLE IF EXISTS attachments;
CREATE TABLE IF NOT EXISTS attachments (
  id SERIAL PRIMARY KEY,
  ref_type varchar(100) NOT NULL,
  ref_id integer NOT NULL,
  id_user integer DEFAULT NULL,
  creation_date timestamp DEFAULT NULL,
  file_name varchar(1024) DEFAULT NULL,
  description varchar(4000) DEFAULT NULL,
  sub_directory varchar(100) DEFAULT NULL,
  mime_type varchar(100) DEFAULT NULL,
  file_size integer DEFAULT NULL,
  link varchar(1024) DEFAULT NULL,
  type varchar(10) DEFAULT 'file',
  id_privacy integer DEFAULT '1',
  id_team integer DEFAULT '1',
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS documents;
CREATE TABLE IF NOT EXISTS documents (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  project_id integer DEFAULT NULL,
  task_id integer DEFAULT '0',
  type_id integer DEFAULT NULL,
  version integer DEFAULT NULL,
  status_id integer DEFAULT NULL,
  author_name varchar(70) DEFAULT NULL,
  file_name varchar(100) DEFAULT NULL,
  description varchar(4000) DEFAULT NULL,
  create_date_time date DEFAULT NULL,
  client_id integer
);


DROP TABLE IF EXISTS document_versions;
CREATE TABLE IF NOT EXISTS document_versions (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  project_id integer NOT NULL,
  task_id integer DEFAULT '0',
  version integer DEFAULT NULL,
  link varchar(400) DEFAULT NULL,
  file_size integer,
  create_date_time timestamp DEFAULT NULL,
  update_date_time timestamp DEFAULT NULL,
  document_id integer DEFAULT NULL,
  author_name varchar(70) DEFAULT NULL,
  status_id integer DEFAULT NULL,
  description varchar(4000) DEFAULT NULL,
  client_id integer DEFAULT NULL
);


DROP TABLE IF EXISTS risks;
CREATE TABLE IF NOT EXISTS risks (
  id SERIAL PRIMARY KEY,
  project_id integer DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  description text,
  type_id integer DEFAULT NULL,
  cause text,
  impact text,
  severity_id integer DEFAULT NULL,
  likelihood_id integer DEFAULT NULL,
  criticality_id integer DEFAULT NULL,
  creation_date date DEFAULT NULL,
  status_id integer DEFAULT NULL,
  planning_end_date date DEFAULT NULL,
  actual_end_date date DEFAULT NULL,
  result text,
  done_date date DEFAULT NULL,
  handled_date date DEFAULT NULL,
  priority_id integer DEFAULT NULL,
  impact_cost decimal(11,2) DEFAULT '0.00',
  update_date date DEFAULT NULL,
  update_by varchar(30) DEFAULT NULL,
  client_id integer DEFAULT 1
);


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

DROP TABLE IF EXISTS call_for_tenders;
CREATE TABLE IF NOT EXISTS call_for_tenders (
  id SERIAL PRIMARY KEY,
  reference varchar(100) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  id_call_for_tender_type integer DEFAULT NULL,
  id_project integer DEFAULT NULL,
  id_user integer DEFAULT NULL,
  description text DEFAULT NULL,
  technical_requirements text DEFAULT NULL,
  business_requirements text DEFAULT NULL,
  other_requirements text DEFAULT NULL,
  creation_date timestamp DEFAULT NULL,
  id_status integer DEFAULT NULL,
  id_resource integer DEFAULT NULL,
  send_date_time timestamp DEFAULT NULL,
  expected_tender_date_time timestamp DEFAULT NULL,
  max_amount decimal(11,2) DEFAULT NULL,
  delivery_date date DEFAULT NULL,
  evaluation_max_value decimal(7,2) DEFAULT NULL,
  fix_value bit(1) NULL DEFAULT B'0',
  id_product integer DEFAULT NULL,
  id_product_version integer DEFAULT NULL,
  id_component integer DEFAULT NULL,
  id_component_version integer DEFAULT NULL,
  result text DEFAULT NULL,
  handled bit(1) NULL DEFAULT B'0',
  done bit(1) NULL DEFAULT B'0',
  idle bit(1) NULL DEFAULT B'0',
  cancelled bit(1) NULL DEFAULT B'0',
  handled_date date DEFAULT NULL,
  done_date date DEFAULT NULL,
  idle_date date DEFAULT NULL,
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS delivery_modes;
CREATE TABLE IF NOT EXISTS delivery_modes (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  sort_order integer DEFAULT '0',
  idle bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS tenders;
CREATE TABLE IF NOT EXISTS tenders (
  id SERIAL PRIMARY KEY,
  reference varchar(100) DEFAULT NULL,
  name varchar(200) DEFAULT NULL,
  id_tender_type integer DEFAULT NULL,
  id_project integer DEFAULT NULL,
  id_call_for_tender integer DEFAULT NULL,
  id_tender_status integer DEFAULT NULL,
  id_user integer DEFAULT NULL,
  creation_date timestamp DEFAULT NULL,
  id_provider integer DEFAULT NULL,
  external_reference varchar(100) DEFAULT NULL,
  description text DEFAULT NULL,
  id_status integer DEFAULT NULL,
  id_resource integer DEFAULT NULL,
  id_contact integer DEFAULT NULL,
  request_date_time timestamp DEFAULT NULL,
  expected_tender_date_time timestamp DEFAULT NULL,
  reception_date_time timestamp DEFAULT NULL,
  evaluation_value decimal(7,2) DEFAULT NULL,
  evaluation_rank integer DEFAULT NULL,
  offer_validity_end_date date DEFAULT NULL,
  planned_amount decimal(11,2) DEFAULT NULL,
  tax_pct decimal(5,2) DEFAULT NULL,
  planned_tax_amount decimal(11,2) DEFAULT NULL,
  planned_full_amount decimal(11,2) DEFAULT NULL,
  initial_amount decimal(11,2) DEFAULT NULL,
  initial_tax_amount decimal(11,2) DEFAULT NULL,
  initial_full_amount decimal(11,2) DEFAULT NULL,
  delivery_delay varchar(100) DEFAULT NULL,
  delivery_date date DEFAULT NULL,
  payment_condition varchar(100) DEFAULT NULL,
  evaluation decimal(7,2) DEFAULT NULL,
  result text DEFAULT NULL,
  handled bit(1) NULL DEFAULT B'0',
  done bit(1) NULL DEFAULT B'0',
  idle bit(1) NULL DEFAULT B'0',
  cancelled bit(1) NULL DEFAULT B'0',
  handled_date date DEFAULT NULL,
  done_date date DEFAULT NULL,
  idle_date date DEFAULT NULL,
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS tender_statuses;
CREATE TABLE IF NOT EXISTS tender_statuses (
  id SERIAL PRIMARY KEY,
  name varchar(100) DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  sort_order integer DEFAULT NULL,
  is_waiting bit(1) NULL DEFAULT B'0',
  is_received bit(1) NULL DEFAULT B'0',
  is_not_select bit(1) NULL DEFAULT B'0',
  is_selected bit(1) NULL DEFAULT B'0',
  idle bit(1) NULL DEFAULT B'0',
  client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS calendars;
CREATE TABLE IF NOT EXISTS calendars(
   	id SERIAL PRIMARY KEY,
    calendar_name text,
	description text,
	attach_file character varying,
    start_date date,
    end_date date,
	project_id integer,
   	client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS task_resource_daily_tracking;
CREATE TABLE IF NOT EXISTS task_resource_daily_tracking
(
  id SERIAL PRIMARY KEY,
  task_id integer,
  project_id integer,
  actual_time integer,
  planning_time integer,
  over_time integer,
  coefficient decimal(12, 2),
  quantity integer,
  date_log date,
  owner character varying,
  resource_id integer,
  salary decimal(12, 2)
);

DROP TABLE IF EXISTS task_equipment_daily_tracking;
CREATE TABLE IF NOT EXISTS task_equipment_daily_tracking
(
  id SERIAL PRIMARY KEY,
  task_id integer,
  project_id integer,
  actual_time integer,
  planning_time integer,
  quantity integer,
  date_log date,
  owner character varying,
  equipment_id integer,
  net_price decimal(12,2),
  price decimal(12, 2)
);

DROP TABLE IF EXISTS task_material_daily_tracking;
CREATE TABLE IF NOT EXISTS task_material_daily_tracking
(
  id SERIAL PRIMARY KEY,
  task_id integer,
  project_id integer,
  actual_material decimal(12, 2),
  date_log date,
  owner character varying,
  material_id integer,
  net_price decimal(12,2),
  price decimal(12,2)
);

DROP TABLE IF EXISTS units;
CREATE TABLE IF NOT EXISTS units(
	id SERIAL PRIMARY KEY,
	name varchar(60),
	devices bit(1),
	client_id integer DEFAULT NULL
);

DROP TABLE IF EXISTS baselines;
CREATE TABLE IF NOT EXISTS baselines(
	id SERIAL PRIMARY KEY,
	name varchar(200) NOT NULL,
	baseline_type integer,
	baseline_date date,
	description character varying(2000),
	client_id integer
);

DROP TABLE IF EXISTS project_resource_baselines;
CREATE TABLE IF NOT EXISTS project_resource_baselines (
  id SERIAL PRIMARY KEY,
  resource_id integer DEFAULT NULL,
  salary decimal(12,2),
  quantity integer DEFAULT 1,
  project_id integer DEFAULT NULL,
  client_id integer DEFAULT NULL,
  baseline_id integer
);

DROP TABLE IF EXISTS project_material_baselines;
CREATE TABLE IF NOT EXISTS project_material_baselines (
  id SERIAL PRIMARY KEY,
  project_id integer,
  material_id integer,
  quantity decimal(12, 4),
  net_price decimal(12,2),
  price decimal(12,2),
  client_id integer,
  baseline_id integer
);

DROP TABLE IF EXISTS project_equipment_baselines;
CREATE TABLE IF NOT EXISTS project_equipment_baselines (
  id SERIAL PRIMARY KEY,
  project_id integer,
  equipment_id integer,
  quantity integer,
  net_price decimal(12,2),
  price decimal(12,2),
  client_id integer,
  baseline_id integer
);

DROP TABLE IF EXISTS task_baselines;
CREATE TABLE IF NOT EXISTS task_baselines(
   	id SERIAL PRIMARY KEY,
    task_name varchar(100) NOT NULL,
    wbs varchar(50),
    parent_id varchar(50),
    id_affectation integer,
    responsible integer,
    task_owner character varying,
	description text DEFAULT NULL,
    start_date date DEFAULT NULL,
    end_date date DEFAULT NULL,
    create_date date DEFAULT now(),
    update_date date DEFAULT NULL,
    estimate_time integer,
    actually_time integer,
	project_id integer DEFAULT NULL,
	status_id integer DEFAULT NULL,
	priority_id integer DEFAULT NULL,
  	type_id integer DEFAULT NULL,
  	completed integer,
  	comment text,
  	is_trash bit(1) NULL DEFAULT B'0',
   	client_id integer DEFAULT NULL,
   	baseline_id integer
);

DROP TABLE IF EXISTS task_resource_baselines;
CREATE TABLE IF NOT EXISTS task_resources (
  id SERIAL PRIMARY KEY,
  task_id integer,
  project_id integer DEFAULT NULL,
  resource_id integer DEFAULT NULL,
  quantity integer DEFAULT 1,
  actual_work integer NULL DEFAULT 0,
  warning varchar(60) DEFAULT NULL,
  client_id integer DEFAULT NULL,
  baseline_id integer
);

DROP TABLE IF EXISTS task_equipment_baselines;
CREATE TABLE IF NOT EXISTS task_equipment_baselines (
  id SERIAL PRIMARY KEY,
  project_id integer,
  task_id integer,
  equipment_id integer,
  quantity integer,
  actual_work integer NULL DEFAULT 0,
  client_id integer,
  baseline_id integer
);
DROP TABLE IF EXISTS task_material_baselines;
CREATE TABLE IF NOT EXISTS task_material_baselines (
  id SERIAL PRIMARY KEY,
  project_id integer,
  task_id integer,
  material_id integer,
  estimate_quantity decimal(12, 4),
  actual_quantity decimal(12, 4),
  client_id integer,
  baseline_id integer
);
CREATE VIEW resource_planning_elements as
SELECT t.project_id,
    p.name AS project_name,
    p.parent_id AS project_parent,
    t.id AS task_id,
    t.task_name,
    t.parent_id,
    t.start_date,
    t.end_date,
    tr.actual_time,
    (tr.actual_time::numeric * tr.salary + tr.over_time::numeric * tr.salary * tr.coefficient)*tr.quantity AS actually_cost,
    r.code,
    r.full_name AS resource_name,
    tr.salary
   FROM tasks t
     LEFT JOIN projects p ON t.project_id = p.id
     LEFT JOIN task_resource_daily_tracking tr ON t.id = tr.task_id
     LEFT JOIN resources r ON tr.resource_id = r.id
     WHERE t.is_trash = B'0'::"bit";

DROP view IF EXISTS equipment_planning_elements;
CREATE VIEW equipment_planning_elements as
SELECT t.project_id,
    p.name AS project_name,
    p.parent_id AS project_parent,
    t.id AS task_id,
    t.task_name,
    t.parent_id,
    t.start_date,
    t.end_date,
    te.actual_time,
    (te.price * te.actual_time::numeric)*te.quantity AS actually_cost,
    e.code,
    e.name AS resource_name,
    te.price
   FROM tasks t
     LEFT JOIN projects p ON t.project_id = p.id
     LEFT JOIN task_equipment_daily_tracking te ON t.id = te.task_id
     LEFT JOIN equipments e ON te.equipment_id = e.id
     WHERE t.is_trash = B'0'::"bit";

DROP view IF EXISTS material_planning_elements;
CREATE VIEW material_planning_elements as
SELECT t.project_id,
    p.name AS project_name,
    t.id AS task_id,
    t.task_name,
    t.parent_id,
    t.start_date,
    t.end_date,
    tm.estimate_quantity,
    tm.actual_quantity AS actually_time,
    tm.estimate_quantity::numeric * m.price AS planning_cost,
    tm.actual_quantity::numeric * m.price AS actually_cost,
    m.code,
    m.name AS resource_name
   FROM tasks t
     LEFT JOIN projects p ON t.project_id = p.id
     LEFT JOIN task_materials tm ON t.id = tm.task_id
     LEFT JOIN materials m ON tm.material_id = m.id
     WHERE t.is_trash = B'0'::"bit";