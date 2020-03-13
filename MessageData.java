import java.net.*;
import java.io.*;

/**
 * A class containing the method used to scrape RSS Feeds
 */
public class MessageData {
    /**
     * Scrapes RSS feeds for the most recently link
     * @param urlAddress A string containing the URl Address to be scraped
     * @return Returns the most recent link as a string
     */
    public static String readRSS(String urlAddress) {
        try {
            URL rssUrl = new URL(urlAddress);
            BufferedReader reader = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                int EndIndex = 0;
                int StartIndex = 0;

                while (StartIndex >=0) {
                    StartIndex = line.indexOf("<link>", EndIndex);
                    EndIndex = line.indexOf("</link>", StartIndex);

                    //Continues loop after lines without <link>
                    if (StartIndex == -1 || StartIndex == -1) {
                        continue;
                    }

                    String urlLink = line.substring(StartIndex + 6, EndIndex);
                    //Prevents homepage from being returned
                    if (!urlLink.equals(urlAddress.substring(0, urlAddress.length() - 6))   ) {
                        reader.close();
                        return urlLink;
                    }
                }
            }
        } catch (MalformedURLException mue) {
            System.out.println("Bad URL");
        } catch (IOException ioe) {
            System.out.println("Something went wrong");
        }
        return null;
    }

}
