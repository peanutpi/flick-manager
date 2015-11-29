package com.pk.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.pk.user.version.UserFlick;

public class User {

    Integer id;
    String name;
    Integer currentVersion;
    Map<Integer, UserFlick> userFlicks = new HashMap<Integer, UserFlick>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }

    public void addFlick(Integer id, String parent) {
        this.userFlicks.put(id, new UserFlick(id, parent));
    }

    public void removeFlick(Integer id) {
        this.userFlicks.remove(id);
    }

    public Collection<UserFlick> getFlicks() {
        return userFlicks.values();
    }

}
