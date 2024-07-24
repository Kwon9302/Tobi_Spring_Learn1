package tobyspring.hellospring.exrate;

import tobyspring.hellospring.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;

    private BigDecimal cachedExRate;
    private LocalDateTime cacheExpiryTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        // 원화 비율을 한번도 안긁어왔거나 || 3초가 지난 시점이라면 원화 비율을 다시 읽어오기
        if(cachedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cachedExRate = target.getExRate(currency); // WebApiExRateProvider의 getExRate메서드를 이용해서 KRW의 비율을 불러온다.
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("cache updated");
            System.out.println("cachedExRate: " + cachedExRate);
        }
        return cachedExRate;
    }
}
