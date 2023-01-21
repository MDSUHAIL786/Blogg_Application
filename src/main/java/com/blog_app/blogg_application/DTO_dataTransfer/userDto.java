package com.blog_app.blogg_application.DTO_dataTransfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


// this class we use for the purpose of data transfer like data send to api response and define parameters of entity which we want to send in reponse like we dont want to send password so we remove  variable from here but we cannot remove from actual entity that's why we create this Dao
@NoArgsConstructor
@Getter
@Setter
public class userDto {

    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Email(message = "enter valid email")
    private String email;

    @NotEmpty
    @Size(min = 8,max = 15,message = "password must be greater than 8 and less than equal to 15")
    private String password;

    @NotNull
    private String about;
}
