package alcohol.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alcohol.dao.AlcoholDao;
import alcohol.models.Alcohol;

@Service
public class AlcoholService {

    private AlcoholDao alcoDao;

    @Autowired
    public AlcoholService(AlcoholDao alcoDao) {
            super();
            this.alcoDao = alcoDao;
    }

    public void init(InputStream in) {
            alcoDao.init(in);
    }

    public List<Alcohol> getData() throws IOException {
            return alcoDao.getAlcohols();
    }
}
