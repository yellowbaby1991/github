package app.yellow.github.bean.home.event;

import com.google.gson.annotations.SerializedName;

public class EventBean {

    /**
     * id : 6083636340
     * type : WatchEvent
     * actor : {"id":25273945,"login":"keyikuaile","display_login":"keyikuaile","gravatar_id":"","url":"https://api.github.com/users/keyikuaile","avatar_url":"https://avatars.githubusercontent.com/u/25273945?"}
     * repo : {"id":86062092,"name":"yellowbaby1991/note","url":"https://api.github.com/repos/yellowbaby1991/note"}
     * payload : {"action":"started"}
     * public : true
     * created_at : 2017-06-16T22:21:21Z
     */

    private String id;
    private String type;
    private ActorBean actor;
    private RepoBean repo;
    private PayloadBean payload;
    @SerializedName("public")
    private boolean publicX;
    private String created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ActorBean getActor() {
        return actor;
    }

    public void setActor(ActorBean actor) {
        this.actor = actor;
    }

    public RepoBean getRepo() {
        return repo;
    }

    public void setRepo(RepoBean repo) {
        this.repo = repo;
    }

    public PayloadBean getPayload() {
        return payload;
    }

    public void setPayload(PayloadBean payload) {
        this.payload = payload;
    }

    public boolean isPublicX() {
        return publicX;
    }

    public void setPublicX(boolean publicX) {
        this.publicX = publicX;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public static class ActorBean {
        /**
         * id : 25273945
         * login : keyikuaile
         * display_login : keyikuaile
         * gravatar_id :
         * url : https://api.github.com/users/keyikuaile
         * avatar_url : https://avatars.githubusercontent.com/u/25273945?
         */

        private int id;
        private String login;
        private String display_login;
        private String gravatar_id;
        private String url;
        private String avatar_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getDisplay_login() {
            return display_login;
        }

        public void setDisplay_login(String display_login) {
            this.display_login = display_login;
        }

        public String getGravatar_id() {
            return gravatar_id;
        }

        public void setGravatar_id(String gravatar_id) {
            this.gravatar_id = gravatar_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }

    public static class RepoBean {
        /**
         * id : 86062092
         * name : yellowbaby1991/note
         * url : https://api.github.com/repos/yellowbaby1991/note
         */

        private int id;
        private String name;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class PayloadBean {
        /**
         * action : started
         */

        private String action;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }
    }
}
