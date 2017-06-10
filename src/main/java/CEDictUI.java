/**
 * 
 */

import java.util.Scanner;

/**
 * A simple text-based user interface for looking up from CC-CEDICT.
 * 
 * @author S H S Wong
 * @version 05/05/2017
 */
public class CEDictUI {

	private CEDictController cedict;
	
	/**
	 * Constructor
	 * 
	 * @param cedict
	 */
	public CEDictUI(CEDictController cedict) {
		
		this.cedict = cedict;
				
		while (true) {
			displayMenu();
			getAndProcessUserOption();
		}
	}

	/**
	 * Displays the header of this application and a summary of menu options.
	 */
	private void displayMenu() {
		display(header());
		display(menu());
	}
	
	/**
	 * Obtains an user option and processes it.
	 */
	private void getAndProcessUserOption() {
		Scanner in = new Scanner(System.in);
		String command = in.nextLine().trim();
		if (command.indexOf(' ') == -1) {
			// only one word can be found in the user input command
			if (command.equalsIgnoreCase("q")) {
				// Exits the application
				display("Thank you for using CC-CEDICT. Goodbye!");
				System.exit(0);
			}
			else if (command.equalsIgnoreCase("stats")) {
				// Displays summary statistics for this CC-CEDICT application
				display(cedict.summaryStatistics());
			}
			else {
				// Looks up a given Chinese word
				display(cedict.lookupChinese(command));
			}
		}
		else {
			// >1 word can be found in the user input command
			String chosenOption = command.substring(0, command.indexOf(' '));
			String query = command.substring(command.indexOf(' ')+1);
			if (chosenOption.equalsIgnoreCase("pre")) {
				// look up the required prefix
				display(cedict.lookupChinesePrefix(query));
			}
			else if (chosenOption.equalsIgnoreCase("py")) {
				// Lookup the query from the PinYin lookup table
				display(cedict.lookupPinyin(query));
			}
			else if (chosenOption.equalsIgnoreCase("eng")) {
				// Lookup the query from the English lookup table
				display(cedict.lookupEnglish(query));
			}
			else {
				// Not a known command option
				display(unrecogniseCommandErrorMsg(command));
			}
		}
	}
	
	/**
	 * Returns a string representation of a brief title for this application as the header.
	 * @return	a header
	 */
	private String header() {
		return "\nCC-CEDICT - Chinese-English dictionary. \n";
	}
	
	/**
	 * Returns a string representation of the user menu.
	 * @return	the user menu
	 */
	private String menu() {
		return "To lookup a Chinese word: Type the Chinese word (in traditional or simplified character(s))\n" +
		       "To lookup a PinYin: Type 'py' followed by the PinYin delimited by [], eg\n" +
		       "\tpy [Ding1 Ru3 chang1]\n" +
		       "To lookup an English word/phrase: Type 'eng' followed by the English word/phrase, eg\n" +
		       "\teng T-bone steak\n" +		       
		   	   "To lookup a Chinese prefix: Type 'pre' followed by the required prefix, eg\n" +
		   	   "\tpre 一字\n" + 
		   	   "To display summary statistics: Type 'stats'\n" +
			   "To exit this application: Type 'q'\n";
	}
	
	/**
	 * Displays the specified information for the user to view.
	 * @param info	info to be displayed on the screen
	 */
	private void display(String info) {
		System.out.println(info);
	}
	
    /**
     * Returns an error message for an unrecognised command.
     * 
     * @param error the unrecognised command
     * @return      an error message
     */
    private String unrecogniseCommandErrorMsg(String error) {
            return String.format("Cannot recognise the given command: %s.%n", error);
    }
	
}
