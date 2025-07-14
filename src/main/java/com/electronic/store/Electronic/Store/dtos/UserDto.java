package com.electronic.store.Electronic.Store.dtos;

import com.electronic.store.Electronic.Store.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {

    private String userId;

    @Size(min=3, max=50, message="Invalid name.!!")
    private String name;

//    @Email(message="Invalid User Email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message="Invalid email id!!")
    @NotBlank(message = "Email can not be blank")
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;

    @Size(min = 4, max = 6, message = "Invalid gender!!")
    private String gender;

    @NotBlank(message = "Write something about yourself!")
    private String about;

    //@Pattern
    //Custom validator

    @ImageNameValid
    private String imageName;
    
}
