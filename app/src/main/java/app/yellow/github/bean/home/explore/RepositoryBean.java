package app.yellow.github.bean.home.explore;

public class RepositoryBean {
    /**
     * id : 7508411
     * name : RxJava
     * full_name : ReactiveX/RxJava
     * owner : {"login":"ReactiveX","id":6407041,"avatar_url":"https://avatars2.githubusercontent.com/u/6407041?v=3","gravatar_id":"","url":"https://api.github.com/users/ReactiveX","html_url":"https://github.com/ReactiveX","followers_url":"https://api.github.com/users/ReactiveX/followers","following_url":"https://api.github.com/users/ReactiveX/following{/other_user}","gists_url":"https://api.github.com/users/ReactiveX/gists{/gist_id}","starred_url":"https://api.github.com/users/ReactiveX/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/ReactiveX/subscriptions","organizations_url":"https://api.github.com/users/ReactiveX/orgs","repos_url":"https://api.github.com/users/ReactiveX/repos","events_url":"https://api.github.com/users/ReactiveX/events{/privacy}","received_events_url":"https://api.github.com/users/ReactiveX/received_events","type":"Organization","site_admin":false}
     * private : false
     * html_url : https://github.com/ReactiveX/RxJava
     * description : RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
     * fork : false
     * url : https://api.github.com/repos/ReactiveX/RxJava
     * forks_url : https://api.github.com/repos/ReactiveX/RxJava/forks
     * keys_url : https://api.github.com/repos/ReactiveX/RxJava/keys{/key_id}
     * collaborators_url : https://api.github.com/repos/ReactiveX/RxJava/collaborators{/collaborator}
     * teams_url : https://api.github.com/repos/ReactiveX/RxJava/teams
     * hooks_url : https://api.github.com/repos/ReactiveX/RxJava/hooks
     * issue_events_url : https://api.github.com/repos/ReactiveX/RxJava/issues/events{/number}
     * events_url : https://api.github.com/repos/ReactiveX/RxJava/events
     * assignees_url : https://api.github.com/repos/ReactiveX/RxJava/assignees{/user}
     * branches_url : https://api.github.com/repos/ReactiveX/RxJava/branches{/branch}
     * tags_url : https://api.github.com/repos/ReactiveX/RxJava/tags
     * blobs_url : https://api.github.com/repos/ReactiveX/RxJava/git/blobs{/sha}
     * git_tags_url : https://api.github.com/repos/ReactiveX/RxJava/git/tags{/sha}
     * git_refs_url : https://api.github.com/repos/ReactiveX/RxJava/git/refs{/sha}
     * trees_url : https://api.github.com/repos/ReactiveX/RxJava/git/trees{/sha}
     * statuses_url : https://api.github.com/repos/ReactiveX/RxJava/statuses/{sha}
     * languages_url : https://api.github.com/repos/ReactiveX/RxJava/languages
     * stargazers_url : https://api.github.com/repos/ReactiveX/RxJava/stargazers
     * contributors_url : https://api.github.com/repos/ReactiveX/RxJava/contributors
     * subscribers_url : https://api.github.com/repos/ReactiveX/RxJava/subscribers
     * subscription_url : https://api.github.com/repos/ReactiveX/RxJava/subscription
     * commits_url : https://api.github.com/repos/ReactiveX/RxJava/commits{/sha}
     * git_commits_url : https://api.github.com/repos/ReactiveX/RxJava/git/commits{/sha}
     * comments_url : https://api.github.com/repos/ReactiveX/RxJava/comments{/number}
     * issue_comment_url : https://api.github.com/repos/ReactiveX/RxJava/issues/comments{/number}
     * contents_url : https://api.github.com/repos/ReactiveX/RxJava/contents/{+path}
     * compare_url : https://api.github.com/repos/ReactiveX/RxJava/compare/{base}...{head}
     * merges_url : https://api.github.com/repos/ReactiveX/RxJava/merges
     * archive_url : https://api.github.com/repos/ReactiveX/RxJava/{archive_format}{/ref}
     * downloads_url : https://api.github.com/repos/ReactiveX/RxJava/downloads
     * issues_url : https://api.github.com/repos/ReactiveX/RxJava/issues{/number}
     * pulls_url : https://api.github.com/repos/ReactiveX/RxJava/pulls{/number}
     * milestones_url : https://api.github.com/repos/ReactiveX/RxJava/milestones{/number}
     * notifications_url : https://api.github.com/repos/ReactiveX/RxJava/notifications{?since,all,participating}
     * labels_url : https://api.github.com/repos/ReactiveX/RxJava/labels{/name}
     * releases_url : https://api.github.com/repos/ReactiveX/RxJava/releases{/id}
     * deployments_url : https://api.github.com/repos/ReactiveX/RxJava/deployments
     * created_at : 2013-01-08T20:11:48Z
     * updated_at : 2017-06-21T03:52:25Z
     * pushed_at : 2017-06-20T08:02:06Z
     * git_url : git://github.com/ReactiveX/RxJava.git
     * ssh_url : git@github.com:ReactiveX/RxJava.git
     * clone_url : https://github.com/ReactiveX/RxJava.git
     * svn_url : https://github.com/ReactiveX/RxJava
     * homepage :
     * size : 41699
     * stargazers_count : 25108
     * watchers_count : 25108
     * language : Java
     * has_issues : true
     * has_projects : true
     * has_downloads : true
     * has_wiki : true
     * has_pages : true
     * forks_count : 4417
     * mirror_url : null
     * open_issues_count : 48
     * forks : 4417
     * open_issues : 48
     * watchers : 25108
     * default_branch : 2.x
     * score : 1.0
     */

    private String name;
    private RepositoryBean.OwnerBean owner;
    private boolean privateX;
    private String description;
    private boolean fork;
    private String created_at;
    private String updated_at;
    private int size;
    private int stargazers_count;
    private int watchers_count;
    private String language;
    private int forks_count;
    private int open_issues_count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OwnerBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerBean owner) {
        this.owner = owner;
    }

    public boolean isPrivateX() {
        return privateX;
    }

    public void setPrivateX(boolean privateX) {
        this.privateX = privateX;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public int getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(int open_issues_count) {
        this.open_issues_count = open_issues_count;
    }


    public static class OwnerBean {
        /**
         * login : ReactiveX
         * id : 6407041
         * avatar_url : https://avatars2.githubusercontent.com/u/6407041?v=3
         * gravatar_id :
         * url : https://api.github.com/users/ReactiveX
         * html_url : https://github.com/ReactiveX
         * followers_url : https://api.github.com/users/ReactiveX/followers
         * following_url : https://api.github.com/users/ReactiveX/following{/other_user}
         * gists_url : https://api.github.com/users/ReactiveX/gists{/gist_id}
         * starred_url : https://api.github.com/users/ReactiveX/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/ReactiveX/subscriptions
         * organizations_url : https://api.github.com/users/ReactiveX/orgs
         * repos_url : https://api.github.com/users/ReactiveX/repos
         * events_url : https://api.github.com/users/ReactiveX/events{/privacy}
         * received_events_url : https://api.github.com/users/ReactiveX/received_events
         * type : Organization
         * site_admin : false
         */

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
}