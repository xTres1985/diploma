package pl.wspa.diploma.web.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.wspa.diploma.data.dao.AdvertDao;
import pl.wspa.diploma.data.dao.UserDao;
import pl.wspa.diploma.data.dao.enums.AdvertType;
import pl.wspa.diploma.security.Authority;
import pl.wspa.diploma.data.repositories.AdvertRepository;
import pl.wspa.diploma.data.repositories.UserRepository;
import pl.wspa.diploma.data.repositories.security.AuthorityRepository;


import java.time.LocalDate;

@Component
public class InitialDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialDataLoader(UserRepository userRepository, AdvertRepository advertRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.advertRepository = advertRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }


    private void loadSecurityData() {
    }


    @Override
    public void run(String... args) throws Exception {

        if (authorityRepository.count() == 0) {
            loadSecurityData();
        }

        Authority admin = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
        Authority user = authorityRepository.save(Authority.builder().role("ROLE_USER").build());
        Authority customer = authorityRepository.save(Authority.builder().role("ROLE_CUSTOMER").build());

        AdvertDao advert1 = new AdvertDao();
        advert1.setTitle("zamienie London");
        advert1.setDescription("Gra ekonomiczna");
        advert1.setPublicationDate(LocalDate.now());
        advert1.setPrice(140.15);
        advert1.setAdvertType(AdvertType.SELL);
        advert1.setActivity(true);

        AdvertDao advert2 = new AdvertDao();
        advert2.setTitle("kupie 7 Cudów");
        advert2.setDescription("Gra Strategiczna");
        advert2.setPublicationDate(LocalDate.now());
        advert2.setPrice(99.75);
        advert2.setAdvertType(AdvertType.BUY);
        advert2.setActivity(true);

        AdvertDao advert3 = new AdvertDao();
        advert3.setTitle("zamienie magia i miecz i dodatki");
        advert3.setDescription("gra wraz z pudełkiem");
        advert3.setPublicationDate(LocalDate.now());
        advert3.setOfferedItem("siekierka");
        advert3.setRequestedItem("kijek");
        advert3.setAdvertType(AdvertType.EXCHANGE);
        advert3.setActivity(true);

        AdvertDao advert4 = new AdvertDao();
        advert4.setTitle("najemnikow kupie");
        advert4.setDescription("Gra Strategiczna");
        advert4.setPublicationDate(LocalDate.now());
        advert4.setRequestedItem("talon");
        advert4.setOfferedItem("balon");
        advert4.setAdvertType(AdvertType.EXCHANGE);
        advert4.setActivity(false);

        AdvertDao advert5 = new AdvertDao();
        advert5.setTitle("kupie magia i miecz");
        advert5.setDescription("gra wraz z pudełkiem");
        advert5.setPublicationDate(LocalDate.now());
        advert5.setAdvertType(AdvertType.SERVICE);
        advert5.setActivity(false);

        AdvertDao advert6 = new AdvertDao();
        advert6.setTitle("najemnikow sprzedam plus dodatki");
        advert6.setDescription("Gra Strategiczna");
        advert6.setPublicationDate(LocalDate.now());
        advert6.setAdvertType(AdvertType.SEARCH);
        advert6.setActivity(true);

        UserDao userDao1 = userRepository.save(UserDao.builder()
                .email("admin@pl")
                .password(passwordEncoder.encode("pass"))
                .authority(admin)
                .nickname("pan koza")
                .phone("123 456 789")
                .firstname("jan")
                .lastname("kowalski")
                .address("kozia wolka")
                .advert(advert1)
                .advert(advert2)
                .advert(advert3)
                .build());

        UserDao userDao2 = userRepository.save(UserDao.builder()
                .email("user@com")
                .password(passwordEncoder.encode("pass"))
                .authority(user)
                .nickname("pan owca")
                .phone("987 654 321")
                .firstname("adam")
                .lastname("mickiewicz")
                .address("rozgraty wielkie")
                .advert(advert4)
                .advert(advert5)
                .advert(advert6)
                .build());

//        userDao1.getAdverts().add(advert1);
//        userDao1.getAdverts().add(advert3);
//        userDao1.getAdverts().add(advert5);
//
//        userDao2.getAdverts().add(advert2);
//        userDao2.getAdverts().add(advert4);
//        userDao2.getAdverts().add(advert6);

        advert1.setUserDao(userDao1);
        advert2.setUserDao(userDao2);
        advert3.setUserDao(userDao1);
        advert4.setUserDao(userDao2);
        advert5.setUserDao(userDao1);
        advert6.setUserDao(userDao2);


        userRepository.save(userDao1);
        userRepository.save(userDao2);

        advertRepository.save(advert1);
        advertRepository.save(advert2);
        advertRepository.save(advert3);
        advertRepository.save(advert4);
        advertRepository.save(advert5);
        advertRepository.save(advert6);

        System.out.println(userDao1.getEmail());
        System.out.println(userDao2.getEmail());
        System.out.println(passwordEncoder.encode(userDao1.getPassword()));
        System.out.println(passwordEncoder.encode(userDao2.getPassword()));
    }

}
