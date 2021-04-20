package pl.wspa.diploma.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "email is mandatory field")
    @Pattern(regexp = "^[0-9a-z_.-]+@[0-9a-z-.]+[.][a-z]{2,}", message = "email is incorrect")
    private String email;

    @Size(min = 6, message = "password must have at least {min} characters")
    @NotBlank(message = "password is mandatory field")
    private String password;

    private String nickname;
    private String phone;
    private String firstname;
    private String lastname;
    private String address;
    private Byte[] avatarImage;
    private Set<AdvertDto> adverts = new HashSet<>();
}
