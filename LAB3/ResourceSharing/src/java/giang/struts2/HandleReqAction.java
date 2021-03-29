/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import giang.tblRequestDAO.TblRequestDAO;
import giang.tblRequestDetailDAO.TblRequestDetailDAO;
import giang.tblRequestDetailDAO.TblRequestDetailDTO;
import giang.tblResourceDAO.TblResourceDAO;
import java.util.List;

/**
 *
 * @author Ninh Giang
 */
public class HandleReqAction {

    private int requestID;
    private String status;
    private int pageNum;
    private String txtResName;
    private String txtUserName;
    private String dateFrom;
    private String dateTo;
    private String txtStatus;
    private final String SUCCESS = "success";
    private final String SUCCESSNOSEARCH = "successnosearch";
    private final String FAIL = "fail";

    public HandleReqAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String handleRequest() throws Exception {
        String url = FAIL;
        TblRequestDAO dao = new TblRequestDAO();
        boolean result = dao.updateRequest(requestID, status);
        if (status.equals("Decline")) {
            TblResourceDAO rdao = new TblResourceDAO();
            TblRequestDetailDAO rddao = new TblRequestDetailDAO();
            rddao.getRequestDetails();
            List<TblRequestDetailDTO> rddto_list = rddao.getList();
            for (TblRequestDetailDTO rddto : rddto_list) {
                if (rddto.getRequestID() == requestID) {
                    rdao.updateResource(rddto.getResourceID(), rddto.getQuantity());
                }
            }
        }
        if (result) {
            if (dateFrom.trim().equals("")
                    && dateTo.trim().equals("")
                    && txtResName.trim().equals("")
                    && txtUserName.trim().equals("")
                    && txtStatus.trim().equals("")) {
                url = SUCCESSNOSEARCH;
            } else {
                url = SUCCESS;
            }
        }
        return url;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}
