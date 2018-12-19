package app.services.impl;

import app.models.dtos.views.CarAndParts;
import app.models.dtos.views.SaleWIthDiscount;
import app.models.entities.Car;
import app.models.entities.Customer;
import app.models.entities.Part;
import app.models.entities.Sale;
import app.repositories.SaleRepository;
import app.services.api.CarService;
import app.services.api.CustomerService;
import app.services.api.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    private SaleRepository saleRepository;
    private CarService carService;
    private CustomerService customerService;
    private ModelMapper modelMapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void seedSales() {
        List<Customer> customers = this.customerService.getAllCustomers();
        List<Car> cars = this.carService.getAllCars();
        Double[] discounts = {0.0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};
        Random random = new Random();
        for (int i = 0; i < 150; i++){
            Sale sale = new Sale();
            Car car = cars.get(random.nextInt(cars.size()));
            sale.setCar(car);
            Customer customer = customers.get(random.nextInt(customers.size()));
            sale.setCustomer(customer);
            Double discount = discounts[random.nextInt(7)];
            if (customer.getYoungDriver()){
                discount += 0.05;
            }
            sale.setDiscount(BigDecimal.valueOf(discount));
            this.saleRepository.saveAndFlush(sale);
        }
    }

    @Override
    public List<SaleWIthDiscount> getAllSalesWithDiscount() {
        List<Sale> sales = this.saleRepository.findAll();
        List<SaleWIthDiscount> salesDto = new ArrayList<>();
        for (Sale sale: sales){
            SaleWIthDiscount saleWIthDiscount = new SaleWIthDiscount();
            CarAndParts carAndParts = this.modelMapper.map(sale.getCar(), CarAndParts.class);
            saleWIthDiscount.setCar(carAndParts);
            saleWIthDiscount.setCustomerName(sale.getCustomer().getName());
            saleWIthDiscount.setDiscount(sale.getDiscount());
            BigDecimal carPrice = BigDecimal.ZERO;
            for (Part part: sale.getCar().getParts()){
                carPrice = carPrice.add(part.getPrice());
            }
            saleWIthDiscount.setPrice(carPrice);
            BigDecimal discountedPrice = carPrice.multiply(BigDecimal.valueOf(1).subtract(sale.getDiscount()));
            saleWIthDiscount.setPriceWithDiscount(discountedPrice);
            salesDto.add(saleWIthDiscount);
        }
        return salesDto;
    }
}
