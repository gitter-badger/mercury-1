package fr.polytech.mercury;

import fr.polytech.mercury.validation.Annotation;

public class Main {
	public static void main(String[] args) throws Exception {
		Annotation annote = new Annotation();
		String resourcesPath = "src/main/resources/corpus/annotated/";
		String corpusName = "corpus.002";
		annote.annoteCorpus(resourcesPath + corpusName + ".g2", resourcesPath
				+ corpusName + ".g3", resourcesPath + corpusName + ".g4",
				resourcesPath + corpusName + ".final");
	}
}
