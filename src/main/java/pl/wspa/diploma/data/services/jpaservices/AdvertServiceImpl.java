package pl.wspa.diploma.data.services.jpaservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wspa.diploma.data.converters.AdvertDaoToDto;
import pl.wspa.diploma.data.converters.AdvertDtoToDao;
import pl.wspa.diploma.data.dao.AdvertDao;
import pl.wspa.diploma.data.dto.AdvertDto;
import pl.wspa.diploma.data.repositories.AdvertRepository;
import pl.wspa.diploma.data.services.AdvertService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class AdvertServiceImpl implements AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertDtoToDao advertDtoToDao;
    private final AdvertDaoToDto advertDaoToDto;

    public AdvertServiceImpl(AdvertRepository advertRepository, AdvertDtoToDao advertDtoToDao, AdvertDaoToDto advertDaoToDto) {
        this.advertRepository = advertRepository;
        this.advertDtoToDao = advertDtoToDao;
        this.advertDaoToDto = advertDaoToDto;
    }

    @Override
    @Transactional
    public AdvertDto findAdvertDtoById(Long l) {
        return advertDaoToDto.convert(findById(l));
    }

    @Override
    @Transactional
    public AdvertDto saveAdvertDto(AdvertDto advertDto) {
        AdvertDao detachedAdvert = advertDtoToDao.convert(advertDto);

        detachedAdvert.setPublicationDate(LocalDate.now());

        AdvertDao savedAdvert = advertRepository.save(detachedAdvert);

        return advertDaoToDto.convert(savedAdvert);
    }

    @Override
    public Set<AdvertDao> findAll() {
        Set<AdvertDao> adverts = new HashSet<>();
        advertRepository.findAll().iterator().forEachRemaining(adverts::add);
        return adverts;
    }

    @Override
    public AdvertDao findById(Long aLong) {
        return advertRepository.findById(aLong).orElse(null);
    }

    @Override
    public AdvertDao save(AdvertDao object) {
        return advertRepository.save(object);
    }

    @Override
    public void delete(AdvertDao object) {
        advertRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        advertRepository.deleteById(aLong);
    }

}
