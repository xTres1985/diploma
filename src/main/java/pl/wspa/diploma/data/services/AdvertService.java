package pl.wspa.diploma.data.services;

import pl.wspa.diploma.data.dao.AdvertDao;
import pl.wspa.diploma.data.dto.AdvertDto;


public interface AdvertService extends BaseService<AdvertDao, Long> {

    AdvertDto findAdvertDtoById(Long l);

    AdvertDto saveAdvertDto(AdvertDto advertDto);

}
