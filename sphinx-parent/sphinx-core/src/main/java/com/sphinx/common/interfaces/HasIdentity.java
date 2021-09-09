package com.sphinx.common.interfaces;

import java.io.Serializable;

public interface HasIdentity<T extends Serializable> {

	T getId();
	
}
