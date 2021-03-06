package alcohol.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.BeforeClass;
import org.junit.Test;
import alcohol.config.ConfigurationHandler;
import alcohol.dao.AlcoholDao;
import alcohol.models.Alcohol;

public class IndexingServiceIndexTest {
	
    private static IndexWriter indexWriter;
    private static ConfigurationHandler config;

    private static IndexingService service;

    @BeforeClass
    public static void setup() throws IOException {
            config = mock(ConfigurationHandler.class);
            when(config.getIndexFolder()).thenReturn("testIndex");
            indexWriter = getIndexWriter();
    }

    public static IndexWriter getIndexWriter() throws IOException {
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            Directory indexDirectory = FSDirectory.open(Paths.get(config.getIndexFolder()));
            return new IndexWriter(indexDirectory, iwc);
    }

    public List<Alcohol> getAlcoholObjects() throws IOException {
            InputStream file = getClass().getClassLoader().getResourceAsStream("data/data.json");
            AlcoholDao dao = new AlcoholDao();
            AlcoholService service = new AlcoholService(dao);
            service.init(file);
            return service.getData();
    }

    @Test
    public void testIndexing() throws IOException {
            service = new IndexingService(indexWriter, config);
            service.index(getAlcoholObjects());
            assertTrue("Index is not created at testIndex location", new File(config.getIndexFolder(), "write.lock").exists());
    }
}
