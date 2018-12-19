package app.services.api;

import app.model.enums.Size;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;

import java.util.List;

public interface ShampooService {

    List<Shampoo> findAllBySize(Size size);
}
