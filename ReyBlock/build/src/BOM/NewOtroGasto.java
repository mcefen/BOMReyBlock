package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class NewOtroGasto extends javax.swing.JFrame {

    private TipoGasto dataTipoGastoList[];
    // Variables de base de datos 
    private Statement st = DataBaseClass.getSt();
    private Connection connection = DataBaseClass.getConnection();
    private ResultSet resultSet = DataBaseClass.getResultSet();

    public NewOtroGasto(int codMolde) {

        // Have to work on it, it is the modify 
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");

        initComponents();
        //Color 
        jPanel3.setBackground(ConfigClass.getColorApp());

        // Coloco la fecha de hoy en el calendario. 
        dateStart.setDate(java.util.Calendar.getInstance().getTime());

    }

    public NewOtroGasto() {
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");

        initComponents();

        //Color 
        jPanel3.setBackground(ConfigClass.getColorApp());

        nameframe.setText("Nuevo Otro Gasto");
        // Coloco la fecha de hoy en el calendario. 
        dateStart.setDate(java.util.Calendar.getInstance().getTime());
        dateEnd.setDate(java.util.Calendar.getInstance().getTime());
        tipoGastoList(); // Listado de tipo de gasto . 

    }

    private class TipoGasto {

        private int codigoGasto;
        private String nombreGasto;

        public TipoGasto(int codigo, String nombre) {
            this.codigoGasto = codigo;
            this.nombreGasto = nombre;
        }

        public int getCodigo() {
            return codigoGasto;
        }

        public String getNombre() {
            return nombreGasto;
        }

    }

    /*
     Configuro el combo box tipo de gasto */
    private void tipoGastoList() {

        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT * FROM tipo_gasto "
                    + " WHERE codigo = 3 "
                    + " OR codigo = 4 "
                    + " OR codigo = 5 "
                    + " OR codigo = 6 "
                    + " ORDER BY etiqueta_gasto");
            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();

            resultSet.last();                // estas lineas se mueven al final de la consulta 

            int numrows = resultSet.getRow();
            int numcolumn = metaData.getColumnCount();

            dataTipoGastoList = new TipoGasto[numrows];  //la cantidad de material que va a salir 

            resultSet.beforeFirst();

            int pos = 0;
            while (resultSet.next()) {
                String nom = "";
                int cod = 0;

                for (int i = 0; i <= numcolumn; i++) {
                    switch (i) {
                        case 2:
                            nom = resultSet.getString(2);
                            break;
                        case 1:
                            cod = resultSet.getInt(1);
                            break;

                    }
                }

                dataTipoGastoList[pos] = new TipoGasto(cod, nom);
                pos++;
            }

            tipoGastoBox.removeAllItems();  // limpio el comboBox

            for (int i = 0; i <= dataTipoGastoList.length - 1; i++) {
                tipoGastoBox.addItem("" + dataTipoGastoList[i].getNombre());
            }

        } catch (SQLException ex) {
            Logger.getLogger(NewOtroGasto.class.getName()).log(Level.SEVERE, null, ex);
        }

        AutoCompleteDecorator.decorate(tipoGastoBox);
        tipoGastoBox.setSelectedIndex(-1);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        nameframe = new javax.swing.JLabel();
        totalField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        dateStart = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacionesField = new javax.swing.JTextArea();
        tipoGastoBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dateEnd = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(116, 186, 232));

        nameframe.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        nameframe.setForeground(new java.awt.Color(255, 255, 255));
        nameframe.setText("Nuevo Otro Gasto");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameframe, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameframe)
        );

        totalField.setText("0.00");
        totalField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalFieldKeyTyped(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<html>Total en <br/>factura ");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setFocusable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png"))); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        jButton3.setText("Cancerlar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Fecha inicial");
        jLabel4.setFocusable(false);

        observacionesField.setColumns(20);
        observacionesField.setLineWrap(true);
        observacionesField.setRows(5);
        jScrollPane1.setViewportView(observacionesField);

        tipoGastoBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Observaciones");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setFocusable(false);

        jLabel8.setText("Fecha Final");
        jLabel8.setFocusable(false);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tipo de gasto");
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel9.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tipoGastoBox, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton3});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dateEnd, dateStart});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel6, jLabel9});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel4, jLabel8});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(dateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoGastoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton3});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel8, tipoGastoBox, totalField});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dateEnd, dateStart});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel6, jLabel9});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void totalFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalFieldKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_totalFieldKeyTyped

    private static java.sql.Date getCurrentDate(com.toedter.calendar.JDateChooser calendar) {
        java.util.Date today;
        today = calendar.getDate();
        return new java.sql.Date(today.getTime());
    }

    public void ingresarMovimientoPersonal() {

        int resultado = 0;
        try {

            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO gasto_empresa (tipo_de_gasto, fecha_inicial, observaciones, total, fecha_final) VALUES (?,?,?,?,?)");
            
            // codigo tipo de gasto  
            ps.setInt(1, dataTipoGastoList[tipoGastoBox.getSelectedIndex()].getCodigo());

            // fecha inicial 
            ps.setDate(2, getCurrentDate(dateStart));
            
            // observaciones 
            ps.setString(3, observacionesField.getText());
            
            // total 
            ps.setDouble(4, Double.parseDouble(totalField.getText()));
           
            // fecha final 
            ps.setDate(5, getCurrentDate(dateEnd));

            

            resultado = ps.executeUpdate();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al ingresar datos : Otros gastos <br/>" + e + " </p></pre></body></html>", "Error al ingresar datos a la base de datos", 0);

        } // fin del catch

    }

    private boolean checkAvaible() {
        double total = 0;
        String error = "";
        boolean dateCorrect = false;
        boolean dateCorrect2 = false;

        try {

            total = Double.parseDouble(totalField.getText()); // Miro si es un numero valido 

        } catch (Exception e) {
            total = 0.00;
            error += "Error en total, debe contener una cifra coherente. <br/>";
        }

        if (total == 0.00) {
            error += "Error en total, no puede ser 0 su valor <br/>";
        }

        if (tipoGastoBox.getSelectedIndex() == -1) {
            error += "Error en tipo de gasto, debe de seleccionar un dato. ";
        }

        
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");

       
    
        java.util.Date date1;
        date1 = dateStart.getDate();
        try {
            date1 = sdf.parse(sdf.format(date1));
        } catch (ParseException ex) {
            date1 = null;
        }

        java.util.Date date2;
        date2 = dateEnd.getDate();
        try {
            date2 = sdf.parse(sdf.format(date2));
        } catch (ParseException ex) {
            date2 = null;
        }
        

        if (date1.before(date2)) {
            dateCorrect = true;
        } else {
            dateCorrect = false;
            error += "Error en fecha, la fecha final debe ser mayor a la fecha inicial. <br/>";
        }

        
        
        if (date1.getTime()!=(date2.getTime())) {
            dateCorrect2 = true;
        } else {
            dateCorrect2 = false;
            error += "Error en fecha, la fecha final no puede ser igual a la fecha inicial. <br/>";
        }
        

        if (error.isEmpty() && total != 0.00 && dateCorrect && dateCorrect2) {

            return true;

        } else {
            JOptionPane.showMessageDialog(rootPane, "<html><body><pre><p style='width: 400px;'>Error al ingresar datos : <br/>" + error + "</p></pre></body></html>", "Error al ingresar datos a la base de datos", 0);
            return false;
        }

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (checkAvaible()) {
            
             ingresarMovimientoPersonal(); // Siempre va a ingresar el dato adecuadamente por la condicion anterior 

             JOptionPane.showMessageDialog(rootPane, "Dato ingresado exitosamente");
             // reconfiguro todo
             Index.refreshTable("Ingreso de Otros Gastos");
             dispose();
             
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();

    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NewOtroGasto().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static com.toedter.calendar.JDateChooser dateEnd;
    private static com.toedter.calendar.JDateChooser dateStart;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel nameframe;
    private javax.swing.JTextArea observacionesField;
    private javax.swing.JComboBox tipoGastoBox;
    private javax.swing.JTextField totalField;
    // End of variables declaration//GEN-END:variables
}
