package com.example.demo.controller;

public class AddResponse {
	
	private String message;
	private int id;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AddResponse(String message, int id) {
		super();
		this.message = message;
		this.id = id;
	}
	public AddResponse() {
		super();
	}

}
