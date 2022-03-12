package layouts;

import loggers.MessageLogger;

public class SimpleLayout implements Layout{



    public String format(String timeStamp, MessageLogger.LogLevel logLevel, String message) {
        return String.format("%s - %s - %s", timeStamp, logLevel, message);
    }
}
