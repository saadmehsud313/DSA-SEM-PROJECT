import java.io.*;
import java.nio.BufferUnderflowException;
import java.util.*;

public class DataLoader {
    public static AVLTree<UserNode> loadUserData(String filename) {
        AVLTree<UserNode> tree = new AVLTree<>();
        filename="D:\\JAVA Programs\\IntelliJ\\DSA SEM PROJECT\\src\\"+filename;
        File file = new File(filename);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0].trim());
                    String username = parts[1].trim();
                    String password = parts[2].trim();
                    tree.insertNode(new UserNode(id, username, password));
                } catch (Exception e) {
                    System.out.println("Skipping invalid line in " + filename + ": " + line);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error Opening File.");
            System.out.println("Error"+e.getMessage());
        }


        return tree;
    }

    public static AVLTree<AccountNode> loadAccountData(String filename) {
        AVLTree<AccountNode> tree = new AVLTree<>();
        filename="D:\\JAVA Programs\\IntelliJ\\DSA SEM PROJECT\\src\\"+filename;
        System.out.println("File Name"+filename);
        File file = new File(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0].trim());
                    int pin = Integer.parseInt(parts[1].trim());
                    String username = parts[2].trim();
                    String password = parts[3].trim();
                    String type = parts[4].trim();
                    float balance = Float.parseFloat(parts[5].trim());
                    String status = parts[6].trim();
                    tree.insertNode(new AccountNode(id, username, password, status, type, balance, pin));
                } catch (Exception e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error opening file: " + filename);
        }
        return tree;
    }
}
