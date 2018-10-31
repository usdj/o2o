/**
 * 
 */
package com.usdj.o2o.dao;

import com.usdj.o2o.entity.WechatAuth;

/**
 * @author gerrydeng
 *
 */
public interface WechatAuthDao {
	/**
	 * @param openId
	 * @return
	 */
	WechatAuth queryWechatInfoByOpenId(String openId);

	/**
	 * @param wechatAuth
	 * @return
	 */
	int insertWechatAuth(WechatAuth wechatAuth);

	/**
	 * @param wechatAuthId
	 * @return
	 */
	int deleteWechatAuth(Long wechatAuthId);
}
