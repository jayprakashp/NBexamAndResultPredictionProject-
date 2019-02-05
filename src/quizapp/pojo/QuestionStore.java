/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp.pojo;

import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class QuestionStore {
     ArrayList<QuestionDTO> questionlist;
    public QuestionStore() {
        this.questionlist =new ArrayList<QuestionDTO>();
    }
    public void addQuestion(QuestionDTO q){
        questionlist.add(q);
    }
   public QuestionDTO getQuestion(int pos){
       return questionlist.get(pos);
   }
   public void removeQuestion(int pos){
       questionlist.remove(pos);
   }
   public void setQuestionAt(int pos,QuestionDTO q)
   {
       questionlist.add(pos, q);
   }
   public ArrayList<QuestionDTO> getAllQuestion(){
       return questionlist;
   }
   public int getCount(){
       return questionlist.size();
   }
    
}
