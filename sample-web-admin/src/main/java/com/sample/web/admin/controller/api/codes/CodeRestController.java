package com.sample.web.admin.controller.api.codes;

import com.sample.domain.dto.common.Page;
import com.sample.domain.dto.common.Pageable;
import com.sample.domain.dto.system.Code;
import com.sample.domain.dto.system.Holiday;
import com.sample.domain.exception.ValidationErrorException;
import com.sample.domain.service.system.CodeService;
import com.sample.web.admin.controller.api.user.UserQuery;
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
@RequestMapping(path = "/api/code", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodeRestController extends AbstractRestController {

    @Override
    public String getFunctionName() {
        return "API_CODE";
    }

    @Autowired
    CodeService codeService;

    /**
     * コードを一括取得します。
     *
     * @param query
     * @param page
     * @return
     */
    @GetMapping
    public PageableResource index(CodeQuery query, @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        // 入力値からDTOを作成する
        val where = modelMapper.map(query, Code.class);

        // 10件で区切って取得する
        Page<Code> results = codeService.findAll(where, Pageable.DEFAULT_PAGEABLE);

        PageableResource resource = modelMapper.map(results, PageableResourceImpl.class);
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * コードを取得します。
     *
     * @param codeId
     * @return
     */
    @GetMapping(value = "/{codeId}")
    public Resource show(@PathVariable Long codeId) {
        // 1件取得する
        Code result = codeService.findById(codeId);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(result));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * コードを追加します。
     *
     * @param
     */
    @PostMapping
    public Resource create(@Validated @RequestBody Code inputCode, Errors errors) {
        // 入力エラーがある場合
        if (errors.hasErrors()) {
            throw new ValidationErrorException(errors);
        }

        // 1件追加する
        Code code = codeService.create(inputCode);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(code));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * コードを更新します。
     *
     * @param
     */
    @PutMapping
    public Resource update(@Validated @RequestBody Code inputCode, Errors errors) {
        // 入力エラーがある場合
        if (errors.hasErrors()) {
            throw new ValidationErrorException(errors);
        }

        // 1件更新する
        Code code = codeService.update(inputCode);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(code));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * コードを削除します。
     *
     * @param
     */
    @DeleteMapping(value = "/{codeId}")
    public Resource delete(@PathVariable("codeId") Long codeId) {
        // 1件取得する
        Code code = codeService.delete(codeId);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(code));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }
}
