package io.demo.cosmosdb.dto;

import org.springframework.data.annotation.Id;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.PartitionKey;

@Document(collection = "demoContainer")
public class DemoContainer {

	@Id
	@PartitionKey
	private String id;
	private String value;
	
	public DemoContainer () {
		
	}
	
	public DemoContainer(String id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
   @Override
    public String toString() {
        return String.format("%s %s", id, value);
    }
}
