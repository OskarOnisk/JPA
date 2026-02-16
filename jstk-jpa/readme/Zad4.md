Zad4 - JPQL


Uzupelnij plik data.sql o dane niezbedne do realizacji nastepujacych zapytan (zapytania umiesc w wybranym przez siebie Repository, uzasadnij wybor):
1. Znajdz pacjentow po nazwisku
2. Znajdz wszystkie wizyty pacjenta po jego ID
3. znajdz pacjentow ktorzy mieli wiecej niz X wizyt (X jest parametrem wejsciowym)


Napisz testy do zapytan w nastepujacej formie:
1. do zapytania nr 1  - test DAO
2. do zapytania nr 2 - test serwisu
3. do zapytania nr 3 - test DAO

Jedno z zapytan ma byc calkowicie przetestowane, do pozostalych wystarczy 'happy path'

Stworz zapytanie korzystajac z JpaSpecification, ktore bedzie wyszukiwac pacjenta po jego polach oraz po description wizyty (porownanie typu 'like')

Do wybranej encji dodaj wersjonowanie