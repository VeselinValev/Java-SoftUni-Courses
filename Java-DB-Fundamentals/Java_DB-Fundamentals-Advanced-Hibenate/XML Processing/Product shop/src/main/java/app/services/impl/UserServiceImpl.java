package app.services.impl;

import app.models.dtos.binding.UserDto;
import app.models.dtos.views.*;
import app.models.entities.Product;
import app.models.entities.User;
import app.repositories.UserRepository;
import app.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void saveAll(List<UserDto> users) {
        for (UserDto userDto: users){
            User user = new User();
            this.modelMapper.map(userDto, user);
            this.userRepository.save(user);
        }
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public List<UserNamesAndProducts> getUserWhoSoldProducts() {
        List<User> users = this.userRepository.getAllWhoSoldProducts();
        List<UserNamesAndProducts> dtoUsers = new ArrayList<>();
        for (User user: users){
            List<ProductNamePriceBuyerNames> dtoProducts = new ArrayList<>();
            for (Product product: user.getSoldProducts()){
                if (product.getBuyer() == null){
                    continue;
                }
                ProductNamePriceBuyerNames dtoProduct = new ProductNamePriceBuyerNames(
                        product.getName(), product.getPrice(), product.getBuyer().getFirstName(), product.getBuyer().getLastName());
                dtoProducts.add(dtoProduct);
            }
            UserNamesAndProducts dtoUser = new UserNamesAndProducts(user.getFirstName(), user.getLastName(), dtoProducts);
            dtoUsers.add(dtoUser);
        }
        return dtoUsers;
    }

    @Override
    public List<UserNamesWithAgeAndProducts> getUserWhoSoldProductsWithAge() {
        List<User> users = this.userRepository.getAllUsersWhoSoldProducts();
        List<UserNamesWithAgeAndProducts> dtoUsers = new ArrayList<>();
        for (User user: users){
            List<ProductNamePrice> dtoProducts = new ArrayList<>();
            for (Product product: user.getSoldProducts()){
                if (product.getBuyer() == null){
                    continue;
                }
                ProductNamePrice dtoProduct = new ProductNamePrice(product.getName(), product.getPrice());
                dtoProducts.add(dtoProduct);
            }
            SoldProducts soldProducts = new SoldProducts(dtoProducts);
            UserNamesWithAgeAndProducts dtoUser = new UserNamesWithAgeAndProducts(user.getFirstName(), user.getLastName(), user.getAge(), soldProducts);
            dtoUsers.add(dtoUser);
        }
        return dtoUsers;
    }
}
