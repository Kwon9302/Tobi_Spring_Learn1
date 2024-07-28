package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tobyspring.hellospring.exrate.ExRateDate;

import java.math.BigDecimal;

public class ErApiExRateExtractor implements ExRateExtractor {

    @Override
    public BigDecimal extract(String response) throws JsonProcessingException {
        // ⭐ JSON객체 다루는 클래스 ObjectMapper ⭐️
        ObjectMapper mapper = new ObjectMapper();
        ExRateDate data = mapper.readValue(response, ExRateDate.class);

        return data.rates().get("KRW");
    }
}
