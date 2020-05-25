package com.appleyk.controller;

import com.appleyk.node.Type;
import com.appleyk.repository.TypeRepository;
import com.appleyk.result.ResponseMessage;
import com.appleyk.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/appleyk/type")
public class TypeController {
	
	@Autowired
	TypeRepository typeRepository;
	

	/**
	 * 根据类型名查询Type实体
	 * @param name
	 * @return
	 */
    @RequestMapping("/get")  
    public List<Type> getTypes(@RequestParam(value="type_name") String name){
    	return typeRepository.findById(name);
    }  
      
    /**
     * 创建一个保险类型节点
     * @param type
     * @return
     */
    @PostMapping("/save")  
    public ResponseResult saveType(@RequestBody Type type){
    	typeRepository.save(type);
    	return new ResponseResult(ResponseMessage.OK);
    }  
}
