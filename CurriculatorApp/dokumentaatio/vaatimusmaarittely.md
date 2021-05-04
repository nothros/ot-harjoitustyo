# Vaatimusmäärittely
## Sovelluksen käyttötarkoitus

Sovelluksen tarkoituksena on pitää kirjaa suoritetuista kursseista, sekä tarkastella edistymistä. Sovellusta voi käyttää useampi käyttäjä, joilla kaikilla on oma käyttäjäprofiilinsa. Käyttäjä voi määritellä oman tutkintonsa laajuuden, sekä lisätä ja merkitä kursseja tehdyksi. Sovellus pitää kirjaa suoritettujen kurssien määrästä, keskiarvosta ja paljon koulutusta on vielä jäljellä, sekä näyttää suorittamattomat kurssit.

## Käyttäjä
Vain yksi käyttäjärooli eli normaalikäyttäjä

## Käyttöliittymäluonnos
Sovellus koostuu kolmesta näkymästä
- ✅ Login- näkymä, josta käyttäjä kirjautuu sisään tai 
- ✅ Register- näkymä, josta käyttäjätilin voi luoda
- Päänäkymä (kirjautumisen jälkeen) Jossa kaikki toiminnallisuus

<img src="https://github.com/nothros/ot-harjoitustyo/blob/master/CurriculatorApp/dokumentaatio/kuvat/kayttoliittymaluonnos.png" width="750">

 
## Toiminnallisuus
### ✅  Ennen kirjautumista
Käyttäjä voi luoda sovellukseen oman käyttäjätunnuksen
 - ✅ Käyttäjätunnuksen tulee olla uniikki, sovellus ilmoittaa jos näin ei ole
 - ✅ Sovellus ilmoittaa virheestä, mikäli kaikkia syöttökenttiä ei ole täytetty
 - ✅  Sovellus kryptaa salasanan käyttäen ulkopuolista kirjastoa apunaan (BCrypt)

Tai käyttäjä voi kirjautua sovellukseen olemassa olevalla käyttäjätunnuksellaan
 - ✅ Sovellus ilmoittaa mikäli käyttäjätunnusta ei löydy tai 
 - ✅  Jos käyttäjä ei ole täyttänyt vaadittavia kenttiä
 - ✅ Mikäli salasana on väärin, ohjelma ilmoittaa siitä.

### Kirjautumisen jälkeen
#### ✅ Ensikirjautuminen
 - ✅ Käyttäjä määrittää tutkinolleen nimen
 - ✅ ja keston haluamallaan mittarilla (opintopisteet, osaamispisteet)

#### Päänäkymä
 - suoritettavat kurssit, ja ne voi merkitä tehdyksi, ja niille voi merkitä arvosanan ja suorituspäivämäärään.
 - Mahdollisuus lisätä kursseja
 - Opintojen keskiarvo
 - Ympyräkaavio suoritettujen ja suorittamattomien opintopisteiden suhteesta
 - Suoritettujen opintopisteiden lukumäärä

## Jatkokehitysideat
- Kursseille on omat pääluokat
- Kurssit eivät poistu näkymästä, vaan siirtyvät laajempaan kurssinseurantanäkymään
- Lisää statistiikkaa mm. Kurssien suoritustahdille/kk/jakso/vuosi
