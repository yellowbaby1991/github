package app.yellow.github.config;

public class GithubConfig {

    // client id/secret
    public static final String CLIENT_ID = "5b074b14a3c166278774";

    public static final String CLIENT_SECRET = "a2c539a256cd861cd9dcc5c86e51a53baf4c96a5";

    // scopes
    public static final String[] SCOPES = {"user", "repo", "notifications", "gist", "admin:org"};

    public static final String NOTE = "GithubApp";

    public static final int PER_PAGE = 30;

    //1000 * 30 * 60
    public static final long CAHE_TIME = 0;//默认缓存事件  数据库缓存设置的过期时间，后来发现使用retrofit的缓存要更加方便，于是废弃了数据库缓存

    public static final String CACHE_HEADER = "Cache-Control: public, max-age=1800";

}