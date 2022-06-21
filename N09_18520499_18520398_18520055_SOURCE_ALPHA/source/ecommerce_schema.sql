CREATE TABLE IF NOT EXISTS Employees (
	employ_id text NOT NULL,
	full_name text NOT NULL,
	email text NOT NULL,
	password text NOT NULL,
	birth_date date NOT NULL DEFAULT NOW(),
	gender text NOT NULL,
	CONSTRAINT pk_employ_id PRIMARY KEY(employ_id)
);

CREATE TABLE IF NOT EXISTS Products (
	product_id text NOT NULL,
	name text NOT NULL,
	description text NOT NULL,
	origin text NOT NULL,
	manufacture_date date NOT NULL,
	quantity bigint NOT NULL,
	price numeric NOT NULL,
	insurance_duration int NOT NULL,
	discount_percentage double precision NOT NULL,
	CONSTRAINT pk_product_id PRIMARY KEY(product_id),
	CONSTRAINT chk_quantity CHECK (quantity >= 0),
	CONSTRAINT chk_price CHECK (price >= 0),
	CONSTRAINT chk_insurance_duration CHECK (insurance_duration >= 0),
	CONSTRAINT chk_discount_percentage CHECK (discount_percentage >= 0)
);