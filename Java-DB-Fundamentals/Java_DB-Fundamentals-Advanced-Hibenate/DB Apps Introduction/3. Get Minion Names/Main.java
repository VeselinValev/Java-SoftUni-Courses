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
        PreparedStatement stmtMinions = null;
        PreparedStatement stmtVillain = null;
        ResultSet rsMinions = null;
        ResultSet rsVillain = null;

        int villainId = Integer.parseInt(reader.readLine());

        try {
            connection = DriverManager.getConnection(url, props);
            stmtMinions = connection.prepareStatement("SELECT m.name, m.age FROM minions AS m\n" +
                    "JOIN minions_villains AS mv\n" +
                    "ON mv.minions_id = m.id\n" +
                    "WHERE mv.villain_id = ?\n" +
                    "ORDER BY m.age;");
            stmtMinions.setInt(1, villainId);

            stmtVillain = connection.prepareStatement("SELECT name FROM villains AS v\n" +
                    "WHERE v.id = ?");
            stmtVillain.setInt(1, villainId);
            rsMinions = stmtMinions.executeQuery();
            rsVillain = stmtVillain.executeQuery();

            if (!rsVillain.first()){
                System.out.printf("No villain with ID %s exists in the database.", villainId);
            }else{
                System.out.printf("Villain: %s%n", rsVillain.getString("name"));
                if (!rsMinions.first()){
                    System.out.println("<no minions>");
                }else{
                    int counter = 1;
                    while (rsMinions.next()){
                        System.out.printf("%s. %s %s%n", counter++, rsMinions.getString("name"), rsMinions.getInt("age"));
                    }
                }
            }

        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }finally{
            if(stmtMinions != null){
                stmtMinions.close();
            }
            if(stmtVillain != null){
                stmtVillain.close();
            }
            if (connection != null){
                connection.close();
            }
        }

    }
}
