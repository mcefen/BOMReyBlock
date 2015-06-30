package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import java.awt.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class Index extends javax.swing.JFrame {

    private static String status = "";
    private static String consulta = "select * from block";

    private boolean borrar = false;
    private boolean entro = false;
    private boolean modificar = false;

    private boolean erase = false;
    private int codigo;
    private String tipo = null;
    private String mistakeConsult = "Delete  from block where codigo =";

    // Variables de base de datos 
    private Statement st = DataBaseClass.getSt();
    private Connection connection = DataBaseClass.getConnection();
    private ResultSet resultSet = DataBaseClass.getResultSet();

    int codigoReporte = -1;  // Este codigo es generado por el systema al pedir un reporte 

    int cuentaPrueba = 0;

    public Index() {

        initComponents();
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");

        internalFrame.dispose();

        calendar.setDate(Calendar.getInstance().getTime());
        calendar2.setDate(calendar.getDate());

        // Cargo los datos de la base de datos del color 
        reDrawFrame(ConfigClass.configReader());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        productsmenu = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        recursosmenu = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        inmenu = new javax.swing.JPopupMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        reportmenu = new javax.swing.JPopupMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        gastos = new javax.swing.JPopupMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        carsMenu = new javax.swing.JPopupMenu();
        Vehiculos = new javax.swing.JMenuItem();
        labPanel = new javax.swing.JPanel();
        nameInstitucion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        programIcon = new javax.swing.JLabel();
        internalFrame = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        bnew = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMain = new javax.swing.JTable();
        trash = new javax.swing.JLabel();
        calendar = new com.toedter.calendar.JDateChooser();
        calendar2 = new com.toedter.calendar.JDateChooser();
        bexc = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        bform = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        bconfig = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316240906_deliverables.png"))); // NOI18N
        jMenuItem1.setLabel("Blocks");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        productsmenu.add(jMenuItem1);

        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Proveedores.png"))); // NOI18N
        jMenuItem20.setText("Proveedores");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        productsmenu.add(jMenuItem20);

        recursosmenu.setLabel("Recursos");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316282939_Users_Group.png"))); // NOI18N
        jMenuItem3.setText("Personal");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        recursosmenu.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340866360_raw_material.png"))); // NOI18N
        jMenuItem4.setText("Materia Prima");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        recursosmenu.add(jMenuItem4);

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340865921_packing.png"))); // NOI18N
        jMenuItem12.setText("Moldes");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        recursosmenu.add(jMenuItem12);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340866123_Community Help.png"))); // NOI18N
        jMenuItem5.setText("Produccion de Block");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        inmenu.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340867019_edit_add.png"))); // NOI18N
        jMenuItem6.setText("Horas Extras");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        inmenu.add(jMenuItem6);

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/descuento.png"))); // NOI18N
        jMenuItem13.setText("Descuentos");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        inmenu.add(jMenuItem13);

        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/material.png"))); // NOI18N
        jMenuItem19.setText("Ingreso de Viajes de Material");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        inmenu.add(jMenuItem19);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316240906_deliverables.png"))); // NOI18N
        jMenuItem7.setText("Produccion de Blocks");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        reportmenu.add(jMenuItem7);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316513843_column_chart.png"))); // NOI18N
        jMenuItem10.setText("Detalle Producciones");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        reportmenu.add(jMenuItem10);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340866360_raw_material.png"))); // NOI18N
        jMenuItem8.setText("Uso de Materia Prima");
        jMenuItem8.setActionCommand("Materia Prima Produccion");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        reportmenu.add(jMenuItem8);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340865921_packing.png"))); // NOI18N
        jMenuItem9.setLabel("Uso de Moldes");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        reportmenu.add(jMenuItem9);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316283555_Dollars.png"))); // NOI18N
        jMenuItem2.setText("Sueldo Trabajadores");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        reportmenu.add(jMenuItem2);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lostMaterial (2).png"))); // NOI18N
        jMenuItem15.setText("Costo de Block ");
        reportmenu.add(jMenuItem15);

        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/gastos (2).png"))); // NOI18N
        jMenuItem18.setText("Gastos Moldes");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        gastos.add(jMenuItem18);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/truck.png"))); // NOI18N
        jMenuItem11.setText("Gastos de Vehiculos");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        gastos.add(jMenuItem11);

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/gastos.png"))); // NOI18N
        jMenuItem14.setText("Otros Gastos");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        gastos.add(jMenuItem14);

        Vehiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/truck.png"))); // NOI18N
        Vehiculos.setText("Camiones");
        Vehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VehiculosActionPerformed(evt);
            }
        });
        carsMenu.add(Vehiculos);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labPanel.setBackground(new java.awt.Color(116, 186, 232));

        nameInstitucion.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        nameInstitucion.setForeground(new java.awt.Color(255, 255, 255));
        nameInstitucion.setText("ReyBlock   S.A.  ");

        javax.swing.GroupLayout labPanelLayout = new javax.swing.GroupLayout(labPanel);
        labPanel.setLayout(labPanelLayout);
        labPanelLayout.setHorizontalGroup(
            labPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        labPanelLayout.setVerticalGroup(
            labPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(nameInstitucion)
                .addGap(20, 20, 20))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        programIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        programIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        //Set the icon of the app.
        internalFrame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconMini.png"))); // NOI18N
        internalFrame.setTitle("B.O.M.");
        internalFrame.setClosable(true);
        internalFrame.setVisible(true);

        jPanel3.setBackground(new java.awt.Color(116, 186, 232));

        label.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setText("Label");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label)
        );

        bnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316277158_filenew.png"))); // NOI18N
        bnew.setText("Nuevo");
        bnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnewActionPerformed(evt);
            }
        });

        tableMain.setModel(new QueryModelTable( consulta,"" ));
        tableMain.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableMain.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        tableMain.setDragEnabled(true);
        tableMain.setGridColor(new java.awt.Color(0, 204, 255));
        tableMain.setRowHeight(20);
        tableMain.setRowMargin(5);
        tableMain.setSelectionBackground(new java.awt.Color(116, 186, 232));
        tableMain.getTableHeader().setReorderingAllowed(false);
        tableMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMainMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMainMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMainMouseReleased(evt);
            }
        });
        tableMain.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tableMainMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tableMainMouseMoved(evt);
            }
        });
        jScrollPane2.setViewportView(tableMain);

        trash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empty.png"))); // NOI18N
        trash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                trashMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                trashMouseEntered(evt);
            }
        });

        calendar.setFocusCycleRoot(true);
        calendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendarPropertyChange(evt);
            }
        });

        calendar2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendar2PropertyChange(evt);
            }
        });

        bexc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340308507_application-vnd.ms-excel.png"))); // NOI18N
        bexc.setText("Excel");
        bexc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout internalFrameLayout = new javax.swing.GroupLayout(internalFrame.getContentPane());
        internalFrame.getContentPane().setLayout(internalFrameLayout);
        internalFrameLayout.setHorizontalGroup(
            internalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(internalFrameLayout.createSequentialGroup()
                .addGroup(internalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalFrameLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(bnew, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(internalFrameLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(trash))
                    .addGroup(internalFrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(internalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bexc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(calendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );
        internalFrameLayout.setVerticalGroup(
            internalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrameLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(internalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalFrameLayout.createSequentialGroup()
                        .addComponent(bnew, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bexc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(calendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(trash, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(internalFrame, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(programIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(internalFrame)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(programIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340865921_packing.png"))); // NOI18N
        jButton2.setText("Productos");
        jButton2.setInheritsPopupMenu(true);
        jButton2.setNextFocusableComponent(productsmenu);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1316277158_filenew.png"))); // NOI18N
        jButton3.setText("Ingreso");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton3MouseReleased(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340865769_custom-reports.png"))); // NOI18N
        jButton4.setText("Reportes");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton4MouseReleased(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340866123_Community Help.png"))); // NOI18N
        jButton5.setText("Recursos");
        jButton5.setInheritsPopupMenu(true);
        jButton5.setNextFocusableComponent(recursosmenu);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton5MouseReleased(evt);
            }
        });

        bform.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340865665_application_form_edit.png"))); // NOI18N
        bform.setText("Formulario");
        bform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bformActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/camion.png"))); // NOI18N
        jButton7.setText("Vehiculos");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton7MouseReleased(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/gastos.png"))); // NOI18N
        jButton8.setText("Gastos");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton8MouseReleased(evt);
            }
        });

        bconfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1340865590_cog.png"))); // NOI18N
        bconfig.setText("Configuraciones");
        bconfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bconfigActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/machine.png"))); // NOI18N
        jButton9.setText("Maquinas");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton9MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bform, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bconfig, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(bform, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(bconfig, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed

        if (evt.isPopupTrigger()) {

            productsmenu.show(jButton2, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
        if (evt.isPopupTrigger()) {

            productsmenu.show(jButton2, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jButton2MouseReleased
    private void configurarParaMostrar() {
        tableMain.setRowHeight(30);
        bexc.setVisible(false);
        bnew.setVisible(false);
        trash.setVisible(false);
        calendar.setVisible(false);
        calendar2.setVisible(false);
        internalFrame.setVisible(true);
        erase = false;

        if (status.equalsIgnoreCase("Personal")) {
            Index.label.setText("Personal");
            bnew.setVisible(true);
            trash.setVisible(true);
            erase = false;
            mistakeConsult = "DELETE FROM personal WHERE codigo =";

            tableMain.setDefaultRenderer(JLabel.class, new PhotoCell());
            tableMain.setDefaultEditor(JLabel.class, new UpdatePhoto());
            tableMain.setRowHeight(150);

            // La tabla cambia solo con el estado. 
            refreshTable(status);

        } else if (status.equalsIgnoreCase("HorasExtras")) {

            Index.label.setText("Ingreso de Horas Extras");
            erase = true;
            mistakeConsult = "Delete  from extras_personal where codigo =";
            bnew.setVisible(true);
            trash.setVisible(true);
            tableMain.setRowHeight(50);
            bexc.setVisible(false);

            // La tabla cambia solo con el estado. 
            refreshTable(status);

        } else if (status.equalsIgnoreCase("Descuentos")) {
            Index.label.setText("Ingreso de Descuentos");
            erase = true;
            mistakeConsult = "Delete  from descuento_personal where codigo =";

            bnew.setVisible(true);
            trash.setVisible(true);
            tableMain.setRowHeight(50);
            bexc.setVisible(false);
            // La tabla cambia solo con el estado. 
            refreshTable(status);

        } else if (status.equalsIgnoreCase("Materia")) {

            Index.label.setText("Materia Prima");
            mistakeConsult = "Delete from Materia where codigo =";
            erase = false;
            bnew.setVisible(true);
            trash.setVisible(true);

            // La tabla cambia solo con el estado. 
            refreshTable(status);

        } else if (status.equalsIgnoreCase("Molde")) {
            label.setText("Moldes");
            mistakeConsult = "Delete from Molde where codigo =";
            erase = false;
            bnew.setVisible(true);
            trash.setVisible(true);

            // La tabla cambia solo con el estado. 
            refreshTable(status);

        } else if (status.equalsIgnoreCase("Block")) {
            Index.label.setText("Blocks");
            mistakeConsult = "Delete from block where codigo =";
            erase = false;
            bnew.setVisible(true);
            trash.setVisible(true);

            // La tabla cambia solo con el estado. 
            refreshTable(status);

        } else if (status.equalsIgnoreCase("Proveedor")) {
            Index.label.setText("Proveedor");
            mistakeConsult = "Delete from proveedor where codigo =";
            erase = false;
            bnew.setVisible(true);
            trash.setVisible(true);
            tableMain.setRowHeight(50);

            // La tabla cambia solo con el estado. 
            refreshTable(status);

        } else if (status.equalsIgnoreCase("Vehiculo")) {

            Index.label.setText("Vehiculos");
            mistakeConsult = "Delete from vehiculo where codigo = ";
            erase = false;
            bnew.setVisible(true);
            trash.setVisible(true);

            // La tabla cambia solo con el estado. 
            refreshTable(status);

        } else if (status.equalsIgnoreCase("Produccion")) {

            Index.label.setText("Producción de Block");
            mistakeConsult = "Delete from produccion where codigo =";
            erase = true;
            bnew.setVisible(true);
            trash.setVisible(true);
            bexc.setVisible(false);

            // La tabla cambia solo con el estado. 
            refreshTable(status);

        } else if (status.equalsIgnoreCase("Ingreso de Material")) {

            Index.label.setText("Ingreso de Viajes de Materia Prima");
            mistakeConsult = "Delete from viaje where codigo =";
            erase = true;
            bnew.setVisible(true);
            trash.setVisible(true);
            bexc.setVisible(false);
            tableMain.setRowHeight(50);

            // La tabla cambia solo con el estado. 
            refreshTable(status);

        } else if (status.equalsIgnoreCase("Ingreso de Otros Gastos")) {

            Index.label.setText("Ingreso de Otros gastos");
            mistakeConsult = "Delete from gasto_empresa where codigo =";
            erase = true;
            bnew.setVisible(true);
            trash.setVisible(true);
            bexc.setVisible(false);
            tableMain.setRowHeight(70);

            // La tabla cambia solo con el estado. 
            refreshTable(status);

        } else if (status.equalsIgnoreCase("reporteProduccion")) {

            Index.label.setText("Produccion de Blocks");
            calendar.setVisible(true);
            calendar2.setVisible(true);

            calendar.setDate(Calendar.getInstance().getTime());
            calendar2.setDate(calendar.getDate());  // cambio de datos 

            changeData();

            bexc.setVisible(true);

        } else if (status.equalsIgnoreCase("reporteProducciones")) {  // Este reporte es parecido al reporte de Produccion solo que en este tenemos un detalle de las producciones

            Index.label.setText("Detalle de Producciones");
            calendar.setVisible(true);
            calendar2.setVisible(true);

            calendar.setDate(Calendar.getInstance().getTime());
            calendar2.setDate(calendar.getDate());

            changeData();  // cambio de datos 

            bexc.setVisible(true);

        } else if (status.equalsIgnoreCase("reporteMateria")) {
            Index.label.setText("Materia Prima Utilizada en Produccion");
            calendar.setVisible(true);
            calendar2.setVisible(true);

            calendar.setDate(Calendar.getInstance().getTime());
            calendar2.setDate(calendar.getDate());  // cambio de datos 

            changeData();

            bexc.setVisible(true);

        } else if (status.equalsIgnoreCase("reporteMolde")) {

            Index.label.setText("Utilización de moldes en Produccion");
            calendar.setVisible(true);
            calendar2.setVisible(true);

            calendar.setDate(Calendar.getInstance().getTime());
            calendar2.setDate(calendar.getDate());  // cambio de datos 

            changeData();

            bexc.setVisible(true);

        } else if (status.equalsIgnoreCase("reporteSueldos")) {

            Index.label.setText("Trabajo de empleados");
            calendar.setVisible(true);
            calendar2.setVisible(true);

            calendar.setDate(Calendar.getInstance().getTime());
            calendar2.setDate(calendar.getDate());  // cambio de datos

            changeData();

            bexc.setVisible(true);

        }

    }

    public static void setColumnWidth() {
        tableMain.setAutoResizeMode(0);
        for (int j = 0; j < tableMain.getColumnCount(); j++) {
            tableMain.getColumnModel().getColumn(j).setPreferredWidth(new TablesModel(status).getColumnWidth(j));
        }

    }

    public static void setQueryTable() {
        tableMain.setModel(new QueryModelTable(consulta, status));
        tableMain.setAutoResizeMode(0);
        for (int j = 0; j < tableMain.getColumnCount(); j++) {
            tableMain.getColumnModel().getColumn(j).setPreferredWidth(new QueryModelTable(consulta, status).getColumnWidth(j));
        }

    }

    private void setColumnWidth(int width) {
        tableMain.setAutoResizeMode(2);
        for (int j = 0; j < tableMain.getColumnCount(); j++) {
            tableMain.getColumnModel().getColumn(j).setPreferredWidth(width);
        }

    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        status = "Block";
        configurarParaMostrar();

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void bnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnewActionPerformed
        if (status.equalsIgnoreCase("block")) {
            NewBlock va = new NewBlock();
            va.setLocationRelativeTo(null);
            va.setVisible(true);

        } else if (status.equalsIgnoreCase("personal")) {
            NewEmployee va = new NewEmployee();
            va.setLocationRelativeTo(null);
            va.setVisible(true);
        } else if (status.equalsIgnoreCase("Materia")) {
            NewMaterial va = new NewMaterial();

            va.setLocationRelativeTo(null);
            va.setVisible(true);
        } else if (status.equalsIgnoreCase("Molde")) {
            NewMold va = new NewMold();
            va.setLocationRelativeTo(null);
            va.setVisible(true);
        } else if (status.equalsIgnoreCase("Gasto_Molde")) {
            NewGasto va = new NewGasto(-1);
            va.setLocationRelativeTo(null);
            va.setVisible(true);
        } else if (status.equalsIgnoreCase("Vehiculo")) {
            NewCar nc = new NewCar();
            nc.setLocationRelativeTo(null);
            nc.setVisible(true);
        } else if (status.equalsIgnoreCase("Ingreso de Material")) {
            MaterialIn va = new MaterialIn();
            va.setLocationRelativeTo(null);
            va.setVisible(true);
        } else if (status.equalsIgnoreCase("Proveedor")) {
            NewProveedor va = new NewProveedor();
            va.setLocationRelativeTo(null);
            va.setVisible(true);
        } else if (status.equalsIgnoreCase("Produccion")) {

            NewProduction va = new NewProduction();
            va.setLocationRelativeTo(null);
            va.setVisible(true);

        } else if (status.equalsIgnoreCase("Descuentos")) {
            NewDescuento va = new NewDescuento();
            va.setLocationRelativeTo(null);
            va.setVisible(true);
        } else if (status.equalsIgnoreCase("HorasExtras")) {
            NewExtra va = new NewExtra();
            va.setLocationRelativeTo(null);
            va.setVisible(true);
        }else if (status.equalsIgnoreCase("Ingreso de Otros Gastos")) {
            NewOtroGasto va = new NewOtroGasto();
            va.setLocationRelativeTo(null);
            va.setVisible(true);
        }
    }//GEN-LAST:event_bnewActionPerformed

    private void tableMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMainMouseClicked
        /* Esta parte la puedo generalizar 
         * ya que se repite varias veces 
         */
        int num = -1;  // Inicializo la variables // Como siempre estara en la primera columna el codigo entonces siempre num sera el codigo del cual yo estoy apachando 
        int fila = tableMain.rowAtPoint(evt.getPoint());
        if (fila > -1) {

            Object ob = tableMain.getModel().getValueAt(fila, 0);
            try {
                num = Integer.parseInt(ob.toString());

            } catch (Exception e) {
                num = -1;

            }
        }

        // Termina el proceso que se repetira siempre que den click 
        if (status.equalsIgnoreCase("molde")) {

            CheckMold va = new CheckMold(num);
            va.setLocationRelativeTo(null);
            va.setVisible(true);

        } else if (status.equalsIgnoreCase("Personal")) {

            CheckEmployee va = new CheckEmployee(num);
            va.setLocationRelativeTo(null);
            va.setVisible(true);

        } else if (status.equalsIgnoreCase("Materia")) {

            CheckMaterial va = new CheckMaterial(num);
            va.setLocationRelativeTo(null);
            va.setVisible(true);

        } else if (status.equalsIgnoreCase("Block")) {

            CheckBlock va = new CheckBlock(num);
            va.setLocationRelativeTo(null);
            va.setVisible(true);

        } else if (status.equalsIgnoreCase("Vehiculo")) {

            CheckCar va = new CheckCar(num);
            va.setLocationRelativeTo(null);
            va.setVisible(true);

        } else if (status.equalsIgnoreCase("Proveedor")) {

            CheckProveedor va = new CheckProveedor(num);
            va.setLocationRelativeTo(null);
            va.setVisible(true);
        } else if (status.equalsIgnoreCase("Ingreso de Material")) {

            CheckMaterialIn va = new CheckMaterialIn(num);
            va.setLocationRelativeTo(null);
            va.setVisible(true);

        } else if (status.equalsIgnoreCase("Produccion")) {

            CheckProduction va = new CheckProduction(num);
            va.setLocationRelativeTo(null);
            va.setVisible(true);
        }


}//GEN-LAST:event_tableMainMouseClicked
    private static java.sql.Date getCurrentDate(com.toedter.calendar.JDateChooser calendar) {
        java.util.Date date = calendar.getDate();

        return new java.sql.Date(date.getTime());
    }
    private void tableMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMainMousePressed
        int row = -1;
        if (erase) {

            row = tableMain.rowAtPoint(evt.getPoint());

            borrar = true;

            // accion qee va hacer siempre
            if (row > -1) {

                Object objeto = tableMain.getModel().getValueAt(row, 0);

                String ja = objeto.toString();
                // leo el tipo
                objeto = tableMain.getModel().getValueAt(row, 1);
                tipo = objeto.toString();

                codigo = Integer.parseInt(ja);

            }
        }
    }//GEN-LAST:event_tableMainMousePressed

    private void tableMainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMainMouseReleased
        if (erase) { // si es igual a true 
            // accion del release
            internalFrame.setCursor(null);
            if (borrar == true && entro == true) {

                int messageType = JOptionPane.QUESTION_MESSAGE;
                String[] options = {"Continuar", "Cancelar"};
                int code = JOptionPane.showInternalOptionDialog(this.getContentPane(),
                        "¿Desea realmente borrar a " + tipo + ", codigo " + codigo + "?",
                        "Confirmación Eliminacion \n" + status + "", 1, messageType,
                        null, options, "Cancelar");

                if (code == 0) {
                    /* Este if es en caso que sea el ingreso de material 
                     esto quiere decir que primero mira si es posible eliminar
                     este material */
                    if (status.equalsIgnoreCase("Ingreso de Material")) {
                        if (checkAvaility()) { // si es verdadero y si se puede cambiar lo hago sino no. 

                            DataBaseClass.executeQuery(mistakeConsult + codigo);
                            // reconfiguro todo
                            refreshTable(status);

                        }

                    } /*
                     Este segundo if es en caso de produccion lo que hare es de que no solo eliminare 
                     la produccion sino que tambien eliminare el movimiento de material que corresponde 
                     a esta produccion antes de eliminar la produccion. ! 
                     */ else if (status.equalsIgnoreCase("Produccion")) {
                        DataBaseClass.executeQuery("DELETE FROM movimiento_material WHERE codigo_produccion = " + codigo);
                        DataBaseClass.executeQuery(mistakeConsult + codigo);
                        // reconfiguro todo 
                        refreshTable(status);

                    } else {
                        DataBaseClass.executeQuery(mistakeConsult + codigo);
                        // reconfiguro todo
                        refreshTable(status);
                    }

                } else if (code == 1) {
                    internalFrame.setCursor(null);
                }
                borrar = false;
                entro = false;
            }

            trash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empty.png")));
            borrar = false;

        }
}//GEN-LAST:event_tableMainMouseReleased
    private boolean checkAvaility() {

        int codigoMaterial = -1;
        double ingresado = 0;
        double gastado = 0;
        double balance = 0;
        String nombre = "";
        ResultSetMetaData metaData;
        int numcolumn = -1;
        double aEliminar = 0;
        String error = "";
        String unidades = "";

        codigoMaterial = Integer.parseInt(new DataBaseClass().giveData("SELECT materia.codigo FROM materia, movimiento_material, viaje WHERE Movimiento_material.codigo_material_entrante = viaje.codigo AND movimiento_material.codigo_produccion IS NULL AND movimiento_material.codigo_material = materia.codigo AND viaje.codigo = " + codigo)[0].toString());
        nombre = new DataBaseClass().giveData("SELECT materia.nombre FROM materia, movimiento_material, viaje WHERE Movimiento_material.codigo_material_entrante = viaje.codigo AND movimiento_material.codigo_produccion IS NULL AND movimiento_material.codigo_material = materia.codigo AND viaje.codigo = " + codigo)[0].toString();
        aEliminar = Double.parseDouble(new DataBaseClass().giveData("SELECT movimiento_material.cantidad FROM materia, movimiento_material, viaje WHERE Movimiento_material.codigo_material_entrante = viaje.codigo AND movimiento_material.codigo_produccion IS NULL AND movimiento_material.codigo_material = materia.codigo AND viaje.codigo = " + codigo)[0].toString());
        unidades = new DataBaseClass().giveData("SELECT materia.presentacion FROM materia, movimiento_material, viaje WHERE Movimiento_material.codigo_material_entrante = viaje.codigo AND movimiento_material.codigo_produccion IS NULL AND movimiento_material.codigo_material = materia.codigo AND viaje.codigo = " + codigo)[0].toString();

        try {
            // Aqui no es un prepared Statement porque solo es uno 
            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT SUM(Cantidad) FROM movimiento_material WHERE codigo_produccion IS NULL AND codigo_material_entrante IS NOT NULL AND existencia = 1 AND codigo_material =" + codigoMaterial + " AND codigo_material_entrante !=" + codigo + ";");
            metaData = (ResultSetMetaData) resultSet.getMetaData();
            numcolumn = metaData.getColumnCount(); // numero de Columnas

            while (resultSet.next()) {
                ingresado = resultSet.getDouble(1);
            }

            // Aqui no es un prepared Statement porque solo es uno 
            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT SUM(Cantidad) FROM movimiento_material WHERE codigo_produccion IS NOT NULL AND codigo_material_entrante IS NOT NULL AND existencia = 2 AND codigo_material =" + codigoMaterial + ";");
            metaData = (ResultSetMetaData) resultSet.getMetaData();
            numcolumn = metaData.getColumnCount(); // numero de Columnas

            while (resultSet.next()) {
                gastado = resultSet.getDouble(1);
            }

            balance = (ingresado) - gastado;
            if (balance < 0) {
                error += (-balance) + " " + unidades + " de " + nombre + "\n";
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "No puede eliminar este viaje. \n" + ex, "Imposibilidad de borrar", 0);
        }

        if (error.isEmpty()) {

            return true;
        } else {
            JOptionPane.showMessageDialog(rootPane, "No puede eliminar este viaje, al eliminarlo faltaria : \n" + error, "Dato incoherente", 0);

            return false;

        }

    }
    private void tableMainMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMainMouseDragged
        if (erase) {
            if (borrar == true && entro == false) {

                Toolkit tk = Toolkit.getDefaultToolkit();
                Image imagen = tk.getImage(System.getProperty("user.dir") + "" + File.separator + "src" + File.separator + "Imagenes" + File.separator + "basura.png");
                Point hotSpot = new Point(0, 0);
                Cursor cursor = tk.createCustomCursor(imagen, hotSpot, "basura");
                internalFrame.setCursor(cursor);

            }
        }

}//GEN-LAST:event_tableMainMouseDragged

    private void trashMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trashMouseEntered
        if (erase) {
            if (borrar == true) {
                entro = true;
                trash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/full.png")));
            }
        }
}//GEN-LAST:event_trashMouseEntered

    private void trashMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trashMouseExited
        if (erase) {
            entro = false;
            borrar = false;
            trash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empty.png")));
        }
}//GEN-LAST:event_trashMouseExited

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        if (evt.isPopupTrigger()) {

            recursosmenu.show(jButton5, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jButton5MousePressed

    private void jButton5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseReleased
        if (evt.isPopupTrigger()) {

            recursosmenu.show(jButton5, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jButton5MouseReleased

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        status = "Personal";
        configurarParaMostrar();


    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        status = "Materia";
        configurarParaMostrar();

}//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        status = "Produccion";
        configurarParaMostrar();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        if (evt.isPopupTrigger()) {

            inmenu.show(jButton3, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jButton3MousePressed

    private void jButton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseReleased
        if (evt.isPopupTrigger()) {

            inmenu.show(jButton3, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jButton3MouseReleased

    private void calendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarPropertyChange

        changeData();
    }//GEN-LAST:event_calendarPropertyChange

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        if (evt.isPopupTrigger()) {

            reportmenu.show(jButton4, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jButton4MousePressed
    public String[] intervalDates() {

        Date date = (Date) calendar.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicial = format.format(date);

        Date dateFinal = (Date) calendar2.getDate();
        SimpleDateFormat formatFinal = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFinal = formatFinal.format(dateFinal);

        String arra[] = {fechaInicial, fechaFinal};
        return arra;
    }

    public void changeData() {
        // si el calendario cambia de fecha haga algo 
        if (status.equalsIgnoreCase("reporteProduccion")) {

            // Primero creo el reporte de produccion para luego trabajar en base a el. 
            codigoReporte = crearReporteProduccion();
            ingresarBlocksProducidos(codigoReporte);
            ingresarBlocksQuebradosProducir(codigoReporte);
            ingresarBlocksQuebradosSacar(codigoReporte);
            ingresarBlocksSegunda(codigoReporte);

            consulta = " SELECT block.nombre_block, block.resistencia, sum(o_item_reporte_produccion.producidos) as Producidos, sum(o_item_reporte_produccion.quebrados_producir) as AlProducir, sum(o_item_reporte_produccion.quebrados_sacar) as AlSacar , sum(o_item_reporte_produccion.segunda) as Segunda, sum(o_item_reporte_produccion.quebrados_producir) + sum(o_item_reporte_produccion.quebrados_sacar) + sum(o_item_reporte_produccion.segunda) as Perdidos, 0 as Vendidos , sum(o_item_reporte_produccion.producidos) - sum(o_item_reporte_produccion.quebrados_producir) - sum(o_item_reporte_produccion.quebrados_sacar) - sum(o_item_reporte_produccion.segunda) as Existentes"
                    + "  FROM o_item_reporte_produccion "
                    + "  INNER JOIN o_produccion_block ON o_item_reporte_produccion.codigo_reporte_produccion = o_produccion_block.codigo "
                    + "  INNER JOIN block ON o_item_reporte_produccion.codigo_block = block.codigo"
                    + "  WHERE o_produccion_block.codigo = " + codigoReporte
                    + "  GROUP BY block.codigo";

            setQueryTable();

        } else if (status.equalsIgnoreCase("reporteProducciones")) { // Detalle de Producciones

            // Primero creo el reporte de detalle de produccion luego trabaje en base a el.  
            codigoReporte = crearReporteDetalle();
            ingresarBlockReporteDetalle(codigoReporte);
            ingresarMateriaPrimaDetalleProduccion(codigoReporte);
            ingresarProduccionSueldosDetalleProduccion(codigoReporte);

            consulta = " SELECT block.nombre_block, block.resistencia, sum(o_item_reporte_produccion.producidos) as Producidos, sum(o_item_reporte_produccion.quebrados_producir) as AlProducir, sum(o_item_reporte_produccion.quebrados_sacar) as AlSacar , sum(o_item_reporte_produccion.segunda) as Segunda, sum(o_item_reporte_produccion.quebrados_producir) + sum(o_item_reporte_produccion.quebrados_sacar) + sum(o_item_reporte_produccion.segunda) as Perdidos, 0 as Vendidos , sum(o_item_reporte_produccion.producidos) - sum(o_item_reporte_produccion.quebrados_producir) - sum(o_item_reporte_produccion.quebrados_sacar) - sum(o_item_reporte_produccion.segunda) as Existentes"
                    + "  FROM o_item_reporte_produccion "
                    + "  INNER JOIN o_detalle_produccion ON o_item_reporte_produccion.codigo_reporte_detalle = o_detalle_produccion.codigo "
                    + "  INNER JOIN block ON o_item_reporte_produccion.codigo_block = block.codigo"
                    + "  WHERE o_detalle_produccion.codigo = " + codigoReporte
                    + "  GROUP BY block.codigo";

            setQueryTable();

        } else if (status.equalsIgnoreCase("reporteMateria")) {

            // Primero creo el reporte de uso de materia prima
            codigoReporte = crearReporteUsoMateria(); // Se puede usar el mismo que el de planilla ya que solo abra uno a la vez no dos. 
            ingresarMaterialEntrante(codigoReporte);
            ingresarMaterialSaliente(codigoReporte);

            consulta = "SELECT materia.nombre, materia.presentacion, coalesce(sum(o_item_reporte_uso_materia.entrante),0) as CantidadEntrante, coalesce(sum(o_item_reporte_uso_materia.saliente),0) as CantidadSaliente, coalesce(sum(o_item_reporte_uso_materia.entrante),0)  - coalesce(sum(o_item_reporte_uso_materia.saliente),0) as CantidadExistente, sum(o_item_reporte_uso_materia.precio_unitario)/sum(o_item_reporte_uso_materia.cuenta) as PrecioUnitario, coalesce(sum(o_item_reporte_uso_materia.precio_total),0) as Total"
                    + " FROM o_item_reporte_uso_materia "
                    + " INNER JOIN materia ON materia.codigo = o_item_reporte_uso_materia.codigo_material"
                    + " INNER JOIN o_reporte_uso_materia ON o_reporte_uso_materia.codigo = o_item_reporte_uso_materia.codigo_reporte_materia "
                    + " WHERE o_item_reporte_uso_materia.codigo_reporte_materia = " + codigoReporte
                    + " GROUP BY materia.codigo  ";

            setQueryTable();

        } else if (status.equalsIgnoreCase("reporteMolde")) {

            consulta = "SELECT molde.Nombre,sum(movimiento_molde.cantidad_blocks) as Cantidad_Block_Hechos, TIMESTAMPDIFF(MONTH,molde.fecha_compra,CURDATE()) as Meses_Utilizado, molde.fecha_compra as Fecha_de_Compra "
                    + "FROM molde, movimiento_molde, produccion "
                    + "WHERE produccion.codigo = movimiento_molde.codigo_produccion "
                    + "AND movimiento_molde.codigo_molde = molde.codigo "
                    + "AND produccion.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "'"
                    + "GROUP BY molde.codigo;";

            tableMain.setModel(new QueryModelTable(consulta, status));
            setColumnWidth(150);

        } else if (status.equalsIgnoreCase("reportequebrados")) {

            consulta = "select nombre_de_Block, sum(Blocks_Quebrados_al_producir) as QuebradosAlProducir, sum(Blocks_Quebrados_al_Sacar) as QuebradosAlSacar from"
                    + " Produccion Where FECHA BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' GROUP BY Tipo_de_Block";

            tableMain.setModel(new QueryModelTable(consulta, status));
            setColumnWidth(150);
        } else if (status.equalsIgnoreCase("reporteSueldos")) {

            // Primero creo el reporte de sueldos 
            codigoReporte = crearReporteSueldos();

            ingresarSueldoBase(codigoReporte); // Ingresar Sueldo Base
            ingresarProduccionSueldos(codigoReporte); // Ingresar Sueldo Acumulado de Produccion tanto como la bonificacion. 
            ingresarViajesSueldos(codigoReporte);
            ingresarHorasExtra(codigoReporte);
            ingresarDescuentos(codigoReporte);

            consulta = "SELECT personal.nombre, personal.apellido, personal.puesto, "
                    + "     COALESCE(SUM(o_item_reporte_sueldo.sueldo_base)), "
                    + "     COALESCE(SUM(o_item_reporte_sueldo.sueldo_acumulado)) , "
                    + "     COALESCE(SUM(o_item_reporte_sueldo.bonificacion_acumulado)), "
                    + "     COALESCE(SUM(o_item_reporte_sueldo.horas_extras_dinero)),  "
                    + "     COALESCE(SUM(o_item_reporte_sueldo.sueldo_base)) + COALESCE(SUM(o_item_reporte_sueldo.sueldo_acumulado)) + COALESCE(SUM(o_item_reporte_sueldo.bonificacion_acumulado)) + COALESCE(SUM(o_item_reporte_sueldo.horas_extras_dinero)) as Total_Devengado, "
                    + "     COALESCE(SUM(o_item_reporte_sueldo.descuentos)),"
                    + "     0 as IGSS,"
                    + "     COALESCE(SUM(o_item_reporte_sueldo.descuentos)) as Total_Descuentos, "
                    + "     COALESCE(SUM(o_item_reporte_sueldo.sueldo_base)) + COALESCE(SUM(o_item_reporte_sueldo.sueldo_acumulado)) + COALESCE(SUM(o_item_reporte_sueldo.bonificacion_acumulado)) + COALESCE(SUM(o_item_reporte_sueldo.horas_extras_dinero)) - COALESCE(SUM(o_item_reporte_sueldo.descuentos)) as Total_aRecibir "
                    + " FROM o_item_reporte_sueldo "
                    + " INNER JOIN o_reporte_sueldo ON o_reporte_sueldo.codigo = o_item_reporte_sueldo.codigo_reporte_sueldo"
                    + " INNER JOIN personal ON personal.codigo = o_item_reporte_sueldo.codigo_personal"
                    + " WHERE fecha_inicial LIKE '" + getCurrentDate(calendar) + "' "
                    + " AND fecha_final LIKE '" + getCurrentDate(calendar2) + "' "
                    + " AND o_item_reporte_sueldo.codigo_reporte_sueldo = " + codigoReporte
                    + " GROUP BY personal.codigo";

            setQueryTable();

        }

    }

    /*
     **********************************************************************
     REPORTE DE DETALLE DE PRODUCCION
     **********************************************************************
     */
    /*
     Se crea de primero el reporte de detall de produccion, y luego creeare los items de 
     la produccion. 
     */
    private int crearReporteDetalle() {

        int cod = -1;

        try {

            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_detalle_produccion "
                    + "(fechaReporte,fechaInicial,fechaFinal) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            java.util.Date today = new Date(); // Fecha de ahorita 
            ps.setDate(1, new java.sql.Date(today.getTime()));
            ps.setDate(2, getCurrentDate(calendar));
            ps.setDate(3, getCurrentDate(calendar2));

            resultado = ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cod = resultSet.getInt(1); // codigo de la que acaba de entrar

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte detalle de producción de block, crear reporte :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de producción", 0);
            cod = -1;
        }

        return cod;
    }

    /*
     **********************************************************************
     REPORTE DE DETALLE DE PRODUCCION / PRODUCCION
     **********************************************************************
     */
    private void ingresarBlockReporteDetalle(int codigo) {
        ingresarBlocksProducidosDetalle(codigo);
        ingresarBlocksQuebradosProducirDetalle(codigo);
        ingresarBlocksQuebradosSacarDetalle(codigo);
        ingresarBlocksSegundaDetalle(codigo);
    }

    private void ingresarBlocksProducidosDetalle(int codigoReporteProduccion) {
        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT block.codigo, movimiento_block.cantidad as cantidad "
                    + "                  FROM movimiento_block"
                    + "                  INNER JOIN block ON movimiento_block.codigo_block = block.codigo "
                    + "                  INNER JOIN produccion ON movimiento_block.codigo_produccion = produccion.codigo"
                    + "                  WHERE movimiento_block.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' "
                    + "                  AND movimiento_block.codigo_ventas IS NULL "
                    + "                  AND movimiento_block.codigo_perdidas IS NULL");

            // Lo hare por cada movimiento_block que sea de entrada. 
            while (resultSet.next()) {
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_produccion "
                            + "(codigo_reporte_detalle,codigo_block,producidos) values (?,?,?)");
                    ps.setInt(1, codigoReporteProduccion);
                    ps.setInt(2, resultSet.getInt(1));  // Codigo Block 
                    ps.setDouble(3, resultSet.getDouble(2)); // Cantidad 

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte detalle de producción block , ingreso de blocks producidos  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de producción block ", 0);

                }
            }  // Fin de todos los movimientos de materia prima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte detalle de producción block , ingreso de blocks producidos  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de producción block ", 0);
        }

    }

    private void ingresarBlocksQuebradosProducirDetalle(int codigoReporteProduccion) {
        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT block.codigo, movimiento_block.cantidad as cantidad "
                    + "                  FROM movimiento_block"
                    + "                  INNER JOIN block ON movimiento_block.codigo_block = block.codigo "
                    + "                  INNER JOIN produccion ON movimiento_block.codigo_produccion = produccion.codigo"
                    + "                  WHERE movimiento_block.codigo_perdidas = 1" // Quebrados al producir 
                    + "                  AND movimiento_block.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' ");

            // Lo hare por cada movimiento_material que sea de entrada. 
            while (resultSet.next()) {
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_produccion "
                            + "(codigo_reporte_detalle,codigo_block,Quebrados_Producir) values (?,?,?)");
                    ps.setInt(1, codigoReporteProduccion);
                    ps.setInt(2, resultSet.getInt(1));  // Codigo Block 
                    ps.setDouble(3, resultSet.getDouble(2)); // Cantidad 

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte detalle de producción block , ingreso de quebrados al producir  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de producción block ", 0);

                }
            }  // Fin de todos los movimientos de materia prima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte detalle de producción block , ingreso de quebrados al producir  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de producción block ", 0);
        }

    }

    private void ingresarBlocksQuebradosSacarDetalle(int codigoReporteProduccion) {
        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT block.codigo, movimiento_block.cantidad as cantidad "
                    + "                  FROM movimiento_block"
                    + "                  INNER JOIN block ON movimiento_block.codigo_block = block.codigo "
                    + "                  INNER JOIN produccion ON movimiento_block.codigo_produccion = produccion.codigo"
                    + "                  WHERE movimiento_block.codigo_perdidas = 2" // Quebrados al sacar 
                    + "                  AND movimiento_block.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' ");

            // Lo hare por cada movimiento_material que sea de entrada. 
            while (resultSet.next()) {
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_produccion "
                            + "(codigo_reporte_detalle,codigo_block,Quebrados_Sacar) values (?,?,?)");
                    ps.setInt(1, codigoReporteProduccion);
                    ps.setInt(2, resultSet.getInt(1));  // Codigo Block 
                    ps.setDouble(3, resultSet.getDouble(2)); // Cantidad 

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte detalle de producción block , ingreso de quebrados al sacar  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de producción block ", 0);

                }
            }  // Fin de todos los movimientos de materia prima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte detalle de producción block , ingreso de quebrados al sacar  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de producción block ", 0);
        }

    }

    private void ingresarBlocksSegundaDetalle(int codigoReporteProduccion) {
        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT block.codigo, movimiento_block.cantidad as cantidad "
                    + "                  FROM movimiento_block"
                    + "                  INNER JOIN block ON movimiento_block.codigo_block = block.codigo "
                    + "                  INNER JOIN produccion ON movimiento_block.codigo_produccion = produccion.codigo"
                    + "                  WHERE movimiento_block.codigo_perdidas = 3" // Blocks de Segunda  
                    + "                  AND movimiento_block.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' ");

            // Lo hare por cada movimiento_material que sea de entrada. 
            while (resultSet.next()) {
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_produccion "
                            + "(codigo_reporte_detalle,codigo_block,Segunda) values (?,?,?)");
                    ps.setInt(1, codigoReporteProduccion);
                    ps.setInt(2, resultSet.getInt(1));  // Codigo Block 
                    ps.setDouble(3, resultSet.getDouble(2)); // Cantidad 

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte detalle de producción block , ingreso de blocks de segunda  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de producción block ", 0);

                }
            }  // Fin de todos los movimientos de materia prima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte detalle de producción block , ingreso de blocks de segunda  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de producción block ", 0);

        }

    }

    /*
     **********************************************************************
     REPORTE DE PRODUCCION
     **********************************************************************
     */
    /*
     Se crea de primero el reporte de produccion, y luego creeare los items de 
     la produccion. 
     */
    private int crearReporteProduccion() {

        int cod = -1;

        try {

            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_produccion_block "
                    + "(fecha_reporte,fecha_inicial,fecha_final) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            java.util.Date today = new Date(); // Fecha de ahorita 
            ps.setDate(1, new java.sql.Date(today.getTime()));
            ps.setDate(2, getCurrentDate(calendar));
            ps.setDate(3, getCurrentDate(calendar2));

            resultado = ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cod = resultSet.getInt(1); // codigo de la que acaba de entrar

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte producción de block, crear reporte :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte producción", 0);
            cod = -1;
        }

        return cod;
    }

    private void ingresarBlocksProducidos(int codigoReporteProduccion) {
        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT block.codigo, movimiento_block.cantidad as cantidad "
                    + "                  FROM movimiento_block"
                    + "                  INNER JOIN block ON movimiento_block.codigo_block = block.codigo "
                    + "                  INNER JOIN produccion ON movimiento_block.codigo_produccion = produccion.codigo"
                    + "                  WHERE movimiento_block.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' "
                    + "                  AND movimiento_block.codigo_ventas IS NULL "
                    + "                  AND movimiento_block.codigo_perdidas IS NULL");

            // Lo hare por cada movimiento_material que sea de entrada. 
            while (resultSet.next()) {
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_produccion "
                            + "(codigo_reporte_produccion,codigo_block,producidos) values (?,?,?)");
                    ps.setInt(1, codigoReporteProduccion);
                    ps.setInt(2, resultSet.getInt(1));  // Codigo Block 
                    ps.setDouble(3, resultSet.getDouble(2)); // Cantidad 

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte producción block , ingreso de blocks producidos  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte producción block ", 0);

                }
            }  // Fin de todos los movimientos de materia prima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte producción block  , error en sql en ingreso de blocks producidos  <br/>" + ex + " </p></pre></body></html>", "Creacion de reporte producción block  ", 0);
        }

    }

    private void ingresarBlocksQuebradosProducir(int codigoReporteProduccion) {
        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT block.codigo, movimiento_block.cantidad as cantidad "
                    + "                  FROM movimiento_block"
                    + "                  INNER JOIN block ON movimiento_block.codigo_block = block.codigo "
                    + "                  INNER JOIN produccion ON movimiento_block.codigo_produccion = produccion.codigo"
                    + "                  WHERE movimiento_block.codigo_perdidas = 1" // Quebrados al producir 
                    + "                  AND movimiento_block.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' ");

            // Lo hare por cada movimiento_material que sea de entrada. 
            while (resultSet.next()) {
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_produccion "
                            + "(codigo_reporte_produccion,codigo_block,Quebrados_Producir) values (?,?,?)");
                    ps.setInt(1, codigoReporteProduccion);
                    ps.setInt(2, resultSet.getInt(1));  // Codigo Block 
                    ps.setDouble(3, resultSet.getDouble(2)); // Cantidad 

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte producción block , ingreso de quebrados al producir  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte producción block ", 0);

                }
            }  // Fin de todos los movimientos de materia prima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte producción block  , error en sql en ingreso de quebrados al producir <br/>" + ex + " </p></pre></body></html>", "Creacion de reporte producción block  ", 0);
        }

    }

    private void ingresarBlocksQuebradosSacar(int codigoReporteProduccion) {
        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT block.codigo, movimiento_block.cantidad as cantidad "
                    + "                  FROM movimiento_block"
                    + "                  INNER JOIN block ON movimiento_block.codigo_block = block.codigo "
                    + "                  INNER JOIN produccion ON movimiento_block.codigo_produccion = produccion.codigo"
                    + "                  WHERE movimiento_block.codigo_perdidas = 2" // Quebrados al sacar 
                    + "                  AND movimiento_block.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' ");

            // Lo hare por cada movimiento_material que sea de entrada. 
            while (resultSet.next()) {
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_produccion "
                            + "(codigo_reporte_produccion,codigo_block,Quebrados_Sacar) values (?,?,?)");
                    ps.setInt(1, codigoReporteProduccion);
                    ps.setInt(2, resultSet.getInt(1));  // Codigo Block 
                    ps.setDouble(3, resultSet.getDouble(2)); // Cantidad 

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte producción block , ingreso de quebrados al sacar  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte producción block ", 0);

                }
            }  // Fin de todos los movimientos de materia prima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte producción block  , error en sql en ingreso de quebrados al sacar <br/>" + ex + " </p></pre></body></html>", "Creacion de reporte producción block  ", 0);
        }

    }

    private void ingresarBlocksSegunda(int codigoReporteProduccion) {
        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT block.codigo, movimiento_block.cantidad as cantidad "
                    + "                  FROM movimiento_block"
                    + "                  INNER JOIN block ON movimiento_block.codigo_block = block.codigo "
                    + "                  INNER JOIN produccion ON movimiento_block.codigo_produccion = produccion.codigo"
                    + "                  WHERE movimiento_block.codigo_perdidas = 3" // Blocks de Segunda  
                    + "                  AND movimiento_block.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' ");

            // Lo hare por cada movimiento_material que sea de entrada. 
            while (resultSet.next()) {
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_produccion "
                            + "(codigo_reporte_produccion,codigo_block,Segunda) values (?,?,?)");
                    ps.setInt(1, codigoReporteProduccion);
                    ps.setInt(2, resultSet.getInt(1));  // Codigo Block 
                    ps.setDouble(3, resultSet.getDouble(2)); // Cantidad 

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte producción block , ingreso de blocks de segunda  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte producción block ", 0);

                }
            }  // Fin de todos los movimientos de materia prima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte producción block  , error en sql en blocks de segunda <br/>" + ex + " </p></pre></body></html>", "Creacion de reporte producción block  ", 0);
        }

    }

    /*
     **********************************************************************
     REPORTE DE DETALLE DE PRODUCCION DE USO DE MATERIA PRIMA
     **********************************************************************
     */
    /*
     Se crea de primero el reporte de uso de materia prima en produccion 
     y se trabaja en base a eso. 
     */
    private void ingresarMateriaPrimaDetalleProduccion(int codigo) {

        ingresarMaterialSalienteDetalleProduccion(codigo);

    }

    private void ingresarMaterialSalienteDetalleProduccion(int codigoReporteUsoMateria) {

        try {

            st = (Statement) connection.createStatement();

            resultSet = st.executeQuery("SELECT materia.codigo, movimiento_material.cantidad as cantidad, movimiento_material.precio_unitario, movimiento_material.total, block.codigo"
                    + "                  FROM  block, movimiento_block, movimiento_material"
                    + "                  INNER JOIN viaje ON movimiento_material.codigo_material_entrante = viaje.codigo "
                    + "                  INNER JOIN materia ON materia.codigo = movimiento_material.codigo_material"
                    + "                  INNER JOIN produccion ON produccion.codigo = movimiento_material.codigo_produccion"
                    + "                  WHERE movimiento_material.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' "
                    + "                  AND movimiento_material.existencia = 2"
                    + "                  AND produccion.codigo = movimiento_block.codigo_produccion"
                    + "                  AND block.codigo= movimiento_block.codigo_block" // Inica que es solo los que se usaron en la produccion.  
                    + "                  GROUP BY movimiento_material.codigo");

            // Lo hare por cada movimiento_material que sea de entrada. 
            while (resultSet.next()) {
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_uso_materia "
                            + "(codigo_reporte_detalle,codigo_material,saliente,precio_unitario,precio_total, codigo_block) values (?,?,?,?,?,?)");

                    ps.setInt(1, codigoReporteUsoMateria);
                    ps.setInt(2, resultSet.getInt(1));
                    ps.setDouble(3, resultSet.getDouble(2));
                    ps.setDouble(4, resultSet.getDouble(3));
                    ps.setDouble(5, resultSet.getDouble(4));
                    ps.setInt(6, resultSet.getInt(5)); // Codigo Block 
                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear  reporte detalle de produccion en uso materia prima , ingreso de material saliente  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de produccion en uso materia prima ", 0);

                }
            }  // Fin de todos los movimientos de materia prima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear  reporte detalle de produccion en uso materia prima , ingreso de material saliente  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de produccion en uso materia prima ", 0);
        }

    }  // Fin de Material Saliente Detalle de Produccion 

    /*
     **********************************************************************
     REPORTE DE USO DE MATERIA PRIMA
     **********************************************************************
     */
    /*
     Se crea de primero el reporte de uso de materia prima en produccion 
     y se trabaja en base a eso. 
     */
    private int crearReporteUsoMateria() {

        int cod = -1;

        try {

            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_reporte_uso_materia "
                    + "(fecha_reporte,fecha_inicial,fecha_final) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            java.util.Date today = new Date(); // Fecha de ahorita 
            ps.setDate(1, new java.sql.Date(today.getTime()));
            ps.setDate(2, getCurrentDate(calendar));
            ps.setDate(3, getCurrentDate(calendar2));

            resultado = ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cod = resultSet.getInt(1); // codigo de la que acaba de entrar

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte uso materia prima, crear reporte :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte uso materia prima", 0);
            cod = -1;
        }

        return cod;
    }

    private void ingresarMaterialEntrante(int codigoReporteUsoMateria) {

        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT materia.codigo, movimiento_material.cantidad as cantidad, movimiento_material.precio_unitario, movimiento_material.total"
                    + "                  FROM movimiento_material"
                    + "                  INNER JOIN viaje ON movimiento_material.codigo_material_entrante = viaje.codigo "
                    + "                  INNER JOIN materia ON materia.codigo = movimiento_material.codigo_material"
                    + "                  WHERE movimiento_material.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' "
                    + "                  AND movimiento_material.existencia = 1" // Inica que es solo los de material de ingreso. 
                    + "                  GROUP BY movimiento_material.codigo");

            // Lo hare por cada movimiento_material que sea de entrada. 
            while (resultSet.next()) {
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_uso_materia "
                            + "(codigo_reporte_materia,codigo_material,entrante,precio_unitario,precio_total,cuenta) values (?,?,?,?,?,?)");
                    ps.setInt(1, codigoReporteUsoMateria);
                    ps.setInt(2, resultSet.getInt(1));
                    ps.setDouble(3, resultSet.getDouble(2));
                    ps.setDouble(4, resultSet.getDouble(3));
                    ps.setDouble(5, resultSet.getDouble(4));
                    ps.setInt(6, 1);

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte uso materia prima , ingreso de material entrante  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte uso materia prima ", 0);

                }
            }  // Fin de todos los movimientos de materia prima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte uso materia prima  , error en sql en material entrante  <br/>" + ex + " </p></pre></body></html>", "Creacion de reporte uso materia prima  ", 0);
        }

    }

    private void ingresarMaterialSaliente(int codigoReporteUsoMateria) {

        try {

            st = (Statement) connection.createStatement();
            resultSet = st.executeQuery("SELECT materia.codigo, movimiento_material.cantidad as cantidad, movimiento_material.precio_unitario, movimiento_material.total"
                    + "                  FROM movimiento_material"
                    + "                  INNER JOIN viaje ON movimiento_material.codigo_material_entrante = viaje.codigo "
                    + "                  INNER JOIN materia ON materia.codigo = movimiento_material.codigo_material"
                    + "                  INNER JOIN produccion ON produccion.codigo = movimiento_material.codigo_produccion"
                    + "                  WHERE movimiento_material.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "' "
                    + "                  AND movimiento_material.existencia = 2" // Inica que es solo los que se usaron en la produccion.  
                    + "                  GROUP BY movimiento_material.codigo");

            // Lo hare por cada movimiento_material que sea de entrada. 
            while (resultSet.next()) {
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_uso_materia "
                            + "(codigo_reporte_materia,codigo_material,saliente,precio_unitario,precio_total) values (?,?,?,?,?)");

                    ps.setInt(1, codigoReporteUsoMateria);
                    ps.setInt(2, resultSet.getInt(1));
                    ps.setDouble(3, resultSet.getDouble(2));
                    ps.setDouble(4, 0); // Si lo pongo se duplica. 
                    ps.setDouble(5, 0); // Si lo pongo se duplica. con el metodo de entrante por que se hace un promedio 

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte uso materia prima , ingreso de material saliente  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte uso materia prima ", 0);

                }
            }  // Fin de todos los movimientos de materia prima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte uso materia prima  , error en sql en material saliente  <br/>" + ex + " </p></pre></body></html>", "Creacion de reporte uso materia prima  ", 0);
        }

    }

    /*
     **********************************************************************
     REPORTE DE DETALLE DE PRODUCCION DE SUELDO TRABAJADORES
     **********************************************************************
     */
    private void ingresarProduccionSueldosDetalleProduccion(int codigoReporteSueldo) {

        ResultSet rs = null;

        try {

            // Aqui no es un prepared Statement porque solo es uno
            st = (Statement) connection.createStatement();
            
            rs = st.executeQuery("SELECT movimiento_personal.codigo, block.codigo "
                    + " FROM block, movimiento_block, movimiento_personal "
                    + " INNER JOIN personal ON personal.codigo = movimiento_personal.codigo_personal "
                    + " INNER JOIN produccion ON produccion.codigo = codigo_produccion "
                    + " WHERE movimiento_personal.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "'"
                    + " AND movimiento_block.codigo_block = block.codigo "
                    + " AND movimiento_block.codigo_produccion = produccion.codigo "
                    + " GROUP BY movimiento_personal.codigo");
            
            // Lo hare por cada movimiento personal que este entre estas fechas y que ademas, 
            // Sean extrictamente relacionadas a produccion y a un personal. 
            while (rs.next()) {
                int codigoMovPersonal = rs.getInt(1); // Obtengo el codigo 
                Object[] data = new DataBaseClass().giveData(" SELECT codigo_personal, cantidad_block, sueldo_en_q, bonificacion_en_q FROM movimiento_personal WHERE codigo = " + codigoMovPersonal);
                try {

                    
                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_sueldo "
                            + "(codigo_reporte_detalle,codigo_personal,sueldo_acumulado, bonificacion_acumulado, codigo_block, cantidad_block, tarifa_sueldo, tarifa_bonificacion) values (?,?,?,?,?,?,?,?)");

                    ps.setInt(1, codigoReporteSueldo);
                    ps.setInt(2, Integer.parseInt(data[0].toString()));
                    ps.setDouble(3, Double.parseDouble(data[1].toString()) * Double.parseDouble(data[2].toString()));
                    ps.setDouble(4, Double.parseDouble(data[1].toString()) * Double.parseDouble(data[3].toString()));
                    ps.setInt(5, rs.getInt(2));
                    ps.setDouble(6, Double.parseDouble(data[1].toString()));
                    ps.setDouble(7, Double.parseDouble(data[2].toString()));
                    ps.setDouble(8, Double.parseDouble(data[3].toString()));

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte detalle de produccion planilla, ingreso de sueldo acumulado produccion :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de produccion planilla", 0);

                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte detalle de produccion planilla, ingreso de sueldo acumulado produccion :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte detalle de produccion planilla", 0);
        }

    }

    /*
     **********************************************************************
     REPORTE DE SUELDOS DE EMPLEADO (PLANILLA)
     **********************************************************************
     */
    /*
     En este momento creare el OPAL, para el reporte de sueldos, para que funciones 
     lo siguiente tratara de crear un reporte en la tabla o_reporte_sueldos, y luego 
     se van metiendo los items y luego se hace una suma de todo agrupando por personal 
     y conciderando el codigo del reporte recien guardado. ! 
     */
    private int crearReporteSueldos() {

        int cod = -1;

        try {

            int resultado = 0;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_reporte_sueldo "
                    + "(fecha_reporte,fecha_inicial,fecha_final) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            java.util.Date today = new Date(); // Fecha de ahorita 
            ps.setDate(1, new java.sql.Date(today.getTime()));
            ps.setDate(2, getCurrentDate(calendar));
            ps.setDate(3, getCurrentDate(calendar2));

            resultado = ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cod = resultSet.getInt(1); // codigo de la que acaba de entrar

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte planilla, crear reporte :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte planilla", 0);
            cod = -1;
        }

        return cod;
    }

    private void ingresarSueldoBase(int codigoReporteSueldo) {

        ResultSet rs = null;

        try {
            // Aqui no es un prepared Statement porque solo es uno
            st = (Statement) connection.createStatement();
            rs = st.executeQuery("SELECT codigo, sueldo_base FROM personal ;");

            // Lo hare por cada movimiento personal que este entre estas fechas y que ademas, 
            // Sean extrictamente relacionadas a produccion y a un personal. 
            while (rs.next()) {
                int resultado = 0;

                PreparedStatement ps = null;
                ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_sueldo "
                        + "(codigo_reporte_sueldo,codigo_personal,sueldo_base) values (?,?,?)");

                ps.setInt(1, codigoReporteSueldo);
                ps.setInt(2, rs.getInt(1));
                ps.setDouble(3, rs.getDouble(2));

                resultado = ps.executeUpdate();

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte planilla, ingreso de sueldo base :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte planilla", 0);

        }

    }

    private void ingresarProduccionSueldos(int codigoReporteSueldo) {

        ResultSet rs = null;

        try {

            // Aqui no es un prepared Statement porque solo es uno
            st = (Statement) connection.createStatement();
            rs = st.executeQuery("SELECT movimiento_personal.codigo FROM movimiento_personal INNER JOIN personal ON personal.codigo = movimiento_personal.codigo_personal INNER JOIN produccion ON produccion.codigo = codigo_produccion WHERE " + "  movimiento_personal.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "'");

            // Lo hare por cada movimiento personal que este entre estas fechas y que ademas, 
            // Sean extrictamente relacionadas a produccion y a un personal. 
            while (rs.next()) {
                int codigoMovPersonal = rs.getInt(1); // Obtengo el codigo 
                Object[] data = new DataBaseClass().giveData("SELECT codigo_personal, cantidad_block, sueldo_en_q, bonificacion_en_q FROM movimiento_personal WHERE codigo = " + codigoMovPersonal);
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_sueldo "
                            + "(codigo_reporte_sueldo,codigo_personal,sueldo_acumulado, bonificacion_acumulado) values (?,?,?,?)");

                    ps.setInt(1, codigoReporteSueldo);
                    ps.setInt(2, Integer.parseInt(data[0].toString()));
                    ps.setDouble(3, Double.parseDouble(data[1].toString()) * Double.parseDouble(data[2].toString()));
                    ps.setDouble(4, Double.parseDouble(data[1].toString()) * Double.parseDouble(data[3].toString()));

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte planilla, ingreso de sueldo acumulado produccion :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte planilla", 0);

                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte en planilla , error en sql sueldo produccion  <br/>" + ex + " </p></pre></body></html>", "Creacion de reporte planilla ", 0);
        }

    }

    private void ingresarViajesSueldos(int codigoReporteSueldo) {

        ResultSet rs = null;

        try {

            // Aqui no es un prepared Statement porque solo es uno
            st = (Statement) connection.createStatement();
            rs = st.executeQuery("SELECT movimiento_personal.codigo FROM movimiento_personal INNER JOIN personal ON personal.codigo = movimiento_personal.codigo_personal INNER JOIN viaje ON viaje.codigo = codigo_viaje WHERE " + "  movimiento_personal.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "'");

            // Lo hare por cada movimiento personal que este entre estas fechas y que ademas, 
            // Sean extrictamente relacionadas a produccion y a un personal. 
            while (rs.next()) {
                int codigoMovPersonal = rs.getInt(1); // Obtengo el codigo 
                Object[] data = new DataBaseClass().giveData("SELECT codigo_personal, total_en_q FROM movimiento_personal WHERE codigo = " + codigoMovPersonal);
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_sueldo "
                            + "(codigo_reporte_sueldo,codigo_personal,sueldo_acumulado) values (?,?,?)");

                    ps.setInt(1, codigoReporteSueldo);
                    ps.setInt(2, Integer.parseInt(data[0].toString()));
                    ps.setDouble(3, Double.parseDouble(data[1].toString()));

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte planilla, ingreso de sueldo viajes :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte planilla", 0);

                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte en planilla , error en sql de sueldo viajes   <br/>" + ex + " </p></pre></body></html>", "Creacion de reporte planilla ", 0);
        }

    }

    private void ingresarHorasExtra(int codigoReporteSueldo) {

        ResultSet rs = null;

        try {

            // Aqui no es un prepared Statement porque solo es uno
            st = (Statement) connection.createStatement();
            rs = st.executeQuery("SELECT movimiento_personal.codigo FROM movimiento_personal INNER JOIN personal ON personal.codigo = movimiento_personal.codigo_personal INNER JOIN extras_personal ON extras_personal.codigo = codigo_horas_extra WHERE " + "  movimiento_personal.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "'");

            // Lo hare por cada movimiento personal que este entre estas fechas y que ademas, 
            // Sean extrictamente relacionadas a produccion y a un personal. 
            while (rs.next()) {
                int codigoMovPersonal = rs.getInt(1); // Obtengo el codigo 
                Object[] data = new DataBaseClass().giveData("SELECT codigo_personal, total_en_q FROM movimiento_personal WHERE codigo = " + codigoMovPersonal);
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_sueldo "
                            + "(codigo_reporte_sueldo,codigo_personal,horas_extras_dinero) values (?,?,?)");

                    ps.setInt(1, codigoReporteSueldo);
                    ps.setInt(2, Integer.parseInt(data[0].toString()));
                    ps.setDouble(3, Double.parseDouble(data[1].toString()));

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte planilla, ingreso de horas extra  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte planilla", 0);

                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte en planilla , error en sql en horas extra <br/>" + ex + " </p></pre></body></html>", "Creacion de reporte planilla ", 0);
        }

    }

    private void ingresarDescuentos(int codigoReporteSueldo) {

        ResultSet rs = null;

        try {

            // Aqui no es un prepared Statement porque solo es uno
            st = (Statement) connection.createStatement();
            rs = st.executeQuery("SELECT movimiento_personal.codigo FROM movimiento_personal INNER JOIN personal ON personal.codigo = movimiento_personal.codigo_personal INNER JOIN descuento_personal ON descuento_personal.codigo = codigo_descuentos WHERE " + "  movimiento_personal.fecha BETWEEN '" + getCurrentDate(calendar) + "' AND '" + getCurrentDate(calendar2) + "'");

            // Lo hare por cada movimiento personal que este entre estas fechas y que ademas, 
            // Sean extrictamente relacionadas a produccion y a un personal. 
            while (rs.next()) {
                int codigoMovPersonal = rs.getInt(1); // Obtengo el codigo 
                Object[] data = new DataBaseClass().giveData("SELECT codigo_personal, total_en_q FROM movimiento_personal WHERE codigo = " + codigoMovPersonal);
                try {

                    int resultado = 0;

                    PreparedStatement ps = null;
                    ps = (PreparedStatement) connection.prepareStatement("INSERT INTO  o_item_reporte_sueldo "
                            + "(codigo_reporte_sueldo,codigo_personal,descuentos) values (?,?,?)");

                    ps.setInt(1, codigoReporteSueldo);
                    ps.setInt(2, Integer.parseInt(data[0].toString()));
                    ps.setDouble(3, Double.parseDouble(data[1].toString()));

                    resultado = ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte planilla, ingreso de descuentos  :  <br/>" + ex + " </p></pre></body></html>", "Error al crear reporte planilla", 0);

                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al crear reporte en planilla , error en sql de descuentos  <br/>" + ex + " </p></pre></body></html>", "Creacion de reporte planilla ", 0);
        }

    }


    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        status = "reporteProduccion";
        configurarParaMostrar();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseReleased
        if (evt.isPopupTrigger()) {

            reportmenu.show(jButton4, evt.getX(), evt.getY());
        }

    }//GEN-LAST:event_jButton4MouseReleased

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        status = "reporteProducciones";
        configurarParaMostrar();


    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        status = "Molde";
        configurarParaMostrar();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void tableMainMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMainMouseMoved

    }//GEN-LAST:event_tableMainMouseMoved

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        status = "reporteMateria";
        configurarParaMostrar();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        status = "reporteMolde";
        configurarParaMostrar();

    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    /*
     * Este metodo hace posible que desde cualquier clase
     * podamos cambiar el valor de la tabla ya que directamente 
     * a la tabla no podemos acceder. 
     */

    public static void refreshTable(String sts) {
        // Load Data of tables 
        tableMain.setModel(new TablesModel(sts));
        setColumnWidth();
    }
    private void calendar2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendar2PropertyChange
        calendarPropertyChange(evt);

    }//GEN-LAST:event_calendar2PropertyChange

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked

    }//GEN-LAST:event_jButton4MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        status = "reporteSueldos";
        configurarParaMostrar();

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        status = "HorasExtras";
        configurarParaMostrar();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        status = "Descuentos";
        configurarParaMostrar();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void bexcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexcActionPerformed

        // la fecha inicial y final                         
        ToExcel arch = new ToExcel();

        if (status.equalsIgnoreCase("reporteSueldos")) {
            String[] dataPlanilla = new String[]{getCurrentDate(calendar).toString(), getCurrentDate(calendar2).toString(), String.valueOf(codigoReporte)};
            arch.con3Datos(dataPlanilla, status);

        } else if (status.equalsIgnoreCase("reporteProduccion")) {
            String[] dataProduccionBlock = new String[]{getCurrentDate(calendar).toString(), getCurrentDate(calendar2).toString(), String.valueOf(codigoReporte)};
            arch.con3Datos(dataProduccionBlock, status);

        } else if (status.equalsIgnoreCase("reporteMateria")) {
            String[] dataUsoMateria = new String[]{getCurrentDate(calendar).toString(), getCurrentDate(calendar2).toString(), String.valueOf(codigoReporte)};
            arch.con3Datos(dataUsoMateria, status);

        } else if (status.equalsIgnoreCase("costoBlock")) {
            // Primero muestro la ventana de gastos,
            //OtrosGastos gas = new OtrosGastos(arrayFecha,status);
            // gas.setLocationRelativeTo(null); // centro la pantalla 
            //gas.setVisible(true);

        } else if (status.equalsIgnoreCase("reporteMolde")) {
            String[] dataReporteMolde = new String[]{getCurrentDate(calendar).toString(), getCurrentDate(calendar2).toString()};
            arch.con2Datos(dataReporteMolde, status);

        } else if (status.equalsIgnoreCase("reporteProducciones")) {

            String[] dataDetalleProduccion = new String[]{getCurrentDate(calendar).toString(), getCurrentDate(calendar2).toString(), String.valueOf(codigoReporte)};
            arch.con3Datos(dataDetalleProduccion, status);
        }


    }//GEN-LAST:event_bexcActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        configurarParaMostrar();

        bnew.setVisible(false);

        status = "ConsultaGastoMolde";
        consulta = "Select * from gasto_empresa";

        mistakeConsult = "Delete from gasto_molde where codigo = ";
        erase = true;

        // reconfiguro todo
        //Index.tableMain.setModel(new QueryModelTable( Index.consulta,"normal" ) );
        Index.label.setText("Consulta Ingreso Gastos Moldes");
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jButton8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MousePressed
        jButton8MouseReleased(evt);
    }//GEN-LAST:event_jButton8MousePressed

    private void jButton8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseReleased
        if (evt.isPopupTrigger()) {

            gastos.show(jButton8, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jButton8MouseReleased
    /*Metodo cuando el venga a configuraciones*/

    public void reDrawFrame(String[] datos) {

        setProgramIcon();
        nameInstitucion.setText(datos[8]);
        labPanel.setBackground(ConfigClass.getColorApp());
        jPanel3.setBackground(ConfigClass.getColorApp());

        repaint();
    }

    /* Metodo para que se configure el icono del logo de la empresa */
    private void setProgramIcon() {

        Object data[] = new DataBaseClass().giveData("SELECT logo FROM configuracion where idConfiguracion = 1 ;");
        // Foto
        byte[] bits = null;
        bits = (byte[]) data[0];

        ImageIcon imagef;

        if (bits != null) {
            imagef = new ImageIcon(bits);

            // creare una condicion cuando la imagen sea demasiado grande
            if (imagef.getIconWidth() > 400 || imagef.getIconHeight() > 286) {
                ImageIcon imaescala = new ImageIcon(imagef.getImage().getScaledInstance(512, 366, Image.SCALE_AREA_AVERAGING));
                programIcon.setIcon(imaescala);
            } else {
                programIcon.setIcon(imagef);
            }
        } else {
            programIcon.setIcon(null);
        }

    }
    private void VehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VehiculosActionPerformed
        status = "Vehiculo";
        configurarParaMostrar();

    }//GEN-LAST:event_VehiculosActionPerformed

    private void jButton7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseReleased
        if (evt.isPopupTrigger()) {

            carsMenu.show(jButton7, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jButton7MouseReleased

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        status = "Ingreso de Material";
        configurarParaMostrar();


    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        status = "Proveedor";
        configurarParaMostrar();
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jButton9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9MouseReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void bformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bformActionPerformed
        String cols = null;
        for (int j = 0; j < tableMain.getColumnCount(); j++) {
            cols += "\nelse if(col==" + j + ")\n\treturn  " + tableMain.getColumnModel().getColumn(j).getPreferredWidth() + ";";
        }
        cols += "\nelse \n\treturn 0;";
        System.out.println(cols);
    }//GEN-LAST:event_bformActionPerformed

    private void jButton7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MousePressed
        jButton7MouseReleased(evt);
    }//GEN-LAST:event_jButton7MousePressed

    private void bconfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bconfigActionPerformed
        //ConfigView c = new ConfigView(ConfigClass.configReader(), this, able(6)); // le mando si es posible modificar configuraciones

        ConfigView c = new ConfigView(ConfigClass.configReader(), this); // le mando si es posible modificar configuraciones
        c.setLocationRelativeTo(null);
        c.setVisible(true);
        this.repaint();
    }//GEN-LAST:event_bconfigActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        status = "Ingreso de Otros Gastos";
        configurarParaMostrar();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Index().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Vehiculos;
    private javax.swing.JButton bconfig;
    private javax.swing.JButton bexc;
    private javax.swing.JButton bform;
    private javax.swing.JButton bnew;
    public static com.toedter.calendar.JDateChooser calendar;
    public static com.toedter.calendar.JDateChooser calendar2;
    private javax.swing.JPopupMenu carsMenu;
    private javax.swing.JPopupMenu gastos;
    private javax.swing.JPopupMenu inmenu;
    public static javax.swing.JInternalFrame internalFrame;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel labPanel;
    public static javax.swing.JLabel label;
    private javax.swing.JLabel nameInstitucion;
    private javax.swing.JPopupMenu productsmenu;
    private javax.swing.JLabel programIcon;
    private javax.swing.JPopupMenu recursosmenu;
    private javax.swing.JPopupMenu reportmenu;
    private static javax.swing.JTable tableMain;
    private javax.swing.JLabel trash;
    // End of variables declaration//GEN-END:variables
}
