PGDMP         3             
    t            redsun    9.6.1    9.6.1 "    u           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            v           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            w           1262    24576    redsun    DATABASE     �   CREATE DATABASE redsun WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE redsun;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            x           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12387    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            y           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    24623    menu    TABLE     �   CREATE TABLE menu (
    id bigint NOT NULL,
    name text NOT NULL,
    description text NOT NULL,
    content text NOT NULL,
    "position" character(5)
);
    DROP TABLE public.menu;
       public         postgres    false    3            �            1259    24589 
   permission    TABLE     w   CREATE TABLE permission (
    "permissionId" bigint NOT NULL,
    name text NOT NULL,
    description text NOT NULL
);
    DROP TABLE public.permission;
       public         postgres    false    3            �            1259    24587    permission_permissionId_seq    SEQUENCE        CREATE SEQUENCE "permission_permissionId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public."permission_permissionId_seq";
       public       postgres    false    187    3            z           0    0    permission_permissionId_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE "permission_permissionId_seq" OWNED BY permission."permissionId";
            public       postgres    false    186            �            1259    24597    role_permission_menu    TABLE     �   CREATE TABLE role_permission_menu (
    id bigint NOT NULL,
    permission_id text NOT NULL,
    username character varying NOT NULL,
    menu_id bigint NOT NULL
);
 (   DROP TABLE public.role_permission_menu;
       public         postgres    false    3            �            1259    24605    roles    TABLE     f   CREATE TABLE roles (
    id bigint NOT NULL,
    name text NOT NULL,
    description text NOT NULL
);
    DROP TABLE public.roles;
       public         postgres    false    3            �            1259    24603    roles_id_seq    SEQUENCE     n   CREATE SEQUENCE roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public       postgres    false    3    191            {           0    0    roles_id_seq    SEQUENCE OWNED BY     /   ALTER SEQUENCE roles_id_seq OWNED BY roles.id;
            public       postgres    false    190            �            1259    24595    user_permission_id_seq    SEQUENCE     x   CREATE SEQUENCE user_permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.user_permission_id_seq;
       public       postgres    false    3    189            |           0    0    user_permission_id_seq    SEQUENCE OWNED BY     H   ALTER SEQUENCE user_permission_id_seq OWNED BY role_permission_menu.id;
            public       postgres    false    188            �            1259    24577    users    TABLE     �   CREATE TABLE users (
    username character varying(45) NOT NULL,
    password character varying(45) NOT NULL,
    enabled smallint NOT NULL
);
    DROP TABLE public.users;
       public         postgres    false    3            �           2604    24592    permission permissionId    DEFAULT     x   ALTER TABLE ONLY permission ALTER COLUMN "permissionId" SET DEFAULT nextval('"permission_permissionId_seq"'::regclass);
 H   ALTER TABLE public.permission ALTER COLUMN "permissionId" DROP DEFAULT;
       public       postgres    false    186    187    187            �           2604    24600    role_permission_menu id    DEFAULT     o   ALTER TABLE ONLY role_permission_menu ALTER COLUMN id SET DEFAULT nextval('user_permission_id_seq'::regclass);
 F   ALTER TABLE public.role_permission_menu ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    188    189    189            �           2604    24608    roles id    DEFAULT     V   ALTER TABLE ONLY roles ALTER COLUMN id SET DEFAULT nextval('roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    191    190    191            r          0    24623    menu 
   TABLE DATA               C   COPY menu (id, name, description, content, "position") FROM stdin;
    public       postgres    false    192   �!       m          0    24589 
   permission 
   TABLE DATA               @   COPY permission ("permissionId", name, description) FROM stdin;
    public       postgres    false    187   �"       }           0    0    permission_permissionId_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('"permission_permissionId_seq"', 1, false);
            public       postgres    false    186            o          0    24597    role_permission_menu 
   TABLE DATA               M   COPY role_permission_menu (id, permission_id, username, menu_id) FROM stdin;
    public       postgres    false    189   #       q          0    24605    roles 
   TABLE DATA               /   COPY roles (id, name, description) FROM stdin;
    public       postgres    false    191   ^#       ~           0    0    roles_id_seq    SEQUENCE SET     4   SELECT pg_catalog.setval('roles_id_seq', 1, false);
            public       postgres    false    190                       0    0    user_permission_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('user_permission_id_seq', 1, false);
            public       postgres    false    188            k          0    24577    users 
   TABLE DATA               5   COPY users (username, password, enabled) FROM stdin;
    public       postgres    false    185   �#       �           2606    24581    users Users_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY users
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (username);
 <   ALTER TABLE ONLY public.users DROP CONSTRAINT "Users_pkey";
       public         postgres    false    185    185            �           2606    24630    menu menu_pkey 
   CONSTRAINT     E   ALTER TABLE ONLY menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.menu DROP CONSTRAINT menu_pkey;
       public         postgres    false    192    192            �           2606    24594    permission permission_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY ("permissionId");
 D   ALTER TABLE ONLY public.permission DROP CONSTRAINT permission_pkey;
       public         postgres    false    187    187            �           2606    24610    roles roles_pkey 
   CONSTRAINT     G   ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public         postgres    false    191    191            �           2606    24602 )   role_permission_menu user_permission_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY role_permission_menu
    ADD CONSTRAINT user_permission_pkey PRIMARY KEY (id);
 S   ALTER TABLE ONLY public.role_permission_menu DROP CONSTRAINT user_permission_pkey;
       public         postgres    false    189    189            r     x���Mo� ���+�w��~]\/��C7��k/g�����_��46�j��d�μOfxD/��q�C!:��v�2%��$�Ӳ��5pڧ«\�j*g!g�vLI|Ɉ�I����x�_쨫��D9v/�G5he���Jʡy����$'%0Di��f��4��y@�g;@��V�0�#E��/6K�;t<��Q����FO$��~{/o�3|tB�@����Ӏdm����%COT�l���tk.�c6Yr䨪Q5���f�h6��]3Z�R���&I�/k^I�      m   %   x�3�,��2�L�t�2�L�t�2�,������ J��      o   C   x�]�)�@@���9v��`H0�Ł����V������)2���(�T�E�)���<6�?`$%      q   T   x�3���q�wt���3����<��� �� .C���`� $>>
 .c�l�/B. ����9D���������/�+F��� �/      k   =   x�++�420JN�43JL6�4072M��M�R��M��8�Sr3�QX�K��=... ]�=     