package com.firoz.mobileappws.dtos;

public class MessageResponseDto {
	private String message;

	public MessageResponseDto(String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
