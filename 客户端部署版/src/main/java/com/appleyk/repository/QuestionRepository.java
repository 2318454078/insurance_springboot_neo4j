package com.appleyk.repository;

import com.appleyk.node.Insurance;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;


/**
 * 基于保险知识图谱的自问自答的查询接口
 * @author yukun24@126.com
 * @blob   http://blog.csdn.net/appleyk
 * @date   2018年5月10日-下午3:48:51
 */
public interface QuestionRepository extends Neo4jRepository<Insurance,Long> {

	/**
	 * 0 对应问题模板0 == in(保险) 投保年龄
	 *
	 * @param in_name 保险名称
	 * @return 返回投保年龄
	 */
	@Query("match(n:insurance) where n.name={in_name} return n.insurance_toubaonianling order by n.insurance_id desc limit 1")
	String getToubaonianling(@Param("in_name") String in_name);

	/**
	 * 1 对应问题模板1 == in(保险) 宽限期
	 *
	 * @param in_name 保险名称
	 * @return 返回保险的宽限期
	 */
	@Query("match(n:insurance) where n.name={in_name} return n.insurance_kuanxianqi order by n.insurance_id desc limit 1")
	String getKuanxianqi(@Param("in_name") String in_name);

	/**
	 * 2 对应问题模板2 == in(保险) 犹豫期
	 *
	 * @param in_name 保险名称
	 * @return 返回保险的犹豫期
	 */
	@Query("match(n:insurance) where n.name={in_name} return n.insurance_youyuqi order by n.insurance_id desc limit 1")
	String getYouyuqi(@Param("in_name") String in_name);

	/**
	 * 3 对应问题模板7 == in(保险) 受益人
	 *
	 * @param in_name 保险名称
	 * @return 返回保险的受益人
	 */
	@Query("match(n:insurance) where n.name={in_name} return n.insurance_shouyiren order by n.insurance_id desc limit 1")
	String getShouyiren(@Param("in_name") String in_name);


	/**
	 * 4 对应问题模板4 == in(保险) ic(保险公司)
	 *
	 * @param in_name 保险名称
	 * @return 返回保险的保险公司
	 */
	@Query("match(n:company)-[:insurance_to_company]-(m:insurance) where m.name ={in_name} return n.name,n.company_introduction order by n.company_id desc limit 1 ")
	List<Map<String, Object>> getInCompany(@Param("in_name") String in_name);
//修改的部分


	/**
	 * 5 对应问题模板5 == in(保险) 保费缴纳方式
	 *
	 * @param in_name 保险名称
	 * @return 返回保险的保费缴纳方式
	 */
	@Query("match(n:insurance) where n.name={in_name} return n.insurance_jiaofeifangshi order by n.insurance_id desc limit 1")
	String getJiaonafangshi(@Param("in_name") String in_name);

	/**
	 * 6 对应问题模板6 == in(保险) 保险期间
	 *
	 * @param in_name 保险名称
	 * @return 返回保险的保险期间
	 */
	@Query("match(m:insurance) where m.name ={in_name} return m.insurance_baoxianqijian order by m.insurance_id desc limit 1")
	String getBaoxianqixian(@Param("in_name") String in_name);


//修改的部分

	/**
	 * 7 对应问题模板8 == in(保险) 基本信息
	 *
	 * @param in_name 保险名称
	 * @return 返回保险的基本信息
	 */
	@Query("match(n:insurance) where n.name={in_name} return n.insurance_toubaonianling,n.insurance_kuanxianqi,n.insurance_youyuqi,n.insurance_zerentiaokuan,n.insurance_mianzetiaokuan,n.insurance_jiaofeifangshi,n.insurance_baoxianqijian,n.insurance_shouyiren order by n.insurance_id desc limit 1")
	List<Map<String, Object>> getAllInfo(@Param("in_name") String in_name);

	/**
	 * 8 对应问题模板3 == in(保险) 责任条款
	 *
	 * @param in_name 保险名称
	 * @return 返回保险的责任条款
	 */
	@Query("match(n:insurance) where n.name ={in_name} return n.insurance_zerentiaokuan,n.insurance_mianzetiaokuan order by n.insurance_id desc limit 1")
	List<Map<String, Object>> getZerentiaokuan(@Param("in_name") String in_name);
}