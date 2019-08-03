package mariusz.ambroziak.kassistant.restnlp.ner;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NerRestController {
	
	private NerParser parserBean;
	
	
    public NerParser getParserBean() {
		return parserBean;
	}

	@Autowired
	public void setParserBean(NerParser parserBean) {
		this.parserBean = parserBean;
	}


	@RequestMapping("/ner")
    public NerResults greeting(@RequestParam(value="param", defaultValue="empty") String param) throws IOException {
        return parserBean.parse(param);
    }

}
