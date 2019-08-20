package com.github.xenteros.misc;

import lombok.*;
import org.apache.log4j.Logger;

import static java.lang.String.format;

@Builder
@Setter
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
class User {

    private static Logger LOG = Logger.getLogger(User.class);

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String country;

    public void updateAdress(String street, String city, String country) {
        LOG.error(format("Updating %s %s address to %s %s %s",
                firstName, lastName, street, city, country));
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public static void main(String[] args) {
        User jan = User.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .country("Poland")
                .build();

        User danuta = new User();
        danuta.setFirstName("Danuta");
        danuta.setLastName("Nowak");
        danuta.setCountry("Poland");

        jan.updateAdress("Ruska", "Wrocław", "Poland");
    }
}
