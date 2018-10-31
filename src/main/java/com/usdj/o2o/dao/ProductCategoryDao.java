/**
 * 
 */
package com.usdj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.usdj.o2o.entity.ProductCategory;

/**
 * @author gerrydeng
 *
 */
public interface ProductCategoryDao {

	/**
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryByShopId(long shopId);

	/**
	 * @param productCategorieList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategorieList);

	/**
	 * @param productCategoryId
	 * @param shopId
	 * @return effectedNum
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);

}
