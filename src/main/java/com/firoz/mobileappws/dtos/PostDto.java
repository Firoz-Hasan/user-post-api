package com.firoz.mobileappws.dtos;

public class PostDto {
    private String postname;
    private String description;
    private Integer userid;

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public PostDto(String postname, String description, Integer userid) {
        this.postname = postname;
        this.description = description;
        this.userid = userid;
    }

    public PostDto() {
    }
}
