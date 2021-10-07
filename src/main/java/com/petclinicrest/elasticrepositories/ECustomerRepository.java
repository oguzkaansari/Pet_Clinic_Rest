package com.petclinicrest.elasticrepositories;

import com.petclinicrest.elasticmodels.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ECustomerRepository extends ElasticsearchRepository<Customer, Integer> {

    @Query("{\"bool\":{\"should\":[{\"match\":{\"cno\":\"?0\"}},{\"match\":{\"cname\":\"?0\"}}, {\"match\":{\"csurname\":\"?0\"}}],\"must_not\":[]}}")
    List<Customer> search(String key);

    @Query("{\"bool\":{\"should\":[{\"match\":{\"cno\":\"?0\"}},{\"match\":{\"cname\":\"?0\"}}, {\"match\":{\"csurname\":\"?0\"}}],\"must_not\":[]}}")
    Page<Customer> search(String key, Pageable pageable);
}
