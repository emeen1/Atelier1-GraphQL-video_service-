package ma.xproce.video_service.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor

public class VideoDto {
    private String name;
    private String url;
    private String description;
    private String datePublication;
    private CreatorDto creator;
}