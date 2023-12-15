package pl.playwithme.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.playwithme.smo.DBEntity.User;
import pl.playwithme.smo.Database.DBRepository;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    DBRepository dbRepository;

    @GetMapping("/user")
    public List<User> getAll() {
        return dbRepository.getAll();
    }

    @GetMapping("/test")
    public String getUser() {
        return "TEST";
    }

}
