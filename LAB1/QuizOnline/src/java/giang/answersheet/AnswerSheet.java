/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.answersheet;

import java.util.HashMap;


/**
 *
 * @author Ninh Giang
 */
public class AnswerSheet {
    private HashMap<Integer, String> answers;

    public HashMap<Integer, String> getAnswers() {
        return answers;
    }

    public void addToSheet(int questionCode, String answer) {
        if (this.answers == null) {
            this.answers = new HashMap<Integer, String>();
        }
        if (this.answers.containsKey(questionCode)) {
            this.answers.replace(questionCode, answer);
        } else {
            this.answers.put(questionCode, answer);
        }
    }
    
}
