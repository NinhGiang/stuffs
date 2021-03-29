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
public class SearchReqAction {

    private final int ROW_PER_PAGE = 2;
    private int pageNum;
    private String txtResName;
    private String txtUserName;
    private String dateFrom;
    private String dateTo;
    private String txtStatus;
    private List<TblRequestDTO> list;
    private List<TblRequestDetailDTO> reslist;
    private final String SUCCESS = "success";
    private final String SUCCESSNOSEARCH = "successnosearch";
    private final String FAIL = "fail";
    private int PAGEQUANTITY;

    public SearchReqAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String searchRequest() throws Exception {
        String url = SUCCESS;
        if (dateFrom == null
                && dateTo == null
                && txtResName == null
                && txtUserName == null
                && txtStatus == null) {
            url = SUCCESSNOSEARCH;
        } else {
            TblRequestDAO dao = new TblRequestDAO();
            if (txtStatus != null) {
                if (txtStatus.equals("All")) {
                    txtStatus = "";
                }
            } else txtStatus = "";
            dao.searchRequests(dateFrom, dateTo, txtResName, txtUserName, txtStatus, pageNum, ROW_PER_PAGE);
            list = dao.getList();
            int size = dao.getSize(dateFrom, dateTo, txtResName, txtUserName, txtStatus, pageNum, ROW_PER_PAGE);
            PAGEQUANTITY = (size % ROW_PER_PAGE == 0) ? (size / ROW_PER_PAGE) : (size / ROW_PER_PAGE + 1);
            TblRequestDetailDAO ddao = new TblRequestDetailDAO();
            boolean r = ddao.getRequestDetails();
            if (r) {
                reslist = ddao.getList();
            }
            //String url = SUCCESS;
        }
        return url;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getTxtResName() {
        return txtResName;
    }

    public void setTxtResName(String txtResName) {
        this.txtResName = txtResName;
    }

    public String getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(String txtUserName) {
        this.txtUserName = txtUserName;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getTxtStatus() {
        return txtStatus;
    }

    public void setTxtStatus(String txtStatus) {
        this.txtStatus = txtStatus;
    }

    public List<TblRequestDTO> getList() {
        return list;
    }

    public void setList(List<TblRequestDTO> list) {
        this.list = list;
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

    public void setReslist(List<TblRequestDetailDTO> resList) {
        this.reslist = resList;
    }

}
