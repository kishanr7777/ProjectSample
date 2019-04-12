package com.tatva.tconnectGeneralConfigs;

public class URLConfigs {
	
	private static String[] allowedHosts = TconnectConsts.ALLOWED_HOSTS;
	
	public static String isRequestHostAuthorised(String host) {
		boolean hostMatch = false;
		
		for (String s : allowedHosts) {
			if (s.equals(host)) {
				hostMatch = true;
				break;
			}
		}
		if (!hostMatch)
			return TconnectConsts.ERROR_UNAUTHORIZED_HOST_RESUEST;

		return null;
	};
}
