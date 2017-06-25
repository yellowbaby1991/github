package app.yellow.github.data.db;

public class DbBean {

    private String url;

    private String json;

    private String date;

    public DbBean(){

    }

    public DbBean(String url, String json, String date) {
        this.url = url;
        this.json = json;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
