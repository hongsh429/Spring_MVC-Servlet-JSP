package hello.servlet.basic.request;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);

    }

    private static void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); // GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); // HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); // http
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        System.out.println("request.getQueryString() = " +
                request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();

//        --- REQUEST-LINE - start ---
//                request.getMethod() = GET
//        request.getProtocol() = HTTP / 1.1
//        request.getScheme() = http
//        request.getRequestURL() = http://localhost:8080/request-header
//        request.getRequestURI() = /request - header
//        request.getQueryString() = null
//        request.isSecure() = false
//        --- REQUEST - LINE - end-- -
    }


    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println("headerName = " + headerName);
//        }

        // 위 아래 같은 내용
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println("headerName = " + headerName));

        System.out.println("---- Headers - end ---");
        System.out.println();

/*결과
        --- Headers - start ---
        headerName = host
        headerName = connection
        headerName = cache-control
        headerName = sec-ch-ua
        headerName = sec-ch-ua-mobile
        headerName = sec-ch-ua-platform
        headerName = upgrade-insecure-requests
        headerName = user-agent
        headerName = accept
        headerName = sec-fetch-site
        headerName = sec-fetch-mode
        headerName = sec-fetch-user
        headerName = sec-fetch-dest
        headerName = accept-encoding
        headerName = accept-language
        ---- Headers - end ---
* */
    }


    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " +
                        locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " +
                request.getContentType());
        System.out.println("request.getContentLength() = " +
                request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();

/*
       ---Header 편의 조회 start---
        [Host 편의 조회]
        request.getServerName() = localhost
        request.getServerPort() = 8080
                [Accept - Language 편의 조회]
        locale = ko
        locale = en_US
        locale = en
        locale = ko_KR
        request.getLocale() = ko
        [cookie 편의 조회]

        [Content 편의 조회]
        request.getContentType() = null >> get방식에서는 따로 contentType이 없다
        request.getContentLength() = -1
        request.getCharacterEncoding() = UTF - 8
        --- Header 편의 조회 end---
*/
    }

    //기타 정보
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " +
                request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " +
                request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " +
                request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " +
                request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " +
                request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " +
                request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();

/*          --- 기타 조회 start ---
            [Remote 정보]
            request.getRemoteHost() = 0:0:0:0:0:0:0:1
            request.getRemoteAddr() = 0:0:0:0:0:0:0:1
            request.getRemotePort() = 54305
            [Local 정보]
            request.getLocalName() = localhost
            request.getLocalAddr() = 0:0:0:0:0:0:0:1
            request.getLocalPort() = 8080
            --- 기타 조회 end ---
*/
    }

}
