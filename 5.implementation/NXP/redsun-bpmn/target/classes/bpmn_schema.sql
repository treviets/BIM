DROP TABLE IF EXISTS phase;
CREATE TABLE IF NOT EXISTS phase (
    id SERIAL PRIMARY KEY,
    project_id integer DEFAULT NULL,
    active_step varchar(20) DEFAULT '1_1_1',
	contain text DEFAULT NULL
);

DROP TABLE IF EXISTS phase_diagram;
CREATE TABLE IF NOT EXISTS phase_diagram (
    id SERIAL PRIMARY KEY,
    project_id integer DEFAULT NULL,
	active_step varchar(20) DEFAULT '1_1_1',
	contain text DEFAULT NULL
);

DROP TABLE IF EXISTS standard_diagram;
CREATE TABLE IF NOT EXISTS standard_diagram (
    id SERIAL PRIMARY KEY,
    project_id integer DEFAULT NULL,
    active_step varchar(20) DEFAULT '1_1_1',
	contain text DEFAULT NULL
);

DROP TABLE IF EXISTS phase_document;
CREATE TABLE IF NOT EXISTS phase_document (
    id SERIAL PRIMARY KEY,
    project_id integer DEFAULT NULL,
    active_step varchar(20) DEFAULT '1_1_1',
    upload_by text DEFAULT NULL,
    upload_date text DEFAULT NULL,
    update_by text DEFAULT NULL,
    update_date text DEFAULT NULL,
    doc_name text DEFAULT NULL,
    url text DEFAULT NULL
);


DROP TABLE IF EXISTS document_step;
CREATE TABLE IF NOT EXISTS document_step (
    id SERIAL PRIMARY KEY,
    project_id integer DEFAULT NULL,
    step_id text DEFAULT NULL,
    document_name text DEFAULT NULL,
    document_description text DEFAULT NULL
);

DROP TABLE IF EXISTS resource_step;
CREATE TABLE IF NOT EXISTS resource_step (
    id SERIAL PRIMARY KEY,
    project_id integer DEFAULT NULL,
    step_id text DEFAULT NULL,
    code text DEFAULT NULL,
    email text DEFAULT NULL
);

