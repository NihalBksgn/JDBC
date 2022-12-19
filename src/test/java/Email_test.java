import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Email_test {
    // 1-MedunnaMessageEmailTest
////User connects to the database
//        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
//        Statement statement = JdbcUtils.createStatement();
////User sends the query to get the names of "email" column from "cmessage" table
////Assert that there are some "cmessage" email "zeynep05@gmail.com".
////User closes the connection
//


    @Test

    public void emailTest() {
        JdbcUtils.connectToDataBase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement st = JdbcUtils.createStatement();


        List<Object> list = JdbcUtils.listeEkleme("cmessage", "email");

        Assert.assertTrue(list.contains("zeynep05@gmail.com"));

    }
//User connects to the database
//User sends the query to get the names of "patient_id" column from "appointment" table
//Assert that there are some appointment patient_id "405892".
//Assert verify patients have 20295
//User closes the connection

    @Test
    public void medunnaPatientTest() {
        JdbcUtils.connectToDataBase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
       // JdbcUtils.executeQuery2("appointment", "patient_id =405892", "patient_id");

List<Object> list3 =JdbcUtils.listeEkleme2("appointment","patient_id = 405892","id");
Assert.assertTrue(list3.contains("20295"));
    }
}
