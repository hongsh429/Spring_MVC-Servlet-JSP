package hello.servlet.web.springmvc.old;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 예날 방식의 컨트롤러  (!= 우리가 아는 @Controller가 아니다)
@Component("/springmvc/old-controller") // Spring Bean의 이름이 다음과 같이 바뀐다.  Spring Bean의 이름을 지금 url로 바꾼것이다.
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}