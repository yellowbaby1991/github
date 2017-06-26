package app.yellow.github.bean.home.event;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventBean {


    /**
     * id : 6134403198
     * type : IssueCommentEvent
     * actor : {"id":66577,"login":"JakeWharton","display_login":"JakeWharton","gravatar_id":"","url":"https://api.github.com/users/JakeWharton","avatar_url":"https://avatars.githubusercontent.com/u/66577?"}
     * repo : {"id":8575137,"name":"JakeWharton/butterknife","url":"https://api.github.com/repos/JakeWharton/butterknife"}
     * payload : {"action":"created","issue":{"url":"https://api.github.com/repos/JakeWharton/butterknife/issues/990","repository_url":"https://api.github.com/repos/JakeWharton/butterknife","labels_url":"https://api.github.com/repos/JakeWharton/butterknife/issues/990/labels{/name}","comments_url":"https://api.github.com/repos/JakeWharton/butterknife/issues/990/comments","events_url":"https://api.github.com/repos/JakeWharton/butterknife/issues/990/events","html_url":"https://github.com/JakeWharton/butterknife/issues/990","id":238362383,"number":990,"title":"Error:(38, 21) The method bind(LoginActivity) is undefined for the type ButterKnife","user":{"login":"MojoJojo17","id":29685213,"avatar_url":"https://avatars2.githubusercontent.com/u/29685213?v=3","gravatar_id":"","url":"https://api.github.com/users/MojoJojo17","html_url":"https://github.com/MojoJojo17","followers_url":"https://api.github.com/users/MojoJojo17/followers","following_url":"https://api.github.com/users/MojoJojo17/following{/other_user}","gists_url":"https://api.github.com/users/MojoJojo17/gists{/gist_id}","starred_url":"https://api.github.com/users/MojoJojo17/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/MojoJojo17/subscriptions","organizations_url":"https://api.github.com/users/MojoJojo17/orgs","repos_url":"https://api.github.com/users/MojoJojo17/repos","events_url":"https://api.github.com/users/MojoJojo17/events{/privacy}","received_events_url":"https://api.github.com/users/MojoJojo17/received_events","type":"User","site_admin":false},"labels":[],"state":"open","locked":false,"assignee":null,"assignees":[],"milestone":null,"comments":2,"created_at":"2017-06-25T06:04:44Z","updated_at":"2017-06-25T22:20:35Z","closed_at":null,"body":"I am using Butterknife for the first time and I am not sure what I am doing wrong. I am getting the following error:\r\n\r\n```\r\nError:(38, 21) The method bind(LoginActivity) is undefined for the type ButterKnife\r\n\r\nError:(36, 21) The method bind(SignupActivity) is undefined for the type ButterKnife\r\n\r\nError:Execution failed for task ':app:transformJackWithJackForDebug'.\r\n> com.android.build.api.transform.TransformException: com.android.builder.core.JackToolchain$ToolchainException: Jack compilation exception\r\n```\r\nThis is what my activities look like:\r\n\r\n```\r\nimport com.myapp.R;\r\n\r\nimport butterknife.BindView;\r\nimport butterknife.ButterKnife;\r\n\r\npublic class LoginActivity extends Activity {\r\n    private static final String TAG = \"LoginActivity\";\r\n    private static final int REQUEST_SIGNUP = 0;\r\n\r\n    @BindView(R.id.input_email) EditText _emailText;\r\n    @BindView(R.id.input_password) EditText _passwordText;\r\n    @BindView(R.id.btn_login) Button _loginButton;\r\n    @BindView(R.id.link_signup) TextView _signupLink;\r\n\r\n    @Override\r\n    public void onCreate(Bundle savedInstanceState) {\r\n        super.onCreate(savedInstanceState);\r\n        setContentView(R.layout.login_main);\r\n        ButterKnife.bind(this);\r\n...\r\n```\r\n\r\nAnd\r\n\r\n```\r\nimport com.myapp.R;\r\nimport butterknife.ButterKnife;\r\nimport butterknife.BindView;\r\n\r\npublic class SignupActivity extends Activity {\r\n    private static final String TAG = \"SignupActivity\";\r\n\r\n    @BindView(R.id.input_name) EditText _nameText;\r\n    @BindView(R.id.input_email) EditText _emailText;\r\n    @BindView(R.id.input_password) EditText _passwordText;\r\n    @BindView(R.id.btn_signup) Button _signupButton;\r\n    @BindView(R.id.link_login) TextView _loginLink;\r\n\r\n    @Override\r\n    public void onCreate(Bundle savedInstanceState) {\r\n        super.onCreate(savedInstanceState);\r\n        setContentView(R.layout.signup_main);\r\n        ButterKnife.bind(this);\r\n...\r\n```\r\n\r\nI have this in my gradle.properties:\r\n\r\n```\r\norg.gradle.jvmargs=-Xmx1536m\r\n```\r\n\r\nThese are my dependencies for butterknife:\r\n\r\n\r\n```\r\ncompile 'com.jakewharton:butterknife:8.6.0'\r\nannotationProcessor 'com.jakewharton:butterknife-compiler:8.6.0'\r\n```\r\n\r\nHere's my gradle's main settings:\r\n\r\n```\r\nandroid {\r\n    compileSdkVersion 25\r\n    buildToolsVersion '25.0.3'\r\n    aaptOptions.cruncherEnabled = false\r\n    aaptOptions.useNewCruncher = false\r\n\r\n    defaultConfig {\r\n        minSdkVersion 16\r\n        targetSdkVersion 25\r\n        versionCode 1\r\n        versionName \"1.0\"\r\n        jackOptions {\r\n            enabled true\r\n        }\r\n    }\r\n\r\n    buildTypes {\r\n        release {\r\n            minifyEnabled false\r\n            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'\r\n        }\r\n    }\r\n\r\n    compileOptions {\r\n        sourceCompatibility JavaVersion.VERSION_1_8\r\n        targetCompatibility JavaVersion.VERSION_1_8\r\n    }\r\n}\r\n```\r\n"},"comment":{"url":"https://api.github.com/repos/JakeWharton/butterknife/issues/comments/310932440","html_url":"https://github.com/JakeWharton/butterknife/issues/990#issuecomment-310932440","issue_url":"https://api.github.com/repos/JakeWharton/butterknife/issues/990","id":310932440,"user":{"login":"JakeWharton","id":66577,"avatar_url":"https://avatars3.githubusercontent.com/u/66577?v=3","gravatar_id":"","url":"https://api.github.com/users/JakeWharton","html_url":"https://github.com/JakeWharton","followers_url":"https://api.github.com/users/JakeWharton/followers","following_url":"https://api.github.com/users/JakeWharton/following{/other_user}","gists_url":"https://api.github.com/users/JakeWharton/gists{/gist_id}","starred_url":"https://api.github.com/users/JakeWharton/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/JakeWharton/subscriptions","organizations_url":"https://api.github.com/users/JakeWharton/orgs","repos_url":"https://api.github.com/users/JakeWharton/repos","events_url":"https://api.github.com/users/JakeWharton/events{/privacy}","received_events_url":"https://api.github.com/users/JakeWharton/received_events","type":"User","site_admin":false},"created_at":"2017-06-25T22:20:34Z","updated_at":"2017-06-25T22:20:34Z","body":"Jack is deprecated so you should migrate away from it. We have no plans to\nsupport it moving forward.\n\nOn Sun, Jun 25, 2017, 5:05 PM MojoJojo <notifications@github.com> wrote:\n\n> Great suggestion! I just tried it and it did work. I am wondering if\n> there's a workaround to keep jack on and still be able to use butterknife?\n>\n> \u2014\n> You are receiving this because you commented.\n>\n>\n> Reply to this email directly, view it on GitHub\n> <https://github.com/JakeWharton/butterknife/issues/990#issuecomment-310931660>,\n> or mute the thread\n> <https://github.com/notifications/unsubscribe-auth/AAEEEfBI54MObOk6H3paWfFSwEv43Rceks5sHtnGgaJpZM4OEhnb>\n> .\n>\n"}}
     * public : true
     * created_at : 2017-06-25T22:20:36Z
     * org : {"id":82592,"login":"square","gravatar_id":"","url":"https://api.github.com/orgs/square","avatar_url":"https://avatars.githubusercontent.com/u/82592?"}
     */

    private String id;
    private String type;
    private ActorBean actor;
    private RepoBean repo;
    private PayloadBean payload;
    @SerializedName("public")
    private boolean publicX;
    private String created_at;
    private OrgBean org;

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

    public OrgBean getOrg() {
        return org;
    }

    public void setOrg(OrgBean org) {
        this.org = org;
    }

    public static class ActorBean {
        /**
         * id : 66577
         * login : JakeWharton
         * display_login : JakeWharton
         * gravatar_id :
         * url : https://api.github.com/users/JakeWharton
         * avatar_url : https://avatars.githubusercontent.com/u/66577?
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
         * id : 8575137
         * name : JakeWharton/butterknife
         * url : https://api.github.com/repos/JakeWharton/butterknife
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
         * action : created
         * issue : {"url":"https://api.github.com/repos/JakeWharton/butterknife/issues/990","repository_url":"https://api.github.com/repos/JakeWharton/butterknife","labels_url":"https://api.github.com/repos/JakeWharton/butterknife/issues/990/labels{/name}","comments_url":"https://api.github.com/repos/JakeWharton/butterknife/issues/990/comments","events_url":"https://api.github.com/repos/JakeWharton/butterknife/issues/990/events","html_url":"https://github.com/JakeWharton/butterknife/issues/990","id":238362383,"number":990,"title":"Error:(38, 21) The method bind(LoginActivity) is undefined for the type ButterKnife","user":{"login":"MojoJojo17","id":29685213,"avatar_url":"https://avatars2.githubusercontent.com/u/29685213?v=3","gravatar_id":"","url":"https://api.github.com/users/MojoJojo17","html_url":"https://github.com/MojoJojo17","followers_url":"https://api.github.com/users/MojoJojo17/followers","following_url":"https://api.github.com/users/MojoJojo17/following{/other_user}","gists_url":"https://api.github.com/users/MojoJojo17/gists{/gist_id}","starred_url":"https://api.github.com/users/MojoJojo17/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/MojoJojo17/subscriptions","organizations_url":"https://api.github.com/users/MojoJojo17/orgs","repos_url":"https://api.github.com/users/MojoJojo17/repos","events_url":"https://api.github.com/users/MojoJojo17/events{/privacy}","received_events_url":"https://api.github.com/users/MojoJojo17/received_events","type":"User","site_admin":false},"labels":[],"state":"open","locked":false,"assignee":null,"assignees":[],"milestone":null,"comments":2,"created_at":"2017-06-25T06:04:44Z","updated_at":"2017-06-25T22:20:35Z","closed_at":null,"body":"I am using Butterknife for the first time and I am not sure what I am doing wrong. I am getting the following error:\r\n\r\n```\r\nError:(38, 21) The method bind(LoginActivity) is undefined for the type ButterKnife\r\n\r\nError:(36, 21) The method bind(SignupActivity) is undefined for the type ButterKnife\r\n\r\nError:Execution failed for task ':app:transformJackWithJackForDebug'.\r\n> com.android.build.api.transform.TransformException: com.android.builder.core.JackToolchain$ToolchainException: Jack compilation exception\r\n```\r\nThis is what my activities look like:\r\n\r\n```\r\nimport com.myapp.R;\r\n\r\nimport butterknife.BindView;\r\nimport butterknife.ButterKnife;\r\n\r\npublic class LoginActivity extends Activity {\r\n    private static final String TAG = \"LoginActivity\";\r\n    private static final int REQUEST_SIGNUP = 0;\r\n\r\n    @BindView(R.id.input_email) EditText _emailText;\r\n    @BindView(R.id.input_password) EditText _passwordText;\r\n    @BindView(R.id.btn_login) Button _loginButton;\r\n    @BindView(R.id.link_signup) TextView _signupLink;\r\n\r\n    @Override\r\n    public void onCreate(Bundle savedInstanceState) {\r\n        super.onCreate(savedInstanceState);\r\n        setContentView(R.layout.login_main);\r\n        ButterKnife.bind(this);\r\n...\r\n```\r\n\r\nAnd\r\n\r\n```\r\nimport com.myapp.R;\r\nimport butterknife.ButterKnife;\r\nimport butterknife.BindView;\r\n\r\npublic class SignupActivity extends Activity {\r\n    private static final String TAG = \"SignupActivity\";\r\n\r\n    @BindView(R.id.input_name) EditText _nameText;\r\n    @BindView(R.id.input_email) EditText _emailText;\r\n    @BindView(R.id.input_password) EditText _passwordText;\r\n    @BindView(R.id.btn_signup) Button _signupButton;\r\n    @BindView(R.id.link_login) TextView _loginLink;\r\n\r\n    @Override\r\n    public void onCreate(Bundle savedInstanceState) {\r\n        super.onCreate(savedInstanceState);\r\n        setContentView(R.layout.signup_main);\r\n        ButterKnife.bind(this);\r\n...\r\n```\r\n\r\nI have this in my gradle.properties:\r\n\r\n```\r\norg.gradle.jvmargs=-Xmx1536m\r\n```\r\n\r\nThese are my dependencies for butterknife:\r\n\r\n\r\n```\r\ncompile 'com.jakewharton:butterknife:8.6.0'\r\nannotationProcessor 'com.jakewharton:butterknife-compiler:8.6.0'\r\n```\r\n\r\nHere's my gradle's main settings:\r\n\r\n```\r\nandroid {\r\n    compileSdkVersion 25\r\n    buildToolsVersion '25.0.3'\r\n    aaptOptions.cruncherEnabled = false\r\n    aaptOptions.useNewCruncher = false\r\n\r\n    defaultConfig {\r\n        minSdkVersion 16\r\n        targetSdkVersion 25\r\n        versionCode 1\r\n        versionName \"1.0\"\r\n        jackOptions {\r\n            enabled true\r\n        }\r\n    }\r\n\r\n    buildTypes {\r\n        release {\r\n            minifyEnabled false\r\n            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'\r\n        }\r\n    }\r\n\r\n    compileOptions {\r\n        sourceCompatibility JavaVersion.VERSION_1_8\r\n        targetCompatibility JavaVersion.VERSION_1_8\r\n    }\r\n}\r\n```\r\n"}
         * comment : {"url":"https://api.github.com/repos/JakeWharton/butterknife/issues/comments/310932440","html_url":"https://github.com/JakeWharton/butterknife/issues/990#issuecomment-310932440","issue_url":"https://api.github.com/repos/JakeWharton/butterknife/issues/990","id":310932440,"user":{"login":"JakeWharton","id":66577,"avatar_url":"https://avatars3.githubusercontent.com/u/66577?v=3","gravatar_id":"","url":"https://api.github.com/users/JakeWharton","html_url":"https://github.com/JakeWharton","followers_url":"https://api.github.com/users/JakeWharton/followers","following_url":"https://api.github.com/users/JakeWharton/following{/other_user}","gists_url":"https://api.github.com/users/JakeWharton/gists{/gist_id}","starred_url":"https://api.github.com/users/JakeWharton/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/JakeWharton/subscriptions","organizations_url":"https://api.github.com/users/JakeWharton/orgs","repos_url":"https://api.github.com/users/JakeWharton/repos","events_url":"https://api.github.com/users/JakeWharton/events{/privacy}","received_events_url":"https://api.github.com/users/JakeWharton/received_events","type":"User","site_admin":false},"created_at":"2017-06-25T22:20:34Z","updated_at":"2017-06-25T22:20:34Z","body":"Jack is deprecated so you should migrate away from it. We have no plans to\nsupport it moving forward.\n\nOn Sun, Jun 25, 2017, 5:05 PM MojoJojo <notifications@github.com> wrote:\n\n> Great suggestion! I just tried it and it did work. I am wondering if\n> there's a workaround to keep jack on and still be able to use butterknife?\n>\n> \u2014\n> You are receiving this because you commented.\n>\n>\n> Reply to this email directly, view it on GitHub\n> <https://github.com/JakeWharton/butterknife/issues/990#issuecomment-310931660>,\n> or mute the thread\n> <https://github.com/notifications/unsubscribe-auth/AAEEEfBI54MObOk6H3paWfFSwEv43Rceks5sHtnGgaJpZM4OEhnb>\n> .\n>\n"}
         */

        private String action;
        private IssueBean issue;
        private CommentBean comment;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public IssueBean getIssue() {
            return issue;
        }

        public void setIssue(IssueBean issue) {
            this.issue = issue;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public static class IssueBean {
            /**
             * url : https://api.github.com/repos/JakeWharton/butterknife/issues/990
             * repository_url : https://api.github.com/repos/JakeWharton/butterknife
             * labels_url : https://api.github.com/repos/JakeWharton/butterknife/issues/990/labels{/name}
             * comments_url : https://api.github.com/repos/JakeWharton/butterknife/issues/990/comments
             * events_url : https://api.github.com/repos/JakeWharton/butterknife/issues/990/events
             * html_url : https://github.com/JakeWharton/butterknife/issues/990
             * id : 238362383
             * number : 990
             * title : Error:(38, 21) The method bind(LoginActivity) is undefined for the type ButterKnife
             * user : {"login":"MojoJojo17","id":29685213,"avatar_url":"https://avatars2.githubusercontent.com/u/29685213?v=3","gravatar_id":"","url":"https://api.github.com/users/MojoJojo17","html_url":"https://github.com/MojoJojo17","followers_url":"https://api.github.com/users/MojoJojo17/followers","following_url":"https://api.github.com/users/MojoJojo17/following{/other_user}","gists_url":"https://api.github.com/users/MojoJojo17/gists{/gist_id}","starred_url":"https://api.github.com/users/MojoJojo17/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/MojoJojo17/subscriptions","organizations_url":"https://api.github.com/users/MojoJojo17/orgs","repos_url":"https://api.github.com/users/MojoJojo17/repos","events_url":"https://api.github.com/users/MojoJojo17/events{/privacy}","received_events_url":"https://api.github.com/users/MojoJojo17/received_events","type":"User","site_admin":false}
             * labels : []
             * state : open
             * locked : false
             * assignee : null
             * assignees : []
             * milestone : null
             * comments : 2
             * created_at : 2017-06-25T06:04:44Z
             * updated_at : 2017-06-25T22:20:35Z
             * closed_at : null
             * body : I am using Butterknife for the first time and I am not sure what I am doing wrong. I am getting the following error:

             ```
             Error:(38, 21) The method bind(LoginActivity) is undefined for the type ButterKnife

             Error:(36, 21) The method bind(SignupActivity) is undefined for the type ButterKnife

             Error:Execution failed for task ':app:transformJackWithJackForDebug'.
             > com.android.build.api.transform.TransformException: com.android.builder.core.JackToolchain$ToolchainException: Jack compilation exception
             ```
             This is what my activities look like:

             ```
             import com.myapp.R;

             import butterknife.BindView;
             import butterknife.ButterKnife;

             public class LoginActivity extends Activity {
             private static final String TAG = "LoginActivity";
             private static final int REQUEST_SIGNUP = 0;

             @BindView(R.id.input_email) EditText _emailText;
             @BindView(R.id.input_password) EditText _passwordText;
             @BindView(R.id.btn_login) Button _loginButton;
             @BindView(R.id.link_signup) TextView _signupLink;

             @Override public void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.login_main);
             ButterKnife.bind(this);
             ...
             ```

             And

             ```
             import com.myapp.R;
             import butterknife.ButterKnife;
             import butterknife.BindView;

             public class SignupActivity extends Activity {
             private static final String TAG = "SignupActivity";

             @BindView(R.id.input_name) EditText _nameText;
             @BindView(R.id.input_email) EditText _emailText;
             @BindView(R.id.input_password) EditText _passwordText;
             @BindView(R.id.btn_signup) Button _signupButton;
             @BindView(R.id.link_login) TextView _loginLink;

             @Override public void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.signup_main);
             ButterKnife.bind(this);
             ...
             ```

             I have this in my gradle.properties:

             ```
             org.gradle.jvmargs=-Xmx1536m
             ```

             These are my dependencies for butterknife:


             ```
             compile 'com.jakewharton:butterknife:8.6.0'
             annotationProcessor 'com.jakewharton:butterknife-compiler:8.6.0'
             ```

             Here's my gradle's main settings:

             ```
             android {
             compileSdkVersion 25
             buildToolsVersion '25.0.3'
             aaptOptions.cruncherEnabled = false
             aaptOptions.useNewCruncher = false

             defaultConfig {
             minSdkVersion 16
             targetSdkVersion 25
             versionCode 1
             versionName "1.0"
             jackOptions {
             enabled true
             }
             }

             buildTypes {
             release {
             minifyEnabled false
             proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
             }
             }

             compileOptions {
             sourceCompatibility JavaVersion.VERSION_1_8
             targetCompatibility JavaVersion.VERSION_1_8
             }
             }
             ```

             */

            private String url;
            private String repository_url;
            private String labels_url;
            private String comments_url;
            private String events_url;
            private String html_url;
            private int id;
            private int number;
            private String title;
            private UserBean user;
            private String state;
            private boolean locked;
            private Object assignee;
            private Object milestone;
            private int comments;
            private String created_at;
            private String updated_at;
            private Object closed_at;
            private String body;
            private List<?> labels;
            private List<?> assignees;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getRepository_url() {
                return repository_url;
            }

            public void setRepository_url(String repository_url) {
                this.repository_url = repository_url;
            }

            public String getLabels_url() {
                return labels_url;
            }

            public void setLabels_url(String labels_url) {
                this.labels_url = labels_url;
            }

            public String getComments_url() {
                return comments_url;
            }

            public void setComments_url(String comments_url) {
                this.comments_url = comments_url;
            }

            public String getEvents_url() {
                return events_url;
            }

            public void setEvents_url(String events_url) {
                this.events_url = events_url;
            }

            public String getHtml_url() {
                return html_url;
            }

            public void setHtml_url(String html_url) {
                this.html_url = html_url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public boolean isLocked() {
                return locked;
            }

            public void setLocked(boolean locked) {
                this.locked = locked;
            }

            public Object getAssignee() {
                return assignee;
            }

            public void setAssignee(Object assignee) {
                this.assignee = assignee;
            }

            public Object getMilestone() {
                return milestone;
            }

            public void setMilestone(Object milestone) {
                this.milestone = milestone;
            }

            public int getComments() {
                return comments;
            }

            public void setComments(int comments) {
                this.comments = comments;
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

            public Object getClosed_at() {
                return closed_at;
            }

            public void setClosed_at(Object closed_at) {
                this.closed_at = closed_at;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public List<?> getLabels() {
                return labels;
            }

            public void setLabels(List<?> labels) {
                this.labels = labels;
            }

            public List<?> getAssignees() {
                return assignees;
            }

            public void setAssignees(List<?> assignees) {
                this.assignees = assignees;
            }

            public static class UserBean {
                /**
                 * login : MojoJojo17
                 * id : 29685213
                 * avatar_url : https://avatars2.githubusercontent.com/u/29685213?v=3
                 * gravatar_id :
                 * url : https://api.github.com/users/MojoJojo17
                 * html_url : https://github.com/MojoJojo17
                 * followers_url : https://api.github.com/users/MojoJojo17/followers
                 * following_url : https://api.github.com/users/MojoJojo17/following{/other_user}
                 * gists_url : https://api.github.com/users/MojoJojo17/gists{/gist_id}
                 * starred_url : https://api.github.com/users/MojoJojo17/starred{/owner}{/repo}
                 * subscriptions_url : https://api.github.com/users/MojoJojo17/subscriptions
                 * organizations_url : https://api.github.com/users/MojoJojo17/orgs
                 * repos_url : https://api.github.com/users/MojoJojo17/repos
                 * events_url : https://api.github.com/users/MojoJojo17/events{/privacy}
                 * received_events_url : https://api.github.com/users/MojoJojo17/received_events
                 * type : User
                 * site_admin : false
                 */

                private String login;
                private int id;
                private String avatar_url;
                private String gravatar_id;
                private String url;
                private String html_url;
                private String followers_url;
                private String following_url;
                private String gists_url;
                private String starred_url;
                private String subscriptions_url;
                private String organizations_url;
                private String repos_url;
                private String events_url;
                private String received_events_url;
                private String type;
                private boolean site_admin;

                public String getLogin() {
                    return login;
                }

                public void setLogin(String login) {
                    this.login = login;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
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

                public String getHtml_url() {
                    return html_url;
                }

                public void setHtml_url(String html_url) {
                    this.html_url = html_url;
                }

                public String getFollowers_url() {
                    return followers_url;
                }

                public void setFollowers_url(String followers_url) {
                    this.followers_url = followers_url;
                }

                public String getFollowing_url() {
                    return following_url;
                }

                public void setFollowing_url(String following_url) {
                    this.following_url = following_url;
                }

                public String getGists_url() {
                    return gists_url;
                }

                public void setGists_url(String gists_url) {
                    this.gists_url = gists_url;
                }

                public String getStarred_url() {
                    return starred_url;
                }

                public void setStarred_url(String starred_url) {
                    this.starred_url = starred_url;
                }

                public String getSubscriptions_url() {
                    return subscriptions_url;
                }

                public void setSubscriptions_url(String subscriptions_url) {
                    this.subscriptions_url = subscriptions_url;
                }

                public String getOrganizations_url() {
                    return organizations_url;
                }

                public void setOrganizations_url(String organizations_url) {
                    this.organizations_url = organizations_url;
                }

                public String getRepos_url() {
                    return repos_url;
                }

                public void setRepos_url(String repos_url) {
                    this.repos_url = repos_url;
                }

                public String getEvents_url() {
                    return events_url;
                }

                public void setEvents_url(String events_url) {
                    this.events_url = events_url;
                }

                public String getReceived_events_url() {
                    return received_events_url;
                }

                public void setReceived_events_url(String received_events_url) {
                    this.received_events_url = received_events_url;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public boolean isSite_admin() {
                    return site_admin;
                }

                public void setSite_admin(boolean site_admin) {
                    this.site_admin = site_admin;
                }
            }
        }

        public static class CommentBean {
            /**
             * url : https://api.github.com/repos/JakeWharton/butterknife/issues/comments/310932440
             * html_url : https://github.com/JakeWharton/butterknife/issues/990#issuecomment-310932440
             * issue_url : https://api.github.com/repos/JakeWharton/butterknife/issues/990
             * id : 310932440
             * user : {"login":"JakeWharton","id":66577,"avatar_url":"https://avatars3.githubusercontent.com/u/66577?v=3","gravatar_id":"","url":"https://api.github.com/users/JakeWharton","html_url":"https://github.com/JakeWharton","followers_url":"https://api.github.com/users/JakeWharton/followers","following_url":"https://api.github.com/users/JakeWharton/following{/other_user}","gists_url":"https://api.github.com/users/JakeWharton/gists{/gist_id}","starred_url":"https://api.github.com/users/JakeWharton/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/JakeWharton/subscriptions","organizations_url":"https://api.github.com/users/JakeWharton/orgs","repos_url":"https://api.github.com/users/JakeWharton/repos","events_url":"https://api.github.com/users/JakeWharton/events{/privacy}","received_events_url":"https://api.github.com/users/JakeWharton/received_events","type":"User","site_admin":false}
             * created_at : 2017-06-25T22:20:34Z
             * updated_at : 2017-06-25T22:20:34Z
             * body : Jack is deprecated so you should migrate away from it. We have no plans to
             support it moving forward.

             On Sun, Jun 25, 2017, 5:05 PM MojoJojo <notifications@github.com> wrote:

             > Great suggestion! I just tried it and it did work. I am wondering if
             > there's a workaround to keep jack on and still be able to use butterknife?
             >
             > —
             > You are receiving this because you commented.
             >
             >
             > Reply to this email directly, view it on GitHub
             > <https://github.com/JakeWharton/butterknife/issues/990#issuecomment-310931660>,
             > or mute the thread
             > <https://github.com/notifications/unsubscribe-auth/AAEEEfBI54MObOk6H3paWfFSwEv43Rceks5sHtnGgaJpZM4OEhnb>
             > .
             >

             */

            private String url;
            private String html_url;
            private String issue_url;
            private int id;
            private UserBeanX user;
            private String created_at;
            private String updated_at;
            private String body;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getHtml_url() {
                return html_url;
            }

            public void setHtml_url(String html_url) {
                this.html_url = html_url;
            }

            public String getIssue_url() {
                return issue_url;
            }

            public void setIssue_url(String issue_url) {
                this.issue_url = issue_url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
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

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public static class UserBeanX {
                /**
                 * login : JakeWharton
                 * id : 66577
                 * avatar_url : https://avatars3.githubusercontent.com/u/66577?v=3
                 * gravatar_id :
                 * url : https://api.github.com/users/JakeWharton
                 * html_url : https://github.com/JakeWharton
                 * followers_url : https://api.github.com/users/JakeWharton/followers
                 * following_url : https://api.github.com/users/JakeWharton/following{/other_user}
                 * gists_url : https://api.github.com/users/JakeWharton/gists{/gist_id}
                 * starred_url : https://api.github.com/users/JakeWharton/starred{/owner}{/repo}
                 * subscriptions_url : https://api.github.com/users/JakeWharton/subscriptions
                 * organizations_url : https://api.github.com/users/JakeWharton/orgs
                 * repos_url : https://api.github.com/users/JakeWharton/repos
                 * events_url : https://api.github.com/users/JakeWharton/events{/privacy}
                 * received_events_url : https://api.github.com/users/JakeWharton/received_events
                 * type : User
                 * site_admin : false
                 */

                private String login;
                private int id;
                private String avatar_url;
                private String gravatar_id;
                private String url;
                private String html_url;
                private String followers_url;
                private String following_url;
                private String gists_url;
                private String starred_url;
                private String subscriptions_url;
                private String organizations_url;
                private String repos_url;
                private String events_url;
                private String received_events_url;
                private String type;
                private boolean site_admin;

                public String getLogin() {
                    return login;
                }

                public void setLogin(String login) {
                    this.login = login;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
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

                public String getHtml_url() {
                    return html_url;
                }

                public void setHtml_url(String html_url) {
                    this.html_url = html_url;
                }

                public String getFollowers_url() {
                    return followers_url;
                }

                public void setFollowers_url(String followers_url) {
                    this.followers_url = followers_url;
                }

                public String getFollowing_url() {
                    return following_url;
                }

                public void setFollowing_url(String following_url) {
                    this.following_url = following_url;
                }

                public String getGists_url() {
                    return gists_url;
                }

                public void setGists_url(String gists_url) {
                    this.gists_url = gists_url;
                }

                public String getStarred_url() {
                    return starred_url;
                }

                public void setStarred_url(String starred_url) {
                    this.starred_url = starred_url;
                }

                public String getSubscriptions_url() {
                    return subscriptions_url;
                }

                public void setSubscriptions_url(String subscriptions_url) {
                    this.subscriptions_url = subscriptions_url;
                }

                public String getOrganizations_url() {
                    return organizations_url;
                }

                public void setOrganizations_url(String organizations_url) {
                    this.organizations_url = organizations_url;
                }

                public String getRepos_url() {
                    return repos_url;
                }

                public void setRepos_url(String repos_url) {
                    this.repos_url = repos_url;
                }

                public String getEvents_url() {
                    return events_url;
                }

                public void setEvents_url(String events_url) {
                    this.events_url = events_url;
                }

                public String getReceived_events_url() {
                    return received_events_url;
                }

                public void setReceived_events_url(String received_events_url) {
                    this.received_events_url = received_events_url;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public boolean isSite_admin() {
                    return site_admin;
                }

                public void setSite_admin(boolean site_admin) {
                    this.site_admin = site_admin;
                }
            }
        }
    }

    public static class OrgBean {
        /**
         * id : 82592
         * login : square
         * gravatar_id :
         * url : https://api.github.com/orgs/square
         * avatar_url : https://avatars.githubusercontent.com/u/82592?
         */

        private int id;
        private String login;
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
}
