# Käyttöohje

Lataa tiedosto CurriculatorApp.jar

## Ohjelman käynnistäminen

Saat sovelluksen käynnistettyä komenolla

`` java -jar CurriculatorApp.jar``
Komento täytyy suorittaa kansiossa missä jar sijaitsee, tai vaihtoehtoisesti voit määritellä polun sovellukseen

`` java -jar tähän polku jariin/CurriculatorApp.jar``


## Kirjautuminen

Tässä näkymässä voit kirjautua olemassa olevalla käyttäjätunnuksellasi ja salasanallasi. Mikäli sinulla ei ole käyttäjätunnusta, siirry rekisteröintiin "Rekisteröidy" painikkeesta

<img src="https://raw.githubusercontent.com/nothros/ot-harjoitustyo/master/CurriculatorApp/dokumentaatio/kuvat/kirjaudu.png" width="600">


## Uuden käyttäjän luominen

Voit luoda uuden käyttäjän. Aseta nimi kohtaan oma nimesi. Käyttäjätunnuksen tulee olla uniikki, ja ohjelma kertoo mikäli käyttäjätunnuksesi on jo käytössä. Kun olet luonut käyttäjän onnistuneesti, voit palata takaisin vasemman yläkulman nuolesta. Nyt voit kirjautua käyttäjätunnuksellasi.

<img src="https://raw.githubusercontent.com/nothros/ot-harjoitustyo/master/CurriculatorApp/dokumentaatio/kuvat/rekisteroidy.png" width="600">

## Oman opinnon luominen

Tämä näkymä tulee näkyviin, kun kirjaudut sovellukseen ensimmäisen kerran. Voit määrittää opinnoillesi nimen, laajuden numeroina, sekä valita seurataanko opintojasi opintopisteinä, vai osaamispisteinä (opintopisteet ovat tuttuja korkeakouluista ja lukiosta, osaamispisteet ovat käytössä ammattikoulussa.

<img src="https://raw.githubusercontent.com/nothros/ot-harjoitustyo/master/CurriculatorApp/dokumentaatio/kuvat/opinnot.png" width="600">

## Kurssikirjanpito

### Kurssin lisääminen
Tämä näkymä tulee näkymiin, kun olet luonut opintosi, tai kirjaudut ohjelmaan uudelleen. Täällä voit hallita lisätä kurssin kirjoittamalla kurssin nimen yläähllä olevaan palkkin ja lisäämällä sille laajuuden. Kurssi tulee alas näkyviin listaan, jossa ensimmäisenä oranssilla pohjalla näkyy kurssin laajuus, sitten nimi. 

<img src="https://raw.githubusercontent.com/nothros/ot-harjoitustyo/master/CurriculatorApp/dokumentaatio/kuvat/paaliittyma.png" width="600">

### Kurssin merkitseminen suoritetuksi
Voit suorittaa kurssin painamalla "Tehty"- painiketta. Ohjelmaan aukeaa uusi ikkuna, jossa voit valita millä arvosanalla kurssin suoritit (hyväksytty, 1-5) 
Voit lisätä kurssin painamalla "Check"- näppäintä, mikäli suljet ikkunan rastista, et ole lisännyt kurssia tehdyksi

<img src="https://raw.githubusercontent.com/nothros/ot-harjoitustyo/master/CurriculatorApp/dokumentaatio/kuvat/kurssitehty.png" width="600">


Tämän jälkeen oikealla olevat numerot, sekä oikeassa yläkulmassa oleva prosessimittari päivittyvät vastaamaan tätä hetkeä, Keskiarvo lasketaan vain niiden kurssien osalta, jotka on arvosteltu asteikolla 1-5.

<img src="https://raw.githubusercontent.com/nothros/ot-harjoitustyo/master/CurriculatorApp/dokumentaatio/kuvat/paanakyma2.png" width="600">
