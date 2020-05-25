package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created on 2020-04-11
 */
public class Question535 {

    //leetcode535
    public static class Codec {

        List<String> urls = new ArrayList<String>();
        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            urls.add(longUrl);
            return String.valueOf(urls.size()-1);
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            int index = Integer.parseInt(shortUrl);
            return (index<urls.size())?urls.get(index):"";
        }
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        String longurl = "https://leetcode.com/problems/design-tinyurl";
        String shorurl = codec.encode(longurl);
        System.out.println(shorurl);
        System.out.println(codec.decode(shorurl));
    }
}
