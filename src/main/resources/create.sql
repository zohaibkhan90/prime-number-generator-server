--DROP TABLE EXECUTION IF EXISTS;

CREATE TABLE execution
(username VARCHAR(50),
timestamp TIMESTAMP,
upperrange INTEGER,
lowerrange INTEGER,
timeelapsed VARCHAR(50),
algorithm VARCHAR(25),
primes INTEGER);