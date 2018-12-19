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

        String minionTokens = reader.readLine();
        String villainTokens = reader.readLine();
        String townName = minionTokens.split(" ")[3];
        String minionName = minionTokens.split(" ")[1];
        int minionAge = Integer.parseInt(minionTokens.split(" ")[2]);
        String villainName = villainTokens.split(" ")[1];

        try {
            connection = DriverManager.getConnection(url, props);

            stmt = connection.prepareStatement("SELECT t.name FROM towns AS t WHERE t.name = ?");
            stmt.setString(1, townName);
            rs = stmt.executeQuery();

            if (!rs.first()) {
                stmt = connection.prepareStatement("INSERT INTO towns(name) VALUES (?)");
                stmt.setString(1, townName);
                stmt.executeUpdate();
                System.out.printf("Town %s was added to the database.%n", townName);
            }

            stmt = connection.prepareStatement("INSERT INTO minions(name, town_id, age) VALUES (?, (SELECT t.id FROM towns AS t WHERE t.name = ? LIMIT 1), ?)");
            stmt.setString(1, minionName);
            stmt.setString(2, townName);
            stmt.setInt(3, minionAge);
            stmt.executeUpdate();

            stmt = connection.prepareStatement("SELECT v.name FROM villains AS v WHERE v.name = ?");
            stmt.setString(1, villainName);
            rs = stmt.executeQuery();
            if (!rs.first()) {
                stmt = connection.prepareStatement("INSERT INTO villains(name) VALUES (?)");
                stmt.setString(1, villainName);
                stmt.executeUpdate();
                System.out.printf("Villain %s was added to the database.%n", villainName);

            }
            stmt = connection.prepareStatement("INSERT INTO minions_villains(minions_id, villain_id) VALUES " +
                    "((SELECT m.id FROM minions AS m WHERE m.name = ? LIMIT 1), (SELECT v.id FROM villains AS v WHERE v.name = ? LIMIT 1))");
            stmt.setString(1, minionName);
            stmt.setString(2, villainName);
            stmt.executeUpdate();
            System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);

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
