# MAS-MDP3

<a name="br1"></a> 

Zarządzanie kontami użytkowników „Acꢀve Account Manager”

\1) Informacje ogólne

1\. Dziedzina problemowa

System został opracowany w celu wspierania małych i średnich ﬁrm w zarządzaniu

kontami swoich pracowników oraz współpracowników.

2\. Cel

System powinien w łatwy sposób zapewnić tworzenie kont oraz nimi zarządzanie.

Udostępnia przejrzysty i intuicyjny interfejs użytkownika pozwalający pracownikom działu

HR w przyjemny sposób przeprowadzić proces onboardingu i oꢁoardingu. Dodatkowo

zapewnia kontrole nad kontami gości i użytkowników z organizacji zewnętrznych

świadczących usługi outsourcingowe.

3\. Zakres odpowiedzialności systemu

System powinien umożliwić:A

•

•

•

•

Zakładanie kont pracownikom i współpracownikom(HR),

Archiwizowanie kont byłych pracowników(HR),

Tworzenie skrzynek pocztowych przypisanych do kont pracowników(IT),

Tworzenie grup dystrybucyjnych, do których mogą być dodawani istniejący

użytkownicy(IT),

•

Tworzenie grup zabezpieczeń uprawniających do zestawów danych(IT).

4\. Użytkownicy systemu

Użytkownikami systemu będą pracownicy działów HR oraz IT.

5\. Wymagania użytkownika

a) W systemie należy przechowywać informację o użytkownikach z podziałem na

użytkowników wewnętrznych, użytkownikach zewnętrznych oraz użytkownikach

gościach.

b) Dla każdego użytkownika przechowywane są informację następujące informajcje:

name, lastName, hireDate, phoneNumber, addresses, userCounter, departments,

userType.

c) Użytkownicy mogą przyjmować różne typy *Standard*, *Contributor*, *LocalAdmin*, *GlobalAdmin.*

d) Do każdego konta może zostać utworzona jedna skrzynka pocztowa przechowujące informację o

Nazwie, Nazwie Wyświetlanej i Adresie e-mail. Skzynka nie może istnieć bez konta.

e) Dla pracowników wewnętrznych należy przechowywać informację o przełożonych.

f) Dla pracowników zewnętrznych należy przechowywać o dacie wystawienia następnej

faktury.

g) Dla kont gościa należy przechowywać informację o domenie organizacji, z której

pochodzą.

h) Podział użytkowników jest kompletny.

i) Użytkownicy mogą zostać przypisani do wielu grupy.

j) Grupy dzielą się na zabezpieczeń i mailingowe. Dla grupy mailingowej dodatkowo

musimy przechowywać informację o jej adresie mailowym.

k) Grupa zabezpieczeń może zostać zmieniona na grupę mailingową i odwrotnie.

System powinien umożliwić użytkownikom:

-Zakładanie kont pracownikom i współpracownikom(HR),

\- Archiwizowanie kont byłych pracowników(HR),



<a name="br2"></a> 

\- Tworzenie skrzynek pocztowych przypisanych do kont pracowników(IT),

\- Tworzenie grup dystrybucyjnych, do których mogą być dodawani istniejący użytkownicy(IT),

-Tworzenie grup zabezpieczeń uprawniających do zestawów danych(IT),

\- Wyświetlenie czasu zatrudnienia dla poszczególnego konta(HR),

\- wyświetlenie listy działów, do których przypisany jest użytkownik(HR),

\- wyświetlenie najdłużej pracującego użytkownika(HR),

\- dodać użytkowników do grupy (IT),

\- dodać pojedynczego użytkownika do grupy (IT),



<a name="br3"></a> 

\2) Diagram przypadków użycia



<a name="br4"></a> 

\3) Diagram klas – analityczny

\4) Diagram projektowy



<a name="br5"></a> 

\5) Projekt GUI



<a name="br6"></a> 



<a name="br7"></a> 



<a name="br8"></a> 

\6) Scenariusz przypadku użycia jako tekst

Ta sekcja opisuje tekstowo przypadek użycia *utworzenie konta użytkownika gościa.*

Po wprowadzeniu podstawowych danych tj. imię, nazwisko, numer telefonu pracownik działu HR

przechodzi do zakładki HR Info gdzie musi wybrać odpowiednią opcje czyli External Guest User. Po

wybraniu odblokowywane są pola HireDate, Department i Domain Name. Dla kont typu gość

zbieramy informację o domenie organizacji, z której pochodzi. Musimy go przypisać do jednego z

działów w ﬁrmie, z którym gość będzie współpracował. Dział wyświetlany jest przez system w postaci

listy rozwijanej. Następnie pracownik tworzący konto musi zmienić dane adresowe, które są inne niż

domyślnie wyświetlane. Po wciśnięciu przycisku „Create User”, pracownik otrzymuję posumowanie

dotyczące utworzenia konta pod warunkiem, że poprawnie wypełnił formularz, w przeciwnym

wypadku otrzymuję informację o błędach, a źle wypełnione pola podświetlają się w kolorze

czerwonym.

\7) Diagram interakcji (sekwencji) dla przypadku użycia *utworzenie konta użytkownika gościa*



<a name="br9"></a> 

8\. Diagram stanu klasy User



<a name="br10"></a> 

\9) Omówienie decyzji projektowych i skutków analizy dynamicznej

Analityczny diagram klas zawiera konstrukcje, które nie występują w jezyku

programowania Java, w którym system zostanie zrealizowany. Na projektowym

diagramie klas te konstrukcje zostały przekształcone w następujący sposób:

•

Dziedziczenie dynamiczne – podejście ze sprytnym kopiowaniem obiektów. Takie

podejście jest proste w implementacji i nie wymaga nadmiernej ilości kodu. Klasa

przyjmuję w konstruktorze obiekt innej klasy tego samego poziomu.

Dziedziczenie overlapping - spłaszczenie hierarchii i dodanie pola typu

EnumSet<UserType>, które odpowiada uprawnieniom użytkownika w systemie.

Uprawnienia mają klika poziomów od standardowego użytkownika, po

globalnego administratora. Takie podejście gwarantuję możliwość szybkiej zmiany

w razie potrzeby (wystarczy wywołać seter dla interesującego nas obiektu),

ponadto mamy tutaj łatwość dodawanie kolejnych typów podczas rozbudowy

sytemu.

•

•

•

•

Kompozycja skrzynki -> user zaimplementowana zostanie jako klasa wewnętrzna z

prywatnymi polami i konstruktorem. Takie rozwiązanie zapewnia brak możliwości

istnienia części bez całości.

Wielodzidziczenie zaimplementowane przy użyciu interfejsu oraz dziedziczenia.

Takie rozwiązanie jest proste w implementacji i zapewnia łatwość przesłaniania

metod.

Zwykła asocjacja – implementacja za pomocą kolekcji.


