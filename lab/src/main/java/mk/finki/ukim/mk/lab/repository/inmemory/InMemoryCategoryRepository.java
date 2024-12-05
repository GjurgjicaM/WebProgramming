package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.bootsrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCategoryRepository {
    public List<Category> findAll() {
        return DataHolder.categories.stream().toList();
    }
    public Optional<Category> findById(Long id){
        return DataHolder.categories.stream().filter(category -> category.getId().equals(id)).findFirst();
    }
}
