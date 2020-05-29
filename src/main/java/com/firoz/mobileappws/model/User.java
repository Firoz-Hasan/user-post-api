package com.firoz.mobileappws.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	
	
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_pages",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "page_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
	private Set<Page> pages;

	public Set<Page> getPages() {
		return pages;
	}

	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> posts;

	@JsonManagedReference
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User( String firstName, String lastName, String email) {
		super();
		
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
	}

	public User() {

	}

	public User(String firstname, String lastname, String email, Set<Page> pages) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.pages = pages;
	}

}
