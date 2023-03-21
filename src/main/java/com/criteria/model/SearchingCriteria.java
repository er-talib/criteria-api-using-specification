package com.criteria.model;

import java.util.List;

import com.criteria.operation.SearchingOperation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchingCriteria {
	
	private String key ;
	private String value ;
	private SearchingOperation operation ;
	private List<Object> values ;

}
