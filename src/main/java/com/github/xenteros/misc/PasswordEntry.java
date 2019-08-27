package com.github.xenteros.misc;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class PasswordEntry {

    String website;
    String password;

    public String toCsvString() {
        String quote = "\"";
        return quote + this.website + quote + ";" + quote + this.password + quote;
    }
}
