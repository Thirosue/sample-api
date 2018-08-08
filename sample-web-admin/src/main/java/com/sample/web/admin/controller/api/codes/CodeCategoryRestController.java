package com.sample.web.admin.controller.api.codes;

import com.sample.domain.dto.common.Page;
import com.sample.domain.dto.common.Pageable;
import com.sample.domain.dto.system.Code;
import com.sample.domain.dto.system.CodeCategory;
import com.sample.domain.exception.ValidationErrorException;
import com.sample.domain.service.system.CodeCategoryService;
import com.sample.domain.service.system.CodeService;
import com.sample.web.base.controller.api.AbstractRestController;
import com.sample.web.base.controller.api.resource.PageableResource;
import com.sample.web.base.controller.api.resource.PageableResourceImpl;
import com.sample.web.base.controller.api.resource.Resource;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

import static com.sample.web.base.WebConst.MESSAGE_SUCCESS;

@RestController
@RequestMapping(path = "/api/codeCategory", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodeCategoryRestController extends AbstractRestController {

    @Override
    public String getFunctionName() {
        return "API_CODECATEGORY";
    }

    @Autowired
    CodeCategoryService codeCategoryService;

    /**
     * コード分類を一括取得します。
     *
     * @return
     */
    @GetMapping
    public PageableResource index() throws IOException {

        Page<CodeCategory> results = codeCategoryService.fetchAll();

        PageableResource resource = modelMapper.map(results, PageableResourceImpl.class);
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }
}
