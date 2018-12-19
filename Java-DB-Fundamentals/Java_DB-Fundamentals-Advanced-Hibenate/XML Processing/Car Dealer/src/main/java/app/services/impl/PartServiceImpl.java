package app.services.impl;

import app.models.dtos.binding.PartDto;
import app.models.entities.Part;
import app.models.entities.Supplier;
import app.repositories.PartRepository;
import app.services.api.PartService;
import app.services.api.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PartServiceImpl implements PartService {
    private PartRepository partRepository;
    private SupplierService supplierService;
    private ModelMapper modelMapper;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierService supplierService) {
        this.partRepository = partRepository;
        this.supplierService = supplierService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void saveAll(List<PartDto> parts) {
        List<Supplier> suppliers = this.supplierService.getAllSuppliers();
        Random random = new Random();
        for (PartDto partDto : parts) {
            Part part = modelMapper.map(partDto, Part.class);
            part.setSupplier(suppliers.get(random.nextInt(suppliers.size())));
            this.partRepository.saveAndFlush(part);
        }
    }

    @Override
    public List<Part> getAllParts() {

        return this.partRepository.findAll();
    }
}
