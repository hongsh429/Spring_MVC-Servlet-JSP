package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        // 인터페이스의 다형성을 이용하여 모든 컨드롤러를 담을 수 있다.
        // 내가 처리하고 싶은 로직이 담겨 있는 객체 생성
        ControllerV3 controller = controllerV3Map.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 객체는 현재 process함수로 view는 박혀 있고, 모델만 남겨주면된다.

        //paramMap
        Map<String, String> paramMap = createParamMap(request);
        // String, String인 이유? > 웹에서의 요청 정보는 String으로 넘어온다

        // 드디어, 모델(데이터 전달 역할)을 만들어 mv 객체에 담아 놓는다.
        // 현재 논리 경로는 박혀 있고, Map<"key", "로직이 처리된 후의 리턴값(Object) ">을 mv객체에 세팅.getter 사용
        ModelView mv = controller.process(paramMap);
        // ↑↑↑
        // 여기까지 진행되었다면, 1. 각각의 컨트롤러 구현체마다 "논리 경로"를 박아놨고,
        //                      2. createParamMap(요청파라미터 내부 값 꺼내기)
        //                                    > mv의 Map<String, 로직실행결과 Object>으로 값을 가지고 있다


        String viewName = mv.getViewName();// 논리이름 new-form 꺼내기
        MyView view = viewResolver(viewName); // render할 수 있는 MyView 객체 생성

        // mv의 모델을 하나씩 돌며, request.setxxx을 실행 후,dispatcher로 forward메소드 실행하는작업수행
        view.render(mv.getModel(), request, response);

    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
