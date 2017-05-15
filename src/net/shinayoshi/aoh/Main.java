package net.shinayoshi.aoh;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    private static String url = "http://www.shinayoshi.net";

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect(url).get();
        Elements newsHeadlines = doc.select("h1");
        for (Element element : newsHeadlines) {
            System.out.println(element.text());
        }
    }
}
