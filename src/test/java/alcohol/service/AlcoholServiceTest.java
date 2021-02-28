package alcohol.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import alcohol.dao.AlcoholDao;

public class AlcoholServiceTest {	
    @Test
    public void testLanguageServiceWorking() throws IOException {
            InputStream file = getClass().getClassLoader().getResourceAsStream("data/data.json");
            AlcoholDao dao = new AlcoholDao();
            AlcoholService service = new AlcoholService(dao);
            service.init(file);
            assertEquals("Number of languages are different", 97, service.getData().size());
    }
}
