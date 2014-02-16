package com.byteworksinc.model;

import java.io.Serializable;

/**
 * Common API for any object that can be identified by a numeric ID.
 *
 * @author R.J. Lorimer
 */
public interface Identifiable extends Serializable {

	/**
	 * Returns the model objects id.
	 * @return The ID of the instance.
	 */
	Long getId();
}
