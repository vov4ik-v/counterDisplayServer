package com.spm.vasylyshyn.repository;

import com.spm.vasylyshyn.dto.UserDto;
import com.spm.vasylyshyn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);

    @Query("select new com.spm.vasylyshyn.dto.UserDto(u.id,u.username,u.firstName, u.lastName,u.phoneNumber,u.email,u.address,u.createdDate) from User as u")
    Optional<UserDto> findDtoUserById(Long id);

    @Query("select new com.spm.vasylyshyn.dto.UserDto(u.id,u.username,u.firstName, u.lastName,u.phoneNumber,u.email,u.address,u.createdDate) from User as u where u.username = :username")
    Optional<UserDto> findUserDtoByUsername(@Param("username") String username);


    @Query("select new com.spm.vasylyshyn.dto.UserDto(u.id,u.username,u.firstName, u.lastName,u.phoneNumber,u.email,u.address,u.createdDate) from User as u")
    Optional<List<UserDto>> findAllUsersDto();
    List<User> findAllByOrderByCreatedDateDesc();
    Optional<User> findUserById(Long id);

}
