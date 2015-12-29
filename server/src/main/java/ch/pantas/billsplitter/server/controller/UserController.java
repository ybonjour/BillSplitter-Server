package ch.pantas.billsplitter.server.controller;

import ch.pantas.billsplitter.server.persistence.EventKeeper;
import ch.pantas.billsplitter.server.services.datatransfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {

    private EventKeeper eventKeeper;

    @Autowired
    public UserController(EventKeeper eventKeeper) {
        this.eventKeeper = eventKeeper;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Collection<UserDto> listUsers() {
        return eventKeeper.getUsers();
    }
}
