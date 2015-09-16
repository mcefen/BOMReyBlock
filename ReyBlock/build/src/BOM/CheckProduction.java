/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CheckProduction extends javax.swing.JFrame {

    // Variables de base de datos 
    private Statement st = DataBaseClass.getSt();
    private Connection connection = DataBaseClass.getConnection();
    private ResultSet resultSet = DataBaseClass.getResultSet();

    // Variables para la tabla de sacadores de la produccion 
    private DiferentTable sacadoresModelTable;
    private String consultaSacadores = "Select Codigo,Nombre, Apellido, Puesto, Tarifa_Sueldo, Tarifa_Bonificacion from Personal ORDER BY PUESTO";
    private String listaNombresTablaSacadores[] = {"Codigo", "Nombre", "Apellido ", "Puesto", "Tarifa Sueldo", "Tarifa Bonifiacion", "Cantidad de Blocks ", "Sueldo en Q ", "Bonificion en Q", "Total en Q"};

    //* Variables para la tabla de responsables de produccion */
    private DiferentTable producctionModelTable;
    private String consulta = "Select Codigo, Nombre, Apellido,Puesto,Tarifa_Sueldo, Tarifa_Bonificacion from Personal  ORDER BY PUESTO ";
    private String listaNombresTabla[] = {"Codigo", "Nombre", "Apellido ", "Puesto", "Sueldo", "Bonifiacion", "Sueldo en Q ", "Bonificion en Q", "Total en Q", "Si / No"};

    // Variables para la tabla de material de produccion 
    private DiferentTable materialModelTable;
    private String consultaMaterial = "Select Codigo, Nombre, unidad_Segundo, presentacion from Materia";
    private String listaNombresTablaMaterial[] = {"Codigo", "Material ", "Unidades por Segundos ", "Segundos", "Material Utilizado", "Unidad del Material "};

    // Codigo de produccion al que se va a modificar
    private int codeToChange = -1;

    // datos para el combo box del molde. 
    private Mould dataMould[];

    // Datos del Block 
    private Block dataBlock[];

    private MaterialEntrante dataMaterialIn[];

    //Cantidad de Blocks en la produccion 
    private int cantidadBlocks = 0;
    private int blockProduccion = 0;

    // Canitdad de Bachadas de la Produccion 
    private int cantidadBachadas = 0;

    // Variable proceesGood para que sepa que la inserccion de los datos todo va bien. 
    boolean processGood = false;

    // Codigo Recien Ingresado de la produccion 
    int codigoRecienIngresado = -1;
    // Metodos 

    public CheckProduction(int num) {
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");

        initComponents();
        
        //Color 
        jPanel5.setBackground(ConfigClass.getColorApp());
        
        codeToChange = num;
        nameframe.setText("" + "Chequear produccion código : " + num);

        setData();

        // Cargo la inforamcion de la produccion. 
        Object[] data = new DataBaseClass().giveData("SELECT * FROM produccion WHERE codigo = " + codeToChange);
        ctablas.setText("" + data[1].toString());
        cblocktabla.setText("" + data[2].toString());
        // Fecha de produccion 
        try {

            calendar.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(data[3].toString())); // Fecha de ingreso

        } catch (ParseException ex) {
            Logger.getLogger(CheckMold.class.getName()).log(Level.SEVERE, null, ex);
        }
        cantidadBachadas = Integer.parseInt(data[4].toString());
        cBachadas.setText("" + cantidadBachadas);

        // Blocks quebrados 
        cproducir.setText(new DataBaseClass().giveData("SELECT movimiento_block.cantidad FROM movimiento_block, perdida_block WHERE movimiento_block.codigo_produccion =" + codeToChange + " AND movimiento_block.codigo_perdidas IS NOT NULL AND perdida_block.etiqueta_perdida LIKE 'Quebrados al producir' AND movimiento_block.codigo_perdidas = perdida_block.codigo")[0].toString());
        csacar.setText(new DataBaseClass().giveData("SELECT movimiento_block.cantidad FROM movimiento_block, perdida_block WHERE movimiento_block.codigo_produccion =" + codeToChange + " AND movimiento_block.codigo_perdidas IS NOT NULL AND perdida_block.etiqueta_perdida LIKE 'Quebrados al sacar' AND movimiento_block.codigo_perdidas = perdida_block.codigo")[0].toString());
        csegunda.setText(new DataBaseClass().giveData("SELECT movimiento_block.cantidad FROM movimiento_block, perdida_block WHERE movimiento_block.codigo_produccion =" + codeToChange + " AND movimiento_block.codigo_perdidas IS NOT NULL AND perdida_block.etiqueta_perdida LIKE 'Blocks de Segunda ' AND movimiento_block.codigo_perdidas = perdida_block.codigo")[0].toString());

        // Aqui lo que voy hacer es que voy a cambiar los datos para que cambie todo lo demas. 
        calculateBlocks();

        // Selecciono el combo box del codigo del block de la produccion. 
        data = new DataBaseClass().giveData("SELECT block.nombre_block, block.resistencia FROM movimiento_block INNER JOIN produccion ON produccion.codigo = movimiento_block.codigo_produccion INNER JOIN block ON block.codigo = movimiento_block.codigo_block WHERE produccion.codigo = " + codeToChange);
        blockBox.setSelectedItem(data[0] + " Resistencia: " + data[1]);

        // Selecciono el comobo box del molde que se uso en la produccion. 
        data = new DataBaseClass().giveData("SELECT molde.nombre, molde.fecha_compra FROM movimiento_molde INNER JOIN produccion ON produccion.codigo = movimiento_molde.codigo_produccion INNER JOIN molde ON molde.codigo = movimiento_molde.codigo_molde WHERE produccion.codigo = " + codeToChange);
        moldeBox.setSelectedItem(data[0] + " Fecha : " + data[1]);

        // Cargo las personas que fueron responsables en produccion. 
        cargarPersonasEnProduccion();
        updateData(); // Esto lo que hace es actualizar a los responsables de produccion. 
        
        // Cargo las personas que fueron sacadores 
        cargarSacadoresProduccion();
        updateSacadores(); // Actualizo Sacadores 
        
        // Cargo los segundos del material de las producciones 
        cargarSegundosMaterialProduccion();
        uploadMts3();
        
    }

    private CheckProduction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class Block {

        private String nombre;
        private int resistencia;
        private int codigo;

        public Block(String nombre, int resistencia, int codigo) {
            this.nombre = nombre;
            this.resistencia = resistencia;
            this.codigo = codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public int getResistencia() {
            return resistencia;
        }

        public int getCodigo() {
            return codigo;
        }

    }

    private class Mould {

        private String name;
        private Date fecha_compra;
        private int codigo;

        public Mould(String name, Date fecha_compra, int codigo) {
            this.name = name;
            this.fecha_compra = fecha_compra;
            this.codigo = codigo;
        }

        public String getName() {
            return name;
        }

        public Date getFecha_compra() {
            return fecha_compra;
        }

        public int getCodigo() {
            return codigo;
        }

    }

    private class MaterialEntrante {

        private int codigoViaje;
        private int codigoMaterial;
        private double precioUnitario;
        private double cantidadExistente;
        private double cantidadEntrante;
        private double cantidadSalida;

        public MaterialEntrante(int codigoViaje, int codigoMaterial, double precioUnitario, double cantidadEntrante, double cantidadSalida) {
            this.codigoViaje = codigoViaje;
            this.codigoMaterial = codigoMaterial;
            this.precioUnitario = precioUnitario;
            this.cantidadEntrante = cantidadEntrante;
            this.cantidadSalida = cantidadSalida;
        }

        public double getCantidadEntrante() {
            return cantidadEntrante;
        }

        public double getCantidadSalida() {
            return cantidadSalida;
        }

        public int getCodigoViaje() {
            return codigoViaje;
        }

        public int getCodigoMaterial() {
            return codigoMaterial;
        }

        public double getPrecioUnitario() {
            return precioUnitario;
        }
        /*
         * Lo que hace el getCantidadEcistente es restas lo entrante 
         * menos lo saliente y nos da ese resultado, de cada elemento 
         * de esta clase. 
         */

        public double getCantidadExistente() {
            cantidadExistente = cantidadEntrante - cantidadSalida;
            return cantidadExistente;
        }

        // Variable que me sirve para imprimir lo que estoy obteniendo 
        @Override
        public String toString() {
            return "MaterialEntrante{" + "codigoViaje=" + codigoViaje + "\n codigoMaterial=" + codigoMaterial + "\n precioUnitario=" + precioUnitario + "\n cantidadExistente=" + this.getCantidadExistente() + "\n cantidadEntrante=" + cantidadEntrante + "\n cantidadSalida=" + cantidadSalida + '}';
        }

    }

    private void setData() {
        getMoulds(); // configuro el combo box del molde. 
        getBlocks();  // configuro el combo box de los blocks   

    }

    private void getMoulds() {
        try {
            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("Select nombre,fecha_compra,codigo from molde ");
            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();

            resultSet.last();                // estas lineas se mueven al final de la consulta 

            int numrows = resultSet.getRow();
            int numcolumn = metaData.getColumnCount();

            dataMould = new Mould[numrows];

            resultSet.beforeFirst();

            int pos = 0;
            while (resultSet.next()) {
                String nombre = "";
                Date date = null;
                int code = -1;
                for (int i = 0; i <= numcolumn; i++) {
                    switch (i) {
                        case 1:
                            nombre = resultSet.getString(1);
                            break;
                        case 2:
                            date = resultSet.getDate(2);
                        case 3:
                            code = resultSet.getInt(3);
                    }
                }
                dataMould[pos] = new Mould(nombre, date, code);
                pos++;
            }
            moldeBox.removeAllItems();
            for (int i = 0; i <= dataMould.length - 1; i++) {
                moldeBox.addItem(dataMould[i].getName() + " Fecha : " + dataMould[i].getFecha_compra().toString());
            }

        } catch (SQLException ex) {
            Logger.getLogger(CheckProduction.class.getName()).log(Level.SEVERE, null, ex);
        }
        moldeBox.setSelectedIndex(-1);
    }

    private void getBlocks() {
        try {
            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("Select nombre_block,resistencia,codigo from block ");
            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();

            resultSet.last();                // estas lineas se mueven al final de la consulta 

            int numrows = resultSet.getRow();
            int numcolumn = metaData.getColumnCount();

            dataBlock = new Block[numrows];  // La cantidadExistente de blocks que seran 

            resultSet.beforeFirst();

            int pos = 0;
            while (resultSet.next()) {

                String nBlock = "";
                int res = 0;
                int cod = 0;

                for (int i = 0; i <= numcolumn; i++) {
                    switch (i) {
                        case 1:
                            nBlock = resultSet.getString(1);
                            break;
                        case 2:
                            res = resultSet.getInt(2);
                        case 3:
                            cod = resultSet.getInt(3);
                    }
                }
                dataBlock[pos] = new Block(nBlock, res, cod);
                pos++;
            }
            blockBox.removeAllItems();
            for (int i = 0; i <= dataBlock.length - 1; i++) {
                blockBox.addItem(dataBlock[i].getNombre() + " Resistencia: " + dataBlock[i].getResistencia());
            }

        } catch (SQLException ex) {
            Logger.getLogger(CheckProduction.class.getName()).log(Level.SEVERE, null, ex);
        }
        blockBox.setSelectedIndex(-1);
    }

    private void getTripInMaterial() {

        int codigoViajeEntrante = -1;
        int codigoMaterialIn = -1;
        double cantidadIn = 0;
        double cantidadOut = 0;
        double precioMaterialIn = 0;

        /* En este metodo llenare una lista de 
         tipo de la clase privada que se llama 
         MaterialEntrante y esta tendran los atributos 
         del codigo del viaje, el codido del material, 
         la cantidad de ingreso, la cantidad de salida 
         y el precio del material de este 
         obtendre todos los viajes que su tipo de viaje 
         sea IngresoMaterial que se referira al ingreso de viajes 
         de materiales*/
        // Variables propias de este metodo
        ResultSetMetaData metaData;
        ResultSet resultSet2;

        try {
            // Codigo de Viajes n 
            // Aqui no es un prepared Statement porque solo es uno 
            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT viaje.codigo"
                    + " From viaje, proveedor, personal, materia, movimiento_material, movimiento_personal, movimiento_vehiculo, vehiculo "
                    + " WHERE viaje.Tipo_viaje like 'IngresoMaterial'"
                    + " AND viaje.codigo_proveedor = proveedor.codigo"
                    + " AND movimiento_material.codigo_material_entrante = viaje.codigo"
                    + " AND movimiento_personal.codigo_viaje = viaje.codigo"
                    + " AND movimiento_vehiculo.codigo_viaje = viaje.codigo"
                    + " AND movimiento_material.codigo_material = materia.codigo"
                    + " AND movimiento_vehiculo.codigo_vehiculo = vehiculo.codigo"
                    + " AND movimiento_personal.codigo_personal = personal.codigo"
                    + " AND movimiento_material.codigo_produccion IS NULL"
                    + " AND movimiento_material.existencia = 1");
            metaData = (ResultSetMetaData) resultSet.getMetaData();

            resultSet.last();                // estas lineas se mueven al final de la consulta 

            int numrows = resultSet.getRow();

            dataMaterialIn = new MaterialEntrante[numrows];  // La cantidad de viajes  

            resultSet.beforeFirst(); // regreso al inicio 

            int pos = 0;
            while (resultSet.next()) {
                codigoViajeEntrante = resultSet.getInt(1);

                // Cantidad Entrante que corresponde al viaje tal ...   
                // Aqui no es un prepared Statement porque solo es uno 
                st = (Statement) connection.createStatement();
                resultSet2 = st.executeQuery(""
                    + "SELECT movimiento_material.cantidad, materia.codigo , movimiento_material.precio_unitario "
                    + "From viaje, proveedor, personal, materia, movimiento_material, movimiento_personal, movimiento_vehiculo, vehiculo "
                    + "WHERE viaje.Tipo_viaje like 'IngresoMaterial'"
                    + " AND viaje.codigo_proveedor = proveedor.codigo"
                    + " AND movimiento_material.codigo_material_entrante = "+codigoViajeEntrante
                    + " AND movimiento_personal.codigo_viaje = viaje.codigo"
                    + " AND movimiento_vehiculo.codigo_viaje = viaje.codigo"
                    + " AND movimiento_material.codigo_material = materia.codigo"
                    + " AND movimiento_vehiculo.codigo_vehiculo = vehiculo.codigo"
                    + " AND movimiento_personal.codigo_personal = personal.codigo"
                    + " AND movimiento_material.codigo_produccion IS NULL"
                    + " AND movimiento_material.existencia = 1"); // OJO no se hace sum a cantidad porque solo va a ver una cantidad de este viaje. 
                metaData = (ResultSetMetaData) resultSet2.getMetaData();

                int numcolumn = metaData.getColumnCount();
                while (resultSet2.next()) {
                    for (int i = 0; i <= numcolumn; i++) {
                        switch (i) {
                            case 1:
                                cantidadIn = resultSet2.getDouble(1);
                                break;
                            case 2:
                                codigoMaterialIn = resultSet2.getInt(2);
                                break;
                            case 3:
                                precioMaterialIn = resultSet2.getDouble(3);
                                break;
                        }
                    }

                }

                // Cantidad Saliente que corresponde al viaje tal ... 
                // Aqui no es un prepared Statement porque solo es uno 
                st = (Statement) connection.createStatement();
                resultSet2 = st.executeQuery(""
                        
                    + "SELECT COALESCE(SUM(movimiento_material.cantidad),0)"
                    + "From viaje, proveedor, personal, materia, movimiento_material, movimiento_personal, movimiento_vehiculo, vehiculo "
                    + "WHERE viaje.Tipo_viaje like 'IngresoMaterial'"
                    + " AND viaje.codigo_proveedor = proveedor.codigo"
                    + " AND movimiento_material.codigo_material_entrante = "+codigoViajeEntrante
                    + " AND movimiento_personal.codigo_viaje = viaje.codigo"
                    + " AND movimiento_vehiculo.codigo_viaje = viaje.codigo"
                    + " AND movimiento_material.codigo_material = materia.codigo"
                    + " AND movimiento_vehiculo.codigo_vehiculo = vehiculo.codigo"
                    + " AND movimiento_personal.codigo_personal = personal.codigo"
                    + " AND movimiento_material.codigo_produccion IS NOT NULL"
                    + " AND movimiento_material.existencia = 2"
                    + " GROUP BY viaje.codigo ");
                metaData = (ResultSetMetaData) resultSet2.getMetaData();
                cantidadOut = 0; // Para evitar datos del anterior 
                while (resultSet2.next()) {
                    cantidadOut = resultSet2.getDouble(1);
                }

                dataMaterialIn[pos] = new MaterialEntrante(codigoViajeEntrante, codigoMaterialIn, precioMaterialIn, cantidadIn, cantidadOut);

                pos++;

            } // Fin del while maestro, que recorre a todos los viajes que sean tipo ingreso

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al ver existencia en materia Prima, no puede crear la produccion </br> " + ex + " </p></pre></body></html>", "Error Viajes Materia Prima", 0);

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        calendar = new com.toedter.calendar.JDateChooser();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        moldeBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        blockBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jLabel8 = new javax.swing.JLabel();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        jComboBox7 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLayeredPane8 = new javax.swing.JLayeredPane();
        jComboBox8 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLayeredPane9 = new javax.swing.JLayeredPane();
        jComboBox9 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        ctablas = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cblocktabla = new javax.swing.JTextField();
        cproducir = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cblocks = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        csacar = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        csegunda = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cBachadas = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        materialTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sacadoresTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        nameframe = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        checkLabelMateria = new javax.swing.JLabel();
        checkLabelProduccion = new javax.swing.JLabel();
        checkLabelResponsablesProduccion = new javax.swing.JLabel();
        checkLabelSacadores = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setText("Fecha");

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Molde"));

        moldeBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        moldeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLayeredPane1.add(moldeBox);
        moldeBox.setBounds(20, 50, 340, 30);

        jLabel2.setText("Nombre y Codigo de Molde");
        jLayeredPane1.add(jLabel2);
        jLabel2.setBounds(10, 20, 250, 23);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Molde"));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLayeredPane3.add(jComboBox3);
        jComboBox3.setBounds(30, 60, 106, 20);

        jLabel5.setText("Nombre y Codigo de Molde");
        jLayeredPane3.add(jLabel5);
        jLabel5.setBounds(10, 30, 140, 23);

        jLayeredPane1.add(jLayeredPane3);
        jLayeredPane3.setBounds(-49, 0, 50, 0);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Molde"));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLayeredPane4.add(jComboBox4);
        jComboBox4.setBounds(30, 60, 106, 20);

        jLabel6.setText("Nombre y Codigo de Molde");
        jLayeredPane4.add(jLabel6);
        jLabel6.setBounds(10, 30, 140, 23);

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Molde"));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLayeredPane5.add(jComboBox5);
        jComboBox5.setBounds(30, 60, 106, 20);

        jLabel7.setText("Nombre y Codigo de Molde");
        jLayeredPane5.add(jLabel7);
        jLabel7.setBounds(10, 30, 140, 23);

        jLayeredPane4.add(jLayeredPane5);
        jLayeredPane5.setBounds(-49, 0, 50, 0);

        jLayeredPane1.add(jLayeredPane4);
        jLayeredPane4.setBounds(-119, 0, 120, 0);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        blockBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        blockBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLayeredPane2.add(blockBox);
        blockBox.setBounds(20, 50, 240, 30);

        jLabel4.setText("Tipo de Block ");
        jLayeredPane2.add(jLabel4);
        jLabel4.setBounds(10, 20, 88, 23);

        jLayeredPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad"));

        jLabel8.setText("Cantidad de Tablas");
        jLayeredPane6.add(jLabel8);
        jLabel8.setBounds(10, 30, 140, 23);

        jLayeredPane7.setBorder(javax.swing.BorderFactory.createTitledBorder("Molde"));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLayeredPane7.add(jComboBox7);
        jComboBox7.setBounds(30, 60, 106, 20);

        jLabel9.setText("Nombre y Codigo de Molde");
        jLayeredPane7.add(jLabel9);
        jLabel9.setBounds(10, 30, 140, 23);

        jLayeredPane6.add(jLayeredPane7);
        jLayeredPane7.setBounds(-49, 0, 50, 0);

        jLayeredPane8.setBorder(javax.swing.BorderFactory.createTitledBorder("Molde"));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLayeredPane8.add(jComboBox8);
        jComboBox8.setBounds(30, 60, 106, 20);

        jLabel10.setText("Nombre y Codigo de Molde");
        jLayeredPane8.add(jLabel10);
        jLabel10.setBounds(10, 30, 140, 23);

        jLayeredPane9.setBorder(javax.swing.BorderFactory.createTitledBorder("Molde"));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLayeredPane9.add(jComboBox9);
        jComboBox9.setBounds(30, 60, 106, 20);

        jLabel11.setText("Nombre y Codigo de Molde");
        jLayeredPane9.add(jLabel11);
        jLabel11.setBounds(10, 30, 140, 23);

        jLayeredPane8.add(jLayeredPane9);
        jLayeredPane9.setBounds(-49, 0, 50, 0);

        jLayeredPane6.add(jLayeredPane8);
        jLayeredPane8.setBounds(-119, 0, 120, 0);

        ctablas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ctablasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ctablasKeyTyped(evt);
            }
        });
        jLayeredPane6.add(ctablas);
        ctablas.setBounds(20, 60, 140, 30);

        jLabel12.setText("Block por Tabla ");
        jLayeredPane6.add(jLabel12);
        jLabel12.setBounds(240, 30, 140, 23);

        cblocktabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cblocktablaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cblocktablaKeyTyped(evt);
            }
        });
        jLayeredPane6.add(cblocktabla);
        cblocktabla.setBounds(250, 60, 140, 30);

        cproducir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cproducirKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cproducirKeyTyped(evt);
            }
        });
        jLayeredPane6.add(cproducir);
        cproducir.setBounds(490, 60, 140, 30);

        jLabel14.setText("Quebrados al producir");
        jLayeredPane6.add(jLabel14);
        jLabel14.setBounds(470, 30, 140, 23);

        cblocks.setEditable(false);
        cblocks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cblocksKeyTyped(evt);
            }
        });
        jLayeredPane6.add(cblocks);
        cblocks.setBounds(490, 140, 140, 30);

        jLabel15.setText("Total de Blocks");
        jLayeredPane6.add(jLabel15);
        jLabel15.setBounds(480, 110, 140, 23);

        jLabel16.setText("Quebrados al Sacar");
        jLayeredPane6.add(jLabel16);
        jLabel16.setBounds(10, 110, 140, 23);

        csacar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                csacarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                csacarKeyTyped(evt);
            }
        });
        jLayeredPane6.add(csacar);
        csacar.setBounds(20, 140, 140, 30);

        jLabel17.setText("Blocks de Segunda");
        jLayeredPane6.add(jLabel17);
        jLabel17.setBounds(240, 110, 140, 23);

        csegunda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                csegundaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                csegundaKeyTyped(evt);
            }
        });
        jLayeredPane6.add(csegunda);
        csegunda.setBounds(250, 140, 140, 30);

        jLabel13.setText("Bachadas");

        cBachadas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cBachadasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cBachadasKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cBachadas, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90))
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLayeredPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(calendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cBachadas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLayeredPane1, jLayeredPane2});

        panelTab.addTab("Datos Generales de Producción", jPanel1);

        try{
            materialModelTable = new DiferentTable( listaNombresTablaMaterial,consultaMaterial,"material" );
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al cargar datos "+e);

        }
        materialTable.setModel(materialModelTable
        );
        materialTable.getTableHeader().setReorderingAllowed(false);
        materialTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                materialTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(materialTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTab.addTab("Materia Prima", jPanel4);

        try{
            producctionModelTable = new DiferentTable( listaNombresTabla,consulta,"personal" );
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al cargar datos "+e);

        }
        jTable3.setModel(producctionModelTable);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTab.addTab("Responsables de Produción", jPanel2);

        try{
            sacadoresModelTable = new DiferentTable( listaNombresTablaSacadores,consultaSacadores,"Sacadores" );
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al cargar datos "+e);

        }
        sacadoresTable.setModel(sacadoresModelTable
        );
        sacadoresTable.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        sacadoresTable.getTableHeader().setReorderingAllowed(false);
        sacadoresTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sacadoresTableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(sacadoresTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTab.addTab("Sacadores", jPanel3);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png"))); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/prev.png"))); // NOI18N
        jButton2.setAutoscrolls(true);
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/next.png"))); // NOI18N
        jButton3.setAutoscrolls(true);
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(116, 186, 232));

        nameframe.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        nameframe.setForeground(new java.awt.Color(255, 255, 255));
        nameframe.setText("Ingreso de Producción");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameframe, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(360, 360, 360))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameframe)
        );

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        checkLabelMateria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        checkLabelMateria.setText("Materia Prima");
        checkLabelMateria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkLabelMateriaMouseClicked(evt);
            }
        });

        checkLabelProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        checkLabelProduccion.setText("Datos Produccion");
        checkLabelProduccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkLabelProduccionMouseClicked(evt);
            }
        });

        checkLabelResponsablesProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        checkLabelResponsablesProduccion.setText("Responsables Producción");
        checkLabelResponsablesProduccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkLabelResponsablesProduccionMouseClicked(evt);
            }
        });

        checkLabelSacadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        checkLabelSacadores.setText("Sacadores");
        checkLabelSacadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkLabelSacadoresMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelTab, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkLabelProduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(checkLabelMateria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkLabelResponsablesProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkLabelSacadores, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 952, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton4});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {checkLabelMateria, checkLabelProduccion, checkLabelResponsablesProduccion, checkLabelSacadores});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(checkLabelProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkLabelMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkLabelResponsablesProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkLabelSacadores, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelTab, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton4});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkLabelMateria, checkLabelProduccion, checkLabelResponsablesProduccion, checkLabelSacadores});

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void updateData() {
        double sueldo = 0;
        double bonificacion = 0;
        double total = 0;
        double sueldoq = 0;
        double bonificacionq = 0;

        int filas = producctionModelTable.getRowCount(); // cantidadExistente de filas 
        boolean value = false;

        for (int fila = 0; fila < filas; fila++) {

            if (fila > -1) {
                try {
                    value = Boolean.valueOf(producctionModelTable.getValueAt(fila, 9).toString()); // obtengo el valor del check
                } catch (Exception e) {
                    value = false;
                }

                if (value) { // solo voy hacer la suma donde tenga el check 
                    try {
                        sueldo = Double.parseDouble(producctionModelTable.getValueAt(fila, 4).toString());
                    } catch (Exception e) {
                        sueldo = 0;
                    }
                    try {
                        bonificacion = Double.parseDouble(producctionModelTable.getValueAt(fila, 5).toString());
                    } catch (Exception e) {
                        bonificacion = 0;
                    }
                    sueldoq = sueldo * cantidadBlocks; // 
                    bonificacionq = bonificacion * cantidadBlocks;
                    producctionModelTable.setValueAt(sueldoq, fila, 6);
                    producctionModelTable.setValueAt(bonificacionq, fila, 7);

                    // sumo todo el total y lo coloco en la ultima columna
                    total = (sueldoq + bonificacionq);
                    producctionModelTable.setValueAt(total, fila, 8);

                } else {
                    producctionModelTable.setValueAt(0, fila, 6);
                    producctionModelTable.setValueAt(0, fila, 7);
                    producctionModelTable.setValueAt(0, fila, 8);
                }

            }
        }

    }

    public void uploadMts3() {
        int filas = materialModelTable.getRowCount();  // cantidadExistente de filas de la tabla    
        for (int fila = 0; fila < filas; fila++) {

            double segsMaterial = 0;
            double botesSegs = 0;
            double mts3 = 0;  // metros cubico material 

            if (fila > -1) {  // excluyo al tipo de block que es String
                try {
                    botesSegs = Double.parseDouble(materialModelTable.getValueAt(fila, 2).toString());
                } catch (Exception e) {
                    botesSegs = 0;
                }
                try {
                    segsMaterial = Double.parseDouble(materialModelTable.getValueAt(fila, 3).toString());
                } catch (Exception e) {
                    segsMaterial = 0;
                }

                // sumo todo el total y lo coloco en la ultima columna
                mts3 = (botesSegs * segsMaterial) * cantidadBachadas;
                materialModelTable.setValueAt(mts3, fila, 4);

            }
        }

    }

    public void updateSacadores() {
        int filas = sacadoresModelTable.getRowCount();  // cantidadExistente de filas de la tabla    
        for (int fila = 0; fila < filas; fila++) {
            double sueldo = 0;
            double bonificacion = 0;
            double total = 0;
            double sueldoq = 0;
            double bonificacionq = 0;
            int cBlocks = 0;

            if (fila > -1) {
                try {
                    sueldo = Double.parseDouble(sacadoresModelTable.getValueAt(fila, 4).toString());
                } catch (Exception e) {
                    sueldo = 0;
                }
                try {
                    bonificacion = Double.parseDouble(sacadoresModelTable.getValueAt(fila, 5).toString());
                } catch (Exception e) {
                    bonificacion = 0;
                }
                try {
                    cBlocks = Integer.parseInt(sacadoresModelTable.getValueAt(fila, 6).toString());
                } catch (Exception e) {
                    cBlocks = 0;
                }
                sueldoq = sueldo * cBlocks; // 1500 es la cantidadExistente de blocks
                bonificacionq = bonificacion * cBlocks;
                sacadoresModelTable.setValueAt(sueldoq, fila, 7);
                sacadoresModelTable.setValueAt(bonificacionq, fila, 8);

                // sumo todo el total y lo coloco en la ultima columna
                total = (sueldoq + bonificacionq);
                sacadoresModelTable.setValueAt(total, fila, 9);

            }
        }
    }

    private void IngresarProduccion() {

        codigoRecienIngresado = -1;

        try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Produccion (Cantidad_de_tablas,Cantidad_de_blocks_por_tablas, Bachadas, codigo) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            // Cantidad de Tablas 
            ps.setInt(1, Integer.parseInt(ctablas.getText()));
            // Blocks por tabla 
            ps.setInt(2, Integer.parseInt(cblocktabla.getText()));
            // Cantidad de Bachadas
            ps.setInt(3, Integer.parseInt(cBachadas.getText()));

            // El codigo en este caso se lo asignare yo porque debe ser el que solia tener antes 
            ps.setInt(4, codeToChange);

            resultado = ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                codigoRecienIngresado = resultSet.getInt(1);
            }

            Ingresar_Fecha("Update Produccion set Fecha =\"", codigoRecienIngresado); // Fecha del viaje 
            processGood = true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(rootPane, "Error al ingresar produccion \n" + e, "Error al ingresar produccion", 0);
            processGood = false;

        } // fin del catch

    }
    /*
     Este metodo funciona para cargar las personas 
     que trabajaron en la produccion y se le da TRUE en el 
     checkbox .! 
     */

    private void cargarPersonasEnProduccion() {

        int codigoPersonal = -1;
        int cantidadBlock = 0; // Esto va a definir si es de responsables de produccion o sacadores 
        
        int filas = producctionModelTable.getRowCount(); // cantidadExistente de filas 
        boolean value = false;

        for (int fila = 0; fila < filas; fila++) {

            if (fila > -1) {
                codigoPersonal = Integer.parseInt(producctionModelTable.getValueAt(fila, 0).toString());
                Object data[] = new DataBaseClass().giveData("SELECT cantidad_block FROM movimiento_personal INNER JOIN produccion ON produccion.codigo = movimiento_personal.codigo_produccion INNER JOIN personal ON personal.codigo = movimiento_personal.codigo_personal WHERE produccion.codigo = " + codeToChange + " AND personal.codigo = " + codigoPersonal);
                
                /*
                 Si nos da error es porque ese personal no tubo nada que ver en la produccion. 
                 */
                try {

                    cantidadBlock = Integer.parseInt(data[0].toString());
                } catch (Exception e) {
                    cantidadBlock = 0;
                }

                if (cantidadBlock != 0) {

                    if (cantidadBlock == cantidadBlocks) {
                        producctionModelTable.setValueAt(Boolean.valueOf(true), fila, 9);

                    } else {
                        producctionModelTable.setValueAt(Boolean.valueOf(false), fila, 9);
                    }

                }

            }
        } // fin del for. 

    }

    /*
    Este metodo carga los que fueron los sacadores 
    */
    private void cargarSacadoresProduccion() {

        int codigoPersonal = -1;
        int cantidadBlock = 0; // Esto va a definir si es de responsables de produccion o sacadores 
        
        int filas = sacadoresModelTable.getRowCount(); // cantidadExistente de filas 
        
        for (int fila = 0; fila < filas; fila++) {

            if (fila > -1) {
                codigoPersonal = Integer.parseInt(sacadoresModelTable.getValueAt(fila, 0).toString());
                Object data[] = new DataBaseClass().giveData("SELECT cantidad_block FROM movimiento_personal INNER JOIN produccion ON produccion.codigo = movimiento_personal.codigo_produccion INNER JOIN personal ON personal.codigo = movimiento_personal.codigo_personal WHERE produccion.codigo = " + codeToChange + " AND personal.codigo = " + codigoPersonal);
                
                /*
                 Si nos da error es porque ese personal no tubo nada que ver en la produccion. 
                 */
                try {

                    cantidadBlock = Integer.parseInt(data[0].toString());
                } catch (Exception e) {
                    cantidadBlock = 0;
                }

                if (cantidadBlock != 0) {

                    if (cantidadBlock == cantidadBlocks) {
                        sacadoresModelTable.setValueAt(0, fila, 6);

                    } else {
                        sacadoresModelTable.setValueAt(cantidadBlock, fila, 6);
                    }

                }

            }
        } // fin del for. 

    }
    
    /*
    Este metodo funciona para cargar los segundos del material 
    */
    
    private void cargarSegundosMaterialProduccion() {

        int codigoMateriaPrima = -1;
        double cantidadMateria = 0; // Esto va a definir si es de responsables de produccion o sacadores 
        double unidadesSegundos =0 ;
        double segundosMaterial = 0.0;
                    
        
        int filas = materialModelTable.getRowCount(); // cantidadExistente de filas 
        
        for (int fila = 0; fila < filas; fila++) {

            if (fila > -1) {
                codigoMateriaPrima = Integer.parseInt(materialModelTable.getValueAt(fila, 0).toString());
                unidadesSegundos = Double.parseDouble(materialModelTable.getValueAt(fila, 2).toString());
                
                Object data[] = new DataBaseClass().giveData("SELECT cantidad FROM movimiento_material INNER JOIN produccion ON produccion.codigo = movimiento_material.codigo_produccion INNER JOIN materia ON materia.codigo = movimiento_material.codigo_material WHERE produccion.codigo = " + codeToChange + " AND materia.codigo = " + codigoMateriaPrima);
                
                /*
                 Si nos da error es porque ese personal no tubo nada que ver en la produccion. 
                 */
                try {

                    cantidadMateria = Double.parseDouble(data[0].toString());
                } catch (Exception e) {
                    cantidadMateria = 0;
                }

                if (cantidadMateria != 0) {
                    
                    segundosMaterial = cantidadMateria/(unidadesSegundos*cantidadBachadas);

                    
                    materialModelTable.setValueAt(segundosMaterial, fila, 3);

                    

                }

            }
        } // fin del for. 

    }
    
    private void ingresarMovimientoTrabajadoresProduccion() {
        double sueldo = 0;
        double bonificacion = 0;
        double total = 0;
        double sueldoq = 0;
        double bonificacionq = 0;

        int filas = producctionModelTable.getRowCount(); // cantidadExistente de filas 
        boolean value = false;

        for (int fila = 0; fila < filas; fila++) {

            if (fila > -1) {
                try {
                    value = Boolean.valueOf(producctionModelTable.getValueAt(fila, 9).toString()); // obtengo el valor del check
                } catch (Exception e) {
                    value = false;
                }

                if (value) { // solo voy hacer la suma donde tenga el check 
                    try {
                        sueldo = Double.parseDouble(producctionModelTable.getValueAt(fila, 4).toString());
                    } catch (Exception e) {
                        sueldo = 0;
                    }
                    try {
                        bonificacion = Double.parseDouble(producctionModelTable.getValueAt(fila, 5).toString());
                    } catch (Exception e) {
                        bonificacion = 0;
                    }
                    sueldoq = sueldo * cantidadBlocks; // 
                    bonificacionq = bonificacion * cantidadBlocks;
                    producctionModelTable.setValueAt(sueldoq, fila, 6);
                    producctionModelTable.setValueAt(bonificacionq, fila, 7);

                    // sumo todo el total y lo coloco en la ultima columna
                    total = (sueldoq + bonificacionq);
                    producctionModelTable.setValueAt(total, fila, 8);

                    /*
                     * Luego de que vuelvo a chequear las cosas, voy a meterlos a la base de datos, en movimiento de personal y la cantidadExistente de blocks de estos seran la de 
                     * la cantidadExistente de blocks que se produjieron ese dia, ignorando la perdida de block 
                     */
                    try {

                        int cod = 0;
                        int resultado = 0;
                        PreparedStatement ps = null;
                        ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Movimiento_Personal "
                                + "(Codigo_Personal,Cantidad_Block,Codigo_Produccion,Sueldo_en_Q,Bonificacion_en_Q) values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

                        ps.setInt(1, Integer.parseInt(producctionModelTable.getValueAt(fila, 0).toString()));
                        ps.setInt(2, cantidadBlocks);
                        ps.setInt(3, codigoRecienIngresado);
                        ps.setDouble(4, sueldo); // Tarifa Sueldo
                        ps.setDouble(5, bonificacion); // Tarifa bonificacion

                        resultado = ps.executeUpdate();

                        resultSet = ps.getGeneratedKeys();
                        while (resultSet.next()) {
                            cod = resultSet.getInt(1); // codigo de la que acaba de entrar

                        }

                        Ingresar_Fecha("Update movimiento_personal set Fecha =\"", cod); // Fecha del viaje 
                        processGood = true;

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Error al ingresar produccion Movimiento Personal \n" + ex, "Error al ingresar produccion", 0);
                        processGood = false;
                    }
                } else {
                    producctionModelTable.setValueAt(0, fila, 6);
                    producctionModelTable.setValueAt(0, fila, 7);
                    producctionModelTable.setValueAt(0, fila, 8);
                }

            }
        }

    }

    private void ingresarMovimientoTrabajaodresSacadores() {
        int fila = sacadoresModelTable.getRowCount();  // cantidadExistente de filas de la tabla 

        int cblocks = 0; // cantidadExistente de blocks 
        Double totalq = 0.0;
        int cod = -1;

        for (int k = 0; k < fila; k++) {

            // Excepciones cuando deje vacio. 
            try {
                cblocks = Integer.parseInt(sacadoresModelTable.getValueAt(k, 6).toString());
            } catch (Exception e) {
                cblocks = 0;
            }

            try {
                totalq = Double.parseDouble(sacadoresModelTable.getValueAt(k, 9).toString());
            } catch (Exception e) {
                totalq = 0.0;
            }

            if (cblocks != 0 && totalq != 0.0) {

                try {

                    // coneccion
                    int resultado = 0;
                    PreparedStatement ps = null;

                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Movimiento_Personal "
                            + "(Codigo_Personal, Cantidad_Block,Codigo_Produccion,Sueldo_en_Q,Bonificacion_en_Q) "
                            + "values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

                    ps.setInt(1, Integer.parseInt(sacadoresModelTable.getValueAt(k, 0).toString()));
                    ps.setInt(2, cblocks);
                    ps.setInt(3, codigoRecienIngresado);
                    ps.setDouble(4, Double.parseDouble(sacadoresModelTable.getValueAt(k, 4).toString()));
                    ps.setDouble(5, Double.parseDouble(sacadoresModelTable.getValueAt(k, 5).toString()));

                    resultado = ps.executeUpdate();

                    resultSet = ps.getGeneratedKeys();
                    while (resultSet.next()) {
                        cod = resultSet.getInt(1);  // codigo de la ultima ingresada

                    }

                    Ingresar_Fecha("Update Movimiento_Personal set Fecha =\"", cod); // Fecha del viaje 
                    processGood = true;

                } catch (SQLException e) {

                    JOptionPane.showMessageDialog(rootPane, "Error al ingresar produccion Ingreso de Sacadores\n" + e, "Error al ingresar produccion", 0);
                    processGood = false;

                } // fin del catch
            }  // Fin del if 

        } // Fin del for. 

    }

    private void ingresarMovimientoMolde() {

        try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Movimiento_Molde (Codigo_molde,Cantidad_Blocks, Codigo_Produccion) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            // Codigo Molde
            ps.setInt(1, dataMould[moldeBox.getSelectedIndex()].getCodigo());

            // Cantidad de block
            int cant = 0;
            cant = Integer.parseInt(ctablas.getText().toString()) * Integer.parseInt(cblocktabla.getText().toString());
            ps.setInt(2, cant);

            // Codigo Produccion
            ps.setInt(3, codigoRecienIngresado);

            resultado = ps.executeUpdate();
            int codenow = 0;
            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                codenow = resultSet.getInt(1);
            }

            Ingresar_Fecha("Update movimiento_molde set Fecha =\"", codenow); // Fecha del viaje 
            processGood = true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(rootPane, "Error al ingresar produccion Movimiento Molde \n" + e, "Error al ingresar produccion", 0);
            processGood = false;

        } // fin del catch

    }

    private void ingresarMovimientoBlock() {

        try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Movimiento_Block (Codigo_block,Cantidad, Codigo_Produccion) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            // Codigo Block
            ps.setInt(1, dataBlock[blockBox.getSelectedIndex()].getCodigo());

            // Cantidad de block
            int cant = 0;
            cant = Integer.parseInt(ctablas.getText().toString()) * Integer.parseInt(cblocktabla.getText().toString());
            ps.setInt(2, cant);

            // Codigo Produccion
            ps.setInt(3, codigoRecienIngresado);

            resultado = ps.executeUpdate();
            int codenow = 0;
            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                codenow = resultSet.getInt(1);
            }

            Ingresar_Fecha("Update movimiento_block set Fecha =\"", codenow); // Fecha del viaje 
            processGood = true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(rootPane, "Error al ingresar produccion Movimiento Block \n" + e, "Error al ingresar produccion", 0);
            processGood = false;

        } // fin del catch

    }

    private void ingresarQuebradosProduccion() {

        try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Movimiento_Block (Codigo_block,Cantidad, Codigo_Perdidas,Codigo_Produccion) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            // Codigo Block
            ps.setInt(1, dataBlock[blockBox.getSelectedIndex()].getCodigo());

            int cant = 0;
            // Cantidad de block
            try {
                cant = Integer.parseInt(cproducir.getText().toString());
            } catch (Exception e) {
                cant = 0;
            }
            ps.setInt(2, cant);

            // Codigo Tipo de Quebrados 
            ps.setInt(3, 1);

            // Codigo Produccion 
            ps.setInt(4, codigoRecienIngresado);

            resultado = ps.executeUpdate();
            int codenow = 0;
            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                codenow = resultSet.getInt(1);
            }

            Ingresar_Fecha("Update movimiento_block set Fecha =\"", codenow); // Fecha del viaje 
            processGood = true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(rootPane, "Error al ingresar produccion Quebrados al Producir \n" + e, "Error al ingresar produccion", 0);
            processGood = false;

        } // fin del catch

    }

    private void ingresarQuebradosSacar() {

        try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Movimiento_Block (Codigo_block,Cantidad, Codigo_Perdidas,Codigo_Produccion) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            // Codigo Block
            ps.setInt(1, dataBlock[blockBox.getSelectedIndex()].getCodigo());

            int cant = 0;
            // Cantidad de block
            try {
                cant = Integer.parseInt(csacar.getText().toString());
            } catch (Exception e) {
                cant = 0;
            }
            ps.setInt(2, cant);

            // Codigo Tipo de quebrados 
            ps.setInt(3, 2);

            // Codigo Produccion 
            ps.setInt(4, codigoRecienIngresado);

            resultado = ps.executeUpdate();
            int codenow = 0;
            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                codenow = resultSet.getInt(1);
            }

            Ingresar_Fecha("Update movimiento_block set Fecha =\"", codenow); // Fecha del viaje 
            processGood = true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(rootPane, "Error al ingresar produccion Quebrados al Sacar \n" + e, "Error al ingresar produccion", 0);
            processGood = false;

        } // fin del catch

    }

    private void ingresarSegunda() {

        try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Movimiento_Block (Codigo_block,Cantidad, Codigo_Perdidas,Codigo_Produccion) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            // Codigo Block
            ps.setInt(1, dataBlock[blockBox.getSelectedIndex()].getCodigo());

            int cant = 0;
            // Cantidad de block
            try {
                cant = Integer.parseInt(csegunda.getText().toString());
            } catch (Exception e) {
                cant = 0;
            }
            ps.setInt(2, cant);

            // Codigo Tipo de quebrados 
            ps.setInt(3, 3);

            // Codigo Produccion 
            ps.setInt(4, codigoRecienIngresado);

            resultado = ps.executeUpdate();
            int codenow = 0;
            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                codenow = resultSet.getInt(1);
            }

            Ingresar_Fecha("Update movimiento_block set Fecha =\"", codenow); // Fecha del viaje 
            processGood = true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(rootPane, "Error al ingresar produccion Quebrados al Sacar \n" + e, "Error al ingresar produccion", 0);
            processGood = false;

        } // fin del catch

    }

    public void Ingresar_Fecha(String preConfig, int cod) {
        //Fecha
        Date date = (Date) calendar.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = format.format(date);
        DataBaseClass.executeQuery(preConfig + fecha + "\" where codigo=" + cod);
    }

    private boolean checkAvaility() {

        int codigo = -1;
        double ingresado = 0;
        double gastado = 0;
        double balance = 0;
        String nombre = "";
        ResultSetMetaData metaData;
        int numcolumn = -1;
        double aGastar = 0;
        String error = "";
        String unidades = "";

        int filas = materialModelTable.getRowCount(); // cantidadExistente de filas 
        for (int fila = 0; fila < filas; fila++) {

            codigo = Integer.parseInt(materialModelTable.getValueAt(fila, 0).toString());
            nombre = materialModelTable.getValueAt(fila, 1).toString();
            aGastar = Double.parseDouble(materialModelTable.getValueAt(fila, 4).toString());
            unidades = materialModelTable.getValueAt(fila, 5).toString();

            try {
                // Aqui no es un prepared Statement porque solo es uno 
                st = (Statement) connection.createStatement();
                resultSet = st.executeQuery(""
                        + " SELECT SUM(movimiento_material.cantidad)"
                        +" From viaje, proveedor, personal, materia, movimiento_material, movimiento_personal, movimiento_vehiculo, vehiculo "
                        +" WHERE viaje.Tipo_viaje like 'IngresoMaterial'"
                        +" AND viaje.codigo_proveedor = proveedor.codigo" 
                        +" AND movimiento_material.codigo_material_entrante = viaje.codigo"
                        +" AND movimiento_personal.codigo_viaje = viaje.codigo" 
                        +" AND movimiento_vehiculo.codigo_viaje = viaje.codigo" 
                        +" AND movimiento_material.codigo_material = materia.codigo" 
                        +" AND movimiento_vehiculo.codigo_vehiculo = vehiculo.codigo" 
                        +" AND movimiento_personal.codigo_personal = personal.codigo" 
                        +" AND movimiento_material.codigo_produccion IS NULL" 
                        +" AND movimiento_material.existencia = 1" // Esto es para que sea solo los de entrada  
                        +" AND codigo_material =" + codigo + ";");
                metaData = (ResultSetMetaData) resultSet.getMetaData();
                numcolumn = metaData.getColumnCount(); // numero de Columnas

                while (resultSet.next()) {
                    ingresado = resultSet.getDouble(1);
                }

                // Aqui no es un prepared Statement porque solo es uno 
                st = (Statement) connection.createStatement();
                resultSet = st.executeQuery(""
                        + " SELECT SUM(movimiento_material.cantidad)"
                        +" From viaje, proveedor, personal, materia, movimiento_material, movimiento_personal, movimiento_vehiculo, vehiculo "
                        +" WHERE viaje.Tipo_viaje like 'IngresoMaterial'"
                        +" AND viaje.codigo_proveedor = proveedor.codigo" 
                        +" AND movimiento_material.codigo_material_entrante = viaje.codigo"
                        +" AND movimiento_personal.codigo_viaje = viaje.codigo" 
                        +" AND movimiento_vehiculo.codigo_viaje = viaje.codigo" 
                        +" AND movimiento_material.codigo_material = materia.codigo" 
                        +" AND movimiento_vehiculo.codigo_vehiculo = vehiculo.codigo" 
                        +" AND movimiento_personal.codigo_personal = personal.codigo" 
                        +" AND movimiento_material.codigo_produccion IS NOT NULL" 
                        +" AND movimiento_material.existencia = 2" // Esto es para que sea solo los de entrada  
                        +" AND codigo_material =" + codigo + ";");
                metaData = (ResultSetMetaData) resultSet.getMetaData();
                numcolumn = metaData.getColumnCount(); // numero de Columnas

                while (resultSet.next()) {
                    gastado = resultSet.getDouble(1);
                }

                balance = ingresado - gastado;
                if (aGastar > balance) {
                    error += (aGastar - balance) + " " + unidades + " de " + nombre + "\n";
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error al ver existencia en materia Prima, no puede crear la produccion\n" + ex, "Falta Materia Prima", 0);
            }

        }

        if (error.isEmpty()) {

            return true;
        } else {
            JOptionPane.showMessageDialog(rootPane, "Error al ver existencia en materia Prima, no puede crear la produccion falta : \n" + error, "Falta Materia Prima", 0);

            return false;
        }

    }

    private void ingresarMateria() {

        // Voy por cada material que tenga segundos
        int filas = materialModelTable.getRowCount(); // cantidadExistente de filas 
        double segundosMaterial = 0;
        int codigo = -1;
        double cantidad = 0;

        for (int i = 0; i < filas; i++) {

            try {
                codigo = Integer.parseInt(materialModelTable.getValueAt(i, 0).toString()); // pruebo convertir los datos 
            } catch (Exception e) {
                codigo = -1;
            }

            try {
                segundosMaterial = Double.parseDouble(materialModelTable.getValueAt(i, 3).toString()); // pruebo convertir los datos 
            } catch (Exception e) {
                segundosMaterial = 0;
            }

            try {
                cantidad = Double.parseDouble(materialModelTable.getValueAt(i, 4).toString()); // pruebo convertir los datos 
            } catch (Exception e) {
                cantidad = 0;
            }

            if (segundosMaterial != 0) {
                /* Aqui quiere decir que 
                 existe datos los cuales se deben de ingresar 
                 sino llega aca en esta condicional quiere decir que este material no se ingreso */
                ingresarMovimientoMaterialCodigo(codigo, cantidad);
            }

        }
    }

    private void ingresarMovimientoMaterialCodigo(int codigo, double cantidad) {
        /*Primero que nada voy a llenar las existecias 
         segun el viaje segun el material 
         */

        getTripInMaterial(); // Cambia la lista de dataMaterialIn

        for (MaterialEntrante element : dataMaterialIn) {
            /*Con este for recorro la lista de dataMaterialIn que 
             es editada por el metodo getTripInMaterial */
            if (element.getCodigoMaterial() == codigo) {
                // Obtengo los que sean de este codigo de material
                if (element.getCantidadExistente() != 0 && cantidad != 0) { // Cuando cantidad sea 0, listo termine de ingresar lo que queria. 
                    // Si no esta vacia hare lo siguiente
                    if (cantidad < element.getCantidadExistente() || cantidad == element.getCantidadExistente()) {  // Menor o igual 
                        // La cantidad a usar en esta produccion es menor 
                        ingresarMovimientoMaterialCodigoSub(element.getCodigoViaje(), codigo, cantidad, element.getPrecioUnitario(), cantidad * element.getPrecioUnitario());
                        cantidad = 0; // osea luego de ingresar ya jalo el material de los viajes asi que en esta produccion ya no tengo cantidad que jalar. 

                    } else if (cantidad > element.getCantidadExistente()) {
                        // La cantidad es mayor pero ya se que es posible hacer 
                        // la produccion, asi que continuare con una nueva cantidad
                        ingresarMovimientoMaterialCodigoSub(element.getCodigoViaje(), codigo, element.getCantidadExistente(), element.getPrecioUnitario(), element.getCantidadExistente() * element.getPrecioUnitario());
                        cantidad = cantidad - element.getCantidadExistente(); // la nueva cantidad
                    }
                }

            }

        }
    }

    private void ingresarMovimientoMaterialCodigoSub(int codigoViaje, int codigoMateriaPrima, double cantidadMateriaPrima, double precioMaterial, double totalDinero) {
        /* -------------------------------------------------------
         *  Este metodo se refiere al ingreso del movimiento 
         *  de la materia prima. Y esto se refiere al ingreso 
         *  de material por medio de los viajes de la empresa 
         *  provenientes de nuestros proveedores. 
         *  Existencia se refiere a un estado del material 
         *  1 simboliza que es proveniente de material 
         *  2 utilizado en produccin 
         *  3 material para venta. 
         * -------------------------------------------------------
         */

        int cod = -1;

        try {

            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Movimiento_Material (Codigo_Material_Entrante,Codigo_Material,Cantidad, Precio_Unitario, Total,Existencia,Codigo_Produccion) values (?,?,?,?,?,?,?) ", PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, codigoViaje);    // Codigo del viaje 
            ps.setInt(2, codigoMateriaPrima); // Codigo Materia Prima 
            ps.setDouble(3, cantidadMateriaPrima);  // Cantidad de Materia Prima 
            ps.setDouble(4, precioMaterial); // Precio del material 
            ps.setDouble(5, totalDinero); // Total del dinero 
            ps.setInt(6, 2);  // Querra decir que es de tipo salida 
            ps.setInt(7, codigoRecienIngresado); // Codigo de produccion

            resultado = ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cod = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al ingresar materia en produccion. \n" + ex, "Error al ingresar Materia", 0);

        }

        Ingresar_Fecha("Update Movimiento_Material set Fecha =\"", cod); // Fecha del Movimineto del Personal del dinero  

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /*
         Aqui sucede algo intersante y es que antes de crear la produccion eliminare lo anterior 
         a esta produccion y la produccion. 
         */
        DataBaseClass.executeQuery("DELETE FROM movimiento_material WHERE codigo_produccion = " + codeToChange);
        DataBaseClass.executeQuery("DELETE FROM produccion WHERE codigo = " + codeToChange);

        if (checkGeneralData()
                && checkMaterial()
                && checkSacadores()
                && checkResposablesProduccion() && checkAvaility()) {
            // Antes de empezar a ingresar el Sistema me tiene que ir a verificar si el material 
            // aproximado que estimo el sistema es menor al que se tiene en el lote virtual. 

            /* *************************************
             *   Ahora Si, ingresemos la produccion 
             * ************************************** */
            // Si ya lo checke me indica a mi que ya solo es de ingresar los datos y ya. 
            //Ingreso Normal de la produccion
            //  Ingreso de la produccion  
            IngresarProduccion();

            // Como los demas ingresos dependen del viaje, preguntare si va bien el proceso, sino no hare nada. 
            if (processGood) {
                ingresarMovimientoBlock();

                ingresarQuebradosProduccion();

                ingresarQuebradosSacar();

                ingresarSegunda();

                ingresarMovimientoMolde();

                ingresarMovimientoTrabajadoresProduccion();

                ingresarMovimientoTrabajaodresSacadores();

                ingresarMateria();

                // Conclusión de ingreso 
                JOptionPane.showMessageDialog(rootPane, "Ha concluido con éxito la instrucción.");

                // Reconfigurar todo para poder apreciar el cambio     
                Index.refreshTable("Produccion");

                // Termino de Reconfigurar todo 
                this.dispose(); // Oculto esta ventana 

            } else {
                JOptionPane.showMessageDialog(rootPane, "Sucedió un error al ingresar el viaje, revise el proceso, repita el proceso. \n", "Error al ingresar Viaje", 0);
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Faltan datos para completar esta instrucción, favor revisar de nuevo. ", "No se pudo ingresar la nueva Produccion", 0);
            // nothing to do porque le faltan datos para concluir esta instruccion  

        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public void calculateBlocks() {
        int tablas = 0;
        int btabla = 0;
        int producir = 0;
        int sacar = 0;
        int segunda = 0;
        cantidadBlocks = 0;

        try {
            tablas = Integer.parseInt(ctablas.getText());
        } catch (Exception e) {
            tablas = 1;
        }

        try {
            btabla = Integer.parseInt(cblocktabla.getText());
        } catch (Exception e) {
            btabla = 1;
        }

        try {
            producir = Integer.parseInt(cproducir.getText());
        } catch (Exception e) {
            producir = 0;
        }

        try {
            sacar = Integer.parseInt(csacar.getText());
        } catch (Exception e) {
            sacar = 0;
        }

        try {
            segunda = Integer.parseInt(csegunda.getText());
        } catch (Exception e) {
            segunda = 0;
        }

        blockProduccion = (tablas * btabla);
        cantidadBlocks = (tablas * btabla) - (segunda + sacar + producir);
        if (cantidadBlocks == 1 && tablas == 1 && btabla == 1) {
            cantidadBlocks = 0;
        }
        cblocks.setText("" + cantidadBlocks);
    }
    private void ctablasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctablasKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE) || ((c == evt.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_ctablasKeyTyped

    private void cblocktablaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cblocktablaKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE) || ((c == evt.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_cblocktablaKeyTyped

    private void cBachadasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cBachadasKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE) || ((c == evt.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_cBachadasKeyTyped

    private void cproducirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cproducirKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE) || ((c == evt.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_cproducirKeyTyped

    private void cblocksKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cblocksKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cblocksKeyTyped

    private void csacarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csacarKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE) || ((c == evt.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_csacarKeyTyped

    private void csegundaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csegundaKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE) || ((c == evt.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_csegundaKeyTyped
    private boolean checkGeneralData() {
        cantidadBachadas = 0; // Bachada Produccion
        try {
            cantidadBachadas = Integer.parseInt(cBachadas.getText());
        } catch (Exception e) {
            cantidadBachadas = 0;
        }

        int tab = 0; // tabla campo 
        try {
            tab = Integer.parseInt(ctablas.getText());
        } catch (Exception e) {
            tab = 0;
        }

        int tabbock = 0; // tabla block campo 
        try {
            tabbock = Integer.parseInt(cblocktabla.getText());
        } catch (Exception e) {
            tabbock = 0;
        }

        int qpro = 0; // quebrados al producir campo 
        try {
            qpro = Integer.parseInt(cproducir.getText());
        } catch (Exception e) {
            qpro = 0;
        }

        int qsac = 0; // quebrados al sacar campo 
        try {
            qsac = Integer.parseInt(csacar.getText());
        } catch (Exception e) {
            qsac = 0;
        }

        int qseg = 0; // blocks de segunda 
        try {
            qseg = Integer.parseInt(csegunda.getText());
        } catch (Exception e) {
            qseg = 0;
        }

        int blocks = 0; // cantidadExistente de blocks 
        try {
            blocks = Integer.parseInt(cblocks.getText());
        } catch (Exception e) {
            blocks = 0;
        }

        if (moldeBox.getSelectedIndex() != -1 && blockBox.getSelectedIndex() != -1 && cantidadBachadas > 0 && cantidadBachadas != 0 && tab > 0 && tab != 0 && tabbock > 0 && tabbock != 0) {
            checkLabelProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));

            return true;
        } else {
            checkLabelProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
            return false;
        }

    }

    private boolean checkMaterial() {

        int filas = materialModelTable.getRowCount(); // cantidadExistente de filas 
        double segundosMaterial = 0;
        String nombreMaterial = new String();
        double botesSegundo = 0;
        double totalMaterial = 0;
        boolean good = false;

        for (int i = 0; i < filas; i++) {

            try {
                nombreMaterial = (String) materialModelTable.getValueAt(i, 1);
            } catch (Exception e) {
                nombreMaterial = " ";
            }

            try {
                botesSegundo = Double.parseDouble(materialModelTable.getValueAt(i, 2).toString()); // pruebo convertir los datos 
            } catch (Exception e) {
                botesSegundo = 0;
            }

            try {
                segundosMaterial = Double.parseDouble(materialModelTable.getValueAt(i, 3).toString()); // pruebo convertir los datos 
            } catch (Exception e) {
                segundosMaterial = 0;
            }

            try {
                totalMaterial = Double.parseDouble(materialModelTable.getValueAt(i, 4).toString()); // pruebo convertir los datos 
            } catch (Exception e) {
                totalMaterial = 0;
            }

            if (segundosMaterial != 0) {
                // Como no estoy ingresando nada solo quiero saber si 
                // Almenos hay uno que ya ingreso segundos. 
                // Entonces solo dire que si los segundos no es cero implica 
                // que tiene valores distinos de 0 y mayor a 0 y por lo tanto 
                // ya hay uno 
                // entonces dire good = true
                good = true;
            }

        }
        // verifico si esta bien o no 
        if (good) {
            // Cambio a verde 
            checkLabelMateria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
            return true;
        } else {
            // Sino quiere decir que no ha ingresado nada. 
            checkLabelMateria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
            return false;
        }

    }

    private boolean checkSacadores() {
        int fila = sacadoresModelTable.getRowCount();  // cantidadExistente de filas de la tabla 

        int cblocks = 0; // cantidadExistente de blocks 
        Double totalq = 0.0;
        int cod = 0;
        boolean good = false;

        for (int k = 0; k < fila; k++) {

            // Excepciones cuando deje vacio. 
            try {
                cblocks = Integer.parseInt(sacadoresModelTable.getValueAt(k, 6).toString());
            } catch (Exception e) {
                cblocks = 0;
            }

            try {
                totalq = Double.parseDouble(sacadoresModelTable.getValueAt(k, 9).toString());
            } catch (Exception e) {
                totalq = 0.0;
            }

            if (cblocks != 0 && totalq != 0.0) {
                good = true;

            }

        }

        if (good) {
            // Cambio a verde 
            checkLabelSacadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
            return true;
        } else {
            // Sino quiere decir que no ha ingresado nada. 
            checkLabelSacadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
            return false;
        }
    }

    private boolean checkResposablesProduccion() {
        updateData(); // Revisarlo una vez mas 
        int filas = producctionModelTable.getRowCount(); // cantidadExistente de filas 
        boolean value = false;
        boolean good = false;
        double sueldo = 0;

        for (int i = 0; i < filas; i++) {
            try {
                value = Boolean.valueOf(producctionModelTable.getValueAt(i, 9).toString()); // obtengo el valor del check
            } catch (Exception e) {
                value = false;
            }

            try {

                sueldo = Double.parseDouble(producctionModelTable.getValueAt(i, 8).toString());
            } catch (Exception e) {

                sueldo = 0;
            }

            if (value == true && sueldo > 0) {
                good = true;

            }

        }

        if (good) { // Cambio a verde 
            checkLabelResponsablesProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
            return true;
        } else {
            // Sino quiere decir que no ha ingresado nada. 
            checkLabelResponsablesProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
            return false;
        }
    }


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// Check all the tabs if it is correct. 
        checkGeneralData();
        checkMaterial();
        checkSacadores();
        checkResposablesProduccion();

        // Movimiento de Paneles  
        if (panelTab.getSelectedIndex() == 3) {
            panelTab.setSelectedIndex(0);
        } else {
            panelTab.setSelectedIndex(panelTab.getSelectedIndex() + 1);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Checkear que todos los datos esten bien.
        checkGeneralData();
        checkMaterial();
        checkSacadores();
        checkResposablesProduccion();

        if (panelTab.getSelectedIndex() == 0) {
            panelTab.setSelectedIndex(3);
        } else {
            panelTab.setSelectedIndex(panelTab.getSelectedIndex() - 1);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ctablasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctablasKeyReleased
        calculateBlocks();
    }//GEN-LAST:event_ctablasKeyReleased

    private void cblocktablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cblocktablaKeyReleased
        calculateBlocks();
    }//GEN-LAST:event_cblocktablaKeyReleased

    private void cproducirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cproducirKeyReleased
        calculateBlocks();
    }//GEN-LAST:event_cproducirKeyReleased

    private void csacarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csacarKeyReleased
        calculateBlocks();
    }//GEN-LAST:event_csacarKeyReleased

    private void csegundaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csegundaKeyReleased
        calculateBlocks();
    }//GEN-LAST:event_csegundaKeyReleased

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        updateData();
    }//GEN-LAST:event_jTable3MouseClicked

    private void materialTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_materialTableKeyReleased
        uploadMts3();
    }//GEN-LAST:event_materialTableKeyReleased

    private void cBachadasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cBachadasKeyReleased
        try {
            cantidadBachadas = Integer.parseInt(cBachadas.getText());
        } catch (Exception e) {
            cantidadBachadas = 0;
        }

    }//GEN-LAST:event_cBachadasKeyReleased

    private void sacadoresTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sacadoresTableKeyReleased
        updateSacadores();
    }//GEN-LAST:event_sacadoresTableKeyReleased

    private void checkLabelProduccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkLabelProduccionMouseClicked
        panelTab.setSelectedIndex(0);
    }//GEN-LAST:event_checkLabelProduccionMouseClicked

    private void checkLabelMateriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkLabelMateriaMouseClicked
        panelTab.setSelectedIndex(1);
    }//GEN-LAST:event_checkLabelMateriaMouseClicked

    private void checkLabelResponsablesProduccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkLabelResponsablesProduccionMouseClicked
        panelTab.setSelectedIndex(2);
    }//GEN-LAST:event_checkLabelResponsablesProduccionMouseClicked

    private void checkLabelSacadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkLabelSacadoresMouseClicked
        panelTab.setSelectedIndex(3);
    }//GEN-LAST:event_checkLabelSacadoresMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CheckProduction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckProduction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckProduction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckProduction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckProduction().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox blockBox;
    private javax.swing.JTextField cBachadas;
    private com.toedter.calendar.JDateChooser calendar;
    private javax.swing.JTextField cblocks;
    private javax.swing.JTextField cblocktabla;
    private javax.swing.JLabel checkLabelMateria;
    private javax.swing.JLabel checkLabelProduccion;
    private javax.swing.JLabel checkLabelResponsablesProduccion;
    private javax.swing.JLabel checkLabelSacadores;
    private javax.swing.JTextField cproducir;
    private javax.swing.JTextField csacar;
    private javax.swing.JTextField csegunda;
    private javax.swing.JTextField ctablas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JLayeredPane jLayeredPane8;
    private javax.swing.JLayeredPane jLayeredPane9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable materialTable;
    private javax.swing.JComboBox moldeBox;
    public static javax.swing.JLabel nameframe;
    private javax.swing.JTabbedPane panelTab;
    private javax.swing.JTable sacadoresTable;
    // End of variables declaration//GEN-END:variables
}
