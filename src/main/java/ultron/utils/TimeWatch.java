package ultron.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TimeWatch {

    private final static Logger logger = LoggerFactory.getLogger(TimeWatch.class);

    long start;

    private TimeWatch() {
        reset();
    }

    public static TimeWatch start() {
        return new TimeWatch();
    }

    public TimeWatch reset() {
        start = System.nanoTime();
        return this;
    }

    public long elapse() {
        long end = System.nanoTime();
        return end - start;
    }

    private long time(TimeUnit unit) {
        return unit.convert(elapse(), TimeUnit.NANOSECONDS);
    }

    public void toMinuteSeconds() {
        logger.debug("{} min, {} sec", time(TimeUnit.MINUTES), time(TimeUnit.SECONDS) - time(TimeUnit.MINUTES));
    }
}
