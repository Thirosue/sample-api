package com.sample.web.admin.controller.api.log;

import com.sample.web.base.controller.api.AbstractRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/log", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogController extends AbstractRestController {

    @Override
    public boolean authorityRequired() {
        return false;
    }

    @Override
    public String getFunctionName() {
        return "API_STATISTICS";
    }

    @GetMapping(path = "/access")
    public void access(@RequestParam(required = false) String path) {
        log.info(path);
    }

    @PostMapping(path = "/error")
    public void error(@RequestParam(required = false) String path) {
        log.info(path);
    }
}
