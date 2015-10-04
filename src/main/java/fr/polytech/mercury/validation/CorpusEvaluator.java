package fr.polytech.mercury.validation;

import fr.polytech.mercury.data.Corpus;
import fr.polytech.mercury.data.EtiquetteEnum;
import fr.polytech.mercury.data.Tweet;

/**
 * Contient les informations suivantes : matrice de contingence, taux de reussite et kappa
 * 
 * @author Julien
 *
 */
public class CorpusEvaluator {
	// Matrice 4x4 : pos-neu-neg-irr
	private int[][] matrice_contingence = new int[4][4];
	private double Po;	// taux de reussite
	private double kappa;
	
	public static void main(String[] args) {
		try {
			String resourcesPath = "src/main/resources/corpus/annotated/";
			Corpus corpus = Parser.parseFile(resourcesPath + "corpus.002.g2");
			CorpusEvaluator eval = new CorpusEvaluator();
			eval.evaluate(corpus, corpus);
			System.out.println(eval.matriceContingenceToString());
			System.out.println("\n\nK=" + eval.getKappa());
			System.out.println("Taux de r�ussite = " + eval.getTauxReussite()*100 + "%");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Calcule la matrice de contingence, le taux de reussite Po et kappa
	 * 
	 * @param prediction : corpus annot� qui doit �tre v�rifi�
	 * @param reference : corpus contenant des etiquettes exactes
	 * @throws Exception 
	 */
	public void evaluate(Corpus prediction, Corpus reference) throws Exception{
		int N = 0;
		// Calcule la matrice de contingence
		for(Tweet tweet_ref : reference){
			EtiquetteEnum et1 = tweet_ref.getEtiquette();
			Tweet tweet_pred = prediction.floor(tweet_ref);
			
			// Verif si le tweet regard� (prediction) est bien present dans le corpus reference
			if(tweet_pred == null || !tweet_pred.getIdTweet().equals(tweet_ref.getIdTweet()))
				throw new Exception("Le tweet de prediction n'est pas disponible dans le corpus de reference : evaluation stopp�.");

			EtiquetteEnum et2 = tweet_pred.getEtiquette();
			
			matrice_contingence[et1.ordinal()][et2.ordinal()]++;
			N++;
		}
		if(N == 0){
			System.out.println("Aucun �l�ment dans les corpus de r�f�rence/pr�diction. Evaluation arret�e.");
			return;
		}
		
		// Calcul le taux de reussite = 1/N * somme(Ci,j)
		Po = (matrice_contingence[0][0]+matrice_contingence[1][1]+matrice_contingence[2][2]+matrice_contingence[3][3])/N;
		
		// Calcul de Pe = 1/N� * somme(C.i*Ci.)
		int somme_Pe = 0;
		for(int i=0; i<4; i++){
			int somme_colonne = 0;
			int somme_ligne = 0;
			for(int j=0; j<3; j++){
				somme_colonne += matrice_contingence[i][j];
				somme_ligne += matrice_contingence[j][i];
			}
			somme_Pe += somme_colonne*somme_ligne;
		}
		double Pe = somme_Pe/(N*N);
		
		kappa = (Po - Pe)/(1-Pe);
	}
	
	/**
	 * Affiche la matrice de contingence
	 */
	public String matriceContingenceToString(){
		String result = "     | POS   NEU  NEG  IRR \n";
		result += "---------------------------\n";
		result += " POS |";
		for(int i=0; i<4; i++){
			result += "  " + matrice_contingence[0][i] + "  ";
		}
		result += "\n NEU |";
		for(int i=0; i<4; i++){
			result += "  " + matrice_contingence[1][i] + "  ";
		}
		result += "\n NEG |";
		for(int i=0; i<4; i++){
			result += "  " + matrice_contingence[2][i] + "  ";
		}
		result += "\n IRR |";
		for(int i=0; i<4; i++){
			result += "  " + matrice_contingence[3][i] + "  ";
		}
		return result;
	}

	public int[][] getMatrice_contingence() {
		return matrice_contingence;
	}
	
	public double getTauxReussite(){
		return Po;
	}

	public double getTauxErreur() {
		return 1-Po;
	}

	public double getKappa() {
		return kappa;
	}
}
