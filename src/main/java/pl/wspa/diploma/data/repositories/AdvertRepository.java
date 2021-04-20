package pl.wspa.diploma.data.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.wspa.diploma.data.dao.AdvertDao;

public interface AdvertRepository extends CrudRepository<AdvertDao, Long> {
}
