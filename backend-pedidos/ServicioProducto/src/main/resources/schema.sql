DROP TABLE IF EXISTS producto;

CREATE TABLE IF NOT EXISTS producto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio NUMERIC NOT NULL,
    descripcion VARCHAR(100),
    stock INTEGER NOT NULL DEFAULT 0
);

INSERT INTO producto (nombre, precio, descripcion, stock) VALUES
('Lapicero', 15, 'Azul', 100),
('Cuaderno', 20, 'Pequeño', 50),
('Regla', 5, 'Plástico', 200),
('Borrador', 3, 'Blanco', 150),
('Marcador', 2, 'Negro', 300),
('Tijeras', 7, 'Escolares', 80),
('Pegamento', 3, 'En barra', 120),
('Calculadora', 15, 'Básica', 60),
('Estuche', 5, 'Lona', 90),
('Crayones', 4, '12 colores', 110);