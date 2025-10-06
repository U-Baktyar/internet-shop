package shop.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import shop.serializeble.DataDeserializer;
import shop.serializeble.DataSerializable;

import java.time.LocalDate;

public class UpdateProfileUserDto {

    private String email;

    @JsonDeserialize(using = DataDeserializer.class)
    private LocalDate birthday;

    public UpdateProfileUserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
