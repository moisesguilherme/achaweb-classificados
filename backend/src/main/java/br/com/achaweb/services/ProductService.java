package br.com.achaweb.services;

import br.com.achaweb.dto.CategoryDTO;
import br.com.achaweb.dto.ProductDTO;
import br.com.achaweb.entities.Category;
import br.com.achaweb.entities.Product;
import br.com.achaweb.repositories.CategoryRepository;
import br.com.achaweb.repositories.ProductRepository;
import br.com.achaweb.services.exceptions.DatabaseException;
import br.com.achaweb.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        Page<Product> list = repository.findAll(pageRequest);

        return list.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());
    }


    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }


    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try{
            Product entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }


    public void delete(Long id) {
        if(!repository.existsById(id))
            throw new ResourceNotFoundException("id not found");

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());

        entity.getCategories().clear();
        for(CategoryDTO catDto : dto.getCategories()) {
            Category category = categoryRepository.getOne(catDto.getId());
            entity.getCategories().add(category);
        }
    }


}
