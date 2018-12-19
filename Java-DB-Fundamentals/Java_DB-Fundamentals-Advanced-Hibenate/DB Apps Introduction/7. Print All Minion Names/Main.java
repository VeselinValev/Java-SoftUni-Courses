import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
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
        List<String> minionNames = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(url, props);

            stmt = connection.prepareStatement("SELECT name FROM minions ORDER BY id");
            rs = stmt.executeQuery();
            while (rs.next()){
                minionNames.add(rs.getString("name"));
            }
            for (int i = 0; i < minionNames.size() / 2; i++){
                System.out.println(minionNames.get(i));
                System.out.println(minionNames.get(minionNames.size() - i - 1));
            }
            if (minionNames.size() % 2 != 0){
                System.out.println(minionNames.get(minionNames.size() / 2));
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
