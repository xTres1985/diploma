package pl.wspa.diploma.data.dao;


import lombok.*;
import pl.wspa.diploma.security.Authority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String email;
    private String password;

    @Singular
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_authority",
    joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
    inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<Authority> authorities;

    private String nickname;
    private String phone;
    private String firstname;
    private String lastname;
    private String address;

    @Lob
    private Byte[] avatarImage;

    @Singular
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDao")
    private Set<AdvertDao> adverts = new HashSet<>();




}
