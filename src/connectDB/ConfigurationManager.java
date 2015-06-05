package connectDB;
/**
 * @author Artyom
 * */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

import constants.CustomConstants;
/**
 * get driver, url, login and password using any source, such as .properties file
 * </p>Singleton
 * */
public class ConfigurationManager {
	private static Logger theLogger = Logger.getLogger(ConfigurationManager.class);
	private Properties info = null;
	private static ConfigurationManager instance;
	private ConfigurationManager(){
		info = new Properties();
		try {
			info.load(new InputStreamReader(new FileInputStream(
					this.getClass().getResource("/").getPath()+ CustomConstants.DB_CONFIG_FILE_ADDRESS),"UTF-8"));
		} catch (IOException e) {
			theLogger.error(CustomConstants.CAN_NOT_LOAD_DB_CONFIG_FILE, e);
		}
	}
	/**
	 * init instance
	 * */
	public static ConfigurationManager getInstance() {
		if(instance==null){
			instance = new ConfigurationManager();
		}
		return instance;
	}
	public String getDriverName() {
		return info.getProperty(CustomConstants.DRIVER_TAG);
	}
	public String getURL() {
		return info.getProperty(CustomConstants.URL_TAG);
	}
	public String getLogin() {
		return info.getProperty(CustomConstants.LOGIN_TAG);
	}
	public String getPassword() {
		return info.getProperty(CustomConstants.PASSWORD_TAG);
	}
	
}
