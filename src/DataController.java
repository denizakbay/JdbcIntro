import java.sql.*;
import java.util.ArrayList;

public class DataController {
    private Connection connection;
    private dbHelper helper;
    private Statement statement;
    private ResultSet resultSet;

    public DataController() {

        helper = new dbHelper();
        statement = null;
        resultSet = null;
        try {
            connection = helper.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void selectData() throws SQLException {


        try {

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

    public void insertData() throws SQLException {
        PreparedStatement statement = null;

        try {

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

    public void updateData(String name, String code, String district, int population, int id) throws SQLException {
        PreparedStatement statement = null;
        try {
            //Sql hazırla..
            String sql = "UPDATE city set Name=?,CountryCode=?,District=?,Population=? where id=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, code);
            statement.setString(3, district);
            statement.setInt(4, population);
            statement.setInt(5, id);
            int result = statement.executeUpdate();//etkilenen kayit sayisi güncellendi..
            System.out.println("Kayit Guncellendi");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);

        } finally {
            if (connection != null && statement != null) {
                connection.close();
                statement.close();
            }

        }


    }

    public void deleteData() throws SQLException {

        PreparedStatement statement = null;
        try {
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
