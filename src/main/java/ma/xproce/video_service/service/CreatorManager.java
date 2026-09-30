package ma.xproce.video_service.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ma.xproce.video_service.dao.entities.Creator;
import ma.xproce.video_service.dao.repositories.CreatorRepository;
import ma.xproce.video_service.dto.CreatorDto;

@Service
public class CreatorManager {

    private final CreatorRepository creatorRepository;
    private final ModelMapper modelMapper;

    public CreatorManager(CreatorRepository creatorRepository, ModelMapper modelMapper) {
        this.creatorRepository = creatorRepository;
        this.modelMapper = modelMapper;
    }

    public Creator saveCreator(CreatorDto request) {
        Creator creator = modelMapper.map(request, Creator.class);
        return creatorRepository.save(creator);
    }

    public Creator findById(Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
    }

    public List<Creator> findAll() {
        return creatorRepository.findAll();
    }
}