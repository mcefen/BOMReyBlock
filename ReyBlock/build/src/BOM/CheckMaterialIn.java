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
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CheckMaterialIn extends javax.swing.JFrame {

    private Materia dataMaterialList[];
    private Personal dataPersonal[];
    private Vehiculo dataVehiculo[];
    private Proveedor dataProveedor[];
    private DecimalFormat money = new DecimalFormat("Q 0.00 ");
    private boolean processGood = true;
    private double cantidadOriginal = -1;

    // Variables de base de datos 
    private Statement st = DataBaseClass.getSt();
    private Connection connection = DataBaseClass.getConnection();
    private ResultSet resultSet = DataBaseClass.getResultSet();

    private int codigoToChange = -1;

    public CheckMaterialIn(int cod) {
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");

        initComponents();
        
        //Color 
        jPanel3.setBackground(ConfigClass.getColorApp());
        
        this.codigoToChange = cod;  // El codigo que voy a cambiar. 
        nameframe.setText("" + "Chequear viaje ingreso de material codigo : " + codigoToChange);

        materialList();
        personalList();
        vehiculoList();
        proveedorList();

        String consulta = "SELECT viaje.codigo, viaje.fecha, viaje.kilometraje, proveedor.nombre, proveedor.direccion, proveedor.telefono, materia.nombre, movimiento_material.cantidad, movimiento_material.total, movimiento_material.precio_unitario, personal.nombre, personal.apellido, personal.puesto, movimiento_personal.total_en_q, vehiculo.nombre, vehiculo.placa, movimiento_vehiculo.total_dinero, viaje.observaciones "
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
                + " AND viaje.codigo = " + codigoToChange + ""
                + " ORDER BY viaje.fecha DESC";

        // En este momeno colocare la inforacion segun corresponda el caso. 
        Object[] data = new DataBaseClass().giveData(consulta);

        // Fecha del viaje 
        try {

            calendar.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(data[1].toString())); // Fecha de ingreso

        } catch (ParseException ex) {
            Logger.getLogger(CheckMold.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Kilometraje viaje 
        ckil.setText(data[2].toString());

        // Proveedor 
        comboProveedor.setSelectedItem(data[3] + " / " + data[4] + " / " + data[5]);

        // Material nombre 
        comboMaterial.setSelectedItem(data[6]);

        // cantidad material
        cantidadMaterial.setText(data[7].toString());
        cantidadOriginal = Double.parseDouble(data[7].toString());

        // Total dinero 
        totalDinero.setText(data[8].toString());

        // Precio Unitario
        precioUnitario.setText("" + money.format(data[9]));

        // personal 
        comboPersonal.setSelectedItem(data[12] + " / " + data[11] + " / " + data[10]);

        //tarifa personal
        tarifaPersonal.setText(data[13].toString());

        // vehiculo 
        comboVehiculo.setSelectedItem(data[14] + " / " + data[15]);

        // Total dinero de vehiculo 
        gananciaCar.setText(data[16].toString());

        // Observaciones 
        cobs.setText(data[17].toString());

    }

    private CheckMaterialIn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class Vehiculo {

        private String nombre;
        private String placa;
        private int codigo;

        public Vehiculo(String nombre, String placa, int codigo) {
            this.nombre = nombre;
            this.placa = placa;
            this.codigo = codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getPlaca() {
            return placa;
        }

        public void setPlaca(String placa) {
            this.placa = placa;
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

    }

    class Personal {

        private String nombre;
        private String apellido;
        private int codigo;
        private String puesto;

        public Personal(String nombre, String apellido, int codigo, String puesto) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.codigo = codigo;
            this.puesto = puesto;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public String getPuesto() {
            return puesto;
        }

        public void setPuesto(String puesto) {
            this.puesto = puesto;
        }

    }

    class Proveedor {

        private int codigo;
        private String nombre;
        private String direccion;
        private String telefono;

        public Proveedor(int codigo, String nombre, String direccion, String telefono) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.direccion = direccion;
            this.telefono = telefono;
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

    }

    class Materia {

        private String nombre;
        private int codigo;
        private String pres;

        public Materia(String nombre, int codigo, String pres) {
            this.nombre = nombre;
            this.codigo = codigo;
            this.pres = pres;

        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

    }

    public void materialList() {

        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("Select Nombre, codigo, presentacion from Materia ORDER BY Nombre");
            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();

            resultSet.last();                // estas lineas se mueven al final de la consulta 

            int numrows = resultSet.getRow();
            int numcolumn = metaData.getColumnCount();

            dataMaterialList = new Materia[numrows];  //la cantidad de material que va a salir 

            resultSet.beforeFirst();

            int pos = 0;
            while (resultSet.next()) {
                String nom = "";
                int cod = 0;
                String pre = "";

                for (int i = 0; i <= numcolumn; i++) {
                    switch (i) {
                        case 1:
                            nom = resultSet.getString(1);
                            break;
                        case 2:
                            cod = resultSet.getInt(2);
                            break;
                        case 3:
                            pre = resultSet.getString(3);
                            break;

                    }
                }

                dataMaterialList[pos] = new Materia(nom, cod, pre);
                pos++;
            }

            comboMaterial.removeAllItems();  // limpio el comboBox

            for (int i = 0; i <= dataMaterialList.length - 1; i++) {
                comboMaterial.addItem(dataMaterialList[i].getNombre());
            }

        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }

        comboMaterial.setSelectedIndex(-1);

    }

    public void personalList() {

        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("Select Nombre, Apellido, Codigo, Puesto from Personal ORDER BY Puesto");
            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();

            resultSet.last();                // estas lineas se mueven al final de la consulta 

            int numrows = resultSet.getRow();
            int numcolumn = metaData.getColumnCount();

            dataPersonal = new Personal[numrows];  //la cantidad de personal que va a salir 

            resultSet.beforeFirst();

            int pos = 0;
            while (resultSet.next()) {
                String nom = "";
                String ape = "";
                int cod = 0;
                String puesto = "";
                for (int i = 0; i <= numcolumn; i++) {
                    switch (i) {
                        case 1:
                            nom = resultSet.getString(1);
                            break;
                        case 3:
                            cod = resultSet.getInt(3);
                            break;
                        case 2:
                            ape = resultSet.getString(2);
                            break;
                        case 4:
                            puesto = resultSet.getString(4);
                            break;

                    }
                }

                dataPersonal[pos] = new Personal(nom, ape, cod, puesto);
                pos++;
            }

            comboPersonal.removeAllItems();  // limpio el comboBox

            for (int i = 0; i <= dataPersonal.length - 1; i++) {
                comboPersonal.addItem(dataPersonal[i].getPuesto() + " / " + dataPersonal[i].getApellido() + " / " + dataPersonal[i].getNombre());
            }

        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }

        comboPersonal.setSelectedIndex(-1);

    }

    public void proveedorList() {

        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("Select Nombre, Direccion, Codigo, Telefono from Proveedor ORDER BY Nombre");
            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();

            resultSet.last();                // estas lineas se mueven al final de la consulta 

            int numrows = resultSet.getRow();
            int numcolumn = metaData.getColumnCount();

            dataProveedor = new Proveedor[numrows];  //la cantidad de personal que va a salir 

            resultSet.beforeFirst();

            int pos = 0;
            while (resultSet.next()) {
                String nom = "";
                String dir = "";
                int cod = 0;
                String tel = "";
                for (int i = 0; i <= numcolumn; i++) {
                    switch (i) {
                        case 1:
                            nom = resultSet.getString(1);
                            break;
                        case 3:
                            cod = resultSet.getInt(3);
                            break;
                        case 2:
                            dir = resultSet.getString(2);
                            break;
                        case 4:
                            tel = resultSet.getString(4);
                            break;

                    }
                }

                dataProveedor[pos] = new Proveedor(cod, nom, dir, tel);
                pos++;
            }

            comboProveedor.removeAllItems();  // limpio el comboBox

            for (int i = 0; i <= dataProveedor.length - 1; i++) {
                comboProveedor.addItem(dataProveedor[i].getNombre() + " / " + dataProveedor[i].getDireccion() + " / " + dataProveedor[i].getTelefono());
            }

        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }

        comboProveedor.setSelectedIndex(-1);

    }

    public void vehiculoList() {
        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("Select Nombre, Placa, Codigo from Vehiculo ORDER BY Nombre");
            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();

            resultSet.last();                // estas lineas se mueven al final de la consulta 

            int numrows = resultSet.getRow();
            int numcolumn = metaData.getColumnCount();

            dataVehiculo = new Vehiculo[numrows];  //la cantidad de personal que va a salir 

            resultSet.beforeFirst();

            int pos = 0;
            while (resultSet.next()) {
                String nom = "";
                String placa = "";
                int cod = 0;

                for (int i = 0; i <= numcolumn; i++) {
                    switch (i) {
                        case 1:
                            nom = resultSet.getString(1);
                            break;
                        case 3:
                            cod = resultSet.getInt(3);
                            break;
                        case 2:
                            placa = resultSet.getString(2);
                            break;

                    }
                }

                dataVehiculo[pos] = new Vehiculo(nom, placa, cod);
                pos++;
            }

            comboVehiculo.removeAllItems();  // limpio el comboBox

            for (int i = 0; i <= dataVehiculo.length - 1; i++) {
                comboVehiculo.addItem(dataVehiculo[i].getNombre() + " / " + dataVehiculo[i].getPlaca());
            }

        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }

        comboVehiculo.setSelectedIndex(-1);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        comboMaterial = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        cantidadMaterial = new javax.swing.JTextField();
        labelPres = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        precioUnitario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        totalDinero = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboPersonal = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tarifaPersonal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        comboVehiculo = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        gananciaCar = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        comboProveedor = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ckil = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cobs = new javax.swing.JTextArea();
        calendar = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        nameframe = new javax.swing.JLabel();
        labelCheckOutMaterial = new javax.swing.JLabel();
        labelCheckOutPersonal = new javax.swing.JLabel();
        labelCheckOutVehiculo = new javax.swing.JLabel();
        labelCheckOutProveedor = new javax.swing.JLabel();
        labelCheckOutViaje = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        comboMaterial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMaterialActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png"))); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cantidadMaterial.setFont(new java.awt.Font("Raavi", 0, 24)); // NOI18N
        cantidadMaterial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cantidadMaterial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantidadMaterialKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadMaterialKeyTyped(evt);
            }
        });

        labelPres.setText("Mts3");

        jLabel2.setText("Material ");

        jLabel8.setText("Cantidad");

        jLabel9.setText("Precio Unitario");

        precioUnitario.setEditable(false);
        precioUnitario.setFont(new java.awt.Font("Raavi", 0, 24)); // NOI18N
        precioUnitario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        precioUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precioUnitarioKeyTyped(evt);
            }
        });

        jLabel10.setText("Total Dinero     Q.");

        totalDinero.setFont(new java.awt.Font("Raavi", 0, 24)); // NOI18N
        totalDinero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalDinero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalDineroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalDineroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalDinero, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                            .addComponent(comboMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelPres, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(precioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPres, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        panelTab.addTab("Material Ingreso", jPanel1);

        jLabel3.setText("Personal");

        comboPersonal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Q.");

        tarifaPersonal.setFont(new java.awt.Font("Raavi", 0, 24)); // NOI18N
        tarifaPersonal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tarifaPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifaPersonalActionPerformed(evt);
            }
        });
        tarifaPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tarifaPersonalKeyTyped(evt);
            }
        });

        jLabel5.setText("Tarifa");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png"))); // NOI18N
        jButton3.setText("Ingresar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 257, Short.MAX_VALUE))
                    .addComponent(comboPersonal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tarifaPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(165, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tarifaPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelTab.addTab("Personal Responsable", jPanel2);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png"))); // NOI18N
        jButton4.setText("Ingresar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setText("Camión");

        comboVehiculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setText("Ganancia");

        jLabel16.setText("Q.");

        gananciaCar.setFont(new java.awt.Font("Raavi", 0, 24)); // NOI18N
        gananciaCar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gananciaCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gananciaCarActionPerformed(evt);
            }
        });
        gananciaCar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                gananciaCarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 565, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(comboVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(gananciaCar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(234, 234, 234))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gananciaCar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelTab.addTab("Vehiculo Responsable", jPanel6);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png"))); // NOI18N
        jButton5.setText("Ingresar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setText("Proveedor");

        comboProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboProveedor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 565, Short.MAX_VALUE)
                                .addComponent(jButton5)))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(599, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelTab.addTab("Datos del Proveedor", jPanel4);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png"))); // NOI18N
        jButton6.setText("Ingresar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel11.setText("Datos del Viaje");

        jLabel12.setText("Kilometraje");

        ckil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ckilKeyTyped(evt);
            }
        });

        jLabel13.setText("Observaciones");

        cobs.setColumns(20);
        cobs.setLineWrap(true);
        cobs.setRows(5);
        jScrollPane1.setViewportView(cobs);

        jLabel14.setText("Fecha");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ckil, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(calendar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckil, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTab.addTab("Datos del Viaje", jPanel5);

        jPanel3.setBackground(new java.awt.Color(116, 186, 232));

        nameframe.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        nameframe.setForeground(new java.awt.Color(255, 255, 255));
        nameframe.setText("Viaje de Ingreso de Material");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameframe, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameframe, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        labelCheckOutMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        labelCheckOutMaterial.setText("Material ");
        labelCheckOutMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCheckOutMaterialMouseClicked(evt);
            }
        });

        labelCheckOutPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        labelCheckOutPersonal.setText("Personal");
        labelCheckOutPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCheckOutPersonalMouseClicked(evt);
            }
        });

        labelCheckOutVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        labelCheckOutVehiculo.setText("Vehiculo");
        labelCheckOutVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCheckOutVehiculoMouseClicked(evt);
            }
        });

        labelCheckOutProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        labelCheckOutProveedor.setText("Proveedor");
        labelCheckOutProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCheckOutProveedorMouseClicked(evt);
            }
        });

        labelCheckOutViaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        labelCheckOutViaje.setText("Viaje");
        labelCheckOutViaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCheckOutViajeMouseClicked(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/end.png"))); // NOI18N
        jButton2.setText(" Finalizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelTab, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelCheckOutMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelCheckOutPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelCheckOutVehiculo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelCheckOutProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCheckOutViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(27, 27, 27))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(labelCheckOutMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCheckOutPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCheckOutVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCheckOutProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCheckOutViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tarifaPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifaPersonalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifaPersonalActionPerformed

    private void comboMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMaterialActionPerformed
        // Cambiar la presentacion
        if (comboMaterial.getSelectedIndex() != -1) {
            labelPres.setText("" + dataMaterialList[comboMaterial.getSelectedIndex()].getPres());
        }
    }//GEN-LAST:event_comboMaterialActionPerformed
    public boolean checkMaterial() {
        double cMat = 0;
        double tot = 0;
        double precioUni = 0;
        try {
            cMat = Double.parseDouble(cantidadMaterial.getText());
        } catch (Exception e) {
            cMat = 0;
        }

        try {
            tot = Double.parseDouble(totalDinero.getText());
        } catch (Exception e) {
            tot = 0;
        }

        precioUni = tot / cMat;

        precioUnitario.setText("" + money.format(precioUni));

        if (comboMaterial.getSelectedIndex() != -1 && cMat > 0 && cMat != 0 && tot > 0 && tot != 0) {
            labelCheckOutMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));

            return true;
        } else {
            labelCheckOutMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
            return false;
        }

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (checkMaterial()) {
            panelTab.setSelectedIndex(1);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public boolean checkPersonal() {
        double cMat = 0; // Tarifa Personal 
        try {
            cMat = Double.parseDouble(tarifaPersonal.getText());
        } catch (Exception e) {
            cMat = 0;
        }
        if (comboPersonal.getSelectedIndex() != -1 && cMat >= 0) {
            labelCheckOutPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));

            return true;
        } else {
            labelCheckOutPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
            return false;
        }

    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (checkPersonal()) {
            panelTab.setSelectedIndex(2);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    public boolean checkCar() {

        double entradaCarro = 0; // Ganancia Vehiculo 
        try {
            entradaCarro = Double.parseDouble(gananciaCar.getText());
        } catch (Exception e) {
            entradaCarro = 0;
        }

        if (comboVehiculo.getSelectedIndex() != -1 && entradaCarro >= 0) {
            labelCheckOutVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));

            return true;
        } else {
            labelCheckOutVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
            return false;
        }

    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (checkCar()) {
            panelTab.setSelectedIndex(3);
        }

    }//GEN-LAST:event_jButton4ActionPerformed
    public boolean checkTrip() {
        double cMat = 0;
        try {
            cMat = Double.parseDouble(ckil.getText());
        } catch (Exception e) {
            cMat = 0;
        }

        if (cMat >= 0) {
            labelCheckOutViaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));

            return true;
        } else {
            labelCheckOutViaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
            return false;
        }

    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (checkTrip()) {
            panelTab.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jButton6ActionPerformed
    public boolean checkProveedor() {

        if (comboProveedor.getSelectedIndex() != -1) {
            labelCheckOutProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));

            return true;
        } else {
            labelCheckOutProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
            return false;
        }

    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (checkProveedor()) {
            panelTab.setSelectedIndex(4);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cantidadMaterialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadMaterialKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == evt.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || ((c == KeyEvent.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_cantidadMaterialKeyTyped

    private void tarifaPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifaPersonalKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == evt.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || ((c == KeyEvent.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_tarifaPersonalKeyTyped

    private void ckilKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ckilKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == evt.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || ((c == KeyEvent.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_ckilKeyTyped

    private void comboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboProveedorActionPerformed
    public void ModificarViaje() throws FileNotFoundException {

        try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("UPDATE Viaje SET Kilometraje = ?,Tipo_viaje = ?,Codigo_Proveedor = ?,Observaciones = ? WHERE codigo =" + codigoToChange);

            // kilometraje
            ps.setDouble(1, Double.parseDouble(ckil.getText()));
            // Tipo de viaje // esto lo que me hara es que al momento de hacer reportes me dara solo los viajes que sean de material de ingreso, porque aparte existiran los viajes que son para clientes 
            ps.setString(2, "IngresoMaterial");  // Es aqui donde declaro que se llamara IngresoMaterial asi cuando genere el reporte solo llamare a los tipos asi. 
            // Codigo Proveedor
            ps.setInt(3, dataProveedor[comboProveedor.getSelectedIndex()].getCodigo());
            //Observaciones
            ps.setString(4, cobs.getText());

            resultado = ps.executeUpdate();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(rootPane, e);
            processGood = false;

        } // fin del catch

        Ingresar_Fecha("Update Viaje set Fecha =\"", codigoToChange); // Fecha del viaje 
        processGood = true;
    }

    public void Ingresar_Fecha(String preConfig, int cod) {
        //Fecha
        Date date = (Date) calendar.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = format.format(date);
        DataBaseClass.executeQuery(preConfig + fecha + "\" where codigo=" + cod);
    }

    public void modificarMovimientoVehiculo() {
        /* -------------------------------------------------------
         *  El movimiento del vehiculo, lo consideramos complejo 
         * ya que tiene movimiento en dinero, debido a que hay 
         * ganancias del camion asi como tambien gastos, 
         * y tambien por otro lado el camion tiene un manejo de 
         * kilometraje, y eso nos es util para saber cuando ha 
         * recorrido en cierto determinado tiempo o cuando ha gastado 
         * o bien cuando dinero nos ha dado. 
         * -------------------------------------------------------
         */

        int cod = -1;

        try {

            // Como primer punto primero vamos a ingresar el movimiento del dinero que va 
            // relacionado a la tabla movimiento_vehiculo. 
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("UPDATE Movimiento_Vehiculo SET Codigo_Viaje =?,Codigo_Vehiculo=?,Total_Dinero = ? WHERE codigo_viaje =  " + codigoToChange, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, codigoToChange);
            ps.setInt(2, dataVehiculo[comboVehiculo.getSelectedIndex()].getCodigo());
            ps.setDouble(3, Double.parseDouble(gananciaCar.getText()));

            resultado = ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cod = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            processGood = false;
        }

        Ingresar_Fecha("Update Movimiento_Vehiculo set Fecha =\"", cod); // Fecha del Movimineto del Vehiculo del dinero  
        processGood = true;

        /*
         * Una vez ya ingrese el movimiento del Vehiculo del dinero y en este caso 
         * ganancia del viaje ahora ingresare el movimiento del Vehiculo 
         * pero de los kilometros que recorrio en este viaje 
         */
        cod = -1;  // Volvemos hacer lo mismo que hicimos arriba 

        try {

              // Este ingreso se relaciona a la tabla de Kilometraje_Vehiculo 
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Kilometraje_vehiculo (Codigo_Viaje,Codigo_Vehiculo,kilometraje) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, codigoToChange);
            ps.setInt(2, dataVehiculo[comboVehiculo.getSelectedIndex()].getCodigo());
            ps.setDouble(3, Double.parseDouble(ckil.getText()));

            resultado = ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cod = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            processGood = false;
        }

        Ingresar_Fecha("Update Kilometraje_Vehiculo set Fecha =\"", cod); // Fecha del Movimineto del Vehiculo del dinero  
        processGood = true;

    }

    public void modificarMovimientoPersonal() {
        /* -------------------------------------------------------
         *  Este metodo se refiere al ingreso del movimiento del personal
         * -------------------------------------------------------
         */

        int cod = -1;

        try {

            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("UPDATE Movimiento_Personal SET Codigo_Viaje = ? ,Codigo_Personal  = ?  ,Total_en_Q  = ? WHERE codigo_viaje = " + codigoToChange + " AND codigo_produccion IS NULL", PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, codigoToChange);
            ps.setInt(2, dataPersonal[comboPersonal.getSelectedIndex()].getCodigo());
            ps.setDouble(3, Double.parseDouble(tarifaPersonal.getText()));

            resultado = ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cod = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            processGood = false;
        }

        Ingresar_Fecha("Update Movimiento_Personal set Fecha =\"", cod); // Fecha del Movimineto del Personal del dinero  
        processGood = true;

    }

    public void modificarMovimientoMateria() {
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
            ps = (PreparedStatement) connection.prepareStatement("UPDATE Movimiento_Material SET Codigo_Material_Entrante  = ?  , Codigo_Material  = ? ,Cantidad  = ? , Precio_Unitario  = ? , Total  = ?  ,Existencia  = ?  WHERE codigo_material_entrante =  " + codigoToChange + " AND codigo_produccion IS NULL", PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, codigoToChange);    // codigo del viaje que seria el codigo de donde el material entra 
            ps.setInt(2, dataMaterialList[comboMaterial.getSelectedIndex()].getCodigo()); // Codigo Materia Prima 
            ps.setDouble(3, Double.parseDouble(cantidadMaterial.getText()));  // Cantidad de Materia Prima 
            try {
                ps.setDouble(4, money.parse(precioUnitario.getText()).doubleValue()); // Este caso particular es para obtener el valor con el formato de money

            } catch (ParseException ex) {
                processGood = false;
            }

            ps.setDouble(5, Double.parseDouble(totalDinero.getText()));
            ps.setInt(6, 1);  // Querra decir que es de tipo entrada 

            resultado = ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cod = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            processGood = false;
        }

        Ingresar_Fecha("Update Movimiento_Material set Fecha =\"", cod); // Fecha del Movimineto del Personal del dinero  
        processGood = true;

    }

    private boolean checkAvaility() {

        int codigo = -1;
        double ingresado = 0;
        double gastado = 0;
        double balance = 0;
        String nombre = "";
        ResultSetMetaData metaData;
        int numcolumn = -1;
        double aCambiar = 0;
        String error = "";
        String unidades = "";

        codigo = dataMaterialList[comboMaterial.getSelectedIndex()].getCodigo();
        nombre = dataMaterialList[comboMaterial.getSelectedIndex()].getNombre();
        aCambiar = Double.parseDouble(cantidadMaterial.getText().toString());
        unidades = dataMaterialList[comboMaterial.getSelectedIndex()].getPres();

        try {
            // Aqui no es un prepared Statement porque solo es uno 
            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT SUM(Cantidad) FROM movimiento_material WHERE codigo_produccion IS NULL AND codigo_material_entrante IS NOT NULL AND existencia = 1 AND codigo_material =" + codigo + " AND codigo_material_entrante !=" + codigoToChange + ";");
            metaData = (ResultSetMetaData) resultSet.getMetaData();
            numcolumn = metaData.getColumnCount(); // numero de Columnas

            while (resultSet.next()) {
                ingresado = resultSet.getDouble(1);
            }

            // Aqui no es un prepared Statement porque solo es uno 
            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT SUM(Cantidad) FROM movimiento_material WHERE codigo_produccion IS NOT NULL AND codigo_material_entrante IS NOT NULL AND existencia = 2 AND codigo_material =" + codigo + ";");
            metaData = (ResultSetMetaData) resultSet.getMetaData();
            numcolumn = metaData.getColumnCount(); // numero de Columnas

            while (resultSet.next()) {
                gastado = resultSet.getDouble(1);
            }

            balance = (ingresado + aCambiar) - gastado;
            if (balance < 0) {
                error += (-balance) + " " + unidades + " de " + nombre + "\n";
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al ver existencia en materia Prima, no puede crear la produccion\n" + ex, "Dato incoherente", 0);
        }

        if (error.isEmpty()) {

            return true;
        } else {
            JOptionPane.showMessageDialog(rootPane, "No puede cambiar la cantidad de material de este viaje menor a el total usado en producción, faltaria : \n" + error, "Dato incoherente", 0);
            cantidadMaterial.setText("" + cantidadOriginal);
            return false;

        }

    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /*
         Aqui sucede algo intersante y es que antes de crear la produccion eliminare lo anterior 
         a esta produccion y la produccion. 
         */
        
        if (checkCar() == true && checkMaterial() == true && checkPersonal() == true && checkProveedor() == true && checkTrip() == true && checkAvaility() == true) {
                // Si ya lo checke me indica a mi que ya solo es de ingresar los datos y ya. 
            //Ingreso Normal del Viaje
            try {

                //  Ingreso el viaje 
                ModificarViaje();

                // Como los demas ingresos dependen del viaje, preguntare si va bien el proceso, sino no hare nada. 
                if (processGood) {
                    // Ingreso el Movimiento del Vehiculo 
                    modificarMovimientoVehiculo();

                    // Ingreso del movimiento del personal involucrados en el Viaje 
                    modificarMovimientoPersonal();

                    // Ingreso del movimiento de la materia prima de ingreso por el viaje. 
                    modificarMovimientoMateria();

                }

                if (processGood) // si el proceso estuvo bien en todos los casos entonces sucede esto 
                // Conclusión de ingreso 
                {
                    JOptionPane.showMessageDialog(rootPane, "Ha concluido con éxito la instrucción.");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Sucedió un error al ingresar el viaje, revise el proceso, repita el proceso. \n");
                }

                // Reconfigurar todo para poder apreciar el cambio     
                Index.refreshTable("Ingreso de Material");

                // Termino de Reconfigurar todo 
                this.dispose(); // Oculto esta ventana 

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(rootPane, "Sucedió un error al ingresar el viaje, revise el proceso, repita el proceso. \n" + ex);

            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Faltan datos para completar esta instrucción, favor revisar de nuevo. ");
            // nothing to do porque le faltan datos para concluir esta instruccion  
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void precioUnitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioUnitarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_precioUnitarioKeyTyped

    private void totalDineroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalDineroKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == evt.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || ((c == KeyEvent.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_totalDineroKeyTyped

    private void gananciaCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gananciaCarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gananciaCarActionPerformed

    private void gananciaCarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gananciaCarKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == evt.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || ((c == KeyEvent.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_gananciaCarKeyTyped

    private void totalDineroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalDineroKeyReleased
        checkMaterial();
    }//GEN-LAST:event_totalDineroKeyReleased

    private void cantidadMaterialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadMaterialKeyReleased
        checkMaterial();
    }//GEN-LAST:event_cantidadMaterialKeyReleased

    private void labelCheckOutMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCheckOutMaterialMouseClicked
        panelTab.setSelectedIndex(0);
    }//GEN-LAST:event_labelCheckOutMaterialMouseClicked

    private void labelCheckOutPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCheckOutPersonalMouseClicked
        panelTab.setSelectedIndex(1);
    }//GEN-LAST:event_labelCheckOutPersonalMouseClicked

    private void labelCheckOutVehiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCheckOutVehiculoMouseClicked
        panelTab.setSelectedIndex(2);
    }//GEN-LAST:event_labelCheckOutVehiculoMouseClicked

    private void labelCheckOutProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCheckOutProveedorMouseClicked
        panelTab.setSelectedIndex(3);
    }//GEN-LAST:event_labelCheckOutProveedorMouseClicked

    private void labelCheckOutViajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCheckOutViajeMouseClicked
        panelTab.setSelectedIndex(4);
    }//GEN-LAST:event_labelCheckOutViajeMouseClicked

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
            java.util.logging.Logger.getLogger(CheckMaterialIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckMaterialIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckMaterialIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckMaterialIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckMaterialIn().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser calendar;
    private javax.swing.JTextField cantidadMaterial;
    private javax.swing.JTextField ckil;
    private javax.swing.JTextArea cobs;
    private javax.swing.JComboBox comboMaterial;
    private javax.swing.JComboBox comboPersonal;
    private javax.swing.JComboBox comboProveedor;
    private javax.swing.JComboBox comboVehiculo;
    private javax.swing.JTextField gananciaCar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCheckOutMaterial;
    private javax.swing.JLabel labelCheckOutPersonal;
    private javax.swing.JLabel labelCheckOutProveedor;
    private javax.swing.JLabel labelCheckOutVehiculo;
    private javax.swing.JLabel labelCheckOutViaje;
    private javax.swing.JLabel labelPres;
    public static javax.swing.JLabel nameframe;
    private javax.swing.JTabbedPane panelTab;
    private javax.swing.JTextField precioUnitario;
    private javax.swing.JTextField tarifaPersonal;
    private javax.swing.JTextField totalDinero;
    // End of variables declaration//GEN-END:variables
}
