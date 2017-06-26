package app.yellow.github.data.db;

import org.litepal.crud.DataSupport;

public class KeyJsonBean extends DataSupport {

    private String key;

    private String json;

    private String date;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
