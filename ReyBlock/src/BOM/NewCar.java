package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class NewCar extends javax.swing.JFrame {

    boolean verificacion = false;
    // Variables de base de datos 
    private Statement st = DataBaseClass.getSt();
    private Connection connection = DataBaseClass.getConnection();
    private ResultSet resultSet = DataBaseClass.getResultSet();
    int cod = 0;

    public NewCar() {
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");
        
        initComponents();
        
        //Color 
        jPanel3.setBackground(ConfigClass.getColorApp());
        
        // cambio la fecha del calendar del mold
        calendarMold.setDate(Calendar.getInstance().getTime());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        nameframe = new javax.swing.JLabel();
        cnom = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        calendarMold = new com.toedter.calendar.JDateChooser();
        cpla = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ccol = new javax.swing.JTextField();
        typeCar = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(116, 186, 232));

        nameframe.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        nameframe.setForeground(new java.awt.Color(255, 255, 255));
        nameframe.setText("Nuevo Vehículo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameframe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameframe)
        );

        cnom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnomActionPerformed(evt);
            }
        });
        cnom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cnomKeyTyped(evt);
            }
        });

        jLabel10.setText("Tipo de Vehículo");
        jLabel10.setFocusable(false);

        jLabel2.setText("Nombre del Vehículo");
        jLabel2.setFocusable(false);

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

        jLabel4.setText("Fecha de Adquisición");
        jLabel4.setFocusable(false);

        jLabel3.setText("Placa");
        jLabel3.setFocusable(false);

        jLabel5.setText("Color");
        jLabel5.setFocusable(false);

        typeCar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Camión de Doble Eje","Camión de una Tonelada","Camión Sencillo","Camioneta","Fugón","Pick-Up","Rastas","Sedán","Trailer"}));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cnom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(typeCar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cpla, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendarMold, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ccol, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 43, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(25, 25, 25))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {calendarMold, ccol, cnom, cpla, typeCar});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton3});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel3, jLabel4, jLabel5});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel10)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel4)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(typeCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(calendarMold, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cpla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ccol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {calendarMold, ccol, cnom, cpla, typeCar});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel3, jLabel4, jLabel5});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton3});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnomActionPerformed

}//GEN-LAST:event_cnomActionPerformed

    private void cnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnomKeyTyped

}//GEN-LAST:event_cnomKeyTyped
    public void Insertar() throws FileNotFoundException {
        try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO vehiculo (Nombre,Placa,Color,Tipo_Vehiculo) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            try {

                if (cnom.getText() == null ? "" == null : cnom.getText().equals("")) {

                    JOptionPane.showMessageDialog(this, "Error, no ingreso datos en Nombre del Vehiculo");
                    cnom.setBackground(Color.red);

                } else {
                    ps.setString(1, cnom.getText());
                }
            } catch (Exception e) {

            }
            // placa 

            try {

                if (cpla.getText() == null ? "" == null : cpla.getText().equals("")) {

                    JOptionPane.showMessageDialog(this, "Error, no ingreso datos en Placa del Vehiculo");
                    cpla.setBackground(Color.red);

                } else {
                    ps.setString(2, cpla.getText());
                }
            } catch (Exception e) {

            }

            // Color 
            try {

                if (ccol.getText() == null ? "" == null : ccol.getText().equals("")) {

                    JOptionPane.showMessageDialog(this, "Error, no ingreso datos en Color del Vehiculo");
                    ccol.setBackground(Color.red);

                } else {
                    ps.setString(3, ccol.getText());
                }
            } catch (Exception e) {

            }

          // Tipo de Vehiculo 
            ps.setString(4, typeCar.getSelectedItem().toString());

            // Ejecuta Instruccion    
            resultado = ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cod = resultSet.getInt(1);

            }

            JOptionPane.showMessageDialog(rootPane, "Dato ingresado exitosamente");
            verificacion = true;

        } catch (SQLException e) {
            verificacion = false;
            JOptionPane.showMessageDialog(rootPane, e);

        } // fin del catch
        if (verificacion == true) {
            Ingresar_Fecha();
        }
    }

    public void Ingresar_Fecha() {
        //Fecha
        Date date = (Date) calendarMold.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = format.format(date);
        DataBaseClass.executeQuery("Update vehiculo set fecha_de_compra =\"" + fecha + "\" where codigo=" + cod);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            Insertar();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewCar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (verificacion == true) {
            dispose();

        }
        // reconfiguro todo
        Index.refreshTable("Vehiculo");
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
                new NewCar().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser calendarMold;
    private javax.swing.JTextField ccol;
    private javax.swing.JTextField cnom;
    private javax.swing.JTextField cpla;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JLabel nameframe;
    private javax.swing.JComboBox typeCar;
    // End of variables declaration//GEN-END:variables
}
