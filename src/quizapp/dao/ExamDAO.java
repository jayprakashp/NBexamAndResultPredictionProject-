/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import quizapp.dbutil.DBConnection;
import quizapp.pojo.ExamDTO;
import quizapp.pojo.UserProfile;

/**
 *
 * @author jp
 */
public class ExamDAO {
    public static String getExamId()throws SQLException{
        Connection con=DBConnection.getConnection();
       Statement st=con.createStatement();
       int rowcount=1;
       ResultSet rs=st.executeQuery("select count(*) as totalrows from exam");
       if(rs.next())
        rowcount=rs.getInt(1);
       return "EX-"+(rowcount+1);
    }
    
    public static boolean addExam(ExamDTO exam)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps= con.prepareStatement("insert into exam values(?,?,?)");
        ps.setString(1,exam.getExamId());
        ps.setString(2,exam.getLanguage());
        ps.setInt(3,exam.getTotalQuestion());
        int ans=ps.executeUpdate();
        if(ans==1)
            return true;
        return false;
    }
    public static ArrayList<String> getLanguage()throws SQLException{
         Connection con=DBConnection.getConnection();
       Statement st=con.createStatement();
       ArrayList<String> list=new ArrayList<>();
       ResultSet rs=st.executeQuery("select language from exam");
       while(rs.next())
       {
           list.add(rs.getString(1));
       }
       return list;
    }
    
    public static ArrayList<String> getExamIdBySubject(String subject)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select examid from exam where language=?");
        ps.setString(1, subject);
        ArrayList<String> list=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
    public static int getQuestionCountByExam(String id)throws SQLException{
         Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select total_question from exam where examid=?");
        ps.setString(1, id);
        int ans=0;
        ResultSet rs=ps.executeQuery();
        rs.next();
            ans=rs.getInt(1);
        
        return ans;
    }
     public static ArrayList<String> getExamIdBySubjectAndUserid(String subject)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select examid from exam where language=? and examid<>all(select examid from performance where userid=?)");
        ps.setString(1, subject);
        ps.setString(2,UserProfile.getUsername());
        ArrayList<String> list=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
   
}
