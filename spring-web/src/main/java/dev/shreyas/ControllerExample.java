package dev.shreyas;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

@Controller
public class ControllerExample {

    @GetMapping(value = "/welcome", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String welcomeAsHTML() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Hello Shreyas</title>
                </head>
                <body>
                <bold><h1>Hello, It is from View</h1></bold>
                </body>
                </html>
                """;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String home() {
        return "Hello World!";
    }

    @RequestMapping(path = "/model", method = RequestMethod.GET)
    public Model home(Model model) {
        model.addAttribute("message", "Hello World!");
        return model;
    }

    // return html page
    @GetMapping("/view")
    public String user(Model model) {
        return "view";
    }
}

@RestController
class RestControllerExample {

    @InitBinder
    protected void initBinder(DataBinder binder) {
        binder.registerCustomEditor(String.class, "firstNAme", new FirstNameCustomPropertyEditor());
    }

    @GetMapping("/rest")
    public String rest() {
        return "Hello World!";
    }

    @GetMapping("/fetchUserName")
    public ResponseEntity<String> fetchUSer(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName", required = false) String lastName) {
        return ResponseEntity.status(HttpStatus.OK).body(firstName);
    }

    @GetMapping("/rest/{name}")
    public String rest(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping("/customer")
    public String getCustomer(@RequestBody Customer customer) {
        return customer.toString();
    }
}

class FirstNameCustomPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.trim().toLowerCase());
    }
}

class Customer {
    @JsonProperty("user_name")
    String name;
    Integer age;
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

