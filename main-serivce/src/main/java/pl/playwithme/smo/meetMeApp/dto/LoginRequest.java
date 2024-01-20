package pl.playwithme.smo.meetMeApp.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
