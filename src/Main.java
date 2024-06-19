import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/Testing","root","hellomysql");

            // 1. CREATE TABLE Product
            String query = "CREATE TABLE Product(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), " +
                    "price_per_unit DOUBLE, active_for_sell BIT);"; // query for create table Product

            Statement statement = con.createStatement();
            statement.execute(query);

            // 2. SELECT ALL Products
            statement =con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); // create statement

            String select = "SELECT * FROM Product;";

            ResultSet rs = statement.executeQuery(select);

            rs.moveToInsertRow();
            rs.updateString("name", "Coke");
            rs.updateDouble("price_per_unit", 2.00);
            rs.updateBoolean("active_for_sell", true);
            rs.insertRow();

            rs.updateString("name", "Pepis");
            rs.updateDouble("price_per_unit", 2.00);
            rs.updateBoolean("active_for_sell", true);
            rs.insertRow();

            rs.updateString("name", "Kizz");
            rs.updateDouble("price_per_unit", 1.50);
            rs.updateBoolean("active_for_sell", true);
            rs.insertRow();

            rs.updateString("name", "Redbull");
            rs.updateDouble("price_per_unit", 2.00);
            rs.updateBoolean("active_for_sell", false);
            rs.insertRow();
            rs.moveToCurrentRow();

            System.out.println(String.format("%-5s %-10s %-10s %-10s", "Id", "Name", "price_per_unit", "active_for_sell"));
            while (rs.next()){
                System.out.println(String.format("%-5s %-10s %-14s %-10s", rs.getInt(1),
                        rs.getString(2), rs.getDouble(3), rs.getBoolean(4)));
            }

            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}