package com.electronic.store.Electronic.Store.services.impl;
import com.electronic.store.Electronic.Store.dtos.PageableResponse;
import com.electronic.store.Electronic.Store.dtos.UserDto;
import com.electronic.store.Electronic.Store.entities.User;
import com.electronic.store.Electronic.Store.exceptions.ResourceNotFoundException;
import com.electronic.store.Electronic.Store.helper.Helper;
import com.electronic.store.Electronic.Store.repositories.UserRepository;
import com.electronic.store.Electronic.Store.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Value("${user.profile.image.path}")
    private String imagePath;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto createUser(UserDto userDto) {

        //generate unique id in string format
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);
       // dto -> entity
        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user);

        //entity -> dto
        UserDto newDto = entityToDto(savedUser);
        return newDto;

    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
       User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with this id"));
       user.setName(userDto.getName());
       user.setGender(userDto.getGender());
       user.setPassword(userDto.getPassword());
       user.setImageName(userDto.getImageName());

       //save data
        User UpdatedUser = userRepository.save(user);
        UserDto updatedDto  = entityToDto(UpdatedUser);
        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with this id"));

        //delete user profile
        //images/user/abc.png
        String fullPath = imagePath+user.getImageName();

        try
        {
            Path path = Paths.get(fullPath);
            Files.delete(path);
        }
        catch (NoSuchFileException ex) {
            logger.info(("User image is not found in folder"));
            ex.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //delete user
        userRepository.delete(user);


    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {


        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending()) ;
//        pageNumber default starts from 0
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> page = userRepository.findAll(pageable);


        PageableResponse<UserDto> response =Helper.getPageableResponse(page, UserDto.class);
        return response;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with this id!!"));
        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
       User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with the given email id!!"));

        return entityToDto(user);
    }

    @Override
    public List<UserDto> serchUser(String keyword) {
       List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    private UserDto entityToDto(User savedUser) {

//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .gender(savedUser.getImageName())
//                .build();

        return mapper.map(savedUser, UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {
//        User user  = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();
        return mapper.map(userDto, User.class);
    }

}
