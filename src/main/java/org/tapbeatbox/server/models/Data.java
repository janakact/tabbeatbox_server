package org.tapbeatbox.server.models;

/**
 * Created by Janaka on 2016-04-30.
 */
public class Data {
    private double x;
    private double y;
    private double z;
    private int time;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static Data getObject(int time, double x, double y, double z)
    {
        Data d = new Data();
        d.setX(x);
        d.setY(y);
        d.setZ(z);
        d.setTime(time);
        return  d;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
