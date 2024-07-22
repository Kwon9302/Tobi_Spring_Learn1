package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExchangeRatePaymentService extends PaymentService {

    @Override
    BigDecimal getExRate(String currency) throws IOException {
        if(currency.equals("USD")) return new BigDecimal(1000);
        throw new IllegalArgumentException("지원되지않는 서비스입니다.");
    }
}
