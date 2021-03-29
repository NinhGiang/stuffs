/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import com.opensymphony.xwork2.ActionContext;
import giang.tblRequestDAO.TblRequestDAO;
import giang.tblRequestDAO.TblRequestDTO;
import giang.tblRequestDetailDAO.TblRequestDetailDAO;
import giang.tblRequestDetailDAO.TblRequestDetailDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ninh Giang
 */
public class LoadHistoryAction {

    private List<TblRequestDetailDTO> reslist;
    private List<TblRequestDTO> list;
    private final String SUCCESS = "success";
    private final int ROW_PER_PAGE = 2;
    private int PAGEQUANTITY;
    private int pageNum;
    private String dateFrom;
    private String dateTo;
    private String txtResName;

    public LoadHistoryAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String loadHistory() throws Exception {
        TblRequestDAO dao = new TblRequestDAO();
        int num = 1;
        if (pageNum != 0) {
            num = pageNum;
        }
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String userID = (String) session.get("USERID");
        if (txtResName == null) {
            txtResName = "";
        }
        if (dateFrom == null) {
            dateFrom = "";
        }
        if (dateTo == null) {
            dateTo = "";
        }
        dao.searchRequestByUserID(dateFrom, dateTo, txtResName, userID, num, ROW_PER_PAGE);
        list = dao.getList();
        int size = dao.getSizeByUserID(dateFrom, dateTo, txtResName, userID, num, ROW_PER_PAGE);
        PAGEQUANTITY = (size % ROW_PER_PAGE == 0) ? (size / ROW_PER_PAGE) : (size / ROW_PER_PAGE + 1);
        String url = SUCCESS;
        TblRequestDetailDAO ddao = new TblRequestDetailDAO();
        boolean r = ddao.getRequestDetails();
        if (r) {
            reslist = ddao.getList();
        }
        return url;
    }

    public List<TblRequestDetailDTO> getReslist() {
        return reslist;
    }

    public void setReslist(List<TblRequestDetailDTO> reslist) {
        this.reslist = reslist;
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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
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

}
