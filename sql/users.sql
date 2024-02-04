-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	user_id bigserial NOT NULL,
	nik varchar(50) NOT NULL,
	full_name varchar(100) NOT NULL,
	email varchar(100) NULL,
	phone_number varchar(15) NULL,
	pin bpchar(6) NULL,
	account_number varchar(20) NULL,
	balance numeric(15, 2) NULL DEFAULT 0.0,
	CONSTRAINT users_account_number_key UNIQUE (account_number),
	CONSTRAINT users_nik_key UNIQUE (nik),
	CONSTRAINT users_pkey PRIMARY KEY (user_id)
);