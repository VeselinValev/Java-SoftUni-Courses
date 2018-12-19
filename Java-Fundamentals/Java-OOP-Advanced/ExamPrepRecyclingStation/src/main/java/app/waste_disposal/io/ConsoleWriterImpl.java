package app.waste_disposal.io;

import app.waste_disposal.contracts.Writer;

public class ConsoleWriterImpl implements Writer {
    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
