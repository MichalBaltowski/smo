package pl.playwithme.smo;

import lombok.Data;

@Data
public class SaveSettingsRequest {

    private String id;
    private String login;
    private String password;
    private String email;
}
