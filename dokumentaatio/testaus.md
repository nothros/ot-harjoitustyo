# Testausdokumentti

Ohjelmaa on testattu automaattisilla yksikkö- ja integtraatiotestein JUnitilla, ja manuaalisesti ohjelmaa kokeilemalla.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluslogiikka testattiin sekä AppServicen, eli Itse ohjelman toiminnallisuuden osalta, sekä LoginServicen, eli kirjautumisen osalta. Nämä tarvitsivat toimiakseen myös tietokantaluokkia. Osa metodeista jäivät testaamatta, sillä ne eivät palauttaneet mitään.

### Dao

Tietokantataulut testattiin kaikkien tietokantaa rakentavien luokkien osalta, eli CoursesDao:n, UserDao:n ja CurriculumDao:n osalta. CurriculumDao tarvitsee toimiakseen userDaon ja CoursesDao kummatkin.

### Domain

Domain:ssa sijaitsevat User, Course, ja Curriculum- luokat. Näistä eniten on testattu User-luokkaa, sillä siellä kryptataan salasana, sekä tarkistetaan sen oikeellisuus.

### Testikattavuus
![Testikattavuus](https://raw.githubusercontent.com/nothros/ot-harjoitustyo/master/dokumentaatio/kuvat/testit.png)


Testikattavuus on 87% jota pienensi hashcoden testaamattomuus. haarautumakattavuus 75 % Testaamatta jäivät kokonaan Controller-luokat, sillä ne rakentavat Käyttöliittymää, ja niiden testaaminen olisi fxml:n käytön vuoksi ollut haastavaa.

## Järjestelmätestaus

Ohjelman toiminta on testattu [käyttöohjeen](https://github.com/nothros/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) osoittamalla tavalla manuaalisesti.

## Puutteet

Koska Controllerit hallinnoivat tyhjiä ja vääriä syötteitä, on nämä voitu testata ainoastaan manuaalisesti. AppServicen ja CoursesDaon osalta tuli ongelmia siinä, ettei metodit palauttaneet itse mitään, eikä näin ollen olleet testattavissa itsenäisesti.
