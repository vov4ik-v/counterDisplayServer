package com.spm.vasylyshyn.repository;

import com.spm.vasylyshyn.dto.UserDto;
import com.spm.vasylyshyn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);

    @Query("select new com.spm.vasylyshyn.dto.UserDto(u.id,u.username,u.phoneNumber,u.email,u.address) from User as u")
    Optional<UserDto> findDtoUserById(Long id);

    @Query("select new com.spm.vasylyshyn.dto.UserDto(u.id,u.username,u.phoneNumber,u.email,u.address) from User as u")
    Optional<UserDto> findDtoUserByUsername(String username);
    List<User> findAllByOrderByCreatedDateDesc();
    Optional<User> findUserById(Long id);




}
