package com.tconnect.entity;

import com.tconnect.config.GatewayConsts;

public class ResetPassword {
	private String token;
	private long time;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public ResetPassword(String token, long time) {
		super();
		this.token = token;
		this.time = time;
	}
	@Override
	public String toString() {
		return "ResetPassword [token=" + token + ", time=" + time + "]";
	}
	
	public static ResetPassword buildToken(){
		return buildToken(GatewayConsts.ALPHANUMERIC, GatewayConsts.MAX_TOKEN_LENGTH);
	}
	
	public static ResetPassword buildToken(String chars, int length){
		StringBuilder token = new StringBuilder(length);
		int range = chars.length();
		for(int i = 0; i < length; i++){
			int index = (int) (Math.random() * range);
			token.append(chars.charAt(index));
		}
		return new ResetPassword(token.toString(), System.currentTimeMillis());
	}
	
}	
