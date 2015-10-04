package fr.polytech.mercury.validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.polytech.mercury.data.Corpus;
import fr.polytech.mercury.data.EtiquetteEnum;
import fr.polytech.mercury.data.Tweet;

/**
 * @author Guillaume
 * Classe permettant de parser les corpus de tweets
 */
public class Parser {

	/**
	 * @param etiquette
	 * @return EtiquetteEnum correspondant a la valeur passee en parametre
	 */
	public static EtiquetteEnum stringToEnum(String etiquette) {
		switch (etiquette) {
		case "pos":
			return EtiquetteEnum.POS;
		case "neu":
			return EtiquetteEnum.NEU;
		case "neg":
			return EtiquetteEnum.NEG;
		case "irr":
			return EtiquetteEnum.IRR;
		case "???":
			return EtiquetteEnum.NONE;
		default:
			return EtiquetteEnum.NONE;
		}
	}

	/**
	 * @param etiquette
	 * @return String correspondant a la valeur de l'etiquette passee en parametre
	 */
	public static String enumToString(EtiquetteEnum etiquette) {
		switch (etiquette) {
		case POS:
			return "pos";
		case NEU:
			return "neu";
		case NEG:
			return "neg";
		case IRR:
			return "irr";
		case NONE:
			return "???";
		default:
			return "???";
		}
	}

	/**
	 * @param fileName
	 * @return Corpus parse du fichier passe en parametre
	 * @throws IOException
	 */
	public static Corpus parseFile(String fileName) throws IOException {
		Corpus corpus = new Corpus();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(fileName)), "UTF-8"))) {
			for (String line; (line = br.readLine()) != null;) {

				// System.out.println(line);
				String tweetTab[] = line.substring(1).split("\\)", 2);
				String[] tweetHeader = tweetTab[0].trim().split(",");
				String tweetContent = tweetTab[1].trim();
				// System.out.println("Header : " + tweetHeader);
				// System.out.println("Content : " + tweetContent);

				EtiquetteEnum etiquette = stringToEnum(tweetHeader[1]);
				String firm = tweetHeader[2];
				Tweet tweet = new Tweet(tweetHeader[0], etiquette, firm,
						tweetContent);
				corpus.add(tweet);
			}
		}
		// System.out.println(corpus);
		return corpus;
	}
}
