package com.petclinicrest.elasticmodels;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document(indexName = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field(type = FieldType.Auto)
    private Integer cid;

    @Field(type = FieldType.Text)
    private String cno;

    @Field(type = FieldType.Text)
    private String cname;

    @Field(type = FieldType.Text)
    private String csurname;

}
