package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class NewMold extends javax.swing.JFrame {

    boolean verificacion = false;
    int cod = 0;
    // Variables de base de datos 
    private Statement st = DataBaseClass.getSt();
    private Connection connection = DataBaseClass.getConnection();
    private ResultSet resultSet = DataBaseClass.getResultSet();

    public NewMold() {
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
        clar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        canc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        calt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        calendarMold = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(116, 186, 232));

        nameframe.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        nameframe.setForeground(new java.awt.Color(255, 255, 255));
        nameframe.setText("Nuevo Molde");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameframe, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        clar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clarKeyTyped(evt);
            }
        });

        jLabel10.setText("Largo (cm)");
        jLabel10.setFocusable(false);

        canc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancActionPerformed(evt);
            }
        });
        canc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cancKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cancKeyTyped(evt);
            }
        });

        jLabel3.setText("Ancho (cm)");
        jLabel3.setFocusable(false);

        jLabel2.setText("Nombre del Molde");
        jLabel2.setFocusable(false);

        jLabel6.setText("Alto (cm)");
        jLabel6.setFocusable(false);

        calt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caltActionPerformed(evt);
            }
        });
        calt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                caltKeyTyped(evt);
            }
        });

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

        jLabel4.setText("Fecha");
        jLabel4.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(calendarMold, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel2))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(canc, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(calt, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {calendarMold, calt, canc, clar, cnom});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(clar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(canc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(calt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendarMold, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {calendarMold, calt, canc, clar, cnom});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnomActionPerformed

}//GEN-LAST:event_cnomActionPerformed

    private void cnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnomKeyTyped

}//GEN-LAST:event_cnomKeyTyped

    private void clarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clarKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == evt.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || ((c == KeyEvent.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }
}//GEN-LAST:event_clarKeyTyped

    private void cancActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancActionPerformed

}//GEN-LAST:event_cancActionPerformed

    private void caltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caltActionPerformed

}//GEN-LAST:event_caltActionPerformed

    private void cancKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cancKeyReleased

    private void cancKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_cancKeyTyped

    private void caltKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caltKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_caltKeyTyped
    public void Insertar() throws FileNotFoundException {
        try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("insert into molde (Nombre,Largo,Ancho,Alto) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            try {

                if (cnom.getText() == null ? "" == null : cnom.getText().equals("")) {

                    JOptionPane.showMessageDialog(this, "Error, no ingreso datos en Nombre del Molde");
                    cnom.setBackground(Color.red);

                } else {
                    ps.setString(1, cnom.getText()); // con el ps genero lo qee voy a ingresar
                    //lo genero en orden el 1 va a ser el dato uno en este caso seria nombre
                    // y como nombre es varchar tonces sera string NO HAY ERROR 
                }
            } catch (Exception e) {

            }
            // largo 
            try {

                ps.setDouble(2, Double.parseDouble(clar.getText()));

            } catch (Exception e) {

            }
            // ancho
            try {

                ps.setDouble(3, Double.parseDouble(canc.getText()));

            } catch (Exception e) {

            }

            // alto
            try {

                ps.setDouble(4, Double.parseDouble(calt.getText()));

            } catch (Exception e) {

            }

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
        DataBaseClass.executeQuery("Update molde set fecha_compra =\"" + fecha + "\" where codigo=" + cod);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            Insertar();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewMold.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (verificacion == true) {
            dispose();

        }
        // reconfiguro todo

        // reconfiguro todo
        Index.refreshTable("Molde");
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
                new NewMold().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser calendarMold;
    private javax.swing.JTextField calt;
    private javax.swing.JTextField canc;
    private javax.swing.JTextField clar;
    private javax.swing.JTextField cnom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JLabel nameframe;
    // End of variables declaration//GEN-END:variables
}
