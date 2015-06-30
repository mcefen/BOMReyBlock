package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.report.ReportManager;
import net.sf.jxls.report.ReportManagerImpl;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ToExcel {

    // Variables de base de datos 
    private Statement st = DataBaseClass.getSt();
    private Connection connection = DataBaseClass.getConnection();
    private ResultSet resultSet = DataBaseClass.getResultSet();

    public void Open(String file) {

        // Codigo para abrir el archivo  
        if (Desktop.isDesktopSupported()) {
            try {
                // esto verifica si puede abrir archivos la computadora 
                Desktop.getDesktop().open(new File(file));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al abrir documento : <br/> "+file+" <br/> " + ex + " </p></pre></body></html>", "Error al abrir documento", 0);

            }

        }

        
    }

    public void con3Datos(String[] dataPlanilla, String reporteNombre) {
        try {
            // tipo a va hacer cuando crea el archivo con una consulta y dos parametros en este
            // caso fecha inicial y final, y luego el nombre de la plantilla que es directamente a el nombre de la plantilla.
            Map beans = new HashMap();
            ReportManager rm = new ReportManagerImpl(connection, beans);

            beans.put("rm", rm);
            beans.put("fechaInicial", dataPlanilla[0]);
            beans.put("fechaFinal", dataPlanilla[1]);
            beans.put("codigoReporte", dataPlanilla[2]);

            XLSTransformer transformer = new XLSTransformer();

            transformer.transformXLS(System.getProperty("user.dir") + ""+File.separator+"src"+File.separator+"Plantillas"+File.separator+"" + reporteNombre + ".xls", beans, System.getProperty("user.dir") + ""+File.separator+"src"+File.separator+"ArchivosGeneradosExcel"+File.separator+"" + reporteNombre + ".xls");

            String file = System.getProperty("user.dir") + ""+File.separator+"src"+File.separator+"ArchivosGeneradosExcel"+File.separator+"" + reporteNombre + ".xls";

            Open(file);  // abro el archivo. 

        } catch (ParsePropertyException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al exportar reporte "+reporteNombre+", ParsePropertyException :  <br/>" + ex + " </p></pre></body></html>", "Error al exportar reporte "+reporteNombre+"", 0);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al exportar reporte "+reporteNombre+", IOException :  <br/>" + ex + " </p></pre></body></html>", "Error al exportar reporte "+reporteNombre+"", 0);

        } catch (InvalidFormatException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al exportar reporte "+reporteNombre+", formato número excepción :  <br/>" + ex + " </p></pre></body></html>", "Error al exportar reporte "+reporteNombre+"", 0);

        }

    }
    
    public void con2Datos(String[] dataPlanilla, String reporteNombre) {
        try {
            // tipo a va hacer cuando crea el archivo con una consulta y dos parametros en este
            // caso fecha inicial y final, y luego el nombre de la plantilla que es directamente a el nombre de la plantilla.
            Map beans = new HashMap();
            ReportManager rm = new ReportManagerImpl(connection, beans);

            beans.put("rm", rm);
            beans.put("fechaInicial", dataPlanilla[0]);
            beans.put("fechaFinal", dataPlanilla[1]);
           

            XLSTransformer transformer = new XLSTransformer();

            transformer.transformXLS(System.getProperty("user.dir") +File.separator+ "src"+File.separator+"Plantillas"+File.separator+ reporteNombre + ".xls", beans, System.getProperty("user.dir")+File.separator+"src"+File.separator+"ArchivosGeneradosExcel"+File.separator+"" + reporteNombre + ".xls");

            String file = System.getProperty("user.dir") + ""+File.separator+"src"+File.separator+"ArchivosGeneradosExcel"+File.separator+"" + reporteNombre + ".xls";

            Open(file);  // abro el archivo. 

        } catch (ParsePropertyException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al exportar reporte "+reporteNombre+", ParsePropertyException :  <br/>" + ex + " </p></pre></body></html>", "Error al exportar reporte "+reporteNombre+"", 0);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al exportar reporte "+reporteNombre+", IOException :  <br/>" + ex + " </p></pre></body></html>", "Error al exportar reporte "+reporteNombre+"", 0);

        } catch (InvalidFormatException ex) {
            JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al exportar reporte "+reporteNombre+", formato número excepción :  <br/>" + ex + " </p></pre></body></html>", "Error al exportar reporte "+reporteNombre+"", 0);

        }

    }

    public void TipoBlockCosto(String[] arrayFecha, String plantilla, double igss, double imn, double luz) throws SQLException, ParsePropertyException, IOException, InvalidFormatException {
        // tipo a va hacer cuando crea el archivo con una consulta y dos parametros en este 
        // caso fecha inicial y final, y luego el nombre de la plantilla que es directamente a el nombre de la plantilla. 
        Map beans = new HashMap();
        ReportManager rm = new ReportManagerImpl(connection, beans);

        beans.put("rm", rm);
        beans.put("fechaInicial", arrayFecha[0]);
        beans.put("fechaFinal", arrayFecha[1]);
        beans.put("IGSS", igss);
        beans.put("Imn", imn);
        beans.put("Luz", luz);

        XLSTransformer transformer = new XLSTransformer();

        transformer.transformXLS(System.getProperty("user.dir") + "\\src\\Plantillas\\" + plantilla + ".xls", beans, System.getProperty("user.dir") + "\\src\\ArchivosGeneradosExcel\\" + plantilla + ".xls");

        String file = System.getProperty("user.dir") + "\\src\\ArchivosGeneradosExcel\\" + plantilla + ".xls";

        Open(file);  // abro el archivo. 

    }

}
