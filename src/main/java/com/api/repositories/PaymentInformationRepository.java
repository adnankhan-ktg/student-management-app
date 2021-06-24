package com.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.models.PaymentInformation;

@Repository
public interface PaymentInformationRepository extends MongoRepository<PaymentInformation, String> {

}
