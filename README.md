# PSW
Projekat iz predmeta Projektovanje softvera: program koji pruža opcije definisanja, modifikacije, brisanja, upravljanja i uvida u šeme baza kao i rukovanje informacionim resursima unutar njih.

Korisnici:
• Administrator
• Projektant
• Regularni korisnik

I Faza: JSON baza podataka
Projektantu je omogućeno da učita ili kreira novu bazu podataka. Moguće je menjati šemu baze na 2 načina: direktno u JSON kodu u desnom panelu ili preko GUI formi u levom panelu.
Omogućeno je pretraživanje, dodavanje, brisanje i menjanje podataka u bazi, dodavanje prevoda za sve podsisteme, pakete i tabele na različitim jezicima.

II Faza: Relaciona baza podataka
Implementirane CRUD operacije za rad nad bazom podataka kao i dodavanje naziva za sve podsisteme, pakete i tabele na različitim jezicima. Zadatak: modelovati deo skladišta podataka koji omogućava održavanje podataka vezanih za rezervacije aranžmana u turističkoj agenciji.
Uključuje:

• putnik: ime, prezime, datum rođenja, adresa, mesto, broj telefona, broj pasoša, datum važenja pasoša, da li ima vizu
• destinacija: država, naziv destinacije, da li je potrebna viza
• aranžman: naziv aranžmana, destinacija, broj noćenja, datum i vreme polaska, mesto polaska, vrsta prevoza (autobus, avion, sopstveni), datum i vreme povratka, vrsta usluge (pansion, polupansion, noćenje s doručkom), broj raspoloživih mesta, cena
• rezervacija aranžmana: putnik, aranžman, datum rezervacije, način plaćanja (u celini, na rate), preostali iznos za naplatu
