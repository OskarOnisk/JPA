Zad3 - Architektura warstwowa, CRUD oraz testy

1. Korzystajac z napisanych testow Repository, napisz testy do serwisu pacjenta:
   - test usuwajacy pacjenta sprawdza czy usuniete zostaly wszystkie wizyty (kaskada) i czy nie zostali usunieci doktorzy
   - pobranie pacjenta po ID powinno zwrocic strukture TO-sow odpowiadajaca wczesniejszym zalozeniom
2. Dodaj metode w PatientDao, ktora na podstawie parametrow wejsciowych:

   ID pacjenta, ID doktora, data wizyty, opis wizyty 

   utworzy nowa encje wizyty i doda ja do pacjenta w jednym wywolaniu - kaskadowy update pacjenta (merge). 

   Napisz test do tej metody (Dao)
3. Stworz metode serwisowa dla doktora, ktora wykonuje update encji doktora. Stworz do tego nowy TO doktora 1:1 z encja z bazy, bez zadnych relacji/kluczy obcych, ktory to TO bedzie wykorzystany jako zrodlo danych do updateu encji w bazie.
