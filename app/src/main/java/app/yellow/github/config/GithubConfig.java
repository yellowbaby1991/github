package app.yellow.github.config;

public class GithubConfig {

    // client id/secret
    public static final String CLIENT_ID = "5b074b14a3c166278774";

    public static final String CLIENT_SECRET = "a2c539a256cd861cd9dcc5c86e51a53baf4c96a5";

    // scopes
    public static final String[] SCOPES = {"user", "repo", "notifications", "gist", "admin:org"};

    public static final String NOTE = "GithubApp";

    public static final int PER_PAGE = 30;

    public static final long CAHE_TIME = 1000 * 30 * 60;//默认缓存事件

}