package balbucio.adfocusapi;

public class InvalidShortenException extends Exception {

    private String url;
    private String message;

    public InvalidShortenException(String url, String message){
        super(message);
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
