package pl.wspa.diploma.data.services.jpaservices;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wspa.diploma.data.converters.UserDaoToDto;
import pl.wspa.diploma.data.converters.UserDtoToDao;
import pl.wspa.diploma.data.dao.UserDao;
import pl.wspa.diploma.data.dto.UserDto;
import pl.wspa.diploma.data.repositories.UserRepository;
import pl.wspa.diploma.data.repositories.security.AuthorityRepository;
import pl.wspa.diploma.data.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final UserDtoToDao userDtoToDao;
    private final UserDaoToDto userDaoToDto;

    public UserServiceImpl(AuthorityRepository authorityRepository,
                           UserRepository userRepository,
                           UserDtoToDao userDtoToDao,
                           UserDaoToDto userDaoToDto) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.userDtoToDao = userDtoToDao;
        this.userDaoToDto = userDaoToDto;
    }

    @Override
    @Transactional
    public UserDto findUserDtoById(Long l) {

        return userDaoToDto.convert(findById(l));
    }

    @Override
    @Transactional
    public UserDto saveUserDto(UserDto userDto) {
        UserDao detachedUser = userDtoToDao.convert(userDto);
        detachedUser.getAuthorities().add(authorityRepository.findById(2l).get());
        UserDao savedUser = userRepository.save(detachedUser);

        return userDaoToDto.convert(savedUser);
    }

    @Override
    public UserDto getUserBasedOnAuth(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        return userDaoToDto.convert(userRepository.findByEmail(principal.getUsername()).get());
    }

    @Override
    public Set<UserDao> findAll() {
        Set<UserDao> users = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    @Override
    public UserDao findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }

    @Override
    public UserDao save(UserDao object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(UserDao object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }


    public boolean isUser(Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getAuthorities()
                .stream()
                .anyMatch(o -> ((GrantedAuthority) o).getAuthority().equals("ROLE_USER"));
    }

    public boolean isAdmin(Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getAuthorities()
                .stream()
                .anyMatch(o -> ((GrantedAuthority) o).getAuthority().equals("ROLE_ADMIN"));
    }


}
