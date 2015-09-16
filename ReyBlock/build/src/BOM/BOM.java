 // Giovanni Rojas Developer
package BOM;

import javax.swing.UIManager;


public class BOM {

     
    public static void main(String[] args) {
        try {
                  // select Look and Feel
            
                  UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                  //UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                  //UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
                 
    
              } catch (Exception ex) {
                  
              }
        
          
        // Este metodo es Estatico porque lo puedo acceder de cualquier 
        // Clase, pero no a sus atributos directamente por lo que continua siendo 
        // Encapsulada 
        if (DataBaseClass.connect(ConfigClass.configReaderInit())){
            // Cargar Backup 
            
            
            
            // Voy a darle acceso o no al programa 
            LoginView vi = new LoginView ();
            vi.setLocationRelativeTo(null);
            vi.setVisible(true);
           
            
            
        }
        else {
            ConfigView va = new ConfigView();
            va.setLocationRelativeTo(null);
            va.setVisible(true);
            
        }
        
                   
       
    }
}
