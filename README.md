Il progetto consiste nella realizzazione di un sistema per la gestione e visualizzazione di cartelloni pubblicitari su LED wall, mostrando pagine HTML animate secondo palinsesti predefiniti. Il sistema è composto da un'applicativo SpringBoot per la visualizzazione dei cartelloni e un applicativo JakartaEE per il monitoraggio e la gestione degli impianti.

Inizialmente sono state create le pagine pubblicitarie (file HTML con animazioni CSS) e un formato XML per definire i palinsesti, che una pagina HTML/JavaScript interpreta per visualizzare in sequenza i cartelloni pubblicitari sugli impianti LED wall. Successivamente è stato sviluppato un sistema di monitoraggio in Jakarta EE, che raccoglie dati sulle visualizzazioni e registra in un database relazionale le segnalazioni inviate dagli impianti. Una servlet fornisce una pagina di riepilogo con lo stato degli impianti, visualizzandoli su una mappa Leaflet o come elenco testuale.

Il sistema successvimente è stato reso dinamico: ogni impianto carica automaticamente il proprio palinsesto in base al proprio ID, memorizzato nel database insieme al suo stato (attivo/inattivo). Una pagina di gestione degli impianti consente di modificare il palinsesto e lo stato di ciascun impianto, mentre una sezione di reportistica permette di analizzare le impressioni dei cartelloni in un intervallo temporale.

Tecnologie utilizzate:
Frontend (Visualizzazione e Interfaccia Utente)
-	HTML, CSS, JavaScript → Per la creazione delle pagine pubblicitarie animate.
-	XML → Per la definizione dei palinsesti pubblicitari.
-	JavaScript (AJAX) → Per caricare dinamicamente il palinsesto XML e per inviare segnalazioni al sistema di monitoraggio.
- Leaflet.js → Per visualizzare gli impianti su una mappa, distinguendo tra attivi e inattivi.

Backend (Gestione e Monitoraggio degli Impianti)

-Spring Boot → Framework backend utilizzato per gestire l'intero sistema:
		-	Distribuzione dei cartelloni pubblicitari.
    -	Servizi REST per recuperare i palinsesti dal database.
    -	Generazione delle pagine web per ogni impianto con l'URL dedicato.
-	Jakarta EE (ex Java EE, Servlets, JSP) → Per la gestione delle segnalazioni, il monitoraggio e la gestione degli impianti.
-	Servlets → Per ricevere segnalazioni dai LED wall e fornire report sullo stato degli impianti.
-	JSP/HTML → Per generare pagine dinamiche con i dati degli impianti.

Database
-	SQL → Per interrogare e gestire i dati relativi a segnalazioni e stato degli impianti.
-	Spring Data JPA → Per l'interazione tra Spring Boot e il database.
