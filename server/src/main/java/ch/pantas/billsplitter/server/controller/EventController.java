package ch.pantas.billsplitter.server.controller;

import ch.pantas.billsplitter.server.model.User;
import ch.pantas.billsplitter.server.store.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/groups")
public class EventController {

    @Autowired
    private UserStore userStore;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String home() {
        return "Splitty!";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public String importGroup() {

        User user = new User(UUID.randomUUID(), "MyUser");

        userStore.save(user);

        return "Saved User: " + user.getId();
    }
}