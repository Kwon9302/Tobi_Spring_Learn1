package tobyspring.hellospring.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.lang.NonNull;
import tobyspring.hellospring.ExRateProviderStub;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
//    @DisplayName("prepare 메소드가 요구사항을 잘 충족하는 검")
    void convertedAmount() throws IOException {
        testAmount(valueOf(500),valueOf(5000));
         testAmount(valueOf(1000),valueOf(10000));
         testAmount(valueOf(3000),valueOf(300000));

        // 유효 시간 검증
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private static void testAmount(BigDecimal exRate, BigDecimal covertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(500)));

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보 가져오기
        assertThat(payment.getExRate()).isEqualByComparingTo((exRate));

        // 원화 계산
        assertThat(payment.getConvertedAmount()).isEqualTo((covertedAmount));
    }
}