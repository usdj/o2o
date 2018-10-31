/**
 * 
 */
package com.usdj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.usdj.o2o.entity.UserAwardMap;

/**
 * @author gerrydeng
 *
 */
public interface UserAwardMapDao {
	/**
	 * @param userAwardMapCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserAwardMap> queryUserAwardMapList(@Param("userAwardCondition") UserAwardMap userAwardMapCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * @param userAwardMapCondition
	 * @return
	 */
	int queryUserAwardMapCount(@Param("userAwardCondition") UserAwardMap userAwardMapCondition);

	/**
	 * @param userAwardId
	 * @return
	 */
	UserAwardMap queryUserAwardMapById(long userAwardId);

	/**
	 * @param userAwardMap
	 * @return
	 */
	int insertUserAwardMap(UserAwardMap userAwardMap);

	/**
	 * @param userAwardMap
	 * @return
	 */
	int updateUserAwardMap(UserAwardMap userAwardMap);
}
