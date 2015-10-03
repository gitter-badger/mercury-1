# mercury
Extraction d'Information Textuelles

    
## Structure des Dossiers
```
src                               → Sources de l'Application
 └ main          
    └ java                        → Dossier contenant les fichiers sources écrits en java
    └ resources                   → Dossier contenant les données nécessaires à l'Application
       └ corpus                  
          └ annotated             → Dossier contenant les corpus annotés à la fois par nous et par les autres équipes 
          └ unannotated           → Dossier contenant les corpus initiaux non annotés 
    └ scala                       → Dossier contenant les fichier sources écrits en scala
 └ test                   
    └ java                        → Dossier contenant les fichiers de tests écrits en java
    └ resources                   → Dossier contenant les resources utilisés pours les tests
    └ scala                       → Dossier contenant les fichiers de tests écrits en scala
target                            → Dossier contenant les executables et les fichiers compilés
build.sbt                         → Fichier de configuration SBT
```