package tobyspring.hellospring.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor {

    @Override
    public String execute(URI uri) throws IOException {
        String response;
        // url을 HttpURLConnection으로 casting하여 사용하는것이 좋다. -> http가 제공하는 기능을 사용할 수 있기 때문.
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        // 원하는 url데이터 읽어오기

        // try문으으로 bufferedReader를 처리하면 자동으로 br.close() 처리를 해준다 -> 제일 상위에 AutoClose라는 인터페이스를 가지고 있ㄱ
        try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            response = br.lines().collect(Collectors.joining());
        }
        return response;
    }
}
