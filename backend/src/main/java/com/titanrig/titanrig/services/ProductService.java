package com.titanrig.titanrig.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.titanrig.titanrig.dto.CategoryDTO;
import com.titanrig.titanrig.dto.ProductDTO;
import com.titanrig.titanrig.dto.ReviewDTO;
import com.titanrig.titanrig.entities.Category;
import com.titanrig.titanrig.entities.Product;
import com.titanrig.titanrig.entities.Review;
import com.titanrig.titanrig.exceptions.ResourceNotFoundException;
import com.titanrig.titanrig.repositories.ProductRepository;
import com.titanrig.titanrig.repositories.ReviewRepository;
import com.titanrig.titanrig.services.exceptions.DatabaseException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable){
        Page<Product> list = repository.findAll(pageable);
        return list.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
	    if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado.");
        }
        try {
            repository.deleteById(id);    		
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial.");
        }
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findByProductId(Long productId) {
        List<Review> list = reviewRepository.findByProductId(productId);
        return list.stream().map(ReviewDTO::new).toList();
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        
        entity.getCategories().clear();
        for (CategoryDTO catDto : dto.getCategories()) {
        	Category cat = new Category();
        	cat.setId(catDto.getId());
        	entity.getCategories().add(cat);
        }
    }
}
