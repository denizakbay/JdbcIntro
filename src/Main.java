import java.sql.*;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws SQLException {
        updateData();

    }

    public static void selectData() throws SQLException {
        Connection connection = null;
        dbHelper helper = new dbHelper();
        Statement statement = null;//sql cümleciği
        ResultSet resultSet;//sonuçlar topluluğu
        try {
            connection = helper.getConnection();
            statement = connection.createStatement();//Bu bağlantı için..
            resultSet = statement.executeQuery("SELECT code,name,Continent,region FROM world.country\n");
            ArrayList<Country> countries = new ArrayList<Country>();
            while (resultSet.next()) {
                countries.add(new Country(
                        resultSet.getString("code"),
                        resultSet.getString("name"),
                        resultSet.getString("continent"),
                        resultSet.getString("region")));
            }
            System.out.println(countries.size());
            for (Country countrie : countries) {
                System.out.println(countrie.toString());
            }

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);

        } finally {
            if (connection != null) {
                connection.close();
            }
        }


    }

    public static void insertData() throws SQLException {
        Connection connection = null;
        dbHelper helper = new dbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            //Sql hazırla..
            String sql = "INSERT into world.city(Name,CountryCode,District,Population) values(?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "Istanbul");
            statement.setString(2, "TUR");
            statement.setString(3, "Turkey");
            statement.setInt(4, 2000000);
            int result = statement.executeUpdate();
            System.out.println("Kayit Eklendi");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);

        } finally {
            statement.close();
            connection.close();
        }

    }

    public static void updateData() throws SQLException {
        Connection connection = null;
        dbHelper helper = new dbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            //Sql hazırla..
            String sql = "UPDATE city set Name=?,CountryCode=?,District=?,Population=? where id=4097";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "Ankara");
            statement.setString(2, "Tur");
            statement.setString(3, "ankara");
            statement.setInt(4, 900000);
            int result = statement.executeUpdate();//etkilenen kayit sayisi güncellendi..
            System.out.println("Kayit Guncellendi");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);

        } finally {
            statement.close();
            connection.close();
        }


    }

    public static void deleteData() throws SQLException {

        Connection connection = null;
        dbHelper helper = new dbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "DELETE from city where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 4096);
            int result = statement.executeUpdate();
            System.out.println("Kayit silindi");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);

        } finally {
            statement.close();
            connection.close();
        }

    }
}
