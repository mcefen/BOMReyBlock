/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * newBlock.java
 *
 * Created on 19/12/2011, 12:07:29 PM
 */
package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CheckMaterial extends javax.swing.JFrame {

   boolean verificacion = false;
   // Variables de base de datos 
   private Statement st = DataBaseClass.getSt();
   private Connection connection = DataBaseClass.getConnection();
   private ResultSet resultSet = DataBaseClass.getResultSet();

   // variables de la coneccion de mysql 

 
   // variables de jalar los datos de proveedores 
   // datos para el combo box del molde. 
   private String data [];
   private String arrayFecha[];
   private int codigoProveedor [];
   private int codigoModificar;
   
   public CheckMaterial(int num) {
       //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");
        
        initComponents();
        
        //Color 
        jPanel3.setBackground(ConfigClass.getColorApp());
        
        this.codigoModificar = num ;
        labelName.setText(""+"Codigo : "+codigoModificar);
        
        /*
        Lo que se hara en este momento es cargar la inforamcion en base 
        al codigo que estamos recibiendo. 
        */
        Object [] data =  new DataBaseClass().giveData("SELECT * FROM materia  WHERE codigo ="+codigoModificar);
        cmat.setText(""+data[1]);
        cres.setText(""+data[2]);
        cmed.setSelectedItem(data[3]);
        ccol.setSelectedItem(data[4]);
        cpres.setSelectedItem(data[5]);
        /*
        Fin de cargar los datos. 
        */
        
        /*
        Para que no me de problemas le coloca la fecha del dia de hoy a todos los calendarios 
        
        */
        calendarIn.setDate(Calendar.getInstance().getTime());
        calendarIn2.setDate(calendarIn.getDate());  // cambio de datos
        
        /* Voy a cargar los datos de cada tabla en especifica y lo voy hacer por metodos */
        changeData1();
    }
        
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        nameframe = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cpres = new javax.swing.JComboBox();
        cres = new javax.swing.JTextField();
        cmat = new javax.swing.JTextField();
        ccol = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmed = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableIn = new javax.swing.JTable();
        bexc1 = new javax.swing.JButton();
        calendarIn = new com.toedter.calendar.JDateChooser();
        calendarIn2 = new com.toedter.calendar.JDateChooser();
        bnew1 = new javax.swing.JButton();
        trash1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bexc = new javax.swing.JButton();
        calendar = new com.toedter.calendar.JDateChooser();
        calendar1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(116, 186, 232));

        nameframe.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        nameframe.setForeground(new java.awt.Color(255, 255, 255));
        nameframe.setText("Chequear Materia Prima");

        labelName.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        labelName.setForeground(new java.awt.Color(255, 255, 255));
        labelName.setText("Nombre del Material ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameframe)
                .addGap(133, 133, 133)
                .addComponent(labelName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(nameframe)
                .addComponent(labelName))
        );

        jLabel10.setText("Medidas por Segundo");
        jLabel10.setFocusable(false);

        jLabel5.setText("Presentación");
        jLabel5.setFocusable(false);

        jLabel4.setText("Color");
        jLabel4.setFocusable(false);

        jLabel2.setText("Materia Prima");
        jLabel2.setFocusable(false);

        cpres.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mt3", "lbs", "oz", "und","quin" }));

        cres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cresActionPerformed(evt);
            }
        });
        cres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cresKeyTyped(evt);
            }
        });

        cmat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmatActionPerformed(evt);
            }
        });
        cmat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cmatKeyTyped(evt);
            }
        });

        ccol.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Gris","Blanco","Negro","Verde","Amarillo","Anaranjado","Rojo","Café" }));

        jLabel3.setText("Medida");
        jLabel3.setFocusable(false);

        cmed.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"3/4","1/4","1/2","3/8","4000 PSI","5800 PSI","Granel","Líquido" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/confirm.png"))); // NOI18N
        jButton1.setText("Confirmado");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ccol, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cpres, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmed, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmat, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cres, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 530, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ccol, cmat, cmed, cpres, cres});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ccol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cpres, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ccol, cmat, cmed, cpres, cres});

        jTabbedPane1.addTab("Información de Materia Prima", jPanel1);

        tableIn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableIn.setRowHeight(50);
        jScrollPane2.setViewportView(tableIn);

        bexc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340308507_application-vnd.ms-excel.png"))); // NOI18N
        bexc1.setText("Excel");

        calendarIn.setFocusCycleRoot(true);
        calendarIn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendarInPropertyChange(evt);
            }
        });

        calendarIn2.setFocusCycleRoot(true);
        calendarIn2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendarIn2PropertyChange(evt);
            }
        });

        bnew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316277158_filenew.png"))); // NOI18N
        bnew1.setText("Nuevo");
        bnew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnew1ActionPerformed(evt);
            }
        });

        trash1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empty.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bexc1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(calendarIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(calendarIn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bnew1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(trash1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bexc1, bnew1, calendarIn, calendarIn2});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(bnew1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bexc1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(calendarIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(calendarIn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(trash1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bexc1, bnew1, calendarIn, calendarIn2});

        jTabbedPane1.addTab("Ingreso de Materia Prima", jPanel4);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        bexc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340308507_application-vnd.ms-excel.png"))); // NOI18N
        bexc.setText("Excel");

        calendar.setFocusCycleRoot(true);

        calendar1.setFocusCycleRoot(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bexc, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bexc, calendar, calendar1});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bexc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(calendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bexc, calendar, calendar1});

        jTabbedPane1.addTab("Uso de Materia Prima", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmatActionPerformed
        
}//GEN-LAST:event_cmatActionPerformed
public void changeData1() {
        String consulta = "";
        
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
                + " AND materia.codigo ="+codigoModificar+""
                + " AND viaje.fecha BETWEEN '"+getCurrentDate(calendarIn)+"' AND '"+getCurrentDate(calendarIn2)+"' "
                + " ORDER BY viaje.fecha DESC";
        
       
        
        tableIn.setModel(new QueryModelTable( consulta,"Ingreso de Material" ) );
        
        /* Tamaño de sus columnas */
        tableIn.setAutoResizeMode(0);
            for (int j=0;j<tableIn.getColumnCount();j++){
                tableIn.getColumnModel().getColumn(j).setPreferredWidth(new TablesModel("Ingreso de Material").getColumnWidth(j));
            }
       
    }

 private static java.sql.Date getCurrentDate(com.toedter.calendar.JDateChooser calendar) {
        java.util.Date today = calendar.getDate(); 
    
    return new java.sql.Date(today.getTime());
} 
    private void cmatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmatKeyTyped
        
}//GEN-LAST:event_cmatKeyTyped

    private void cresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cresKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE) || (c==evt.VK_DELETE) || (c == KeyEvent.VK_PERIOD)|| ((c== KeyEvent.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }
}//GEN-LAST:event_cresKeyTyped
  public void updateData() throws FileNotFoundException {
        try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("UPDATE materia SET Nombre = ?,unidad_segundo = ?,medida = ?,color = ?,presentacion =? WHERE codigo ="+codigoModificar);


            try {

                if (cmat.getText() == null ? "" == null : cmat.getText().equals("")) {

                    JOptionPane.showMessageDialog(this, "Error, no ingreso datos en Materia Prima");
                    cmat.setBackground(Color.red);
                   

                } else {
                    ps.setString(1, cmat.getText()); // con el ps genero lo qee voy a ingresar
                    //lo genero en orden el 1 va a ser el dato uno en este caso seria nombre
                    // y como nombre es varchar tonces sera string NO HAY ERROR 
                }
            } catch (Exception e) {
               
            }
            // Votes * segundo  
           try {

                ps.setDouble(2, Double.parseDouble(cres.getText()));

            } catch (Exception e) {
                ps.setInt(2, 0);
            }   
     // Medida
           
           ps.setString(3, cmed.getSelectedItem().toString());
           
         
              
              //color
            
               
                    ps.setString(4, ccol.getSelectedItem().toString()
                            ); // con el ps genero lo qee voy a ingresar
             
          // presentacion    
            

           
                    ps.setString(5, cpres.getSelectedItem().toString()); 
                
           
     
              resultado = ps.executeUpdate();

            JOptionPane.showMessageDialog(rootPane, "Dato ingresado exitosamente" );
            verificacion = true;

            

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(rootPane, e);
        } // fin del catch
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
          
            updateData();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CheckMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (verificacion == true) {
            dispose();
            
            
        }
        // reconfiguro todo
        Index.refreshTable("Materia");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cresActionPerformed

    private void bnew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnew1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bnew1ActionPerformed

    private void calendarInPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarInPropertyChange
        try {
            changeData1();
        }catch (Exception e){
            
        }
    }//GEN-LAST:event_calendarInPropertyChange

    private void calendarIn2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarIn2PropertyChange
        calendarInPropertyChange(evt);
    }//GEN-LAST:event_calendarIn2PropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CheckMaterial(0).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bexc;
    private javax.swing.JButton bexc1;
    private javax.swing.JButton bnew1;
    public com.toedter.calendar.JDateChooser calendar;
    public com.toedter.calendar.JDateChooser calendar1;
    public com.toedter.calendar.JDateChooser calendarIn;
    public com.toedter.calendar.JDateChooser calendarIn2;
    private javax.swing.JComboBox ccol;
    private javax.swing.JTextField cmat;
    private javax.swing.JComboBox cmed;
    private javax.swing.JComboBox cpres;
    private javax.swing.JTextField cres;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    public static javax.swing.JLabel labelName;
    public static javax.swing.JLabel nameframe;
    private javax.swing.JTable tableIn;
    private javax.swing.JLabel trash1;
    // End of variables declaration//GEN-END:variables
}
