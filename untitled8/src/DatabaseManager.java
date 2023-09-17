import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlserver://localhost\\STUDIA;databaseName=MAS;user=sa;password=Qazwsx12345!;trustServerCertificate=true;";

    private Connection connection;

    public DatabaseManager() {
        establishConnection();
    }

    private void establishConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(User user) {
        String sql = "INSERT INTO users (name, lastName, hireDate, phoneNumber) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastName());
            stmt.setDate(3, java.sql.Date.valueOf(user.getHireDate()));
            stmt.setInt(4, user.getPhoneNumber());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveGroup(Group group) {
        String sql = "INSERT INTO groups (name) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, group.getName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT DISTINCT name, lastName, hireDate, phoneNumber FROM users";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                Date hireDate = rs.getDate("hireDate");
                int phoneNumber = rs.getInt("phoneNumber");

                User user = new User(name, lastName, hireDate.toLocalDate(), phoneNumber);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }
    public List<Group> getGroups() {
        List<Group> groupList = new ArrayList<>();

        String sql = "SELECT DISTINCT name FROM groups";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("name");
                Group group = new Group(name);
                groupList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupList;
    }
}