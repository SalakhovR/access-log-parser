import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Statistics {

    static int totalTraffic;
    static LocalDateTime maxTime;
    static LocalDateTime minTime;

    public Statistics() {
        this.totalTraffic = 0;
        this.maxTime = LocalDateTime.MIN;
        this.minTime = LocalDateTime.MAX;
    }

    public static void addEntry(LogEntry logEntry) {
        totalTraffic += logEntry.getSize();
        if (maxTime.compareTo(logEntry.getDateTime()) < 0) maxTime = logEntry.getDateTime();
        if (minTime.compareTo(logEntry.getDateTime()) > 0) minTime = logEntry.getDateTime();
    }

    public static void getTrafficRate() {
        long hoursDifference = maxTime.until(minTime, ChronoUnit.HOURS);
        System.out.println("Средний объем трафика за час: " + (totalTraffic / hoursDifference));
    }
}
