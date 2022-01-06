package com.databasir.api.api;

import com.databasir.common.JsonData;
import com.databasir.api.biz.data.*;
import com.databasir.api.biz.service.SchemaSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/schema_sources")
@Validated
public class SchemaSourceController {

    private final SchemaSourceService schemaSourceService;

    @PostMapping
    public JsonData<Void> create(@RequestBody @Valid SchemaSourceCreateRequest request) {
        schemaSourceService.create(request);
        return JsonData.ok();
    }

    @PatchMapping
    public JsonData<Void> update(@RequestBody @Valid SchemaSourceUpdateRequest request) {
        schemaSourceService.update(request);
        return JsonData.ok();
    }

    @DeleteMapping("/{id}")
    public JsonData<Void> delete(@PathVariable Integer id) {
        schemaSourceService.delete(id);
        return JsonData.ok();
    }

    @GetMapping("/{id}")
    public JsonData<SchemaSourceResponse> getOne(@PathVariable Integer id) {
        return JsonData.ok(schemaSourceService.getOne(id));
    }

    @GetMapping
    public JsonData<Page<SchemaSourceSimpleResponse>> list(@PageableDefault(size = 20, sort = "id",
            direction = Sort.Direction.DESC)
                                                                   Pageable page,
                                                           SchemaSourceListCondition condition) {
        return JsonData.ok(schemaSourceService.list(page, condition));
    }
}
