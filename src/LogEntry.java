import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {

    private final String IP;
    private final LocalDateTime dateTime;
    private final Method method;
    private final String requestPath;
    private final int statusCode;
    private final long size;
    private final String referer;
    private final UserAgent userAgent;

    public LogEntry(String line) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        if (line.length() > 1024) {
            throw new RuntimeException("Длина одной из строк превышает 1024 символам");
        } else {
            this.IP = line.substring(0, line.indexOf(" "));
            this.dateTime = LocalDateTime.parse(line.substring(line
                    .indexOf("[") + 1, line.indexOf("]")), formatter);
            this.method = Method.valueOf(line.substring(line
                    .indexOf("\"") + 1, line
                    .indexOf(" ", line.indexOf("\"") + 1)));
            this.requestPath = line.substring(line
                    .indexOf(" ", line.indexOf("\"") + 1), line
                    .indexOf("\"", line.indexOf("\"") + 1)).trim();
            int lastChar = line.indexOf("\"", line.indexOf("\"") + 1) + 2;
            this.statusCode = Integer.parseInt(line.substring(line.indexOf("\"", line
                    .indexOf("\"") + 1) + 1, line.indexOf(" ", lastChar)).trim());
            this.size = Long.parseLong(line.substring(line.indexOf(" ", lastChar), (line
                    .indexOf("\"", lastChar))).trim());
            int nextLastChar = line.indexOf("\"", line.indexOf("\"", lastChar) + 1);
            this.referer = line.substring(line.indexOf("\"", lastChar) + 1, nextLastChar);
            this.userAgent = new UserAgent(line.substring(nextLastChar + 3, line.length() - 1));
        }
    }

    public String getIP() {
        return IP;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Method getMethod() {
        return method;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long getSize() {
        return size;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}