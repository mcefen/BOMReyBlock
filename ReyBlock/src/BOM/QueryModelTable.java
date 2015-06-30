package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class QueryModelTable extends AbstractTableModel {

    private int numfilas;
    private String status = "";
    private Object[][] data;

    // Variables de base de datos 
    private Statement st = DataBaseClass.getSt();
    private Connection connection = DataBaseClass.getConnection();
    private ResultSet resultSet = DataBaseClass.getResultSet();
    private ResultSetMetaData metaData = DataBaseClass.getMetaData();

    private String[] columnNames;

    public QueryModelTable(String consulta, String stat) {
        status = stat;
        /*
         Para ciertas tablas que quiero un nombre adecuado
         hare una excepcion y me ire a un metodo que hara nombres especiales. 
         */
        setSpecialNames(stat);
        setQuery(consulta);

    }
    /*
     Metodo que se dedica a ponerle nombre a las tablas, 
     que quiera un nombre especifico. 
     */

    public void setSpecialNames(String kind) {
        /*
         * Tabla de descuentos de los trabajadores de la empresa 
         */
        if (kind.equalsIgnoreCase("Descuentos")) {
            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Descuento </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Apellido </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha </td></tr><tr><td align=\"center\"> Descuento </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Motivo </td></tr><tr><td align=\"center\"> Descuento </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Monto </td></tr><tr><td align=\"center\"> Descuento </td> </tr></table></center></html>"};

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

        } else if (kind.equalsIgnoreCase("reporteProduccion")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Block </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Resistencia del </td></tr><tr><td align=\"center\"> Block </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad Blocks </td></tr><tr><td align=\"center\"> Producidos </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Quebrados al </td></tr><tr><td align=\"center\"> Producir </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Quebrados al </td></tr><tr><td align=\"center\"> Sacar </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad Blocks </td></tr><tr><td align=\"center\"> Segunda </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad Blocks </td></tr><tr><td align=\"center\"> Perdidos </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad Blocks </td></tr><tr><td align=\"center\"> Vendidos  </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad Blocks </td></tr><tr><td align=\"center\"> Existentes </td> </tr></table></center></html>"

            };

        } else if (kind.equalsIgnoreCase("reporteProducciones")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Block </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Resistencia del </td></tr><tr><td align=\"center\"> Block </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad Blocks </td></tr><tr><td align=\"center\"> Producidos </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Quebrados al </td></tr><tr><td align=\"center\"> Producir </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Quebrados al </td></tr><tr><td align=\"center\"> Sacar </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad Blocks </td></tr><tr><td align=\"center\"> Segunda </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad Blocks </td></tr><tr><td align=\"center\"> Perdidos </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad Blocks </td></tr><tr><td align=\"center\"> Vendidos  </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad Blocks </td></tr><tr><td align=\"center\"> Existentes </td> </tr></table></center></html>"
  
            };

        }else if (kind.equalsIgnoreCase("reporteMateria")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Materia Prima </td> </tr></table></center></html>",
                "Presentación",
                "<html><center><table><tr><td align=\"center\" > Cantidad </td></tr><tr><td align=\"center\"> Entrante </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad </td></tr><tr><td align=\"center\"> Saliente </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad </td></tr><tr><td align=\"center\"> Existente </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Precio Unitario </td></tr><tr><td align=\"center\"> (Promedio) </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Total en </td></tr><tr><td align=\"center\"> Quetzales (Entrante)</td> </tr></table></center></html>"

            };

        } else if (kind.equalsIgnoreCase("reporteMolde")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Molde </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad de </td></tr><tr><td align=\"center\"> Block Hechos </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Meses </td></tr><tr><td align=\"center\"> Utilizado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha de compra </td></tr><tr><td align=\"center\"> Molde </td> </tr></table></center></html>"

            };

        } else if (kind.equalsIgnoreCase("reporteSueldos")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Apellido </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Puesto </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Sueldo  </td></tr><tr><td align=\"center\"> Base </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Sueldo </td></tr><tr><td align=\"center\"> Acumulado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Bonificacion </td></tr><tr><td align=\"center\"> Acumulado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Horas </td></tr><tr><td align=\"center\"> Extra </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Total </td></tr><tr><td align=\"center\"> Devengado </td> </tr></table></center></html>",
                "Descuentos",
                "IGSS",
                "<html><center><table><tr><td align=\"center\" > Total </td></tr><tr><td align=\"center\"> Descuentos </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Total </td></tr><tr><td align=\"center\"> a Recibir </td> </tr></table></center></html>"

            };
        } else if (kind.equalsIgnoreCase("work")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Apellido </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha </td></tr><tr><td align=\"center\"> Produccion </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Cantidad de </td></tr><tr><td align=\"center\"> Blocks </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Codigo de  </td></tr><tr><td align=\"center\"> Produccion </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Sueldo </td></tr><tr><td align=\"center\"> Acumulado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Bonificacion </td></tr><tr><td align=\"center\"> Acumulado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Total en </td></tr><tr><td align=\"center\"> Quetzales </td> </tr></table></center></html>"

            };

        } else if (kind.equalsIgnoreCase("work2")) {

            columnNames = new String[]{
                "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Apellido </td></tr><tr><td align=\"center\"> Empleado </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Fecha </td></tr><tr><td align=\"center\"> Viaje </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Viaje </td> </tr></table></center></html>",
                "<html><center><table><tr><td align=\"center\" > Total en </td></tr><tr><td align=\"center\"> Quetzales </td> </tr></table></center></html>"

            };

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

        }
    }

    // declara la consulta
    public void setQuery(String consulta) {
        try {
            st = (Statement) connection.createStatement();
            //especifica la consulta y la ejecuta
            resultSet = (ResultSet) st.executeQuery(consulta);

            // obtiene los datos de resultSet
            metaData = (ResultSetMetaData) resultSet.getMetaData();

            // determina el final de filas
            resultSet.last(); // se mueve a la ultima fila
            numfilas = resultSet.getRow(); // la cantidad de filas

            // estructura de la tabla cambiada
            data = new Object[metaData.getColumnCount()][numfilas];
            resultSet.beforeFirst();

            int j = 0;
            while (resultSet.next()) {
                for (int i = 0; i < metaData.getColumnCount(); i++) {

                    if (status.equalsIgnoreCase("descuentos") || status.equalsIgnoreCase("HorasExtras")) {
                        if (i == 4) {
                            data[i][j] = "<html><body><pre><p style='width:200px;'>" + resultSet.getString(5) + "</p></pre></body></html>";  // esto de aqui es para cuando sea un texto grande. 
                        } else {
                            data[i][j] = resultSet.getObject(i + 1); // normalmente
                        }
                    } else if (status.equalsIgnoreCase("Ingreso de Material")) {
                        if (i == 14) {
                            data[i][j] = "<html><body><pre><p style='width:200px;'>" + resultSet.getString(15) + "</p></pre></body></html>";  // esto de aqui es para cuando sea un texto grande. 
                        } else {
                            data[i][j] = resultSet.getObject(i + 1); // normalmente
                        }
                    } else {
                        data[i][j] = resultSet.getObject(i + 1);
                    }
                }
                j++;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la tabla setQuery \n " + ex, "Error en tabla", 0);

        }
    }
    /*  Lo que hago es que mando a llamar siempre al columnwidth, asi el
     se encarga de darle el tamaño a la columna . */

    public int getColumnWidth(int col) {
        if (status.equalsIgnoreCase("horasExtras")) {
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
        } else if (status.equalsIgnoreCase("Descuentos")) {
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
        } else if (status.equalsIgnoreCase("reporteSueldos")) {
            if (col == 0) {
                return 127;
            } else if (col == 1) {
                return 121;
            } else if (col == 2) {
                return 129;
            } else if (col == 3) {
                return 84;
            } else if (col == 4) {
                return 81;
            } else if (col == 5) {
                return 88;
            } else if (col == 6) {
                return 75;
            } else if (col == 7) {
                return 89;
            } else if (col == 8) {
                return 96;
            } else if (col == 9) {
                return 103;
            } else if (col == 10) {
                return 97;
            } else if (col == 11) {
                return 98;
            } else {
                return 0;
            }
        } else {
            return 150;
        }

    }

    /*
     Aqui empiezan metodos de la interfaz. 
     */
    /*
     Devuelve si la celda es editable o no. En este caso como todas las 
     tablas son de querys entonces no seran editables. 
     */
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    // tipo de columna
    @Override
    public Class getColumnClass(int column) {

        // determina la clase de la columna 
        try {

            String className = metaData.getColumnClassName(column + 1);
            // devuelve el tipo que es la columna
            return Class.forName(className);

        } catch (Exception exception) {

            JOptionPane.showMessageDialog(null, "Error en la tabla getColumnClass \n " + exception, "Error en tabla" + status, 0);
        }

        // si hay problemas o no la reconoce seria tipo object qee seria la imagen
        return Object.class;
    }

    // metodo para saber el numero de columnas
    @Override
    public int getColumnCount() {
        // determine number of columns
        try {
            return metaData.getColumnCount();

        } catch (SQLException sqlException) {

            JOptionPane.showMessageDialog(null, "Error en la tabla getColumnCount \n " + sqlException, "Error en tabla" + status, 0);

        }
        // si no hay columnas regresara 0 columnas
        return 0;
    }

// metodo para el nombre de cada columna
    @Override
    public String getColumnName(int column) {
        if (!status.equalsIgnoreCase("")) {  // Si no es vacio, le asigno el valor de las columnas que quiero
            return columnNames[column];
        } else {
            //determina el nombre OJO miremos qee estamos usando un objeto de la clase metaData
            try {

                return metaData.getColumnName(column + 1);

            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Error en la tabla getColumnName \n " + sqlException, "Error en tabla" + status, 0);
            }

            //si no tiene nombre enviara "" es decir un string vacio
            return "";
        }
    }

//metodo para saber el numero de filas
    public int getRowCount() {
        return numfilas; // no va en un try and catch por qee no ocasionara un problema
    }

    // obtener el valor de la fila y columan
    public Object getValueAt(int row, int column) {

        return data[column][row];
    }

    public void setValueAt(Object value, int row, int col) {
        data[col][row] = value;
        fireTableCellUpdated(row, col);

    }
}
