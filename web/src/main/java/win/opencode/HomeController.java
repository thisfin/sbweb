package win.opencode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wenyou on 2016/10/25.
 */
@RestController
@Slf4j
public class HomeController {
    @RequestMapping("/")
    String home() {
        log.error("home");
        return "hello";
    }
}
