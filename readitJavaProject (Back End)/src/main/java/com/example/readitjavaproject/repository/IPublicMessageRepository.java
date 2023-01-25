package com.example.readitjavaproject.repository;

import com.example.readitjavaproject.domain.PublicMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPublicMessageRepository extends JpaRepository<PublicMessage, Integer> {
}
