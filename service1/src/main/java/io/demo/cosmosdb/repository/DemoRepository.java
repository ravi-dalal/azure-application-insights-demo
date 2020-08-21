package io.demo.cosmosdb.repository;

import org.springframework.stereotype.Repository;

import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

import io.demo.cosmosdb.dto.DemoContainer;

@Repository
public interface DemoRepository extends CosmosRepository<DemoContainer, String> {
	
}
