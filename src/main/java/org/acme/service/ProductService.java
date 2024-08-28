package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ProductDTO;
import org.acme.entity.ProductEntity;
import org.acme.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    public List<ProductDTO> findAll() {
        List<ProductDTO> customers = new ArrayList<>();
        productRepository.findAll().stream().forEach(item -> {
            customers.add(mapCustomerEntitytoDTO(item));
        });
        return customers;
    }

    public void create(ProductDTO customerDTO) {
        productRepository.persist(mapEntitytoCustomerDTO(customerDTO));
    }

    public void change(Long id, ProductDTO customerDTO) {
        ProductEntity customer = productRepository.findById(id);
        customer.setName(customerDTO.getName());
        productRepository.persist(customer);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapCustomerEntitytoDTO(ProductEntity customer) {
        return ProductDTO.builder().name(customer.getName()).description(customer.getDescription()).
                category(customer.getCategory()).build();
    }

    private ProductEntity mapEntitytoCustomerDTO(ProductDTO customer) {
        return ProductEntity.builder().name(customer.getName()).description(customer.getDescription()).
                category(customer.getCategory()).build();
    }
}
