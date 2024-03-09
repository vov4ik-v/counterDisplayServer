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
import java.time.LocalDateTime;
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
        user.setFirstName(userIn.getFirstname());
        user.setLastName(userIn.getLastname());
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

    public UserDto getCurrentUserDto(Principal principal){
        return getUserDtoByPrincipal(principal);
    }

    private UserDto getUserDtoByPrincipal(Principal principal){
        String username = principal.getName();
        return userRepository.findUserDtoByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
    public UserDto getUserDtoByUsername(String username){
       return userRepository.findUserDtoByUsername(username).orElseThrow();
    }

    public User getUserById(Long userId) {
        return userRepository.findUserById(userId).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public UserDto getUserDtoById(Long id){
        return userRepository.findDtoUserById(id).orElseThrow(()->new UsernameNotFoundException("User by id: " + id + " not found"  ));
    }


    public List<UserDto> getAllUserDto(){
        return userRepository.findAllUsersDto().orElseThrow();

    }



    public List<DeviceDto> getDeviceForCurrentUser(Principal principal) {
        User user = getUserByPrincipal(principal);
        List<Device> devices = user.getDeviceList();
        return devices.stream().map(deviceFacade::deviceToDeviceDTO).collect(Collectors.toList());
    }

// Затичка, після міграції переробити
    public ApiResponse addDeviceToUser(Long deviceNumber, CType deviceType, String address,
//                                       String password,
                                       Principal principal) {
        User user = getUserByPrincipal(principal);
        Device device = deviceService.registerDevice(new DeviceDto(deviceNumber,"",null,address,0L,deviceType,0));
//        if (device.getPassword().equals(password)){
            device.setAddress(address);
            device.setCounterType(deviceType);
            device.setOwner(user);
            user.getDeviceList().add(device);
            userRepository.save(user);
            deviceRepository.save(device);

            return new ApiResponse(true,"Device added successfully");
//        }
//        else{
//            return new ApiResponse(true,"Please write correct number or password");
//        }


    }
}
