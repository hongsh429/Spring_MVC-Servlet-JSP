package hello.servlet;


import hello.servlet.web.springmvc.old.OldController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
public class TestWork {

    @Test
    void test(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(OldController.class);
        OldController bean = ac.getBean(OldController.class);
        System.out.println("bean : " + bean);

    }
}
