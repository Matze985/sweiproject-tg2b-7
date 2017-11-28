package hello;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/post")
    public String post() {
        return "post";
    }

    @RequestMapping("/post")
    public String postSubmit(@ModelAttribute Post post) {
        System.out.println(post.getEmail());
        System.out.println(post.getPost());
        return "result";
    }
}
