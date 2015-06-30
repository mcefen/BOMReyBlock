SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `durablock` DEFAULT CHARACTER SET latin1 ;
USE `durablock` ;

-- -----------------------------------------------------
-- Table `durablock`.`block`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`block` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre_block` VARCHAR(250) NULL DEFAULT NULL,
  `Largo` DOUBLE NULL DEFAULT NULL,
  `Ancho` DOUBLE NULL DEFAULT NULL,
  `Alto` DOUBLE NULL DEFAULT NULL,
  `Resistencia` INT NULL,
  `Color` VARCHAR(70) NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`descuento_personal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`descuento_personal` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NULL DEFAULT NULL,
  `Descripcion` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `durablock`.`extras_personal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`extras_personal` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NULL DEFAULT NULL,
  `Descripcion` TEXT NULL DEFAULT NULL,
  `Cantidad_Horas` INT NULL,
  `Tarifa_Hora` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `durablock`.`molde`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`molde` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(250) NULL DEFAULT NULL,
  `Largo` DOUBLE NULL DEFAULT NULL,
  `Ancho` DOUBLE NULL DEFAULT NULL,
  `Alto` DOUBLE NULL DEFAULT NULL,
  `Fecha_Compra` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`vehiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`vehiculo` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(250) NULL DEFAULT NULL,
  `Placa` VARCHAR(250) NULL DEFAULT NULL,
  `Fecha_de_Compra` DATE NULL DEFAULT NULL,
  `Color` VARCHAR(250) NULL DEFAULT NULL,
  `Tipo_Vehiculo` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`tipo_gasto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`tipo_gasto` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Etiqueta_Gasto` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `durablock`.`Configuracion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`Configuracion` (
  `idConfiguracion` INT NOT NULL AUTO_INCREMENT,
  `colorRed` VARCHAR(45) NULL DEFAULT NULL,
  `colorGreen` VARCHAR(45) NULL DEFAULT NULL,
  `colorBlue` VARCHAR(45) NULL DEFAULT NULL,
  `institutionName` VARCHAR(250) NULL DEFAULT NULL,
  `motherPath` VARCHAR(1000) NULL DEFAULT NULL,
  `motherUser` VARCHAR(1000) NULL DEFAULT NULL,
  `motherPassword` VARCHAR(250) NULL DEFAULT NULL,
  `logo` BLOB NULL,
  PRIMARY KEY (`idConfiguracion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `durablock`.`gasto_empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`gasto_empresa` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Tipo_de_Gasto` INT NULL,
  `Fecha_Inicial` DATE NULL DEFAULT NULL,
  `Observaciones` TEXT NULL DEFAULT NULL,
  `Total` DECIMAL(10,2) NULL DEFAULT NULL,
  `Codigo_Molde` INT NULL DEFAULT NULL,
  `Codigo_Vehiculo` INT NULL DEFAULT NULL,
  `Fecha_Final` DATE NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `Tipo_de_Gasto_idx` (`Tipo_de_Gasto` ASC),
  INDEX `Codigo_Molde_idx` (`Codigo_Molde` ASC),
  INDEX `Codigo_Vehiculo_idx` (`Codigo_Vehiculo` ASC),
  CONSTRAINT `GastoEmpresaCodigo_Molde`
    FOREIGN KEY (`Codigo_Molde`)
    REFERENCES `durablock`.`molde` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `GastoEmpresaCodigo_Vehiculo`
    FOREIGN KEY (`Codigo_Vehiculo`)
    REFERENCES `durablock`.`vehiculo` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `GastoEmpresaTipo_de_Gasto`
    FOREIGN KEY (`Tipo_de_Gasto`)
    REFERENCES `durablock`.`tipo_gasto` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `durablock`.`item_gasto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`item_gasto` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Codigo_Gasto` INT NULL,
  `Cantidad` INT NULL DEFAULT NULL,
  `Precio_Unitario` DECIMAL(10,2) NULL DEFAULT NULL,
  `Total_Dinero` DECIMAL(10,2) NULL DEFAULT NULL,
  `Descripcion` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `Codigo_Gasto_idx` (`Codigo_Gasto` ASC),
  CONSTRAINT `Codigo_Gasto`
    FOREIGN KEY (`Codigo_Gasto`)
    REFERENCES `durablock`.`gasto_empresa` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `durablock`.`proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`proveedor` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(250) NULL DEFAULT NULL,
  `Direccion` TEXT NULL DEFAULT NULL,
  `Telefono` INT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`viaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`viaje` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NULL DEFAULT NULL,
  `Kilometraje` DECIMAL(10,2) NULL DEFAULT NULL,
  `Tipo_Viaje` VARCHAR(250) NULL DEFAULT NULL,
  `Codigo_Proveedor` INT NULL,
  `Observaciones` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `Codigo_Proveedor_idx` (`Codigo_Proveedor` ASC),
  CONSTRAINT `Codigo_Proveedor`
    FOREIGN KEY (`Codigo_Proveedor`)
    REFERENCES `durablock`.`proveedor` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`kilometraje_vehiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`kilometraje_vehiculo` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Codigo_Vehiculo` INT NULL,
  `Codigo_Viaje` INT NULL DEFAULT NULL,
  `Kilometraje` DECIMAL(10,2) NULL DEFAULT NULL,
  `Fecha` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `Codigo_Viaje_idx` (`Codigo_Viaje` ASC),
  INDEX `Codigo_Vehiculo_idx` (`Codigo_Vehiculo` ASC),
  CONSTRAINT `Codigo_Vehiculo`
    FOREIGN KEY (`Codigo_Vehiculo`)
    REFERENCES `durablock`.`vehiculo` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `Codigo_Viaje`
    FOREIGN KEY (`Codigo_Viaje`)
    REFERENCES `durablock`.`viaje` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`materia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`materia` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NULL DEFAULT NULL,
  `Unidad_Segundo` DOUBLE NULL DEFAULT NULL,
  `Medida` VARCHAR(250) NULL DEFAULT NULL,
  `Color` VARCHAR(70) NULL DEFAULT NULL,
  `Presentacion` VARCHAR(70) NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`perdida_block`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`perdida_block` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Etiqueta_Perdida` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  UNIQUE INDEX `Etiqueta_Perdida_UNIQUE` (`Etiqueta_Perdida` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `durablock`.`produccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`produccion` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Cantidad_de_tablas` INT NULL DEFAULT NULL,
  `Cantidad_de_blocks_por_tablas` INT NULL DEFAULT NULL,
  `Fecha` DATE NULL DEFAULT NULL,
  `Bachadas` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`venta` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Codigo_Cliente` VARCHAR(45) NULL DEFAULT NULL,
  `Fecha` DATE NULL DEFAULT NULL,
  `Cantidad` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `durablock`.`movimiento_block`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`movimiento_block` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Codigo_Block` INT NULL DEFAULT NULL,
  `Cantidad` INT NULL DEFAULT NULL,
  `Codigo_Produccion` INT NULL DEFAULT NULL,
  `Codigo_Perdidas` INT NULL DEFAULT NULL,
  `Codigo_Ventas` INT NULL DEFAULT NULL,
  `Fecha` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `Codigo_Block_idx` (`Codigo_Block` ASC),
  INDEX `Codigo_ventas_idx` (`Codigo_Ventas` ASC),
  INDEX `Codigo_Produccion_idx` (`Codigo_Produccion` ASC),
  INDEX `Codigo_Perdidas_idx` (`Codigo_Perdidas` ASC),
  CONSTRAINT `Codigo_Block`
    FOREIGN KEY (`Codigo_Block`)
    REFERENCES `durablock`.`block` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `Codigo_Perdidas`
    FOREIGN KEY (`Codigo_Perdidas`)
    REFERENCES `durablock`.`perdida_block` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `Codigo_Produccion`
    FOREIGN KEY (`Codigo_Produccion`)
    REFERENCES `durablock`.`produccion` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `Codigo_ventas`
    FOREIGN KEY (`Codigo_Ventas`)
    REFERENCES `durablock`.`venta` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `durablock`.`movimiento_material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`movimiento_material` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Codigo_Material` INT NULL DEFAULT NULL,
  `Cantidad` DECIMAL(10,2) NULL DEFAULT NULL,
  `Codigo_Produccion` INT NULL,
  `Precio_Unitario` DECIMAL(10,2) NULL DEFAULT NULL,
  `Total` DECIMAL(10,2) NULL DEFAULT NULL,
  `Fecha` DATE NULL DEFAULT NULL,
  `Codigo_Material_Entrante` INT NULL DEFAULT NULL,
  `Existencia` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `Codigo_Material_idx` (`Codigo_Material` ASC),
  INDEX `Codgio_Produccion_idx` (`Codigo_Produccion` ASC),
  INDEX `Codigo_Nuevo_idx` (`Codigo_Material_Entrante` ASC),
  CONSTRAINT `MovMaterialCodgio_Produccion`
    FOREIGN KEY (`Codigo_Produccion`)
    REFERENCES `durablock`.`produccion` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `MovMaterialCodigo_Material`
    FOREIGN KEY (`Codigo_Material`)
    REFERENCES `durablock`.`materia` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `MovMaterialCodigo_Nuevo`
    FOREIGN KEY (`Codigo_Material_Entrante`)
    REFERENCES `durablock`.`viaje` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`movimiento_molde`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`movimiento_molde` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NULL DEFAULT NULL,
  `Codigo_Molde` INT NULL DEFAULT NULL,
  `Cantidad_Blocks` INT NULL DEFAULT NULL,
  `Codigo_Produccion` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `Codigo_Produccion_idx` (`Codigo_Produccion` ASC),
  INDEX `Codigo_Molde_idx` (`Codigo_Molde` ASC),
  CONSTRAINT `MovMoldeCodigo_Molde`
    FOREIGN KEY (`Codigo_Molde`)
    REFERENCES `durablock`.`molde` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `MovMoldeCodigo_Produccion`
    FOREIGN KEY (`Codigo_Produccion`)
    REFERENCES `durablock`.`produccion` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `durablock`.`personal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`personal` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(250) NULL DEFAULT NULL,
  `Apellido` VARCHAR(250) NULL DEFAULT NULL,
  `Puesto` VARCHAR(250) NULL DEFAULT NULL,
  `DPI` VARCHAR(15) NULL DEFAULT NULL,
  `Direccion` TEXT NULL DEFAULT NULL,
  `Telefono` INT NULL,
  `Sueldo_Base` DOUBLE NULL DEFAULT NULL,
  `IGSS` VARCHAR(15) NULL DEFAULT NULL,
  `Tarifa_Sueldo` DOUBLE NOT NULL,
  `Tarifa_Bonificacion` DOUBLE NULL DEFAULT NULL,
  `Tarifa_Horas_Extras` DOUBLE NULL DEFAULT NULL,
  `Foto` BLOB NULL DEFAULT NULL,
  `Fecha_Ingreso` DATE NULL DEFAULT NULL,
  `Fecha_Liquidacion` DATE NULL DEFAULT NULL,
  `Fecha_Nacimiento` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`movimiento_personal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`movimiento_personal` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Codigo_Personal` INT NULL,
  `Cantidad_Block` INT NULL DEFAULT NULL,
  `Codigo_Produccion` INT NULL DEFAULT NULL,
  `Sueldo_en_Q` DOUBLE NULL DEFAULT NULL,
  `Bonificacion_en_Q` DOUBLE NULL DEFAULT NULL,
  `Codigo_Horas_Extra` INT NULL DEFAULT NULL,
  `Codigo_Descuentos` INT NULL DEFAULT NULL,
  `Fecha` DATE NULL DEFAULT NULL,
  `Total_en_Q` DOUBLE NULL DEFAULT NULL,
  `Codigo_Viaje` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `Codigo_Personal_idx` (`Codigo_Personal` ASC),
  INDEX `Codigo_Produccion_idx` (`Codigo_Produccion` ASC),
  INDEX `Codigo_Descuentos_idx` (`Codigo_Descuentos` ASC),
  INDEX `Codigo_Horas_idx` (`Codigo_Horas_Extra` ASC),
  INDEX `Codigo_Viaje_idx` (`Codigo_Viaje` ASC),
  CONSTRAINT `MovPersonalCodigo_Descuentos`
    FOREIGN KEY (`Codigo_Descuentos`)
    REFERENCES `durablock`.`descuento_personal` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `MovPersonalCodigo_Horas`
    FOREIGN KEY (`Codigo_Horas_Extra`)
    REFERENCES `durablock`.`extras_personal` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `MovPersonalCodigo_Personal`
    FOREIGN KEY (`Codigo_Personal`)
    REFERENCES `durablock`.`personal` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `MovPersonalCodigo_Produccion`
    FOREIGN KEY (`Codigo_Produccion`)
    REFERENCES `durablock`.`produccion` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `MovPersonalCodigo_Viaje`
    FOREIGN KEY (`Codigo_Viaje`)
    REFERENCES `durablock`.`viaje` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`movimiento_vehiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`movimiento_vehiculo` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Codigo_Vehiculo` INT NULL,
  `Codigo_Viaje` INT NULL DEFAULT NULL,
  `Codigo_Gasto` INT NULL DEFAULT NULL,
  `Total_Dinero` DECIMAL(10,2) NULL DEFAULT NULL,
  `Fecha` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `MovVehiculo_Codigo_Vehiculo_idx` (`Codigo_Vehiculo` ASC),
  INDEX `MovViaje_CodigoViaje_idx` (`Codigo_Viaje` ASC),
  INDEX `MovVehiculo_CodigoGasto_idx` (`Codigo_Gasto` ASC),
  CONSTRAINT `MovVehiculo_CodigoGasto`
    FOREIGN KEY (`Codigo_Gasto`)
    REFERENCES `durablock`.`gasto_empresa` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `MovVehiculo_CodigoViaje`
    FOREIGN KEY (`Codigo_Viaje`)
    REFERENCES `durablock`.`viaje` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `MovVehiculo_Codigo_Vehiculo`
    FOREIGN KEY (`Codigo_Vehiculo`)
    REFERENCES `durablock`.`vehiculo` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `durablock`.`configuracion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`configuracion` (
  `idConfiguracion` INT(11) NOT NULL AUTO_INCREMENT,
  `colorRed` VARCHAR(45) NULL DEFAULT NULL,
  `colorGreen` VARCHAR(45) NULL DEFAULT NULL,
  `colorBlue` VARCHAR(45) NULL DEFAULT NULL,
  `institutionName` VARCHAR(250) NULL DEFAULT NULL,
  `motherPath` VARCHAR(1000) NULL DEFAULT NULL,
  `motherUser` VARCHAR(1000) NULL DEFAULT NULL,
  `motherPassword` VARCHAR(250) NULL DEFAULT NULL,
  `logo` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`idConfiguracion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `durablock`.`o_detalle_produccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`o_detalle_produccion` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `FechaReporte` DATE NULL DEFAULT NULL,
  `FechaInicial` DATE NULL DEFAULT NULL,
  `FechaFinal` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `durablock`.`o_produccion_block`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`o_produccion_block` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `Fecha_Reporte` DATE NULL DEFAULT NULL,
  `Fecha_Inicial` DATE NULL DEFAULT NULL,
  `Fecha_Final` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `durablock`.`o_item_reporte_produccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`o_item_reporte_produccion` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo_reporte_produccion` INT(11) NULL DEFAULT NULL,
  `codigo_block` INT(11) NULL DEFAULT NULL,
  `Producidos` INT(11) NULL DEFAULT '0',
  `Quebrados_Producir` INT(11) NULL DEFAULT '0',
  `Quebrados_Sacar` INT(11) NULL DEFAULT '0',
  `Segunda` INT(11) NULL DEFAULT '0',
  `Venta` INT(11) NULL DEFAULT '0',
  `Codigo_Reporte_detalle` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `o_item_reporte_produccion_o_produccion_idx` (`codigo_reporte_produccion` ASC),
  INDEX `codigo_block_o_item_reporte_produccion_idx` (`codigo_block` ASC),
  INDEX `ReporteProduccionReporteDetalleRelacionEntreEllas_idx` (`Codigo_Reporte_detalle` ASC),
  CONSTRAINT `codigo_block_o_item_reporte_produccion`
    FOREIGN KEY (`codigo_block`)
    REFERENCES `durablock`.`block` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `o_item_reporte_produccion_o_produccion`
    FOREIGN KEY (`codigo_reporte_produccion`)
    REFERENCES `durablock`.`o_produccion_block` (`codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `ReporteProduccionReporteDetalleRelacionEntreEllas`
    FOREIGN KEY (`Codigo_Reporte_detalle`)
    REFERENCES `durablock`.`o_detalle_produccion` (`codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `durablock`.`o_reporte_sueldo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`o_reporte_sueldo` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `Fecha_Reporte` DATE NOT NULL,
  `Fecha_Inicial` DATE NOT NULL,
  `Fecha_Final` DATE NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `durablock`.`o_item_reporte_sueldo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`o_item_reporte_sueldo` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `Codigo_Reporte_Sueldo` INT(11) NULL DEFAULT NULL,
  `Codigo_Personal` INT(11) NULL DEFAULT NULL,
  `Sueldo_Base` DOUBLE(10,2) NULL DEFAULT '0.00',
  `Sueldo_Acumulado` DOUBLE(10,2) NULL DEFAULT '0.00',
  `Bonificacion_Acumulado` DOUBLE(10,2) NULL DEFAULT '0.00',
  `Horas_Extras_Dinero` DOUBLE(10,2) NULL DEFAULT '0.00',
  `Cantidad_Horas` INT(11) NULL DEFAULT '0',
  `Descuentos` DOUBLE(10,2) NULL DEFAULT '0.00',
  `Codigo_Reporte_Detalle` INT(11) NULL DEFAULT NULL,
  `Codigo_Block` INT(11) NULL DEFAULT NULL,
  `Cantidad_block` INT NULL DEFAULT 0,
  `Tarifa_sueldo` DOUBLE NULL DEFAULT 0,
  `Tarifa_bonificacion` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`codigo`),
  INDEX `CodigoReporteSueldoO_idx` (`Codigo_Reporte_Sueldo` ASC),
  INDEX `CodigoReporteSueldoPersonal_idx` (`Codigo_Personal` ASC),
  INDEX `ReporteSueldoDetalleProduccionCodigoRelacion_idx` (`Codigo_Reporte_Detalle` ASC),
  INDEX `ReporteSueldoBlockCodigoRelacion_idx` (`Codigo_Block` ASC),
  CONSTRAINT `CodigoReporteSueldoO`
    FOREIGN KEY (`Codigo_Reporte_Sueldo`)
    REFERENCES `durablock`.`o_reporte_sueldo` (`codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `CodigoReporteSueldoPersonal`
    FOREIGN KEY (`Codigo_Personal`)
    REFERENCES `durablock`.`personal` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `ReporteSueldoBlockCodigoRelacion`
    FOREIGN KEY (`Codigo_Block`)
    REFERENCES `durablock`.`block` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `ReporteSueldoDetalleProduccionCodigoRelacion`
    FOREIGN KEY (`Codigo_Reporte_Detalle`)
    REFERENCES `durablock`.`o_detalle_produccion` (`codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `durablock`.`o_reporte_uso_materia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`o_reporte_uso_materia` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `Fecha_Reporte` DATE NULL DEFAULT NULL,
  `Fecha_Inicial` DATE NULL DEFAULT NULL,
  `Fecha_Final` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `durablock`.`o_item_reporte_uso_materia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `durablock`.`o_item_reporte_uso_materia` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `Codigo_Reporte_Materia` INT(11) NULL DEFAULT NULL,
  `Codigo_Material` INT(11) NULL DEFAULT NULL,
  `Entrante` DOUBLE(10,4) NULL DEFAULT '0.0000',
  `Saliente` DOUBLE(10,4) NULL DEFAULT '0.0000',
  `Precio_Unitario` DOUBLE(10,2) NULL DEFAULT '0.00',
  `Precio_Total` DOUBLE(10,2) NULL DEFAULT '0.00',
  `Cuenta` INT(11) NULL DEFAULT '0',
  `Codigo_Reporte_Detalle` INT(11) NULL DEFAULT NULL,
  `Codigo_Block` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `reporte_item_uso_materia_idx` (`Codigo_Reporte_Materia` ASC),
  INDEX `item_reporte_uso_materia_codigo_materia_idx` (`Codigo_Material` ASC),
  INDEX `itemReporteUsuReporteDetalleProduccionCodigoRelacion_idx` (`Codigo_Reporte_Detalle` ASC),
  INDEX `itemReporteUsoBlockCodigoRelacion_idx` (`Codigo_Block` ASC),
  CONSTRAINT `itemReporteUsoBlockCodigoRelacion`
    FOREIGN KEY (`Codigo_Block`)
    REFERENCES `durablock`.`block` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `itemReporteUsuReporteDetalleProduccionCodigoRelacion`
    FOREIGN KEY (`Codigo_Reporte_Detalle`)
    REFERENCES `durablock`.`o_detalle_produccion` (`codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `item_reporte_uso_materia_codigo_materia`
    FOREIGN KEY (`Codigo_Material`)
    REFERENCES `durablock`.`materia` (`Codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `reporte_item_uso_materia`
    FOREIGN KEY (`Codigo_Reporte_Materia`)
    REFERENCES `durablock`.`o_reporte_uso_materia` (`codigo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
