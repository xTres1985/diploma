package pl.wspa.diploma.data.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.wspa.diploma.data.dao.UserDao;
import pl.wspa.diploma.data.dto.UserDto;

@Component
public class UserDaoToDto implements Converter<UserDao, UserDto> {


    private final PasswordEncoder passwordEncoder;

    public UserDaoToDto(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Synchronized
    @Nullable
    @Override
    public UserDto convert(UserDao source) {

        if (source == null) {

            return null;
        }

        final UserDto userDto = new UserDto();
        userDto.setId(source.getId());
        userDto.setEmail(source.getEmail());
        userDto.setNickname(source.getNickname());
        userDto.setPhone(source.getPhone());
        userDto.setFirstname(source.getFirstname());
        userDto.setLastname(source.getLastname());
        userDto.setAddress(source.getAddress());
        userDto.setAvatarImage(source.getAvatarImage());

//
//        if(source.getAdverts() != null && source.getAdverts().size() > 0) {
//            source.getAdverts()
//                    .forEach((AdvertDao advert) -> userDto.getAdverts().add(advertDaoToDto.convert(advert)));
//        }

        return userDto;
    }
}
