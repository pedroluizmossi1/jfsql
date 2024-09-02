INSERT INTO modules (name, description, active, created_at, updated_at)
VALUES ('redis', 'Redis Cache', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO modules (name, description, active, created_at, updated_at)
VALUES ('scheduler', 'Scheduler Job', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO connections(id, host, port, username, password, database, db_type, active, created_at, updated_at)
VALUES(1, 'localhost', 3306, 'root', 'mysql_password', 'public_data', 'MYSQL', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO connections(id, host, port, username, password, database, db_type, active, created_at, updated_at, db_conn_mode)
VALUES (2, 'localhost', 1521, 'system', 'password', 'FREEPDB1', 'ORACLE', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SERVICE_NAME');
INSERT INTO connections(id, host, port, username, password, database, db_type, active, created_at, updated_at)
VALUES (3, 'localhost', 5433, 'postgres', 'password', 'public_data', 'POSTGRES', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO endpoints (description, endpoint, method, active, created_at, updated_at, connections_id)
VALUES ('Teste1', '/generated/teste1', 'GET', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
INSERT INTO endpoints (description, endpoint, method, active, created_at, updated_at, connections_id)
VALUES ('Teste2', '/generated/teste2', 'GET', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);
INSERT INTO endpoints (description, endpoint, method, active, created_at, updated_at, connections_id)
VALUES ('Teste3', '/generated/teste3', 'GET', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);