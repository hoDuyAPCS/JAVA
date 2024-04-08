import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//installing jdbc driver
//make sure sql server and sql server browser is active


public class Test extends JFrame {

    private JTextField usernameField, passwordField, emailField;
    private JButton submitButton;

    public void UserForm() {
        setTitle("User Form");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        submitButton = new JButton("Submit");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(submitButton);

        add(panel);

//        submitButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                createUser();
//            }
//        });
    }

    public static void main(String[] args){
        String url = "jdbc:sqlserver://DESKTOP-PV4B9UK\\SQLEXPRESS;databaseName=test;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "776195A";

        FormFrame formFrame = new FormFrame();
        formFrame.showForm();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Connection successful, do something...
            System.out.println("Connected to the database!");
            Statement stmt = conn.createStatement();

            // SELECT * FROM Users
            // DELETE FROM Users WHERE ID = 4
            // INSERT INTO Users (ID, Name, Age, Email) VALUES (4, 'Bob Brown', 28, 'bob@example.com')

            String test = "DELETE FROM Users WHERE ID = 4";
            stmt.executeUpdate(test);


            String sql = "SELECT * FROM Users";
            ResultSet rs = stmt.executeQuery(sql);
            // Process the results
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }

        } catch (SQLException e) {
            // Connection failed, handle the exception...
            e.printStackTrace();
        }
    }
}

class FormFrame extends JFrame {
    public FormFrame() {
        super("Simple Form Example"); // Set the title of the JFrame
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // We'll set the positions manually
    }

    public void showForm() {
        // Create labels
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 50, 80, 25);

        // Create text fields
        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(100, 20, 165, 25);
        JTextField emailTextField = new JTextField();
        emailTextField.setBounds(100, 50, 165, 25);

        // Create a button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(100, 90, 80, 25);

        // Add components to the frame
        add(nameLabel);
        add(emailLabel);
        add(nameTextField);
        add(emailTextField);
        add(submitButton);

        setVisible(true); // Make the frame visible
    }
}


