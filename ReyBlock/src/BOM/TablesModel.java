/**
 * ****************************************************************************
 * VirtualLibrary.java by Giovanni Rojas Mazariegos and Javier Alay
 * geovaroma@gmail.com javier.alay@gmail.com
 * 
* Model of all the tables
 * ****************************************************************************
 */
package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author giovannirojas
 */
public class TablesModel extends AbstractTableModel {

    private Object data[][] = null;
    private String[] columnNames;
    private String consulta = "";
    private String type = "";
    private int numfilas;
    private int numcolumn;
    private String parameters[];

    // Variables de la base de datos. 
    /* Jalo los valores de la base de datos para generar el sql para la tabla.  */
    private Statement st = DataBaseClass.getSt();
    private Connection connection = DataBaseClass.getConnection();
    private ResultSet resultSet = DataBaseClass.getResultSet();
    private ResultSetMetaData metaData = DataBaseClass.getMetaData();

    public TablesModel(String kind) { // Constructor del Table Model
        type = kind; // El tipo sera lo que ingrese
        buildTable(type);  // Ire a construir la tabla segun el tipo de tabla que reciba. 

        /*
         * Load Data to data vector, just need when the table 
         * receibe data of the user. 
         */
        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery(consulta);
            metaData = (ResultSetMetaData) resultSet.getMetaData();

            resultSet.last();                // estas lineas se mueven al final de la consulta 
            numfilas = resultSet.getRow();

            data = new Object[columnNames.length][numfilas];
            resultSet.beforeFirst();
            int j = 0;
            // Este ciclo se mueve en columnas y luego en filas. 
            while (resultSet.next()) {
                for (int i = 0; i < columnNames.length; i++) {

                    // Esta es para jalar datos a la tabla de forma normal, mas sin embargo si quisieramos 
                    // hacer una tabla se tendria que mandar el tipo y segun el tipo hara las cosas. 
                    if (type.equalsIgnoreCase("permiso modulos")) {

                        if (i == 0) {
                            data[i][j] = resultSet.getInt(1);
                        }
                        if (i == 1) {
                            data[i][j] = resultSet.getString(2);
                        }
                        if (i == 2) {
                            data[i][j] = resultSet.getString(3);
                        }

                    } // fin del if     
                    else if (type.equalsIgnoreCase("descuentos") || type.equalsIgnoreCase("HorasExtras")) {
                        if (i == 4) {
                            data[i][j] = "<html><body><pre><p style='width:200px;'>" + resultSet.getString(5) + "</p></pre></body></html>";  // esto de aqui es para cuando sea un texto grande. 
                        } else {
                            data[i][j] = resultSet.getObject(i + 1); // normalmente
                        }
                    } else if (type.equalsIgnoreCase("Ingreso de Material")) {
                        if (i == 14) {
                            data[i][j] = "<html><body><pre><p style='width:200px;'>" + resultSet.getString(15) + "</p></pre></body></html>";  // esto de aqui es para cuando sea un texto grande. 
                        } else {
                            data[i][j] = resultSet.getObject(i + 1); // normalmente
                        }
                    } else if (type.equalsIgnoreCase("Ingreso de otros gastos")) {
                        if (i == 3) {
                            data[i][j] = "<html><body><pre><p style='width:200px;'>" + resultSet.getString(4) + "</p></pre></body></html>";  // esto de aqui es para cuando sea un texto grande. 
                        } else {
                            data[i][j] = resultSet.getObject(i + 1); // normalmente
                        }
                    }else if (type.equalsIgnoreCase("Personal")) {
                        if (i == 12) {
                            byte[] bits = null;
                            JLabel labelnew = new JLabel();
                            bits = resultSet.getBytes(i + 1);

                            ImageIcon imagef;

                            if (bits != null) {
                                imagef = new ImageIcon(bits);
                            } else {
                                imagef = new ImageIcon(getClass().getResource("/Imagenes/user (2).png"));
                            }

                            // creare una condicion cuando la imagen sea demasiado grande
                            if (imagef.getIconWidth() > 100 || imagef.getIconHeight() > 100) {
                                ImageIcon imaescala = new ImageIcon(imagef.getImage().getScaledInstance(184, 150, Image.SCALE_AREA_AVERAGING));
                                labelnew.setIcon(imaescala);

                            } else {
                                labelnew.setIcon(imagef);
                            }

                            data[i][j] = labelnew;

                        } else if (i == 5) {

                            data[i][j] = "<html><body><pre><p style='width:200px;'>" + resultSet.getString(6) + "</p></pre></body></html>";  // esto de aqui es para cuando sea un texto grande. 

                        } else {
                            data[i][j] = resultSet.getObject(i + 1);
                        }

                    } else if (type.equalsIgnoreCase("Proveedor")) {
                        if (i == 2) {
                            data[i][j] = "<html><body><pre><p style='width:230px;'>" + resultSet.getString(3) + "</p></pre></body></html>";  // esto de aqui es para cuando sea un texto grande. 
                        } else {
                            data[i][j] = resultSet.getObject(i + 1); // normalmente
                        }
                    } else {
                        data[i][j] = resultSet.getObject(i + 1);  // Es asi como obtengo 
                        // Los valores, y seran de tipo objeto porque seran 
                        // de varios tipos, de igual manera en columnClass defino que tipo 
                        //sera. 
                    }

                }

                j++;

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos a la tabla : " + kind + "\n" + ex, "Error en base de datos", 0);
        }

    }
    /* 
     * Este constructor es necesarios cuando la consulta tiene algunos valores iniciales,
     * entonces asi se puede realizar cambios en ella. 
     */

    public TablesModel(String kind, String parameters[]) { // Constructor del Table Model
        type = kind; // El tipo sera lo que ingrese
        buildTable(type);  // Ire a construir la tabla segun el tipo de tabla que reciba. 

        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery(consulta);
            metaData = (ResultSetMetaData) resultSet.getMetaData();

            resultSet.last();                // estas lineas se mueven al final de la consulta 
            numfilas = resultSet.getRow();

            data = new Object[columnNames.length][numfilas];
            resultSet.beforeFirst();
            int j = 0;
            // Este ciclo se mueve en columnas y luego en filas. 
            while (resultSet.next()) {
                for (int i = 0; i < columnNames.length; i++) {

                    data[i][j] = resultSet.getObject(i + 1);  // Es asi como obtengo 
                    // Los valores, y seran de tipo objeto porque seran 
                    // de varios tipos, de igual manera en columnClass defino que tipo 
                    //sera. 

                }

                j++;

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos a la tabla : " + kind + "\n" + ex, "Error en base de datos", 0);
        }

    }

    public void buildTable(String kind) {

        //Voy a construir las tablas segun 
        // el tipo que me mande 
        // La tabla de los usuarios ser llamara usuarios
        if (kind.equals("usuarios")) {
            columnNames = new String[]{"Id Usuario", "Nombre", "Apellido", "email"};
            consulta = "SELECT idUsuario,Nombre,Apellido,email from usuario";
        } /*Tabla de permiso modulos. */ else if (kind.equalsIgnoreCase("permiso modulos")) {
            columnNames = new String[]{"Id Permiso", "Nombre", "Descripcion", "Acceso"};
            consulta = "Select IdPermiso,Nombre,Descripcion from permiso WHERE codigo_modulo IS NOT NULL";

        } /*
         * Tabla de descuentos de los trabajadores de la empresa 
         */ else if (kind.equalsIgnoreCase("Descuentos")) {
            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Descuento </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Apellido </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha </td></tr><tr><td align=\"center\"> Descuento </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Motivo </td></tr><tr><td align=\"center\"> Descuento </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Monto </td></tr><tr><td align=\"center\"> Descuento </td> </tr></table></center></html>"};

            consulta = "SELECT  descuento_personal.codigo, personal.nombre, personal.apellido, movimiento_personal.fecha, descuento_personal.descripcion ,movimiento_personal.total_en_q"
                    + " FROM personal, descuento_personal, movimiento_personal"
                    + " WHERE movimiento_personal.codigo_personal = personal.codigo "
                    + "     AND movimiento_personal.codigo_descuentos IS NOT NULL "
                    + "     AND movimiento_personal.codigo_descuentos = descuento_personal.codigo ORDER BY movimiento_personal.fecha;";
        } else if (kind.equalsIgnoreCase("HorasExtras")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Horas Extras </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Apellido </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha </td></tr><tr><td align=\"center\"> Hora Extra </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Descripcion </td></tr><tr><td align=\"center\"> Hora Extra </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Tarifa </td></tr><tr><td align=\"center\"> Hora Extra </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad </td></tr><tr><td align=\"center\"> Horas </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Total en  </td></tr><tr><td align=\"center\"> Quetzales </td> </tr></table></center></html>"};

            consulta = "SELECT  extras_personal.codigo, personal.nombre, personal.apellido, movimiento_personal.fecha, extras_personal.descripcion ,extras_personal.Tarifa_Hora , extras_personal.cantidad_horas,movimiento_personal.total_en_q"
                    + " FROM personal, extras_personal, movimiento_personal"
                    + " WHERE movimiento_personal.codigo_personal = personal.codigo "
                    + "     AND movimiento_personal.codigo_horas_extra IS NOT NULL "
                    + "     AND movimiento_personal.codigo_horas_extra = extras_personal.codigo ORDER BY movimiento_personal.fecha;";

        } else if (kind.equalsIgnoreCase("Personal")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Apellido </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Puesto que </td></tr><tr><td align=\"center\"> Ocupa  </td> </tr></table></center></html>",
                "DPI",
                "<html><center><table><tr><td align=\"center\" > Dirección </td></tr><tr><td align=\"center\">  Domicilio Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Telefono </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "Sueldo Base",
                "<html><center><table><tr><td align=\"center\" > Número de </td></tr><tr><td align=\"center\"> IGSS </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Sueldo Base </td></tr><tr><td align=\"center\"> (Tárifa por trabajo) </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Bonificacion </td></tr><tr><td align=\"center\"> (Tárifa por trabajo) </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Horas Extras </td></tr><tr><td align=\"center\"> (Tárifa por hora) </td> </tr></table></center></html>",
                "Foto",
                "<html><center><table><tr><td align=\"center\" > Fecha de  </td></tr><tr><td align=\"center\"> Ingreso </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha de  </td></tr><tr><td align=\"center\"> Liquidación </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha de  </td></tr><tr><td align=\"center\"> Nacimiento </td> </tr></table></center></html>",};

            consulta = "SELECT  * FROM personal ;";

        } else if (kind.equalsIgnoreCase("Materia")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Material </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Material </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Unidades por </td></tr><tr><td align=\"center\"> Segundo </td> </tr></table></center></html>",
                "Medida",
                "Color",
                "<html><center><table><tr><td align=\"center\" > Presentación del </td></tr><tr><td align=\"center\">  Material </td> </tr></table></center></html>"

            };

            consulta = "SELECT  * FROM materia ;";

        } else if (kind.equalsIgnoreCase("Molde")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Molde </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Molde </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Largo </td></tr><tr><td align=\"center\"> Molde </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Ancho </td></tr><tr><td align=\"center\"> Molde </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Alto </td></tr><tr><td align=\"center\"> Molde </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha de </td></tr><tr><td align=\"center\"> Compra </td> </tr></table></center></html>"

            };

            consulta = "SELECT  * FROM molde ;";

        } else if (kind.equalsIgnoreCase("Block")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Block </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Block </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Largo </td></tr><tr><td align=\"center\"> Block </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Ancho </td></tr><tr><td align=\"center\"> Block </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Alto </td></tr><tr><td align=\"center\"> Block </td> </tr></table></center></html>",
                "Resistencia",
                "Color"
            };

            consulta = "SELECT  * FROM Block ;";

        } else if (kind.equalsIgnoreCase("Proveedor")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Proveedor </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Proveedor </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Dirección </td></tr><tr><td align=\"center\"> Proveedor </td> </tr></table></center></html>",
                "Telefono"

            };

            consulta = "SELECT  * FROM Proveedor ;";

        } else if (kind.equalsIgnoreCase("Vehiculo")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Vehiculo </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Vehiculo </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Numero de </td></tr><tr><td align=\"center\"> Placa </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha de </td></tr><tr><td align=\"center\"> Compra </td> </tr></table></center></html>",
                "Color",
                "<html><center><table><tr><td align=\"center\" > Tipo de </td></tr><tr><td align=\"center\"> Vehiculo </td> </tr></table></center></html>"

            };

            consulta = "SELECT  * FROM Vehiculo ;";

        } else if (kind.equalsIgnoreCase("Produccion")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo de </td></tr><tr><td align=\"center\"> Producción </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad de  </td></tr><tr><td align=\"center\"> Tablas </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad de blocks </td></tr><tr><td align=\"center\"> por tablas </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha de </td></tr><tr><td align=\"center\"> Producción </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Número de </td></tr><tr><td align=\"center\"> Bachadas </td> </tr></table></center></html>"

            };

            consulta = "Select * from produccion ORDER BY Fecha";

        } else if (kind.equalsIgnoreCase("Ingreso de Material")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo de </td></tr><tr><td align=\"center\"> Viaje </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha del  </td></tr><tr><td align=\"center\"> Viaje </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Kilometraje de </td></tr><tr><td align=\"center\"> Viaje </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre de </td></tr><tr><td align=\"center\"> Proveedor </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre de </td></tr><tr><td align=\"center\"> Material </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad de </td></tr><tr><td align=\"center\"> Material </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Total en </td></tr><tr><td align=\"center\"> Quetzales (Viaje) </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Precio Unitario de </td></tr><tr><td align=\"center\"> Materia Prima </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre de </td></tr><tr><td align=\"center\"> Chofer </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Apellido de </td></tr><tr><td align=\"center\"> Chofer </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Ganancia de </td></tr><tr><td align=\"center\"> Chofer </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre de </td></tr><tr><td align=\"center\"> Vehiculo </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Placa de </td></tr><tr><td align=\"center\"> Vehiculo </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Ganancia de </td></tr><tr><td align=\"center\"> Vehiculo </td> </tr></table></center></html>",
                "Observaciones"

            };

            
            
            consulta = "SELECT viaje.codigo, viaje.fecha, viaje.kilometraje, proveedor.nombre, materia.nombre, movimiento_material.cantidad, movimiento_material.total, movimiento_material.precio_unitario, personal.nombre, personal.apellido, movimiento_personal.total_en_q, vehiculo.nombre, vehiculo.placa, movimiento_vehiculo.total_dinero, viaje.observaciones "
                    + "From viaje, proveedor, personal, materia, movimiento_material, movimiento_personal, movimiento_vehiculo, vehiculo "
                    + "WHERE viaje.Tipo_viaje like 'IngresoMaterial'"
                    + " AND viaje.codigo_proveedor = proveedor.codigo"
                    + " AND movimiento_material.codigo_material_entrante = viaje.codigo"
                    + " AND movimiento_personal.codigo_viaje = viaje.codigo"
                    + " AND movimiento_vehiculo.codigo_viaje = viaje.codigo"
                    + " AND movimiento_material.codigo_material = materia.codigo"
                    + " AND movimiento_vehiculo.codigo_vehiculo = vehiculo.codigo"
                    + " AND movimiento_personal.codigo_personal = personal.codigo"
                    + " AND movimiento_material.codigo_produccion IS NULL"
                    + " AND movimiento_material.existencia = 1" // Esto es para que sea solo los de entrada 
                    + " ORDER BY viaje.fecha DESC";

        } else if (kind.equalsIgnoreCase("Ingreso de Otros Gastos")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo de </td></tr><tr><td align=\"center\"> Gasto </td> </tr></table></center></html>",
                "Tipo de Gasto",
                "<html><center><table><tr><td align=\"center\" > Fecha </td></tr><tr><td align=\"center\"> Inicial </td> </tr></table></center></html>",
                "Observaciones",
                "Total en Dinero",
                "<html><center><table><tr><td align=\"center\" > Fecha </td></tr><tr><td align=\"center\"> Final </td> </tr></table></center></html>"

            };

            consulta = "SELECT gasto_empresa.codigo, tipo_gasto.etiqueta_gasto, gasto_empresa.fecha_inicial,gasto_empresa.observaciones, gasto_empresa.total, gasto_empresa.fecha_final "
                    + " From gasto_empresa"
                    + " INNER JOIN Tipo_gasto ON tipo_gasto.codigo = gasto_empresa.tipo_de_gasto"
                    + " WHERE gasto_empresa.tipo_de_gasto = 3 "
                    + " OR gasto_empresa.tipo_de_gasto = 4 "
                    + " OR gasto_empresa.tipo_de_gasto = 5 "
                    + " OR gasto_empresa.tipo_de_gasto = 6 "
                    + " ORDER BY gasto_empresa.fecha_inicial DESC";

        }

        numcolumn = columnNames.length; // el numero de columnas, esto lo hara siempre. 

    }

    /* ------------------------------------
     // Metodos propios de AbastracTableModel 
     * ------------------------------------ */
    @Override
    public Class getColumnClass(int col) { // Regresa el tipo de columna. 

        /* 
         * Es necesario definir el tipo de permiso, en caso 
         * que la tabla vaya a recibir datos del usuario 
         * o bien exista un campo especial como JTextArea
         * JLabel, etc. 
         */
        if (type.equals("permiso modulos")) {
            if (col == 3) {
                return Boolean.class;
            } else if (col == 0) {
                return Integer.class;
            } else {
                return String.class;
            }
        } else if (type.equalsIgnoreCase("Personal")) {
            if (col == 12) {
                return JLabel.class;
            } else {
                return String.class;
            }
        } else {
            return String.class; // el default sera String al menos de que el tipo lo cambie
        }
    }
    /*  Lo que hago es que mando a llamar siempre al columnwidth, asi el
     se encarga de darle el tamaño a la columna . */

    public int getColumnWidth(int col) {
        if (type.equalsIgnoreCase("Personal")) {
            if (col == 0) {
                return 98;
            } else if (col == 1) {
                return 252;
            } else if (col == 2) {
                return 282;
            } else if (col == 3) {
                return 151;
            } else if (col == 4) {
                return 118;
            } else if (col == 5) {
                return 270;
            } else if (col == 6) {
                return 113;
            } else if (col == 7) {
                return 112;
            } else if (col == 8) {
                return 140;
            } else if (col == 9) {
                return 147;
            } else if (col == 10) {
                return 160;
            } else if (col == 11) {
                return 147;
            } else if (col == 12) {
                return 184;
            } else if (col == 13) {
                return 114;
            } else if (col == 14) {
                return 114;
            } else if (col == 15) {
                return 114;
            } else {
                return 0;
            }
        } else if (type.equalsIgnoreCase("Produccion")) {
            if (col == 0) {
                return 127;
            } else if (col == 1) {
                return 200;
            } else if (col == 2) {
                return 207;
            } else if (col == 3) {
                return 238;
            } else if (col == 4) {
                return 187;
            } else {
                return 0;
            }
        } else if (type.equalsIgnoreCase("horasExtras")) {
            if (col == 0) {
                return 136;
            } else if (col == 1) {
                return 153;
            } else if (col == 2) {
                return 155;
            } else if (col == 3) {
                return 132;
            } else if (col == 4) {
                return 270;
            } else if (col == 5) {
                return 117;
            } else if (col == 6) {
                return 132;
            } else if (col == 7) {
                return 110;
            } else {
                return 0;
            }
        } else if (type.equalsIgnoreCase("Descuentos")) {
            if (col == 0) {
                return 136;
            } else if (col == 1) {
                return 153;
            } else if (col == 2) {
                return 155;
            } else if (col == 3) {
                return 132;
            } else if (col == 4) {
                return 270;
            } else if (col == 5) {
                return 117;
            } else {
                return 0;
            }
        } else if (type.equalsIgnoreCase("Materia")) {
            if (col == 0) {
                return 114;
            } else if (col == 1) {
                return 224;
            } else if (col == 2) {
                return 142;
            } else if (col == 3) {
                return 130;
            } else if (col == 4) {
                return 177;
            } else if (col == 5) {
                return 176;
            } else {
                return 0;
            }

        } else if (type.equalsIgnoreCase("Molde")) {
            if (col == 0) {
                return 105;
            } else if (col == 1) {
                return 365;
            } else if (col == 2) {
                return 121;
            } else if (col == 3) {
                return 114;
            } else if (col == 4) {
                return 122;
            } else if (col == 5) {
                return 136;
            } else {
                return 0;
            }
        } else if (type.equalsIgnoreCase("Block")) {
            if (col == 0) {
                return 82;
            } else if (col == 1) {
                return 394;
            } else if (col == 2) {
                return 129;
            } else if (col == 3) {
                return 106;
            } else if (col == 4) {
                return 108;
            } else if (col == 5) {
                return 142;
            } else if (col == 6) {
                return 142;
            } else {
                return 0;
            }

        } else if (type.equalsIgnoreCase("Proveedor")) {
            if (col == 0) {
                return 93;
            } else if (col == 1) {
                return 406;
            } else if (col == 2) {
                return 320;
            } else if (col == 3) {
                return 143;
            } else {
                return 0;
            }

        } else if (type.equalsIgnoreCase("Vehiculo")) {
            if (col == 0) {
                return 81;
            } else if (col == 1) {
                return 331;
            } else if (col == 2) {
                return 136;
            } else if (col == 3) {
                return 125;
            } else if (col == 4) {
                return 155;
            } else if (col == 5) {
                return 204;
            } else {
                return 0;
            }
        } else if (type.equalsIgnoreCase("Ingreso de Material")) {
            if (col == 0) {
                return 86;
            } else if (col == 1) {
                return 106;
            } else if (col == 2) {
                return 109;
            } else if (col == 3) {
                return 108;
            } else if (col == 4) {
                return 136;
            } else if (col == 5) {
                return 106;
            } else if (col == 6) {
                return 141;
            } else if (col == 7) {
                return 137;
            } else if (col == 8) {
                return 209;
            } else if (col == 9) {
                return 200;
            } else if (col == 10) {
                return 98;
            } else if (col == 11) {
                return 154;
            } else if (col == 12) {
                return 135;
            } else if (col == 13) {
                return 100;
            } else if (col == 14) {
                return 270;
            } else {
                return 0;
            }

        } else if (type.equalsIgnoreCase("Ingreso de Otros Gastos")) {
            
            if (col == 0) {
                return 86;
            } else if (col == 1) {
                return 155;
            } else if (col == 2) {
                return 152;
            } else if (col == 3) {
                return 270;
            } else if (col == 4) {
                return 119;
            } else if (col == 5) {
                return 155;
            } else {
                return 0;
            }
        } else {
            return 200;
        }
    }

    @Override
    public String getColumnName(int col) {

        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) { // dejo que editen algunas columnas (Empieza en 0)
        if (type.equalsIgnoreCase("permiso modulos")) {
            if (col == 3) {
                return true;
            } else {
                return false;
            }
        } else {
            return false; // en default no tendra que editar nada, almenos que exista algun tipo y cambie esto. 
        }
    }

    @Override
    public int getRowCount() {

        return numfilas; // no va en un try and catch por qee no ocasionara un problema
    }

    @Override
    public int getColumnCount() {

        return numcolumn;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[col][row];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data[col][row] = value;
        fireTableCellUpdated(row, col);

    }

}
