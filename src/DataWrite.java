import java.sql.*;

public class DataWrite {

    private static final String URL = "jdbc:mysql://localhost:3306/car_garage";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // database connection || check Done
    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection Successful ");
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection Failed ");
            e.printStackTrace();
            return null;
        }

    }

    // add customar || check Done

    public static void addCustomer(int Customer_ID, String name, String phone) throws SQLException {
        String query = "INSERT INTO customer (Customer_ID ,name, phone) VALUES (?, ?, ?)";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Customer_ID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phone);
            preparedStatement.executeUpdate();

            System.out.println("Customer added successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to add customer ");
            e.printStackTrace();
        }
    }

    // add department || check Done
    public static void addDepartment(int ID, String name) throws SQLException {
        String query = "INSERT INTO department (Dep_ID, name ) VALUES (?, ?)";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, ID);

            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();

            System.out.println("Department added successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to add Department ");
            e.printStackTrace();
        }
    }

    // add employee || check Done (ID is plate number)
    public static void addEmployee(int Employee_ID, String name, String phone, int Dep_ID) throws SQLException {
        String query = "INSERT INTO employee (Employee_ID ,name, phone ,Dep_ID) VALUES (?, ?, ?, ?)";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Employee_ID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phone);
            preparedStatement.setInt(4, Dep_ID);
            preparedStatement.executeUpdate();

            System.out.println("Employee added successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to add Employee ");
            e.printStackTrace();
        }
    }

    // add service || check Done
    public static void addService(int Service_ID, String name, int Dep_ID) throws SQLException {
        String query = "INSERT INTO service  (Service_ID ,name,Dep_ID) VALUES (?, ?, ?)";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Service_ID);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, Dep_ID);
            preparedStatement.executeUpdate();

            System.out.println("Service added successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to add Service ");
            e.printStackTrace();
        }
    }

    // add vihicle || check Done
    public static void addVehicle(int Vehicle_ID, String Brand, String Model, int Customer_ID) throws SQLException {
        String query = "INSERT INTO vehicle (Vehicle_ID ,Brand, Model ,Customer_ID) VALUES (?, ?, ?, ?)";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Vehicle_ID);
            preparedStatement.setString(2, Brand);
            preparedStatement.setString(3, Model);
            preparedStatement.setInt(4, Customer_ID);
            preparedStatement.executeUpdate();

            System.out.println("Vehicle added successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to add Vehicle ");
            e.printStackTrace();
        }
    }

    // add Bill || check Done
    public static void addBill(int Bill_ID, int Cost, int Service_ID) throws SQLException {
        String query = "INSERT INTO bill  (Bill_ID ,Cost,Service_ID) VALUES (?, ?, ?)";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Bill_ID);
            preparedStatement.setInt(2, Cost);
            preparedStatement.setInt(3, Service_ID);
            preparedStatement.executeUpdate();

            System.out.println("Service added successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to add Service ");
            e.printStackTrace();
        }
    }

    public static void setPassword(int Employee_ID, int Password_Hash) throws SQLException {
        String query = "INSERT INTO password (Employee_ID, Password_Hash ) VALUES (?, ?)";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, Employee_ID);

            preparedStatement.setInt(2, Password_Hash);

            preparedStatement.executeUpdate();

            System.out.println("password set successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to set password ");
            e.printStackTrace();
        }
    }

}
