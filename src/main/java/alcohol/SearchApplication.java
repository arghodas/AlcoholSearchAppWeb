package alcohol;

import java.util.List;
import alcohol.models.Alcohol;
import alcohol.service.IndexingService;
import alcohol.service.AlcoholService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication
public class SearchApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(SearchApplication.class);
	
	@Autowired
	private ResourceLoader loader;

	@Autowired
	private AlcoholService alcoholService;
	
	@Autowired
	private IndexingService indexService;
	
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
            log.info("Loading the classpath respurce data.json");
            Resource resource = loader.getResource("classpath:data/data.json");
            alcoholService.init(resource.getInputStream());
            log.info("Successfully loaded the contents from data.json");
            List<Alcohol> data = alcoholService.getData();
            indexService.index(data); // Indexes data here
    }
}