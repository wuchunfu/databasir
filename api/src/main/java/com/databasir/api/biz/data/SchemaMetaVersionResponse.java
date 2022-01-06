package com.databasir.api.biz.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchemaMetaVersionResponse {

    private Long version;

    private LocalDateTime createAt;

}
