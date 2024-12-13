package vttp.batch5.ssf.noticeboard.controllers;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.batch5.ssf.noticeboard.models.Notice;
@RestController
@RequestMapping
public class NoticeRestController {
    
    @Autowired
    NoticeService noticeService;
    // POST /notice
   // Content-Type: application/json
   // Accept: application/json
   @PostMapping(path="/notice", produces = MediaType.APPLICATION_JSON_VALUE
         , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postAsJsonBody(@RequestBody String payload) {
        //cr8 payload
        Notice n = new Notice();
        JsonObject json = Json.createObjectBuilder()
          .add("title", n.getTitle())
          .add("poster", n.())
          .build();

        // Create a request
        RequestEntity<String> req = RequestEntity
            .body(json.toString(), String.class);

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);
        String payload = json.getBody();
       
        if (resp != null) {
            //Return the article with a 200 OK status
            return ResponseEntity.ok(resp.toString());
        } else {
            //If article not found, return 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.printf(">>> PAYLOAD: %s\n", payload);
    }
     //GET /status
    // Content-Type: application/json
    @GetMapping("/status")
    public ResponseEntity<String> getRandomKey() {
        String keyResponse = NoticeRepo.getRandom();

        if (keyResponse != null) {
            return ResponseEntity.status(200).ok("{}");
        } else {
            return ResponseEntity.status(503).body("{}");
        }
    }
    
}
