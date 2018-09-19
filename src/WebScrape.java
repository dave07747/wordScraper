import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class WebScrape {
    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    public static int oneWordCount(final String url, final String word) {
        int count = 0;

        String[] words = urlToString(url).toLowerCase().split("[^a-zA-Z0-9]");

        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word.toLowerCase())) {
                count++;
            }
        }

        return count;
    }

    public static int uniqueCount(final String url) {
        String[] words = urlToString(url).toLowerCase().split("[^a-zA-Z0-9]");
        ArrayList<String> cache = new ArrayList<String>();

        for(int i = 0; i < words.length; ++i) {
            if (cache.indexOf(words[i]) < 0) {
                cache.add(words[i]);
            }
        }

        return cache.size();
    }

    public static int wordCount(final String url) {
        return urlToString(url).toLowerCase().split(" ").length;
    }

    public static void main(String[] unused) {
        System.out.println(wordCount("http://erdani.com/tdpl/hamlet.txt"));
        System.out.println(oneWordCount("http://erdani.com/tdpl/hamlet.txt", "prince"));
        System.out.println(uniqueCount("http://erdani.com/tdpl/hamlet.txt"));
    }
}
