package com.company.zad2pop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.company.zad2pop.EndpointService.endpoint1;
import static com.company.zad2pop.EndpointService.endpoint2;

@RestController
@RequestMapping("/CSV")
public class EndpointController {
    @GetMapping("/1/{size}")
    public static void getPreparedColumns(HttpServletResponse response, @PathVariable int size) throws IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().print(endpoint1(size));

    }
    @GetMapping("/2/{size}/{requestList}")
    public static void getChosenColumn(HttpServletResponse response, @PathVariable int size, @PathVariable List<String> requestList) throws IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().print(endpoint2(size, requestList));
    }
}