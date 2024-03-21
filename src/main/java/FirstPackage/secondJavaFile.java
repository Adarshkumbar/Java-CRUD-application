package FirstPackage;
import java.util.Scanner;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

public class secondJavaFile {
    public static Pair<String, String> sample() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter mail:");
        String name = sc.next();
    
        System.out.println("Enter password:");
        String password = sc.next();
        
        // Create a pair of strings
        Pair<String, String> result = new Pair<>(name, password);
        
        // Return the pair
        return result;
    }
}

