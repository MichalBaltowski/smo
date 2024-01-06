# MeetMe


## Todo:
- {Front} Dodać obsługe otrzymanych pytań w quiz (pokazywanie samego pytania, pokazywanie odpowiedzi po kliknięciu, 
  pokazywanie następnego pytania po kliknięciu)
- Dodać wszystkie pytania do bazy danych
- Użyć Hibernate we wszystkich połączeniach z bazą danych

## Done:
- dodanie ekranu obsługującego quiz (przeklikiwanie fiszek)
- pousuwać usługi które nie są używane
- dodanie endpoita do pobrania pytań do quizu
- użycie hibernate przy pobieraniu pytań z bazy danych





## Backlog:
- dodanie ekranu obsługującego przeglądanie fiszek
- dodanie opcji edycji fiszek
- Rozszerzenie zabezpieczeń o sprawdzanie zawartości tokena o issuer
- Przekazywanie id zalogowanego użytkownika w tokenie jwt
- Zabezpieczenie aplikacji przed sql injection
- Dodanie weryfikacji czy login jest zajęty (przy zmianie danych użytkownika)
- Dodanie weryfikacji czy mail jest już wykorzystywany (przy zmianie danych użytkownika)
- Dodanie testów jednostkowych

## Backlog - tech debt
- używać zmiennych w css (dowiedzieć się jak to się robi, kiedy warto, jakie są dobre praktyki i to zastosować w projekcie)
- Zmienić wywołania onclick na nasłuchiwanie zdarzeń w javascript(aby oddzielić strukture od zachowania)
