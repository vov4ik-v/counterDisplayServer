package com.spm.vasylyshyn.repository;

import com.spm.vasylyshyn.dto.user.UserDto;
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
    @Query("select new com.spm.vasylyshyn.dto.user.UserDto(u.id, u.username, u.email, u.phoneNumber, u.imageUrl, u.firstName, u.lastName, u.address) from User as u where u.id = :id")
    Optional<UserDto> findDtoUserById(@Param("id") Long id);
    @Query("select new com.spm.vasylyshyn.dto.user.UserDto(u.id, u.username, u.email, u.phoneNumber, u.imageUrl, u.firstName, u.lastName, u.address) from User as u where u.username = :username")
    Optional<UserDto> findUserDtoByUsername(@Param("username") String username);
    @Query("select new com.spm.vasylyshyn.dto.user.UserDto(u.id, u.username, u.email, u.phoneNumber, u.imageUrl, u.firstName, u.lastName, u.address) from User as u")
    Optional<List<UserDto>> findAllUsersDto();
    List<User> findAllByOrderByCreatedDateDesc();
    Optional<User> findUserById(Long id);

}
