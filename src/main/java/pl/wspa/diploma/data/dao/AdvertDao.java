package pl.wspa.diploma.data.dao;

import lombok.*;
import pl.wspa.diploma.data.dao.enums.AdvertType;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AdvertDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;
    private boolean activity;
    private LocalDate publicationDate;

    @Enumerated
    private AdvertType advertType;

    private String offeredItem;
    private String requestedItem;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "userDao_id")
    private UserDao userDao;

    @Lob
    private Byte[] Image;


}
