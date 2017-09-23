package at.jku.csi.dao;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import at.jku.csi.cdi.Service;
import at.jku.csi.rest.model.Filter;

@Service
public class DaoQueryService implements Serializable {

	public <S> CriteriaQuery<Long> buildCountQuery(EntityManager entityManager, Class<S> responseClass) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		criteriaQuery.select(builder.count(criteriaQuery.from(responseClass)));
		return criteriaQuery;
	}

	public <S> CriteriaQuery<S> buildFindAllQuery(EntityManager entityManager, Class<S> responseClass) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<S> criteriaQuery = builder.createQuery(responseClass);
		return criteriaQuery.select(criteriaQuery.from(responseClass));
	}

	public <S> CriteriaQuery<S> extendFilters(EntityManager entityManager, CriteriaQuery<S> query,
			List<Filter> filters) {
		Root<?> root = new ArrayList<>(query.getRoots()).get(0);
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		return query.where(builder.and(buildPredicates(filters, root, builder)));
	}

	private Predicate[] buildPredicates(List<Filter> filters, Root<?> root, CriteriaBuilder builder) {
		List<Predicate> predicates = filters.stream().map(filter -> {
			Path<Object> fieldPath = root.get(filter.getField());
			switch (filter.getType()) {
			case LIKE:
				return builder.like(fieldPath.as(String.class), convertToLikeString(filter.getValue()));
			case LESS_THAN:
				return builder.lessThan(fieldPath.as(Integer.class), convertToInteger(filter.getValue()));
			case GREATER_THAN:
				return builder.greaterThan(fieldPath.as(Integer.class), convertToInteger(filter.getValue()));
			default:
				return builder.equal(fieldPath, filter.getValue());
			}
		}).collect(toList());
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private String convertToLikeString(Object object) {
		return "%" + object.toString() + "%";
	}

	private int convertToInteger(Object object) {
		return Integer.parseInt(object.toString());
	}

}
