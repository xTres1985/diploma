package pl.wspa.diploma.data.services;

import org.springframework.web.multipart.MultipartFile;

public interface AvatarImageService {

    void saveAvatarImageFile(Long userId, MultipartFile file);

}
