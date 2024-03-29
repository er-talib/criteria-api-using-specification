package com.criteria.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.criteria.entities.Address;

public interface AddressRepository extends JpaRepository<Address,Serializable> , JpaSpecificationExecutor<Address>{

}
