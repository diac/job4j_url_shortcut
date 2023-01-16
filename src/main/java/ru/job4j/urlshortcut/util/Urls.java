package ru.job4j.urlshortcut.util;

public class Urls {

    private Urls() {

    }

    public static String idToShortUrl(int id) {
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuffer shorturl = new StringBuffer();
        while (id > 0) {
            shorturl.append(map[id % 62]);
            id = id / 62;
        }
        return shorturl.reverse().toString();
    }

    static int shortUrlToId(String shortUrl) {
        int id = 0;
        for (int i = 0; i < shortUrl.length(); i++) {
            if ('a' <= shortUrl.charAt(i)
                    && shortUrl.charAt(i) <= 'z') {
                id = id * 62 + shortUrl.charAt(i) - 'a';
            }
            if ('A' <= shortUrl.charAt(i)
                    && shortUrl.charAt(i) <= 'Z') {
                id = id * 62 + shortUrl.charAt(i) - 'A' + 26;
            }
            if ('0' <= shortUrl.charAt(i)
                    && shortUrl.charAt(i) <= '9') {
                id = id * 62 + shortUrl.charAt(i) - '0' + 52;
            }
        }
        return id;
    }
}