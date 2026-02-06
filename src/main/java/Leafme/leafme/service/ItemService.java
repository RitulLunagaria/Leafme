package Leafme.leafme.service;
import Leafme.leafme.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemService {

    private final List<Item> items = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Item addItem(Item item) {
        item.setId(idGenerator.getAndIncrement());
        items.add(item);
        return item;
    }

    public Optional<Item> getItemById(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    public List<Item> getAllItems() {
        return items;
    }

    // UPDATE
    public Optional<Item> updateItem(Long id, Item updatedItem) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .map(existingItem -> {
                    existingItem.setName(updatedItem.getName());
                    existingItem.setDescription(updatedItem.getDescription());
                    existingItem.setPrice(updatedItem.getPrice());
                    return existingItem;
                });
    }

    // DELETE
    public boolean deleteItem(Long id) {
        return items.removeIf(item -> item.getId().equals(id));
    }
}
