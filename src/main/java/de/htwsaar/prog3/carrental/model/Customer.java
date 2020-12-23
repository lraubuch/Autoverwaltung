package de.htwsaar.prog3.carrental.model;

import de.htwsaar.prog3.carrental.model.validation.MinAge;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Customer object model (JPA entity).
 *
 * @author Julian Quint
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    private String street;

    @Digits(integer = 5, fraction = 0)
    @Column(nullable = false)
    private int zipcode;

    @NotBlank
    @Column(nullable = false)
    private String city;

    @NotBlank
    @Pattern(regexp = "\\+\\p{Digit}+")
    @Column(nullable = false)
    private String phone;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @NotNull
    @MinAge(18)
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank
    @Pattern(regexp = "\\p{Alnum}{9}")
    @Column(nullable = false, unique = true)
    private String idNumber;

    @NotBlank
    @Pattern(regexp = "\\p{Alnum}{11}")
    @Column(nullable = false, unique = true)
    private String driverLicenseNumber;

    @Override
    public String toString() {
        return "%s %s".formatted(firstName, lastName);
    }
}
