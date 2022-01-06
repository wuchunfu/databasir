package com.databasir.api.api;

import com.databasir.common.JsonData;
import com.databasir.api.biz.data.SchemaMetaResponse;
import com.databasir.api.biz.data.SchemaMetaVersionResponse;
import com.databasir.api.biz.service.SchemaMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/schema_metas")
@Validated
public class SchemaMetaController {

    private final SchemaMetaService schemaMetaService;

    @PostMapping("/sync_by_schema_source_id/{schemaSourceId}")
    public JsonData<Void> sync(@PathVariable Integer schemaSourceId) {
        schemaMetaService.syncBySchemaSourceId(schemaSourceId);
        return JsonData.ok();
    }

    @GetMapping("/get_by_schema_source_id/{schemaSourceId}")
    public JsonData<SchemaMetaResponse> getOneBySchemaSourceId(@PathVariable Integer schemaSourceId) {
        return schemaMetaService.getBySchemaSourceId(schemaSourceId)
                .map(JsonData::ok)
                .orElseGet(JsonData::ok);
    }

    @GetMapping("/versions/get_by_schema_source_id/{schemaSourceId}")
    public JsonData<Page<SchemaMetaVersionResponse>> getVersionsBySchemaSourceId(@PathVariable Integer schemaSourceId,
                                                                                 @PageableDefault(size = 20,
                                                                                         sort = "id", direction = Sort.Direction.DESC)
                                                                                         Pageable page) {
        return JsonData.ok(schemaMetaService.getVersionsBySchemaSourceId(schemaSourceId, page));
    }

    /**
     * paramaters: id、version
     */
    @GetMapping("/{id}")
    public JsonData<SchemaMetaResponse> getOne(@PathVariable Integer id,
                                               @RequestParam(required = false) Long version) {
        return schemaMetaService.getOne(id, version)
                .map(JsonData::ok)
                .orElseGet(JsonData::ok);
    }

}
