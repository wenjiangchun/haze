package com.xinyuan.haze.core.entity;

import java.io.Serializable;

public interface BasePersistable<PK extends Serializable> extends Serializable {

	PK getId();

	boolean isNew();
}
