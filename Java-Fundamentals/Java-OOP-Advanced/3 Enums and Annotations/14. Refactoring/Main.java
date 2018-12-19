import contracts.ConsoleReader;
import contracts.ConsoleWriter;
import contracts.Repository;
import contracts.Weapon;
import engine.Engine;
import io.ConsoleReaderImpl;
import io.ConsoleWriterImpl;
import repositories.WeaponRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ConsoleWriter writer = new ConsoleWriterImpl();
        ConsoleReader reader1 = new ConsoleReaderImpl();
        Repository<Weapon> allWeapons = new WeaponRepository();
        Engine engine = new Engine(allWeapons, writer, reader1);
        engine.run();
    }
}
