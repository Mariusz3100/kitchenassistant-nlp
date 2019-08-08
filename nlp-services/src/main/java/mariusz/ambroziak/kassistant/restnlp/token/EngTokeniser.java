package mariusz.ambroziak.kassistant.restnlp.token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import mariusz.ambroziak.kassistant.restnlp.ner.NerResults;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

@Component
public class EngTokeniser {

	
	private ResourceLoader resourceLoader;
	private TokenizerModel tokenizerModel;
	private TokenizerME tokenizer;



	public TokenizationResults parse(String phrase) throws IOException {
		String[] tokens = tokenizer.tokenize(phrase);
		
		return new TokenizationResults(phrase,tokens);
	}

    @Lazy
	@Autowired
	public EngTokeniser(ResourceLoader resourceLoader) throws IOException {
		super();
		this.resourceLoader=resourceLoader;
		InputStream is;
		try {
			is = this.resourceLoader.getResource("classpath:/nlp-models/en-token.bin").getInputStream();
			tokenizerModel = new TokenizerModel(is);
			tokenizer = new TokenizerME(tokenizerModel);

		} catch (IOException e) {
			throw e;
		}

		
	}
}
