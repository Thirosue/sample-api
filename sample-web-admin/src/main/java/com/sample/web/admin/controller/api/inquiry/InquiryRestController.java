package com.sample.web.admin.controller.api.inquiry;

import com.sample.domain.dto.common.Page;
import com.sample.domain.dto.common.PageFactory;
import com.sample.domain.enmn.Category;
import com.sample.domain.enmn.Genre;
import com.sample.domain.enmn.Sex;
import com.sample.web.base.controller.api.AbstractRestController;
import com.sample.web.base.controller.api.resource.PageableResource;
import com.sample.web.base.controller.api.resource.PageableResourceImpl;
import com.sample.web.base.controller.api.resource.Resource;

import java.util.Arrays;
import java.util.List;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.stream.Stream;

import static com.sample.domain.util.DomaUtils.createSelectOptions;
import static com.sample.web.base.WebConst.MESSAGE_SUCCESS;
import static com.sample.web.base.WebConst.defaultRows;
import static com.sample.web.base.helper.PagerHelper.setPage;
import static com.sample.domain.enmn.Category.*;
import static com.sample.domain.enmn.Genre.*;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/api/inquiry", produces = MediaType.APPLICATION_JSON_VALUE)
public class InquiryRestController extends AbstractRestController {

    @Override
    public String getFunctionName() {
        return "API_INQUIRY";
    }

    @Autowired
    protected PageFactory pageFactory;

    public static List<InquiryResource> inquiryResourceList = new ArrayList<>();

    static {
        val resource = new InquiryResource();
        resource.setName("john doe");
        resource.setEmail("test@sample.com");
        resource.setCategory(etc);
        resource.setTitle("問い合わせタイトル");
        resource.setBody("問い合わせ内容");
        inquiryResourceList.add(resource);

        val resource2 = new InquiryResource();
        resource2.setName("robert de niro");
        resource2.setEmail("hoge@sample.com");
        resource2.setCategory(ticket);
        resource2.setGenre(Arrays.asList(edm, randb));
        resource2.setTitle("チケットの払い戻しについて");
        resource2.setBody("払い戻しは可能でしょうか");
        inquiryResourceList.add(resource2);

        val resource3 = new InquiryResource();
        resource3.setName("al pacino");
        resource3.setEmail("fuga@sample.com");
        resource3.setBody("スカーフェイス");
        inquiryResourceList.add(resource3);
    }

    /**
     * 問い合わせを一括取得します。
     *
     * @param query
     * @param page
     * @return
     */
    @GetMapping
    public PageableResource index(InquiryQuery query,
                                  @RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = defaultRows) int rows) {

        // 入力値からDTOを作成する
        List<InquiryResource> data = inquiryResourceList;

        if(query.category != null) {
            data = data.stream().filter(d->d.category != null && d.category.equals(query.category)).collect(toList());
        }
        if(query.genre != null) {
            data = data.stream().filter(d->d.genre != null && query.genre.stream().anyMatch(param->d.genre.contains(param))).collect(toList());
        }
        if(query.title != null) {
            data = data.stream().filter(d->d.title != null && d.title.indexOf(query.title) != -1).collect(toList());
        }

        val pageable = setPage(page, rows);
        val options = createSelectOptions(pageable).count();

        // 10件で区切って取得する
        Page<InquiryResource> results = pageFactory.create(data, pageable, data.size());

        PageableResource resource = modelMapper.map(results, PageableResourceImpl.class);
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * 問い合わせを追加します。
     *
     * @param
     */
    @PostMapping
    public Resource create(@Validated @RequestBody InquiryResource inquiryResource) {
        inquiryResourceList.add(inquiryResource);

        Resource resource = resourceFactory.create();
        resource.setMessage(getMessage(MESSAGE_SUCCESS));

        return resource;
    }

    /**
     * ジャンルを一括取得します。
     *
     * @return
     */
    @GetMapping("/genre")
    public List<Option> genre() {
        return Stream.of(Genre.values()).map(e->Option.create(e.name(), e.getValue())).collect(toList());
    }

    /**
     * カテゴリを一括取得します。
     *
     * @return
     */
    @GetMapping("/category")
    public List<Option> category() {
        return Stream.of(Category.values()).map(e->Option.create(e.name(), e.getValue())).collect(toList());
    }

    /**
     * 性別を一括取得します。
     *
     * @return
     */
    @GetMapping("/sex")
    public List<Option> sex() {
        return Stream.of(Sex.values()).map(e->Option.create(e.name(), e.getValue())).collect(toList());
    }
}
