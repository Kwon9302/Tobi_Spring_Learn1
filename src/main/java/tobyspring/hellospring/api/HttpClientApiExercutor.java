package tobyspring.hellospring.api;

import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientApiExercutor implements ApiExecutor {
    @Override
    public String execute(URI uri) throws IOException {

        // 설명 : HttpRequest로 우리가 사용라혀는 uri를 던져준다 Request로 -> 애초에 서비스가 내부 시스템에서 자동으로 api를 사용하도록 요청해서 환율정보를 받은다음
        // 해결해야하기 때문에 내부적으로 request를 생성하고 HttpClient를 안에 request를 넣어서 원하는 api정보를 가져온다.
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpClient client = HttpClient.newBuilder().build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
