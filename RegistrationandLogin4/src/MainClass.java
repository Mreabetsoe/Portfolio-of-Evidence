
/*POE PART 3*/

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;


public class MainClass {
    public static void main(String[] args) {
        // Call the main method of MessageSystemJOptionPane
        QuickChat chat = new QuickChat();
        
        chat.startChat();
    }
}


class Message {
    String recipient;
    String message;
    String flag;
    String developer;
    String id;
    int hash;

    public Message(String recipient, String message, String flag, String developer, String id) {
        this.recipient = recipient;
        this.message = message;
        this.flag = flag;
        this.developer = developer;
        this.id = id;
        this.hash = message.hashCode();
    }

    public String getSender() {
        return developer != null ? developer : "SYSTEM";
    }

    public String toString() {
        return "ID: " + id + " | Recipient: " + recipient + " | Message: " + message + " | Flag: " + flag + " | Hash: " + hash;
    }
}


class  MessageSystemJOptionPane{

    static ArrayList<Message> sentMessages = new ArrayList<>();
    static ArrayList<Message> storedMessages = new ArrayList<>();
    static ArrayList<Message> disregardedMessages = new ArrayList<>();

    public static void main(String[] args) {
        loadTestMessages();
        showMenu();
    }

    static void loadTestMessages() {
        sentMessages.add(new Message("+27834557896", "Where are you? You are late! I have asked you to be on time.", "Sent", null, "0000"));
        storedMessages.add(new Message("+27838884567", "Did you get the cake?", "Stored", null, "0001"));
        sentMessages.add(new Message(null, "It is dinner time !", "Sent", "0838884567", "0003"));
        disregardedMessages.add(new Message("+27834484567", "Yohoooo, I am at your gate.", "Disregard", null, "0002"));
        storedMessages.add(new Message("+27838884567", "Ok, I am leaving without you.", "Stored", null, "0004"));
    }

    static void showMenu() {
        String[] options = {
                "1. Display Sent Messages",
                "2. Show Longest Message",
                "3. Search Message by ID",
                "4. Show Messages by Recipient",
                "5. Delete Message by Hash",
                "6. Display Message Report",
                "7. Exit"
        };

        while (true) {
            String choice = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose an option:",
                    "Message System Menu",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == null || choice.contains("7")) break;

            switch (choice.charAt(0)) {
                case '1':
                    displaySentMessages();
                    break;
                case '2':
                    displayLongestMessage();
                    break;
                case '3':
                    String id = JOptionPane.showInputDialog("Enter Message ID:");
                    searchByMessageID(id);
                    break;
                case '4':
                    String recipient = JOptionPane.showInputDialog("Enter recipient number:");
                    searchByRecipient(recipient);
                    break;
                case '5':
                    String message = JOptionPane.showInputDialog("Enter the full message to delete (by content):");
                    deleteByHash(message);
                    break;
                case '6':
                    displayReport();
                    break;
            }
        }
    }

    static void displaySentMessages() {
        StringBuilder result = new StringBuilder("--- Sent Messages ---\n");
        for (Message msg : sentMessages) {
            result.append("Sender: ").append(msg.getSender())
                  .append(" | Recipient: ").append(msg.recipient).append("\n");
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }

    static void displayLongestMessage() {
        Message longest = null;
        for (Message msg : sentMessages) {
            if (longest == null || msg.message.length() > longest.message.length()) {
                longest = msg;
            }
        }
        JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest.message);
    }

    static void searchByMessageID(String id) {
        for (Message msg : sentMessages) {
            if (msg.id.equals(id)) {
                JOptionPane.showMessageDialog(null,
                        "Message Found!\nRecipient: " + msg.recipient + "\nMessage: " + msg.message);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message ID not found.");
    }

    static void searchByRecipient(String recipient) {
        StringBuilder result = new StringBuilder("--- Messages for " + recipient + " ---\n");
        boolean found = false;
        for (Message msg : storedMessages) {
            if (recipient.equals(msg.recipient)) {
                result.append(msg.message).append("\n");
                found = true;
            }
        }
        if (found) {
            JOptionPane.showMessageDialog(null, result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No messages found for recipient.");
        }
    }

    static void deleteByHash(String msgText) {
        int hash = msgText.hashCode();
        Iterator<Message> iterator = storedMessages.iterator();
        while (iterator.hasNext()) {
            Message msg = iterator.next();
            if (msg.hash == hash) {
                iterator.remove();
                JOptionPane.showMessageDialog(null, "Message successfully deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message successfully deleted.");
    }

    static void displayReport() {
        StringBuilder report = new StringBuilder("--- Sent Messages Report ---\n");
        for (Message msg : sentMessages) {
            report.append(msg.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    void showMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

    
       

