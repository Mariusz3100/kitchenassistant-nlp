package mariusz.ambroziak.kassistant.restnlp.ner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class NerParser {

	@Value("classpath:/nlp-models/test")
	Resource template;
	
	public NerResults parse(String phrase) throws IOException {
		
		
		return new NerResults(" Bean parsed:"+phrase);
	}
}
