package com.zylear.j2eelab.rabbitmq.params;


public class MqConfigParam {
    private String url;
    private String vhost;
    private String username;
    private String passpword;

    public String getVhost() {
        return vhost;
    }

    public void setVhost(String vhost) {
        this.vhost = vhost;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasspword() {
        return passpword;
    }

    public void setPasspword(String passpword) {
        this.passpword = passpword;
    }
}
