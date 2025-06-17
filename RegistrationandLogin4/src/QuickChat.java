
import javax.swing.*;
import java.util.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class QuickChat {
    private static String username;
    private static String password;
    private static String cellphone;
    private static final List<Message> messages = new ArrayList<>();
    private static int numberOfMessages;

    public static void main(String[] args) {
        registerUser();
        if (loginUser()) {
            JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");
            numberOfMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));
            showMenu();
        }
    }

    private static void registerUser() {
        // Username
        username = JOptionPane.showInputDialog("Enter a username (max 5 characters, must contain an underscore):");
        while (!isValidUsername(username)) {
            username = JOptionPane.showInputDialog("Invalid username. Please enter again (max 5 characters, must contain an underscore):");
        }

        // Password
        password = JOptionPane.showInputDialog("Enter a password (min 8 chars, 1 uppercase, 1 number, 1 special char):");
        while (!isValidPassword(password)) {
            password = JOptionPane.showInputDialog("Invalid password. Please enter again (min 8 chars, 1 uppercase, 1 number, 1 special char):");
        }

        // Cellphone
        cellphone = JOptionPane.showInputDialog("Enter your cellphone number (e.g., +27823456789):");
        while (!isValidCellphone(cellphone)) {
            cellphone = JOptionPane.showInputDialog("Invalid number. Must start with +27 and be 9 characters total (10 digits):");
        }
        JOptionPane.showMessageDialog(null, "Cellphone correctly added.");
        JOptionPane.showMessageDialog(null, "Registration successful!");
    }

    private static boolean isValidUsername(String username) {
        return username != null && username.matches("^(?=.*_).{1,5}$");
    }

    private static boolean isValidPassword(String password) {
        return password != null &&
               password.length() >= 8 &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("[0-9]").matcher(password).find() &&
               Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }

    private static boolean isValidCellphone(String cellphone) {
        return cellphone != null && cellphone.matches("^\\+27[0-9]{9}$");
    }

    private static boolean loginUser() {
        String inputUsername = JOptionPane.showInputDialog("Enter your username:");
        String inputPassword = JOptionPane.showInputDialog("Enter your password:");
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            JOptionPane.showMessageDialog(null, "Login successful!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.");
            return false;
        }
    }

    private static void showMenu() {
        while (true) {
            String input = JOptionPane.showInputDialog("Menu:\n1. Send Messages\n2. Show Recent Messages\n3. Go to Message System(PART 3)\n4. Quit\nEnter your choice:");
            switch (input) {
                case "1":
                    sendMessages();
                    break;
                case "2":
                    MessageDisplay.showMessages(messages);
                    break;
                     case "3":
                MessageSystemJOptionPane.main(null); // Linking the two systems
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice. Please enter 1, 2, 3, or 4.");
        
                case "4":
                    JOptionPane.showMessageDialog(null, "Thank you for using QuickChat!");
                    System.exit(0);
                    break;
               
            }
        }
    }

    private static void sendMessages() {
        if (messages.size() >= numberOfMessages) {
            JOptionPane.showMessageDialog(null, "You have already sent all " + numberOfMessages + " messages.");
            return;
        }

        for (int i = messages.size(); i < numberOfMessages; i++) {
            String recipient = JOptionPane.showInputDialog("Enter recipient for message " + (i + 1) + ":");
            String content = JOptionPane.showInputDialog("Enter your message:");
            int id = i + 1;
            int hash = content.hashCode();
            messages.add(new Message(id, hash, recipient, content));
        }

        JOptionPane.showMessageDialog(null, numberOfMessages + " messages sent successfully.");
    }

    void startChat() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

// Message class
class Message {
    int id;
    int hash;
    String recipient;
    String content;

    public Message(int id, int hash, String recipient, String content) {
        this.id = id;
        this.hash = hash;
        this.recipient = recipient;
        this.content = content;
    }
}

// Separate class for message display
class MessageDisplay {
    public static void showMessages(List<Message> messages) {
        if (messages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages to display.");
            return;
        }

        StringBuilder sb = new StringBuilder("Messages Sent:\n");
        for (Message msg : messages) {
            sb.append("Message ID: ").append(msg.id)
              .append("\nMessage Hash: ").append(msg.hash)
              .append("\nRecipient: ").append(msg.recipient)
              .append("\nMessage: ").append(msg.content)
              .append("\n-------------------\n");
        }
        sb.append("Total number of messages: ").append(messages.size());
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
