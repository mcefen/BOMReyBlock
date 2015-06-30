
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




public class CheckMold extends javax.swing.JFrame {

private boolean verificacion = false;
private int codigoModificar = -1;

  // Variables de base de datos 
   private Statement st = DataBaseClass.getSt();
   private Connection connection = DataBaseClass.getConnection();
   private ResultSet resultSet = DataBaseClass.getResultSet();
  
        public CheckMold(int codigoMoldeSelecionado ) {
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");
        
        initComponents();
        
        //Color 
        jPanel3.setBackground(ConfigClass.getColorApp());
        
        this.codigoModificar = codigoMoldeSelecionado ;
        // cambio la fecha del calendar del mold
        calendarMold.setDate(Calendar.getInstance().getTime());
        labelName.setText(""+"Codigo : "+this.codigoModificar);
        
        /*
        Load the data of moldes. 
        */
        Object [] data = new DataBaseClass().giveData("SELECT * FROM molde WHERE codigo = "+codigoModificar);
        
        cnom.setText(""+data[1]);
        clar.setText(""+data[2]);
        canc.setText(""+data[3]);
        calt.setText(""+data[4]);
    try {
        // Fecha de compra.
        calendarMold.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(data[5].toString())); // Fecha de ingreso
        /*
        Fin de cargar los datos de los moldes. 
        */
    } catch (ParseException ex) {
        Logger.getLogger(CheckMold.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
        
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        calt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        clar = new javax.swing.JTextField();
        cnom = new javax.swing.JTextField();
        canc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        calendarMold = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        bexc1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        calendar2 = new com.toedter.calendar.JDateChooser();
        calendar3 = new com.toedter.calendar.JDateChooser();
        bnew = new javax.swing.JButton();
        trash = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        calendar = new com.toedter.calendar.JDateChooser();
        calendar1 = new com.toedter.calendar.JDateChooser();
        bexc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        nameframe = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

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

        jLabel6.setText("Alto (cm)");
        jLabel6.setFocusable(false);

        clar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clarKeyTyped(evt);
            }
        });

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

        jLabel10.setText("Largo (cm)");
        jLabel10.setFocusable(false);

        jLabel2.setText("Nombre del Molde");
        jLabel2.setFocusable(false);

        jLabel3.setText("Ancho (cm)");
        jLabel3.setFocusable(false);

        jLabel4.setText("Fecha");
        jLabel4.setFocusable(false);

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
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel10)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(clar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(canc, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(calt, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(calendarMold, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(562, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {calendarMold, calt, canc, clar, cnom});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(canc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendarMold, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {calendarMold, calt, canc, clar, jLabel10, jLabel2, jLabel3, jLabel4, jLabel6});

        jTabbedPane1.addTab("Información Molde", jPanel1);

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

        bnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316277158_filenew.png"))); // NOI18N
        bnew.setText("Nuevo");

        trash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empty.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bexc1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(calendar3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bnew)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(trash)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bexc1, bnew});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
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
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bexc1, bnew});

        jTabbedPane1.addTab("Gastos del Molde", jPanel4);

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

        calendar.setFocusCycleRoot(true);
        calendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendarMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                calendarMouseReleased(evt);
            }
        });
        calendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendarPropertyChange(evt);
            }
        });
        calendar.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                calendarInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                calendarCaretPositionChanged(evt);
            }
        });
        calendar.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                calendarVetoableChange(evt);
            }
        });

        calendar1.setFocusCycleRoot(true);
        calendar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendar1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                calendar1MouseReleased(evt);
            }
        });
        calendar1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendar1PropertyChange(evt);
            }
        });
        calendar1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                calendar1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                calendar1CaretPositionChanged(evt);
            }
        });
        calendar1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                calendar1VetoableChange(evt);
            }
        });

        bexc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340308507_application-vnd.ms-excel.png"))); // NOI18N
        bexc.setText("Excel");
        bexc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bexc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bexc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(calendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Movimiento del Molde", jPanel2);

        jPanel3.setBackground(new java.awt.Color(116, 186, 232));

        nameframe.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        nameframe.setForeground(new java.awt.Color(255, 255, 255));
        nameframe.setText("Chequear Molde");

        labelName.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        labelName.setForeground(new java.awt.Color(255, 255, 255));
        labelName.setText("Nombre del Molde que se va a jalar ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameframe)
                .addGap(40, 40, 40)
                .addComponent(labelName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(nameframe)
                .addComponent(labelName))
        );

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
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnomActionPerformed
        
}//GEN-LAST:event_cnomActionPerformed

    private void cnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnomKeyTyped
        
}//GEN-LAST:event_cnomKeyTyped

    private void clarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clarKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE) || (c==evt.VK_DELETE) || (c == KeyEvent.VK_PERIOD)|| ((c== KeyEvent.VK_SPACE)))) {
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
   public void updateData() throws FileNotFoundException {
       
       try {
            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("UPDATE MOLDE SET Nombre = ?,Largo=?,Ancho=?,Alto = ? WHERE codigo = "+codigoModificar+";");


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
              Date date=(Date) calendarMold.getDate(); 
              SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
              String fecha = format.format(date);
              DataBaseClass.executeQuery("Update molde set fecha_compra =\""+ fecha +"\" where codigo="+codigoModificar);
  }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try {
          
            updateData();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CheckMold.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (verificacion == true) {
            dispose();
            
            
        }
       // reconfiguro todo
        Index.refreshTable("Molde");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void calendarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_calendarMouseClicked

    private void calendarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_calendarMouseReleased

    private void calendarCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendarCaretPositionChanged

    }//GEN-LAST:event_calendarCaretPositionChanged

    private void calendarInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendarInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_calendarInputMethodTextChanged

    private void calendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarPropertyChange
   /*     // si el calendario cambia de fecha haga algo
        if (status.equalsIgnoreCase("reporteProduccion")){
            arrayFecha=intervalDates();
            consulta = "select Tipo_de_Block, sum(cantidad_de_blocks) as Cantidad from"
            + " Produccion Where FECHA BETWEEN '"+arrayFecha[0]+"' AND '"+arrayFecha[1]+"' GROUP BY Tipo_de_Block";

            changeData(consulta,"Produccion de Blocks");
        }
        else{
            if (status.equalsIgnoreCase("reportequebrados")){
                arrayFecha=intervalDates();
                consulta = "select Tipo_de_Block, sum(Blocks_Quebrados_al_producir) as QuebradosAlProducir, sum(Blocks_Quebrados_al_Sacar) as QuebradosAlSacar from"
                + " Produccion Where FECHA BETWEEN '"+arrayFecha[0]+"' AND '"+arrayFecha[1]+"' GROUP BY Tipo_de_Block";

                changeData(consulta,"Blocks Quebrados");
            }

            else {
                if (status.equalsIgnoreCase("reporteMateria")){
                    arrayFecha=intervalDates();

                    consulta = "select materialesp.Nombre, sum(materialesp.Cantidad) as Cantidad,"
                    + " materia.presentacion, sum(materialesp.Total_Dinero) as Total_en_Quetzales,  AVG(materialesp.Precio_mt3) as Precio from materialesp, materia,produccion Where produccion.codigo = materialesp.codigo_produccion AND"
                    + " materialesp.nombre = materia.nombre AND materialesp.FECHA BETWEEN '"+arrayFecha[0]+"' AND '"+arrayFecha[1]+"' GROUP BY materialesp.Nombre";

                    changeData(consulta,"Materia Prima Utilizada");
                }
                else {
                    if (status.equalsIgnoreCase("reporteMolde")){
                        arrayFecha=intervalDates();
                        consulta = "select moldesp.Nombre, sum(moldesp.Cantidad_de_blocks) as Cantidad_Entre_Estas_Fechas, molde.unidades_de_block as Cantidad_Inicial,sum(moldesp.Cantidad_de_blocks+molde.unidades_de_block) as Total"
                        + ", TIMESTAMPDIFF(MONTH,molde.fecha,CURDATE()) as Meses_Utilizado, molde.fecha as Fecha_de_Compra from moldesp, produccion,molde Where produccion.codigo = moldesp.codigo_produccion AND"
                        + " moldesp.nombre = molde.nombre AND moldesp.FECHA BETWEEN '"+arrayFecha[0]+"' AND '"+arrayFecha[1]+"' GROUP BY moldesp.Nombre";

                        changeData(consulta,"Utilizacion de Moldes");
                    }
                    else{
                        if (status.equalsIgnoreCase("reporteSueldos")){
                            arrayFecha=intervalDates();

                            consulta = "select nombre, apellido, puesto, sueldo_base,(select sum(personalp.sueldo_en_Q) "
                            + "from personalp,produccion where personal.nombre = personalp.nombre AND personalp.codigo_produccion = produccion.codigo AND personalp.FECHA BETWEEN '"+arrayFecha[0]+"' AND '"+arrayFecha[1]+"') as Sueldo, "
                            + "(select sum(bonificacion_en_q) from personalp,produccion where personal.nombre = personalp.nombre AND personalp.codigo_produccion = produccion.codigo AND personalp.FECHA BETWEEN '"+arrayFecha[0]+"' AND '"+arrayFecha[1]+"') as Bonificacion, "
                            + "(select sum(personalp.horas_extras) from personalp,produccion where personal.nombre = personalp.nombre AND personalp.codigo_produccion = produccion.codigo AND personalp.FECHA BETWEEN '"+arrayFecha[0]+"' AND '"+arrayFecha[1]+"') as Horas_Extras, "
                            + "(select sum(personalp.descuentos) from personalp,produccion where personal.nombre = personalp.nombre AND personalp.codigo_produccion = produccion.codigo AND personalp.FECHA BETWEEN '"+arrayFecha[0]+"' AND '"+arrayFecha[1]+"') as Descuentos from personal;";

                            changeData(consulta,"Sueldo Trabajadores");

                        }

                    }

                }
            }

        }
*/
    }//GEN-LAST:event_calendarPropertyChange

    private void calendarVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_calendarVetoableChange

    }//GEN-LAST:event_calendarVetoableChange

    private void calendar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar1MouseClicked

    private void calendar1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendar1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar1MouseReleased

    private void calendar1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendar1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar1CaretPositionChanged

    private void calendar1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendar1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar1InputMethodTextChanged

    private void calendar1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendar1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar1PropertyChange

    private void calendar1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_calendar1VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_calendar1VetoableChange

    private void bexcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexcActionPerformed
/*
        arrayFecha=intervalDates();   // la fecha inicial y final
        ToExcel arch = new ToExcel();

        if (status.equalsIgnoreCase("reporteSueldos")){

            try {

                arch.TipoSueldo(arrayFecha, status);

            } catch (InvalidFormatException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParsePropertyException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else if (status.equalsIgnoreCase("reporteProduccion")){

            // Primero muestro la ventana de gastos,
            OtrosGastos gas = new OtrosGastos(arrayFecha,status);
            gas.setLocationRelativeTo(null); // centro la pantalla
            gas.setVisible(true);

        }
*/
    }//GEN-LAST:event_bexcActionPerformed

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CheckMold(0).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bexc;
    private javax.swing.JButton bexc1;
    private javax.swing.JButton bnew;
    public com.toedter.calendar.JDateChooser calendar;
    public com.toedter.calendar.JDateChooser calendar1;
    public com.toedter.calendar.JDateChooser calendar2;
    public com.toedter.calendar.JDateChooser calendar3;
    private com.toedter.calendar.JDateChooser calendarMold;
    private javax.swing.JTextField calt;
    private javax.swing.JTextField canc;
    private javax.swing.JTextField clar;
    private javax.swing.JTextField cnom;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JLabel labelName;
    public static javax.swing.JLabel nameframe;
    private javax.swing.JLabel trash;
    // End of variables declaration//GEN-END:variables
}
