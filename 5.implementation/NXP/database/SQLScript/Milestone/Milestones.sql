-- Table: public.milestones

DROP TABLE IF EXISTS milestones;

CREATE TABLE milestones
(
  id SERIAL PRIMARY KEY,
  id_client integer,
  id_project integer,
  id_milestone_type integer,
  id_activity integer,
  id_user integer,
  id_status integer,
  id_resource integer,
  id_version integer,
  name character varying(100) DEFAULT NULL::character varying,
  description text,
  creation_date date,
  result text,
  comment character varying(4000) DEFAULT NULL::character varying,
  idle boolean,
  done boolean,
  idle_date date,
  done_date date,
  handled boolean,
  handled_date date,
  reference character varying(100) DEFAULT NULL::character varying,
  external_reference character varying(100) DEFAULT NULL::character varying,
  cancelled boolean,
  CONSTRAINT milestone_name UNIQUE("name")
)