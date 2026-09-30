package ma.xproce.video_service.web;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import ma.xproce.video_service.dao.entities.Creator;
import ma.xproce.video_service.dao.entities.Video;
import ma.xproce.video_service.dto.*;
import ma.xproce.video_service.service.CreatorManager;
import ma.xproce.video_service.service.VideoManager;

import java.util.Random;
import java.util.stream.Stream;

import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import reactor.core.publisher.Flux;

@Controller
public class VideoGraphQlController {

    private final CreatorManager creatorManager;
    private final VideoManager videoManager;

    public VideoGraphQlController(CreatorManager creatorManager, VideoManager videoManager) {
        this.creatorManager = creatorManager;
        this.videoManager = videoManager;
    }

    @QueryMapping
    public List<Video> videoList() {
        return videoManager.findAll();
    }

    @QueryMapping
    public Video videoById(@Argument Long id) {
        return videoManager.findById(id);
    }

    @QueryMapping
    public List<Creator> creatorList() {
        return creatorManager.findAll();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorManager.findById(id);
    }

    @MutationMapping
    public Creator saveCreator(@Argument CreatorDto creator) {
        return creatorManager.saveCreator(creator);
    }

    @MutationMapping
    public Video saveVideo(@Argument VideoDto video) {
        return videoManager.saveVideo(video);
    }
    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
            Stream.generate(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Random random = new Random();
                CreatorDto creatorDto = CreatorDto.builder()
                                        .name("x" + random.nextInt())
                                        .email("x@gmail.com")
                                        .build();
            Creator creator = creatorManager.saveCreator(creatorDto);
            Video video = videoManager.findById(1L);
            video.setCreator(creator);
            videoManager.updateVideo(video);
            return video;
            }));
    }
}