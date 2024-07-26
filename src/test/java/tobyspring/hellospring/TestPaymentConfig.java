package tobyspring.hellospring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.payment.ExRateProviderStub;
import tobyspring.hellospring.payment.PaymentService;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1000));
    }

    @Bean
    public Clock clock() {
        // ZoneId-> Asia/seoul, Europe/Paris등 표준된 시간대 식별자를 사용systemDefault 시스템의 현재시간을 가자ㅕ온다.
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
