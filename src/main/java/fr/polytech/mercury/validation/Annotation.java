package fr.polytech.mercury.validation;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import fr.polytech.mercury.data.Corpus;
import fr.polytech.mercury.data.EtiquetteEnum;
import fr.polytech.mercury.data.Tweet;

/**
 * @author Guillaume
 * classe permettant la validation de la triple annotation
 */
public class Annotation {
	
	Corpus corpus1;
	Corpus corpus2;
	Corpus corpus3;
	Corpus corpus_final = new Corpus();

	/**
	 * methode permettant d'annoter les tweets
	 * 
	 * @param fileNameCorpus1
	 * @param fileNameCorpus2
	 * @param fileNameCorpus3
	 * @param fileNameCorpusFinal
	 * @throws Exception
	 */
	public void annoteCorpus(String fileNameCorpus1, String fileNameCorpus2,
			String fileNameCorpus3, String fileNameCorpusFinal)
			throws Exception {
		try {
			corpus1 = Parser.parseFile(fileNameCorpus1);
			corpus2 = Parser.parseFile(fileNameCorpus2);
			corpus3 = Parser.parseFile(fileNameCorpus3);

			for (Tweet entry : corpus1) {
				Tweet tweet = validateAnnotationTweet(entry,
						corpus1.floor(entry).getEtiquette(),
						corpus2.floor(entry).getEtiquette(),
						corpus3.floor(entry).getEtiquette());
				corpus_final.add(tweet);
			}

			writeCorpus(fileNameCorpusFinal);

		} catch (Exception e) {
			throw new Exception("Impossible d'annoter les corpus : "
					+ e.getMessage());
		}
	}

	/**
	 * methode permettant d'ecrire le corpus dans le fichier
	 * @param fileNameCorpusFinal
	 * @throws IOException
	 */
	private void writeCorpus(String fileNameCorpusFinal) throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream(fileNameCorpusFinal), "UTF-8"));
		
		for (Tweet entry : corpus_final) {
			out.write("(" + entry.getIdTweet() + ","
					+ Parser.enumToString(entry.getEtiquette()) + ","
					+ entry.getFirm() + ") "
					+ entry.getTweetContent());
			out.write("\r\n");
		}
		out.close();
	}
	
	/**
	 * methode permettant d'ecrire les conflits pour un affichage permettant la future validation
	 * @param pos
	 * @param neu
	 * @param neg
	 * @param irr
	 * @return
	 */
	public String messageConflit(int pos, int neu, int neg, int irr){
		String result = "";
		
		if(pos!=0){
			result+=pos + "POS ";
		}
		if(neu!=0){
			result+=neu + "NEU ";
		}
		if(neg!=0){
			result+=neg + "NEG ";
		}
		if(irr!=0){
			result+=irr + "IRR ";
		}
		return result.trim();
	}

	/**
	 * algorithme permettant de valider un tweet
	 * @param inputTweet
	 * @param et1
	 * @param et2
	 * @param et3
	 * @return
	 */
	public Tweet validateAnnotationTweet(Tweet inputTweet, EtiquetteEnum et1,
			EtiquetteEnum et2, EtiquetteEnum et3) {
		int pos = 0;
		int neu = 0;
		int neg = 0;
		int irr = 0;
		Tweet tweet = inputTweet;
		EtiquetteEnum[] tabEtiquette = { et1, et2, et3 };

		for (int i = 0; i < tabEtiquette.length; i++) {
			if (tabEtiquette[i] == EtiquetteEnum.POS) {
				pos++;
			} else if (tabEtiquette[i] == EtiquetteEnum.NEU) {
				neu++;
			} else if (tabEtiquette[i] == EtiquetteEnum.NEG) {
				neg++;
			} else {
				irr++;
			}
		}

		// Les 3 etiquettes sont diff�rentes
		if (et1 != et2 && et1 != et3 && et2 != et3) {
			/* REFAIRE */
			tweet.setEtiquette(EtiquetteEnum.NONE);
			tweet.setFirm(inputTweet.getFirm() +" "+ messageConflit(pos, neu, neg, irr));
		} 
		// Les 3 etiquettes sont identiques
		else if (et1 == et2 && et1 == et3) {
			/* OK */
			tweet.setEtiquette(et1);
		} 
		// 2 etiquettes sur 3 sont irrelevant --> irrelevant
		else if (irr == 2) {
			/* IRRELEVANT */
			tweet.setEtiquette(EtiquetteEnum.IRR);

		} 
		// 1 etiquette est irr et les autres ne le sont pas 
		else if (irr == 1) {
			/* REFAIRE */
			tweet.setEtiquette(EtiquetteEnum.NONE);
			tweet.setFirm(inputTweet.getFirm() +" "+ messageConflit(pos, neu, neg, irr));

		} 
		// 2 etiquettes sont neutres --> neutre
		else if (neu == 2) {
			/* NEUTRE */
			tweet.setEtiquette(EtiquetteEnum.NEU);

		} 
		else if (neg == 2) {
			// 2 negatives + 1 positive 
			if (pos == 1) {
				/* REFAIRE */
				tweet.setEtiquette(EtiquetteEnum.NONE);
				tweet.setFirm(inputTweet.getFirm() +" "+ messageConflit(pos, neu, neg, irr));

			} 
			// 2 negatives + 1 neutre --> negative
			else {
				/* NEGATIF */
				tweet.setEtiquette(EtiquetteEnum.NEG);
			}
		} 
		else if (pos == 2) {
			// 2 positives + 1 negative
			if (neg == 1) {
				/* REFAIRE */
				tweet.setEtiquette(EtiquetteEnum.NONE);
				tweet.setFirm(inputTweet.getFirm() +" "+ messageConflit(pos, neu, neg, irr));
			} 
			// 2 positives + 1 neutre --> positive
			else {
				/* POSITIF */
				tweet.setEtiquette(EtiquetteEnum.POS);
			}
		}

		return tweet;
	}
}
