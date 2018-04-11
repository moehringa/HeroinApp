DROP TABLE USERS IF exists;
CREATE TABLE USERS(id INTEGER NOT NULL AUTO_INCREMENT,
              username VARCHAR(255) NOT NULL, -- everyone has a first name
              email VARCHAR(255) NOT NULL,  -- everyone has a last name
              password VARCHAR(255) NOT NULL,   -- everyone has a password
              daysClean INTEGER DEFAULT 0,
              goalDaysClean INTEGER DEFAULT 0,
              PRIMARY KEY(id)
              );


DROP TABLE FOLLOWERS if exists;
CREATE TABLE FOLLOWERS(userId INTEGER NOT NUll,
                       followingId INTEGER NOT NULL,
                       FOREIGN KEY(userId) REFERENCES USERS(id),
                       FOREIGN KEY(followingId) REFERENCES USERS(id),
                       CONSTRAINT f_id PRIMARY KEY(userId, followingId)
                       );

INSERT INTO USERS(username, email, password) VALUES('dmterk', 'myEmail@domain.com', 'myPass');
INSERT INTO USERS(username, email, password) VALUES('Eoin', 'EoinWithAnE@wheresThePrinter.com', '12345');
INSERT INTO USERS(username, email, password) VALUES('Taylor', 'pm_me_security_jobs', 'asdfghjkl');
INSERT INTO USERS(username, email, password) VALUES('Emma', 'testEmail.com', '54321');

INSERT INTO FOLLOWERS(userId, followingId) VALUES(1, 2);
INSERT INTO FOLLOWERS(userId, followingId) VALUES(1, 3);


