package by.kostya.entity;

import by.kostya.converter.BirthdayConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class PersonInfo {
    private String firstname;
    private String lastname;
    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_date")
    @NotNull
    private Birthday birthDate;
}
