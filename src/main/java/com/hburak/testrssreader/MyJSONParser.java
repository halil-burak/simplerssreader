package com.hburak.testrssreader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

public class MyJSONParser {
	
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	public JSONObject getJsonFromUrl(String url) {
		
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			HttpResponse response = httpclient.execute(post);
			HttpEntity httpEntity = response.getEntity();
			is = httpEntity.getContent();
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                is, "iso-8859-1"), 8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	            System.out.println(line);
	        }
	        is.close();
	        json = sb.toString();

	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    }

	    // try parse the string to a JSON object
	    try {
	        jObj = new JSONObject(json);
	    } catch (JSONException e) {
	       e.printStackTrace();
	        System.out.println("error on parse data in jsonparser.java");
	    }

	    // return JSON String
	    return jObj;
		
	}
}
