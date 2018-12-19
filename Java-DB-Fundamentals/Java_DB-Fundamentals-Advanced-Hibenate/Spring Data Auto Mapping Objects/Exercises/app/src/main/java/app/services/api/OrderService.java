package app.services.api;


import app.models.dto.binding.AddGameDto;
import app.models.entities.Game;
import app.models.entities.Order;

import java.text.ParseException;

public interface OrderService {
    void saveOrder(Order order);
}
