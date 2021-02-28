package alcohol.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import alcohol.models.Alcohol;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AlcoholDao {

    private InputStream in;

    public void init(InputStream in) {
            this.in = in;
    }

    public List<Alcohol> getAlcohols() throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            List<Alcohol> alcohols = mapper.readValue(this.in, new TypeReference<List<Alcohol>>() {
            });
            return alcohols;
    }
}
