package shop.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import shop.serializeble.DataSerializable;

import java.time.LocalDate;

public class UserDto {
    private String login;
    private String email;
    @JsonSerialize(using = DataSerializable.class)
    private LocalDate birthday;

    public UserDto() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
