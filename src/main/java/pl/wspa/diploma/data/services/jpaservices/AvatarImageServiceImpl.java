package pl.wspa.diploma.data.services.jpaservices;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.wspa.diploma.data.dao.UserDao;
import pl.wspa.diploma.data.repositories.UserRepository;

import java.io.IOException;

@Service
public class AvatarImageServiceImpl implements pl.wspa.diploma.data.services.AvatarImageService {

    UserRepository userRepository;

    public AvatarImageServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveAvatarImageFile(Long userId, MultipartFile avatarFile) {

        try {
            UserDao user = userRepository.findById(userId).get();

            Byte[] byteObject = new Byte[avatarFile.getBytes().length];

            int i = 0;

            for (byte b : avatarFile.getBytes()) {
                byteObject[i++] = b;
            }

            user.setAvatarImage(byteObject);

            userRepository.save(user);
        } catch (IOException e) {

        }
    }
}
