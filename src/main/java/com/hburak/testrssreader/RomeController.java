package com.hburak.testrssreader;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rometools.rome.io.FeedException;

@RestController
public class RomeController {

	@Autowired
	private MyRssReader rssView;
	
	@GetMapping("/rss")
	public String getFeed() throws IllegalArgumentException, MalformedURLException, FeedException, IOException {
		return rssView.consume();
	}
	
	@GetMapping("/rss3")
	public String getJsonFeed() throws MalformedURLException {
		return rssView.consumeJson();
	}
	
	@GetMapping("/rss2")
	public String getJsonFeed2() throws JsonParseException, JsonMappingException, IOException {
		return rssView.consumeJson4();
	}
}
