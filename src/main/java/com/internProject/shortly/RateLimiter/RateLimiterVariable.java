package com.internProject.shortly.RateLimiter;

public class RateLimiterVariable {

    private String key;


    private double permitsPerSecond;


    private int permits;

    private int period;

    public RateLimiterVariable() {
    }

    public RateLimiterVariable(String key, double permitsPerSecond, int permits, int period) {
        this.key = key;
        this.permitsPerSecond = permitsPerSecond;
        this.permits = permits;
        this.period = period;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getPermitsPerSecond() {
        return permitsPerSecond;
    }

    public void setPermitsPerSecond(double permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    public int getPermits() {
        return permits;
    }

    public void setPermits(int permits) {
        this.permits = permits;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "RateLimiterViriable{" +
                "key='" + key + '\'' +
                ", permitsPerSecond=" + permitsPerSecond +
                ", permits=" + permits +
                ", period=" + period +
                '}';
    }
}
