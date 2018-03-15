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

INSERT INTO Account(
  id,
  user_id,
  bank_name,
  type,
  balance
)
VALUES(
    1,
    1,
    'TD',
    'Checking',
    15823.12
  ),
  (
    20,
    2,
    'SocGen',
    'Checking',
    48572.95
  ),
  (
    21,
    2,
    'Desjardins',
    'Visa',
    0.00
  );

INSERT INTO AccountTransaction(
      id,
      account_id,
      date,
      amount,
      type,
      category,
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
    	'Rent',
    	NULL,
    	2
    ),
    (
    	2,
      1,
      1517099082,
      232,
      'Transfer',
      'Leisure',
      NULL,
      3
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
    20,
    'SocGen',
    'Checking',
    48572.95
  ),
  (
    21,
    'Desjardins',
    'Visa',
    0.00
  ),
  (
    22,
    'Capital One',
    'Mastercard',
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