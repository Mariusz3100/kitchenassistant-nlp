package mariusz.ambroziak.kassistant.restnlp.ner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import mariusz.ambroziak.kassistant.restnlp.token.EngTokeniser;
import mariusz.ambroziak.kassistant.restnlp.token.TokenizationResults;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

@Component
public class EngNerParser {


	ResourceLoader resourceLoader;
	private NameFinderME dateNameFinder;
	private NameFinderME locationNameFinder;
	private NameFinderME moneyNameFinder;
	private NameFinderME organizationNameFinder;
	private NameFinderME percentageNameFinder;
	private NameFinderME personNameFinder;
	private NameFinderME timeNameFinder;

	
	private EngTokeniser tokenizer;
    
	
	@Lazy
	@Autowired
	public EngNerParser(ResourceLoader resourceLoader, EngTokeniser tokenizer) throws IOException{
		super();
		this.tokenizer=tokenizer;
		this.resourceLoader = resourceLoader;

		Resource modelResource;
		TokenNameFinderModel model;
		try {
			
			modelResource=this.resourceLoader.getResource("classpath:/nlp-models/en-ner-date.bin");
			model = new TokenNameFinderModel(modelResource.getInputStream());
			dateNameFinder = new NameFinderME(model);
			modelResource.getInputStream().close();
			
			modelResource=this.resourceLoader.getResource("classpath:/nlp-models/en-ner-location.bin");
			model = new TokenNameFinderModel(modelResource.getInputStream());
			locationNameFinder = new NameFinderME(model);
			modelResource.getInputStream().close();
			
			modelResource=this.resourceLoader.getResource("classpath:/nlp-models/en-ner-money.bin");
			model = new TokenNameFinderModel(modelResource.getInputStream());
			moneyNameFinder = new NameFinderME(model);
			modelResource.getInputStream().close();

			modelResource=this.resourceLoader.getResource("classpath:/nlp-models/en-ner-organization.bin");
			model = new TokenNameFinderModel(modelResource.getInputStream());
			organizationNameFinder = new NameFinderME(model);
			modelResource.getInputStream().close();

			modelResource=this.resourceLoader.getResource("classpath:/nlp-models/en-ner-percentage.bin");
			model = new TokenNameFinderModel(modelResource.getInputStream());
			percentageNameFinder = new NameFinderME(model);
			modelResource.getInputStream().close();

			
			
			modelResource=this.resourceLoader.getResource("classpath:/nlp-models/en-ner-person.bin");
			model = new TokenNameFinderModel(modelResource.getInputStream());
			personNameFinder = new NameFinderME(model);
			modelResource.getInputStream().close();
			
			
			modelResource=this.resourceLoader.getResource("classpath:/nlp-models/en-ner-time.bin");
			model = new TokenNameFinderModel(modelResource.getInputStream());
			timeNameFinder = new NameFinderME(model);
			modelResource.getInputStream().close();
		
	

		
		} catch (IOException e) {
			throw e;//new ResourceNotFoundException("/nlp-models/en-ner-person.bin");
		}

	}



	public NerResults parse(String phrase) throws IOException {
		TokenizationResults tokenized = this.tokenizer.parse(phrase);

		List<Span> spansList = new ArrayList<Span>();

		Span[] found = null;
		
		found = this.dateNameFinder.find(tokenized.getTokens());
		spansList.addAll(Arrays.asList(found));

		found = this.locationNameFinder.find(tokenized.getTokens());
		spansList.addAll(Arrays.asList(found));

		found = this.moneyNameFinder.find(tokenized.getTokens());
		spansList.addAll(Arrays.asList(found));

		

		found = this.organizationNameFinder.find(tokenized.getTokens());
		spansList.addAll(Arrays.asList(found));

		found = this.percentageNameFinder.find(tokenized.getTokens());
		spansList.addAll(Arrays.asList(found));
		
		
		found = this.personNameFinder.find(tokenized.getTokens());
		spansList.addAll(Arrays.asList(found));
		
		found = this.timeNameFinder.find(tokenized.getTokens());
		spansList.addAll(Arrays.asList(found));

		
		Stream<Span> stream = spansList.stream();

	//	String results = stream.map(t->t.toString()).collect(Collectors.joining(" : "));
		List<Span> list = stream.collect(Collectors.toList());
		
		return new NerResults(phrase,list);
	}
}
