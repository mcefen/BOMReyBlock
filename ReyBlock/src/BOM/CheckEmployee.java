
package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class CheckEmployee extends javax.swing.JFrame {

    boolean verificacion = false;
    
    // Variables de base de datos 
    private Statement st = DataBaseClass.getSt();
    private Connection connection = DataBaseClass.getConnection();
    private ResultSet resultSet = DataBaseClass.getResultSet();

    private String pathFoto = "";
    private String foto = "";
    private JMFCamara tphoto = new JMFCamara();
    
    private int codigoModificar = 0;


  
    public CheckEmployee(int cod) {
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");
       
        initComponents();
        
        //Color 
        jPanel3.setBackground(ConfigClass.getColorApp());
        
        
        
        // Configuro el codigo que esta ingresando 
        this.codigoModificar = cod ;
        labelName.setText(""+"Codigo : "+codigoModificar);
        
        
        
        /*Load all data of the especific employee */
         
        Object data[] = new DataBaseClass().giveData("SELECT * FROM personal WHERE codigo = "+codigoModificar+";");
        
        /* Put the info in the specific field */
        cnom.setText(""+data[1]);
        cape.setText(""+data[2]);
        cpuesto.setSelectedItem(""+data[3]);
        cced.setText(""+data[4]);
        cdir.setText(""+data[5]);
        ctel.setText(""+data[6]);
        csue.setText(""+data[7]);
        cigss.setText(""+data[8]);
        csuel.setText(""+data[9]);
        cboni.setText(""+data[10]);
        chrs.setText(""+data[11]);
        // Foto
            byte [] bits =  null;
            bits = (byte[]) data[12];

            ImageIcon imagef;
            
            if (bits!= null)
                imagef = new ImageIcon (bits);
            else 
                imagef = new ImageIcon(getClass().getResource("/Imagenes/user (2).png"));
            
            
             // creare una condicion cuando la imagen sea demasiado grande

            if (imagef.getIconWidth() > 50 || imagef.getIconHeight() > 50) {
                ImageIcon imaescala = new ImageIcon(imagef.getImage().getScaledInstance(254, 206,Image.SCALE_AREA_AVERAGING));
                imagen1.setIcon(imaescala);

            } else {
                imagen1.setIcon(imagef);
            }

        // Fechas importantes. 
        try {
            
            calendar1.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(data[13].toString())); // Fecha de ingreso
            calendar2.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(data[14].toString())); // Fecha de Liquidacion
            calendar3.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(data[15].toString())); // Fecha de nacimiento
        
        } catch (ParseException ex) {
            Logger.getLogger(CheckEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        Para que no me de problemas le coloca la fecha del dia de hoy a todos los calendarios 
        
        */
        calendarHoras.setDate(Calendar.getInstance().getTime());
        calendarHoras2.setDate(calendarHoras.getDate());  // cambio de datos 
        calendarDescuentos.setDate(Calendar.getInstance().getTime());
        calendarDescuentos2.setDate(calendarDescuentos.getDate());  // cambio de datos 
        calendarWork.setDate(Calendar.getInstance().getTime());
        calendarWork2.setDate(calendarWork.getDate());  // cambio de datos 
        /* 
            Load the info of tables. 
        */
        
        changeData1();
        changeData2();
        changeData3();
        
        /*
            Fin de cargar los datos en las tablas 
        */
        
        
        
        //se coloca un layout tipo CAJA    
        try {  // este try es para que atrape si no hay camara. y continue ejecutando la instruccion 
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

    private CheckEmployee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        workTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        nameframe = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
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
        jPanel8 = new javax.swing.JPanel();
        labelFoto = new javax.swing.JLabel();
        labelDates = new javax.swing.JLabel();
        labelMoney = new javax.swing.JLabel();
        labelInfo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        bnew1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        horasTable = new javax.swing.JTable();
        bexc2 = new javax.swing.JButton();
        calendarHoras = new com.toedter.calendar.JDateChooser();
        calendarHoras2 = new com.toedter.calendar.JDateChooser();
        trash1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        descuentosTable = new javax.swing.JTable();
        bnew = new javax.swing.JButton();
        bexc1 = new javax.swing.JButton();
        trash = new javax.swing.JLabel();
        calendarDescuentos = new com.toedter.calendar.JDateChooser();
        calendarDescuentos2 = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        workTable = new javax.swing.JTable();
        bexc = new javax.swing.JButton();
        calendarWork2 = new com.toedter.calendar.JDateChooser();
        calendarWork = new com.toedter.calendar.JDateChooser();
        jScrollPane6 = new javax.swing.JScrollPane();
        workTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        workTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        workTable1.setRowHeight(30);
        jScrollPane5.setViewportView(workTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(116, 186, 232));

        nameframe.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        nameframe.setForeground(new java.awt.Color(255, 255, 255));
        nameframe.setText("Chequear Empleado");

        labelName.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        labelName.setForeground(new java.awt.Color(255, 255, 255));
        labelName.setText("Nombre del empleado ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameframe)
                .addGap(59, 59, 59)
                .addComponent(labelName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(nameframe)
                .addComponent(labelName))
        );

        panelTab.setTabPlacement(javax.swing.JTabbedPane.LEFT);

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
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel11)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addGap(18, 18, 18)
                    .addComponent(cape, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel7)
                    .addGap(18, 18, 18)
                    .addComponent(cpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(42, 42, 42)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel4)
                .addComponent(jLabel10)
                .addComponent(jLabel9))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cced, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ctel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(cigss, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton8)
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
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel9)
                .addComponent(cced, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cape, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ctel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))))
            .addGap(18, 18, 18)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel7)
                .addComponent(cigss, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4))
            .addGap(32, 32, 32)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
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

    jLabel12.setText("Horas Extras");
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

    jLabel6.setText("Sueldo (Tarifa)");
    jLabel6.setFocusable(false);

    jLabel8.setText("Bonificacion (Tarifa)");
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
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel12))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(csue, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chrs, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cboni, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(csuel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 203, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jButton9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7)))
            .addContainerGap())
    );

    jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel12, jLabel3, jLabel6, jLabel8});

    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(csue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3))
            .addGap(18, 18, 18)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12)
                .addComponent(chrs, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(26, 26, 26)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(csuel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cboni, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING))
            .addGap(24, 24, 24))
    );

    jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {csuel, jLabel12, jLabel3, jLabel6, jLabel8});

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
            .addGap(208, 386, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jButton4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton5)
            .addGap(19, 19, 19))
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton5))
                .addGroup(jPanel5Layout.createSequentialGroup()
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
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                    .addComponent(jButton4)))
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
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jButton10)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(VIDEO1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(40, 40, 40))
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(130, 130, 130)
            .addComponent(jButton2)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(50, 50, 50)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(VIDEO1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(imagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButton2)
            .addGap(4, 4, 4)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton6)
                .addComponent(jButton10))
            .addGap(15, 15, 15))
    );

    panelTab.addTab("Fotografía Empleado", jPanel1);

    labelFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
    labelFoto.setText("Fotografía del Empleado");

    labelDates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
    labelDates.setText("Fechas Importantes del Empleado");

    labelMoney.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
    labelMoney.setText("Tarifas de Pago del Empleado");

    labelInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
    labelInfo.setText("Información del Empleado");

    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/confirm.png"))); // NOI18N
    jButton1.setText("Confirmado");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(labelMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelDates, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 0, Short.MAX_VALUE))
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(23, Short.MAX_VALUE))
    );

    jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelDates, labelFoto, labelInfo, labelMoney});

    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
            .addComponent(labelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labelMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labelDates, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelDates, labelFoto, labelInfo, labelMoney});

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
            .addGap(0, 843, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTab, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(279, Short.MAX_VALUE)))
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTab, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addContainerGap()))
    );

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("Información Empleado", jPanel10);

    bnew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316277158_filenew.png"))); // NOI18N
    bnew1.setText("Nuevo");

    horasTable.setModel(new javax.swing.table.DefaultTableModel(
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
    horasTable.setRowHeight(50);
    jScrollPane4.setViewportView(horasTable);

    bexc2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340308507_application-vnd.ms-excel.png"))); // NOI18N
    bexc2.setText("Excel");
    bexc2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bexc2ActionPerformed(evt);
        }
    });

    calendarHoras.setFocusCycleRoot(true);
    calendarHoras.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            calendarHorasPropertyChange(evt);
        }
    });

    calendarHoras2.setFocusCycleRoot(true);
    calendarHoras2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            calendarHoras2PropertyChange(evt);
        }
    });

    trash1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empty.png"))); // NOI18N

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bexc2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addComponent(bnew1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(trash1))
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(calendarHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(calendarHoras2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(18, 18, 18)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)
            .addContainerGap())
    );

    jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bexc2, calendarHoras, calendarHoras2});

    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(bnew1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(bexc2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(47, 47, 47)
                    .addComponent(calendarHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(calendarHoras2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(136, 136, 136)
                    .addComponent(trash1)))
            .addGap(21, 21, 21))
    );

    jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bexc2, calendarHoras, calendarHoras2});

    jTabbedPane1.addTab("Horas Extras de Empleado", jPanel6);

    descuentosTable.setModel(new javax.swing.table.DefaultTableModel(
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
    descuentosTable.setRowHeight(50);
    jScrollPane3.setViewportView(descuentosTable);

    bnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316277158_filenew.png"))); // NOI18N
    bnew.setText("Nuevo");

    bexc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340308507_application-vnd.ms-excel.png"))); // NOI18N
    bexc1.setText("Excel");
    bexc1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bexc1ActionPerformed(evt);
        }
    });

    trash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empty.png"))); // NOI18N

    calendarDescuentos.setFocusCycleRoot(true);
    calendarDescuentos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            calendarDescuentosPropertyChange(evt);
        }
    });

    calendarDescuentos2.setFocusCycleRoot(true);
    calendarDescuentos2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            calendarDescuentos2PropertyChange(evt);
        }
    });

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bexc1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addComponent(bnew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(trash))
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(calendarDescuentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(calendarDescuentos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(18, 18, 18)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
            .addContainerGap())
    );

    jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bexc1, calendarDescuentos, calendarDescuentos2});

    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addComponent(bnew, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(bexc1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(47, 47, 47)
                    .addComponent(calendarDescuentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(calendarDescuentos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(136, 136, 136)
                    .addComponent(trash)))
            .addGap(21, 21, 21))
    );

    jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bexc1, calendarDescuentos, calendarDescuentos2});

    jTabbedPane1.addTab("Descuentos de Empleado", jPanel7);

    workTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    workTable.setModel(new javax.swing.table.DefaultTableModel(
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
    workTable.setRowHeight(30);
    jScrollPane2.setViewportView(workTable);

    bexc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340308507_application-vnd.ms-excel.png"))); // NOI18N
    bexc.setText("Excel");
    bexc.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bexcActionPerformed(evt);
        }
    });

    calendarWork2.setFocusCycleRoot(true);
    calendarWork2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            calendarWork2PropertyChange(evt);
        }
    });

    calendarWork.setFocusCycleRoot(true);
    calendarWork.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            calendarWorkPropertyChange(evt);
        }
    });

    workTable2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    workTable2.setModel(new javax.swing.table.DefaultTableModel(
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
    workTable2.setRowHeight(30);
    jScrollPane6.setViewportView(workTable2);

    jLabel1.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
    jLabel1.setText("Trabajo Relacionados a Viajes");

    jLabel16.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
    jLabel16.setText("Trabajo Relacionados a Producción");

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(calendarWork, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(calendarWork2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bexc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );

    jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bexc, calendarWork, calendarWork2});

    jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane2, jScrollPane6});

    jPanel9Layout.setVerticalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(bexc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)
                    .addComponent(calendarWork, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(calendarWork2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1)
                    .addGap(11, 11, 11)))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bexc, calendarWork, calendarWork2});

    jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane2, jScrollPane6});

    jTabbedPane1.addTab("Trabajo de Empleado", jPanel9);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents
    
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
    
    public void updateData() {
      // Cada vez que hace este metodo inicio las variables de false de las booleanas
      
    if (checkInfo() == true && checkMoney() == true) {
        verificacion = true;
        
        try {
            int resultado = 0;

            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("UPDATE personal SET Nombre = ?,Apellido = ?,Puesto = ?,DPI = ?,Direccion = ?,Telefono = ?,Sueldo_Base = ?,IGSS = ?,Tarifa_Sueldo = ?, Tarifa_Bonificacion = ? ,Tarifa_Horas_Extras = ?,Foto = ? WHERE codigo = "+codigoModificar);

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
            
            File photoToSave = new File(System.getProperty("user.dir") + "\\src\\Empleados\\"+cnom.getText()+"empleado"+cape.getText()+".jpg");
            // si no es null la imagen guardo la ultima que tome. 
            if (tphoto.getImagen() != null){
                try {
                    ImageIO.write((RenderedImage) tphoto.getImagen(),"JPEG",photoToSave);
                } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error al cargar foto : "+ex);
                }
            }
            else {
                // si es nula voy a tomar la que tengo como icono. 
                ImageIcon ii = (ImageIcon) imagen1.getIcon();
                Image im = ii.getImage();
                 try {
                            BufferedImage bi = new BufferedImage(im.getWidth(null),im.getHeight(null), BufferedImage.TYPE_INT_RGB);
                            Graphics g = bi.getGraphics();                    
                            g.drawImage(im,0, 0,null);     
                            g.dispose();                  

                            ImageIO.write(bi,"JPEG",photoToSave);
                        } catch (IOException ex) {

                        JOptionPane.showMessageDialog(rootPane, "Error al cargar foto : "+ex);
                        }
            }
            
            // despues guardo la imagen en la base de datos y listo 
            FileInputStream fis = null;

                try {
                    fis = new FileInputStream(photoToSave);
                } catch (FileNotFoundException ex) {
                    fis = null;
                }

            ps.setBinaryStream(12, fis, (int) photoToSave.length());
            
            resultado = ps.executeUpdate(); // envio los datos 

            
            JOptionPane.showMessageDialog(rootPane, "Dato ingresado exitosamente" );
            verificacion = true;

           // conexion.close(); // cierro la conexion

        } catch (SQLException e) {
            verificacion = false;
            JOptionPane.showMessageDialog(rootPane, e);

        } // fin del catch
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
              DataBaseClass.executeQuery("Update Personal SET "+fechaIn+" =\""+ fecha +"\" where codigo="+codigoModificar);
  }    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
          
            updateData();
        
        if (verificacion == true) {
            Index.refreshTable("Personal");
            Index.setColumnWidth();
            dispose();
            tphoto.turnOff();
           
            
            
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        labelFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
        back();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        labelFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));

        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        tphoto.capturarImagen();
        preview();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        labelDates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
        next();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        labelDates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png")));
        back();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (checkMoney())
        back();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (checkMoney())
        next();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void cboniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboniKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_cboniKeyTyped

    private void cboniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboniKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cboniKeyReleased

    private void cboniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboniActionPerformed

    private void csuelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csuelKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_csuelKeyTyped

    private void csuelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csuelKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_csuelKeyReleased

    private void csuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csuelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_csuelActionPerformed

    private void chrsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chrsKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_chrsKeyTyped

    private void chrsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chrsKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_chrsKeyReleased

    private void chrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chrsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chrsActionPerformed

    private void csueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csueKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_csueKeyTyped

    private void csueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_csueKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_csueKeyReleased

    private void csueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csueActionPerformed

    }//GEN-LAST:event_csueActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (checkInfo())  // si esta verdadero pasa
        next();
        // sino no
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cigssKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cigssKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cigssKeyTyped

    private void cigssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cigssActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cigssActionPerformed

    private void ctelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctelKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE) || (c==evt.VK_DELETE) || ((c== KeyEvent.VK_SPACE)))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_ctelKeyTyped

    private void ccedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ccedKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ccedKeyTyped

    private void ccedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ccedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ccedActionPerformed

    private void cnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnomKeyTyped

    }//GEN-LAST:event_cnomKeyTyped

    private void cnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnomActionPerformed

    }//GEN-LAST:event_cnomActionPerformed

    private void capeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_capeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_capeKeyTyped

    private void capeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_capeActionPerformed

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

    private void bexc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexc2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bexc2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        tphoto.turnOff();
    }//GEN-LAST:event_formWindowClosing

    private void calendarHorasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarHorasPropertyChange
        try {
            changeData1();
        }catch (Exception e){
            
        }
    }//GEN-LAST:event_calendarHorasPropertyChange

    private void calendarHoras2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarHoras2PropertyChange
        calendarHorasPropertyChange(evt);
    }//GEN-LAST:event_calendarHoras2PropertyChange

    private void calendarDescuentosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarDescuentosPropertyChange
        try {
            changeData2();
        }catch (Exception e){
            
        }
    }//GEN-LAST:event_calendarDescuentosPropertyChange

    private void calendarDescuentos2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarDescuentos2PropertyChange
         calendarDescuentosPropertyChange(evt);
    }//GEN-LAST:event_calendarDescuentos2PropertyChange

    private void calendarWorkPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarWorkPropertyChange
        try {
            changeData3();
            changeData4();
        }catch (Exception e){
            
        }
    }//GEN-LAST:event_calendarWorkPropertyChange

    private void calendarWork2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarWork2PropertyChange
        calendarWorkPropertyChange(evt);
    }//GEN-LAST:event_calendarWork2PropertyChange
    private static java.sql.Date getCurrentDate(com.toedter.calendar.JDateChooser calendar) {
        java.util.Date today = calendar.getDate(); 
    
    return new java.sql.Date(today.getTime());
} 
    public void changeData1() {
        String consulta = "";
        consulta = "SELECT  extras_personal.codigo, personal.nombre, personal.apellido, movimiento_personal.fecha, extras_personal.descripcion ,extras_personal.Tarifa_Hora , extras_personal.cantidad_horas,movimiento_personal.total_en_q"
                    + " FROM personal, extras_personal, movimiento_personal"
                    + " WHERE movimiento_personal.codigo_personal = personal.codigo "
                    + "     AND movimiento_personal.codigo_horas_extra IS NOT NULL "
                    + "     AND movimiento_personal.codigo_horas_extra = extras_personal.codigo "
                    + "     AND personal.codigo = "+codigoModificar+""
                    + "     AND movimiento_personal.fecha BETWEEN '"+getCurrentDate(calendarHoras)+"' AND '"+getCurrentDate(calendarHoras2)+"' "
                    + "ORDER BY movimiento_personal.fecha;"; 
        
        horasTable.setModel(new QueryModelTable( consulta,"HorasExtras" ) );
        
        /* Tamaño de sus columnas */
        horasTable.setAutoResizeMode(0);
            for (int j=0;j<horasTable.getColumnCount();j++){
                horasTable.getColumnModel().getColumn(j).setPreferredWidth(new TablesModel("HorasExtras").getColumnWidth(j));
            }
       
    }
    public void changeData2() {
        String consulta = "";
         consulta = "SELECT  descuento_personal.codigo, personal.nombre, personal.apellido, movimiento_personal.fecha, descuento_personal.descripcion ,movimiento_personal.total_en_q"
                    + " FROM personal, descuento_personal, movimiento_personal"
                    + " WHERE movimiento_personal.codigo_personal = personal.codigo "
                    + "     AND movimiento_personal.codigo_descuentos IS NOT NULL "
                    + "     AND movimiento_personal.codigo_descuentos = descuento_personal.codigo "
                    + "     AND personal.codigo = "+codigoModificar+""
                    + "     AND movimiento_personal.fecha BETWEEN '"+getCurrentDate(calendarDescuentos)+"' AND '"+getCurrentDate(calendarDescuentos2)+"' "
                   
                 + "ORDER BY movimiento_personal.fecha;"; 
        
        
        descuentosTable.setModel(new QueryModelTable( consulta,"Descuentos" ) );
        
        /* Tamaño de sus columnas */
        descuentosTable.setAutoResizeMode(0);
            for (int j=0;j<descuentosTable.getColumnCount();j++){
                descuentosTable.getColumnModel().getColumn(j).setPreferredWidth(new TablesModel("Descuentos").getColumnWidth(j));
            }
       
    }
    public void changeData3() {
       
        
        
        String consulta = "";
         consulta = "SELECT  personal.nombre, personal.apellido, movimiento_personal.fecha, movimiento_personal.cantidad_block, movimiento_personal.codigo_produccion, movimiento_personal.sueldo_en_Q, movimiento_personal.bonificacion_en_Q, movimiento_personal.total_en_Q"
                    + " FROM personal, produccion, movimiento_personal"
                    + " WHERE movimiento_personal.codigo_personal = personal.codigo "
                    + "     AND movimiento_personal.codigo_descuentos IS NULL "
                    + "     AND movimiento_personal.codigo_horas_extra IS NULL"
                    + "     AND movimiento_personal.codigo_produccion = produccion.codigo "
                    + "     AND personal.codigo = "+codigoModificar+""
                    + "     AND movimiento_personal.fecha BETWEEN '"+getCurrentDate(calendarWork)+"' AND '"+getCurrentDate(calendarWork2)+"' "
                   
                 + "ORDER BY movimiento_personal.fecha;"; 
        
        
        workTable.setModel(new QueryModelTable( consulta,"work" ) );
        
        /* Tamaño de sus columnas */
        workTable.setAutoResizeMode(0);
            for (int j=0;j<workTable.getColumnCount();j++){
                workTable.getColumnModel().getColumn(j).setPreferredWidth(150);
            }
       
    }
    public void changeData4() {
       
      
        String consulta = "";
         consulta = "SELECT  personal.nombre, personal.apellido, movimiento_personal.fecha,  movimiento_personal.codigo_viaje, movimiento_personal.total_en_Q"
                    + " FROM personal,  movimiento_personal, viaje"
                    + " WHERE movimiento_personal.codigo_personal = personal.codigo "
                    + "     AND movimiento_personal.codigo_descuentos IS NULL "
                    + "     AND movimiento_personal.codigo_horas_extra IS NULL"
                    + "     AND movimiento_personal.codigo_viaje = viaje.codigo "
                    + "     AND movimiento_personal.codigo_produccion IS NULL"
                    + "     AND personal.codigo = "+codigoModificar+""
                    + "     AND movimiento_personal.fecha BETWEEN '"+getCurrentDate(calendarWork)+"' AND '"+getCurrentDate(calendarWork2)+"' "
                   
                 + "ORDER BY movimiento_personal.fecha;"; 
        
        
        workTable2.setModel(new QueryModelTable( consulta,"work2" ) );
        
        /* Tamaño de sus columnas */
        workTable2.setAutoResizeMode(0);
            for (int j=0;j<workTable2.getColumnCount();j++){
                workTable2.getColumnModel().getColumn(j).setPreferredWidth(150);
            }
       
    }
    public void preview() {

        ImageIcon im = new ImageIcon(tphoto.getImagen()); // obtengo la imagen. 
        // creare una condicion cuando la imagen sea demasiado grande

        
            ImageIcon imaescala = new ImageIcon(im.getImage().getScaledInstance(254, 206, Image.SCALE_AREA_AVERAGING));
            imagen1.setIcon(imaescala);
        



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
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CheckEmployee().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel VIDEO1;
    private javax.swing.JButton bexc;
    private javax.swing.JButton bexc1;
    private javax.swing.JButton bexc2;
    private javax.swing.JButton bnew;
    private javax.swing.JButton bnew1;
    private com.toedter.calendar.JDateChooser calendar1;
    private com.toedter.calendar.JDateChooser calendar2;
    private com.toedter.calendar.JDateChooser calendar3;
    public com.toedter.calendar.JDateChooser calendarDescuentos;
    public com.toedter.calendar.JDateChooser calendarDescuentos2;
    public static com.toedter.calendar.JDateChooser calendarHoras;
    public static com.toedter.calendar.JDateChooser calendarHoras2;
    public com.toedter.calendar.JDateChooser calendarWork;
    public com.toedter.calendar.JDateChooser calendarWork2;
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
    private javax.swing.JTable descuentosTable;
    private javax.swing.JTable horasTable;
    public static javax.swing.JLabel imagen1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelDates;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelInfo;
    private javax.swing.JLabel labelMoney;
    public static javax.swing.JLabel labelName;
    public static javax.swing.JLabel nameframe;
    private javax.swing.JTabbedPane panelTab;
    private javax.swing.JLabel trash;
    private javax.swing.JLabel trash1;
    private javax.swing.JTable workTable;
    private javax.swing.JTable workTable1;
    private javax.swing.JTable workTable2;
    // End of variables declaration//GEN-END:variables
}
