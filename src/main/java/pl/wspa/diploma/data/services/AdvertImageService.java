package pl.wspa.diploma.data.services;

import org.springframework.web.multipart.MultipartFile;

public interface AdvertImageService {

    void saveAdvertImageFile(Long advertId, MultipartFile file);

}
