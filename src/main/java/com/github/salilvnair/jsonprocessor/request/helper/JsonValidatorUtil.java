package com.github.salilvnair.jsonprocessor.request.helper;

import java.util.Collections;
import java.util.List;
import com.github.salilvnair.jsonprocessor.request.context.JsonValidatorContext;
import com.github.salilvnair.jsonprocessor.request.context.ValidationMessage;
import com.github.salilvnair.jsonprocessor.request.core.JsonRequest;


public class JsonValidatorUtil {
	public static List<ValidationMessage> validate(JsonValidatorContext validatorContext) {
		JsonRequest jsonRequest = validatorContext.getRootRequest();
		List<?> jsonRequestList = validatorContext.getRootList();
		if(jsonRequest==null && (jsonRequestList==null||jsonRequestList.isEmpty())) {
			return Collections.emptyList();
		}
		JsonProcessorUtil jsonProcessorUtil = new JsonProcessorUtil(validatorContext);
		if(jsonRequestList==null || jsonRequestList.isEmpty()) {
			return jsonProcessorUtil.validate(jsonRequest);
		}
		else {
			return jsonProcessorUtil.validate(jsonRequestList);
		}
	}
	
}
