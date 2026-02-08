package Leafme.leafme.service;
import Leafme.leafme.model.Item;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemService {

    private final List<Item> items = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void loadDefaultItems() {
        items.add(new Item(1L, "Tomato", "Tomato is a juicy and versatile vegetable widely used in Indian and global cuisines. Rich in vitamins A and C, it adds tangy flavor and bright color to dishes. Tomatoes are essential for curries, salads, sauces, and soups, making everyday meals healthier and tastier.", 30.0));
        items.add(new Item(2L, "Brinjal", "Brinjal, also called eggplant, is a soft and absorbent vegetable used in many traditional recipes. It is rich in fiber and nutrients. Brinjal easily absorbs spices and flavors, making it ideal for curries, bharta, fries, and regional Indian dishes.", 20.0));
        items.add(new Item(3L, "Mirchi", "Mirchi, or chili, is a spicy vegetable that adds heat and flavor to food. It is rich in vitamin C and antioxidants. Used fresh, dried, or powdered, mirchi enhances taste in curries, snacks, and sauces, making dishes bold and aromati", 25.0));
        items.add(new Item(4L, "Bhindi", "Bhindi, also known as okra, is a nutritious green vegetable popular in Indian cooking. It is rich in fiber, vitamins, and antioxidants. Bhindi is commonly used in stir-fries, curries, and gravies, offering a mild taste and soft texture when cooked properly.", 40.0));
    }

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
