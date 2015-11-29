package com.pk.user;

public interface UserStorageService {

    User get(Integer userId);

    User create(User user);

    User update(User user);

}
