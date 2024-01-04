# MeetMe
MeetMe

Todo:
- dodanie tabeli w bazie danych przechowującej pytania na rozmowy rekrutacyjne
- dodanie nowego ekranu ekranu obsługującego quiz wiedzy


Done:
- Odbieranie id zalogowane użytkownika z tokenu jwt w usługach get i save settings
- zmiana danych usera poprzez ekran settings
- dodanie popupu z informacją, który przenosi do strony logowania po wygaśnięciu tokenu
- dodanie weryfikacji tokenu na wszystkich usługach



Backlog:
- rozszerzenie zabezpieczeń o sprawdzanie zawartości tokena o issuer
- Przekazywanie id zalogowanego użytkownika w tokenie jwt
- Dodanie weryfikacji czy login jest zajęty (przy zmianie danych użytkownika)
- Dodanie weryfikacji czy mail jest już wykorzystywany (przy zmianie danych użytkownika)
- Zabezpieczenie aplikacji przed sql injection