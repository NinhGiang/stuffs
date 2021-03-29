/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import giang.tblResourceDAO.TblResourceDAO;
import giang.tblResourceDAO.TblResourceDTO;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Ninh Giang
 */
public class SearchAction {

    private final int ROW_PER_PAGE = 2;
    private int pageNum;
    private String txtCategory;
    private String txtName;
    private String dateFrom;
    private String dateTo;
    private List<TblResourceDTO> list;
    private final String SUCCESS = "success";
    private int PAGEQUANTITY;

    public SearchAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String searchResource() throws NamingException, SQLException {
        String url = SUCCESS;
        if (!(dateFrom.trim().equals("")
                && dateTo.trim().equals("")
                && txtCategory.trim().equals("")
                && txtName.trim().equals(""))) {
            TblResourceDAO dao = new TblResourceDAO();
            dao.searchResources(txtCategory, txtName, dateFrom, dateTo, pageNum, ROW_PER_PAGE);
            list = dao.getList();
            int size = dao.getSize(txtCategory, txtName, dateFrom, dateTo, pageNum, ROW_PER_PAGE);
            PAGEQUANTITY = (size % ROW_PER_PAGE == 0) ? (size / ROW_PER_PAGE) : (size / ROW_PER_PAGE + 1);
        }
        return url;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getTxtCategory() {
        return txtCategory;
    }

    public void setTxtCategory(String txtCategory) {
        this.txtCategory = txtCategory;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
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

    public List<TblResourceDTO> getList() {
        return list;
    }

    public void setList(List<TblResourceDTO> list) {
        this.list = list;
    }

    public int getPAGEQUANTITY() {
        return PAGEQUANTITY;
    }

    public void setPAGEQUANTITY(int PAGEQUANTITY) {
        this.PAGEQUANTITY = PAGEQUANTITY;
    }
    
}
