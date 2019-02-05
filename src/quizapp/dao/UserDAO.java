/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import quizapp.dbutil.DBConnection;
import quizapp.pojo.User;

/**
 *
 * @author jp
 */
public class UserDAO {
    
    public static boolean loginUser(User u)throws SQLException{
        
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("Select 5 from users where userid=? and password=? and usertype=?");
            ps.setString(1,u.getUserName());
            ps.setString(2,u.getPassword());
            ps.setString(3,u.getUserType());
            int ans=ps.executeUpdate();
            System.out.println(ans);
            if(ans==1){
                
                return true;
            }
            return false;
    }
    public static boolean addUser(User u)throws SQLException{
        Connection con=DBConnection.getConnection();
        if(loginUser(u)){
            return false;
        }
        PreparedStatement ps=con.prepareStatement("insert into users values(?,?,?)");
        ps.setString(1, u.getUserName());
        ps.setString(2, u.getPassword());
        ps.setString(3,u.getUserType());
        int ans=ps.executeUpdate();
        if(ans==1){
            return true;
        }
        return false;
    } 
    public static boolean updatePassword(String Userid,String pwd)throws SQLException{
        Connection con=DBConnection.getConnection();
         PreparedStatement ps=con.prepareStatement("update users set password=? where userid=? and usertype=?");
         ps.setString(1, pwd);
         ps.setString(2,Userid);
         ps.setString(3,"student");
         int ans=ps.executeUpdate();
         if(ans==1){
             return true;
         }
         return false;
    }
}
