package org.tapbeatbox.server.models;

/**
 * Created by Janaka on 2016-04-30.
 */
public class Data {
    private double value;
    private int time;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static Data getObject(int time, double value)
    {
        Data d = new Data();
        d.setValue(value);
        d.setTime(time);
        return  d;
    }
}
