package alcohol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationHandler {
	@Autowired
	private Environment env;

	public String getIndexFolder() {
		return env.getProperty("document.index.dir");
	}
}
