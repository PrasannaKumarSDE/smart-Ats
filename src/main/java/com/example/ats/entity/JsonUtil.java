package com.example.ats.entity;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static List<String> readList(String json){
        if(json==null || json.isBlank()) {
			return Collections.emptyList();
		}
        try { return MAPPER.readValue(json, new TypeReference<List<String>>(){}); }
        catch (Exception e){ return Collections.emptyList(); }
    }
    public static String writeList(List<String> list){
        try { return MAPPER.writeValueAsString(list); }
        catch (Exception e){ return "[]"; }
    }
}
