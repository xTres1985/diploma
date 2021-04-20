package pl.wspa.diploma.data.converters;

import lombok.Synchronized;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.wspa.diploma.data.dao.UserDao;
import pl.wspa.diploma.data.dto.UserDto;

@Component
public class UserDtoToDao implements Converter<UserDto, UserDao> {


    private final PasswordEncoder passwordEncoder;

    public UserDtoToDao(PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
    }

    @Synchronized
    @Nullable
    @Override
    public UserDao convert(UserDto source) {

        if (source == null) {
            return null;
        }

        final UserDao user = new UserDao();
        user.setId(source.getId());
        user.setEmail(source.getEmail());

        if (source.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(source.getPassword()));
        }

        user.setNickname(source.getNickname());
        user.setPhone(source.getPhone());
        user.setFirstname(source.getFirstname());
        user.setLastname(source.getLastname());
        user.setAddress(source.getAddress());

//        if (source.getAdverts() != null && source.getAdverts().size() > 0) {
//            source.getAdverts()
//                    .forEach((AdvertDto advertDto) -> user.getAdverts().add(advertDtoToDao.convert(advertDto)));
//        }

        return user;
    }


}
