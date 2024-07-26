package tobyspring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    void beforeEach(){
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());;
    }

    @Test
//    @DisplayName("prepare 메소드가 요구사항을 잘 충족하는 검")
    void convertedAmount() throws IOException {

        testAmount(valueOf(500),valueOf(5000),this.clock);
         testAmount(valueOf(1000),valueOf(10000),this.clock);
         testAmount(valueOf(3000),valueOf(30000),this.clock);
    }
    @Test
    void validUntil() throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1000)), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        LocalDateTime now = LocalDateTime.now(clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    private static void testAmount(BigDecimal exRate, BigDecimal covertedAmount,Clock clock) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate),clock );

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보 가져오기
        assertThat(payment.getExRate()).isEqualByComparingTo((exRate));

        // 원화 계산
        assertThat(payment.getConvertedAmount()).isEqualTo((covertedAmount));
    }
}