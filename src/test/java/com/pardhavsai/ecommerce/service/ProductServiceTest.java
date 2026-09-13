package com.pardhavsai.ecommerce.service;

import com.pardhavsai.ecommerce.entity.Product;
import com.pardhavsai.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock private ProductRepository repository;
    @InjectMocks private ProductService service;

    @Test
    void findByIdReturnsProductWhenItExists(){
        Product product=new Product("Keyboard",1499,10);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(product));

        Product result=service.findById(1L);

        assertEquals("Keyboard", result.getName());
        assertEquals(1499, result.getPrice());
    }
}
