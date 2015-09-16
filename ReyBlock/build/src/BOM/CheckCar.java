
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;




public class CheckCar extends javax.swing.JFrame {

private boolean verificacion = false;
private int codigoModificar = -1 ;

  // Variables de base de datos 
   private Statement st = DataBaseClass.getSt();
   private Connection connection = DataBaseClass.getConnection();
   private ResultSet resultSet = DataBaseClass.getResultSet();

  
        public CheckCar(int cod) {
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");
        
        initComponents();
        
        //Color 
        jPanel3.setBackground(ConfigClass.getColorApp());
        
        // cambio la fecha del calendar del mold
        calendarCar.setDate(Calendar.getInstance().getTime());
        
        // Le coloco de que molde esta jalando la información
        this.codigoModificar = cod ;
        labelName.setText(""+"Codigo : "+codigoModificar);
        
        /*
        Update data in fields. 
        */
        
        Object [] data = new DataBaseClass().giveData("SELECT * FROM vehiculo WHERE codigo = "+codigoModificar);
        cnom.setText(""+data[1]);
        cpla.setText(""+data[2]);
        try {
            // Fecha de compra.
            calendarCar.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(data[3].toString())); // Fecha de ingreso
            /*
            Fin de cargar los datos de los moldes. 
            */
        } catch (ParseException ex) {
            Logger.getLogger(CheckMold.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        ccol.setText(""+data[4]);
        typeCar.setSelectedItem(data[5]);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        nameframe = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        calendarCar = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        cpla = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cnom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ccol = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        typeCar = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        bnew = new javax.swing.JButton();
        bexc1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        calendar2 = new com.toedter.calendar.JDateChooser();
        calendar3 = new com.toedter.calendar.JDateChooser();
        trash = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        bnew1 = new javax.swing.JButton();
        bexc2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        calendar4 = new com.toedter.calendar.JDateChooser();
        calendar5 = new com.toedter.calendar.JDateChooser();
        trash1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(116, 186, 232));

        nameframe.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        nameframe.setForeground(new java.awt.Color(255, 255, 255));
        nameframe.setText("Chequear Vehículo");

        labelName.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        labelName.setForeground(new java.awt.Color(255, 255, 255));
        labelName.setText("Nombre del Carro ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameframe)
                .addGap(46, 46, 46)
                .addComponent(labelName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(nameframe)
                .addComponent(labelName))
        );

        jLabel4.setText("Fecha de Adquisición");
        jLabel4.setFocusable(false);

        jLabel3.setText("Placa");
        jLabel3.setFocusable(false);

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

        jLabel5.setText("Color");
        jLabel5.setFocusable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/confirm.png"))); // NOI18N
        jButton1.setText("Confirmado");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Tipo de Vehiculo");
        jLabel10.setFocusable(false);

        jLabel2.setText("Nombre del Vehiculo");
        jLabel2.setFocusable(false);

        typeCar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Camión de Doble Eje","Camión de una Tonelada","Camión Sencillo","Camioneta","Fugón","Pick-Up","Rastas","Sedán","Trailer"}));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cnom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cpla, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(calendarCar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ccol, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(typeCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(648, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {calendarCar, ccol, cnom, cpla, typeCar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendarCar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ccol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {calendarCar, ccol, cnom, cpla, typeCar});

        jTabbedPane1.addTab("Información del Camión", jPanel1);

        bnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316277158_filenew.png"))); // NOI18N
        bnew.setText("Nuevo");

        bexc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340308507_application-vnd.ms-excel.png"))); // NOI18N
        bexc1.setText("Excel");
        bexc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexc1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        calendar2.setFocusCycleRoot(true);
        calendar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendar2MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                calendar2MouseReleased(evt);
            }
        });
        calendar2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendar2PropertyChange(evt);
            }
        });
        calendar2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                calendar2InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                calendar2CaretPositionChanged(evt);
            }
        });
        calendar2.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                calendar2VetoableChange(evt);
            }
        });

        calendar3.setFocusCycleRoot(true);
        calendar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendar3MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                calendar3MouseReleased(evt);
            }
        });
        calendar3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendar3PropertyChange(evt);
            }
        });
        calendar3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                calendar3InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                calendar3CaretPositionChanged(evt);
            }
        });
        calendar3.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                calendar3VetoableChange(evt);
            }
        });

        trash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empty.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bexc1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendar3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnew)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(trash)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bexc1, bnew});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bnew)
                        .addGap(14, 14, 14)
                        .addComponent(bexc1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(calendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendar3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(trash))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bexc1, bnew});

        jTabbedPane1.addTab("Gastos del Camión", jPanel2);

        bnew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316277158_filenew.png"))); // NOI18N
        bnew1.setText("Nuevo");

        bexc2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340308507_application-vnd.ms-excel.png"))); // NOI18N
        bexc2.setText("Excel");
        bexc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexc2ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        calendar4.setFocusCycleRoot(true);
        calendar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendar4MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                calendar4MouseReleased(evt);
            }
        });
        calendar4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendar4PropertyChange(evt);
            }
        });
        calendar4.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                calendar4InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                calendar4CaretPositionChanged(evt);
            }
        });
        calendar4.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                calendar4VetoableChange(evt);
            }
        });

        calendar5.setFocusCycleRoot(true);
        calendar5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendar5MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                calendar5MouseReleased(evt);
            }
        });
        calendar5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendar5PropertyChange(evt);
            }
        });
        calendar5.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                calendar5InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                calendar5CaretPositionChanged(evt);
            }
        });
        calendar5.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                calendar5VetoableChange(evt);
            }
        });

        trash1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empty.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendar4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bexc2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendar5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnew1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(trash1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bexc2, bnew1});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(bnew1)
                        .addGap(14, 14, 14)
                        .addComponent(bexc2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(calendar4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendar5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(trash1))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bexc2, bnew1});

        jTabbedPane1.addTab("Ganancias del Camión", jPanel4);

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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnomActionPerformed
        
}//GEN-LAST:event_cnomActionPerformed

    private void cnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnomKeyTyped
        
}//GEN-LAST:event_cnomKeyTyped
  public void updateData() throws FileNotFoundException {
      
        try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("UPDATE vehiculo SET Nombre=?,Placa=?,Color=?,Tipo_Vehiculo=? WHERE codigo = "+codigoModificar);


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
              
              

            JOptionPane.showMessageDialog(rootPane, "Dato ingresado exitosamente" );
            verificacion = true;

           

        } catch (SQLException e) {
            verificacion = false;
            JOptionPane.showMessageDialog(rootPane, e);

           
        } // fin del catch
        if(verificacion == true)
        Ingresar_Fecha();
    }
  public void Ingresar_Fecha(){
       //Fecha
              Date date=(Date) calendarCar.getDate(); 
              SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
              String fecha = format.format(date);
              DataBaseClass.executeQuery("Update vehiculo set fecha_de_compra =\""+ fecha +"\" where codigo="+codigoModificar);
  }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try {
          
            updateData();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CheckCar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (verificacion == true) {
            dispose();
            
            
        }
        // reconfiguro todo
             
         // reconfiguro todo
        Index.refreshTable("Vehiculo");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bexc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bexc1ActionPerformed

    private void calendar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar2MouseClicked

    private void calendar2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendar2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar2MouseReleased

    private void calendar2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendar2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar2PropertyChange

    private void calendar2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendar2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar2CaretPositionChanged

    private void calendar2InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendar2InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar2InputMethodTextChanged

    private void calendar2VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_calendar2VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar2VetoableChange

    private void calendar3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendar3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar3MouseClicked

    private void calendar3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendar3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar3MouseReleased

    private void calendar3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendar3PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar3PropertyChange

    private void calendar3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendar3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar3CaretPositionChanged

    private void calendar3InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendar3InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar3InputMethodTextChanged

    private void calendar3VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_calendar3VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar3VetoableChange

    private void bexc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexc2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bexc2ActionPerformed

    private void calendar4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendar4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar4MouseClicked

    private void calendar4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendar4MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar4MouseReleased

    private void calendar4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendar4PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar4PropertyChange

    private void calendar4CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendar4CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar4CaretPositionChanged

    private void calendar4InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendar4InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar4InputMethodTextChanged

    private void calendar4VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_calendar4VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar4VetoableChange

    private void calendar5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendar5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar5MouseClicked

    private void calendar5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendar5MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar5MouseReleased

    private void calendar5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendar5PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar5PropertyChange

    private void calendar5CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendar5CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar5CaretPositionChanged

    private void calendar5InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendar5InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar5InputMethodTextChanged

    private void calendar5VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_calendar5VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar5VetoableChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CheckCar(0).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bexc1;
    private javax.swing.JButton bexc2;
    private javax.swing.JButton bnew;
    private javax.swing.JButton bnew1;
    public com.toedter.calendar.JDateChooser calendar2;
    public com.toedter.calendar.JDateChooser calendar3;
    public com.toedter.calendar.JDateChooser calendar4;
    public com.toedter.calendar.JDateChooser calendar5;
    private com.toedter.calendar.JDateChooser calendarCar;
    private javax.swing.JTextField ccol;
    private javax.swing.JTextField cnom;
    private javax.swing.JTextField cpla;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    public static javax.swing.JLabel labelName;
    public static javax.swing.JLabel nameframe;
    private javax.swing.JLabel trash;
    private javax.swing.JLabel trash1;
    private javax.swing.JComboBox typeCar;
    // End of variables declaration//GEN-END:variables
}
