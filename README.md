# CurriculatorApp- kurssiseurantasovellus

Sovelluksen tarkoituksena on pitää kirjaa opintojen edistymisestä:
Jäljellä olevia opintoja, ja kertynyttä keskiarvoa.
Sovellus ei ole sidottu tietyn koulutusasteen opintoihin, sillä käyttäjä voi itse päättää, seurataanko
opintopisteitä vai osaamispisteitä, sekä merkitä kurssit vaikka omakeksimillä nimillään!
Sovellukseen voi luoda useampia käyttäjiä

Sovellus on osa Helsingin yliopiston tietojenkäsittelytieteiden aineopintojen "Ohjelmistotekniikka -kevät 2021" suoritusta.

>(Curriculator- nimi tulee yhdistelmästä Curriculum[kurssi] ja Calculator [laskin])

## Dokumentaatio

 - [Työaikakirjanpito](https://github.com/nothros/ot-harjoitustyo/blob/master/CurriculatorApp/dokumentaatio/tyoaika.md)
 - [Vaatimusmäärittely](https://github.com/nothros/ot-harjoitustyo/blob/master/CurriculatorApp/dokumentaatio/vaatimusmaarittely.md)
 - [Arkkitehtuurikuvaus](https://github.com/nothros/ot-harjoitustyo/blob/master/CurriculatorApp/dokumentaatio/arkkitehtuuri.md)

## Releaset
 - [Viikko 5](https://github.com/nothros/ot-harjoitustyo/releases/tag/viikko5)


## Komentorivitoiminnot

### Suoritettavan jarin generointi
```sh
mvn package
```
generoi hakemistoon "target" suoritettavan jar-tiedoston CurriculatorApp-1.0-SNAPSHOT.jar
### Testaus
Ohjelman voi testata komennolla
```sh
mvn test
```
Testikattavuusraportin voi luoda komennolla
```sh
mvn jacoco:report
```
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedoston sijainnista *target/site/jacoco/index.html*


### Checkstyle
Checkstylen voi generoida komenolla 
```sh
mvn jacoco:report
```
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedoston sijainnista *target/site/checkstyle.html*



