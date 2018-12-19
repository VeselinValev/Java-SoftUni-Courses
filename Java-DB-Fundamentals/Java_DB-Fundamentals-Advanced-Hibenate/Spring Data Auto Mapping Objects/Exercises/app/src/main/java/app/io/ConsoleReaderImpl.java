package app.io;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ConsoleReaderImpl implements ConsoleReader {

    BufferedReader reader;

    public ConsoleReaderImpl() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));   ;
    }

    @Override
    public String readLine() throws IOException {
        return reader.readLine();
    }
}
