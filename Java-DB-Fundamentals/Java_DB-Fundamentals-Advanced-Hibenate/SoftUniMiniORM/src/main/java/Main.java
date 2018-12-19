import entities.User;
import orm.persistence.EntityManager;
import orm.persistence.EntityManagerBuilder;
import orm.strategies.schema_init_strategies.DropCreateStrategy;

import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String username = scanner.nextLine().trim();
        String password = scanner.nextLine().trim();
        String adapter = scanner.nextLine().trim();
        String host = scanner.nextLine().trim();
        String port = scanner.nextLine().trim();
        String driver = scanner.nextLine().trim();
        String dbName = scanner.nextLine().trim();

        EntityManagerBuilder emBuilder = new EntityManagerBuilder();
        EntityManager em = emBuilder.configureConnectionString()
                                        .setUser(username)
                                        .setPass(password)
                                        .setAdapter(adapter)
                                        .setDriver(driver)
                                        .setHost(host)
                                        .setPort(port)
                                        .createConnection()
                .configureCreationType().set(DropCreateStrategy.class)
                .setDataSource(dbName)
                .build();

        User user = new User("Nakov", 30, new Date());
        em.persist(user);
    }
}