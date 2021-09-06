package com.sphinx.common;

import java.io.Serializable;

public interface HasIdentity<T extends Serializable> {

	T getId();
	
}
