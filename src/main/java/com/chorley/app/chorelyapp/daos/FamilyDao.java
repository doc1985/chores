package com.chorley.app.chorelyapp.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "family")
public class FamilyDao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String email;
	
	private String password;
	
	private String name;
	
	@OneToMany(
		mappedBy = "family",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
	private List<UserDao> users = new ArrayList<>();
	
	@OneToMany(
		mappedBy = "family",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
	private List<ChoreDao> chores = new ArrayList<>();
	
	@OneToMany(
			mappedBy = "family",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<RewardDao> rewards = new ArrayList<>();

	public List<RewardDao> getRewards() {
		return rewards;
	}

	public void setRewards(List<RewardDao> rewards) {
		this.rewards = rewards;
	}

	public List<ChoreDao> getChores() {
		return chores;
	}

	public void setChores(List<ChoreDao> chores) {
		this.chores = chores;
	}

	public List<UserDao> getUsers() {
		return users;
	}

	public void setUsers(List<UserDao> users) {
		this.users = users;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
