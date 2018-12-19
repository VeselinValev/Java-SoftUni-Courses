import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

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

        String countryName = reader.readLine();
        List<String> townNamesToCapitalize = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, props);

            stmt = connection.prepareStatement("SELECT t.name FROM towns AS t WHERE t.country = ?");
            stmt.setString(1, countryName);
            rs = stmt.executeQuery();

            while (rs.next()){
                String currentTownName = rs.getString("name");
                if (!currentTownName.chars().allMatch(Character::isUpperCase)){
                    townNamesToCapitalize.add(currentTownName);
                }
            }
            if (townNamesToCapitalize.isEmpty()){
                System.out.println("No town names were affected.");
            }else{
                for (String town : townNamesToCapitalize) {
                    String capitalizedTownName = town.toUpperCase();
                    stmt = connection.prepareStatement("UPDATE towns SET name = ? WHERE name = ?");
                    stmt.setString(1, capitalizedTownName);
                    stmt.setString(2, town);
                    stmt.executeUpdate();
                }
                System.out.printf("%s town names were affected.%n", townNamesToCapitalize.size());
                System.out.println(townNamesToCapitalize.stream().map(String::toUpperCase).collect(Collectors.toList()).toString());
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
