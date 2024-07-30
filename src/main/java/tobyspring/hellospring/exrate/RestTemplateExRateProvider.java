package tobyspring.hellospring.exrate;

import org.springframework.web.client.RestTemplate;
import tobyspring.hellospring.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class RestTemplateExRateProvider implements ExRateProvider {
    private final RestTemplate restTemplate;

    public RestTemplateExRateProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return restTemplate.getForObject(url, ExRateDate.class).rates().get("KRW");
    }
}
