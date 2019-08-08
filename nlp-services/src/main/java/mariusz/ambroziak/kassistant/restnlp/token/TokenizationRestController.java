package mariusz.ambroziak.kassistant.restnlp.token;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenizationRestController {
	
	private EngTokeniser tokenizationBean;
	
	
 

	public EngTokeniser getTokenizationBean() {
		return tokenizationBean;
	}


    @Lazy
	@Autowired
	public void setTokenizationBean(EngTokeniser tokenizationBean) {
		this.tokenizationBean = tokenizationBean;
	}




	@RequestMapping("/tokenize")
    public TokenizationResults greeting(@RequestParam(value="param", defaultValue="empty") String param) throws IOException {
        return tokenizationBean.parse(param);
    }

}
