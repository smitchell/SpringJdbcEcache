package com.byteworksinc.model;

/**
 * Abstract common API for model classes (classes representing records in a table.
 *
 * @author R.J. Lorimer
 */
public abstract class Model implements Identifiable {
	private Long id;

	/**
	 * Returns the  ID of the instance.
	 * @return instance Id.
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * Sets the instance Id.
	 * @param id - ID of the instance.
	 */
	public final void setId(final Long id) {
		this.id = id;
	}

    /**
     * Returns object hash.
     * @return hash of instance.
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        if (id == null) {
            return super.hashCode();
        }
        return id.hashCode();
    }
	
}
