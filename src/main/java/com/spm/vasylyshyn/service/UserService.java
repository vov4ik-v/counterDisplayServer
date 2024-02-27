package com.spm.vasylyshyn.service;


import com.spm.vasylyshyn.common.ApiResponse;
import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.enums.CType;
import com.spm.vasylyshyn.exeptions.UserExistException;
import com.spm.vasylyshyn.facade.DeviceFacade;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.repository.DeviceRepository;
import com.spm.vasylyshyn.repository.UserRepository;
import com.spm.vasylyshyn.dto.UserDto;
import com.spm.vasylyshyn.enums.ERole;
import com.spm.vasylyshyn.model.User;
import com.spm.vasylyshyn.request.SignupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);


    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceFacade deviceFacade;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DeviceService deviceService;

    @Autowired
    public UserService(UserRepository userRepository, DeviceRepository deviceRepository, DeviceFacade deviceFacade, BCryptPasswordEncoder passwordEncoder, DeviceService deviceService) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.deviceFacade = deviceFacade;
        this.passwordEncoder = passwordEncoder;
        this.deviceService = deviceService;
    }

    public List<User> getAllUser() {
        return userRepository.findAllByOrderByCreatedDateDesc();
    }

    public User createUser(SignupRequest userIn) {
        User user = new User();
        int[] arr = new int[10];
        user.setEmail(userIn.getEmail() );
        user.setUsername(userIn.getUsername());
        user.setAvatarId(1);
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.USER);
        try {
            LOG.info("Saving User {}" + userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {

            LOG.error("Error during registration. {}" + e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }


    }


    public User updateUser(UserDto userDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        user.setUsername(userDTO.getUsername());
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return userRepository.save(user);

    }

    public User getCurrentUser(Principal principal) {

        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        User user =  userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
        System.out.println(user.getAuthorities());
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public User getUserById(Long userId) {
        return userRepository.findUserById(userId).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public UserDto getUserDtoById(Long id){
        return userRepository.findDtoUserById(id).orElseThrow(()->new UsernameNotFoundException("User by id: " + id + " not found"  ));
    }




    public List<DeviceDto> getDeviceForCurrentUser(Principal principal) {
        User user = getUserByPrincipal(principal);
        List<Device> devices = user.getDeviceList();
        return devices.stream().map(deviceFacade::deviceToDeviceDTO).collect(Collectors.toList());
    }


    public ApiResponse addDeviceToUser(Long deviceNumber, CType deviceType, String address, String password, Principal principal) {
        User user = getUserByPrincipal(principal);
        Device device = deviceService.getDeviceByNumber(deviceNumber);
        if (device.getPassword().equals(password)){
            device.setAddress(address);
            device.setCounterType(deviceType);
            device.setOwner(user);
            user.getDeviceList().add(device);
            userRepository.save(user);
            deviceRepository.save(device);

            return new ApiResponse(true,"Device added successfully");
        }
        else{
            return new ApiResponse(true,"Please write correct number or password");
        }


    }
}
