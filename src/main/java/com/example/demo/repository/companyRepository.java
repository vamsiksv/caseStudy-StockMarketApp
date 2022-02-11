package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.stockModel.Company;

@Repository
@Transactional
public interface companyRepository extends JpaRepository<Company, String> {

}
