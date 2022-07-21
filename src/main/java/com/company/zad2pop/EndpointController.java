package com.company.zad2pop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/CSV")
public class EndpointController {

    private final EndpointService endpointService;

    @Autowired
    public EndpointController(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    @GetMapping("/1/{size}")
    public void getPreparedColumns(HttpServletResponse response, @PathVariable int size) throws IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().print(endpointService.endpoint1(size));

    }
    @GetMapping("/2/{size}/{requestList}")
    public void getChosenColumn(HttpServletResponse response, @PathVariable int size, @PathVariable List<String> requestList) throws IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().print(endpointService.endpoint2(size, requestList));
    }
}