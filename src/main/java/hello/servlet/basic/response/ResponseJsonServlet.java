package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-type : application/json
//        response.setHeader("Content-type", "application/json;charset=utf-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(30);

        // {"username":"kim", "age":20}
        // 이렇게 하려면 ObjectMapper 객체가 필요. SpringBoot에서는 Jackson이라는 라이브러리가 내장
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);

        // 들어가서보면
        /*
        {
            "username": "kim",
            "age": 30
        }
            이런 내용이 들어있다. 바디에~~~~~
        */

    }
}
