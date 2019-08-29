package kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class ProducerConsumerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProducerConsumerApplication.class, args);
    }

    @Autowired
    private Producer sender;

    @Override
    public void run(String... strings) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kafka", "root", "rootroot");
        String query = "SELECT * FROM queries";

        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(query);

        while(rs.next()) {
            String output = rs.getString("url");
            System.out.println("sent this: " + output);
            sender.send(output);
        }

        st.close();
//        sender.send("Spring Kafka Producer and Consumer Example");
//        sender.send("another message");
    }
}