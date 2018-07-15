package com.sample.web.admin.controller.api.holidays;

import com.sample.domain.dto.common.Page;
import com.sample.domain.dto.common.Pageable;
import com.sample.domain.dto.system.Holiday;
import com.sample.domain.dto.user.User;
import com.sample.domain.exception.ValidationErrorException;
import com.sample.domain.service.system.HolidayService;
import com.sample.domain.service.users.UserService;
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
@RequestMapping(path = "/api/holiday", produces = MediaType.APPLICATION_JSON_VALUE)
public class HolidayRestController extends AbstractRestController {

    @Override
    public String getFunctionName() {
        return "API_HOLIDAY";
    }

    @Autowired
    HolidayService holidayService;

    /**
     * 祝日を一括取得します。
     *
     * @param query
     * @param page
     * @return
     */
    @GetMapping
    public PageableResource index(UserQuery query, @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        // 入力値からDTOを作成する
        val where = modelMapper.map(query, Holiday.class);

        // 10件で区切って取得する
        Page<Holiday> results = holidayService.findAll(where, Pageable.DEFAULT_PAGEABLE);

        PageableResource resource = modelMapper.map(results, PageableResourceImpl.class);
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * 祝日を取得します。
     *
     * @param holidayId
     * @return
     */
    @GetMapping(value = "/{holidayId}")
    public Resource show(@PathVariable Long holidayId) {
        // 1件取得する
        Holiday result = holidayService.findById(holidayId);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(result));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * 祝日を追加します。
     *
     * @param
     */
    @PostMapping
    public Resource create(@Validated @RequestBody Holiday inputHoliday, Errors errors) {
        // 入力エラーがある場合
        if (errors.hasErrors()) {
            throw new ValidationErrorException(errors);
        }

        // 1件追加する
        Holiday holiday = holidayService.create(inputHoliday);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(holiday));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * 祝日を更新します。
     *
     * @param
     */
    @PutMapping
    public Resource update(@Validated @RequestBody Holiday inputHoliday, Errors errors) {
        // 入力エラーがある場合
        if (errors.hasErrors()) {
            throw new ValidationErrorException(errors);
        }

        // 1件更新する
        Holiday holiday = holidayService.update(inputHoliday);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(holiday));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * 祝日を削除します。
     *
     * @param
     */
    @DeleteMapping(value = "/{holidayId}")
    public Resource delete(@PathVariable("holidayId") Long holidayId) {
        // 1件取得する
        Holiday holiday = holidayService.delete(holidayId);

        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(holiday));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }
}
