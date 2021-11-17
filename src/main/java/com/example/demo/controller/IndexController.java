package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import src.Status;

@RestController
public class IndexController {
	
	@Autowired
	private Status status;

}
