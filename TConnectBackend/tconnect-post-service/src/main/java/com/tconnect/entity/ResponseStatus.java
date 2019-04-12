package com.tconnect.entity;

public class ResponseStatus {
	private boolean status;
	private String message;
	public ResponseStatus(boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResponseStatus [status=" + status + ", message=" + message + "]";
	}
	
	
}
