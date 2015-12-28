package ch.pantas.billsplitter.server.controller;

import ch.pantas.billsplitter.server.services.EventKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    EventKeeper eventKeeper;

    @RequestMapping(value = "snapshot", method = RequestMethod.POST)
    @ResponseBody
    public void takeSnapshot() {
        eventKeeper.takeSnapshot();
    }
}
