import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WeatherProgram {

	  private static String trim(String s, int width) {
	        if (s.length() > width)
	            return s.substring(0, width-1) + ".";
	        else
	            return s;
	    }
	  
	public static void main(String[] args) throws IOException{		
		String url = "https://weather.com/";
		System.out.printf("Fetching...%s",url);
		
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a[href]");
		Elements media = doc.select("[src]");
		
		System.out.printf("\nLinks: (%d)", links.size());
		for(Element link : links){
			System.out.printf("\n * a: (%s)", link.attr("abs:href"), trim(link.text(),35));
		}
		
		System.out.printf("\nMedia: (%d)", media.size());
		for(Element pics : media){
			if(pics.tagName().equals("img")){
				System.out.printf("\n * %s:  <%s> %sx%s (%s)", 
						pics.tagName(), pics.attr("abs:src"), pics.attr("width"), pics.attr("height"), trim(pics.attr("alt"), 20));
			}
			else
				System.out.printf("\n * %s: <%s>", pics.tagName(), pics.attr("abs:src"));
		}
		
	}

}
