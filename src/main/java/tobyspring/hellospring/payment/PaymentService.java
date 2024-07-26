package tobyspring.hellospring.payment;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class PaymentService {
    private final ExRateProvider exRateProvider;
    private final Clock clock;

    public PaymentService(ExRateProvider exRateProvider, Clock clock) {
        this.exRateProvider = exRateProvider;
        this.clock = clock;
    }

    //       주문ID     ,  변환 국가      ,  비율
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        // 원하는 국가의 환율정보를 가져오기
        BigDecimal exRate = exRateProvider.getExRate(currency);


        return Payment.createPrepared(orderId, currency,foreignCurrencyAmount, exRate,LocalDateTime.now(clock));
    }
}
