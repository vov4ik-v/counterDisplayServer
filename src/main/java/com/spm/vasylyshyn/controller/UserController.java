package com.spm.vasylyshyn.controller;



import com.spm.vasylyshyn.common.ApiResponse;
import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.validations.ResponseErrorValidation;
import com.spm.vasylyshyn.dto.UserDto;
import com.spm.vasylyshyn.facade.UserFacade;
import com.spm.vasylyshyn.model.User;
import com.spm.vasylyshyn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {



    private final UserService userService;

    private final UserFacade userFacade;

    private final ResponseErrorValidation responseErrorValidation;

    @Autowired
    public UserController(UserService userService, UserFacade userFacade, ResponseErrorValidation responseErrorValidation) {
        this.userService = userService;
        this.userFacade = userFacade;
        this.responseErrorValidation = responseErrorValidation;
    }

    @GetMapping("/")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal){

        User user = userService.getCurrentUser(principal);
        UserDto userDTO = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @GetMapping("/allUser")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> usersDTO = userService.getAllUser().stream().map(userFacade::userToUserDTO).collect(Collectors.toList());
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping("/getDevicesForCurrentUser")
    public ResponseEntity<List<DeviceDto>> getDevicesForCurrentUser(Principal principal){
        List<DeviceDto> deviceDtoList = userService.getDeviceForCurrentUser(principal);
        return new ResponseEntity<>(deviceDtoList,HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable("username") String username ){
        User user = userService.getUserByUsername(username);
        UserDto userDTO = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }
    @GetMapping("/byId/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId ){
        User user = userService.getUserById(userId);
        UserDto userDTO = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDto userDTO, BindingResult bindingResult, Principal principal){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        User user = userService.updateUser(userDTO,principal);
        UserDto userUpdated = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    //    @PostMapping("/addDeviceToUser")
    //    public ResponseEntity<ApiResponse> getDeviceToUser(@RequestParam("device_number") Long deviceNumber, @RequestParam("device_password") String password){
    //
    //    }



}


