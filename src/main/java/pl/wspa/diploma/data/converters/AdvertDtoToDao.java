package pl.wspa.diploma.data.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.wspa.diploma.data.dao.AdvertDao;
import pl.wspa.diploma.data.dto.AdvertDto;



@Component
public class AdvertDtoToDao implements Converter<AdvertDto, AdvertDao> {

    private UserDtoToDao userDtoToDao;


    public AdvertDtoToDao(UserDtoToDao userDtoToDao) {
        this.userDtoToDao = userDtoToDao;
    }

    @Synchronized
    @Nullable
    @Override
    public AdvertDao convert(AdvertDto source) {

        if (source == null) {
            return null;
        }

        final AdvertDao advert = new AdvertDao();
        advert.setId(source.getId());
        advert.setTitle(source.getTitle());
        advert.setDescription(source.getDescription());
        advert.setActivity(source.isActivity());
        advert.setPublicationDate(source.getPublicationDate());
        advert.setAdvertType(source.getAdvertType());
        advert.setOfferedItem(source.getOfferedItem());
        advert.setRequestedItem(source.getRequestedItem());
        advert.setPrice(source.getPrice());

        if (source.getUserDto() != null) {
            advert.setUserDao(userDtoToDao.convert(source.getUserDto()));
        }

        return advert;
    }
}

