package com.sphinx.common;

import java.io.Serializable;

public interface Identifiable<T extends Serializable> extends HasIdentity<T> {

	void setId(T id);
	
}
