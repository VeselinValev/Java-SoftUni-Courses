package app.services.impl;

import app.model.enums.Size;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;
import app.repositories.IngredientRepository;
import app.repositories.ShampooRepository;
import app.services.api.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private ShampooRepository shampooRepository;


    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findAllBySize(Size size) {
        return this.shampooRepository.getAllBySizeOrderById(size);
    }
}
