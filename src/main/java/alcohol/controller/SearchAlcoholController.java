package alcohol.controller;

import java.io.IOException;
import java.util.List;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alcohol.models.Alcohol;
import alcohol.service.IndexingService;

@RestController
public class SearchAlcoholController {

    private IndexingService indexService;

    @Autowired
    public SearchAlcoholController(IndexingService indexService) {
            this.indexService = indexService;
    }

    @RequestMapping("/search")
    public List<Alcohol> getAlcohols(@RequestParam(value = "query") String query) throws IOException, ParseException {
            List<Alcohol> results = indexService.query(query);
            return results;

    }
}

