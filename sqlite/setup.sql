DROP TABLE IF EXISTS RemoteAccountTransaction;
DROP TABLE IF EXISTS RemoteAccount;
DROP TABLE IF EXISTS AccountTransaction;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS User;

CREATE TABLE RemoteAccount (
  id INTEGER PRIMARY KEY,
  bank_name VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  balance REAL NOT NULL
);
  
 CREATE TABLE RemoteAccountTransaction(
  id INTEGER PRIMARY KEY,
  account_id INTEGER NOT NULL,
  date INTEGER NOT NULL,
  amount REAL NOT NULL,
  type VARCHAR(255),
  source_id INTEGER,
  destination_id INTEGER,
  FOREIGN KEY(account_id) REFERENCES RemoteAccount(id)
);
	
CREATE TABLE User(
	id INTEGER PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	username VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255)
);

CREATE TABLE Account (
  id INTEGER PRIMARY KEY,
  user_id INTEGER NOT NULL,
  bank_name VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  balance REAL NOT NULL,
  FOREIGN KEY(user_id) REFERENCES User(id)
);

CREATE TABLE AccountTransaction(
  id INTEGER PRIMARY KEY,
  account_id INTEGER NOT NULL,
  date INTEGER NOT NULL,
  amount REAL NOT NULL,
  type VARCHAR(255),
  category VARCHAR(255),
  source_id INTEGER,
  destination_id INTEGER,
  FOREIGN KEY(account_id) REFERENCES Account(id)
);

INSERT INTO RemoteAccount (
    id,
    bank_name,
    type,
    balance
  )
  VALUES
  (
    1,
    'TD',
    'Checking',
    15823.12
  ),
  (
    2,
    'BMO',
    'Savings',
    55135.123
  ),
  (
    3,
    'Desjardins',
    'Checking',
    1312.12
  ),
  (
    4,
    'SocGen',
    'Checking',
    48572.95
  ),
  (
    5,
    'Desjardins',
    'Savings',
    0.00
  ),
  (
    6,
    'Capital One',
    'Checking',
    -800
  );


INSERT INTO RemoteAccountTransaction (
    id,
    account_id,
    date,
    amount,
    type,
    source_id,
    destination_id
  )
	VALUES
	(
    1,
    1,
    1517091082,
    52.2,
    'Transfer',
    NULL,
    2
  ),
	(
	2,
    1,
    1517099082,
    232,
    'Transfer',
    NULL,
    3
  ),
	(
    3,
    2,
    1517095342,
    142.12,
    'Deposit',
    2,
    NULL
	),
	(
    4,
    2,
    1522023463,
    5334.12,
    'Transfer',
    NULL,
    3
    ),
    (
    5,
    3,
    1520342525,
    2342.12,
    'Deposit',
    2,
    NULL
    ),
    (
    6,
    5,
    1510342525,
    50.21,
    'Deposit',
    1,
    NULL
    ),
    (
    7,
    6,
    1510342525,
    50.21,
    'Deposit',
    1,
    NULL
    ),
    (
    8,
    4,
    1500342525,
    11000.12,
    'Deposit',
    6,
    NULL
    ),
    (
    9,
    2,
    1510342525,
    123.123,
    'Transfer',
    NULL,
    1
    );


INSERT INTO User(
    id,
    first_name,
    last_name,
    username,
    password,
    email,
    address,
    phone
  )
	VALUES
	(
    1,
		'Hrachya',
		'Hakobyan',
		'admin',
		'admin',
        'sample@email.com',
        'address',
        '111111'
	),
	(
	  2,
		'Marc',
		'Dube',
		'madube',
		'admin',
        'sample@email.com',
        'address',
        '222222'
	);