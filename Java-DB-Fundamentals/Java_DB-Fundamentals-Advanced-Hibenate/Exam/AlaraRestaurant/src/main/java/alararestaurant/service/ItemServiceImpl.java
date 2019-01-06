package alararestaurant.service;

import alararestaurant.domain.dtos.ItemDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {

    private final String FILE_PATH = "C:\\Users\\Ves\\Desktop\\AlaraRestaurant\\src\\main\\resources\\files\\items.json";

    private ItemRepository itemRepository;
    private ModelMapper modelMapper;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private Gson gson;
    private CategoryRepository categoryRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil, Gson gson, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() != 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return this.fileUtil.readFile(this.FILE_PATH);
    }

    @Override
    public String importItems(String items) {
        StringBuilder sb = new StringBuilder();
        ItemDto[] itemDtos = this.gson.fromJson(items, ItemDto[].class);

        for (ItemDto itemDto: itemDtos){

            if (!this.validationUtil.isValid(itemDto)){
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            Item item = this.itemRepository.getByName(itemDto.getName());

            if (item != null){
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            item = this.modelMapper.map(itemDto, Item.class);

            Category category = this.categoryRepository.getByName(itemDto.getCategory());

            if (category == null){
                category = new Category();
                category.setName(itemDto.getCategory());
                this.categoryRepository.saveAndFlush(category);
            }

            item.setCategory(category);
            this.itemRepository.saveAndFlush(item);

            sb.append(String.format("Record %s successfully imported.%n", item.getName()));
        }
        return sb.toString().trim();
    }
}
