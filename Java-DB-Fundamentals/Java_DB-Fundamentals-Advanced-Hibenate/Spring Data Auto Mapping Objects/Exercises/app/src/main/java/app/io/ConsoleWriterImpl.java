package app.io;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriterImpl implements ConsoleWriter {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
