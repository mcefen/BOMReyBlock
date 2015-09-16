

package BOM;



import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class UpdatePhoto extends AbstractCellEditor
                         implements TableCellEditor,
			            ActionListener {
    
    static Icon icono;
    JButton button;
    UpdatePhotoWindow window;
    JDialog dialog;
    static JLabel lab = new JLabel();
    protected static final String EDIT = "edit";

    public UpdatePhoto() {
        // intalar un boton en la celda. 
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);

    }

  
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
        
            button.setIcon(icono);
           
            window = new UpdatePhotoWindow();
            window.setVisible(true);
            //Make the renderer reappear.
            fireEditingStopped();

        } else { 
            
         // sino dio click en la ventana. 
            
            JOptionPane.showMessageDialog(null, "yeah ");
        }
    }

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    public Object getCellEditorValue() {
        return lab;
        
    }

    
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        
        lab = (JLabel) value;
        icono = lab.getIcon();
        return button;
    }

    
}

