package com.criteria.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.model.IdPropertyIdentifierAccessor;

import com.criteria.entities.Product;
import com.criteria.model.SearchingCriteria;
import com.criteria.operation.SearchingOperation;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductSpecification implements Specification<Product> {

	private static final long serialVersionUID = 1L;
	private List<SearchingCriteria> list;

	public ProductSpecification() {
		this.list = new ArrayList<>();
	}

	public void add(SearchingCriteria criteria) {
		list.add(criteria);
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		List<Predicate> predicates = new ArrayList<>();

		for (SearchingCriteria criteria : list) {
			if (criteria.getOperation().equals(SearchingOperation.EQUAL)) {
				predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue().toString()));
			}
			if (criteria.getOperation().equals(SearchingOperation.NOT_EQUAL)) {
				predicates.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue().toString()));
			}
			if (criteria.getOperation().equals(SearchingOperation.GREATER_THAN)) {
				predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
			}
			if (criteria.getOperation().equals(SearchingOperation.LESS_THAN)) {
				predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
			}
			if (criteria.getOperation().equals(SearchingOperation.GREATER_THAN_EQUAL)) {
				predicates
						.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
			}
			if (criteria.getOperation().equals(SearchingOperation.LESS_THAN_EQUAL)) {
				predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
			}
			if (criteria.getOperation().equals(SearchingOperation.IN)) {
				predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValues()));
			}
			if (criteria.getOperation().equals(SearchingOperation.NOT_IN)) {
				predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValues()));
			}
			if (criteria.getOperation().equals(SearchingOperation.MATCH)) {
				predicates.add(builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
			}
			if (criteria.getOperation().equals(SearchingOperation.MATCH_START)) {
				predicates.add(builder.like(root.get(criteria.getKey()), "%" + criteria.getValue()));
			}
			if (criteria.getOperation().equals(SearchingOperation.MATCH_END)) {
				predicates.add(builder.like(root.get(criteria.getKey()), criteria.getValue() + "%"));
			}if (criteria.getOperation().equals(SearchingOperation.BITWEEN)) {
				predicates.add(builder.between(root.get(criteria.getKey()), criteria.getValues().get(0).toString(), criteria.getValues().get(1).toString()));
			}
		}
		return builder.and(predicates.toArray(new Predicate[0]));
	}

}
