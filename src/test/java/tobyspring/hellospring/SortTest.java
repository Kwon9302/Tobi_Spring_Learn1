package tobyspring.hellospring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {
    Sort sort;

    @BeforeEach // @Test실행 전 실행
    void beforeEach(){
        sort = new Sort();
        // Sort sort = new Sort()로 하면 3번의 Test마다 같은 sort를 생성하므로 사용할 수 없다.
        System.out.println(this); // 3개의 다른 sort를 생성한다(는 증거).
    }

    @Test
    void sort() {
        // 준비

        // 실행
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        // 검증
        Assertions.assertThat(list).isEqualTo(List.of("b", "aa"));

    }

    @Test
    void sort3Items() {
        // 준비

        // 실행
        List<String> list = sort.sortByLength(Arrays.asList("aa","ccc" ,"b"));

        // 검증
        Assertions.assertThat(list).isEqualTo(List.of("b", "aa","ccc"));
    }

    @Test
    void sortAlreadySorted() {
        // 준비

        // 실행
        List<String> list = sort.sortByLength(Arrays.asList("b","aa" ,"ccc"));

        // 검증
        Assertions.assertThat(list).isEqualTo(List.of("b", "aa","ccc"));

    }
}
