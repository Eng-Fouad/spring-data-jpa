package org.springframework.data.jpa.repository.query;

import java.util.Set;

import org.springframework.data.domain.Sort;

interface QueryTransformer {

	Set<String> getProjectionAliases();

	/**
	 * Determine when an {@link org.springframework.data.domain.Sort.Order} parameter should alias (or not).
	 *
	 * @param order
	 * @return boolean whether or not to apply the primary FROM clause's alias
	 */
	default boolean shouldAlias(Sort.Order order) {

		// If the Sort contains a function
		if (order.getProperty().contains("(")) {
			return false;
		}

		if (getProjectionAliases().contains(order.getProperty())) {
			return false;
		}

		return true;
	}
}
