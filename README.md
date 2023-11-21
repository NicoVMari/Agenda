# Agenda

Progetto per la prova finale del corso di

Paradigmi di Programmazione

a.a. 2022/2023

Il progetto va svolto in gruppi di al più 2 persone. In casi eccezionali si accettano gruppi di 3 persone, ma dovete avere la mia autorizzazione.

Requisiti

Lo svolgimento deve tenere in considerazione i seguenti principi :

- le classi del sistema devono essere corredate da classi di test;
- il progetto deve essere accompagnato da una breve relazione in cui vengono spiegate ed illustrate le scelte adottate;
- commentate i metodi public e classi con javadoc (trovate una breve intro- duzione a javadoc in questo contenitore);
- per quanto riguarda l’interfaccia utente (che deve essere separata dalla classi che realizzano la logica del sistema) è sufficiente accedere a tutte le funzioni del sistema da riga di comando;

Nello svolgere il progetto usare i principi visti nel corso. In particolare, buona strutturazione delle classi, uso di classi interne e interfacce se necessario. Ogni classe ha un compito preciso (e fa solo quello), ha campi privati, i metodi di dimensione limitata, no ripetizione di codice, astrazioni appropriate, buon uso delle eccezioni, test semplici, ecc.

Progetto: Gestione di un’agenda di appuntamenti

Progettare ed implementare in Java un sistema per la gestione di un insieme di agende.

Un’agenda è caratterizzata dall’avere un nome (unico) e dal contenere un insieme di appuntamenti. Ogni appuntamento è caratterizzato dalle seguenti informazioni:

- data dell’appuntamento, nel formato gg-mm-aaaa
- orario dell’appuntamento, nel formato hh-mm
- durata prevista espressa in minuti
- nome della persona con cui si ha l’appuntamento
- luogo in cui si terrà l’appuntamento

Il programma deve permettere di interagire con diverse agende e dare la possi- bilità di:

1. creare una nuova agenda vuota fornendone il nome
1. cancellare un agenda anche se sono presenti appuntamenti
3. creare una nuova agenda leggendo gli appuntamenti da file
3. scrivere un’agenda su file

Una volta selezionata un’agenda (fra quelle definite) si possono fare le seguenti operazioni:

a Inserire un nuovo appuntamento nell’agenda. In tal caso è necessario verificare

correttezza e completezza dei dati inseriti e che non ci siano sovrapposizioni con altri appuntamenti già definiti per quello stesso orario, tenendo anche conto della durata.

b Modificare i dati relativi a un appuntamento già registrato, effettuando gli

stessi controlli del punto precedente.

c Cercare gli appuntamenti e presentare tutte le relative informazioni. La ricerca

può essere effettuata per data oppure usando il nome della persona con cui si ha l’appuntamento.

d Elencare, ordinati per data, tutti gli appuntamenti dell’agenda.

Infine l’agenda deve essere iterabile, ma non permettere di rimuovere elementi mentre si itera.


<p align="center">
  <img src="https://github.com/NicoVMari/Agenda/assets/96552280/f218b85c-5f41-4d0f-aa2d-71cfd1093c14" alt="Testo alternativo" width="300" />
</p>
