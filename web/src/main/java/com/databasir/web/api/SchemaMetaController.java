package com.databasir.web.api;

import com.databasir.web.core.domain.data.SchemaMetaResponse;
import com.databasir.web.core.domain.service.SchemaMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/schema_metas")
@Validated
public class SchemaMetaController {

    private final SchemaMetaService schemaMetaService;

    @PostMapping("/sync_by_schema_source_id/{schemaSourceId}")
    public void sync(@PathVariable Integer schemaSourceId) {
        schemaMetaService.syncBySchemaSourceId(schemaSourceId);
    }

    @GetMapping("/get_by_schema_source_id/{schemaSourceId}")
    public SchemaMetaResponse getOneBySchemaSourceId(@PathVariable Integer schemaSourceId) {
        return schemaMetaService.getBySchemaSourceId(schemaSourceId).orElse(null);
    }

    /**
     * paramaters: id、version
     */
    @GetMapping("/{id}")
    public SchemaMetaResponse getOne(@PathVariable Integer id,
                                     @RequestParam(required = false) Long version) {
        return schemaMetaService.getOne(id, version).orElse(null);
    }

}
