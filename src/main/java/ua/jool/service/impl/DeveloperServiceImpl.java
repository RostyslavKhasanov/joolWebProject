package ua.jool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.jool.domain.DeveloperDTO;
import ua.jool.entity.DeveloperEntity;
import ua.jool.exception.ResourceNotFoundException;
import ua.jool.repository.DeveloperRepository;
import ua.jool.service.DeveloperService;
import ua.jool.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void create(DeveloperDTO developerDTO) {
        DeveloperEntity developerEntity = modelMapper.map(developerDTO, DeveloperEntity.class);
        developerRepository.save(developerEntity);
    }

    @Override
    public List<DeveloperDTO> findAll() {
        List<DeveloperEntity> developerEntities = developerRepository.findAll();
        List<DeveloperDTO> developerDTOS = modelMapper.mapAll(developerEntities, DeveloperDTO.class);
        return developerDTOS;
    }

    @Override
    public void deleteDeveloperById(Long id) {
        DeveloperEntity developerEntity = developerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Could not delete product with id[" + id + "]. Not found")
        );
        developerRepository.deleteById(id);
    }
}
