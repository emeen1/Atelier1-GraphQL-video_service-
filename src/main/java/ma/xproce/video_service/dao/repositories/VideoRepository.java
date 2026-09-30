package ma.xproce.video_service.dao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ma.xproce.video_service.dao.entities.Video;
public interface VideoRepository extends JpaRepository<Video, Long> {}
