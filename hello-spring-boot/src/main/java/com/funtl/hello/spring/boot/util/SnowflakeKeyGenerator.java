package com.funtl.hello.spring.boot.util;

import org.apache.commons.lang3.RandomUtils;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author qy
 * @date 2020/3/12 16:21
 * @description 雪花算法
 */
public final class SnowflakeKeyGenerator {

    private static final SnowflakeKeyGenerator SNOWFLAKEKEYGENERATOR = new SnowflakeKeyGenerator();

    private static final long EPOCH;

    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_BITS = 10L;

    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

    private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;

    private static final long WORKER_ID = RandomUtils.nextLong(1, WORKER_ID_MAX_VALUE);

    private static final int MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS = 10;


    private byte sequenceOffset;

    private long sequence;

    private long lastMilliseconds;


    private SnowflakeKeyGenerator() {
    }

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
    }


    public synchronized Long generateKey() {
        long currentMilliseconds = System.currentTimeMillis();
        if (waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
            currentMilliseconds = System.currentTimeMillis();
        }
        if (lastMilliseconds == currentMilliseconds) {
            if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
                currentMilliseconds = waitUntilNextTime(currentMilliseconds);
            }
        } else {
            vibrateSequenceOffset();
            sequence = sequenceOffset;
        }
        lastMilliseconds = currentMilliseconds;
        return ((currentMilliseconds - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (getWorkerId() << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }


    private boolean waitTolerateTimeDifferenceIfNeed(final long currentMilliseconds) {
        if (lastMilliseconds <= currentMilliseconds) {
            return false;
        }
        long timeDifferenceMilliseconds = lastMilliseconds - currentMilliseconds;

        if (timeDifferenceMilliseconds < getMaxTolerateTimeDifferenceMilliseconds()) {
            throw new RuntimeException(String.format("Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastMilliseconds, currentMilliseconds));
        }
        try {
            TimeUnit.MILLISECONDS.sleep(timeDifferenceMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private long getWorkerId() {

        return WORKER_ID;
    }

    private int getMaxTolerateTimeDifferenceMilliseconds() {
        return MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS;
    }

    private long waitUntilNextTime(final long lastTime) {
        long result = System.currentTimeMillis();
        while (result <= lastTime) {
            result = System.currentTimeMillis();
        }
        return result;
    }

    private void vibrateSequenceOffset() {
        sequenceOffset = (byte) (~sequenceOffset & 1);
    }


    /**
     * 获取实例
     *
     * @return
     */
    public static SnowflakeKeyGenerator getSnowflakekeygenerator() {
        return SNOWFLAKEKEYGENERATOR;
    }

    public static void main(String[] args) {
        String id = SnowflakeKeyGenerator.getSnowflakekeygenerator().generateKey().toString();
        System.out.println(id);
    }
}
