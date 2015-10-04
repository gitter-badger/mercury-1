package fr.polytech.mercury.data;

import java.util.TreeSet;

/**
 * @author Guillaume
 * classe contenant un treeset de tweet, trie selon les id de tweets
 */
public class Corpus extends TreeSet<Tweet> {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		String result = "";

		for (Tweet tweet : this) {
			result += tweet + "\n";
		}
		
		return result;
	}
}
