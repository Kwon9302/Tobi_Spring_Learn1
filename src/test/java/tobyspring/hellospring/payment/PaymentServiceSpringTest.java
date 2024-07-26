package tobyspring.hellospring.payment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.TestPaymentConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) // Test시 SpringExtension을 이용하도록 요청한다 (Test시 고정값이므로 외워도 좋다.)
@ContextConfiguration(classes = TestPaymentConfig.class)  // @ExtendWith, @ContextConfiguration은 고정으로 나온다.
class PaymentServiceSpringTest {

    @Autowired PaymentService paymentService;
    @Autowired ExRateProviderStub exRateProviderStub;
    @Autowired Clock clock;


    @Test
//    @DisplayName("prepare 메소드가 요구사항을 잘 충족하는 검")
    void convertedAmount() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1000));
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10000));

    }

    }
