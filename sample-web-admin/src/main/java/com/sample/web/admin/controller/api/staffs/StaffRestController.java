package com.sample.web.admin.controller.api.staffs;

import com.sample.domain.dto.common.Page;
import com.sample.domain.dto.common.Pageable;
import com.sample.domain.dto.system.Staff;
import com.sample.domain.exception.ValidationErrorException;
import com.sample.domain.service.system.StaffService;
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
@RequestMapping(path = "/api/staff", produces = MediaType.APPLICATION_JSON_VALUE)
public class StaffRestController extends AbstractRestController {

    @Override
    public String getFunctionName() {
        return "API_STAFF";
    }

    @Autowired
    StaffService staffService;

    /**
     * 担当者を一括取得します。
     *
     * @param query
     * @param page
     * @return
     */
    @GetMapping
    public PageableResource index(StaffQuery query, @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        // 入力値からDTOを作成する
        val where = modelMapper.map(query, Staff.class);

        // 10件で区切って取得する
        Page<Staff> results = staffService.findAll(where, Pageable.DEFAULT_PAGEABLE);

        PageableResource resource = modelMapper.map(results, PageableResourceImpl.class);
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * 担当者を取得します。
     *
     * @param staffId
     * @return
     */
    @GetMapping(value = "/{staffId}")
    public Resource show(@PathVariable Long staffId) {
        // 1件取得する
        Staff staff = staffService.findById(staffId);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(staff));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * 担当者を追加します。
     *
     * @param
     */
    @PostMapping
    public Resource create(@Validated @RequestBody Staff inputStaff, Errors errors) {
        // 入力エラーがある場合
        if (errors.hasErrors()) {
            throw new ValidationErrorException(errors);
        }

        // 1件追加する
        Staff staff = staffService.create(inputStaff);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(staff));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * 担当者を更新します。
     *
     * @param
     */
    @PutMapping
    public Resource update(@Validated @RequestBody Staff inputStaff, Errors errors) {
        // 入力エラーがある場合
        if (errors.hasErrors()) {
            throw new ValidationErrorException(errors);
        }

        // 1件更新する
        Staff staff = staffService.update(inputStaff);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(staff));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * 担当者を削除します。
     *
     * @param
     */
    @DeleteMapping(value = "/{staffId}")
    public Resource delete(@PathVariable("staffId") Long staffId) {
        // 1件取得する
        Staff staff = staffService.delete(staffId);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(staff));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }
}
