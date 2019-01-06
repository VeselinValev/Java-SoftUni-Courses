package alararestaurant.service;

import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String exportCategoriesByCountOfItems() {
        StringBuilder sb = new StringBuilder();
        List<Category> categories = this.categoryRepository.findAll();
        categories.sort((x,y) -> {
            int compare = Integer.compare(y.getItems().size(), x.getItems().size());
            BigDecimal sumX = BigDecimal.ZERO;
            for (Item item: x.getItems()){
                sumX = sumX.add(item.getPrice());
            }
            BigDecimal sumY = BigDecimal.ZERO;
            for (Item item: y.getItems()){
                sumX = sumX.add(item.getPrice());
            }
            if (compare == 0){
                compare = sumY.compareTo(sumX);

            }
            return compare;
        });
        for (Category category: categories){
            sb.append(String.format("Category: %s%n", category.getName()));
            for (Item item: category.getItems()){
                sb.append(String.format("--- Item Name: %s%n--- Item Price: %s%n%n", item.getName(), item.getPrice()));
            }
        }
        return sb.toString().trim();
    }
}
