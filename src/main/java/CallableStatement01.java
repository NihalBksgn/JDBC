import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techproed", "postgres", "Nihal.2710");
        Statement st = con.createStatement();

        /*
    Java'da methodlar return type sahibi olsa da olmasa da method olarak adlandırılır.
    SQL'de ise data return ediyorsa "function" denir. Return yapmıyorsa "procedure" olarak adlandırılır.
     */

        //CallableStatement ile function çağırmayı parametrelendireceğiz.
        //1.Adim: Function kodunu yaz
        String sql1 = "CREATE OR REPLACE FUNCTION  toplamaF(x NUMERIC, y NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2.Adim : Function' calistir
        st.execute(sql1);

        //3.Adim: Function'i cagir
        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?, ?)}");

        //4.Adim : Return icin registerOurParameter() methodunu, parametreler icin ise set()... methodlarini uygula
        cst1.registerOutParameter(1,Types.NUMERIC);
        cst1.setInt(2,6);
        cst1.setInt(3,4);

        //5.Adim : execute() methodu ile CallableStatemnt' calistir
        cst1.execute();

        //6.Adim : sonucu cagirmak icin return data type tipini gore
        System.out.println(cst1.getBigDecimal(1));


        ////2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        //1.adim
        String sql2 = "CREATE OR REPLACE FUNCTION  konininHacmiF(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2.Adim : Function' calistir
        st.execute(sql2);

        //3.Adim: Function'i cagir
        CallableStatement cst2 = con.prepareCall("{? = call konininHacmiF(?, ?)}");

        //4.Adim : Return icin registerOurParameter() methodunu, parametreler icin ise set()... methodlarini uygula
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,1);
        cst2.setInt(3,6);

        //5.Adim : execute() methodu ile CallableStatemnt' calistir
        cst2.execute();

        //6.Adim : sonucu cagirmak icin return data type tipini gore
        System.out.printf("%.2f",cst2.getBigDecimal(1));


        String sql3 = "CREATE OR REPLACE FUNCTION  prizmaHacmiF(a NUMERIC, b NUMERIC, c NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN a*b*c;\n" +
                "\n" +
                "END\n" +
                "$$";
        st.execute(sql3);
        CallableStatement cst3 = con.prepareCall("{? = call prizmaHacmiF(?, ?, ? )}");
        cst3.registerOutParameter(1,Types.NUMERIC);
        cst3.setInt(2,1);
        cst3.setInt(3,6);
        cst3.setInt(4,6);
        cst3.execute();
        System.out.println();
        System.out.println(cst3.getBigDecimal(1));

        String sql4 = "CREATE OR REPLACE FUNCTION  birlestirmeF(a VARCHAR, b VARCHAR\n" +
                "RETURNS VARCHAR\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN concat(a,b);\n" +
                "\n" +
                "END\n" +
                "$$";
        st.execute(sql4);
        CallableStatement cst4= con.prepareCall("{? = call birlestirmeF(? , ? )}");
        cst4.registerOutParameter(1,Types.VARCHAR);
        cst4.setString(2,"Ali");
        cst4.setString(3,"Can");
        cst4.execute();
        System.out.println(cst4.getString(1));
    }
}
