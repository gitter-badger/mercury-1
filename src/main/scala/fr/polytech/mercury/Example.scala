package fr.polytech.mercury

import scala.io.Source


/**
 * Exemple pour parser un fichier
 *
 * @author Serge Martial NGUETTA
 */
object Example extends App {

  // java -> System.out.println("Hello world");
  println("Hello world")

  // Declare une constante qui contient le chemin vers le ficher de corpus
  // java -> final String Filename = "/corpus/annotated/corpus001.g1"
  val Filename = "/corpus/annotated/corpus.001.g1"

  // Declare une constante qui contient Expression regulière qui correspond au
  // format du tweet :  (ID,TAG,BRAND)TWEET
  val LineRegExp = """\((\d+),(neg|neu|pos|irr|\?\?\?),(\w+)\)(.*)""".r

  // On lit le fichier Filename precedemment défini et on en recupère les lignes
  val lines = Source.fromURL(getClass.getResource(Filename)).getLines()

  // Pour chaque ligne du fichier,
  for(line <- lines){

    // Equivalent de :  switch(line)  en java
    line match {
        // Si la ligne a le format de l'expression reguliere, alors on recupere les
        // variablers id, tag, brand, tweet qui auront été reconnus sur cette ligne.
      case LineRegExp(id, tag , brand, tweet) => {
        // ici on fait le traitement que l'on veut, par exemple, on affiche les id et les
        // tags de chaque ligne
        println(id)
        println(tag)
        println()
      }
    }
  }
}
