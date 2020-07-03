/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.utils;

import giang.tblQuestionDAO.TblQuestionDTO;
import giang.tblQuizDAO.TblQuizDTO;
import java.util.ArrayList;

/**
 *
 * @author Ninh Giang
 */
public class Pagination {

    public static ArrayList<TblQuestionDTO> getPagingQuestion(ArrayList<TblQuestionDTO> dtoList, int quantityPerPage, int currentPage) {
        int skip = quantityPerPage * (currentPage - 1);//20 trang, quan 4, current 4 => skip 16
        int end = skip + quantityPerPage;//end = 20
        if (skip + quantityPerPage > dtoList.size()) {
            end = dtoList.size();
        }
        ArrayList<TblQuestionDTO> result = new ArrayList<>();
        for (int i = skip; i < end; i++) {
            result.add(dtoList.get(i));
        }
        return result;
    }

    public static int calculateNumofPage(int listSize, int rowPerPage) {
        int result = listSize / rowPerPage;
        if (listSize % rowPerPage != 0) {
            result = result + 1;
        }
        return result;
    }

    public static ArrayList<TblQuizDTO> getPagingQuiz(ArrayList<TblQuizDTO> dtoList, int quantityPerPage, int currentPage) {
        int skip = quantityPerPage * (currentPage - 1);//20 trang, quan 4, current 4 => skip 16
        int end = skip + quantityPerPage;//end = 20
        if (skip + quantityPerPage > dtoList.size()) {
            end = dtoList.size();
        }
        ArrayList<TblQuizDTO> result = new ArrayList<>();
        for (int i = skip; i < end; i++) {
            result.add(dtoList.get(i));
        }
        return result;
    }
}
