package com.ssafy.dadok.api.request.MeetingRequest;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "모임 생성 Dto")
@Getter
@Setter
@NoArgsConstructor
public class MeetingCreateRequest {
    @Schema(description = "모임 이름")
    private String name;
    @Schema(description = "모임 테마")
    private String theme;
    @Schema(description = "이미지 경로")
    private String img;
    @Schema(description = "관심 분야")
    private String interest;
    @Schema(description = "모임 정원")
    private int people;
    @Schema(description = "모임 소개")
    private String introduce;
    @Schema(description = "모임 진행 방식")
    private String format ;
    @Schema(description = "모임 추천 대상")
    private String targetUser;
    @Schema(description = "모임 시작 날짜")
    private String startDay;
    @Schema(description = "모임 일정")
    private String schedule;
    @Schema(description = "모임 주최자 ID")
    private String host;
}
