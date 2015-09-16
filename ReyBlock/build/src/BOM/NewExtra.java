
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;




public class NewExtra extends javax.swing.JFrame {
    private DecimalFormat money = new DecimalFormat("Q 0.00 "); 
    private boolean verificacion = false;
    private int codigoExtra = 0;
    
    private Empleado dataEmpleadoList [];
  // Variables de base de datos 
   private Statement st = DataBaseClass.getSt();
   private Connection connection = DataBaseClass.getConnection();
   private ResultSet resultSet = DataBaseClass.getResultSet();

  
    public NewExtra(int codMolde) {
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");
        
        initComponents();
        
        //Color 
        jPanel3.setBackground(ConfigClass.getColorApp());
        // Coloco la fecha de hoy en el calendario. 
        calendarDay.setDate(java.util.Calendar.getInstance().getTime());
        
        
    }

    public  NewExtra() {
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");
        
        initComponents();
        
        //Color 
        jPanel3.setBackground(ConfigClass.getColorApp());
        
        // Coloco la fecha de hoy en el calendario. 
        calendarDay.setDate(java.util.Calendar.getInstance().getTime());
        empleadoList(); // Listado del empleado. 
        
          
    }
    private class Empleado {
        private int codigo;
        private String nombre;
        private String apellido;

        public Empleado(int codigo, String nombre, String apellido) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.apellido = apellido;
        }
        
        

        public String getApellido() {
            return apellido;
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
        
        
        
    }
    
    
    private void empleadoList (){
    
        
     
            try {

                        st = (Statement) connection.createStatement();
                        resultSet = st.executeQuery("SELECT codigo, nombre, apellido FROM PERSONAL ORDER BY Nombre");
                        ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();

                        resultSet.last();                // estas lineas se mueven al final de la consulta 

                        int numrows=resultSet.getRow();
                        int numcolumn=metaData.getColumnCount();

                        dataEmpleadoList = new Empleado [numrows];  //la cantidad de material que va a salir 

                        resultSet.beforeFirst();

                        int pos=0;
                        while(resultSet.next()){
                            String nom="";
                            int cod =0;
                            String ape="";

                            for(int i=0;i<=numcolumn;i++){
                                switch(i){
                                    case 2 :
                                       nom=resultSet.getString(2);
                                       break;
                                    case 1:
                                        cod=resultSet.getInt(1);
                                        break;
                                    case 3 :
                                       ape=resultSet.getString(3);
                                       break;
                                    


                                  }
                            }

                            dataEmpleadoList[pos]= new Empleado (cod,nom,ape);
                            pos++;
                        }


                     empleadoBox.removeAllItems();  // limpio el comboBox
                     
                     for(int i=0;i<=dataEmpleadoList.length-1;i++){
                        empleadoBox.addItem(""+dataEmpleadoList[i].getNombre()+" "+dataEmpleadoList[i].getApellido()+"");
                    }


                } catch (SQLException ex) {
                    Logger.getLogger(NewExtra.class.getName()).log(Level.SEVERE, null, ex);
                }


                
                AutoCompleteDecorator.decorate(empleadoBox);
                empleadoBox.setSelectedIndex(-1);
                
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        nameframe = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cTarifa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        calendarDay = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        cdes = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        empleadoBox = new javax.swing.JComboBox();
        cHoras = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cTotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(116, 186, 232));

        nameframe.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        nameframe.setForeground(new java.awt.Color(255, 255, 255));
        nameframe.setText("Nueva Hora Extra");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameframe, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameframe)
        );

        jLabel10.setText("Descripcion");
        jLabel10.setFocusable(false);

        cTarifa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cTarifaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cTarifaKeyTyped(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tarifa hora extra");
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

        jLabel4.setText("Fecha");
        jLabel4.setFocusable(false);

        cdes.setColumns(20);
        cdes.setLineWrap(true);
        cdes.setRows(5);
        jScrollPane1.setViewportView(cdes);

        jLabel5.setText("Personal ");
        jLabel5.setFocusable(false);

        empleadoBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        empleadoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadoBoxActionPerformed(evt);
            }
        });

        cHoras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cHorasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cHorasKeyTyped(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cantidad de horas");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setFocusable(false);

        cTotal.setEditable(false);
        cTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTotalActionPerformed(evt);
            }
        });
        cTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cTotalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cTotalKeyTyped(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Total en Quetzales");
        jLabel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel7.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(33, 33, 33)
                        .addComponent(calendarDay, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel5))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                            .addComponent(empleadoBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(cHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(cTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton3});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cTarifa, jLabel3, jLabel6, jLabel7});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(calendarDay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(empleadoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton3});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cTarifa, empleadoBox, jLabel10, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cTarifaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cTarifaKeyReleased
        updateTotal();
    }//GEN-LAST:event_cTarifaKeyReleased

    private void cTarifaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cTarifaKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_cTarifaKeyTyped
    
    private static java.sql.Date getCurrentDate() {
    java.util.Date today;
    today = calendarDay.getDate();
    return new java.sql.Date(today.getTime());
}
    public void ingresarExtraPersonal()  {
        
        int resultado = 0;
        try {
            
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO extras_personal (Descripcion,Cantidad_horas,Tarifa_hora) values (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);

            // descripcion 
            ps.setString(1,cdes.getText());
            // Cantidad de horas 
            ps.setInt(2, Integer.parseInt(cHoras.getText().toString()));
            try {
                // tarifa de horas 
                ps.setDouble(3,  Double.parseDouble(cTarifa.getText().toString()));
            } catch (Exception ex) {
                //ps.setDouble(3, 0);  // asi me dara error. 
            }

            resultado = ps.executeUpdate();
            resultSet = ps.getGeneratedKeys();
               while (resultSet.next()) {
                      codigoExtra = resultSet.getInt(1);

             }
               
             verificacion = true;
             
            } catch (SQLException e) {
                 verificacion = false;
                 JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al ingresar datos : Extras Personal <br/>"+e+" </p></pre></body></html>","Error al ingresar datos a la base de datos", 0);

            } // fin del catch
        
    }
    public void ingresarMovimientoPersonal(){
           
        int resultado = 0;
        try {
            
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO movimiento_personal (codigo_personal,codigo_horas_extra,fecha,total_en_Q) VALUES (?,?,?,?)");

            // codigo personal 
            ps.setInt(1,dataEmpleadoList[empleadoBox.getSelectedIndex()].getCodigo());
            
            // codigo extras 
            ps.setInt(2, codigoExtra);
            
            // fecha
            ps.setDate(3, getCurrentDate());
            
            // total en q 
            try {

                ps.setDouble(4, money.parse(cTotal.getText()).doubleValue());

            } catch (Exception e) {
                // ps.setDouble(3, 0.0); // Asi me da error 
            }   

            resultado = ps.executeUpdate();
            verificacion = true;
            } catch (SQLException e) {
                 verificacion = false;
                 DataBaseClass.executeQuery("DELETE FROM extras_personal where codigo ="+codigoExtra);
                 JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al ingresar datos : Movimiento Personal <br/>"+e+" </p></pre></body></html>","Error al ingresar datos a la base de datos", 0);

            } // fin del catch
       
        
    }
    private boolean checkIn (){
        double total = 0;
        String error = "" ;
        
        // total en q 
        try {
            total = money.parse(cTotal.getText()).doubleValue();
            if (total==0)
                error+="Error, el total debe ser diferente de cero. <br/>";
            
        } catch (Exception e) {
            total = 0;
            error += "Error en hora extra, debe contener una cifra coherente. <br/>";
        }   
        
        if (empleadoBox.getSelectedIndex()==-1){
            error +="Error en empleado, debe de seleccionar un dato. ";
        }
        
        if (error.isEmpty()){
            
            return true;
        }
    
        else {                                      
            JOptionPane.showMessageDialog(rootPane, "<html><body><pre><p style='width: 400px;'>Error al ingresar datos : <br/>"+error+"</p></pre></body></html>","Error al ingresar datos a la base de datos", 0);
            return false;
        }
            
        
       
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if (checkIn()){
            ingresarExtraPersonal();
            ingresarMovimientoPersonal();
            
            if (verificacion) {
                
                JOptionPane.showMessageDialog(rootPane, "Dato ingresado exitosamente" );
                // reconfiguro todo
                Index.refreshTable("HorasExtras");
                dispose();


            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cHorasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cHorasKeyReleased
        updateTotal();
    }//GEN-LAST:event_cHorasKeyReleased

    private void cHorasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cHorasKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_cHorasKeyTyped

    private void cTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cTotalActionPerformed

    private void cTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cTotalKeyReleased

    private void cTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cTotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cTotalKeyTyped

    private void empleadoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadoBoxActionPerformed
         try {
          // Coloco la tarifa del trabajador. 
          cTarifa.setText(""+new DataBaseClass().giveData("SELECT tarifa_horas_extras FROM personal where codigo = "+dataEmpleadoList[empleadoBox.getSelectedIndex()].getCodigo()+";")[0]);
         }catch (Exception e){
          cTarifa.setText("");
         }
         updateTotal();
    }//GEN-LAST:event_empleadoBoxActionPerformed
    public void updateTotal () {
            double tarifa = 0;
            double horas = 0;
            double total = 0;
            try {
                tarifa = Double.parseDouble(cTarifa.getText());
            }catch(Exception e ){
                tarifa = 0;
            }

            try {
                horas = Double.parseDouble(cHoras.getText());
            }catch(Exception e ){
                horas = 0;
            }

            total=horas*tarifa;

            cTotal.setText(""+money.format(total));

            
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NewExtra().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cHoras;
    private javax.swing.JTextField cTarifa;
    private javax.swing.JTextField cTotal;
    private static com.toedter.calendar.JDateChooser calendarDay;
    private javax.swing.JTextArea cdes;
    private javax.swing.JComboBox empleadoBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel nameframe;
    // End of variables declaration//GEN-END:variables
}
