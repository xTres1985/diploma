package pl.wspa.diploma.data.repositories.security;

import org.springframework.data.repository.CrudRepository;
import pl.wspa.diploma.security.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
