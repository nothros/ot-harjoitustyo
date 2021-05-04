# Arkkitehtuurikuvaus
## Rakenne
<img src ="https://raw.githubusercontent.com/nothros/ot-harjoitustyo/master/CurriculatorApp/dokumentaatio/kuvat/paakaavio.png" width="500"> 

<p>&nbsp;</p>

<p> Ohjelman rakenne on on jaoiteltu seuraavanlaisesti</p>

  - ui: Ohjelman käyttöliittymään liittyvät luokat.
  - control: Ohjelman päätoiminnasta vastaavat luokat eli fxml- controllerit(hoitavat tapahtumia, kuten nappien paineiluita ja syötteitä)
  - logic: Sovelluslogiikasta vastaavat luokat
  - domain: Luokat kuten user ja course
  - dao: tietokantoihin liittyvä toiminallisuus.
  

### Käyttöliittymä

Käyttöliittymä sisältää neljä erilaista näkymää

   - kirjautuminen
   - uuden käyttäjän luominen
   - uuden opintoseurattavan luominen
   - kurssiseuranta

Kaikki näkymät luodaan uusina sceneolioina.
Virallinen graafisen käyttöliittymän käynnistävä toiminta on luokassa curriculator.ui.CurriculatorUi.

Käyttöliittymä on muuten eristetty täysin muusta toiminallisuudesta, mutta osittain myös ns. alemmat pakettiluokat joutuvat hyödyntämään itse ui:n toimintaa:

FXML tarvitsee toimiakseen controllerin asetukset, jotta controllerit, eli nappien painalluksista ja syötteistä vastaavat luokat voivat käyttää myös logiikkaluokkaa. Lähinnä haasteellisuus tulee esiin uutta sceneä asettaessa, ja vaatii käyttöliityymään omat metodinsa, joita controller ja service- luokat hyödyntävät.


Käyttöliittymä luo myös uuden userdaon, sekä appservicen- joka kuuluu sovelluslogiikkaan.

### Päätoiminnallisuudet
#### Uuden käyttäjän luominen
Käyttäjä luo uuden käyttäjän RegisterUi- nimisessä näkymässä, hän syöttää tekstikenttiin tiedot nimestään, käyttäjänimestään sekä salasanan. Tämän jälkeen kontrolli etenee seuraavanlaisesti.
<img src ="https://raw.githubusercontent.com/nothros/ot-harjoitustyo/master/CurriculatorApp/dokumentaatio/kuvat/uusikayttajakaavio.png">
Controlleri kutsuu LoginServiceä, joka ensin tarkistaa, että syötekentät ovat täytetty. Tämän jälkeen se tarkistaa UserDao:sta, onko käyttäjänimi jo käytössä. Jos ei ole, palauttaa UserDao null, ja LoginService luo uuden User-olion. Tämän jälkeen salasana cryptataan user-luokasa, ja lisätään nämä tiedot, ja cryptattu salasana tietokantaan "curriculatorapp.db". LoginService antaa LoginControllerin vaihtaa ilmoitusviestin, jotta käyttäjä osaa palata takasin Login-näkymään.
#### Kirjautuminen




