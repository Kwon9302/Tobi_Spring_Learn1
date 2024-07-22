package tobyspring.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

// 환율 정보 가져오기
public class WebApiExRatePaymentService extends PaymentService {
    @Override
    BigDecimal getExRate(String currency) throws IOException {
        // https://open.er-api.com/v6/latest/USD
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);

        // url을 HttpURLConnection으로 casting하여 사용하는것이 좋다. -> http가 제공하는 기능을 사용할 수 있기 때문.
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 원하는 url데이터 읽어오기
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = br.lines().collect(Collectors.joining());
        br.close();

        // ⭐ JSON객체 다루는 클래스 ObjectMapper ⭐️
        ObjectMapper mapper = new ObjectMapper();
        ExRateDate data = mapper.readValue(response, ExRateDate.class);
        BigDecimal exRate = data.rates().get("KRW");
        return exRate;


    }

}
