package pl.wspa.diploma.data.services.jpaservices;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.wspa.diploma.data.dao.AdvertDao;
import pl.wspa.diploma.data.repositories.AdvertRepository;
import pl.wspa.diploma.data.services.AdvertImageService;

import java.io.IOException;

@Service
public class AdvertImageServiceImpl implements AdvertImageService {

    AdvertRepository advertRepository;

    public AdvertImageServiceImpl(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Override
    public void saveAdvertImageFile(Long advertId, MultipartFile file) {

        try {
            AdvertDao advert = advertRepository.findById(advertId).get();

            Byte[] byteObject = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }

            advert.setImage(byteObject);

            advertRepository.save(advert);
        } catch (IOException e) {

        }
    }

}

