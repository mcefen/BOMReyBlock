

package BOM;



import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class PhotoCell extends DefaultTableCellRenderer
{
    Border unselectedBorder = null;
    Border selectedBorder = null;
    boolean isBordered = true;

    public PhotoCell() {
       
    }
    public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column)
        {
                if(value instanceof JLabel)
                {
                    JLabel label = (JLabel)value;
                    
                    return label;
                }
                else
                {
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
        }


   

   
  
}
