
package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class NewEmployee extends javax.swing.JFrame {

boolean verificacion = false;
  // Variables de base de datos 
   private Statement st = DataBaseClass.getSt();
   private Connection connection = DataBaseClass.getConnection();
   private ResultSet resultSet = DataBaseClass.getResultSet();

String pathFoto = "";
String foto = "";
JMFCamara tphoto ;
int cod =0;
  
        public NewEmployee() {
            //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");
        initComponents();
        
        //Color 
        jPanel3.setBackground(ConfigClass.getColorApp());
        
        
        // Fecha de hoy en los tres calendarios 
        calendar1.setDate(Calendar.getInstance().getTime()); // fecha de ingreso
        calendar2.setDate(Calendar.getInstance().getTime()); // fecha de indenizacion 
        calendar3.setDate(Calendar.getInstance().getTime()); // fecha de nacimiento
        
        //se coloca un layout tipo CAJA    
        try {
            tphoto = new JMFCamara();
            VIDEO1.setLayout(null);
            //VIDEO1.setLayout(new javax.swing.BoxLayout(VIDEO1, javax.swing.BoxLayout.LINE_AXIS));
            VIDEO1.add(tphoto.Componente());
            //se añade el componente de video
            //VIDEO1.add(tphoto.Componente());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error al cargar camara","Error al cargar camara",0);
        }
        
        
        // Box Inteligentes 
        AutoCompleteDecorator.decorate(cpuesto);
        cpuesto.setSelectedIndex(-1); // Combo puesto 
        
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        nameframe = new javax.swing.JLabel();
        panelTab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        cape = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cnom = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cced = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ctel = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cigss = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cdir = new javax.swing.JTextArea();
        cpuesto = new javax.swing.JComboBox();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        csue = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        chrs = new javax.swing.JTextField();
        csuel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboni = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        calendar1 = new com.toedter.calendar.JDateChooser();
        calendar2 = new com.toedter.calendar.JDateChooser();
        calendar3 = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        imagen1 = new javax.swing.JLabel();
        VIDEO1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        labelInfo = new javax.swing.JLabel();
        labelDates = new javax.swing.JLabel();
        labelMoney = new javax.swing.JLabel();
        labelFoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(116, 186, 232));

        nameframe.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        nameframe.setForeground(new java.awt.Color(255, 255, 255));
        nameframe.setText("Nuevo Empleado");

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

        cape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capeActionPerformed(evt);
            }
        });
        cape.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                capeKeyTyped(evt);
            }
        });

        jLabel5.setText("Apellido");
        jLabel5.setFocusable(false);

        jLabel2.setText("Nombre ");
        jLabel2.setFocusable(false);

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

        jLabel7.setText("Puesto");
        jLabel7.setFocusable(false);

        cced.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ccedActionPerformed(evt);
            }
        });
        cced.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ccedKeyTyped(evt);
            }
        });

        jLabel9.setText("DPI");
        jLabel9.setFocusable(false);

        ctel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ctelKeyTyped(evt);
            }
        });

        jLabel10.setText("Telefono");
        jLabel10.setFocusable(false);

        jLabel4.setText("No.IGSS");
        jLabel4.setFocusable(false);

        cigss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cigssActionPerformed(evt);
            }
        });
        cigss.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cigssKeyTyped(evt);
            }
        });

        jLabel11.setText("Direccion");
        jLabel11.setFocusable(false);

        cdir.setColumns(20);
        cdir.setLineWrap(true);
        cdir.setRows(7);
        jScrollPane1.setViewportView(cdir);

        cpuesto.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
            "Administrador","Ayudante","Ayudante de Chofer", "Ayudante de Guardía","Ayudante de Herrero",
            "Cargador","Carretero","Chofer","Contador","Encargado de Planta","Encargado de Herrería","Guardía",
            "Herrero","Oficinista","Operador","Programador","Sacador","Secretaría","Tablero","Otros"}));

jButton8.setBackground(new java.awt.Color(255, 255, 255));
jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/next.png"))); // NOI18N
jButton8.setAutoscrolls(true);
jButton8.setBorder(null);
jButton8.setBorderPainted(false);
jButton8.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton8ActionPerformed(evt);
    }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton8))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(cape, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cced, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(cigss, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(18, 18, 18)
                                    .addComponent(ctel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(0, 116, Short.MAX_VALUE)))
            .addContainerGap())
    );

    jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel4});

    jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel5, jLabel7, jLabel9});

    jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cigss, cpuesto});

    jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cape, jScrollPane1});

    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(19, 19, 19)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(cced, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ctel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cigss, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel5))
                        .addComponent(cape, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))))
            .addGap(18, 18, 18)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
            .addComponent(jButton8)
            .addContainerGap())
    );

    jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cigss, cpuesto});

    panelTab.addTab("Información Empleado", jPanel2);

    csue.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            csueActionPerformed(evt);
        }
    });
    csue.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            csueKeyReleased(evt);
        }
        public void keyTyped(java.awt.event.KeyEvent evt) {
            csueKeyTyped(evt);
        }
    });

    jLabel3.setText("Sueldo Base ");
    jLabel3.setFocusable(false);

    jLabel12.setText("Tarifa Horas Extras");
    jLabel12.setFocusable(false);

    chrs.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            chrsActionPerformed(evt);
        }
    });
    chrs.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            chrsKeyReleased(evt);
        }
        public void keyTyped(java.awt.event.KeyEvent evt) {
            chrsKeyTyped(evt);
        }
    });

    csuel.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            csuelActionPerformed(evt);
        }
    });
    csuel.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            csuelKeyReleased(evt);
        }
        public void keyTyped(java.awt.event.KeyEvent evt) {
            csuelKeyTyped(evt);
        }
    });

    jLabel6.setText("Tarifa Sueldo");
    jLabel6.setFocusable(false);

    jLabel8.setText("Tarifa Bonificación");
    jLabel8.setFocusable(false);

    cboni.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cboniActionPerformed(evt);
        }
    });
    cboni.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            cboniKeyReleased(evt);
        }
        public void keyTyped(java.awt.event.KeyEvent evt) {
            cboniKeyTyped(evt);
        }
    });

    jButton7.setBackground(new java.awt.Color(255, 255, 255));
    jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/next.png"))); // NOI18N
    jButton7.setAutoscrolls(true);
    jButton7.setBorder(null);
    jButton7.setBorderPainted(false);
    jButton7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton7ActionPerformed(evt);
        }
    });

    jButton9.setBackground(new java.awt.Color(255, 255, 255));
    jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/prev.png"))); // NOI18N
    jButton9.setAutoscrolls(true);
    jButton9.setBorder(null);
    jButton9.setBorderPainted(false);
    jButton9.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton9ActionPerformed(evt);
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
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(csue, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chrs, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8))
                            .addGap(48, 48, 48)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(csuel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboni, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 339, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addComponent(jButton9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7)))
            .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(csue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(chrs, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(csuel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGap(53, 53, 53))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboni, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING))
            .addContainerGap())
    );

    panelTab.addTab("Tarifas de Pago", jPanel4);

    jLabel13.setText("Fecha de Nacimiento");
    jLabel13.setFocusable(false);

    jLabel14.setText("Fecha de Ingres a la Empresa");
    jLabel14.setFocusable(false);

    jLabel15.setText("Fecha de Liquidación");
    jLabel15.setFocusable(false);

    jButton4.setBackground(new java.awt.Color(255, 255, 255));
    jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/prev.png"))); // NOI18N
    jButton4.setAutoscrolls(true);
    jButton4.setBorder(null);
    jButton4.setBorderPainted(false);
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });

    jButton5.setBackground(new java.awt.Color(255, 255, 255));
    jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/next.png"))); // NOI18N
    jButton5.setAutoscrolls(true);
    jButton5.setBorder(null);
    jButton5.setBorderPainted(false);
    jButton5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(calendar3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(calendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(calendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(208, 420, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jButton4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton5)
            .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(calendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(calendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(calendar3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton5)
                .addComponent(jButton4))
            .addContainerGap())
    );

    panelTab.addTab("Fechas Importantes", jPanel5);

    imagen1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    imagen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user (2).png"))); // NOI18N
    imagen1.setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lead.png")))); // NOI18N

    VIDEO1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    VIDEO1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user (2).png"))); // NOI18N
    VIDEO1.setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lead.png")))); // NOI18N

    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316233129_Camera.png"))); // NOI18N
    jButton2.setText("Tomar Foto");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jButton6.setBackground(new java.awt.Color(255, 255, 255));
    jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/confirm.png"))); // NOI18N
    jButton6.setAutoscrolls(true);
    jButton6.setBorder(null);
    jButton6.setBorderPainted(false);
    jButton6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton6ActionPerformed(evt);
        }
    });

    jButton10.setBackground(new java.awt.Color(255, 255, 255));
    jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/prev.png"))); // NOI18N
    jButton10.setAutoscrolls(true);
    jButton10.setBorder(null);
    jButton10.setBorderPainted(false);
    jButton10.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton10ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jButton10)
            .addGap(79, 79, 79)
            .addComponent(jButton2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton6)
            .addContainerGap())
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addComponent(VIDEO1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
            .addComponent(imagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(39, 39, 39))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(VIDEO1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addGap(0, 15, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jButton10))))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6)))
            .addContainerGap())
    );

    panelTab.addTab("Fotografía Empleado", jPanel1);

    jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
    jButton3.setText("Cancerlar");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    labelInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
    labelInfo.setText("Información del Empleado");

    labelDates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
    labelDates.setText("Fechas Importantes del Empleado");

    labelMoney.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
    labelMoney.setText("Tarifas de Pago del Empleado");

    labelFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
    labelFoto.setText("Fotografía del Empleado");

    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png"))); // NOI18N
    jButton1.setText("Ingresar");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(panelTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton3))
                .addGroup(layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelDates, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelDates, labelFoto, labelInfo, labelMoney});

    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(panelTab, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(labelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)
                    .addComponent(labelMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(37, 37, 37)
                    .addComponent(labelDates, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)
                    .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(0, 12, Short.MAX_VALUE))
    );

    layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelDates, labelFoto, labelInfo, labelMoney});

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnomActionPerformed
        
}//GEN-LAST:event_cnomActionPerformed

    private void cnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnomKeyTyped
        
}//GEN-LAST:event_cnomKeyTyped

    private void ctelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctelKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE) || (c==evt.VK_DELETE) || ((c== KeyEvent.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }
}//GEN-LAST:event_ctelKeyTyped

    private void csueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csueActionPerformed
        
}//GEN-LAST:event_csueActionPerformed

    private void csueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csueKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_csueKeyReleased

    private void csueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csueKeyTyped
         char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_csueKeyTyped
     public boolean checkMoney(){
        boolean money = true; // si se cambia se arruina 
        String infomsj = "Datos vacios en : \n";
        if(csue.getText().isEmpty()){
             money = false;
             infomsj+= "Sueldo Base\n";
        }
        if (chrs.getText().isEmpty()){
            money = false;
            infomsj+= "Tárifa de horas extra\n";
        }
        if (csuel.getText().isEmpty()){
            money = false;
            infomsj+="Tarifa de Sueldo \n";
        }
        if (cboni.getText().isEmpty()){
            money = false ;
            infomsj+="Tarifa de Bonificación\n";
        }
    
        
        
        if (!money)
        JOptionPane.showMessageDialog(rootPane, infomsj);
        
        if (money)
              labelMoney.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
        else 
              labelMoney.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        
        return money ;
    }
    
    public boolean checkInfo(){
        boolean info = true; // si se cambia se arruina 
        String infomsj = "Datos vacios en : \n";
        if(cnom.getText().isEmpty()){
             info = false;
             infomsj+= "Nombre\n";
        }
        if (cape.getText().isEmpty()){
            info = false;
            infomsj+= "Apellido\n";
        }
        if (cced.getText().isEmpty()){
            info = false;
            infomsj+="DPI\n";
        }
        if (cdir.getText().isEmpty()){
            info = false ;
            infomsj+="Dirección\n";
        }
        if (ctel.getText().isEmpty()){
            info = false;
            infomsj+="Telefono\n";
        }
        if (cigss.getText().isEmpty()){
            info = false;
            infomsj += "No. de IGSS\n";
        }
        
        
        if (!info)
        JOptionPane.showMessageDialog(rootPane, infomsj);
        
        if (info)
              labelInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
        else 
               labelInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png")));
        
        return info ;
    }
    
    public void Insertar() throws FileNotFoundException {
      // Cada vez que hace este metodo inicio las variables de false de las booleanas
      
    if (checkInfo() == true && checkMoney() == true) {
        verificacion = true;
        
        try {
            int resultado = 0;

            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("insert into personal (Nombre,Apellido,Puesto,DPI,Direccion,Telefono,Sueldo_Base,IGSS,Tarifa_Sueldo,Tarifa_Bonificacion,Tarifa_Horas_Extras,Foto) values (?,?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);

         // Nombre        
            try {

                if (cnom.getText() == null ? "" == null : cnom.getText().equals("")) {

                    JOptionPane.showMessageDialog(this, "Error, no ingreso datos en Nombre");
                    cnom.setBackground(Color.red);
                   

                } else {
                    ps.setString(1, cnom.getText()); // con el ps genero lo qee voy a ingresar
                    //lo genero en orden el 1 va a ser el dato uno en este caso seria nombre
                    // y como nombre es varchar tonces sera string NO HAY ERROR 
                }
            } catch (Exception e) {
               
            }
          // Apellido
             try {

                if (cape.getText() == null ? "" == null : cape.getText().equals("")) {

                    JOptionPane.showMessageDialog(this, "Error, no ingreso datos en Apellido");
                    cape.setBackground(Color.red);
                   

                } else {
                    ps.setString(2, cape.getText()); // con el ps genero lo qee voy a ingresar
                    //lo genero en orden el 1 va a ser el dato uno en este caso seria nombre
                    // y como nombre es varchar tonces sera string NO HAY ERROR 
                }
            } catch (Exception e) {
               
            }
             
           // Puesto 
             
                    ps.setString(3, cpuesto.getSelectedItem().toString()); // con el ps genero lo qee voy a ingresar
                  
           // No. de Cedula 
               try {

                if (cced.getText() == null ? "" == null : cced.getText().equals("")) {

                    JOptionPane.showMessageDialog(this, "Error, no ingreso datos en No. de Cedula");
                    cced.setBackground(Color.red);
                   

                } else {
                    ps.setString(4, cced.getText()); // con el ps genero lo qee voy a ingresar
                    //lo genero en orden el 1 va a ser el dato uno en este caso seria nombre
                    // y como nombre es varchar tonces sera string NO HAY ERROR 
                }
            } catch (Exception e) {
               
            }
           // Direccion 
                try {

                if (cdir.getText() == null ? "" == null : cdir.getText().equals("")) {

                    JOptionPane.showMessageDialog(this, "Error, no ingreso datos en Direccion");
                    cdir.setBackground(Color.red);
                   

                } else {
                    ps.setString(5, cdir.getText()); // con el ps genero lo qee voy a ingresar
                    //lo genero en orden el 1 va a ser el dato uno en este caso seria nombre
                    // y como nombre es varchar tonces sera string NO HAY ERROR 
                }
            } catch (Exception e) {
               
            }
            // Telefono
               
             try {

                ps.setInt(6, Integer.parseInt(ctel.getText()));

            } catch (Exception e) {
                ps.setInt(6, 0);
            }   
     // Sueldo Base 
           try {

                ps.setDouble(7, Double.parseDouble(csue.getText()));

            } catch (Exception e) {
                ps.setDouble(7, 0);
            }   
              
       // No. IGSS 
            
              try {

                if (cigss.getText() == null ? "" == null : cigss.getText().equals("")) {

                    JOptionPane.showMessageDialog(this, "Error, no ingreso datos en No. de IGSS");
                    cigss.setBackground(Color.red);
                   

                } else {
                    ps.setString(8, cigss.getText()); // con el ps genero lo qee voy a ingresar
                    //lo genero en orden el 1 va a ser el dato uno en este caso seria nombre
                    // y como nombre es varchar tonces sera string NO HAY ERROR 
                }
            } catch (Exception e) {
               
            }
       // Sueldo
              
              try {

                ps.setDouble(9, Double.parseDouble(csuel.getText()));

            } catch (Exception e) {
                ps.setDouble(9, 0);
            }   
              
       // Bonificacion
              
              try {

                ps.setDouble(10, Double.parseDouble(cboni.getText()));

            } catch (Exception e) {
                ps.setDouble(10, 0);
            }   
            try {

                ps.setDouble(11, Double.parseDouble(chrs.getText()));

            } catch (Exception e) {
                ps.setDouble(11, 0);
            }   
              
            //Foto
            
            File photoToSave = new File(System.getProperty("user.dir") + ""+File.separator+"src"+File.separator+"Empleados"+File.separator+""+cnom.getText()+"empleado"+cape.getText()+".jpg");
            // si no es null la imagen guardo la ultima que tome. 
            if (tphoto.getImagen() != null){
                try {
                    ImageIO.write((RenderedImage) tphoto.getImagen(),"JPEG",photoToSave);
                } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error al cargar foto : "+ex);
                }
                
                // despues guardo la imagen en la base de datos y listo 
                FileInputStream fis = null;

                try {
                    fis = new FileInputStream(photoToSave);
                } catch (FileNotFoundException ex) {
                }

                ps.setBinaryStream(12, fis);
                
            }
            else {
                ps.setBinaryStream(12, null);
            }
            
            
           
            
            resultado = ps.executeUpdate(); // envio los datos 

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                  cod = resultSet.getInt(1);
                
            }
            
            JOptionPane.showMessageDialog(rootPane, "Dato ingresado exitosamente" );
            verificacion = true;

           // conexion.close(); // cierro la conexion

        } catch (SQLException e) {
            verificacion = false;
            JOptionPane.showMessageDialog(rootPane, e);

        } // fin del catch

        // si verificacion esta bien. 
        if (verificacion){
            
           // Fecha de Ingreso 
            
            Ingresar_Fecha(calendar1,"Fecha_Ingreso");
          // Fecha de Liquidación
            
            Ingresar_Fecha(calendar2,"Fecha_Liquidacion");
          
            // Fecha de Nacimiento  
            Ingresar_Fecha(calendar3,"Fecha_Nacimiento");
        }
    
        } // fin del if 
    }
    
 public void Ingresar_Fecha(JDateChooser calendar,String fechaIn){
       //Fecha
              Date date=(Date) calendar.getDate(); 
              SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
              String fecha = format.format(date);
              DataBaseClass.executeQuery("Update Personal set "+fechaIn+" =\""+ fecha +"\" where codigo="+cod);
  }    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
          
            Insertar();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (verificacion == true) {
            dispose();
            tphoto.turnOff();
            // reconfiguro todo
            Index.refreshTable("Personal");
            Index.setColumnWidth();
            
            
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        tphoto.turnOff();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cigssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cigssActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cigssActionPerformed

    private void cigssKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cigssKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cigssKeyTyped

    private void capeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_capeActionPerformed

    private void capeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_capeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_capeKeyTyped

    private void ccedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ccedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ccedActionPerformed

    private void ccedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ccedKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ccedKeyTyped

    private void csuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csuelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_csuelActionPerformed

    private void csuelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csuelKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_csuelKeyReleased

    private void csuelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csuelKeyTyped
       char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_csuelKeyTyped

    private void cboniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboniActionPerformed

    private void cboniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboniKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cboniKeyReleased

    private void cboniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboniKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_cboniKeyTyped

    private void chrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chrsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chrsActionPerformed

    private void chrsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chrsKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_chrsKeyReleased

    private void chrsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chrsKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_chrsKeyTyped
public void preview() {

        ImageIcon im = new ImageIcon(tphoto.getImagen()); // obtengo la imagen. 
        // creare una condicion cuando la imagen sea demasiado grande

       // if (im.getIconWidth() > 50 || im.getIconHeight() > 50) {
            ImageIcon imaescala = new ImageIcon(im.getImage().getScaledInstance(254, 206, Image.SCALE_AREA_AVERAGING));
            imagen1.setIcon(imaescala);
       // } else {
         //   imagen1.setIcon(im);
        //}



    }

// Este Metodo es para moverme al siguiente plano
public void next(){
       if (panelTab.getSelectedIndex()==3)
           panelTab.setSelectedIndex(0);
       else
        panelTab.setSelectedIndex(panelTab.getSelectedIndex()+1);
}
// Este Metodo es para moverme al anterior plano
public void back(){
      if (panelTab.getSelectedIndex()==0)
           panelTab.setSelectedIndex(3);
       else  
        panelTab.setSelectedIndex(panelTab.getSelectedIndex()-1);
    
}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        tphoto.capturarImagen();
        preview();
        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       if (checkInfo())  // si esta verdadero pasa 
            next();
       // sino no 
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
      if (checkMoney())
        back();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       if (checkMoney())
         next();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        labelDates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
        back();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       
        labelDates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
        next();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        labelFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
        jButton1ActionPerformed(evt);
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        labelFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
        back();
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NewEmployee().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel VIDEO1;
    private com.toedter.calendar.JDateChooser calendar1;
    private com.toedter.calendar.JDateChooser calendar2;
    private com.toedter.calendar.JDateChooser calendar3;
    private javax.swing.JTextField cape;
    private javax.swing.JTextField cboni;
    private javax.swing.JTextField cced;
    private javax.swing.JTextArea cdir;
    private javax.swing.JTextField chrs;
    private javax.swing.JTextField cigss;
    private javax.swing.JTextField cnom;
    private javax.swing.JComboBox cpuesto;
    private javax.swing.JTextField csue;
    private javax.swing.JTextField csuel;
    private javax.swing.JTextField ctel;
    public static javax.swing.JLabel imagen1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDates;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelInfo;
    private javax.swing.JLabel labelMoney;
    public static javax.swing.JLabel nameframe;
    private javax.swing.JTabbedPane panelTab;
    // End of variables declaration//GEN-END:variables
}
