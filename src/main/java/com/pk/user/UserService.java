package com.pk.user;

import java.util.List;

import com.pk.user.flick.FlickDto;
import com.pk.user.version.Version;
import com.pk.util.BaseController.ResourceNotFoundException;

public interface UserService {

    UserDto get(Integer userId) throws ResourceNotFoundException;

    UserDto create(UserDto user);

    void update(UserDto user) throws ResourceNotFoundException;

    public abstract Version getCurrentVersion(Integer userId);

    public abstract void updateVersion(Integer userId, Integer newVersion, List<FlickDto> addedFlicks, List<FlickDto> removedFlicks);

    List<FlickDto> getFlicks(Integer userId);

}
