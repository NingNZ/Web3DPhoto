--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    cid character varying(20) NOT NULL,
    username character varying(20) NOT NULL,
    content character varying(5000) NOT NULL
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- Name: photoset; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.photoset (
    id integer NOT NULL,
    address character varying(100) NOT NULL
);


ALTER TABLE public.photoset OWNER TO postgres;

--
-- Name: userall; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.userall (
    username character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    access character(1) NOT NULL
);


ALTER TABLE public.userall OWNER TO postgres;

--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comments (cid, username, content) FROM stdin;
2	Linda	你好
3	Linda	世界
4	Mensil	beautiful
5	Mensil	Amazing
1	admin	Hello
6	Mun	你好呀，朋友
7	Linda	Heloo
8	Lemmon	HaHa~
\.


--
-- Data for Name: photoset; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.photoset (id, address) FROM stdin;
2	\\last\\src\\main\\resources\\static\\2.jpg
1	\\last\\src\\main\\resources\\static\\1.jpg
3	\\last\\src\\main\\resources\\static\\3.jpg
4	\\last\\src\\main\\resources\\static\\4.jpg
5	\\last\\src\\main\\resources\\static\\5.jpg
6	\\last\\src\\main\\resources\\static\\6.jpg
7	\\last\\src\\main\\resources\\static\\7.jpg
\.


--
-- Data for Name: userall; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.userall (username, password, access) FROM stdin;
Mensil	123	c
admin	456	m
Mun	456	c
Linda	789	c
Lemmon	abc	c
\.


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (cid);


--
-- Name: photoset photoset_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photoset
    ADD CONSTRAINT photoset_pkey PRIMARY KEY (id);


--
-- Name: userall userall_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userall
    ADD CONSTRAINT userall_pkey PRIMARY KEY (username);


--
-- PostgreSQL database dump complete
--

