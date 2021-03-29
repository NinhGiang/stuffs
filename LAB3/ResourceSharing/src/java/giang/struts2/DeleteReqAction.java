/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import giang.tblRequestDAO.TblRequestDAO;

/**
 *
 * @author Ninh Giang
 */
public class DeleteReqAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String dateFrom;
    private String dateTo;
    private String txtResName;
    private int pageNum;
    
    private int requestID;

    public DeleteReqAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String deleteRequest() throws Exception {
        TblRequestDAO dao = new TblRequestDAO();
        String url = FAIL;
        if (dateFrom == null) {
            dateFrom = "";
        }
        if (dateTo == null) {
            dateTo = "";
        }
        if (txtResName == null) {
            txtResName = "";
        }
        if (pageNum == 0) {
            pageNum = 1;
        }
        boolean result = dao.deleteRequest(requestID);
        if (result) {
            url = SUCCESS;
        }
        return url;
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

    public String getTxtResName() {
        return txtResName;
    }

    public void setTxtResName(String txtResName) {
        this.txtResName = txtResName;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

}
