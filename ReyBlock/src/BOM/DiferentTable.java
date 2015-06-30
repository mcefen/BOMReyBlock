 
package BOM;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


public class DiferentTable extends AbstractTableModel
{

   private Object data [][];
   private int numrows;
   private String [] columnNames ;
   private String consulta = "";
   private String status = "";
 
   // Variables de base de datos 
   private Statement st = DataBaseClass.getSt();
   private Connection connection = DataBaseClass.getConnection();
   private ResultSet resultSet = DataBaseClass.getResultSet();
   
   public DiferentTable(String [] listaTablas,String consul,String stats){// constructor
  
       
        columnNames = listaTablas; // recibo los datos. 
        consulta = consul;
        status = stats;
        try {

           st = (Statement) connection.createStatement();
           resultSet = st.executeQuery(consulta);
           ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
           
           resultSet.last();                // estas lineas se mueven al final de la consulta 
           numrows=resultSet.getRow();
           
           data = new Object [columnNames.length][numrows];
           resultSet.beforeFirst();
           int j=0;
           // Este ciclo se mueve en columnas y luego en filas. 
           while(resultSet.next()){
               for(int i=0;i<=columnNames.length;i++){
                   if (status.equalsIgnoreCase("Personal") || status.equalsIgnoreCase("Sacadores")){
                      
                       if(i==0)
                           data[i][j]=resultSet.getInt(1);
                       if(i==1)
                           data[i][j]=resultSet.getString(2);
                       if(i==2)
                           data[i][j]=resultSet.getString(3);
                       if(i==3)
                           data[i][j]=resultSet.getString(4);
                       if(i==4)
                           data[i][j]=resultSet.getDouble(5);
                       if(i==5)
                           data[i][j]=resultSet.getDouble(6);
       
                   }
                   else{
                     
                        if (status.equalsIgnoreCase("material")){ // llena los datos en material de produccion se pone para no generar error. 
                                if(i==0)
                                    data[i][j]=resultSet.getInt(1);
                                if (i==1)    
                                    data[i][j]=resultSet.getString(2);
                                if (i==2)
                                    data[i][j]=resultSet.getDouble(3);
                                if (i==5)
                                    data[i][j] = resultSet.getString(4);
                                
                                }      
                      
                       else {
                           if (status.equalsIgnoreCase("horasExtras")){
                             if (i==0)
                              data[i][j]=resultSet.getString(1);
                            if (i==1)
                              data[i][j]=resultSet.getString(2);
                            if (i==2)
                              data[i][j]=resultSet.getString(3);
                            if (i==3)
                              data[i][j]=resultSet.getDouble(4);
                          }
                     else {
                         if (status.equalsIgnoreCase("descuentos")){
                          if (i==0)
                           data[i][j]=resultSet.getString(1);
                          if (i==1)
                           data[i][j]=resultSet.getString(2);
                          if (i==2)
                           data[i][j]=resultSet.getString(3);
                         }
                         else {
                            if (status.equalsIgnoreCase("materialNuevo")){
                                if (i==0)
                                    data[i][j]=resultSet.getString(1);
                                if (i==1)
                                    data[i][j]=resultSet.getInt(2);
                                if (i==2)
                                    data[i][j]=resultSet.getInt(3);
                                if (i==3)
                                    data[i][j]=resultSet.getDouble(4);
                                if (i==4)
                                    data[i][j]=resultSet.getInt(5);
                                if (i==5)
                                    data[i][j]=resultSet.getDouble(6);
                                if (i==6)
                                    data[i][j]=resultSet.getInt(7);
                                if (i==7)
                                    data[i][j]=resultSet.getInt(8);
                                if (i==8)
                                    data[i][j]=resultSet.getString(9);

                            }

                    }
                      }
             
                            
                        }
                   }

                  
               }
              
               j++;
               
           }
           
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la tabla Load Data : \n "+ex, "Error en tabla", 0);
 
        }
    }
         
    
    
    @Override
    public int getRowCount() {
        return numrows; // numero de filas 
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    
    @Override
    public String getColumnName(int col){
      return columnNames[col];  
    }
    @Override
    public Object getValueAt(int row, int col) {
        return data[col][row];
    }
    
    @Override
    public boolean isCellEditable (int row, int col){ // dejo qee editen algunas columnas
    
    if(status.equalsIgnoreCase("block")){
        if(col ==0 || col == 6 )
           return false;
    }
    else {
       if (status.equalsIgnoreCase("material")){
           if (col == 3)
               return true;
           else 
               return false;
       }
    else {
     if (status.equalsIgnoreCase("personal")){
        if (col <= 8)
           return false;
         
     }
     else {
         if (status.equalsIgnoreCase("sacadores")){
             if (col != 6)
             return false;
         }
             
    else {
         if (status.equalsIgnoreCase("horasExtras")){
             if (col !=4)            
                 return false;
         }
    else {
         if (status.equalsIgnoreCase("descuentos")){
             if (col !=3)            
                 return false;
            }
  
         }
 
             }
     }
       }
        }   
    
       
     
     return true;  // si no pasa ninguna condicion regresa verdadero     
    } // fin del metodo 
    
    @Override
    public Class getColumnClass (int col){
     
       if (status.equalsIgnoreCase("block")){
           if (col != 0)       
            return Integer.class; 
       }
       else {
       if (status.equalsIgnoreCase("material")){
           if (col == 0)
               return Integer.class;
           if (col == 1 || col == 5)
               return String.class;
           else 
               return Double.class;
       }
       
       else {
       if (status.equalsIgnoreCase("personal")){
           if (col == 0)
               return Integer.class;
           if (col == 1 || col == 2 || col == 3)
               return String.class;
           if (col == 9)
               return Boolean.class;
           else
               return Double.class;
           
       }
       else {
       if (status.equalsIgnoreCase("sacadores")){
           if (col == 6 || col == 0)
               return Integer.class;
           if (col == 1 || col == 2 || col == 3)
               return String.class;
           else
               return Double.class;
       }
       else {
       if (status.equalsIgnoreCase("horasExtras")){
           if (col == 3 || col == 4 || col == 5)
               return Double.class;
         }
       else {
           if (status.equalsIgnoreCase("descuentos")){
               if (col == 3)
                   return Double.class;
               
           }
        }
       }
       }
        }
         }
         
       return String.class;
    }
    
   
    
    @Override
    public void setValueAt(Object value, int row, int col){
     
   /*if (status.equalsIgnoreCase("material"))
        {
            if (col == 2){
                 data[4][row]= value;
            }
            if (col == 4){
                data[2][row]= value;
            }
        }*/
      data[col][row]= value;
      fireTableCellUpdated(row,col);
      
      
    }
    
}
