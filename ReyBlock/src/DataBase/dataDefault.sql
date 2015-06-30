SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- --------------------------------------------------------
-- Insertar los tipos de peridas de block en la produccion 
-- --------------------------------------------------------
USE durablock;

INSERT INTO perdida_block (etiqueta_perdida) values ('Quebrados al producir');
INSERT INTO perdida_block (etiqueta_perdida) values ('Quebrados al sacar');
INSERT INTO perdida_block (etiqueta_perdida) values ('Blocks de Segunda ');

-- -----------------------------------------------------
-- Insertar los valores iniciales de cofiguraciones 
-- color blanco y resto null
-- -----------------------------------------------------

INSERT INTO configuracion VALUES(0,'255','255', '255', 'No Name', ' ', ' ', null);

-- --------------------------------------------------------
-- Insertar los tipos de gastos de la empresa 
-- --------------------------------------------------------

INSERT INTO tipo_gasto (etiqueta_gasto) VALUES ('Vehiculo');
INSERT INTO tipo_gasto (etiqueta_gasto) VALUES ('Molde');
INSERT INTO tipo_gasto (etiqueta_gasto) VALUES ('Agua');
INSERT INTO tipo_gasto (etiqueta_gasto) VALUES ('Luz');
INSERT INTO tipo_gasto (etiqueta_gasto) VALUES ('Administrativos');
INSERT INTO tipo_gasto (etiqueta_gasto) VALUES ('Seguridad');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
