import com.ljuslin.app.WigellApplication;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        Application.launch(WigellApplication.class, args);
    }
}
/*
* Vad ska jag ha för egen tråd?
*
* En separat tråd ska skapas och användas för att köra minst en ytterligare funktion. Val av
funktion är valfri, men exempel kan vara – en autosave som sparar data med jämna
mellanrum i bakgrunden. – en timer som visar hur länge applikationen varit aktiv. Tråden ska
startas och stängas på ett kontrollerat sätt. Dvs ingen evig tråd…
*
*
*
*
* Inlämningsuppgift OOP - Fördjupning
Bakgrund
Du har fått en provanställning på Wigellkoncernen och som andra uppdrag ska du bygga en
Java-applikation med grafiskt gränssnitt som modellerar en medlemsklubb med uthyrning av
utrustning. (t.ex. fordon, verktyg, sportprylar eller liknande)
Tekniska krav
Klasser som ska finnas i applikationen:
 Member (id, namn, status/level, historik)
 Item (abstract)  Subklasser som ex. Vehicle, Tool, SportGear eller liknande
beroende på ditt val av applikation. Var och en av klasserna ska innehålla sina unika
attribut och metoder enligt det vi pratat om.
 Rental (kopplar member och item för en viss tidsperiod)
 PricePolicy (interface) + konkreta strategier, ex. standard, student, premium.
 Inventory och MemberRegistry
 RentalService och MembershipService ska innehålla affärslogiken
User Interface (JavaFX)
Applikationen ska ha ett grafiskt gränssnitt i JavaFX
Användaren ska kunna:
 Lägga till, söka och ändra medlemmar
 Lägga till och ändra items
 Lista och filtrera items
 Boka och avsluta uthyrningar
 Se summeringar (ex intäkter eller antal aktiva uthyrningar)
Data ska presenteras i t.ex. TableView, ListView eller liknande komponenter
Fel och meddelanden till användaren ska visas på ett begripligt sätt
Det är helt ok att utgå från ditt föregående projekt, men du förväntas refaktorisera och bygga
vidare.
Bedömningskriterier
G
Körbar applikation som körs tills användaren väljer att avsluta
Korrekt uppförda klasser samt användande av objekt och metoder
Item som basklass + minst två konkreta subklasser. PricePolicy som interface + minst två
implementationer
Privata attribut samt nödvändiga getters/setters. Enkel ansvarsdelning mellan klasser/logik
samt huvudprogrammet
Collections och streams ska användas där det är lämpligt
Vid start ska inventory och memberRegistry läsas in från fil och populera aktuell tabell
Mindre kompletteringar får förekomma
VG
Samtliga krav på G är uppfyllda. Dessutom finns en robust felhantering på metoder där så är
lämpligt. Det finns även en hög kodkvalitet med väl namngivna klasser och metoder.
Applikationens data (Medlemmar och items) ska kunna sparas till fil och via ett knapptryck
läsas in och populera önskad tabell
En separat tråd ska skapas och användas för att köra minst en ytterligare funktion. Val av
funktion är valfri, men exempel kan vara – en autosave som sparar data med jämna
mellanrum i bakgrunden. – en timer som visar hur länge applikationen varit aktiv. Tråden ska
startas och stängas på ett kontrollerat sätt. Dvs ingen evig tråd…
Helheten ska ge intrycket av ett ”litet men seriöst system” snarare än ”bara en skolövning”
Koden ska vara tydligt objektorienterad
Uppgiften ska lämnas in innan deadline.
Mindre kompletteringar får förekomma
*
*
*
*
*
*
* Inlämningsuppgift objektorienterad programmering
Bakgrund
Du har fått en provanställning på Wigellkoncernen och som första uppdrag ska du bygga
en konsolbaserad Java-applikation som modellerar en medlemsklubb med uthyrning av
utrustning. (t.ex. fordon, verktyg, sportprylar eller liknande)
Tekniska krav
Klasser som ska finnas i applikationen:
• Member (id, namn, status/level, historik)
• Item (abstract)  Subklasser som ex. Vehicle, Tool, SportGear eller liknande
beroende på ditt val av applikation. Var och en av klasserna ska innehålla sina
unika attribut och metoder enligt det vi pratat om.
• Rental (kopplar member och item för en viss tidsperiod)
• PricePolicy (interface) + konkreta strategier, ex. standard, student, premium.
• Inventory och MemberRegistry (hanteras i minnet via List, Map eller Set)
• RentalService och MembershipService ska innehålla affärslogiken
• Konsolmeny: lägg till/sök/ändra medlemmar. Lista/filtrera items. Boka/avsluta
uthyrning. Summera intäkter
Bedömningskriterier
G
Körbar applikation som körs tills användaren väljer att avsluta
Korrekt uppförda klasser samt användande av objekt och metoder
Item som basklass + minst två konkreta subklasser. PricePolicy som interface + minst
två implementationer
Privata attribut samt nödvändiga getters/setters. Enkel ansvarsdelning mellan
klasser/logik samt huvudprogrammet
Collections ska användas där det är lämpligt
Mindre kompletteringar får förekomma
VG
Samtliga krav på G är uppfyllda. Dessutom finns en robust felhantering på metoder där
så är lämpligt. Det finns även en hög kodkvalitet med väl namngivna klasser och
metoder. Uppgiften ska lämnas in innan deadline. Mindre kompletteringar får
förekomma
* */


