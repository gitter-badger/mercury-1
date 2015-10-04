package fr.polytech.mercury.data;

/**
 * @author Guillaume
 * classe de representation d'un tweet en memoire (bean)
 */
public class Tweet implements Comparable<Tweet> {

	private String idTweet;
	private EtiquetteEnum etiquette;
	private String firm;
	private String tweetContent;

	public Tweet(String idTweet, EtiquetteEnum etiquette, String firm,
			String tweetContent) {
		this.idTweet = idTweet;
		this.etiquette = etiquette;
		this.firm = firm;
		this.tweetContent = tweetContent;
	}

	public Tweet() {
	}

	public String getIdTweet() {
		return idTweet;
	}

	public void setIdTweet(String idTweet) {
		this.idTweet = idTweet;
	}

	public EtiquetteEnum getEtiquette() {
		return etiquette;
	}

	public void setEtiquette(EtiquetteEnum etiquette) {
		this.etiquette = etiquette;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public String getTweetContent() {
		return tweetContent;
	}

	public void setTweetContent(String tweetContent) {
		this.tweetContent = tweetContent;
	}

	@Override
	public String toString() {
		return "(" + idTweet + ", " + etiquette + ", " + firm + ") " + tweetContent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object) methode utile pour
	 * le type Corpus<Tweet> (TreeSet)
	 */
	@Override
	public int compareTo(Tweet o) {
		int tweet1 = Integer.parseInt(idTweet);
		int tweet2 = Integer.parseInt(o.getIdTweet());

		if (tweet1 > tweet2) {
			return 1;
		} else if (tweet1 < tweet2) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		Tweet tweet = (Tweet) obj;
		return tweet.getIdTweet().equals(idTweet);
	}

}
