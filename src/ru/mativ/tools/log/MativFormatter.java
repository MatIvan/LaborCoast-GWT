package ru.mativ.tools.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MativFormatter extends Formatter {
    private static final int SOURCE_NAME_SIZE = 40;
    private static final String POSTFIX_FORMAT = "%5$s %n";
    private static final String FORMAT = "[%1$tF %1$tT] [%2$-7s] [%3$-" + SOURCE_NAME_SIZE + "." + SOURCE_NAME_SIZE + "s] %4$s %n";

    private final Date dat = new Date();

    @Override
    public synchronized String format(LogRecord record) {
        String format = FORMAT;
        dat.setTime(record.getMillis());

        String source = trim(record.getLoggerName());

        String message = formatMessage(record);

        String throwable = "";
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStackTrace(pw);
            pw.close();
            throwable = sw.toString();
            format += POSTFIX_FORMAT;
        }

        return String.format(format,
                dat,
                record.getLevel().getName(),
                source,
                message,
                throwable);
    }

    private synchronized String trim(String str) {
        if (str.length() <= SOURCE_NAME_SIZE) {
            return str;
        }
        String result = str;

        int part = 0;
        String sub = "";
        while (result.length() > SOURCE_NAME_SIZE) {
            sub = trimPart(part, result);
            if (sub.equals(result)) {
                break;
            }
            result = sub;
            part++;
        }
        return result;
    }

    private synchronized String trimPart(int partNumber, String str) {
        int off = 0;
        for (int i = 0; i < partNumber; i++) {
            off = str.indexOf(".", off) + 1;
        }

        int nextDot = str.indexOf(".", off);
        if (nextDot < 0) {
            nextDot = off + 1;
        }
        return str.substring(0, off + 1) + str.substring(nextDot);
    }

}
