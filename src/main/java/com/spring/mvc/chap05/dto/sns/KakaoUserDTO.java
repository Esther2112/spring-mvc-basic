package com.spring.mvc.chap05.dto.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserDTO {

    private long id;
    @JsonProperty("connected_at")
    private LocalDateTime connectedAt;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Setter @Getter @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoAccount {
        private String email;
        private Profile profile;

        @Setter @Getter @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public class Profile {

            private String nickname;
            @JsonProperty("profile_image_url")
            private String profileImageUrl;

        }
    }
}
