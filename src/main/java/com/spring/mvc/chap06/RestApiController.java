package com.spring.mvc.chap06;

import com.spring.mvc.jdbc.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/rests")
public class RestApiController {

    @GetMapping("/hello")
    public String hello(){
        return "안녕하세요!";
    }

    @GetMapping("/foods")
    public List<String> foods(){
//        String[] foodlist = {"탕수육", "족발", "마라탕"};
        List<String> foodlist = List.of("탕수육", "족발", "마라탕");
        return foodlist;
    }

    @GetMapping("/person")
    public Person person(){
        Person p = new Person(1L, "루피", 2);
        return p;
    }

    @GetMapping("/person-list")
    public ResponseEntity<?> personlist(){
        Person p1 = new Person(4L, "뜨든", 2);
        Person p2 = new Person(2L, "뚜뚜루", 3);
        Person p3 = new Person(3L, "루카코", 67);
        List<Person> personList = List.of(p1, p2, p3);
        return ResponseEntity.ok().body(personList);
    }

    @GetMapping("/bmi")
    public ResponseEntity<?> bmi(
            @RequestParam(required = false) Double cm,
            @RequestParam(required = false) Double kg){

        if(cm == null || kg == null){
            return ResponseEntity.badRequest().body("키랑 몸무게 보내라고");
        }
        double bmi = kg / (cm / 100) * (cm / 100);

        HttpHeaders headers = new HttpHeaders();
        headers.add("fruits", "melon");
        headers.add("hobby", "swim");
        return ResponseEntity.ok().headers(headers).body(bmi);
    }
}
