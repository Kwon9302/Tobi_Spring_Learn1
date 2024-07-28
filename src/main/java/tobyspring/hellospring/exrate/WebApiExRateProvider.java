package tobyspring.hellospring.exrate;

import org.springframework.stereotype.Component;
import tobyspring.hellospring.api.*;
import tobyspring.hellospring.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Component // Spring에게 읽어야 한다고 표시해줌
// 환율 정보 가져오기
public class WebApiExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency){
        // https://open.er-api.com/v6/latest/USD
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url, new HttpClientApiExercutor(), new ErApiExRateExtractor());
    }
}