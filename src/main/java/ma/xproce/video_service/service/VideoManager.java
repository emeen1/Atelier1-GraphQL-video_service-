package ma.xproce.video_service.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ma.xproce.video_service.dao.entities.Creator;
import ma.xproce.video_service.dao.entities.Video;
import ma.xproce.video_service.dao.repositories.VideoRepository;
import ma.xproce.video_service.dto.VideoDto;

@Service
public class VideoManager {

    private final VideoRepository videoRepository;
    private final CreatorManager creatorManager;
    private final ModelMapper modelMapper;

    public VideoManager(VideoRepository videoRepository, CreatorManager creatorManager, ModelMapper modelMapper) {
        this.videoRepository = videoRepository;
        this.creatorManager = creatorManager;
        this.modelMapper = modelMapper;
    }

    public Video saveVideo(VideoDto request) {
        Video video = modelMapper.map(request, Video.class);
        if (request.getCreator() != null) {
            Creator creator = creatorManager.saveCreator(request.getCreator());
            video.setCreator(creator);
        }
        return videoRepository.save(video);
    }

    public Video updateVideo(Video video) {
        return videoRepository.save(video);
    }

    public Video findById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Video %s not found", id)));
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }
}