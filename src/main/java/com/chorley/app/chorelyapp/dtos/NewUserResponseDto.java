package com.chorley.app.chorelyapp.dtos;

public class NewUserResponseDto {
	private long id;
	private String firstName;
	
	public NewUserResponseDto(long id, String firstName) {
		this.id = id;
		this.firstName = firstName;
	}
	
	public NewUserResponseDto() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
}
