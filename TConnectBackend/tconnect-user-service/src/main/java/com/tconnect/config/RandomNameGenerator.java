package com.tconnect.config;

public class RandomNameGenerator{
	public static String generateImageName(){
		return generateImageName(UserServiceConsts.ALPHANUMERIC, UserServiceConsts.MAX_IMAGE_NAME_LENGTH);
	}
	
	public static String generateImageName(String chars, int length){
		StringBuilder name = new StringBuilder(length);
		int range = chars.length();
		for(int i = 0; i < length; i++){
			int index = (int) (Math.random() * range);
			name.append(chars.charAt(index));
		}
		return name.toString();
	}
}