package appenders;

import layouts.Layout;
import loggers.MessageLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class XmlAppender extends BaseAppender{

    public XmlAppender(Layout layout) {
        super(layout);
    }

    @Override
    public void append(String timeStamp, MessageLogger.LogLevel logLevel, String message) {
        try {
            File xmlOutput = new File("logs.xml");

            if (!xmlOutput.exists()) {
                xmlOutput.createNewFile();
            }

            Files.writeString(Path.of("logs.xml"), getLayout().format(timeStamp, logLevel, message), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
