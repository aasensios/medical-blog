This project has been uploaded to a public GitHub repository:

https://github.com/aasensios/medical-blog

--------------
INS Provençana
CFGS de DAW BIO -2
Curs 2018-19
Departament d'Informàtica
Examen 3 UF1
JSP, ACCÉS A DADES I MODEL VISTA CONTROLADOR

Nota:
   Mòdul M15-UF1
           
Data: 02/04/2019

EL MAQUETAT NO ÉS OBLIGATORI, SÍ QUE S’HAN DE COMPLIR ELS REQUERIMENTS QUE S’HI EXPLIQUEN A L’ENUNCIAT

OBJECTIU:
En una web de temàtica mèdica hi ha un secció que es dedica a mostrar / mantenir  informació relacionada amb llibres, notícies i webs. Depèn del rol de l’usuari que es validi, es podrà només llegir o mantenir completament actualitzada aquesta informació.

Etapa 1: Entrada 1 punt

Quan entrem se’ns mostra una primera pàgina (index.jsp) que serà com es veu a continuació:














    • Les imatges venen donades dinàmicament d’una taula que es dirà categories.
    • No es podrà fer clic a cap categoria fins que no ens validem.
    • Quan cliquem a Login, apareixerà un formulari just a sota per poder validar-nos.


    • Una validació positiva fa que tornem a index.jsp però ara les imatges són links. La imatge de Books i News portaran a google.es però la de Webs sí que tindrà continuació en les següents etapes.
    • Una validació negativa ens mostra un missatge al respecte just a sota del formulari i no ens deixa continuar.
    • Advanced Medical ens portarà sempre a index.jsp

Etapa 2: Registre 1,75 punts
Si decidim Registrar un usuari, mostrarem la següent pantalla a l'usuari, per registrar-ho. Tots els usuaris enregistrats així seran Basics
Validarem que:
        a. el nom tingui una longitud mínima de 4 i el password una longitud mínima de 8. 
        b. que els camps de password i de repetir coincideixin
        c. que el password pugui tenir lletres o números.

	Si no es compleixen alguna d’aquestes regles, no se’ns deixarà avançar i no 	se’ns inserirà a la BBDD.
	Si es compleixen les regles s’inserirà l’usuari a la base de dades i tornarem a 	la pàgina principal index.jsp 

A PARTIR D’AQUÍ FAREU SERVIR JSTL CORE         

Etapa 3: Rol basic 1,5 punts

Si l'usuari té el rol basic, en clicar a la categoria Webs, se’ns manté el menú de dalt però a sota en lloc de les imatges només se’ns mostrarà el llistat de les webs que hi han donades d’alta a la taula webs. L'usuari no podrà realitzar cap operació en la Base de Dades. Les altres dues categories no faran res.

Etapa 3: Rol admin  - llistar amb botons 1 punt

Si l'usuari es Administrador, al clicar en la categoria Webs, s'ens mostrarà el mateix llistat que si tenim rol basic però l'usuari podrà veure els botons tal i com es pot veure a la imatge de sota.

	Webs:	









Etapa 4: Rol admin  - afegir  2 punts

En clicar en Add a Web apareix a sota el següent formulari que servirà per afegir un ítem













Només s’hi afegirà la web si tots els ítems estan omplerts i si en fer les següents comprovacions tot és correcte:
a)  el codi tingui la forma P-xyz amb xyz numèrics i de mida 3. 
b) la data tingui la forma dd/mm/yyyy.

Quan s’ahi afegeixi un ítem s’informarà mitjançant un missatge.

Etapa 5: Rol admin  - esborrar  2 punts

Una vegada l'usuari Administrador es troba en qualsevol de les sortides anteriors (etapa 3) en cas que decideixi esborrar qualsevol dels ítems s'ens demanarà confirmació i en cas positiu es farà.

Etapa 6: Altres  0,75 punts
    • Tindrem l’opció de Sortir de l’aplicació, tancarem sessions i tornarem a index.jsp
    • Tota l’aplicació estarà documentada de tal manera que podrem generar JavaDoc i obtenir el manual de documentació


Resultats d’aprenentatge
RA1' Verifica el funcionament de programes dissenyant i realitzant proves i elabora la documentació de l'aplicació web seleccionant eines de generació de documentació i control de versions a l'empresa.

RA2'   Implanta arquitectures web analitzant i aplicant criteris de funcionalitat, gestiona servidors web avaluant i aplicant criteris de configuració per a l'accés segur als serveis de l'empresa.

RA3' Implanta i verifica aplicacions web en servidors d'aplicacions avaluant i aplicant criteris de configuració per al seu funcionament segur a l'empresa

RA4'  Reconeix i avalua les eines que intervenen en el desenvolupament integrat i elabora la documentació de l'aplicació web a l'empresa.

RA5'  Escriu sentències i blocs embeguts en llenguatges de marques, seleccionant i utilitzant les estructures de programació a l'empresa.

RA6' Desenvolupa aplicacions web identificant i aplicant mecanismes per separar el codi de presentació de la lògica de negoci a l'empresa.

RA7' Desenvolupa aplicacions d'accés a magatzems de dades, aplicant mesures per mantenir la seguretat i la integritat de la informació a l'empresa.

Criteris de correcció
    • Els apartats que donin errors d’interpretació o no es puguin executar no seran avaluats.
    • La funcionalitat ha de ser adient a la demanda de l’enunciat. 
    • Evitar missatges per pantalla innecessaris.
    • El tractament d'errors ha de ser adequat, de manera que si es produeixen hauria d'informar-se a l'usuari del tipus d'error produït.
    • Cuidar l'estructura dels nostres programes.


Avaluació
    • Aquest examen Pe1.2 representa el 25% del total de la puntuació de la UF1.
    • L’examen tindrà una puntuació màxima de 10 punts. La puntuació de cadascun dels apartats s’indica al contingut de l’enunciat.
