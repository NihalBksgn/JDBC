import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techproed", "postgres", "Nihal.2710");
        Statement st = con.createStatement();

     //1.Ornek : number_of_employees degeri ortalama calisan sayisindan az olan number_of_employees degerlerini
     //          16000 olarak UPDATE ediniz

String sql1 = "UPDATE companies\n" +
        "SET number_of_employees=16000\n" +
        "WHERE number_of_employees< (SELECT avg(number_of_employees) FROM companies);";

int updateEdilenSatirSayisi = st.executeUpdate(sql1);
        System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi);

ResultSet resultSet1 = st.executeQuery("SELECT * FROM companies")    ;
     while (resultSet1.next()){
         System.out.println(resultSet1.getInt(1) + "---" + resultSet1.getString(2) + "---" + resultSet1.getInt(3));
     }

     con.close();
     st.close();
    }
}
