/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class DBConnection {
    private static Connection con;
   static{  try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@//jp-pc:1521/XE","project","java");
            JOptionPane.showMessageDialog(null,"connection is established SUCCESSFULLY ","SUCCESS!",JOptionPane.INFORMATION_MESSAGE);
           
        }catch(ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Error in loading driver class :"+ex,"Error!",JOptionPane.ERROR_MESSAGE);
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null,"SQL Error : "+e,"ERROR!",JOptionPane.ERROR_MESSAGE);
        } 
        
    }
   public static Connection getConnection(){
       return con;
   }
   public static void closeConnection(){
       if(con!=null){
           try{
               con.close();
               JOptionPane.showMessageDialog(null,"Connection Closed ","Message",JOptionPane.INFORMATION_MESSAGE);
           }
           catch(SQLException e){
               JOptionPane.showMessageDialog(null,"SQL Error : "+e,"ERROR!",JOptionPane.ERROR_MESSAGE);
           }
       }
      
   }
    
}
