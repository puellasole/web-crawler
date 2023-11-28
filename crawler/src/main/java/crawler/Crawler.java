package crawler;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	private static final int MAX_DEPTH = 5;
	private static Set<String> visitedUrls = new HashSet<String>();
	private static void crawl(String url, int depth) {
		if (depth > MAX_DEPTH || visitedUrls.contains(url)) {
            return;
        }
		 visitedUrls.add(url);
		try {
			Document doc = Jsoup.connect(url).get();

			Elements links = doc.select("a[href]");
	        for (Element link : links) {
	            String href = link.attr("abs:href");
		        System.out.println(href);	           
	        }
	        crawl(url, ++depth);
	        
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {		
		String url = "https://en.wikipedia.org/wiki/Main_Page";
		crawl(url,0);
	}
}
