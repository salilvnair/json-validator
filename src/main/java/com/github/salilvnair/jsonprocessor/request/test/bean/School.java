package com.github.salilvnair.jsonprocessor.request.test.bean;

import java.util.ArrayList;
import java.util.List;

import com.github.salilvnair.jsonprocessor.request.annotation.JsonKeyValidation;
import com.github.salilvnair.jsonprocessor.request.annotation.UserDefinedMessage;
import com.github.salilvnair.jsonprocessor.request.constant.JsonKeyValidatorConstant;
import com.github.salilvnair.jsonprocessor.request.core.JsonRequest;
import com.github.salilvnair.jsonprocessor.request.helper.DateParsingUtil.DateFormat;
import com.github.salilvnair.jsonprocessor.request.type.MessageType;
import com.github.salilvnair.jsonprocessor.request.type.Mode;
import com.github.salilvnair.jsonprocessor.request.type.ValidatorType;
@JsonKeyValidation(id="School", customTaskValidatorBeanName ="SchoolCustomTask")
public class School implements JsonRequest {
	@JsonKeyValidation(required=true)
	private long id;
	@JsonKeyValidation(
			conditional=true,
			required = true,
			condition="validateAlumini",
			userDefinedMessages = {
					@UserDefinedMessage(
							validatorType=ValidatorType.CONDITIONAL,
							message="Non Hogward schools are not allowed",
							messageType=MessageType.ERROR,
							messageId = "100"
					),
					@UserDefinedMessage(
							validatorType=ValidatorType.REQUIRED,
							message="It is a required field",
							messageType = MessageType.ERROR,
							messageId = "101"
					)
			},
			mode=Mode.SYNC
	)
	private String name;
	@JsonKeyValidation(required=true, mode=Mode.SYNC)
	private HeadMaster headMaster;
	@JsonKeyValidation(
		required=true,
		minItems=4,		
		userDefinedMessages = {
				@UserDefinedMessage(
						validatorType=ValidatorType.REQUIRED,
						message="Students are mandatory in a school",
						messageType=MessageType.ERROR
				),
				@UserDefinedMessage(
						validatorType=ValidatorType.MINITEMS,
						message="Minimum 4 students should be there at "+JsonKeyValidatorConstant.PATH_PLACEHOLDER,
						messageType = MessageType.WARNING
				)
		}
	)
	private ArrayList<Student> students;
	@JsonKeyValidation(allowEmpty=true,numeric=true,message="numeric value is expected")
	private String totalStudents;
	@JsonKeyValidation(dateString=true, dateFormat=DateFormat.SLASH_MM_DD_YYYY,convertIntoDateTimeString=true)
	private String year;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	public HeadMaster getHeadMaster() {
		return headMaster;
	}
	public void setHeadMaster(HeadMaster headMaster) {
		this.headMaster = headMaster;
	}
	public String getTotalStudents() {
		return totalStudents;
	}
	public void setTotalStudents(String totalStudents) {
		this.totalStudents = totalStudents;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
