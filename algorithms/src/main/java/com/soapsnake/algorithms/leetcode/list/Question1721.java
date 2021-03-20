package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-03-15
 */
public class Question1721 {

    public ListNode swapNodes(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode first = head, second = head;

        // Put fast (k-1) nodes after slow
        for (int i = 0; i < k - 1; ++i)
            fast = fast.next;

        // Save the node for swapping
        first = fast;

        // Move until the end of the list
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Save the second node for swapping
        // Note that the pointer second isn't necessary: we could use slow for swapping as well
        // However, having second improves readability
        second = slow;

        // Swap values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
        return head;
    }

    public static class Codec {

        private static final Map<String, String> URL_MAP = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String key = "dwz";
            // 要使用生成 URL 的字符
            String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                    "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                    "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                    "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                    "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                    "U", "V", "W", "X", "Y", "Z"
            };
            // 对传入网址进行 MD5 加密
            String sMD5EncryptResult = "";
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update((key + longUrl).getBytes());
                byte[] digest = md.digest();
                sMD5EncryptResult = DatatypeConverter.printHexBinary(digest).toUpperCase();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            String short_url = sMD5EncryptResult.substring(10);
            URL_MAP.put(short_url, longUrl);
            return short_url;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return URL_MAP.get(shortUrl);
        }
    }
}
