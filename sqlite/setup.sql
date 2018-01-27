DROP TABLE RemoteAccountTransaction;
DROP TABLE RemoteAccount;
DROP TABLE AccountTransaction;
DROP TABLE Account;
DROP TABLE User;

CREATE TABLE RemoteAccount (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  bank_name VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  balance REAL NOT NULL,
  currency VARCHAR(255) NOT NULL
  );
  
 CREATE TABLE RemoteAccountTransaction(
 	id INTEGER AUTO_INCREMENT PRIMARY KEY,
    account_id INTEGER NOT NULL,
    date INTEGER NOT NULL,
    amount REAL NOT NULL,
    currency VARCHAR(255) NOT NULL,
    type VARCHAR(255),
    source_id INTEGER,
    destination_id INTEGER,
    FOREIGN KEY(account_id) REFERENCES RemoteAccount(id));
	
CREATE TABLE User(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL
);	
	
CREATE TABLE Account (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  user_id INTEGER NOT NULL,
  bank_name VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  balance REAL NOT NULL,
  currency VARCHAR(255) NOT NULL,
  FOREIGN KEY(user_id) REFERENCES User(id)
  );
  
CREATE TABLE AccountTransaction(
 	id INTEGER AUTO_INCREMENT PRIMARY KEY,
    account_id INTEGER NOT NULL,
    date INTEGER NOT NULL,
    amount REAL NOT NULL,
    currency VARCHAR(255) NOT NULL,
    type VARCHAR(255),
    source_id INTEGER,
    destination_id INTEGER,
    FOREIGN KEY(account_id) REFERENCES Account(id));
    
INSERT INTO RemoteAccount (
   bank_name,
   type,
   balance,
   currency)
  VALUES
  (
    "TD",
    "Checking",
    15823.12,
    "CAD"),
   (
     "BMO",
     "Savings",
     55135.123,
     "CAD"),
    (
      "Desjardins",
      "Checking",
      1312.12,
      "CAD");
    

INSERT INTO RemoteAccountTransaction (
	account_id,
	date,
	amount,
	currency,
	type,
	source_id,
	destination_id
)
	VALUES
	(
	1,
	1517091082,
	52.2,
	"CAD",
	"Transfer",
	NULL,
	2),
	(
	1,
	1517095342,
	142.12,
	"CAD",
	"Deposit",
	2,
	NULL
	);
	
INSERT INTO User(
	first_name,
	last_name,
	username,
	password
)
	VALUES(
		"Hrachya",
		"Hakobyan",
		"admin",
		"admin"
	);