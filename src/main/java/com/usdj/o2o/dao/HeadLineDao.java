/**
 * 
 */
package com.usdj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.usdj.o2o.entity.HeadLine;

/**
 * @author gerrydeng
 *
 */
public interface HeadLineDao {
	/**
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);

	/**
	 * @param lineId
	 * @return
	 */
	HeadLine queryHeadLineById(long lineId);
	
	/**
	 * @param lineIdList
	 * @return
	 */
	List<HeadLine> queryHeadLineByIds(List<Long> lineIdList);
	
	/**
	 * @param headLine
	 * @return
	 */
	int insertHeadLine(HeadLine headLine);
	
	/**
	 * @param headLine
	 * @return
	 */
	int updateHeadLine(HeadLine headLine);
	
	/**
	 * @param headLineId
	 * @return
	 */
	int deleteHeadLine(long headLineId);
	
	/**
	 * @param lineIdList
	 * @return
	 */
	int batchDeleteHeadLine(List<Long> lineIdList);
	
}
