# Arkkitehtuurikuvaus
## Rakenne
<img src ="https://raw.githubusercontent.com/nothros/ot-harjoitustyo/master/CurriculatorApp/dokumentaatio/kuvat/pakkauspuu.png">
<em> Kuvaan lisätyt viivat tarkoittaa riippuvuksiaa, tai johteita pakkausten välillä</em>
<p>&nbsp;</p>

<p> Ohjelman rakenne on on jaoiteltu seuraavanlaisesti</p>

  - ui: Ohjelman käyttöliittymään liittyvät luokat.
  - control: Ohjelman päätoiminnasta vastaavat luokat eli fxml- controllerit(hoitavat tapahtumia, kuten nappien paineiluita ja syötteitä)
  - logic: Sovelluslogiikasta vastaavat luokat
  - domain: Luokat kuten user ja course
  - dao: tietokantoihin liittyvä toiminallisuus.
  

### Käyttöliittymä

Käyttöliittymä sisältää kolme erillistä näkymää

   - kirjautuminen
   - uuden käyttäjän luominen
   - kurssiseuranta

Näistä kirjautuminen ja kurssiseuranta luovat uuden scenen, mutta uuden käyttäjän luominen tapahtuu vain Pane:a vaihtamalla. 
Virallinen graafisen käyttöliittymän käynnistävä toiminta on luokassa curriculator.ui.CurriculatorUi.

Käyttöliittymä on muuten eristetty täysin muusta toiminallisuudesta, mutta osittain myös ns. alemmat pakettiluokat joutuvat hyödyntämään itse ui:n toimintaa:

FXML tarvitsee toimiakseen controllerin asetukset, jotta controllerit, eli nappien painalluksista ja syötteistä vastaavat metodit voivat käyttää myös logiikkaluokkaa. Lähinnä haasteellisuus tulee esiin uutta sceneä asettaessa, ja vaatii käyttöliityymään omat metodinsa, joita vontroller- luokat hyödyntävät.


Käyttöliittymä luo myös uuden userdaon, sekä appservicen- joka kuuluu sovelluslogiikkaan.

