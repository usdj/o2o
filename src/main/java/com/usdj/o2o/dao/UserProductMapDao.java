/**
 * 
 */
package com.usdj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.usdj.o2o.entity.UserProductMap;

/**
 * @author gerrydeng
 *
 */
public interface UserProductMapDao {
	/**
	 * @param userProductCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserProductMap> queryUserProductMapList(@Param("userProductCondition") UserProductMap userProductCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * @param userProductMapCondition
	 * @return
	 */
	int queryUserProductMapCount(@Param("userProductCondition") UserProductMap userProductMapCondition);

	/**
	 * @param userProductMap
	 * @return
	 */
	int insertUserProductMap(UserProductMap userProductMap);
}
