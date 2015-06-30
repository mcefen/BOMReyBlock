/*
 * Universidad del Valle de Guatemla 
 * Giovanni Rojas Mazariegos 12134
 * 
 * 
 * Clase FiltroArchivo: Esta clase se dedica solo a filtrar el archivo de entrada. 
 * Este archivo fue de documentos previos crados por el alumno Giovanni Rojas 
 */

package BOM;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;



// esta clase es utilizada para el filtrar archivos en el Jfile Dialog. 

public class FiltroArchivo extends FileFilter {

    @Override
    public boolean accept(File file) {
        
        if (file.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(file);
        if (extension != null) {
            if (extension.equals(Utils.getRp()[1]) ||  extension.equals(Utils.getRp()[0])) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Archivos RP (.jpg y .png)";
    }
    
}
