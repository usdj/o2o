/**
 * 
 */
package com.usdj.o2o.cache;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usdj.o2o.BaseTest;
import com.usdj.o2o.cache.JedisUtil;
import com.usdj.o2o.dao.WechatAuthDao;
import com.usdj.o2o.entity.WechatAuth;

/**
 * @author gerrydeng
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JedisUtilTest extends BaseTest {
	
	@Autowired
	private JedisUtil.Strings jedisStrings;


	@Autowired
	private WechatAuthDao wechatAuthDao;

	@Test
	public void testSetListAndGetList() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId("ddfaz");
		List<WechatAuth> wechatAuthList = new ArrayList<WechatAuth>();
		wechatAuthList.add(wechatAuth);
		String jsonString = mapper.writeValueAsString(wechatAuthList);
		System.out.println(jsonString);
		jedisStrings.set("shopCategoryList", jsonString);
		String scListString = jedisStrings.get("shopCategoryList");
		System.out.println(scListString);
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, WechatAuth.class);
		wechatAuthList = mapper.readValue(scListString, javaType);
		for (WechatAuth wa : wechatAuthList) {
			System.out.println(wa.getPersonInfo().getName());
		}
	}
}
