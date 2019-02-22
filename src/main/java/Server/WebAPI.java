package Server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebAPI {



    @RequestMapping("/request")
    public String Request(@RequestParam(value="name", defaultValue="World") String name)
    {



        return "Hello " + name;
    }






}
