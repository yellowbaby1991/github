package app.yellow.github.bean.repositorydetail;

public class ContentBean {


    /**
     * name : README.md
     * path : README.md
     * sha : 4fb607bcd9d09d14ceb2ac9482afd6877d6290cb
     * size : 174
     * url : https://api.github.com/repos/yellowbaby1991/note/contents/README.md?ref=master
     * html_url : https://github.com/yellowbaby1991/note/blob/master/README.md
     * git_url : https://api.github.com/repos/yellowbaby1991/note/git/blobs/4fb607bcd9d09d14ceb2ac9482afd6877d6290cb
     * download_url : https://raw.githubusercontent.com/yellowbaby1991/note/master/README.md
     * type : file
     * content : 5pyJ5LiA5Liq6Ieq5bex55qE55+l6K+G5bqTLCDmmK/kuIDku7blpJrkuYjo
     o4XpgLznmoTkuovmg4XllYoKClvohJHlm77lnLDlnYBdWzFdCgoKICBbMV06
     IGh0dHA6Ly9uYW90dS5iYWlkdS5jb20vZmlsZS9iODYyZmE4Y2U3Yzg5OTFj
     NjlkMGIzMTAxMjQ0M2Q2Mz90b2tlbj01NzVmOTVmYmQ2MWQ3NDNk

     * encoding : base64
     * _links : {"self":"https://api.github.com/repos/yellowbaby1991/note/contents/README.md?ref=master","git":"https://api.github.com/repos/yellowbaby1991/note/git/blobs/4fb607bcd9d09d14ceb2ac9482afd6877d6290cb","html":"https://github.com/yellowbaby1991/note/blob/master/README.md"}
     */

    private String name;
    private String path;
    private String sha;
    private String content;
    private String type;
    private long size;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
