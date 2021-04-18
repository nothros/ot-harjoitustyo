# Arkkitehtuurikuvaus
## Rakenne

Ohjelman rakenne on on jaoiteltu seuraavanlaisesti
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

