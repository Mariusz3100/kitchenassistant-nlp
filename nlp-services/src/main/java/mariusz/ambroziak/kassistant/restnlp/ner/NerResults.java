package mariusz.ambroziak.kassistant.restnlp.ner;

import java.util.List;

import opennlp.tools.util.Span;

public class NerResults {



	private String originalPhrase;
	private List<Span> spans;
	
	
	


	public NerResults(String originalPhrase, List<Span> spans) {
		super();
		this.originalPhrase = originalPhrase;
		this.spans = spans;
	}

	public String getOriginalPhrase() {
		return originalPhrase;
	}

	public void setOriginalPhrase(String originalPhrase) {
		this.originalPhrase = originalPhrase;
	}
	
	
	public List<Span> getSpans() {
		return spans;
	}

	public void setSpans(List<Span> spans) {
		this.spans = spans;
	}
}
