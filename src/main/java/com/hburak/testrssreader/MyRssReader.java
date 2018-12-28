package com.hburak.testrssreader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Component
public class MyRssReader {

	public String consume() throws IllegalArgumentException, FeedException, MalformedURLException, IOException {
		String url = "https://catalog.api.gamedistribution.com/api/v1.0/rss/All/?collection=all&categories=All&type=all&amount=10&page=1&format=xml";
		//String url = "http://rss.cnn.com/rss/cnn_latest.rss";
		StringBuilder sb = new StringBuilder();
		XmlReader reader = new XmlReader(new URL(url));
		SyndFeed feed = new SyndFeedInput().build(reader);
		sb.append(feed.getTitle());
		for (SyndEntry entry : feed.getEntries()) {
			sb.append(entry);
		}
		return sb.toString();
	}
}
