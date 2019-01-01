package com.hburak.testrssreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Component
public class MyRssReader {

	public String consume() throws IllegalArgumentException, FeedException, MalformedURLException, IOException {
		// String url =
		// "https://catalog.api.gamedistribution.com/api/v1.0/rss/All/?collection=all&categories=All&type=all&amount=10&page=1&format=xml";
		// String url = "http://rss.cnn.com/rss/cnn_latest.rss";
		String url = "http://localhost:9045/test2";
		StringBuilder sb = new StringBuilder();
		XmlReader reader = new XmlReader(new URL(url));
		SyndFeed feed = new SyndFeedInput().build(reader);
		sb.append(feed.getTitle());
		for (SyndEntry entry : feed.getEntries()) {
			sb.append(entry);
		}
		return sb.toString();
	}

	public String consumeJson() throws MalformedURLException {
		JSONObject json = new JSONObject(new URL(
				"https://catalog.api.gamedistribution.com/api/v1.0/rss/All/?collection=all&categories=All&type=all&amount=10&page=1&format=json"));
		return json.toString();
	}

	public String consumeJson2() {
		// String url =
		// "https://catalog.api.gamedistribution.com/api/v1.0/rss/All/?collection=all&categories=All&type=all&amount=10&page=1&format=json";
		String url = "http://localhost:9045/test4";
		MyJSONParser jsonParser = new MyJSONParser();
		JSONObject object = jsonParser.getJsonFromUrl(url);
		return object.toString();
	}

	public String consumeJson3() throws JsonParseException, JsonMappingException, IOException {
		String url = "http://localhost:9045/test4"; // just a string
		ObjectMapper mapper = new ObjectMapper(); // just need one
		Map<String, Object> map = mapper.readValue(url, Map.class);
		return map.toString();
	}

	public String consumeJson4() {
		String link = "http://localhost:9045/test3"; //just a string
		StringBuilder sb = new StringBuilder();
		URLConnection urlconn = null;
		InputStreamReader in = null;
		
		try {
			URL url = new URL(link);
			urlconn = url.openConnection();
			if(urlconn != null) {
				urlconn.setReadTimeout(60*100);
			}
			if(urlconn != null && urlconn.getInputStream() != null) {
				in = new InputStreamReader(urlconn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
