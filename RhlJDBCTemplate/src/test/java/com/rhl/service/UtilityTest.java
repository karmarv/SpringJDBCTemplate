/**
 * 
 */
package com.rhl.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rahul Vishwakarma
 * 
 */
public class UtilityTest {

	protected static Logger log = LoggerFactory.getLogger(UtilityTest.class);

	public static String findTextBasedOnRegex(String regex, String text) {
		List<String> result = null;
		Matcher m = Pattern.compile(regex).matcher(text);
		if (m.find()) {
			result = new ArrayList<String>();
			result.add(m.group());
			while (m.find()) {
				result.add(m.group());
			}
		}
		String val = "";
		if(result != null)
			val =result.get(0);
		return val;
	}
	
	public static long getTimeFromTimestampString(String timestamp){
		long time = 0; 
		boolean done = false;
		if(!done){
			try{
				//Format 4/1/2013 12:00:00 AM
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
				time = dateFormat.parse(timestamp).getTime();
				done = true;
			} catch (ParseException e) {
				log.debug("1. Unable to parse timestamp: "+e.getMessage());
			}
		}
		return time;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Double ddate = 40379.0088888889;
		long jValue = 0;
		Date date= null;
		// Earlier than 1985 in Unix, must be Apple
		if(ddate < 473410800) {
			jValue =(long) ((ddate + 978307200L) * 1000);
		    date = new Date(jValue);
		} else {
			jValue =(long) (ddate * 1000);
		    date = new Date(jValue);
		}
		log.info("Java Date "+date);
		log.info("Current time mili "+System.currentTimeMillis());
		log.info("Current time nano "+System.nanoTime());
		log.info("Timestamp value "+(new Timestamp(getTimeFromTimestampString("40379.0088888889"))));
		log.info("\n");
		log.info("-----------------------------------------------------");
	}

}
