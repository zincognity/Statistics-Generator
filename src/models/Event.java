package src.models;

public class Event {
    private int date;
    private int time;
    private double latitude;
    private double longitute;
    private double depth;
    private double magnitude;
    private int cutoff_date;

    // Constructor
    public Event(int date, int time, double latitude, double longitute, double depth, double magnitude, int cutoff_date){
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longitute = longitute;
        this.depth = depth;
        this.magnitude = magnitude;
        this.cutoff_date = cutoff_date;
    }

    // Getters
    public int getDate(){
        return date;
    }

    public int getTime(){
        return time;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitute;
    }

    public double getDepth(){
        return depth;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public int getCutoff_date(){
        return cutoff_date;
    }
}
