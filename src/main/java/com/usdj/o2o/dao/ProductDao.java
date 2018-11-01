/**
 * 
 */
package com.usdj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.usdj.o2o.entity.Product;

/**
 * @author gerrydeng
 *
 */
public interface ProductDao {
	/**
	 * @param productCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);

	/**
	 * @param productCondition
	 * @return
	 */
	int queryProductCount(@Param("productCondition") Product productCondition);

	/**
	 * @param productId
	 * @return
	 */
	Product queryProductByProductId(long productId);

	/**
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);

	/**
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);

	/**
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(long productCategoryId);

	/**
	 * @param productId
	 * @param shopId
	 * @return
	 */
	int deleteProduct(@Param("productId") long productId, @Param("shopId") long shopId);
}
