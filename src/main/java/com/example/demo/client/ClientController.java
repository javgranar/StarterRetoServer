package com.example.demo.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
@RestControllerEndpoint(id = "reto")
public class ClientController {
	
	private List<String> listado = new ArrayList<>();
	
	private Counter counter;
	
	public ClientController(MeterRegistry registry) {
		this.counter = Counter.builder("invocaciones.cambios").description("Invocaciones totales").register(registry);
	}
	
	@ReadOperation
	public List<String> estados(){
		return listado;
	}
	
	@WriteOperation
	public void writeOperation(@Selector String newStatus) {
		counter.increment();
		listado.add(newStatus);
	}
	
	@DeleteOperation
	public void deleteOperation(@Selector String deleteStatus) {
		listado.remove(deleteStatus);
	}
	
}