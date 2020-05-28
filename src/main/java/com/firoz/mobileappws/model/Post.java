package com.firoz.mobileappws.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Post {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String postname;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "post_tags",
            joinColumns = {
                    @JoinColumn(name = "post_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "tag_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
	private Set<Tag> tags;
	
	//@JsonBackReference
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Post(String postname, String description, Integer userid) {
		super();
		this.postname = postname;
		this.description = description;
		this.userid = userid;
	}
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name="userid", insertable=false, updatable=false)
	private User user;
	
	private Integer userid;	
	
	public Integer getUserid() {
		return userid;
	}
	
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPostName() {
		return postname;
	}
	public void setPostName(String postName) {
		this.postname = postName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonBackReference
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post(String postName, String description, User user) {
		super();
		this.postname = postName;
		this.description = description;
		this.user = user;
	}
	public Post() {
		super();
	}
	
}
