Laboratorium II - Architektura warstwowa, EntityManager oraz testy

1. Korzystajac z przykladowego kodu dla encji AddressEntity utworz warstwy dostepu do danych (Repository, Service, mappery, TOsy, opcjonalnie RESTy - dla chetnych) dla encji PatientEntity, DoctorEntity, VisitEntity. Spelnione maja byc nasteoujace wymagania:
   - TO pacjenta ma miec podstawowe dane o pacjencie (z tabeli)
   - TO pacjenta ma miec liste wizyt
   - TO lekarza ma miec podstawowe informacje o lekarzu (z tabeli)
   - kazda wizyta ma miec informacje o czasie (daty), imie i nazwisko lekarza oraz liste typow (z encji MedicalTreatment)
2. Korzystajac z przykladowych insertow w pliku data.sql uzupelnij encje pacjenta, doktorow oraz wizyt danymi testowymi
3. Stworz metode w serwisie i kontrolerze findById() dla pacjenta, ktora przekazuje bezposrednio encje pacjenta do Kontrolera i wysyla przez zapyzanie HTTP dalej. Przetestuj przez przegladarke, wyjasnij rezultaty.
4. Napisz testy do klas Repository:
   - kaskadowy zapis pacjenta i wizyty
   - kaskadowe usuwanie pacjenta i wizyty (pomysl co powinno byc sprawdzone w asercjach)