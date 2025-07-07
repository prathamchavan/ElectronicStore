package com.electronic.store.Electronic.Store.services;
import com.electronic.store.Electronic.Store.dtos.UserDto;
import java.util.List;

public interface UserService {

    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto, String userId);

    //delete

    void deleteUser(String userId);

    //get all user

    List<UserDto> getAllUser();
    //get single user by id

    UserDto getUserById(String userId);
    //get single user by email

    UserDto getUserByEmail(String email);
    //search user
    List<UserDto> serchUser(String keyword);

    //other user specific features if you want

}
