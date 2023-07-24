package com.oliveira.rest.restfulapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.rest.restfulapi.beans.v1.PersonV1;
import com.oliveira.rest.restfulapi.beans.v2.Name;
import com.oliveira.rest.restfulapi.beans.v2.PersonV2;

@RestController
public class VersioningPersonController {
	
	@GetMapping("/v1/person")
	public PersonV1 getPerson() {
		
		return new PersonV1("John Doe");
	}
	
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		
		return new PersonV2(new Name("John", "Doe"));
	}
	
	
	@GetMapping(path="/person", params="version=1")
	public PersonV1 getPersonReqParams() {
		
		return new PersonV1("John Doe");
	}

	
	@GetMapping(path="/person", params="version=2")
	public PersonV2 getPersonV2ReqParams() {
		
		return new PersonV2(new Name("John", "Doe"));
	}
	
	
	@GetMapping(path="/person", headers = "X-API-VERSION=1")
	public PersonV1 getPersonHeaderV1() {
		
		return new PersonV1("John Doe");
	}
	
	@GetMapping(path="/person", headers = "X-API-VERSION=2")
	public PersonV2 getPersonHeaderV2() {
		
		return new PersonV2(new Name("John", "Doe"));
	}
	
	
	@GetMapping(path="/person", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonAcceptHeaderV1() {
		
		return new PersonV1("John Doe");
	}
	
	@GetMapping(path="/person", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonAcceptHeaderV2() {
		
		return new PersonV2(new Name("John", "Doe"));
	}

}
