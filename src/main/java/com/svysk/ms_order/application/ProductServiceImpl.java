package com.svysk.ms_order.application;

import com.svysk.ms_order.domain.Product;
import com.svysk.ms_order.domain.repository.ProductRepository;
import com.svysk.ms_order.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
            return repository.findById(id);
    }
}
