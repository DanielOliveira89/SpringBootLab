package com.oliveira.rest.restfulapi.beans;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@JsonIgnoreProperties({"address", "country"}) //Fields to be ignored at class level
public class User {
	
	private Integer id;
	
	@Size(min=2, message="Name min length must be 2")
	@JsonProperty("user_name")
	private String name;
	
	@Past(message="Birth Date must be a past date")
	@JsonProperty("birth_date")
	private LocalDate birthDate;
	
	private String gender;
	
	//@JsonIgnore //Ignoring the field individually
	private String address;
	private String country;
	
	public User() {
		
	}

	public User(Integer id, String name, LocalDate birthDate, String gender, String address, String country) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.address = address;
		this.country = country;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + ", address="
				+ address + ", country=" + country + "]";
	}

	
	

}
