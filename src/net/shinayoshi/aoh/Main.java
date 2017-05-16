package net.shinayoshi.aoh;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Input Email Address: ");
        String email = br.readLine();
        System.out.print("Input Password: ");
        String password = br.readLine();

        String url = "https://www.amazon.co.jp/ap/signin?_encoding=UTF8&openid.assoc_handle=jpflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.co.jp%2Fgp%2Fyourstore%2Fhome%3Fie%3DUTF8%26ref_%3Dnav_signin";

        Map<String, String> cookie = Jsoup.connect(url)
                .data("email", email)
                .data("password", password)
                .followRedirects(false)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                .method(Connection.Method.POST)
                .execute()
                .cookies();

        int year = 2017;
        int num = 0;
        String url2 = "https://www.amazon.co.jp/gp/css/order-history?digitalOrders=1&unifiedOrders=1&orderFilter=year-"+year+"&startIndex="+(num*10);
        Connection conn = Jsoup.connect(url2).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0");
        for (Map.Entry<String, String> entry : cookie.entrySet()) {
            conn.cookie(entry.getKey(), entry.getValue());
        }
        Document doc = conn.get();

        System.out.println(doc.title());
    }

}
