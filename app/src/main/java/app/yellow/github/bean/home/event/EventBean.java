package app.yellow.github.bean.home.event;

import java.util.List;

public class EventBean {

    private String type;
    private RepoBean repo;
    private PayloadBean payload;
    private String created_at;

    private ActorBean actor;

    public ActorBean getActor() {
        return actor;
    }

    public void setActor(ActorBean actor) {
        this.actor = actor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public static class ActorBean {

        private String login;
        private String avatar_url;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }

    public static class RepoBean {

        private String name;

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public static class PayloadBean {

        private IssueBean issue;
        private List<Commits> commits;

        public List<Commits> getCommits() {
            return commits;
        }

        public void setCommits(List<Commits> commits) {
            this.commits = commits;
        }

        public IssueBean getIssue() {
            return issue;
        }

        public void setIssue(IssueBean issue) {
            this.issue = issue;
        }

        public static class IssueBean {

            private String body;

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

        }

        public static class Commits {

            private String message;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }

    }

}
