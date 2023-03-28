package com.ssafy.dadok.api.request.JoinAcceptRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "가입 수락/거절 Dto")
@Getter
@NoArgsConstructor
public class JoinAcceptRequest {

    @Schema(description = "지원자 ID")
    private String applicantId;
    @Schema(description = "모임 HOST ID")
    private String hostId;
}
