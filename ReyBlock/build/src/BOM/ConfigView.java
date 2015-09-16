/**
 * ****************************************************************************
 * VirtualLibrary.java by Giovanni Rojas Mazariegos and Javier Alay
 * geovaroma@gmail.com javier.alay@gmail.com
 * 
* View of configuration .
 * ****************************************************************************
 */
package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author giovanni rojas
 */
public class ConfigView extends javax.swing.JFrame {

    private Color color;
    private Index indexview;
    private String iconPath;
    private boolean nuevo;

    public ConfigView() {
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");
        
        String[] datos = ConfigClass.configReader();
        initComponents();
        color = new Color(255, 255, 255);
        nuevo = true; // Indica que despues de configurar 

        try {
            if (ConfigClass.fileExists()) {
            // Se indexview a mandar a MasterMainView

                jPanel3.setBackground(ConfigClass.getColorApp());
                computadora.setText(datos[0]);
                usuario.setText(datos[1]);
                password.setText(datos[2]);
                baseDatos.setText(datos[3]);
                colorTema.setColor(ConfigClass.getColorApp());
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error en configuraciones, vuelva a configurarlo \n" + e, "Error al Cargar Configuraciones", 0);

        }

        // El panel del backup solo lo tendra el localhost es decir el servidor 
        if (!"localhost".equals(datos[0])) {
            panels.remove(backupPanel);
        }
        
        // Icono de la empresa 
        setProgramIcon();

    }

    // Voy a tener dos constructores, el 
    //siguiente de abajo es cuando exista un color configurado
    public ConfigView(String[] datos, Index mmv) {
        //Set the icon of the app. 
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icon.png")));
        this.setTitle("B.O.M.");
        
        nuevo = false; // Ya esta configurado las configuraciones
        indexview = mmv;
        initComponents();
        int Red = Integer.parseInt(datos[4]);
        int Green = Integer.parseInt(datos[5]);
        int Blue = Integer.parseInt(datos[6]);
        Color bgColor = new Color(Red, Green, Blue);
        jPanel3.setBackground(bgColor);
        computadora.setText(datos[0]);
        usuario.setText(datos[1]);
        password.setText(datos[2]);
        baseDatos.setText(datos[3]);
        colorTema.setColor(bgColor);
        
        nombreInstitucion.setText(datos[8]);

        // El panel del backup solo lo tendra el localhost es decir el servidor 
        if (!"localhost".equals(datos[0])) {
            panels.remove(backupPanel);
        }

        // Icono de la empresa 
        setProgramIcon();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panels = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        computadora = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        baseDatos = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        colorTema = new javax.swing.JColorChooser();
        jPanel4 = new javax.swing.JPanel();
        logoChooser = new javax.swing.JButton();
        selectedFileField = new javax.swing.JTextField();
        programIcon = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nombreInstitucion = new javax.swing.JTextField();
        backupPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 102, 0));

        jLabel10.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel10.setText("Configuraciones");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aceptar.png"))); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Computadora");

        computadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computadoraActionPerformed(evt);
            }
        });

        jLabel2.setText("Usuario");

        jLabel3.setText("Contraseña");

        jLabel4.setText("Base de Datos");

        baseDatos.setEditable(false);
        baseDatos.setText("durablock");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(baseDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(computadora))))
                .addContainerGap(326, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {baseDatos, password, usuario});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(computadora, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(baseDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {baseDatos, computadora, password, usuario});

        panels.addTab("Base de Datos", jPanel1);

        colorTema.setBorder(javax.swing.BorderFactory.createTitledBorder("Color Aplicación"));
        jScrollPane1.setViewportView(colorTema);

        panels.addTab("Color Aplicación", jScrollPane1);

        logoChooser.setText("Elegir Logo");
        logoChooser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoChooserMouseClicked(evt);
            }
        });
        logoChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoChooserActionPerformed(evt);
            }
        });

        selectedFileField.setEditable(false);

        programIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        programIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ReyBlock.png"))); // NOI18N
        programIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel6.setText("Nombre");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(programIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(logoChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectedFileField, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel6, logoChooser});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {nombreInstitucion, selectedFileField});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nombreInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectedFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(programIcon)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, logoChooser, nombreInstitucion, selectedFileField});

        panels.addTab("Institución", jPanel4);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1389964910_Time-Machine.png"))); // NOI18N
        jButton2.setText("Crear");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1389965068_Downloads.png"))); // NOI18N
        jButton3.setText("Cargar");
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backupPanelLayout = new javax.swing.GroupLayout(backupPanel);
        backupPanel.setLayout(backupPanelLayout);
        backupPanelLayout.setHorizontalGroup(
            backupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backupPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        backupPanelLayout.setVerticalGroup(
            backupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backupPanelLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addGroup(backupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        panels.addTab("Copias de Seguridad", backupPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panels, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Color getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = new Color(color[0], color[1], color[2]);
    }

    public int[] getColor(String color) {
        int colorArray[] = new int[3];
        String tmp1[] = color.split("=");
        String tmpColor1[] = tmp1[1].split(",");
        String tmpColor2[] = tmp1[2].split(",");
        String tmpColor3[] = tmp1[3].split("]");

        colorArray[0] = Integer.parseInt(tmpColor1[0]);
        colorArray[1] = Integer.parseInt(tmpColor2[0]);
        colorArray[2] = Integer.parseInt(tmpColor3[0]);

        return colorArray;
    }

    @SuppressWarnings("empty-statement")
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FileWriter fichero = null;
        int arregloColor[] = getColor(colorTema.getColor().toString());
        setColor(arregloColor);
        String cadenaColor[] = new String[3];
        cadenaColor[0] = "" + arregloColor[0];
        cadenaColor[1] = "" + arregloColor[1];
        cadenaColor[2] = "" + arregloColor[2];

        try {
            fichero = new FileWriter(System.getProperty("user.dir") + "" + File.separator + "src" + File.separator + "Configuraciones" + File.separator + "config.txt");
            PrintWriter pw = new PrintWriter(fichero);
            pw.println(computadora.getText());
            pw.println(usuario.getText());
            pw.println(password.getText());
            pw.println(baseDatos.getText());
            pw.println(iconPath);

        } catch (IOException ex) {
            Logger.getLogger(ConfigView.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }

                String[] datos = {computadora.getText(), usuario.getText(), password.getText(), baseDatos.getText(), cadenaColor[0], cadenaColor[1], cadenaColor[2], iconPath, nombreInstitucion.getText()};

                if (nuevo) {
                    // Instalar Base de Datos 
                    DataBaseClass.updateBackup(ConfigClass.configReaderInit(), System.getProperty("user.dir") + "" + File.separator + "src" + File.separator + "DataBase" + File.separator + "dataBase.sql");
                    // Instalar Data Default
                    DataBaseClass.updateBackup(ConfigClass.configReaderInit(), System.getProperty("user.dir") + "" + File.separator + "src" + File.separator + "DataBase" + File.separator + "dataDefault.sql");

                    if (DataBaseClass.connect(ConfigClass.configReaderInit())) {

                        String update = "UPDATE configuracion SET colorRed='" + arregloColor[0] + "', colorGreen='" + arregloColor[1] + "', colorBlue='" + arregloColor[2] + "', institutionName='" + nombreInstitucion.getText() + "';";
                        DataBaseClass.executeQuery(update);

                        // Voy a darle acceso o no al programa 
                        LoginView lv = new LoginView();
                        lv.setLocationRelativeTo(null);
                        lv.setVisible(true);

                        // Cambio la foto del logo 
                        // si es nula voy a tomar la que tengo como icono. 
                        ImageIcon ii = (ImageIcon) programIcon.getIcon();
                        Image im = ii.getImage();
                        updatePhoto(im);

                    } else {

                        ConfigView va = new ConfigView();
                        va.setLocationRelativeTo(null);
                        va.setVisible(true);

                    }
                    this.dispose();
                } else {
                    // Configuracion de configuraciones 
                    if (DataBaseClass.connect(ConfigClass.configReader())) {
                        String update = "UPDATE configuracion SET colorRed='" + arregloColor[0] + "', colorGreen='" + arregloColor[1] + "', colorBlue='" + arregloColor[2] + "', institutionName='" + nombreInstitucion.getText() + "';";
                        DataBaseClass.executeQuery(update);
                        
                        // Cambio la foto del logo 
                        // si es nula voy a tomar la que tengo como icono. 
                        ImageIcon ii = (ImageIcon) programIcon.getIcon();
                        Image im = ii.getImage();
                        updatePhoto(im);
                        
                        indexview.reDrawFrame(datos);
                        this.dispose();
                    }

                }

            } catch (Exception E2) {
                E2.printStackTrace();
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    /* Lo que hace el metodo es cambiar el logo de la institucion */
    private void updatePhoto(Image im) {
        int resultado = 0;
        try {

            PreparedStatement ps = null;
            Connection connection = DataBaseClass.getConnection();
            ps = (PreparedStatement) connection.prepareStatement("UPDATE configuracion SET logo = ? WHERE idConfiguracion = 1 ;");

            File photoToSave = new File(System.getProperty("user.dir") + "" + File.separator + "src" + File.separator + "Imagenes" + File.separator + "logoEmpresa.jpg");
            // si no es null la imagen guardo la ultima que tome. 
            try {
                BufferedImage bi = new BufferedImage(im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(im, 0, 0, null);
                g.dispose();

                ImageIO.write(bi, "JPEG", photoToSave);
            } catch (IOException ex) {

                JOptionPane.showMessageDialog(rootPane, "Error al cargar foto : " + ex);
            }

            // despues guardo la imagen en la base de datos y listo
            FileInputStream fis = null;

            try {
                fis = new FileInputStream(photoToSave);
            } catch (FileNotFoundException ex) {
            }

            ps.setBinaryStream(1, fis);

            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConfigView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /* Lo que hace es obtener la foto que tengo guardada en configuraciones. 
     */

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
    
    private void computadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computadoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_computadoraActionPerformed

    private void logoChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoChooserActionPerformed

        File archivoSeleccionado = null;

        // Primero que nada mostrare para cargar el archivo
        JFileChooser fc = new JFileChooser(); // despliego un menu file seleccion

        fc.addChoosableFileFilter(new FiltroArchivo());  // solo archivos .mrn abrire

        fc.setAcceptAllFileFilterUsed(false); // acepto todos loa archivos: falsos.

        int returnValue = fc.showDialog(null, "Cargar Imagen");

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = fc.getSelectedFile(); // obtengo el archivo seleccionado
        } else {
            // quiere decir que dio cancelar al archivo entoncesno hago nadaa
        }

        fc.setSelectedFile(null); // esto es para indicar al file chooser que para la proxima empezara de cero.

        if (archivoSeleccionado != null) {
            // Continuo con el programa, pero antes vuelvo a verificar que sea .jpg o .png
            String extension = Utils.getExtension(archivoSeleccionado);
            if (extension != null) {
                if (extension.equals(Utils.getRp()[1]) || extension.equals(Utils.getRp()[0])) {
                    // Continuo con el programa
                    selectedFileField.setText("" + archivoSeleccionado.getAbsolutePath());
                    previewImage(archivoSeleccionado.toString());

                } else {
                    // Muestro esta ventana de Error indicando que hay error en cargar el archivo
                    JOptionPane.showMessageDialog(null, "Archivo no válido", "Error al Cargar Archivo", 0);

                }
            }

        } else {
            // Muestro esta ventana de Error indicando que hay error en cargar el archivo
            JOptionPane.showMessageDialog(null, "Archivo no válido", "Error al Cargar Archivo", 0);
        }


    }//GEN-LAST:event_logoChooserActionPerformed

    private void logoChooserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoChooserMouseClicked

    }//GEN-LAST:event_logoChooserMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Crear Backup
        DataBaseClass.commitBackup(ConfigClass.configReader());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Cargar Backup 
        DataBaseClass.updateBackup(ConfigClass.configReaderInit(), System.getProperty("user.dir") + "" + File.separator + "src" + File.separator + "DataBase" + File.separator + "dataBackup.sql");

    }//GEN-LAST:event_jButton3ActionPerformed
    private void previewImage(String img) {
        ImageIcon th = null;
        if (img == null) {
            th = null;
            return;
        }

        //Don't use createImageIcon (which is a wrapper for getResource)
        //because the image we're trying to load is probably not one
        //of this program's own resources.
        ImageIcon tmpIcon = new ImageIcon(img);
        if (tmpIcon != null) {
            if (tmpIcon.getIconWidth() > 400 || tmpIcon.getIconHeight() > 286) {
                th = new ImageIcon(tmpIcon.getImage().
                        getScaledInstance(401, 286,
                                Image.SCALE_DEFAULT));
            } else { //no need to miniaturize
                th = tmpIcon;
            }

        }

        programIcon.setIcon(th);
        iconPath = img;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfigView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfigView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backupPanel;
    private javax.swing.JTextField baseDatos;
    private javax.swing.JColorChooser colorTema;
    private javax.swing.JTextField computadora;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoChooser;
    private javax.swing.JTextField nombreInstitucion;
    private javax.swing.JTabbedPane panels;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel programIcon;
    private javax.swing.JTextField selectedFileField;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
