package pl.wspa.diploma.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wspa.diploma.data.dao.enums.AdvertType;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AdvertDto {


    private Long id;
    private String title;
    private String description;
    private boolean activity;
    private LocalDate publicationDate;
    private AdvertType advertType;
    private String offeredItem;
    private String requestedItem;
    private Double price;
    private UserDto userDto;
    private Byte[] Image;
}
