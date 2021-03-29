/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import giang.tblRequestDAO.TblRequestDAO;
import giang.tblRequestDAO.TblRequestDTO;
import giang.tblRequestDetailDAO.TblRequestDetailDAO;
import giang.tblRequestDetailDAO.TblRequestDetailDTO;
import java.util.List;

/**
 *
 * @author Ninh Giang
 */
public class LoadReqAction {

    private List<TblRequestDetailDTO> reslist;
    private List<TblRequestDTO> list;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final int ROW_PER_PAGE = 2;
    private int PAGEQUANTITY;
    private String pageNum;

    public LoadReqAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String loadRequest() throws Exception {
        String url = FAIL;
        TblRequestDAO dao = new TblRequestDAO();
        int num = 1;
        if (pageNum != null) {
            num = Integer.parseInt(pageNum);
        }
        boolean result = dao.loadRequests(num, ROW_PER_PAGE);
        if (result) {
            list = dao.getList();
            int size = dao.getSize("", "", "", "", "", num, ROW_PER_PAGE);
            PAGEQUANTITY = (size % ROW_PER_PAGE == 0) ? (size / ROW_PER_PAGE) : (size / ROW_PER_PAGE + 1);
            url = SUCCESS;
            TblRequestDetailDAO ddao = new TblRequestDetailDAO();
            boolean r = ddao.getRequestDetails();
            if (r) {
                reslist = ddao.getList();
            }
        }
        return url;
    }

    public List<TblRequestDTO> getList() {
        return list;
    }

    public void setList(List<TblRequestDTO> list) {
        this.list = list;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public int getPAGEQUANTITY() {
        return PAGEQUANTITY;
    }

    public void setPAGEQUANTITY(int PAGEQUANTITY) {
        this.PAGEQUANTITY = PAGEQUANTITY;
    }

    public List<TblRequestDetailDTO> getReslist() {
        return reslist;
    }

    public void setReslist(List<TblRequestDetailDTO> reslist) {
        this.reslist = reslist;
    }
}
