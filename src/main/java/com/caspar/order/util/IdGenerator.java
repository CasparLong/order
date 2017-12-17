package com.caspar.order.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Description:分布式唯一数字生成器
 *
 * @author Caspar
 * @Date 2017-12-16
 */
@Slf4j
public class IdGenerator {

    private long workerId;
    private long datacenterId;
    private long sequence = 0L;

    private static long twepoch = 1288834974657L;

    private static long workerIdBits = 5L;
    private static long datacenterIdBits = 5L;
    private static long maxWorkerId = -1L ^ (-1L << (int) workerIdBits);
    private static long maxDatacenterId = -1L ^ (-1L << (int) datacenterIdBits);
    private static long sequenceBits = 12L;

    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << (int) sequenceBits);

    private long lastTimestamp = -1L;
    private static Object syncRoot = new Object();

    private static Random random = new Random();

    private IdGenerator(long workerId, long datacenterId) {

        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new RuntimeException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new RuntimeException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    private long nextId() {
        synchronized (syncRoot) {
            long timestamp = timeGen();

            if (timestamp < lastTimestamp) {
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            }

            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & sequenceMask;
                if (sequence == 0) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0L;
            }

            lastTimestamp = timestamp;

            return ((timestamp - twepoch) << (int) timestampLeftShift) | (datacenterId << (int) datacenterIdShift) | (workerId << (int) workerIdShift) | sequence;
        }
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 生成唯一随机long型数字
     *
     * @return
     */
    public static Long generate() {
        IdGenerator idGenerator = new IdGenerator(random.nextInt(30), random.nextInt(30));
        try {
            //算法中加入了时间戳，sleep 5毫秒确保唯一
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error("IdGenerator生成唯一id时sleep被打断", e);
        }
        return idGenerator.nextId();
    }
}
