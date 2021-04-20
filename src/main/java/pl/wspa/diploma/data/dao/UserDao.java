package pl.wspa.diploma.data.dao;


import lombok.*;
import pl.wspa.diploma.security.Authority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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

    @NotBlank
    @Pattern(regexp = "^[0-9a-z_.-]+@[0-9a-z-.]+[.][a-z]{2,}", message = "email is incorrect")
    private String email;
    private String password;

    @Singular
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<Authority> authorities = new HashSet<>();

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
