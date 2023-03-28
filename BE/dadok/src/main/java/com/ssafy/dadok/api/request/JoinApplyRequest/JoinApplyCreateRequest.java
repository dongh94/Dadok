package com.ssafy.dadok.api.request.JoinApplyRequest;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Schema(description = "가입 신청 Dto")
@Getter
@NoArgsConstructor
public class JoinApplyCreateRequest {

    @Schema(description = "가입 요청 미팅 ID")
    Long meetingId;
    @Schema(description = "지원자 ID")
    String applicantId;

}
