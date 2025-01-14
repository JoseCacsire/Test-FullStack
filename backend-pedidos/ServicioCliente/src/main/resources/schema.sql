DROP TABLE IF EXISTS cliente;

CREATE TABLE IF NOT EXISTS cliente (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL
);

INSERT INTO cliente (nombre, correo) VALUES
('Juan', 'juan.perez@example.com'),
('María', 'maria.lopez@example.com'),
('Carlos', 'carlos.gomez@example.com'),
('Ana', 'ana.torres@example.com'),
('Pedro', 'pedro.sanchez@example.com'),
('Lucía', 'lucia.fernandez@example.com'),
('Luis', 'luis.ramirez@example.com'),
('Sofía', 'sofia.diaz@example.com'),
('Miguel', 'miguel.vargas@example.com'),
('Laura', 'laura.castillo@example.com');
