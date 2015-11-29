package com.pk.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pk.user.UserDto;
import com.pk.user.UserService;
import com.pk.user.flick.FlickDto;
import com.pk.util.BaseController;

@RestController
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param userId
     * @return
     * @throws NotFoundException
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable("id") Integer userId) throws ResourceNotFoundException {
        return userService.get(userId);
    }

    /**
     * @param user
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public UserDto createUser(@RequestBody UserDto user) {
        if (user == null)
            throw new IllegalArgumentException("RequestBody cannot be null.");
        return userService.create(user);
    }

    /**
     * @param user
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public void updateUser(@PathVariable("id") Integer userId, @RequestBody(required = false) UserDto user)
            throws ResourceNotFoundException {

        if (user == null)
            throw new IllegalArgumentException("RequestBody cannot be null.");

        user.setId(userId);
        userService.update(user);
    }

    /**
     * @param userId
     * @return
     */
    @RequestMapping(value = "/users/{id}/flicks", method = RequestMethod.GET)
    public List<FlickDto> getFlicks(@PathVariable("id") Integer userId) {
        return userService.getFlicks(userId);
    }

}
