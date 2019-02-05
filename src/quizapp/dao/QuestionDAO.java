/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp.dao;

import java.sql.*;
import java.util.ArrayList;
import quizapp.dbutil.DBConnection;
import quizapp.pojo.QuestionDTO;
import quizapp.pojo.QuestionStore;

/**
 *
 * @author jp
 */
public class QuestionDAO {
    
    public static void addQuestion(QuestionStore qs)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("insert into questions values(?,?,?,?,?,?,?,?,?)");
        for(QuestionDTO q:qs.getAllQuestion()){
            ps.setString(1,q.getExamid());
            ps.setInt(2,q.getQno());
            ps.setString(3, q.getQuestion());
            ps.setString(4, q.getAnswer1());
            ps.setString(5,q.getAnswer2());
            ps.setString(6, q.getAnswer3());
             ps.setString(7, q.getAnswer4());
              ps.setString(8, q.getCorrectAnswer());
               ps.setString(9, q.getLanguage());
               ps.executeUpdate();
        }
    }
    public static ArrayList<QuestionDTO> getQuestionByExamId(String examid)throws SQLException{
         Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select * from questions where examid=? order by qno");
        ps.setString(1, examid);
        ArrayList<QuestionDTO> list=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
       while(rs.next()){
           QuestionDTO obj=new QuestionDTO(rs.getString(1),rs.getInt(2),rs.getString(9),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(3));
           list.add(obj);
       } 
        return list;
    }
    public static void updateQuestion(QuestionStore qs)throws SQLException{
         Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("update questions set question=?,answer1=?,answer2=?,answer3=?,answer4=?,correct_answer=? where examid=? and qno=?");
      
        for(QuestionDTO q:qs.getAllQuestion()){
            ps.setString(7,q.getExamid());
            ps.setInt(8,q.getQno());
            ps.setString(1, q.getQuestion());
            ps.setString(2, q.getAnswer1());
            ps.setString(3,q.getAnswer2());
            ps.setString(4, q.getAnswer3());
             ps.setString(5, q.getAnswer4());
              ps.setString(6, q.getCorrectAnswer());
              
               ps.executeUpdate();
        }
    }
    public static String getCorrectAnswer(String examid,int qno)throws SQLException{
       Connection con=DBConnection.getConnection();
       PreparedStatement ps=con.prepareStatement("select correct_answer from questions where examid=? and qno=?");
       ps.setString(1,examid);
       ps.setInt(2, qno);
       ResultSet rs=ps.executeQuery();
       rs.next();
       return rs.getString(1);
    }
}
