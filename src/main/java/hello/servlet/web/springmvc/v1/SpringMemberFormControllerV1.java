package hello.servlet.web.springmvc.v1;

import org.springframework.beans.factory.support.MethodOverride;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 이렇게 작성해도 아래와 동일한 결과를 얻을 수 있음
//@Component
//@RequestMapping
// but!! Spring 3.xx 부터는 위의 두가지 에노테이션에 @Controller 기능이 빠져서 안됨!

@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form"); // application.properties 에서  prefix / suffix 두개 처리한거
    }
}

/*
@Controller
 - 자동으로 Spring Bean으로 등록이 된다. ComponentScan의 대상이 됨
 - 스프링 MVC에서 annotation 기반 컨트롤러로 인식!

@RequestMapping(
 - 요청 정보를 매핑한다

ModelAndView
 - 모델과 뷰 정보를 담아서 반환


* */
