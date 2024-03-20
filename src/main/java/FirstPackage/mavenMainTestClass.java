package FirstPackage;
//import org.apache.commons.lang3.tuple.Pair;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;  

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

public class mavenMainTestClass {
	public static void main( String args[]) throws IOException {	
		DBconnection.getConnection();
		

		
		//System.err.println(pair.left + " and " + pair.right);
		
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("1)Login:\n2)Register:\n3)Exit:");
			//reg
			int choice = scanner.nextInt();
			UserDAO user = new UserDAO();
			Pair<String, String> pair = secondJavaFile.sample();
			
			//log
			
			if( choice == 1 && user.loginUser(pair.left, pair.right))
			{
				System.out.println("\n||| **** Welcome **** |||");	

			 
//			    String input = scanner.nextInt();

			    try {
			    	while(true) {
			    		   System.out.println("1. Add Todo");
						    System.out.println("2. Update Todo");
						    System.out.println("3. Delete Todo");
						    System.out.println("4. View All Todos");
						    System.out.println("5. Logout");
						    System.out.print("Enter your choice: ");
			    		int choice2 = scanner.nextInt();
			    		scanner.nextLine();
				        switch (choice2) {
				            case 1:
				                System.out.println("\nEnter task :");
				                String task = scanner.nextLine();
				                user.addTodo(pair.left , task);
				                break;
				                
				            case 2:
				            	 System.out.println("Enter todo ID to update: ");
				            	    int todoIdToUpdate = scanner.nextInt();
				            	    scanner.nextLine(); // Consume newline character
				            	    System.out.println("Enter new task: ");
				            	    String newTask = scanner.nextLine();
				            	    System.out.println("Is it completed? (true/false): ");
				            	    boolean isCompleted = scanner.nextBoolean();
				            	    user.updateTodo(todoIdToUpdate, newTask, isCompleted);
				            	    break;
				                
				            case 3:
				                // Handle delete operation
				            	List<StringIntPair> list = user.getAllTodos(pair.left);
				            	System.out.println("Select id of todo you want to delete:");
				            	for (StringIntPair todoPair : list) {
				            	    String taskG = todoPair.getFirst();
				            	    int taskId = todoPair.getSecond();
				            	    System.out.println("Task ID: " + taskId + ", Task: " + taskG);
				            	}
				            	int id = scanner.nextInt();
				            	user.deleteTodo(id);
				            	
				                break;
				                
				            case 4:
				            	list = user.getAllTodos(pair.left);
				            	System.out.println("You have the following todos:");
				            	for (StringIntPair todoPair : list) {
				            	    task = todoPair.getFirst();
				            	    int taskId = todoPair.getSecond();
				            	    System.out.println("Task ID: " + taskId + ", Task: " + task);
				            	}

				            	break;

				                
				            case 5:
				                System.out.println("Logging out...");
				                return; // Exit the main method
				                
				            default:
				                System.out.println("Invalid choice. Please try again.");
				        }
				    }
			    	}
			         catch (NumberFormatException e) {
			        System.out.println("Invalid input. Please enter a number.");
			    }
			}
			else if( choice == 2 ) {
				
				user.registerUser(pair.left, pair.right);
			}
			
			else if ( choice == 3 ) {
				return ;
			}
			else {
				System.out.println("\n||| Invalid Entry try again |||");
			}
			
		}
		
		//DBconnection.closeConnection();
    }
}

