package pl.wspa.diploma.data.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.wspa.diploma.data.dao.UserDao;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserDao, Long> {

    Optional<UserDao> findByEmail(String email);
}
