package alcohol.dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class AlcoholDaoTest {
    @Test
    public void TestLangDao() throws IOException {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream file = classLoader.getResourceAsStream("data/data.json");
            AlcoholDao dao = new AlcoholDao();
            dao.init(file);
            assertEquals("Number of languages are different", 97, dao.getAlcohols().size());
    }
}
