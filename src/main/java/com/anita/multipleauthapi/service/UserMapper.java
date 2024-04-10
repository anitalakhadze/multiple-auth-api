package com.anita.multipleauthapi.service;

import com.anita.multipleauthapi.model.entity.User;
import com.anita.multipleauthapi.model.payload.UserResponse;

public class UserMapper {

    public static UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        return userResponse;
    }

}
