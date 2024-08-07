package br.com.achaweb.services;

import br.com.achaweb.dto.CategoryDTO;
import br.com.achaweb.dto.ProductDTO;
import br.com.achaweb.entities.Category;
import br.com.achaweb.entities.Product;
import br.com.achaweb.repositories.CategoryRepository;
import br.com.achaweb.repositories.ProductRepository;
import br.com.achaweb.services.exceptions.DatabaseException;
import br.com.achaweb.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Long categoryId, String name, PageRequest pageRequest) {

        List<Category> categories = (categoryId == 0) ? null : Arrays.asList(categoryRepository.getOne(categoryId));
        Page<Product> page = repository.find(categories, name, pageRequest);

        repository.findProductsWithCategories(page.getContent());
        return page.map(x -> new ProductDTO(x, x.getCategories()));
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findByRegionOrCityPaged(Long regionId, Long cityId, PageRequest pageRequest) {

        List<Product> products = repository.findByRegionOrCity(cityId);
        List<ProductDTO> productsDto = products.stream().map(x -> new ProductDTO(x, x.getCategories())).collect(Collectors.toList());
        Page<ProductDTO> productDtoPage = new PageImpl<>(productsDto, pageRequest, productsDto.size());
        return productDtoPage;
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
