package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

abstract public class PaymentService {
                           // 주문ID     ,  변환 국가      ,  비율
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

        // 원하는 국가의 환율정보를 가져오기
        BigDecimal exRate = getExRate(currency);
        // 금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        // 유효시간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency,foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    // 환율 정보 가져오기
    abstract BigDecimal getExRate(String currency) throws IOException ;


}
