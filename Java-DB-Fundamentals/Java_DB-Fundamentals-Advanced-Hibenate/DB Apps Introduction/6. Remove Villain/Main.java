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

        int villainId = Integer.parseInt(reader.readLine());

        try {
            connection = DriverManager.getConnection(url, props);

            stmt = connection.prepareStatement("SELECT v.name FROM villains AS v WHERE v.id = ?");
            stmt.setInt(1, villainId);
            rs = stmt.executeQuery();
            String villainName = "";
            if (rs.first()){
                villainName = rs.getString("name");
                stmt = connection.prepareStatement("DELETE FROM villains WHERE name = ?");
                stmt.setString(1, villainName);
                stmt.executeUpdate();
                System.out.println(villainName + " was deleted");

                stmt = connection.prepareStatement("SELECT COUNT(minions_id) AS minions_released FROM minions_villains WHERE villain_id = ?");
                stmt.setInt(1, villainId);
                rs = stmt.executeQuery();
                int minionsReleased = 0;
                if (rs.first()){
                    minionsReleased = rs.getInt("minions_released");
                    stmt = connection.prepareStatement("DELETE FROM minions_villains WHERE villain_id = ?");
                    stmt.setInt(1, villainId);
                    stmt.executeUpdate();
                }
                System.out.println(minionsReleased + " minions released");
            }else{
                System.out.println("No such villain was found");
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
