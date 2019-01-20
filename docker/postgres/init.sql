--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 11.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: attraction_tag; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.attraction_tag (
    attraction uuid NOT NULL,
    tag uuid NOT NULL
);


ALTER TABLE public.attraction_tag OWNER TO devpav;

--
-- Name: attraction_type; Type: TABLE; Schema: public; Owner: devpav
--

CREATE TABLE public.attraction_type (
    attraction uuid NOT NULL,
    type uuid NOT NULL
);


ALTER TABLE public.attraction_type OWNER TO devpav;

--
-- Name: attractions; Type: TABLE; Schema: public; Owner: devpav
--

CREATE TABLE public.attractions (
    id uuid NOT NULL,
    image character varying(255),
    link_source character varying(255),
    title uuid NOT NULL
);


ALTER TABLE public.attractions OWNER TO devpav;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO devpav;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO devpav;

--
-- Name: products; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.products (
    id uuid NOT NULL,
    price numeric(19,2),
    id_service uuid NOT NULL,
    attraction uuid
);


ALTER TABLE public.products OWNER TO devpav;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.roles (
    id uuid NOT NULL,
    title character varying(50)
);


ALTER TABLE public.roles OWNER TO devpav;

--
-- Name: string_values; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.string_values (
    id uuid NOT NULL,
    language uuid NOT NULL,
    value character varying(255) NOT NULL,
    translate_id uuid
);


ALTER TABLE public.string_values OWNER TO devpav;

--
-- Name: tags; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.tags (
    id uuid NOT NULL,
    title uuid NOT NULL
);


ALTER TABLE public.tags OWNER TO devpav;

--
-- Name: translate; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.translate (
    id uuid NOT NULL
);


ALTER TABLE public.translate OWNER TO devpav;

--
-- Name: type_service; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.type_service (
    id uuid NOT NULL,
    title uuid NOT NULL
);


ALTER TABLE public.type_service OWNER TO devpav;

--
-- Name: types; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.types (
    id uuid NOT NULL,
    title uuid NOT NULL
);


ALTER TABLE public.types OWNER TO devpav;

--
-- Name: user_profile; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.user_profile (
    id uuid NOT NULL,
    email character varying(255),
    phone character varying(255),
    price_distance integer
);


ALTER TABLE public.user_profile OWNER TO devpav;

--
-- Name: user_role; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.user_role (
    id_user uuid NOT NULL,
    id_role uuid NOT NULL
);


ALTER TABLE public.user_role OWNER TO devpav;

--
-- Name: users; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    enabled boolean NOT NULL,
    last_password_reset_date timestamp without time zone,
    login character varying(50) NOT NULL,
    password character varying(100) NOT NULL
);


ALTER TABLE public.users OWNER TO devpav;

--
-- Name: verification_tokens; Type: TABLE; Schema: public; Owner: artteam
--

CREATE TABLE public.verification_tokens (
    id bigint NOT NULL,
    expiration_date timestamp without time zone,
    token character varying(255),
    user_id uuid
);


ALTER TABLE public.verification_tokens OWNER TO devpav;

--
-- Name: verification_tokens_id_seq; Type: SEQUENCE; Schema: public; Owner: artteam
--

CREATE SEQUENCE public.verification_tokens_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.verification_tokens_id_seq OWNER TO devpav;

--
-- Name: verification_tokens_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: artteam
--

ALTER SEQUENCE public.verification_tokens_id_seq OWNED BY public.verification_tokens.id;


--
-- Name: verification_tokens id; Type: DEFAULT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.verification_tokens ALTER COLUMN id SET DEFAULT nextval('public.verification_tokens_id_seq'::regclass);


--
-- Data for Name: attraction_tag; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.attraction_tag (attraction, tag) FROM stdin;
ff6612e3-8406-4a4b-a446-205bf08bb239	2f6b0859-2120-4bcc-9bc1-bec3bc545561
\.


--
-- Data for Name: attraction_type; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.attraction_type (attraction, type) FROM stdin;
ff6612e3-8406-4a4b-a446-205bf08bb239	2fa0b7fc-1bb4-4f51-a1d7-b7388d9e84e7
\.


--
-- Data for Name: attractions; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.attractions (id, image, link_source, title) FROM stdin;
ff6612e3-8406-4a4b-a446-205bf08bb239	http://image.jpg	http://image.jpg	a79d0474-d022-421c-9a20-273163bc75ca
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.products (id, price, id_service, attraction) FROM stdin;
5ecf84f7-a45b-48a2-bcb5-67dae451efe1	246673.00	bf7dd3a4-05e7-4805-9694-44a9cd4dffe2	ff6612e3-8406-4a4b-a446-205bf08bb239
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.roles (id, title) FROM stdin;
15ab8ed6-651c-42b4-89d6-62cf99e6dbb4	ROLE_ADMIN
\.


--
-- Data for Name: string_values; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.string_values (id, language, value, translate_id) FROM stdin;
17f0bc34-eae6-4d2f-b0cd-c8972ccdbbc6	064e54e0-602a-49c2-a99d-d7eabd4169d7	Спорт	3e081032-9edf-41b8-9b74-fccd639a79ba
2b7e30a7-844b-489d-990a-07f57ce39fd7	36602afd-1a67-4a21-8b0f-e9cb54ff5e05	Sport	3e081032-9edf-41b8-9b74-fccd639a79ba
bd608470-c9ff-41a7-8475-bb3959345bd0	064e54e0-602a-49c2-a99d-d7eabd4169d7	new service	589d3c31-437c-4db7-b39e-4bca879e555f
98e87bf8-65a0-425d-8057-cc6a69c394db	36602afd-1a67-4a21-8b0f-e9cb54ff5e05	Новый сервис	589d3c31-437c-4db7-b39e-4bca879e555f
b3f3209f-7db4-419a-aae1-1e3352900ad7	064e54e0-602a-49c2-a99d-d7eabd4169d7	New Year 2019	1107cc5d-5c5a-4443-868d-55c5be42b5bf
775320aa-3427-4324-b7c6-b364747d762f	36602afd-1a67-4a21-8b0f-e9cb54ff5e05	Новый год 2019	1107cc5d-5c5a-4443-868d-55c5be42b5bf
194a1148-02fc-4edb-84e2-efc5906ea24c	064e54e0-602a-49c2-a99d-d7eabd4169d7	New Year 2019	a79d0474-d022-421c-9a20-273163bc75ca
27533e43-d5aa-4f47-9c46-55cd50cb54c1	36602afd-1a67-4a21-8b0f-e9cb54ff5e05	Новый год 2019	a79d0474-d022-421c-9a20-273163bc75ca
\.


--
-- Data for Name: tags; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.tags (id, title) FROM stdin;
2f6b0859-2120-4bcc-9bc1-bec3bc545561	1107cc5d-5c5a-4443-868d-55c5be42b5bf
\.


--
-- Data for Name: translate; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.translate (id) FROM stdin;
3e081032-9edf-41b8-9b74-fccd639a79ba
589d3c31-437c-4db7-b39e-4bca879e555f
1107cc5d-5c5a-4443-868d-55c5be42b5bf
a79d0474-d022-421c-9a20-273163bc75ca
\.


--
-- Data for Name: type_service; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.type_service (id, title) FROM stdin;
bf7dd3a4-05e7-4805-9694-44a9cd4dffe2	589d3c31-437c-4db7-b39e-4bca879e555f
\.


--
-- Data for Name: types; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.types (id, title) FROM stdin;
2fa0b7fc-1bb4-4f51-a1d7-b7388d9e84e7	3e081032-9edf-41b8-9b74-fccd639a79ba
\.


--
-- Data for Name: user_profile; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.user_profile (id, email, phone, price_distance) FROM stdin;
a4486ad2-3879-4afd-98f6-7feafeafb109	jsdeveloper@yahoo.com	+375299479630	65
\.


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.user_role (id_user, id_role) FROM stdin;
a4486ad2-3879-4afd-98f6-7feafeafb109	15ab8ed6-651c-42b4-89d6-62cf99e6dbb4
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.users (id, enabled, last_password_reset_date, login, password) FROM stdin;
a4486ad2-3879-4afd-98f6-7feafeafb109	t	2018-11-13 17:16:30.802	admin	$2a$04$eq8a2soxlIEga/BrF20lO.HsSWXsjlpUTY0r6YiY.MARNLgCgc0pa
\.


--
-- Data for Name: verification_tokens; Type: TABLE DATA; Schema: public; Owner: artteam
--

COPY public.verification_tokens (id, expiration_date, token, user_id) FROM stdin;
\.


--
-- Name: verification_tokens_id_seq; Type: SEQUENCE SET; Schema: public; Owner: artteam
--

SELECT pg_catalog.setval('public.verification_tokens_id_seq', 1, false);


--
-- Name: attractions attractions_pkey; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.attractions
    ADD CONSTRAINT attractions_pkey PRIMARY KEY (id);


--
-- Name: databasechangeloglock pk_databasechangeloglock; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: string_values string_values_pkey; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.string_values
    ADD CONSTRAINT string_values_pkey PRIMARY KEY (id);


--
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- Name: translate translate_pkey; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.translate
    ADD CONSTRAINT translate_pkey PRIMARY KEY (id);


--
-- Name: type_service type_service_pkey; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.type_service
    ADD CONSTRAINT type_service_pkey PRIMARY KEY (id);


--
-- Name: types types_pkey; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.types
    ADD CONSTRAINT types_pkey PRIMARY KEY (id);


--
-- Name: users uk_ow0gan20590jrb00upg3va2fn; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_ow0gan20590jrb00upg3va2fn UNIQUE (login);


--
-- Name: user_profile user_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.user_profile
    ADD CONSTRAINT user_profile_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: verification_tokens verification_tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.verification_tokens
    ADD CONSTRAINT verification_tokens_pkey PRIMARY KEY (id);


--
-- Name: types fk1cq1rf2wy2khmdaceyywkvrqb; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.types
    ADD CONSTRAINT fk1cq1rf2wy2khmdaceyywkvrqb FOREIGN KEY (title) REFERENCES public.translate(id);


--
-- Name: user_role fk2yqlxhjhgilevh7qvt2ve6udh; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fk2yqlxhjhgilevh7qvt2ve6udh FOREIGN KEY (id_role) REFERENCES public.roles(id);


--
-- Name: verification_tokens fk54y8mqsnq1rtyf581sfmrbp4f; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.verification_tokens
    ADD CONSTRAINT fk54y8mqsnq1rtyf581sfmrbp4f FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: products fk7lqagw1qny7cikooc914cmi4m; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk7lqagw1qny7cikooc914cmi4m FOREIGN KEY (attraction) REFERENCES public.attractions(id);


--
-- Name: attraction_tag fk8nsttgdubm36fkutru96bp5po; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.attraction_tag
    ADD CONSTRAINT fk8nsttgdubm36fkutru96bp5po FOREIGN KEY (tag) REFERENCES public.tags(id);


--
-- Name: products fkb0i5sdu7n6vurqs54vqxyic2v; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkb0i5sdu7n6vurqs54vqxyic2v FOREIGN KEY (id_service) REFERENCES public.type_service(id);


--
-- Name: attractions fkb5ek6e3tybwdyim3j4vlmmtgd; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.attractions
    ADD CONSTRAINT fkb5ek6e3tybwdyim3j4vlmmtgd FOREIGN KEY (title) REFERENCES public.translate(id);


--
-- Name: tags fkcf1v0sjodvyv3kkoh42hkfxw3; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT fkcf1v0sjodvyv3kkoh42hkfxw3 FOREIGN KEY (title) REFERENCES public.translate(id);


--
-- Name: type_service fki23eflj82kllsa94otgjvaqk; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.type_service
    ADD CONSTRAINT fki23eflj82kllsa94otgjvaqk FOREIGN KEY (title) REFERENCES public.translate(id);


--
-- Name: attraction_type fkioass2blokum8layldxs8agee; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.attraction_type
    ADD CONSTRAINT fkioass2blokum8layldxs8agee FOREIGN KEY (attraction) REFERENCES public.attractions(id);


--
-- Name: attraction_tag fkju79re617ev0f1qsahg6gypyo; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.attraction_tag
    ADD CONSTRAINT fkju79re617ev0f1qsahg6gypyo FOREIGN KEY (attraction) REFERENCES public.attractions(id);


--
-- Name: attraction_type fknnp7kkvsdt4v83xwdia3ojwub; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.attraction_type
    ADD CONSTRAINT fknnp7kkvsdt4v83xwdia3ojwub FOREIGN KEY (type) REFERENCES public.types(id);


--
-- Name: user_role fkr53t650tbjk5yipcm228wf1nc; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fkr53t650tbjk5yipcm228wf1nc FOREIGN KEY (id_user) REFERENCES public.users(id);


--
-- Name: string_values fksnt5nmoqr6j2mjeu7ae61bc5p; Type: FK CONSTRAINT; Schema: public; Owner: artteam
--

ALTER TABLE ONLY public.string_values
    ADD CONSTRAINT fksnt5nmoqr6j2mjeu7ae61bc5p FOREIGN KEY (translate_id) REFERENCES public.translate(id);


--
-- PostgreSQL database dump complete
--

