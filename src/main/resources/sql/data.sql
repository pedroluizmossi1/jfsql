INSERT INTO modules (name, description, active, created_at, updated_at)
VALUES ('redis', 'Redis Cache', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO modules (name, description, active, created_at, updated_at)
VALUES ('scheduler', 'Scheduler Job', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO connections(id, host, port, username, password, database, db_type, active, created_at, updated_at)
VALUES(1, 'localhost', 3306, 'root', 'mysql_password', 'public_data', 'MYSQL', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO connections(id, host, port, username, password, database, db_type, active, created_at, updated_at)
VALUES (2, 'localhost', 1521, 'system', 'oracle_password', '', 'ORACLE', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO connections(id, host, port, username, password, database, db_type, active, created_at, updated_at)
VALUES (3, 'localhost', 5433, 'postgres', 'postgres_password', 'public_data', 'POSTGRES', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO endpoints (description, endpoint, method, active, created_at, updated_at)
VALUES ('Teste1', '/generated/teste1', 'GET', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO endpoints (description, endpoint, method, active, created_at, updated_at)
VALUES ('Teste2', '/generated/teste2', 'GET', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO endpoints (description, endpoint, method, active, created_at, updated_at)
VALUES ('Teste3', '/generated/teste3', 'GET', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
