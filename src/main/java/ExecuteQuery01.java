
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = JdbcUtils.connectToDataBase("localhost", "techproed","postgres", "Nihal.2710");
        Statement statement = JdbcUtils.createStatement();

        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.
        String sql1 = "SELECT country_name FROM countries WHERE region_id=1";
        boolean r1 = statement.execute(sql1);
        System.out.println("r1 = " + r1);

        //Recordlari gormek icin ExecuteQuery() methodunu kullanmaliyiz
        ResultSet resultSet1 = statement.executeQuery(sql1);
        while (resultSet1.next()) {

            System.out.println(resultSet1.getString("country_name"));
        }

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
            String sql2 = "select country_id,country_name from countries where region_id>2";
            ResultSet resultSet2 = statement.executeQuery(sql2);
            System.out.println("------------------------------------------");
            while (resultSet2.next()){
                System.out.println(resultSet2.getString("country_name") + "--" + resultSet2.getString("country_id"));

            }


        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.

        String sql3 = "select * from companies where number_of_employees=(select min(number_of_employees) from companies );";
        ResultSet resultSet3 = statement.executeQuery(sql3);
        System.out.println("----------------------");
        while (resultSet3.next()){
            System.out.println(resultSet3.getInt(1)+"---"+ resultSet3.getString(2)+ "---"+ resultSet3.getInt(3));
        }




    }//main
}//class
