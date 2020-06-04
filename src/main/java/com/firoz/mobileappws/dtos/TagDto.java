package com.firoz.mobileappws.dtos;

public class TagDto {
    public String getTgname() {
        return tgname;
    }

    public void setTgname(String tgname) {
        this.tgname = tgname;
    }

    public TagDto() {
    }

    private String tgname;

    public TagDto(String tgname) {
        this.tgname = tgname;
    }
}
