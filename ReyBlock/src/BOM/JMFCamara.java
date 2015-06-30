
package BOM;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.*;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class JMFCamara {
    //Controlador universal de windows
    private  String dispositivo = "vfw:Microsoft WDM Image Capture (Win32):0";
    private  Player player = null;
    private  CaptureDeviceInfo device = null;
    private  MediaLocator localizador = null ;

    public Component Componente(){
        
    
    Component componente_video;
    
      try {
            //El localizador es del tipo "vfw://0" video para windows
            //se crea el PLAYER y se ejecuta
             // Se obtiene el dispositivo
            device = CaptureDeviceManager.getDevice(dispositivo);
            //se obtiene la fuente de datos de captura
            localizador = new MediaLocator(dispositivo);
            player = Manager.createRealizedPlayer(localizador);
            player.start();
            
        } catch (IOException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoPlayerException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotRealizeException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        //Si se pudo crear el PLAYER, se obtiene el componente de video
        if ((componente_video = player.getVisualComponent()) != null) {
                //se da un tamaño al componente
                componente_video.setBounds(15, 15, 293, 231);
               // componente_video.setSize(323, 261);
                return componente_video;
            } else {
                JOptionPane.showMessageDialog(null,"No se pudo crear el video...");
                return null;
            }
    }

    //Metodo para capturar la imagen de la webcam
    Image img = null;
    public void capturarImagen(){
        FrameGrabbingControl ControlFG = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
        Buffer buffer = ControlFG.grabFrame();
        // creamos la imagen awt
       BufferToImage imagen = new BufferToImage((VideoFormat)buffer.getFormat());
       img = imagen.createImage(buffer);
       
    }

    public Image getImagen(){
        
        return img;
    }

    public void setImage(JLabel lb){
        capturarImagen();        
        lb.setIcon( new javax.swing.ImageIcon( img ) );
        System.out.println("ancho= " + img.getWidth(null));
        System.out.println("alto= " + img.getHeight(null));
    }
    
    public void turnOff(){
        try {
            player.stop();
            player.close();
            }catch(Exception e){
                
            }
    }
            

}
