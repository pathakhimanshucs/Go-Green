package server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebApi {

    @RequestMapping("/request")
    public String request(@RequestParam(value = "name", defaultValue = "Anonymous") String name) {
        return "Hello " + name;
    }
}
