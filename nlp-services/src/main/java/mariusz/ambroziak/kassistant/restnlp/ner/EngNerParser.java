package mariusz.ambroziak.kassistant.restnlp.ner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;

@Component
public class EngNerParser {
	
	@Autowired
	ResourceLoader resourceLoader;
	

	
	public NerResults parse(String phrase) throws IOException {
		//enNerLocation.getInputStream()
		
		Resource en_ner_location=resourceLoader.getResource("classpath:/nlp-models/en-ner-location.bin");

		TokenNameFinderModel model = new TokenNameFinderModel(en_ner_location.getInputStream());
		NameFinderME nameFinder = new NameFinderME(model);
		
		return new NerResults(" Bean parsed:"+phrase);
	}
}
