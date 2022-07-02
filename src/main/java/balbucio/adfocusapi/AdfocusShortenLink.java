package balbucio.adfocusapi;

import java.util.Date;

public class AdfocusShortenLink {

    private String finalurl;
    private String originalurl;
    private int id;
    private long time;

    public AdfocusShortenLink(String finalurl, String originalurl, int id) {
        this.finalurl = finalurl;
        this.originalurl = originalurl;
        this.id = id;
        this.time = new Date().getTime();
    }

    public String getFinalurl() {
        return finalurl;
    }

    public String getOriginalurl() {
        return originalurl;
    }

    public int getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public Date getDate(){
        return new Date(time);
    }
}
