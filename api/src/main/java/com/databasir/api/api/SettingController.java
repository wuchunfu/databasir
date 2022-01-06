package com.databasir.api.api;

import com.databasir.common.JsonData;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/settings/database_types")
@Validated
public class SettingController {

    public JsonData<List<String>> listDatabaseMetas() {
        return null;
    }

    public JsonData<Void> deleteDatabaseType() {
        return null;
    }

}
