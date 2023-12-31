package com.github.salilvnair.jsonprocessor.request.test.bean;

import java.util.List;

import com.github.salilvnair.jsonprocessor.request.annotation.JsonKeyValidation;
import com.github.salilvnair.jsonprocessor.request.annotation.ValidValues;
import com.github.salilvnair.jsonprocessor.request.core.JsonRequest;
import com.github.salilvnair.jsonprocessor.request.helper.DateParsingUtil.DateFormat;
import com.github.salilvnair.jsonprocessor.request.helper.DateParsingUtil.DateType;
import com.github.salilvnair.jsonprocessor.request.type.Mode;

@JsonKeyValidation(id="Student")
public class Student implements JsonRequest {
	@JsonKeyValidation(required=true)
	private String name;
	@JsonKeyValidation(alphaNumeric=true)
	@ValidValues({"X","Y"})
	private String section;
	@JsonKeyValidation(dateString=true,dateFormat=DateFormat.SLASH_MM_DD_YYYY)
	private String dateOfBirth;
	@JsonKeyValidation(required=true, mode=Mode.SYNC)
	private String gender;
	@JsonKeyValidation(required=true)
	private List<Subject> subjects;
    	@JsonKeyValidation(
    		required=true, 
    		date=true, 
    		dateFormat=DateFormat.SLASH_MM_DD_YYYY,
    		dateGte=DateType.Value.TODAY,
    		message="Date of Admission should be greater than or equals to current date!"
    	)
    	private String dateOfAdmission;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateOfAdmission() {
		return dateOfAdmission;
	}
	public void setDateOfAdmission(String dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}
	
}
