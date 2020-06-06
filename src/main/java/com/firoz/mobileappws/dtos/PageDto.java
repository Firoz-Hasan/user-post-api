package com.firoz.mobileappws.dtos;

public class PageDto {
    private int id;
    private int pagemembers;
    private String pagename;
    private String pagedescription;

    public PageDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPagemembers() {
        return pagemembers;
    }

    public void setPagemembers(int pagemembers) {
        this.pagemembers = pagemembers;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public String getPagedescription() {
        return pagedescription;
    }

    public void setPagedescription(String pagedescription) {
        this.pagedescription = pagedescription;
    }
}
