package tobyspring.hellospring.exrate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

// record
// 1. 한번 값을 넣으면 수정할 수 없다- 불변 객체를 생성한다.(기본값이 final)
// 2. 필드, 생성자, 접근자 메서드를 자동으로 생성해준다.
@JsonIgnoreProperties(ignoreUnknown = true) // result, rates 타입 이외의 데이터가 들어와도 이를 무시하고 객체로 변환한다.
public record ExRateDate(String result, Map<String, BigDecimal> rates) {
    // 필드값이 result, Map<String, BigDecimal> rates 이다.
}
