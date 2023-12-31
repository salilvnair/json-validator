package com.github.salilvnair.jsonprocessor.request.validator.main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.salilvnair.jsonprocessor.request.annotation.JsonKeyValidation;
import com.github.salilvnair.jsonprocessor.request.context.JsonValidatorContext;
import com.github.salilvnair.jsonprocessor.request.context.ValidationMessage;
import com.github.salilvnair.jsonprocessor.request.helper.ReflectionUtil;
import com.github.salilvnair.jsonprocessor.request.type.ValidatorType;
import com.github.salilvnair.jsonprocessor.request.validator.core.BaseJsonRequestValidator;
import com.github.salilvnair.jsonprocessor.request.validator.core.JsonKeyValidator;

public class PatternValidator extends BaseJsonRequestValidator implements JsonKeyValidator {
	
	private Field field;

	public PatternValidator(Field field) {
		this.field = field;
	}

	@Override
	public List<ValidationMessage> validate(Object currentInstance, String path,
			JsonValidatorContext jsonValidatorContext) {
		List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
		Object fieldValue = ReflectionUtil.getFieldValue(currentInstance, field.getName());
		boolean fieldHasPattern = false;
		JsonKeyValidation jsonFieldKeyValidator = field.getAnnotation(JsonKeyValidation.class);
		if(!EMPTY_STRING.equals(jsonFieldKeyValidator.pattern())) {
			fieldHasPattern = true;
		}
		boolean isPatternValid = true;
		if(fieldHasPattern) {
			if(jsonFieldKeyValidator.allowNull() && fieldValue==null) {
				return Collections.unmodifiableList(errors);
			}
			else if(jsonFieldKeyValidator.allowEmpty() && (fieldValue==null || EMPTY_STRING.equals(fieldValue))) {
				return Collections.unmodifiableList(errors);
			}
			else if((!jsonFieldKeyValidator.allowNull() && fieldValue==null)  
					|| (!jsonFieldKeyValidator.allowEmpty() && (fieldValue==null || EMPTY_STRING.equals(fieldValue)))){
				isPatternValid = false;
			}
			else {
				isPatternValid = PatternValidator.isValid(jsonFieldKeyValidator.pattern(), fieldValue+"");
			}
		}
		if(!isPatternValid) {
			errors = prepareFieldViolationMessage(currentInstance,jsonValidatorContext,ValidatorType.PATTERN,field,errors,path,"pattern error");	
		}
		return Collections.unmodifiableList(errors);
	}
	
	public static boolean isValid(String patternString, String inputString) { 
		Pattern pattern = Pattern.compile(patternString);
		Matcher m = pattern.matcher(inputString);
		return m.matches();
	}
}
