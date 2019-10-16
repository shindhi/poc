# Project bank

## Create table account
### Table script used
...
CREATE TABLE  account(
	id bigserial primary key,
	holder varchar not null,
	balance double precision not null,
	type_account varchar not null,
	loan_limit double precision not null,
	created_at timestamp not null,
	updated_at timestamp not null,
);

| id | holder | balance | type_account | loan_limit | created_at | updated_at |
|----|--------|---------|--------------|------------|------------|------------|
...

