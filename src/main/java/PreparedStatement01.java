import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techproed", "postgres", "Nihal.2710");
        Statement st = con.createStatement();

        /*
        PreparedStatement interface birden cok kez calistirilabilen onceden derlenmisbir SQL kodunu temsil eder
        Parametrelendirilmis SQL sorgulari ile calisir.Bu sorguyu 0 veya daha fazla parametre ile kullanabiliriz
         */

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //    1.Adim: PreparedStatement query'sini olustur
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ? ";

        //2.Adim PreparedStatement objesini olustur
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3.Adim: setInt(), setString(),.... methodlarini kullanarak soru isaretleri yerlerine  gir
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        //4.Adim: Query'yi calistir
        int updateEdilenSatirSayisi = pst1.executeUpdate();
        System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi);
        ResultSet rs1 = st.executeQuery("Select * from companies");
        while (rs1.next()) {
            System.out.println(rs1.getInt(1) + "---" + rs1.getString(2) + "---" + rs1.getInt(3));
        }

        ////2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");

        int updateEdilenSatirSayisi2 = pst1.executeUpdate();
        System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi2);

        ResultSet rs2 = st.executeQuery("Select * from companies");
        while (rs2.next()) {
            System.out.println(rs2.getInt(1) + "---" + rs2.getString(2) + "---" + rs2.getInt(3));
        }

        pst1.setInt(1, 18000);
        pst1.setString(2, "APPLE");
        int updateEdilenSatirSayisi3 = pst1.executeUpdate();
        System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi3);

        ResultSet rs3  = st.executeQuery("Select * from companies");
        while(rs3.next()){
            System.out.println(rs3.getInt(1) + "---" + rs3.getString(2) + "---" + rs3.getInt(3));
        }

        con.close();
        st.close();
        rs1.close();
        rs2.close();
        pst1.close();
    }
}