package pl.playwithme.smo;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
