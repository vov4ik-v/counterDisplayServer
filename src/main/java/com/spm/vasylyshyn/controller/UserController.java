package com.spm.vasylyshyn.controller;



import com.spm.vasylyshyn.response.ApiResponse;
import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.enums.CounterType;
import com.spm.vasylyshyn.request.RegisterDeviceRequest;
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
        UserDto userDto = userService.getCurrentUserDto(principal);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @GetMapping("/allUser")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> usersDTO = userService.getAllUserDto();
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping("/getDevicesForCurrentUser")
    public ResponseEntity<List<DeviceDto>> getDevicesForCurrentUser(Principal principal){
        List<DeviceDto> deviceDtoList = userService.getDeviceForCurrentUser(principal);
        return new ResponseEntity<>(deviceDtoList,HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable("username") String username ){
        UserDto userDto = userService.getUserDtoByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }
    @GetMapping("/byId/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId ){
        UserDto userDTO = userService.getUserDtoById(userId);
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

    @PostMapping("/addDeviceToUser")
        public ResponseEntity<ApiResponse> addDeviceToUser(@RequestBody RegisterDeviceRequest registerDeviceRequest, Principal principal){
           ApiResponse response =  userService.addDeviceToUser(registerDeviceRequest.getDeviceNumber()
                   ,registerDeviceRequest.getDeviceType(),registerDeviceRequest.getAddress()
//                   ,registerDeviceRequest.getPassword()
                   ,principal);
           return new ResponseEntity<>(response,HttpStatus.OK);
        }



}


