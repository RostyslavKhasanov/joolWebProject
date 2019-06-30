package ua.jool.service;

import ua.jool.domain.DeveloperDTO;

import java.util.List;

public interface DeveloperService {

    void create(DeveloperDTO developerDTO);

    List<DeveloperDTO>  findAll();

    void deleteDeveloperById(Long id);

}
