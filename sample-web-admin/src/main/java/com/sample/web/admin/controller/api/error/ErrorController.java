package com.sample.web.admin.controller.api.error;

import com.sample.domain.exception.APIException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.sample.web.base.controller.api.AbstractRestController;


@RestController
@RequestMapping(path = "/api/error", produces = MediaType.APPLICATION_JSON_VALUE)
public class ErrorController extends AbstractRestController {

    @Override
    public boolean authorityRequired() {
        return false;
    }

    @Override
    public String getFunctionName() {
        return "API_ERROR";
    }

    @PutMapping(value = "/{status}")
    public void put(@PathVariable Integer status) {
        throw new APIException(String.valueOf(status));
    }

    @PostMapping(value = "/{status}")
    public void post(@PathVariable Integer status) {
        throw new APIException(String.valueOf(status));
    }

    @DeleteMapping(value = "/{status}")
    public void delete(@PathVariable Integer status) {
        throw new APIException(String.valueOf(status));
    }
}
