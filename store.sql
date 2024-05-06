--
-- PostgreSQL database dump
--

-- Dumped from database version 14.9
-- Dumped by pg_dump version 14.9

-- Started on 2024-04-19 12:13:10

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

--
-- TOC entry 4 (class 2615 OID 295124)
-- Name: customers; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA customers;


ALTER SCHEMA customers OWNER TO postgres;

--
-- TOC entry 7 (class 2615 OID 295126)
-- Name: facturacion; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA facturacion;


ALTER SCHEMA facturacion OWNER TO postgres;

--
-- TOC entry 8 (class 2615 OID 295125)
-- Name: inventario; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA inventario;


ALTER SCHEMA inventario OWNER TO postgres;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3412 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 212 (class 1259 OID 295163)
-- Name: clientes; Type: TABLE; Schema: customers; Owner: postgres
--

CREATE TABLE customers.clientes (
    id_cliente integer NOT NULL,
    nombres character varying NOT NULL,
    tipo_identificacion character(1) NOT NULL,
    direccion character varying,
    telefono character varying,
    correo character varying,
    identificacion character varying NOT NULL
);


ALTER TABLE customers.clientes OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 295170)
-- Name: id_cliente_cliente_seq; Type: SEQUENCE; Schema: customers; Owner: postgres
--

CREATE SEQUENCE customers.id_cliente_cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE customers.id_cliente_cliente_seq OWNER TO postgres;

--
-- TOC entry 3413 (class 0 OID 0)
-- Dependencies: 213
-- Name: id_cliente_cliente_seq; Type: SEQUENCE OWNED BY; Schema: customers; Owner: postgres
--

ALTER SEQUENCE customers.id_cliente_cliente_seq OWNED BY customers.clientes.id_cliente;


--
-- TOC entry 221 (class 1259 OID 302678)
-- Name: factura; Type: TABLE; Schema: facturacion; Owner: postgres
--

CREATE TABLE facturacion.factura (
    id integer NOT NULL,
    num_factura character varying NOT NULL,
    fecha_emision timestamp without time zone NOT NULL,
    cliente integer NOT NULL,
    subtotal double precision NOT NULL,
    total double precision NOT NULL,
    iva double precision NOT NULL,
    sistema integer NOT NULL,
    estado_sri character varying DEFAULT 'PEND'::character varying NOT NULL
);


ALTER TABLE facturacion.factura OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 302735)
-- Name: formas_pagos_factura; Type: TABLE; Schema: facturacion; Owner: postgres
--

CREATE TABLE facturacion.formas_pagos_factura (
    id integer NOT NULL,
    factura_id integer NOT NULL,
    forma_pago_id integer NOT NULL,
    valor_pagado double precision NOT NULL
);


ALTER TABLE facturacion.formas_pagos_factura OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 302595)
-- Name: categorias; Type: TABLE; Schema: inventario; Owner: postgres
--

CREATE TABLE inventario.categorias (
    id_categoria integer NOT NULL,
    nombre character varying NOT NULL,
    estado boolean NOT NULL,
    sistema integer NOT NULL
);


ALTER TABLE inventario.categorias OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 295657)
-- Name: productos; Type: TABLE; Schema: inventario; Owner: postgres
--

CREATE TABLE inventario.productos (
    id_producto integer NOT NULL,
    nombre character varying NOT NULL,
    detalle character varying NOT NULL,
    pvp double precision NOT NULL,
    iva boolean NOT NULL,
    existente integer NOT NULL,
    codigo character varying NOT NULL,
    proveedor character varying NOT NULL,
    costo_proveedor double precision NOT NULL,
    id_categoria integer NOT NULL,
    ruta_imagen character varying,
    sistema integer
);


ALTER TABLE inventario.productos OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 295667)
-- Name: seq_producto; Type: SEQUENCE; Schema: inventario; Owner: postgres
--

CREATE SEQUENCE inventario.seq_producto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE inventario.seq_producto OWNER TO postgres;

--
-- TOC entry 3414 (class 0 OID 0)
-- Dependencies: 215
-- Name: seq_producto; Type: SEQUENCE OWNED BY; Schema: inventario; Owner: postgres
--

ALTER SEQUENCE inventario.seq_producto OWNED BY inventario.productos.id_producto;


--
-- TOC entry 222 (class 1259 OID 302701)
-- Name: documentos_electronicos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.documentos_electronicos (
    id integer NOT NULL,
    tipo_doc integer NOT NULL,
    xml_ruta character varying NOT NULL,
    clave_acceso character varying NOT NULL,
    cliente integer NOT NULL,
    autorizacion character varying,
    estado_sri integer,
    store integer,
    fecha_registro time with time zone DEFAULT now() NOT NULL,
    fecha_update time with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.documentos_electronicos OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 302640)
-- Name: formas_pagos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.formas_pagos (
    id integer NOT NULL,
    label character varying NOT NULL,
    estado boolean DEFAULT true NOT NULL,
    valor_sri character varying NOT NULL
);


ALTER TABLE public.formas_pagos OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 302710)
-- Name: next_doc_elect; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.next_doc_elect
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.next_doc_elect OWNER TO postgres;

--
-- TOC entry 3415 (class 0 OID 0)
-- Dependencies: 224
-- Name: next_doc_elect; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.next_doc_elect OWNED BY public.documentos_electronicos.id;


--
-- TOC entry 223 (class 1259 OID 302708)
-- Name: next_forma_pago; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.next_forma_pago
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.next_forma_pago OWNER TO postgres;

--
-- TOC entry 3416 (class 0 OID 0)
-- Dependencies: 223
-- Name: next_forma_pago; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.next_forma_pago OWNED BY public.formas_pagos.id;


--
-- TOC entry 217 (class 1259 OID 302622)
-- Name: stores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stores (
    id integer NOT NULL,
    nombre character varying NOT NULL,
    ruc character varying NOT NULL,
    email character varying NOT NULL,
    direccion character varying NOT NULL,
    telefono character varying NOT NULL,
    rep_legal character varying NOT NULL,
    firma character varying NOT NULL,
    logo character varying,
    estado boolean DEFAULT true NOT NULL,
    contabilidad boolean DEFAULT false NOT NULL,
    pass_firma character varying NOT NULL
);


ALTER TABLE public.stores OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 302724)
-- Name: next_store; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.next_store
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.next_store OWNER TO postgres;

--
-- TOC entry 3417 (class 0 OID 0)
-- Dependencies: 225
-- Name: next_store; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.next_store OWNED BY public.stores.id;


--
-- TOC entry 218 (class 1259 OID 302629)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    nombres character varying NOT NULL,
    "user" character varying NOT NULL,
    pass character varying NOT NULL,
    nivel integer DEFAULT 1 NOT NULL,
    estado boolean DEFAULT true NOT NULL,
    id_store integer NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 302726)
-- Name: next_user; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.next_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.next_user OWNER TO postgres;

--
-- TOC entry 3418 (class 0 OID 0)
-- Dependencies: 226
-- Name: next_user; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.next_user OWNED BY public.users.id;


--
-- TOC entry 219 (class 1259 OID 302634)
-- Name: parametros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.parametros (
    id integer NOT NULL,
    parametro character varying NOT NULL,
    estado boolean DEFAULT true NOT NULL,
    valor character varying NOT NULL
);


ALTER TABLE public.parametros OWNER TO postgres;

--
-- TOC entry 3208 (class 2604 OID 295171)
-- Name: clientes id_cliente; Type: DEFAULT; Schema: customers; Owner: postgres
--

ALTER TABLE ONLY customers.clientes ALTER COLUMN id_cliente SET DEFAULT nextval('customers.id_cliente_cliente_seq'::regclass);


--
-- TOC entry 3209 (class 2604 OID 295668)
-- Name: productos id_producto; Type: DEFAULT; Schema: inventario; Owner: postgres
--

ALTER TABLE ONLY inventario.productos ALTER COLUMN id_producto SET DEFAULT nextval('inventario.seq_producto'::regclass);


--
-- TOC entry 3222 (class 2604 OID 302713)
-- Name: documentos_electronicos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documentos_electronicos ALTER COLUMN id SET DEFAULT nextval('public.next_doc_elect'::regclass);


--
-- TOC entry 3218 (class 2604 OID 302709)
-- Name: formas_pagos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.formas_pagos ALTER COLUMN id SET DEFAULT nextval('public.next_forma_pago'::regclass);


--
-- TOC entry 3212 (class 2604 OID 302725)
-- Name: stores id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stores ALTER COLUMN id SET DEFAULT nextval('public.next_store'::regclass);


--
-- TOC entry 3213 (class 2604 OID 302727)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.next_user'::regclass);


--
-- TOC entry 3391 (class 0 OID 295163)
-- Dependencies: 212
-- Data for Name: clientes; Type: TABLE DATA; Schema: customers; Owner: postgres
--

INSERT INTO customers.clientes VALUES (9, 'Sergio Castillo Leones', '1', 'Santo domingo Ciudad Verder , MS 9 ', '0969587444', 'catmanyan@hotmail.com', '1722154802');
INSERT INTO customers.clientes VALUES (8, 'Karen Anzlues Vinces', '1', 'Quevedo , San camilo calle unidad nacional y Chile', '0969587444', 'catmanyan@hotmail.com', '1250267083');


--
-- TOC entry 3400 (class 0 OID 302678)
-- Dependencies: 221
-- Data for Name: factura; Type: TABLE DATA; Schema: facturacion; Owner: postgres
--



--
-- TOC entry 3406 (class 0 OID 302735)
-- Dependencies: 227
-- Data for Name: formas_pagos_factura; Type: TABLE DATA; Schema: facturacion; Owner: postgres
--



--
-- TOC entry 3395 (class 0 OID 302595)
-- Dependencies: 216
-- Data for Name: categorias; Type: TABLE DATA; Schema: inventario; Owner: postgres
--

INSERT INTO inventario.categorias VALUES (1, 'ALIMENTOS', true, 1);
INSERT INTO inventario.categorias VALUES (2, 'MEDICINA', true, 1);


--
-- TOC entry 3393 (class 0 OID 295657)
-- Dependencies: 214
-- Data for Name: productos; Type: TABLE DATA; Schema: inventario; Owner: postgres
--

INSERT INTO inventario.productos VALUES (1, 'prueba', 'este es un producto de prueba', 92.5, true, 100, 'adcb123', 'Crisles', 80, 1, 'https://static.vecteezy.com/system/resources/thumbnails/023/329/714/small/heart-tree-love-for-nature-red-landscape-at-sunset-generativ-ai-photo.jpg', NULL);
INSERT INTO inventario.productos VALUES (2, 'prueba ac', 'este es un producto de prueba actuali', 92.47, true, 99, 'adcb1234', 'Crisles', 81, 2, 'https://static.vecteezy.com/system/resources/thumbnails/023/329/714/small/heart-tree-love-for-nature-red-landscape-at-sunset-generativ-ai-photo.jpg', NULL);


--
-- TOC entry 3401 (class 0 OID 302701)
-- Dependencies: 222
-- Data for Name: documentos_electronicos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3399 (class 0 OID 302640)
-- Dependencies: 220
-- Data for Name: formas_pagos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.formas_pagos VALUES (1, 'SIN UTILIZACION DEL SISTEMA FINANCIERO', true, '01');
INSERT INTO public.formas_pagos VALUES (2, 'CHEQUE PROPIO', true, '02');
INSERT INTO public.formas_pagos VALUES (3, 'CHEQUE CERTIFICADO', true, '03');
INSERT INTO public.formas_pagos VALUES (4, 'CHEQUE DE GERENCIA', true, '04');
INSERT INTO public.formas_pagos VALUES (5, 'CHEQUE DEL EXTERIOR', true, '05');
INSERT INTO public.formas_pagos VALUES (6, 'DEBITO DE CUENTA', true, '06');
INSERT INTO public.formas_pagos VALUES (7, 'TRANSFERENCIA PROPIO BANCO', true, '07');
INSERT INTO public.formas_pagos VALUES (8, 'TRANSFERENCIA OTRO BANCO NACIONAL', true, '08');
INSERT INTO public.formas_pagos VALUES (9, 'TRANSFERENCIA BANCO EXTERIOR', true, '09');
INSERT INTO public.formas_pagos VALUES (10, 'TARJETA DE CREDITO NACIONAL', true, '10');
INSERT INTO public.formas_pagos VALUES (11, 'TARJETA DE CREDITO INTERNACIONAL', true, '11');
INSERT INTO public.formas_pagos VALUES (12, 'GIRO', true, '12');
INSERT INTO public.formas_pagos VALUES (13, 'DEPOSITO EN CUENTA (CORRIENTE/AHORROS)', true, '13');
INSERT INTO public.formas_pagos VALUES (14, 'ENDOSO DE INVERSION', true, '14');
INSERT INTO public.formas_pagos VALUES (15, 'COMPENSACION DE DEUDAS', true, '15');
INSERT INTO public.formas_pagos VALUES (16, 'TARJETAS DE DEBITO', true, '16');
INSERT INTO public.formas_pagos VALUES (17, 'DINERO ELECTRONICO', true, '17');
INSERT INTO public.formas_pagos VALUES (18, 'TARJETA PREPAGO', true, '18');
INSERT INTO public.formas_pagos VALUES (19, 'TARJETA DE CREDITO', true, '19');
INSERT INTO public.formas_pagos VALUES (20, 'OTROS CON UTILIZACION DEL SISTEMA FINANCIERO', true, '20');
INSERT INTO public.formas_pagos VALUES (21, 'ENDOSO DE TITULOS', true, '21');


--
-- TOC entry 3398 (class 0 OID 302634)
-- Dependencies: 219
-- Data for Name: parametros; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.parametros VALUES (1, 'IVA', true, '15');


--
-- TOC entry 3396 (class 0 OID 302622)
-- Dependencies: 217
-- Data for Name: stores; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3397 (class 0 OID 302629)
-- Dependencies: 218
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3419 (class 0 OID 0)
-- Dependencies: 213
-- Name: id_cliente_cliente_seq; Type: SEQUENCE SET; Schema: customers; Owner: postgres
--

SELECT pg_catalog.setval('customers.id_cliente_cliente_seq', 9, true);


--
-- TOC entry 3420 (class 0 OID 0)
-- Dependencies: 215
-- Name: seq_producto; Type: SEQUENCE SET; Schema: inventario; Owner: postgres
--

SELECT pg_catalog.setval('inventario.seq_producto', 2, true);


--
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 224
-- Name: next_doc_elect; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.next_doc_elect', 1, false);


--
-- TOC entry 3422 (class 0 OID 0)
-- Dependencies: 223
-- Name: next_forma_pago; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.next_forma_pago', 21, true);


--
-- TOC entry 3423 (class 0 OID 0)
-- Dependencies: 225
-- Name: next_store; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.next_store', 1, false);


--
-- TOC entry 3424 (class 0 OID 0)
-- Dependencies: 226
-- Name: next_user; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.next_user', 1, false);


--
-- TOC entry 3224 (class 2606 OID 295169)
-- Name: clientes clientes_pkey; Type: CONSTRAINT; Schema: customers; Owner: postgres
--

ALTER TABLE ONLY customers.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (id_cliente);


--
-- TOC entry 3242 (class 2606 OID 302739)
-- Name: formas_pagos_factura formas_pagos_factura_pkey; Type: CONSTRAINT; Schema: facturacion; Owner: postgres
--

ALTER TABLE ONLY facturacion.formas_pagos_factura
    ADD CONSTRAINT formas_pagos_factura_pkey PRIMARY KEY (id);


--
-- TOC entry 3228 (class 2606 OID 302601)
-- Name: categorias categorias_pkey; Type: CONSTRAINT; Schema: inventario; Owner: postgres
--

ALTER TABLE ONLY inventario.categorias
    ADD CONSTRAINT categorias_pkey PRIMARY KEY (id_categoria);


--
-- TOC entry 3226 (class 2606 OID 295663)
-- Name: productos productos_pkey; Type: CONSTRAINT; Schema: inventario; Owner: postgres
--

ALTER TABLE ONLY inventario.productos
    ADD CONSTRAINT productos_pkey PRIMARY KEY (id_producto);


--
-- TOC entry 3240 (class 2606 OID 302712)
-- Name: documentos_electronicos documentos_electronicos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documentos_electronicos
    ADD CONSTRAINT documentos_electronicos_pkey PRIMARY KEY (id);


--
-- TOC entry 3238 (class 2606 OID 302689)
-- Name: formas_pagos formas_pagos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.formas_pagos
    ADD CONSTRAINT formas_pagos_pkey PRIMARY KEY (id);


--
-- TOC entry 3236 (class 2606 OID 302662)
-- Name: parametros parametros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parametros
    ADD CONSTRAINT parametros_pkey PRIMARY KEY (id);


--
-- TOC entry 3230 (class 2606 OID 302653)
-- Name: stores stores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stores
    ADD CONSTRAINT stores_pkey PRIMARY KEY (id);


--
-- TOC entry 3232 (class 2606 OID 302731)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3234 (class 2606 OID 302655)
-- Name: users users_user_user1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_user_user1_key UNIQUE ("user") INCLUDE ("user");


--
-- TOC entry 3247 (class 2606 OID 302683)
-- Name: factura factura_cliente_fkey; Type: FK CONSTRAINT; Schema: facturacion; Owner: postgres
--

ALTER TABLE ONLY facturacion.factura
    ADD CONSTRAINT factura_cliente_fkey FOREIGN KEY (cliente) REFERENCES customers.clientes(id_cliente) NOT VALID;


--
-- TOC entry 3248 (class 2606 OID 302695)
-- Name: factura factura_sistema_fkey; Type: FK CONSTRAINT; Schema: facturacion; Owner: postgres
--

ALTER TABLE ONLY facturacion.factura
    ADD CONSTRAINT factura_sistema_fkey FOREIGN KEY (sistema) REFERENCES public.stores(id) NOT VALID;


--
-- TOC entry 3251 (class 2606 OID 302740)
-- Name: formas_pagos_factura formas_pagos_factura_forma_pago_id_fkey; Type: FK CONSTRAINT; Schema: facturacion; Owner: postgres
--

ALTER TABLE ONLY facturacion.formas_pagos_factura
    ADD CONSTRAINT formas_pagos_factura_forma_pago_id_fkey FOREIGN KEY (forma_pago_id) REFERENCES public.formas_pagos(id) NOT VALID;


--
-- TOC entry 3245 (class 2606 OID 302668)
-- Name: categorias categorias_sistema_fkey; Type: FK CONSTRAINT; Schema: inventario; Owner: postgres
--

ALTER TABLE ONLY inventario.categorias
    ADD CONSTRAINT categorias_sistema_fkey FOREIGN KEY (sistema) REFERENCES public.stores(id) NOT VALID;


--
-- TOC entry 3243 (class 2606 OID 302602)
-- Name: productos fk_categoria; Type: FK CONSTRAINT; Schema: inventario; Owner: postgres
--

ALTER TABLE ONLY inventario.productos
    ADD CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES inventario.categorias(id_categoria) NOT VALID;


--
-- TOC entry 3244 (class 2606 OID 302673)
-- Name: productos productos_sistema_fkey; Type: FK CONSTRAINT; Schema: inventario; Owner: postgres
--

ALTER TABLE ONLY inventario.productos
    ADD CONSTRAINT productos_sistema_fkey FOREIGN KEY (sistema) REFERENCES public.stores(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3249 (class 2606 OID 302714)
-- Name: documentos_electronicos documentos_electronicos_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documentos_electronicos
    ADD CONSTRAINT documentos_electronicos_cliente_fkey FOREIGN KEY (cliente) REFERENCES customers.clientes(id_cliente) NOT VALID;


--
-- TOC entry 3250 (class 2606 OID 302719)
-- Name: documentos_electronicos documentos_electronicos_store_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documentos_electronicos
    ADD CONSTRAINT documentos_electronicos_store_fkey FOREIGN KEY (store) REFERENCES public.stores(id) NOT VALID;


--
-- TOC entry 3246 (class 2606 OID 302656)
-- Name: users users_id_store_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_store_fkey FOREIGN KEY (id_store) REFERENCES public.stores(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


-- Completed on 2024-04-19 12:13:10

--
-- PostgreSQL database dump complete
--

