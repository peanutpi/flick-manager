package com.pk.user.version;


public class UserFlick {

    private String parent;
    private Integer flickId;

    public UserFlick() {
    }

    public UserFlick(Integer flickId, String parent) {
        this.parent = parent;
        this.flickId = flickId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getFlickId() {
        return flickId;
    }

    public void setFlickId(Integer flickId) {
        this.flickId = flickId;
    }

}
