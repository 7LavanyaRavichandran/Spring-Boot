package com.lavanya.spring_boot_web_application.FirstWebApplication.hello;


import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloWorldController {
    // http://localhost:8080/hello-world
    //"say-hello" =>  "Hello World!"
    @RequestMapping("hello-world")
    @ResponseBody
    public String helloworld() {
        return "Hello World!";
    }

    // Printing helloworld using HTML
    @RequestMapping("hello-world-html")
    @ResponseBody
    public String helloworldHTML(){
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Hello World!</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1>Hello World!</h1>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    // Printing helloworld using jsp page below code will return to hello.jsp
    //"say-hello-jsp" => sayHello.jsp
    @RequestMapping("hello-world-jsp")
    public String helloworldJSP() {
        return "hello";  //returning jsp file name
    }

    //Using Model to map the value in JSP
    //hello --> SayHellowWorldController --> hello.jsp
    //http;//localhost:8080/hello-world-jsp-model?name=Lavanya
    @RequestMapping("hello-world-jsp-model")
    public String helloworldModel(@RequestParam String name, ModelMap model){
        model.put("name", name);
        return "hello";

    }


}
