package com.spm.vasylyshyn.facade;


import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.dto.UserDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.User;
import org.springframework.stereotype.Component;

@Component
public class DeviceFacade {

    public DeviceDto deviceToDeviceDTO(Device device){
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setNumberOfDevice(device.getNumberOfDevice());
        deviceDto.setPrice(device.getPrice());
        deviceDto.setCounterType(device.getCounterType());
        deviceDto.setCantoraName(device.getCantoraName());

        return deviceDto;
    }

    public UserDto userToUserDTO(User user){
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setAvatarId(user.getAvatarId());

        return userDTO;
    }
}
