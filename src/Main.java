import java.sql.*;


public class Main {


    public static void main(String[] args) throws SQLException {
        DataController dataController = new DataController();
        dataController.updateData("Ankara", "AFG", "Istanbul", 13245, 4097);
        //  DataController.deleteData(4098);


    }


}
