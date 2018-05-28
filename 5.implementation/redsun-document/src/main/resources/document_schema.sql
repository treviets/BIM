DROP TABLE IF EXISTS default_directories;
CREATE TABLE IF NOT EXISTS default_directories (
  id SERIAL PRIMARY KEY,
  name character varying(300) COLLATE pg_catalog."default" NOT NULL,
  location character varying(1000) COLLATE pg_catalog."default" DEFAULT NULL,
  parent_id int NOT NULL,
  project_id integer NOT NULL,
  client_id integer NOT NULL,
  create_date_time date DEFAULT NULL,
  update_date_time date DEFAULT NULL,
  create_user_name character varying(45),
  trash integer DEFAULT 0
);

DROP TABLE IF EXISTS directories;
CREATE TABLE IF NOT EXISTS directories (
  id SERIAL PRIMARY KEY,
  name character varying(300) COLLATE pg_catalog."default" NOT NULL,
  location character varying(1000) COLLATE pg_catalog."default" DEFAULT NULL,
  parent_id int NOT NULL,
  project_id integer NOT NULL,
  client_id integer NOT NULL,
  create_date_time date DEFAULT NULL,
  update_date_time date DEFAULT NULL,
  create_user_name character varying(45),
  trash integer DEFAULT 0
);

DROP TABLE IF EXISTS directory_resources;
CREATE TABLE IF NOT EXISTS directory_resources (
  id SERIAL PRIMARY KEY,
  directory_id integer,
  project_id integer DEFAULT NULL,
  resource_id integer DEFAULT NULL,
  client_id integer DEFAULT NULL,
  user_name character varying(45)
);

DROP TABLE IF EXISTS documents;
CREATE TABLE IF NOT EXISTS documents (
  id SERIAL PRIMARY KEY,
  name character varying(1000) COLLATE pg_catalog."default" NOT NULL,
  location character varying(1000) COLLATE pg_catalog."default" NOT NULL,
  project_id integer DEFAULT NULL,
  client_id integer NOT NULL,
  ref_type varchar(100) DEFAULT NULL,
  directory_id integer NOT NULL,
  mime_type varchar(100) DEFAULT NULL,
  file_size integer DEFAULT NULL,
  link varchar(1024) DEFAULT NULL,
  document_type integer DEFAULT NULL,
  document_version_id integer NOT NULL,
  locked smallint DEFAULT '0',
  description character varying(4000) DEFAULT NULL,
  user_name character varying(45),
  create_date_time date DEFAULT NULL
);


DROP TABLE IF EXISTS document_versions;
CREATE TABLE IF NOT EXISTS document_version (
  id SERIAL PRIMARY KEY,
  version smallint DEFAULT NULL,
  create_date_time date DEFAULT NULL,
  update_date_time date DEFAULT NULL,
  document_id integer NOT NULL,
  description character varying(4000) DEFAULT NULL
);
