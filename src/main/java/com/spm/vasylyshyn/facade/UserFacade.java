package com.spm.vasylyshyn.facade;


import com.spm.vasylyshyn.dto.UserDto;
import com.spm.vasylyshyn.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public UserDto userToUserDTO(User user){
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setAvatarId(user.getAvatarId());

        return userDTO;
    }
}
