package app.runners;

import app.model.enums.Size;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;
import app.services.api.IngredientService;
import app.services.api.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private ShampooService shampooService;

    private IngredientService ingredientService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Shampoo> shampoos = this.shampooService.findAllBySize(Size.MEDIUM);

        for (Shampoo shampoo: shampoos){
            System.out.println(shampoo.getLabel());
        }
    }
}
