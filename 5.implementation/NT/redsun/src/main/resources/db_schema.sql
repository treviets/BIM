DROP TABLE IF EXISTS clients;
CREATE TABLE IF NOT EXISTS clients (
	id SERIAL PRIMARY KEY,
    name character varying(30) COLLATE pg_catalog."default",
    address character varying(200) COLLATE pg_catalog."default",
    phone character varying(20) COLLATE pg_catalog."default",
    no character varying(15) COLLATE pg_catalog."default",
    logo character varying(300)
);

DROP TABLE IF EXISTS menus;
CREATE TABLE IF NOT EXISTS menus (
	id SERIAL PRIMARY KEY,
    name text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" ,
    url text COLLATE pg_catalog."default" ,
    "position" character(5) COLLATE pg_catalog."default",
    iconclass text COLLATE pg_catalog."default" ,
    status bit(1)
);


DROP TABLE IF EXISTS permission;
CREATE TABLE IF NOT EXISTS permission (
	"permissionId" SERIAL PRIMARY KEY,
    name text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL
);

DROP TABLE IF EXISTS role_permission_menu;
CREATE TABLE IF NOT EXISTS role_permission_menu (
	id SERIAL PRIMARY KEY,
    role_id smallint NOT NULL,
    menu_id smallint NOT NULL,
    permission text COLLATE pg_catalog."default" NOT NULL
);



DROP TABLE IF EXISTS roles;
CREATE TABLE IF NOT EXISTS roles (
	id SERIAL PRIMARY KEY,
    name text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL
);

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    username character varying(45) COLLATE pg_catalog."default" NOT NULL PRIMARY KEY,
    password character varying(45) COLLATE pg_catalog."default" NOT NULL,
    profile_id integer NOT NULL,
    client_id integer NOT NULL,
    status bit(1),
    create_by character varying(45) COLLATE pg_catalog."default",
    create_date date DEFAULT now(),
    update_by character varying COLLATE pg_catalog."default",
    update_date date
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE IF NOT EXISTS users_roles (
	id SERIAL PRIMARY KEY,
    role_id smallint NOT NULL,
    username character varying COLLATE pg_catalog."default" NOT NULL,
    client_id bigint,
    create_by character varying COLLATE pg_catalog."default",
    create_date date DEFAULT now(),
    update_by character varying COLLATE pg_catalog."default",
    update_date date
);
DROP TABLE IF EXISTS module_properties;
CREATE TABLE module_properties
(
    name character varying(100) COLLATE pg_catalog."default",
    item character varying(100) COLLATE pg_catalog."default" NOT NULL,
    item_title text COLLATE pg_catalog."default",
    CONSTRAINT module_properties_pkey PRIMARY KEY (name, item)
);


DROP TABLE IF EXISTS module_permission;
CREATE TABLE public.module_permission
(
    id SERIAL,
    name text COLLATE pg_catalog."default",
    key character varying(50) COLLATE pg_catalog."default" NOT NULL,
    module_item character varying(100) COLLATE pg_catalog."default",
    permission text COLLATE pg_catalog."default",
    CONSTRAINT module_permission_pkey PRIMARY KEY (id,name, key)
);

DROP TABLE IF EXISTS module_role;
CREATE TABLE public.module_role
(
    id SERIAL PRIMARY KEY,
    username character varying COLLATE pg_catalog."default" NOT NULL,
	module_permission_id integer NOT NULL,
	module_permission_key character varying(50) COLLATE pg_catalog."default" NOT NULL
);
