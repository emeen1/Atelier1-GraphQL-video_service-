package ma.xproce.video_service.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.xproce.video_service.dao.entities.Creator;

public interface CreatorRepository extends JpaRepository<Creator, Long> {}

