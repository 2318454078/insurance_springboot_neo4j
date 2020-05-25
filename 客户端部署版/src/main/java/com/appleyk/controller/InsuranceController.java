package com.appleyk.controller;

import com.appleyk.node.Insurance;
import com.appleyk.repository.InsuranceRepository;
import com.appleyk.result.ResponseMessage;
import com.appleyk.result.ResponseResult;
import com.appleyk.result.ResultData;
import com.appleyk.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/appleyk/insurance") //restful风格的api接口
public class InsuranceController {
   
	@Autowired
	InsuranceService insuranceService;

	InsuranceRepository insuranceRepository;

	/**
	 * 公司id，返还不同投保年龄范围的保险数量(返回值map：age(0-55，55-60,60--)，num)
	 * @param company_id
	 * @return
	 */
//	@RequestMapping("/getComInNumByAge")
//	//public Map<String,Integer> getComInNumByAge(@RequestParam(value="company_id") int company_id){
//	public String getComInNumByAge(@RequestParam(value="company_id") int company_id){
//		return insuracneDao.getComInNumByAge(company_id);
//	}

	@RequestMapping("/getComInNumByAge")
	Map<String,Integer> getComInNumByAge(@RequestParam(value="company_id") int company_id) throws Exception {
	//public String getComInNumByAge(@RequestParam(value="company_id") int company_id) throws Exception {
		return insuranceService.getComInNumByAge(company_id);
	}
	@RequestMapping("/getComInsurNumByTypes")
	Map<String,Integer> getComInsurNumByTypes(@RequestParam(value="company_id") int company_id) throws Exception {
		//public String getComInNumByAge(@RequestParam(value="company_id") int company_id) throws Exception {
		return insuranceService.getComInsurNumByTypes(company_id);
	}
	@RequestMapping("/getInNumByAge")
	Map<String,Integer> getInNumByAge() throws Exception {
		//public String getComInNumByAge(@RequestParam(value="company_id") int company_id) throws Exception {
		return insuranceService.getInNumByAge();
	}
	@RequestMapping("/getInsurNumByTypes")
	Map<String,Integer> getInsurNumByTypes() throws Exception {
		//public String getComInNumByAge(@RequestParam(value="company_id") int company_id) throws Exception {
		return insuranceService.getInNumByTypes();
	}

	/**
	 * 根据保险名查询保险实体
	 * @param in_name
	 * @return
	 */
    @RequestMapping("/get")  
    public List<Insurance> getInsurances(@RequestParam(value="in_name") String in_name){
    	return insuranceRepository.findById(in_name);
    }  
      
    /**
     * 创建一个保险节点
     * @param insurance
     * @return
     */
    @PostMapping("/save")  
    public ResponseResult saveInsurance(@RequestBody Insurance insurance){
    	insuranceRepository.save(insurance);
    	return new ResponseResult(ResponseMessage.OK);
    }  
	
    
    @RequestMapping("/query")  
    public ResponseResult queryInNames(){
    	List<String> InNames = insuranceRepository.getInsuranceNames();
    	ResultData<String> result = new ResultData<String>(ResponseMessage.OK, InNames);
    	return new ResponseResult(result);
    } 
}
