import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/minionsdb";
        String user = "root";
        String password = "";

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, props);
            String retrieveData = "SELECT v.name, COUNT(mv.villain_id) AS number_of_minions FROM villains AS v, minions_villains AS mv\n" +
                    "WHERE v.id = mv.villain_id\n" +
                    "GROUP BY v.id\n" +
                    "HAVING number_of_minions > 3\n" +
                    "ORDER BY number_of_minions DESC;";
            stmt = connection.prepareStatement(retrieveData);
            rs = stmt.executeQuery();

            while (rs.next()){
                System.out.printf("%s %s%n", rs.getString("name"), rs.getInt("number_of_minions"));
            }
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }finally{
            if(stmt != null){
                stmt.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }
}
