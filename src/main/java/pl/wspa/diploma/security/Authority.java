package pl.wspa.diploma.security;

import lombok.*;
import pl.wspa.diploma.data.dao.UserDao;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @ManyToMany(mappedBy = "authorities")
    private Set<UserDao> users = new HashSet<>();
}
