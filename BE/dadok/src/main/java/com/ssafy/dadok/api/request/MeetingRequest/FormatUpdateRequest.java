package com.ssafy.dadok.api.request.MeetingRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "모임 진행방식 수정 Dto")
@Getter
@NoArgsConstructor
public class FormatUpdateRequest {
    @Schema(description = "진행 방식 수정 내용")
    private String format;
}
