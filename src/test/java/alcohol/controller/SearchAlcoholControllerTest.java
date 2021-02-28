package alcohol.controller;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import alcohol.models.Alcohol;
import alcohol.service.IndexingService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchAlcoholControllerTest {

    private static IndexingService indexService;
    private static SearchAlcoholController controller;

    @BeforeClass
    public static void setup() {
            indexService = mock(IndexingService.class);
            controller = new SearchAlcoholController(indexService);
    }

    @Test
    public void TestGetAlcohols() throws IOException, ParseException {
            List<Alcohol> value = new ArrayList<>(
                            asList(new Alcohol("Lisp", "Lisp", "Lisp", "Lisp"), new Alcohol("Lisp", "Lisp", "Common Lisp", "Lisp")));
            when(indexService.query("Lisp")).thenReturn(value);
            List<Alcohol> alcohols = controller.getAlcohols("Lisp");
            assertEquals("Number of alcohols should be 2", 2, alcohols.size());
    }

    @Test(expected = ParseException.class)
    public void TestThrowException() throws IOException, ParseException {
            when(indexService.query("*")).thenThrow(new ParseException());
            controller.getAlcohols("*");
    }
}
