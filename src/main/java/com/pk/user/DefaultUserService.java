package com.pk.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.flick.Flick;
import com.pk.flick.FlickService;
import com.pk.user.flick.FlickDto;
import com.pk.user.version.UserFlick;
import com.pk.user.version.Version;
import com.pk.util.BaseController.ResourceNotFoundException;

@Service
class DefaultUserService implements UserService {

    private final UserStorageService storageService;
    private final FlickService flickService;
    private final Mapper dozerBeanMapper;

    @Autowired
    public DefaultUserService(UserStorageService storageService, FlickService flickService, Mapper dozerBeanMapper) {
        this.storageService = storageService;
        this.flickService = flickService;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public UserDto get(Integer userId) throws ResourceNotFoundException {
        User user = storageService.get(userId);
        if (user == null)
            throw new ResourceNotFoundException("User : " + userId + " not found.");
        return dozerBeanMapper.map(user, UserDto.class, "default-user-mapping");
    }

    @Override
    public UserDto create(UserDto user) {
        User newUser = new User();
        newUser.setCurrentVersion(0);
        newUser.setName(user.getName());
        newUser = storageService.create(newUser);
        return dozerBeanMapper.map(newUser, UserDto.class, "default-user-mapping");
    }

    @Override
    public void update(UserDto userInfo) throws ResourceNotFoundException {
        if (userInfo == null)
            throw new IllegalArgumentException("User information cannot be blank.");
        if (userInfo.getId() == null)
            throw new IllegalArgumentException("Please provide valid user information.");
        User user = storageService.get(userInfo.getId());
        if (user == null)
            throw new ResourceNotFoundException("user with id : " + userInfo.getId() + " not found.");

        dozerBeanMapper.map(userInfo, user, "default-user-mapping");
        storageService.update(user);
    }

    @Override
    public void updateVersion(Integer userId, Integer newVersion, List<FlickDto> addedFlicks,
            List<FlickDto> removedFlicks) {
        User currentUser = storageService.get(userId);
        if (currentUser == null) {
            throw new IllegalArgumentException("No user found for the provided userId : " + userId);
        }

        if (currentUser.getCurrentVersion() >= newVersion) {
            throw new IllegalArgumentException("Please check your new version : " + newVersion);
        }

        currentUser.setCurrentVersion(newVersion);

        for (FlickDto flickDto : addedFlicks) {
            Flick flick = dozerBeanMapper.map(flickDto, Flick.class, "default-flick-mapping");
            flick = flickService.add(flick);
            currentUser.addFlick(flick.getId(), flick.getParent());
        }

        for (FlickDto flickDto : removedFlicks) {
            Flick flick = flickService.getByHash(flickDto.getHash());
            currentUser.removeFlick(flick.getId());
        }

        storageService.update(currentUser);
    }

    @Override
    public Version getCurrentVersion(Integer userId) {
        User currentUser = storageService.get(userId);
        if (currentUser == null) {
            throw new IllegalArgumentException("No user found for the provided userId : " + userId);
        }

        Version currentVersion = new Version();
        currentVersion.setVersion(currentUser.getCurrentVersion());
        return currentVersion;
    }

    @Override
    public List<FlickDto> getFlicks(Integer userId) {
        User currentUser = storageService.get(userId);
        if (currentUser == null) {
            throw new IllegalArgumentException("No user found for the provided userId : " + userId);
        }

        Collection<UserFlick> flicks = currentUser.getFlicks();
        List<FlickDto> response = new ArrayList<FlickDto>();
        for (UserFlick userFlick : flicks) {
            Flick flick = flickService.get(userFlick.getFlickId());
            response.add(dozerBeanMapper.map(flick, FlickDto.class));
        }
        return response;
    }
}
