package br.com.achaweb.services;

import br.com.achaweb.dto.CategoryDTO;
import br.com.achaweb.entities.Category;
import br.com.achaweb.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();

        List<CategoryDTO> listDto = new ArrayList<>();
        for (Category category : list) {
            listDto.add(new CategoryDTO(category));
        }
        return listDto;
    }

}
