-- Table: public.milestone_types

DROP TABLE IF EXISTS milestone_types;

CREATE TABLE milestone_types
(
  id SERIAL PRIMARY KEY,
  id_workflow integer,
  id_planning_mode integer,
  name character varying(100) DEFAULT NULL::character varying,
  code character varying(50) DEFAULT NULL::character varying,
  description text,
  idle boolean,
  created_by integer,
  created_date date,
  modified_by integer,
  modified_date date,
  CONSTRAINT milestone_types_name UNIQUE("name")
)
