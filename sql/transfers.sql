-- public.transfers definition

-- Drop table

-- DROP TABLE public.transfers;

CREATE TABLE public.transfers (
	transfer_id bigserial NOT NULL,
	reference_number varchar(50) NULL,
	sender_account_number varchar(20) NULL,
	receiver_account_number varchar(20) NULL,
	receiver_bank_code varchar(10) NULL,
	amount numeric(15, 2) NOT NULL,
	transfer_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	channel varchar(50) NULL,
	memo text NULL,
	transfer_type varchar(20) NOT NULL,
	CONSTRAINT transfers_pkey PRIMARY KEY (transfer_id),
	CONSTRAINT transfers_reference_number_key UNIQUE (reference_number)
);