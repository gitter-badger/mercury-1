package mercury;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import fr.polytech.mercury.data.EtiquetteEnum;
import fr.polytech.mercury.data.Tweet;
import fr.polytech.mercury.validation.Annotation;

public class TestAnnotation {

	@Test
	public void test3EqualsEtiquettes() {
		Annotation annote = new Annotation();
		Tweet resultNONE = new Tweet("0123",EtiquetteEnum.NONE,"apple","new product");
		annote.validateAnnotationTweet(resultNONE, 
				EtiquetteEnum.NONE, 
				EtiquetteEnum.NONE, 
				EtiquetteEnum.NONE);
		assertEquals(EtiquetteEnum.NONE, resultNONE.getEtiquette());
		
		Tweet resultIRR = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR, 
				EtiquetteEnum.IRR, 
				EtiquetteEnum.IRR, 
				EtiquetteEnum.IRR);
		assertEquals(EtiquetteEnum.IRR, resultIRR.getEtiquette());
		
		Tweet resultPOS = new Tweet("0123",EtiquetteEnum.NONE,"apple","good");
		annote.validateAnnotationTweet(resultPOS, 
				EtiquetteEnum.POS, 
				EtiquetteEnum.POS, 
				EtiquetteEnum.POS);
		assertEquals(EtiquetteEnum.POS, resultPOS.getEtiquette());
		
		Tweet resultNEG = new Tweet("0123",EtiquetteEnum.NONE,"apple","bad");
		annote.validateAnnotationTweet(resultNEG, 
				EtiquetteEnum.NEG, 
				EtiquetteEnum.NEG, 
				EtiquetteEnum.NEG);
		assertEquals(EtiquetteEnum.NEG, resultNEG.getEtiquette());
	}
	
	@Test
	public void test2EqualsEtiquettesIRR() {
		Annotation annote = new Annotation();

		Tweet resultIRR1 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR1, 
				EtiquetteEnum.IRR, 
				EtiquetteEnum.IRR, 
				EtiquetteEnum.NEG);
		assertEquals(EtiquetteEnum.IRR, resultIRR1.getEtiquette());

		Tweet resultIRR2 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR2, 
				EtiquetteEnum.IRR, 
				EtiquetteEnum.POS, 
				EtiquetteEnum.IRR);
		assertEquals(EtiquetteEnum.IRR, resultIRR2.getEtiquette());
		
		Tweet resultIRR3 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR3, 
				EtiquetteEnum.NEU, 
				EtiquetteEnum.IRR, 
				EtiquetteEnum.IRR);
		assertEquals(EtiquetteEnum.IRR, resultIRR3.getEtiquette());		
		
	}
	
	@Test
	public void test2EqualsEtiquettesPOS() {
		Annotation annote = new Annotation();

		Tweet resultIRR1 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR1, 
				EtiquetteEnum.POS, 
				EtiquetteEnum.POS, 
				EtiquetteEnum.NEG);
		assertEquals(EtiquetteEnum.NONE, resultIRR1.getEtiquette());

		Tweet resultIRR2 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR2, 
				EtiquetteEnum.POS, 
				EtiquetteEnum.POS, 
				EtiquetteEnum.IRR);
		assertEquals(EtiquetteEnum.NONE, resultIRR2.getEtiquette());
		
		Tweet resultIRR3 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR3, 
				EtiquetteEnum.POS, 
				EtiquetteEnum.POS, 
				EtiquetteEnum.NEU);
		assertEquals(EtiquetteEnum.POS, resultIRR3.getEtiquette());
	}

	@Test
	public void test2EqualsEtiquettesNEG() {
		Annotation annote = new Annotation();

		Tweet resultIRR1 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR1, 
				EtiquetteEnum.NEG, 
				EtiquetteEnum.NEG, 
				EtiquetteEnum.POS);
		assertEquals(EtiquetteEnum.NONE, resultIRR1.getEtiquette());

		Tweet resultIRR2 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR2, 
				EtiquetteEnum.IRR, 
				EtiquetteEnum.NEG, 
				EtiquetteEnum.NEG);
		assertEquals(EtiquetteEnum.NONE, resultIRR2.getEtiquette());
		
		Tweet resultIRR3 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR3, 
				EtiquetteEnum.NEU, 
				EtiquetteEnum.NEG, 
				EtiquetteEnum.NEG);
		assertEquals(EtiquetteEnum.NEG, resultIRR3.getEtiquette());
	}
	
	@Test
	public void test2EqualsEtiquettesNEU() {
		Annotation annote = new Annotation();

		Tweet resultIRR1 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR1, 
				EtiquetteEnum.NEU, 
				EtiquetteEnum.NEU, 
				EtiquetteEnum.NEG);
		assertEquals(EtiquetteEnum.NEU, resultIRR1.getEtiquette());

		Tweet resultIRR2 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR2, 
				EtiquetteEnum.IRR, 
				EtiquetteEnum.NEU, 
				EtiquetteEnum.NEU);
		assertEquals(EtiquetteEnum.NONE, resultIRR2.getEtiquette());
		
		Tweet resultIRR3 = new Tweet("0123",EtiquetteEnum.NONE,"apple","wtf");
		annote.validateAnnotationTweet(resultIRR3, 
				EtiquetteEnum.NEU, 
				EtiquetteEnum.NEU, 
				EtiquetteEnum.POS);
		assertEquals(EtiquetteEnum.NEU, resultIRR3.getEtiquette());
	}
}
