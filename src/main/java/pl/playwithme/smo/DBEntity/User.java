package pl.playwithme.smo.DBEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String name;
    private String password;

    public String getId() {
        return String.valueOf(id);
    }
}
