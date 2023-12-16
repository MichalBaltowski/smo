package pl.playwithme.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.playwithme.smo.DBEntity.User;
import pl.playwithme.smo.Database.DBRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    DBRepository dbRepository;

    @GetMapping()
    public ResponseEntity getAll() {
        return dbRepository.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") int id) {
        return dbRepository.getById(id);
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody List<User> users) {
        return dbRepository.save(users);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable int id,
                          @RequestBody User user) {
        return dbRepository.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
       return dbRepository.delete(id);
    }
}
