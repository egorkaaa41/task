package ru.atom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.atom.dao.GUIDDao;
import ru.atom.model.GUID;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private final GUIDDao guidDao = new GUIDDao();


    /**
     * curl -X POST -i localhost:8080/task
     */
    @RequestMapping(
            path = "task",
            method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> guid() throws InterruptedException{
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        GUID newGuid = new GUID().setId(randomUUIDString).setStatus("created");
        guidDao.insert(newGuid);
        guidDao.UpDate(newGuid.setStatus("running"));
        Thread.sleep(120000);

        guidDao.UpDate(newGuid.setStatus("finished"));
        log.info("[" + randomUUIDString + "]  ");
        return ResponseEntity.status(202).body(newGuid.getGuid());



    }



    /**
     * http://localhost:8080/task/{id}
     */

    @GetMapping("/task/{id}")
    @ResponseBody
    public String id(@PathVariable String id) {
        List<GUID> authors = guidDao.GetAllWhere("task.guid = '" + id + "'");

        if (!(id.length() == 36 && id.charAt(8) == '-' && id.charAt(13) == '-' && id.charAt(18) == '-')){
            return "400";
        }
        if (authors.isEmpty()) {
            return "404";
        }


        String responseBody = authors.stream()
                .map(m -> "Status: <font size=\"4\" color=#A52A2A face=\"Arial\">" + m.getStatus() + "</font>"
                   + "   timestamp: <font size=\"4\" color=#A52A2A face=\"Arial\">" + m.getTimestamp() +  "</font>"   )
                .collect(Collectors.joining("\n"));

        return "200 "  + responseBody;
    }


}
