# MeetMe
MeetMe

Todo:
-
- dodanie endpoita do pobrania pytań do quizu
- Dodanie weryfikacji czy login jest zajęty (przy zmianie danych użytkownika)
- Dodanie weryfikacji czy mail jest już wykorzystywany (przy zmianie danych użytkownika)
- Dodanie testów jednostkowych
-

Done:
- dodanie ekranu obsługującego quiz (przeklikiwanie fiszek)
- pousuwać usługi które nie są używane





Backlog:
- dodanie ekranu obsługującego przeglądanie fiszek
- dodanie opcji edycji fiszek
- Rozszerzenie zabezpieczeń o sprawdzanie zawartości tokena o issuer
- Przekazywanie id zalogowanego użytkownika w tokenie jwt
- Zabezpieczenie aplikacji przed sql injection

Backlog - tech debt
- używać zmiennych w css
- poprawić nadmiarowość kodu w wywołaniu usług
- Zmienić wywołania onclick na nasłuchiwanie zdarzeń w javascript(aby oddzielić strukture od zachowania)







DONE_ARCHIVES
*Finiszed 05.01.2024* { 
- dodanie nowego ekranu ekranu obsługującego menu quizu
- dodanie tabeli w bazie danych przechowującej pytania na rozmowy rekrutacyjne
- Odbieranie id zalogowane użytkownika z tokenu jwt w usługach get i save settings
- zmiana danych usera poprzez ekran settings
- dodanie popupu z informacją, który przenosi do strony logowania po wygaśnięciu tokenu
- dodanie weryfikacji tokenu na wszystkich usługach
}