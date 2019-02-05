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
import quizapp.pojo.Performance;
import quizapp.pojo.StudentAnalysis;
import quizapp.pojo.StudentScore;

/**
 *
 * @author jp
 */
public class PerformanceDAO {
    
    public static ArrayList<String> getAllStudentId()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
        ArrayList<String> list=new ArrayList();
        ResultSet rs=st.executeQuery("select distinct(userid) from performance");
        while(rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
    public static ArrayList<String> getAllExamId(String id)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select examid from performance where userid=?");
        ps.setString(1, id);
        ResultSet rs=ps.executeQuery();
        ArrayList<String> list=new ArrayList();
        while(rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
    public static StudentScore getScore(String userid,String examid)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select language,per from performance where userid=? and examid=?");
        ps.setString(1,userid);
        ps.setString(2, examid);
        ResultSet rs=ps.executeQuery();
        rs.next();
            StudentScore obj=new StudentScore();
            obj.setLanguage(rs.getString(1));
            obj.setPer(rs.getDouble(2));
            return obj;
        
    }
    public static ArrayList<Performance> getAllData()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
       ArrayList<Performance> list=new ArrayList();
       ResultSet rs=st.executeQuery("select * from performance");
       while(rs.next()){
           Performance obj=new Performance(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),rs.getString(7));
           list.add(obj);
       }
       return list;
    }
    public static boolean addperformance(Performance p)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("insert into performance values(?,?,?,?,?,?,?)");
        ps.setString(1,p.getUserId());
        ps.setString(2,p.getExamId());
       ps.setInt(3,p.getRight());
       ps.setInt(4,p.getWrong());
       ps.setInt(5,p.getUnattempted());
       ps.setDouble(6,p.getPer());
       ps.setString(7,p.getLanguage());
      int ans=ps.executeUpdate();
      if(ans==1){
          return true;
      }
      return false;
    }
    public static ArrayList<String> getAllSubject(String id)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select language from performance where userid=?");
        ps.setString(1, id);
        ResultSet rs=ps.executeQuery();
        ArrayList<String> list=new ArrayList();
        while(rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
     public static ArrayList<StudentAnalysis> getStudentResult(String id)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select examid,per from performance where userid=?");
        ps.setString(1, id);
        ResultSet rs=ps.executeQuery();
        ArrayList<StudentAnalysis> list=new ArrayList();
        while(rs.next()){
            StudentAnalysis s=new StudentAnalysis();
            s.setTestid(rs.getString(1));
            s.setPercentage(rs.getDouble(2));
           list.add(s);
        }
        return list;
    }
    public static ArrayList<resultAnalysis> getStudenttestresult(String userid,String examid)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select max(RIGHT),max(WRONG),max(UNATTEMPTED) from performance where userid=? and examid=?");
        ps.setString(1, userid);
        ps.setString(2, examid);
        ResultSet rs=ps.executeQuery();
        ArrayList<resultAnalysis> list=new ArrayList();
        while(rs.next()){
            resultAnalysis s=new resultAnalysis();
            s.setCorrect(rs.getInt(1));
            s.setIncorrect(rs.getInt(2));
            s.setUnattempted(rs.getInt(3));
           list.add(s);
        }
        return list;
    }
       
    public static ArrayList<String> getSubjectWiseExamId(String id,String subject)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select examid from performance where userid=? and language=?");
        ps.setString(1, id);
        ps.setString(2,subject);
        ResultSet rs=ps.executeQuery();
        ArrayList<String> list=new ArrayList();
        while(rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
     public static ArrayList<Double> getStudentPer(String id,String subject)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select per from performance where userid=? and language=?");
        ps.setString(1, id);
        ps.setString(2,subject);
        ResultSet rs=ps.executeQuery();
        ArrayList<Double> list=new ArrayList();
        while(rs.next()){
            list.add(rs.getDouble(1));
        }
        return list;
    }
      public static ArrayList<resultAnalysis> getStudentRightWrongList(String userid,String subject)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select right,wrong,unattempted from performance where userid=? and language=?");
        ps.setString(1, userid);
        ps.setString(2,subject);
        ResultSet rs=ps.executeQuery();
       ArrayList<resultAnalysis> list=new ArrayList();
        while(rs.next()){
            resultAnalysis s=new resultAnalysis();
            s.setCorrect(rs.getInt(1));
            s.setIncorrect(rs.getInt(2));
            s.setUnattempted(rs.getInt(3));
           list.add(s);
        }
        return list;
    }
    }

