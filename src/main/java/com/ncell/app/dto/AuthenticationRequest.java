package com.ncell.app.dto;

public class AuthenticationRequest {
	
	private long number;
	private int otp;
	
	public long getNumber() {
		return number;
	}
	
	public void setNumber(long number) {
		this.number = number;
	}
	
	public int getOtp() {
		return otp;
	}
	
	public void setOtp(int otp) {
		this.otp = otp;
	}
	
	

}
