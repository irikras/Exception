package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();

    private Product first = new Book(1, "B1", 350, "A1", 500, 2021);
    private Product second = new Book(2, "B2", 800, "A2", 350, 2020);
    private Product third = new TShirt(3, "S1", 1200, "red", "M");
    private Product fourth = new TShirt(4, "S2", 850, "black", "L");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
    }

    @Test
    public void shouldRemovedByExitId() {
        repository.removeById(1);

        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{second, third, fourth};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNegativeIdException() {
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(8);
        });
    }
}
