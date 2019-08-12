DROP TABLE IF EXISTS EMPLOYEES;
  
CREATE TABLE EMPLOYEES (
  id INT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(50) DEFAULT NULL,
  gender VARCHAR(10) DEFAULT NULL,
  department VARCHAR(30) DEFAULT NULL
);

DROP TABLE IF EXISTS MONITOR;
  
CREATE TABLE MONITOR (
  size INT NOT NULL,
  operation VARCHAR(50) NOT NULL,
  status VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS DEPARTMENT;
  
CREATE TABLE DEPARTMENT (
  dept_id INT NOT NULL,
  dept_name VARCHAR(50) NOT NULL,
  location VARCHAR(20)
);

INSERT INTO DEPARTMENT (dept_id, dept_name, location) VALUES
  ('101', 'Human Resource', 'Milton Keynes'),
  ('102', 'Accounts', 'London'),
  ('103', 'Payroll', 'Birmingham'),
  ('104', 'Data Innovation', 'Leicester'),
  ('105', 'Hiring', 'Bedford'),
  ('106', 'Testing', 'Louton'),
  ('107', 'Development', 'Edinburgh'),
  ('108', 'Learning', 'Dublin'),
  ('109', 'Administrator', 'Berlin'),
  ('110', 'Business Analyst', 'Ipswich'),  
  ('111', 'Director', 'Coventry');