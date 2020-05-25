package com.appleyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageIndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/index")
	public String shouye() {
		return "index";
	}

	@RequestMapping("/bnrs")
	public String bnrs() {
		return "bnrs";
	}

	@RequestMapping("/data_visualization")
	public String data_visualization() {
		return "data_visualization";
	}

	@RequestMapping("/zong")
	public String zong() {
		return "zong";
	}

	@RequestMapping("/policy_analysis")
	public String policy_analysis() {
		return "policy_analysis";
	}

	@RequestMapping("/intelligent_assistant")
	public String intelligent_assistant() {
		return "intelligent_assistant";
	}

	@RequestMapping("/jieshi")
	public String jieshi() {
		return "jieshi";
	}

	@RequestMapping("/download")
	public String download() {
		return "download";
	}

	@RequestMapping("/policy_download")
	public String policy_download() {
		return "policy_download";
	}

}
