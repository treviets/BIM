DROP TABLE IF EXISTS companies;
CREATE TABLE IF NOT EXISTS companies (
  id SERIAL PRIMARY KEY,
  code character varying(50) NOT NULL,
  name character varying(150) NOT NULL,
  phone character varying(20) DEFAULT NULL::character varying,
  fax character varying(20) DEFAULT NULL::character varying,
  email character varying(100) DEFAULT NULL::character varying, 
  address character varying(4000) DEFAULT NULL::character varying,
  street character varying(50) DEFAULT NULL::character varying,
  zip character varying(50) DEFAULT NULL::character varying,
  city character varying(50) DEFAULT NULL::character varying,
  state character varying(50) DEFAULT NULL::character varying,
  country character varying(50) DEFAULT NULL::character varying,
  parent_id integer,
  wbs character varying(50) DEFAULT NULL,
  manager integer DEFAULT NULL,
  is_trash bit(1) DEFAULT B'0'::"bit",
  client_id integer
);
DROP TABLE IF EXISTS departments;
CREATE TABLE IF NOT EXISTS departments (
  id SERIAL PRIMARY KEY,
  code character varying(50) NOT NULL,
  name character varying(150) NOT NULL,
  parent_id integer,
  wbs character varying(50) DEFAULT NULL,
  level integer,
  manager integer DEFAULT NULL,
  company_id integer,
  is_trash bit(1) DEFAULT B'0'::"bit",
  client_id integer
);
DROP TABLE IF EXISTS religions;
CREATE TABLE IF NOT EXISTS religions (
  id SERIAL PRIMARY KEY,
  name character varying(150) NOT NULL,
  is_used bit(1) DEFAULT B'0'::"bit",
);
DROP TABLE IF EXISTS positions;
CREATE TABLE IF NOT EXISTS positions (
  id SERIAL PRIMARY KEY,
  name character varying(150) NOT NULL,
  is_used bit(1) DEFAULT B'0'::"bit", 
);
DROP TABLE IF EXISTS resources;
CREATE TABLE IF NOT EXISTS resources (
  id SERIAL PRIMARY KEY,
  code character varying(100) NOT NULL,
  title character varying(500) DEFAULT NULL::character varying,
  id_card character varying(20) NOT NULL,
  id_card_issue_date date,
  id_card_issued character varying (100) DEFAULT NULL,
  name character varying(100) DEFAULT NULL::character varying,
  full_name character varying(100) DEFAULT NULL::character varying,
  gender smallint DEFAULT NULL,
  date_of_birth date DEFAULT NULL,
  place_of_birth character varying(500) DEFAULT NULL,
  religion character varying(100) DEFAULT NULL,
  married bit(1) DEFAULT B'0'::"bit",
  email character varying(100) DEFAULT NULL::character varying,
  description text,
  phone character varying(20) DEFAULT NULL::character varying,
  mobile character varying(20) DEFAULT NULL::character varying,
  fax character varying(20) DEFAULT NULL::character varying,
  address character varying(4000) DEFAULT NULL::character varying,
  street character varying(50) DEFAULT NULL::character varying,
  zip character varying(50) DEFAULT NULL::character varying,
  city character varying(50) DEFAULT NULL::character varying,
  state character varying(50) DEFAULT NULL::character varying,
  country character varying(50) DEFAULT NULL::character varying,
  salary numeric(12,2),
  img character varying(100) DEFAULT NULL,
  created_user integer DEFAULT NULL,
  created_date date DEFAULT NULL,
  modifier_user integer DEFAULT NULL,
  modifier_date date DEFAULT NULL,
  is_resource bit(1) DEFAULT B'0'::"bit",
  is_user bit(1) DEFAULT B'0'::"bit",
  is_contact bit(1) DEFAULT B'0'::"bit",
  id_client integer,
  is_trash bit(1) DEFAULT B'0'::"bit",
  client_id integer,
  department_id integer DEFAULT NULL
);

ALTER TABLE resources
  ADD id_card_issue_date date,
  ADD id_card_issued character varying (100) DEFAULT NULL,
  ADD gender smallint DEFAULT NULL,
  ADD date_of_birth date DEFAULT NULL,
  ADD place_of_birth character varying(500) DEFAULT NULL,
  ADD religion character varying(100) DEFAULT NULL,
  ADD married bit(1) DEFAULT B'0'::"bit",
  ADD img character varying(100) DEFAULT NULL,
  ADD created_user integer DEFAULT NULL,
  ADD created_date date DEFAULT NULL,
  ADD modifier_user integer DEFAULT NULL,
  ADD modifier_date date DEFAULT NULL
  ADD department_id integer DEFAULT NULL
