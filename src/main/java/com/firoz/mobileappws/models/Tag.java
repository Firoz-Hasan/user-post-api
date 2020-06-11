package com.firoz.mobileappws.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tagname;
	
	public Tag() {
		super();
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	

	@JsonBackReference
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@ManyToMany( fetch = FetchType.LAZY)
	private List<Post> posts;

	public Tag(String tagname, List<Post> posts) {
		super();
		this.tagname = tagname;
		this.posts = posts;
	}

	public Tag(String tagname) {
		super();
		this.tagname = tagname;
	}


	
}
