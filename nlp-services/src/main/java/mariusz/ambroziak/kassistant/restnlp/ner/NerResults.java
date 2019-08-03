package mariusz.ambroziak.kassistant.restnlp.ner;

public class NerResults {

	
	private String originalPhrase;

	
	
	
	public NerResults(String originalPhrase) {
		super();
		this.originalPhrase = originalPhrase;
	}

	public String getOriginalPhrase() {
		return originalPhrase;
	}

	public void setOriginalPhrase(String originalPhrase) {
		this.originalPhrase = originalPhrase;
	}
}
