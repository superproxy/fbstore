package faststore.configserver.admin.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        // 跳转的url
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        // 跳转的url
        return "index";
    }
}
