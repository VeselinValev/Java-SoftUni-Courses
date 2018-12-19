package app.services.impl;

import app.models.dtos.binding.SupplierDto;
import app.models.dtos.views.LocalSupplier;
import app.models.entities.Supplier;
import app.repositories.SupplierRepository;
import app.services.api.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void saveAll(List<SupplierDto> suppliers) {
        for (SupplierDto supplierDto: suppliers){
            Supplier supplier = modelMapper.map(supplierDto, Supplier.class);
            this.supplierRepository.saveAndFlush(supplier);
        }
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return this.supplierRepository.findAll();
    }

    @Override
    public List<LocalSupplier> getLocalSuppliers() {
        List<Supplier> suppliers = this.supplierRepository.getLocalSuppliers();
        List<LocalSupplier> suppliersDto = new ArrayList<>();
        for (Supplier supplier: suppliers){
            LocalSupplier localSupplier = new LocalSupplier();
            localSupplier.setId(supplier.getId());
            localSupplier.setName(supplier.getName());
            localSupplier.setPartsCount(supplier.getParts().size());
            suppliersDto.add(localSupplier);
        }
        return suppliersDto;
    }
}
