import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        int[] minionIds = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> age = new ArrayList<>();
        List<String> minionNames = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(url, props);
            for (int i = 0; i < minionIds.length; i++){
                stmt = connection.prepareStatement("UPDATE minions AS m SET m.age = m.age + 1 WHERE m.id = ?");
                stmt.setInt(1, minionIds[i]);
                stmt.executeUpdate();
                stmt = connection.prepareStatement("UPDATE minions AS m SET m.name = CONCAT(UCASE(LEFT(m.name, 1)), SUBSTRING(m.name, 2)) WHERE m.id = ?");
                stmt.setInt(1, minionIds[i]);
                stmt.executeUpdate();
            }

            stmt = connection.prepareStatement("SELECT name, age FROM minions");
            rs = stmt.executeQuery();
            while (rs.next()){
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
