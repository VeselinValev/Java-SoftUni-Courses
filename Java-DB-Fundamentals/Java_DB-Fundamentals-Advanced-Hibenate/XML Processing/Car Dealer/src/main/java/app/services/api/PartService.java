package app.services.api;

import app.models.dtos.binding.PartDto;
import app.models.entities.Part;

import java.util.List;

public interface PartService {
    void saveAll(List<PartDto> parts);

    List<Part> getAllParts();
}
