package com.tatva.tconnectGeneralConfigs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationPropertyConfigs {
	public static void setApplicationProperties(String path) throws IOException {
		
		File file = new File(path);
		
		Properties props = new Properties();
		
		props.load(new FileInputStream(file));
		
		props.setProperty("spring.data.mongodb.database", TconnectConsts.DB_NAME);
		props.setProperty("spring.data.mongodb.host", TconnectConsts.DB_HOST);
		props.setProperty("spring.data.mongodb.port", TconnectConsts.DB_PORT);

		FileOutputStream out = new FileOutputStream(new File(path));
		
		props.store(out, null);
		
		out.close();
	}
}
