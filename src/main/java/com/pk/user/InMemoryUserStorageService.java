package com.pk.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryUserStorageService implements UserStorageService {

    private Map<Integer, User> userMap;

    public InMemoryUserStorageService() {
        this.userMap = new HashMap<Integer, User>();
    }

    @Override
    public User get(Integer userId) {
        return this.userMap.get(userId);
    }

    @Override
    public User create(User user) {
        user.setId(RandomUtils.nextInt(1, 50));
        this.userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user) {
        this.userMap.put(user.getId(), user);
        return user;
    }
}