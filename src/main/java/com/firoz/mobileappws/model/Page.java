package com.firoz.mobileappws.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "pages")
public class Page {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int pagemembers;
	
	public Page() {
		super();
	}

	public Page(int pagemembers, String pagename, String pagedescription) {
		super();
		this.pagemembers = pagemembers;
		this.pagename = pagename;
		this.pagedescription = pagedescription;
	}

	private String pagename;
	private String pagedescription;
	
	@ManyToMany(mappedBy = "pages", fetch = FetchType.LAZY)
	private Set<User> users;

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

	@JsonBackReference
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}



	




}
