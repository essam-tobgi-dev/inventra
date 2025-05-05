CREATE SEQUENCE order_number_seq START 100000 INCREMENT BY 1;
ALTER TABLE orders ADD COLUMN order_number BIGINT NOT NULL DEFAULT nextval('order_number_seq');
ALTER TABLE orders ADD CONSTRAINT orders_order_number_key UNIQUE (order_number);