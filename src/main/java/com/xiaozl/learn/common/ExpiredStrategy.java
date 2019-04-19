package com.xiaozl.learn.common;

import java.util.concurrent.TimeUnit;

/**
 * redis 过期策略枚举
 * Created by DK on 2017/11/16.
 */
public enum ExpiredStrategy {
    /**
     * 五分钟
     */
    FIVE_MINUTES(5L, TimeUnit.MINUTES),
    /**
     * 十分钟
     */
    TEN_MINUTES(10L, TimeUnit.MINUTES),
    /**
     * 十五分钟
     */
    FIFTEEN_MINUTES(15L, TimeUnit.MINUTES),
    /**
     * 三十分钟
     */
    THIRTY_MINUTES(30L, TimeUnit.MINUTES),
    /**
     * 一天
     */
    ONE_DAY(1L,TimeUnit.DAYS),
    /**
     *十五天
     */
    FIFTEEN_DAY(15L,TimeUnit.DAYS),

    /**
     * 三十天
     */
    THIRTY_DAY(15L,null),

    /**
     * 永不
     */
    NEVER(0L,TimeUnit.DAYS),

    /**
     * 自定义  不改值 默认一分钟
     */
    CUSTOMIZE(1L,TimeUnit.MINUTES);

    private long value;
    private TimeUnit timeUnit;

    ExpiredStrategy(long value, TimeUnit timeUnit){
        this.value=value;
        this.timeUnit=timeUnit;
    }

    public long getValue() {
        return value;
    }

    public ExpiredStrategy setValue(long value) {
        this.value = value;
        return this;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
