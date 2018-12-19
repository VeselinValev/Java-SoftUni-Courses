import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = "jdbc:mysql://localhost:3306/minionsdb";
        String user = "root";
        String password = "";

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int minionId = Integer.parseInt(reader.readLine());
        try {
            connection = DriverManager.getConnection(url, props);
            stmt = connection.prepareStatement("CALL usp_get_older(?)");
            stmt.setInt(1, minionId);
            stmt.executeUpdate();

            stmt = connection.prepareStatement("SELECT m.name, m.age FROM minions AS m WHERE m.id = ?");
            stmt.setInt(1, minionId);
            rs = stmt.executeQuery();
            if (rs.first()){
                System.out.printf("%s %s%n", rs.getString("name"), rs.getInt("age"));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            if (rs != null){
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }
}
