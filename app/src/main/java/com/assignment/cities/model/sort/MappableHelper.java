package com.assignment.cities.model.sort;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by Artur Kasprzak on 02.05.2018.
 */
public interface MappableHelper<T> {

	void initTree(List<T> cities);

	void setTreeMap(TreeMap<String, T> treeMap);

	List<T> getItems();

	List<T> getItems(String key);

	boolean isGenerated();
}
