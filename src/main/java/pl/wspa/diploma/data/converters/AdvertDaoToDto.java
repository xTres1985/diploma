package pl.wspa.diploma.data.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.wspa.diploma.data.dao.AdvertDao;
import pl.wspa.diploma.data.dto.AdvertDto;
import pl.wspa.diploma.data.services.UserService;

@Component
public class AdvertDaoToDto implements Converter<AdvertDao, AdvertDto> {

    private UserDaoToDto userDaoToDto;

    public AdvertDaoToDto(UserDaoToDto userDaoToDto) {
        this.userDaoToDto = userDaoToDto;

    }

    @Synchronized
    @Nullable
    @Override
    public AdvertDto convert(AdvertDao source) {

        if (source == null) {
            return null;
        }

        final AdvertDto advertDto = new AdvertDto();
        advertDto.setId(source.getId());
        advertDto.setTitle(source.getTitle());
        advertDto.setDescription(source.getDescription());
        advertDto.setActivity(source.isActivity());
        advertDto.setPublicationDate(source.getPublicationDate());
        advertDto.setAdvertType(source.getAdvertType());
        advertDto.setOfferedItem(source.getOfferedItem());
        advertDto.setRequestedItem(source.getRequestedItem());
        advertDto.setPrice(source.getPrice());
        advertDto.setImage(source.getImage());

        if (source.getUserDao() != null) {
            advertDto.setUserDto(userDaoToDto.convert(source.getUserDao()));
        }


        return advertDto;
    }


}
