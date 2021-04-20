package pl.wspa.diploma.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String phone;
    private String firstname;
    private String lastname;
    private String address;
    private Byte[] avatarImage;
    private Set<AdvertDto> adverts = new HashSet<>();
}
