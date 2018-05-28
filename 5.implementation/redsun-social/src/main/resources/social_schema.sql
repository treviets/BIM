DROP TABLE IF EXISTS discussing;
CREATE TABLE IF NOT EXISTS discussing (
	id SERIAL PRIMARY KEY,
	client_id integer DEFAULT NULL,
	project_id integer DEFAULT NULL,
	group_id character varying(100) DEFAULT NULL,
	created_by character varying(100) DEFAULT NULL,
	message text DEFAULT NULL,
	avatar text DEFAULT NULL,
	created_date timestamp(0) DEFAULT NOW(),
	scopes integer default 1
	
);

DROP TABLE IF EXISTS discussing_groups;
CREATE TABLE IF NOT EXISTS discussing_groups (
	id SERIAL PRIMARY KEY,
	client_id integer DEFAULT NULL,
	project_id integer DEFAULT NULL,
	created_by character varying(100) DEFAULT NULL,
	name text DEFAULT NULL,
	description text DEFAULT NULL,
	created_date timestamp(0) DEFAULT NOW()
);

DROP TABLE IF EXISTS discussing_groups_members;
CREATE TABLE IF NOT EXISTS discussing_groups_members (
	id SERIAL PRIMARY KEY,
	group_id integer,
	username varchar(100),
	avatar text DEFAULT NULL,
	created_by character varying(100) DEFAULT NULL,
	created_date timestamp(0) DEFAULT NOW(),
	status integer DEFAULT 0,
	approved_by varchar(100)
);

DROP TABLE IF EXISTS file_sharing;
CREATE TABLE IF NOT EXISTS file_sharing (
	id SERIAL PRIMARY KEY,
	client_id integer DEFAULT NULL,
	project_id integer DEFAULT NULL,
	group_id integer DEFAULT NULL,
	name character varying(100),
	created_by character varying(100) DEFAULT NULL,
	title character varying(200) DEFAULT NULL,
	created_date timestamp(0) DEFAULT now(),
	description text default NULL,
	file_path character varying(1500) DEFAULT NULL,
	scopes integer default 1
);


