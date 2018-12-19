package app.services.api;

import app.models.dtos.binding.SupplierDto;
import app.models.dtos.views.SaleWIthDiscount;

import java.util.List;

public interface SaleService {
    void seedSales();

    List<SaleWIthDiscount> getAllSalesWithDiscount();
}
