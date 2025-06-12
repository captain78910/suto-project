CREATE TABLE IF NOT EXISTS public.tb_trn_store
(
    store_latitude double precision,
    store_longitude double precision,
    user_id character varying(10) COLLATE pg_catalog."default" NOT NULL,
    place_id character varying(500) COLLATE pg_catalog."default" NOT NULL,
    memo character varying(500) COLLATE pg_catalog."default",
    visit boolean,
    evalation integer,
    create_user character varying(10) COLLATE pg_catalog."default",
    create_date timestamp without time zone,
    update_user character varying(10) COLLATE pg_catalog."default",
    update_date timestamp without time zone,
    store_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT tb_trn_store_pkey PRIMARY KEY (place_id, user_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_trn_store
    OWNER to postgres;