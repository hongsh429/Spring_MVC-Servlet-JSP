package hello.servlet.basic.request;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

/**
        * 1. 파라미터 전송 기능
        * http://localhost:8080/request-param?username=hello&age=20
        * <p>
 * 2. 동일한 파라미터 전송 가능
         * http://localhost:8080/request-param?username=hello&username=kim&age=20
 * */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");

        System.out.println("[전체 파라미터 조회] - start");
        Enumeration<String> parameterNames = request.getParameterNames();
        request.getParameterNames().asIterator()
                        .forEachRemaining(param-> System.out.println(param + " = " + request.getParameter(param)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        // 같은 이름으로 두개가 전달 될때는?
        System.out.println("[이름이 같은 복수 파라미터 조회방법]");
        String[] usernames = request.getParameterValues("username");
        System.out.println(Arrays.toString(usernames)); //
        for (String name : usernames) {
            System.out.println("name = " + name);
        }


        response.getWriter().write("ok");
    }
}
