package com.sogou.upd.passport.admin.manager.form;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-17
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
public class ProblemQueryParam {
    //@RequestParam("status") int status, @RequestParam("clientId") int clientId, @RequestParam("typeId") int typeId,
    //@RequestParam("startDateStr") String startDateStr, @RequestParam("endDateStr") String endDateStr,
    //@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("pageNum") int pageNum,
    private int status;
    private int clientId;
    private int typeId;
    private  String startDateStr;
    private  String endDateStr;
    private String title;
    private String content;
    private int pageNum;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
