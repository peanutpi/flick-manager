package com.pk.user.version;

import com.pk.user.User;
import com.pk.util.BaseController.ResourceNotFoundException;

public interface VersionManager {

    Version get(Integer userId);

    void update(Integer userId, Version version) throws ResourceNotFoundException;

    public abstract void createVersionFor(User user);
}
