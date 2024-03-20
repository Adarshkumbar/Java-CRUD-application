package FirstPackage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import org.apache.commons.lang3.tuple.Pair;
import java.util.List;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

public class UserDAO {
    // Other methods for CRUD operations
    
    public void registerUser(String email, String password) {
        String sql = "INSERT INTO user (email, password) VALUES (?, ?)";
        
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            
            preparedStatement.executeUpdate();
            
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            System.err.println("Error registering user: " + e.getMessage());
        }
    }
    
    public boolean loginUser(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Incorrect username or password. Please try again.");
            }
        } catch (SQLException e) {
            System.err.println("Error logging in: " + e.getMessage());
        }
        return false;
    }
    
    public void addTodo(String email, String task) {
        String sql = "INSERT INTO todos (email, task) VALUES (?, ?)";
        
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, task);
            
            preparedStatement.executeUpdate();
            
            System.out.println("To-do added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding to-do: " + e.getMessage());
        }
    }
    
    public void updateTodo(int todoId, String task, boolean completed) {
        String sql = "UPDATE todos SET task = ?, completed = ? WHERE todo_id = ?";
        
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, task);
            preparedStatement.setBoolean(2, completed);
            preparedStatement.setInt(3, todoId);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("To-do updated successfully!");
            } else {
                System.out.println("To-do not found or no changes made.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating to-do: " + e.getMessage());
        }
    }
    
    public void deleteTodo(int todoId) {
        String sql = "DELETE FROM todos WHERE todo_id = ?";
        
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, todoId);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("To-do deleted successfully!");
            } else {
                System.out.println("To-do not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting to-do: " + e.getMessage());
        }
    }
   
    public List<StringIntPair> getAllTodos(String email) {
        List<StringIntPair> todoList = new ArrayList<>();
        String sql = "SELECT task, todo_id FROM todos WHERE email = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String task = resultSet.getString("task");
                int taskId = resultSet.getInt("todo_id");
                todoList.add(new StringIntPair(task, taskId));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving todos: " + e.getMessage());
        }

        return todoList;
    }


}
