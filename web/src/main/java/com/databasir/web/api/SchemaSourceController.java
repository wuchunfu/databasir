package com.databasir.web.api;

import com.databasir.web.core.domain.data.*;
import com.databasir.web.core.domain.service.SchemaSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void create(@RequestBody @Valid SchemaSourceCreateRequest request) {
        schemaSourceService.create(request);
    }

    @PatchMapping
    public void update(@RequestBody @Valid SchemaSourceUpdateRequest request) {
        schemaSourceService.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        schemaSourceService.delete(id);
    }

    @GetMapping("/{id}")
    public SchemaSourceResponse getOne(@PathVariable Integer id) {
        return schemaSourceService.getOne(id);
    }

    @GetMapping
    public Page<SchemaSourceSimpleResponse> list(@PageableDefault(size = 20) Pageable page,
                                                 SchemaSourceListCondition condition) {
        return schemaSourceService.list(page, condition);
    }
}
