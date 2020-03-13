import java.util.Scanner;

/**
 *  A class that contains the methods used to obtain user input
 */
public class InputReader {

    /**
     * Prompts user for a bot token and accepts the bot token as a string input
     * @return Returns the bot token as a string
     */
    public static String returnToken() {

        System.out.println("Please enter your Discord bot token");
        Scanner inputScanner = new Scanner(System.in);
        String botToken = inputScanner.nextLine();

        return botToken;

    }

    /**
     * Prompts user for a channelID and accepts the ID as a long input
     * @return Returns the channelID as a long
     */
    public static Long returnChannelID() {

        System.out.println("Please enter the ID of the channel updates are to be sent to");
        Scanner inputScanner = new Scanner(System.in);
        Long channelID = inputScanner.nextLong();

        return channelID;
    }

    /**
     * Prompts user for the feeds to scrape as a string, and converts it to a string array
     * @return Returns a string array where each element is a feed URL
     */
    public static String [] returnURLAddresses() {

        System.out.println("Please enter the feeds that you want updates for, separated by commas");
        Scanner inputScanner = new Scanner(System.in);
        String URLInput = inputScanner.nextLine();
        URLInput = URLInput.replaceAll("\\s+","");
        String[] URLAddresses = URLInput.split(",");

        return URLAddresses;
    }

    /**
     * Initialises a string array to contain previous URL's
     * @param arraylength Integer length of the array containing feed URLs
     * @return A string array the same length as the feed URL address where each element is blank
     */
    public static String [] returnPreviousURLs(int arraylength) {

        String [] previousURLs = new String[arraylength];
        for (int i = 0; i < arraylength; i++) {
            previousURLs[i] = "";
        }
        System.out.println("Set up complete!");

        return previousURLs;
    }
}
