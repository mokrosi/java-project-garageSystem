import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInDriver {

    private static final String URL = "jdbc:mysql://localhost:3306/car_garage";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static boolean pass = false;

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ShowLogIn();

    }

    public static void ShowLogIn() {

        JFrame frame = new JFrame("Garage Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        // Main
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(60, 63, 65));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(panel);

        // Title
        JLabel titleLabel = new JLabel("Welcome to the Garage");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        // ID
        JLabel idLabel = new JLabel("Employee ID:");
        idLabel.setForeground(Color.WHITE);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(idLabel, gbc);

        JTextField idField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(idField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        // Login Button
        JButton loginButton = new JButton("Log In");
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(loginButton, gbc);

        // Label
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLabel.setForeground(Color.YELLOW);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(statusLabel, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idField.getText();
                char[] passwordArray = passwordField.getPassword();
                String passwordText = new String(passwordArray);

                if (idText.isEmpty() || passwordText.isEmpty()) {
                    statusLabel.setText("Please fill in both fields.");

                }

                try {
                    int id = Integer.parseInt(idText);
                    int password = Integer.parseInt(passwordText);
                    if (checkPassword(id, password)) {
                        // statusLabel.setForeground(Color.GREEN);
                        // statusLabel.setText("Login successful! Welcome.");
                        pass = true;
                        frame.setVisible(false);
                        // TODO program functoin Statr from here
                        JOptionPane.showMessageDialog(null, "Pass");

                    } else {
                        statusLabel.setForeground(Color.RED);
                        statusLabel.setText("Login failed. Check your ID and password.");
                    }
                } catch (NumberFormatException ex) {
                    statusLabel.setForeground(Color.RED);
                    statusLabel.setText("Invalid ID or Password format.");
                }
            }
        });

        frame.setVisible(true);
    }

    // check login
    private static boolean checkPassword(int idCheck, int password) throws NumberFormatException {
        String query = "SELECT * FROM car_garage.employee;";

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection Successful");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("Employee_ID");
                resultSet.getString("Name");

                if (idCheck == id) {

                    System.out.println(" name check Done");

                    int passwordAsk = password;
                    if (passwordAsk == passTeblecheck(id)) {

                        return true;
                    } else {
                        System.out.println("password wrong");
                        break;
                    }

                }

            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    // check if password
    public static int passTeblecheck(int idCheck) {

        // SQL cod ask for table
        String query = "SELECT * FROM car_garage.password;";

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection Successful");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("Employee_ID");
                int password = resultSet.getInt("Password_Hash");

                if (idCheck == id) {

                    System.out.println(" Id check Done");
                    return password;

                }

            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
