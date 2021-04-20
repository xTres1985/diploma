package pl.wspa.diploma.data.services;


import org.springframework.security.core.Authentication;
import pl.wspa.diploma.data.dao.UserDao;
import pl.wspa.diploma.data.dto.UserDto;

public interface UserService extends BaseService<UserDao, Long> {

    UserDto findUserDtoById(Long l);

    UserDto saveUserDto(UserDto userDto);

    UserDto getUserBasedOnAuth (Authentication authentication);

    boolean isUser(Authentication authentication);

    boolean isAdmin(Authentication authentication);

}
