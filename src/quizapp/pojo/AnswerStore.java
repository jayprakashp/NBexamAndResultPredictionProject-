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
public class AnswerStore {
    ArrayList<Answer> answerlist;
    public AnswerStore(){
        this.answerlist=new ArrayList<>();
    }
    public void addAnswer(Answer answer){
        answerlist.add(answer);
    }
    public Answer getAnswer(int pos){
        return answerlist.get(pos);
    }
    public void removeAnswer(int pos){
        answerlist.remove(pos);
    }
    public void setAnswerAt(int pos,Answer ans){
        answerlist.add(pos, ans);
    }
    public ArrayList<Answer> getAllAnswer(){
        return answerlist;
    }
    public int getCount(){
        return answerlist.size();
    }
    public Answer getAnswerByQno(int qno){
        for(Answer ans:answerlist){
            if(ans.getQno()==qno)
            return ans;
        }
         return null;
    }
    public int removeAnswer(Answer ans){
        int pos=answerlist.indexOf(ans);
        answerlist.remove(pos);
        return pos;
    }
    
}
