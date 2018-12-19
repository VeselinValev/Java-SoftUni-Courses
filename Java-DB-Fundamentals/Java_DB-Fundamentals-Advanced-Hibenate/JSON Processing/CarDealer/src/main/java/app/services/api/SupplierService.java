package app.services.api;

import app.models.dtos.binding.SupplierDto;
import app.models.dtos.views.LocalSupplier;
import app.models.entities.Supplier;

import java.util.List;

public interface SupplierService {
    void saveAll(SupplierDto[] suppliers);

    List<Supplier> getAllSuppliers();

    List<LocalSupplier> getLocalSuppliers();
}
