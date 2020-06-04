package com.firoz.mobileappws.dtos;

public class TagDto {
    private String tagname;

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public TagDto(String tagname) {
        this.tagname = tagname;
    }
}
