import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");
        //2. Adım: Database'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techproed", "postgres", "Nihal.2710");
        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();
        //4. Adim: Query calistir

        /*
        execute methodu DDL(create,drop,alter table) ve DQL(select) icin kullanilabilir.
        1)Eger execute() methodu DDL icin kullanilirsa false return yapar
        2)Eger execute() methodu DQL icin kullanilirsa ResultSet alindiginda 'true' aksi halde 'false' verir
         */
        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        boolean sql1 = st.execute("CREATE TABLE workers(worker_id varchar(20), worker_name varchar(20), worker_salary int)");
        System.out.println("sql1 = " + sql1);//false return eder cunku data cagirmiyoruz


        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        String sql2 = "ALTER TABLE workers ADD worker_address varchar(80)";
        boolean sql4 = st.execute(sql2);
        System.out.println("sql4 = " + sql4);

        //3.Örnek: Drop workers table
        String sql3 = "DROP TABLE workers";
        st.execute(sql3);

        //5. Adim:Baglanti ve Statement'i kapat
        con.close();
        st.close();
    }
}
