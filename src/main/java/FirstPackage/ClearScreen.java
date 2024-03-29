package FirstPackage;

public class ClearScreen {
	public static void clearScreen() {
	    try {
	        if (System.getProperty("os.name").contains("Windows")) {
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        } else {
	            new ProcessBuilder("clear").inheritIO().start().waitFor();
	        }
	    } catch (Exception e) {
	        System.out.println("Error clearing screen: " + e.getMessage());
	    }
	}
}
