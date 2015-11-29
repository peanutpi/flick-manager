package com.pk.user.version.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pk.user.UserService;
import com.pk.user.version.Version;
import com.pk.util.BaseController;

@RestController
public class UserVersionController extends BaseController {

    private UserService userService;

    @Autowired
    public UserVersionController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users/{id}/version/current", method = RequestMethod.GET)
    public Version getVersion(@PathVariable("id") Integer userId) {
        return userService.getCurrentVersion(userId);
    }

    @RequestMapping(value = "/users/{id}/version", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateVersion(@PathVariable("id") Integer userId, @RequestBody Version version)
            throws ResourceNotFoundException {
        userService.updateVersion(userId, version.getVersion(), version.getAdded(), version.getRemoved());
    }

}
