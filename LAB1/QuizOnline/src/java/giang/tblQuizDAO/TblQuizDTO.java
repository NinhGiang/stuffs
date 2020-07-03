/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblQuizDAO;

import java.sql.Timestamp;

/**
 *
 * @author Ninh Giang
 */
public class TblQuizDTO {

    private int quizCode;
    private int quantity;
    private int correctAnswers;
    private float point;
    private String subjectCode;
    private Timestamp takenTime;
    private String email;

    public TblQuizDTO() {
    }

    public TblQuizDTO(int questionCode, int quantity, int correctAnswers, float point, String subjectCode, Timestamp takenTime, String email) {
        this.quizCode = questionCode;
        this.quantity = quantity;
        this.correctAnswers = correctAnswers;
        this.point = point;
        this.subjectCode = subjectCode;
        this.takenTime = takenTime;
        this.email = email;
    }

    public int getQuizCode() {
        return quizCode;
    }

    public void setQuizCode(int quizCode) {
        this.quizCode = quizCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Timestamp getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(Timestamp takenTime) {
        this.takenTime = takenTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
