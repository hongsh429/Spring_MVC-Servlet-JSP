package hello.servlet.basic.response;

import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);  // 200 숫자와 동일

        //[response-headers]
        response.setHeader("Content-type", "text/plain;charset=utf-8");//한글안깨지는설정
        response.setHeader("Cache-Control", "no-cache no-store must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("me-header", "hello");   // 임의의 헤더를 만들수있다.

        content(response);
        cookie(response);
//        redirect(response);

        // [message Body]
        HelloData helloData = new HelloData();
        helloData.setAge(30);
        helloData.setUsername("홍승현");
        PrintWriter writer = response.getWriter();
        writer.println(helloData);  // 객체를 보내면 toString 값이 나간다
        writer.println("안녕하세요");    // ContentLength : 3*5 + 2 (enter 포함)
        writer.print("안녕하세요");      // 15       utf-8 encoding - 한글3 영어1 공백2
        writer.print("ok");             // 2

        /*
        Cache-Control:
        no-cache no-store must-revalidate

        Connection: keep-alive
        Content-Length: 4
        Conttent-Type: text/plain;charset=utf-8
        Date: Tue, 06 Jun 2023 20:46:45 GMT
        Keep-Alive: timeout=60
        Me-Header: hello
        Pragma: no-cache

        */
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);

        // response header 보면 나온다~
        // Set-Cookie: myCookie=good; Max-Age=600; Expires=Tue, 06 Jun 2023 21:06:53 GMT
    }


    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");

        // response header 가보면!
        // Location: http://localhost:8080/basic/hello-form.html   << 이 정보가 들어있다.
        // 그래서 우리가 redirect할 때 정보를 같이 보내고 싶다면? response.addxxx() 메소드가 있는것!

    }
}
