package ch.pantas.billsplitter.server;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
@RequestMapping("/")
public class EventImportController {

    @RequestMapping("")
    @ResponseBody
    public String home(){
        return "Splitty!";
    }

    @RequestMapping(value = "group", method = RequestMethod.POST)
    @ResponseBody
    public String importGroup() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EventImportController.class, args);
    }
}