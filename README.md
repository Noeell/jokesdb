# JokesDB
## Verwendete Features

Für mein Projekt verwende ich einen hardcoded Client, um einen Joke von https://jokeapi.dev zu holen.

Diese Methode führe ich mehrere Male aus und speichere sie in der Lokalen DB (Duplikate werden verhindert).

Meine Datenbank besitzt sinvolle Felder (creation-timestamp, modified-timestamp) und die Jokes können via separate Tabelle (Ratings) bewertet werden.

Das Projekt hat keine Getters / Setters, stattdessen wurde Lombok verwendet. 

Im Projekt befinden sich Loggers (Bsp. 'RemoteJokesService').

Ich habe die Funkionalitäten per JUnit und AssertJ getestet.

Ausserdem wurden WebTestClient Tests der Endpunkte durchgeführt. 

Zudem habe ich ein Frontend für die Applikation gebaut (Pfad : '\src\joke-frontend'). 
Darin kann man sich den Joke of The Day anzeigen lassen und ihn auch Bewerten.
Wenn ein Joke aus zwei Teilen besteht, kann man per Button die Pointe anzeigen lassen.
Die Bewertung wird dann mit der dazugehörigen JokesId gespeichert. 
Bei jedem laden eines Jokes prüft es zuerst, ob bereits schon ein Rating vorhanden ist. 
Dieses lädt es dann. 
Die Ratings können auch geupdated werden.
Ausserdem werden die Flags zum Joke auch angezeigt. 


## Ausblick

Wenn ich noch mehr Zeit hätte, würde ich mein Frontend noch ein wenig schöner gestalten. Man könnte die Ratings auch per User speichern. 

Ein generischer Jokes-Client mit mehreren Parametern wäre auch noch eine Option für die Zukunft. 

## Selbsteinschätzung

5.5