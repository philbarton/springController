package uk.co.maclon.claimantService.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class Claimant {

    @NotBlank(message = "Field cannot be empty")
    private String nino;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

    private Title title;
    @NotBlank(message = "Field cannot be empty")
    private String firstName;
    @NotBlank(message = "Field cannot be empty")
    private String lastName;

    private String middleName;
    private Gender gender;
    private RelationshipStatus relationshipStatus;

    private Boolean hasPartner;
    private Boolean hasSavings;
}
