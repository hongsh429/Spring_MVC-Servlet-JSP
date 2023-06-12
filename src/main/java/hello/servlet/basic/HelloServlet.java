package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 싱글톤!
@WebServlet(name = "helloServlet", urlPatterns = "/hello")  // url= /hello 가들어오면 실행되는 클래스
public class HelloServlet extends HttpServlet {

    @Override                           //client에서 전달받은 request   client에게 내려주기 위한 response
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request); // request = org.apache.catalina.connector.RequestFacade@16934db1
        System.out.println("response = " + response); // response = org.apache.catalina.connector.ResponseFacade@a42e23f

        //query parameter 읽기
        // http://localhost:8080/hello?username=kim
        String username = request.getParameter("username");
        System.out.println("username = " + username); // username = kim


        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);



    }
}
