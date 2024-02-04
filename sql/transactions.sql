-- public.transactions definition

-- Drop table

-- DROP TABLE public.transactions;

CREATE TABLE public.transactions (
	transaction_id bigserial NOT NULL,
	user_id int8 NULL,
	amount numeric(15, 2) NOT NULL,
	admin_fee numeric(15, 2) NULL,
	transaction_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	transfer_id int4 NULL,
	transaction_type varchar(20) NOT NULL,
	channel varchar(50) NULL,
	memo text NULL,
	CONSTRAINT transactions_pkey PRIMARY KEY (transaction_id)
);


-- public.transactions foreign keys

ALTER TABLE public.transactions ADD CONSTRAINT transactions_transfer_id_fkey FOREIGN KEY (transfer_id) REFERENCES public.transfers(transfer_id);
ALTER TABLE public.transactions ADD CONSTRAINT transactions_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);