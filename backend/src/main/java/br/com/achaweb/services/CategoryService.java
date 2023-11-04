package br.com.achaweb.services;

import br.com.achaweb.dto.CategoryDTO;
import br.com.achaweb.entities.Category;
import br.com.achaweb.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();

        List<CategoryDTO> listDto = list.stream()
                .map(x -> new CategoryDTO(x)).collect(Collectors.toList());

        return listDto;
    }

}
