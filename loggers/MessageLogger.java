package loggers;

import appenders.Appender;
import appenders.FileAppender;

import java.util.Map;

public class MessageLogger implements Logger{

    public static enum LogLevel{
        INFO,
        WARNING,
        ERROR,
        CRITICAL,
        FATAL;
    }

    private Map<Appender, LogLevel> appendersWithLevels;

    public MessageLogger(Map<Appender, LogLevel> appendersWithLevels) {
        this.appendersWithLevels = appendersWithLevels;
    }
//    private Appender[] appenders;
//
//    public MessageLogger(Appender... appender) {
//        setAppenders(appender);
//    }
//
//    public void setAppenders(Appender[] appenders) {
//        if (appenders == null || appenders.length < 1) {
//            throw new IllegalArgumentException("Message logger should have at least one appender.");
//        }
//        this.appenders = appenders;
//    }

    @Override
    public void logInfo(String timeStamp, String message) {
        logToAll(timeStamp, LogLevel.INFO, message);
    }

    @Override
    public void logWarning(String timeStamp, String message) {
        logToAll(timeStamp, LogLevel.WARNING, message);
    }

    @Override
    public void logError(String timeStamp, String message) {
        logToAll(timeStamp, LogLevel.ERROR, message);
    }

    @Override
    public void logCritical(String timeStamp, String message) {
        logToAll(timeStamp, LogLevel.CRITICAL, message);
    }

    @Override
    public void logFatal(String timeStamp, String message) {
        logToAll(timeStamp, LogLevel.FATAL, message);
    }

    private void logToAll(String timeStamp, LogLevel logLevel, String message) {
//        for (Appender appender : appenders) {
//            appender.append(timeStamp, logLevel, message);
//        }
        for (Map.Entry<Appender, LogLevel> appenderLogLevelEntry : appendersWithLevels.entrySet()) {
            Appender appender = appenderLogLevelEntry.getKey();
            LogLevel level = appenderLogLevelEntry.getValue();
            if (level.ordinal() <= logLevel.ordinal())
                appender.append(timeStamp, logLevel, message);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Logger info").append(System.lineSeparator());

        for (Map.Entry<Appender, LogLevel> entry : appendersWithLevels.entrySet()) {
            Appender appender = entry.getKey();
            LogLevel logLevel = entry.getValue();
            sb.append(String.format("Appender type: %s, Layout type: %s,",
                    appender.getClass().getSimpleName(), appender.getLayout().getClass().getSimpleName()))
                    .append(String.format(" Report level: %s,", logLevel.toString()))
                    .append(String.format(" Messages appended: %d", appender.getMessageCount()));
            if (appender instanceof FileAppender) {
                sb.append(String.format(", File size: %d", ((FileAppender)appender).getFile().size()));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
