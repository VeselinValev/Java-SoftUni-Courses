package io;

import contracts.ConsoleWriter;

public class ConsoleWriterImpl implements ConsoleWriter {
    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
