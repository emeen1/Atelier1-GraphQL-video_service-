package ma.xproce.video_service.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CreatorDto {
    private String name;
    private String email;
}