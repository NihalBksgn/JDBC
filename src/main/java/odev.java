import java.sql.Statement;

public class odev {
    public static void main(String[] args) {
        JdbcUtils.connectToDataBase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement st = JdbcUtils.createStatement();


// 1-MedunnaMessageEmailTest
////User connects to the database
//        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
//        Statement statement = JdbcUtils.createStatement();
////User sends the query to get the names of "email" column from "cmessage" table
////Assert that there are some "cmessage" email "zeynep05@gmail.com".
////User closes the connection
//

    }
}
