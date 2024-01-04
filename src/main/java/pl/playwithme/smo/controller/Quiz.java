package pl.playwithme.smo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.playwithme.smo.DBEntity.Card;
import pl.playwithme.smo.Database.DBRepository;


@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*")
public class Quiz {

    @Autowired
    DBRepository dbRepository;

    @PostMapping("/addCard")
    public ResponseEntity add(@RequestHeader("Authorization") String authorizationHeader,
                              @RequestBody Card card) {
        return dbRepository.addCard(authorizationHeader, card);
    }
}
