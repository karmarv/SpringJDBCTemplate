package com.rhl.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class HttpUtility {

	/**
	 * 
	 * @param url
	 * @param postBodyContent
	 * @return
	 * @throws Exception
	 */
	static StringBuilder httpPostRequest(String url,String postBodyContent) throws Exception{
		
		HttpURLConnection connection = null;
	      BufferedReader rd  = null;
	      StringBuilder requestOutput = new StringBuilder(100);
	      String line = null;
	    
	      URL serverAddress = null;
	    
	      try {
	          serverAddress = new URL(url);
	          //set up out communications stuff
	          connection = null;
	        
	          //Set up the initial connection
	          connection = (HttpURLConnection)serverAddress.openConnection();
	          connection.setUseCaches(false);
	          connection.setDoOutput(true); // Triggers POST.
	          connection.setRequestProperty("accept-charset", "UTF-8");
	          connection.setRequestProperty("content-type", "application/json");

	          OutputStreamWriter writer = null;
	          try {
	              writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	              writer.write(postBodyContent); // Write POST query string (if any needed).
	          } finally {
	              if (writer != null) try { writer.close(); } catch (IOException logOrIgnore) {}
	          }
	        
	          //read the result from the server
	          rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        
	          while ((line = rd.readLine()) != null){
	        	  requestOutput.append(line + '\n');
	          }
	        
	                    
	      } catch (MalformedURLException e) {
	          e.printStackTrace();
	      } catch (ProtocolException e) {
	          e.printStackTrace();
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	      finally{
	          //close the connection, set all objects to null
	          connection.disconnect();
	          rd = null;
	         // requestOutput = null;
	          connection = null;
	      }
	      
	      return requestOutput;
	}
	
	/**
	 * 
	 * @param url
	 * @param postBodyContent
	 * @return
	 * @throws Exception
	 */
	static StringBuilder httpputRequest(String url,String postBodyContent) throws Exception{
		
		HttpURLConnection connection = null;
	      BufferedReader rd  = null;
	      StringBuilder requestOutput = new StringBuilder(100);
	      String line = null;
	    
	      URL serverAddress = null;
	    
	      try {
	          serverAddress = new URL(url);
	          //set up out communications stuff
	          connection = null;
	        
	          //Set up the initial connection
	          connection = (HttpURLConnection)serverAddress.openConnection();
	          connection.setUseCaches(false);
	          connection.setDoOutput(true); // Triggers POST.
	          connection.setRequestMethod("PUT");
	          connection.setRequestProperty("accept-charset", "UTF-8");
	          connection.setRequestProperty("content-type", "application/json");

	          OutputStreamWriter writer = null;
	          try {
	              writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	              writer.write(postBodyContent); // Write POST query string (if any needed).
	          } finally {
	              if (writer != null) try { writer.close(); } catch (IOException logOrIgnore) {}
	          }
	        
	          //read the result from the server
	          rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        
	          while ((line = rd.readLine()) != null){
	        	  requestOutput.append(line + '\n');
	          }
	        
	                    
	      } catch (MalformedURLException e) {
	          e.printStackTrace();
	      } catch (ProtocolException e) {
	          e.printStackTrace();
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	      finally{
	          //close the connection, set all objects to null
	          connection.disconnect();
	          rd = null;
	         // requestOutput = null;
	          connection = null;
	      }
	      
	      return requestOutput;
	}
}
