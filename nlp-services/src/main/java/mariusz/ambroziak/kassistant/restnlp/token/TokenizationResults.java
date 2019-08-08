package mariusz.ambroziak.kassistant.restnlp.token;

public class TokenizationResults {

	
	private String originalPhrase;
	private String[] tokens;
	
	
	
	public TokenizationResults(String originalPhrase, String[] tokens) {
		super();
		this.originalPhrase = originalPhrase;
		this.tokens = tokens;
	}


	public String[] getTokens() {
		return tokens;
	}


	public void setTokens(String[] tokens) {
		this.tokens = tokens;
	}


	public String getOriginalPhrase() {
		return originalPhrase;
	}

	public void setOriginalPhrase(String originalPhrase) {
		this.originalPhrase = originalPhrase;
	}
}
