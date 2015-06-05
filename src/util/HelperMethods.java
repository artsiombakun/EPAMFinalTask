package util;

import java.io.UnsupportedEncodingException;
/**
 * Provide methods for execute specific helper actions
 * */
public class HelperMethods {
	/**
	 * Change encoding from ISO-8859-1 to UTF-8
	 * @param source - string at ISO-8859-1 encoding
	 * @return string at UTF-8 encoding
	 * */
	public static String setEncoding(String source) throws UnsupportedEncodingException{
		String rez = null;
		rez = new String(source.getBytes("ISO-8859-1"),"UTF-8");
		return rez;
	}
	/*
	public static String setEncoding(String source, String toEncoding) throws UnsupportedEncodingException{
		String rez = null;
		rez = new String(source.getBytes("ISO-8859-1"),toEncoding);
		return rez;
	}
	
	public static String setEncoding(String source, String fromEncoding, String toEncoding) throws UnsupportedEncodingException{
		String rez = null;
		rez = new String(source.getBytes(fromEncoding),toEncoding);
		return rez;
	}*/
}
