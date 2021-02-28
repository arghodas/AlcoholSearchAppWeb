package alcohol.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alcohol.config.ConfigurationHandler;
import alcohol.models.Alcohol;

@Service
public class IndexingService {

    private IndexWriter indexWriter;
    private ConfigurationHandler config;

    @Autowired
    public IndexingService(IndexWriter indexWriter, ConfigurationHandler config) {
            super();
            this.indexWriter = indexWriter;
            this.config = config;
    }

    public List<Alcohol> query(String query) throws IOException, ParseException {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(Paths.get(config.getIndexFolder())));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        MultiFieldQueryParser qp = new MultiFieldQueryParser(new String[] { "ProName", "ProPrice", "Alcohol Concentrations", "ProDescription" },
                        new StandardAnalyzer());
        qp.setDefaultOperator(MultiFieldQueryParser.Operator.AND);
        TopDocs results = indexSearcher.search(qp.parse(query), 100);
        ScoreDoc[] scoreDocs = results.scoreDocs;
        List<Alcohol> alcohols = new ArrayList<>();
        for (ScoreDoc scoreDoc : scoreDocs) {
                Document doc = indexSearcher.doc(scoreDoc.doc);
                Alcohol lang = new Alcohol();
                lang.setproDescription(doc.get("ProDescription"));
                lang.setalcoholConcentrations(doc.get("Alcohol Concentrations"));
                lang.setproPrice(doc.get("ProPrice"));
                lang.setproName(doc.get("ProName"));
                alcohols.add(lang);
        }
        return alcohols;
    }

    public void index(List<Alcohol> alcohols) throws IOException {
        if(indexWriter.isOpen())
                indexWriter.deleteAll();
        try {
                for (Alcohol alcohol : alcohols) {
                        Document doc = new Document();
                        Field proNameField = new TextField("ProName", alcohol.getproName(), Field.Store.YES);
                        doc.add(proNameField);
                        Field proPriceField = new TextField("ProPrice", alcohol.getproPrice(), Field.Store.YES);
                        doc.add(proPriceField);
                        Field alcoholConcentrationsField = new TextField("Alcohol Concentrations", alcohol.getalcoholConcentrations(), Field.Store.YES);
                        doc.add(alcoholConcentrationsField);
                        Field proDescriptionField = new TextField("ProDescription", alcohol.getproDescription(), Field.Store.YES);
                        doc.add(proDescriptionField);
                        indexWriter.addDocument(doc);
                }
        } finally {
                indexWriter.commit();
                indexWriter.close();
        }
    }
}
