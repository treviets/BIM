-- Table: public.statuses

DROP TABLE IF EXISTS public.statuses;

CREATE TABLE public.statuses
(
  id SERIAL PRIMARY KEY,
  name character varying(100) DEFAULT NULL::character varying,
  set_done_status boolean,
  set_idle_status boolean,
  color character varying(7) DEFAULT NULL::character varying,
  sort_order integer,
  idle boolean,
  set_handled_status boolean,
  is_copy_status boolean,
  set_cancelled_status boolean,
  created_by integer,
  created_date date,
  modified_by integer,
  modified_date date,
  CONSTRAINT statuses_name UNIQUE("name")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.statuses
  OWNER TO postgres;
