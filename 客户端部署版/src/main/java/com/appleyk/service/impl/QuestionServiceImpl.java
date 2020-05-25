package com.appleyk.service.impl;

import com.appleyk.node.Insurance;
import com.appleyk.process.ModelProcess;
import com.appleyk.repository.QuestionRepository;
import com.appleyk.service.QuestionService;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Primary
public class QuestionServiceImpl implements QuestionService {

	@Value("${rootDirPath}")
	private String rootDictPath;

	@Value("${HanLP.CustomDictionary.path.InsuranceDict}")
	private String InsuranceDictPath;

	@Value("${HanLP.CustomDictionary.path.TypeDict}")
	private String TypeDictPath;

	@Value("${HanLP.CustomDictionary.path.CompanyDict}")
	private String CompanyDictPath;

	//    @Value("${HanLP.CustomDictionary.path.TermDict}")
//    private String TermDictPath;
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public void showDictPath() {
		System.out.println("HanLP分词字典及自定义问题模板根目录：" + rootDictPath);
		System.out.println("用户自定义扩展词库【电影】：" + InsuranceDictPath);
	}


	@Override
	public String answer(String question) throws Exception {

		ModelProcess queryProcess = new ModelProcess(rootDictPath);

		/**
		 * 加载自定义的电影字典 == 设置词性 nm 0
		 */

		loadMovieDict(InsuranceDictPath);

		/**
		 * 加载自定义的类型字典 == 设置词性 ng 0
		 */
		loadGenreDict(TypeDictPath);

		/**
		 * 加载自定义的评分字典 == 设置词性 x 0
		 */
		loadScoreDict(CompanyDictPath);

		/**
		 * 加载自定义的评分字典 == 设置词性 x 0
		 */
//        loadTermDict(TermDictPath);

		ArrayList<String> reStrings = queryProcess.analyQuery(question);
		int modelIndex = Integer.valueOf(reStrings.get(0));
		String answer = null;
		String in_name = "";
		String name = "";
		String type = "";
		Double score = 0.0;
		/**
		 * 匹配问题模板
		 */
		switch (modelIndex) {
			case 0:
				/**
				 * in 投保年龄 == 保险投保年龄
				 */
				in_name = reStrings.get(1);
				String age = questionRepository.getToubaonianling(in_name);
				if (age != null) {
					answer = age;
					answer.replace(" ","");
				} else {
					answer = null;
				}
				break;
			case 1:
				/**
				 * in 宽限期 == 保险宽限期
				 */
				in_name = reStrings.get(1);
				String Date = questionRepository.getKuanxianqi(in_name);
				if (Date != null) {
					answer = Date;
					answer.replace(" ","");
				} else {
					answer = null;
				}
				break;
			case 2:
				/**
				 * in 犹豫期 == 保险犹豫期
				 */
				in_name = reStrings.get(1);
				System.out.println(in_name);
				String YDate = questionRepository.getYouyuqi(in_name);
				if (YDate != null) {
					answer = YDate;
					answer.replace(" ","");
				} else {
					answer = null;
				}
				break;
			case 3:
				/**
				 * in 受益人 == 保险受益人
				 */
				in_name = reStrings.get(1);
				String Person = questionRepository.getShouyiren(in_name);
				if(Person != null){
					answer = Person;
					answer.replace(" ","");
				}else {
					answer = null;
				}
				break;
			case 4:
				/**
				 * in ic == 保险保险公司
				 */
				in_name = reStrings.get(1);
				String comname = null;
				String comIn = null;
				List<Map<String,Object>> company = questionRepository.getInCompany(in_name);
				for(Map m:company){
					Iterator<String> iter = m.keySet().iterator();
					while (iter.hasNext()) {
						String key = iter.next();
						if (key.equals("n.name")) {
							comname = (String) m.get("n.name");
							System.out.println(comname);
						} else if (key.equals("n.company_introduction")) {
							comIn = (String) m.get("n.company_introduction");
							System.out.println(comIn);
						} else {
							System.out.println(key);
						}
					}
				}
				if (company != null) {
					answer = comname+comIn;
					answer.replace(" ","");
				} else {
					answer = null;
				}
				break;
			case 5:
				/**
				 * in 保费缴纳方式 == 保险保费缴纳方式
				 */
				in_name = reStrings.get(1);
				String Way = questionRepository.getJiaonafangshi(in_name);
				if(Way != null){
					answer = Way;
					answer.replace(" ","");
				}else {
					answer = null;
				}
				break;
			case 6:
				/**
				 * in 保险期间 == 保险保险期间
				 */
				in_name = reStrings.get(1);
				String InDat = questionRepository.getBaoxianqixian(in_name);
				if(InDat != null){
					answer = InDat;
					answer.replace(" ","");
				}else {
					answer = null;
				}
				break;
			case 7:
				/**
				 * in 基本信息 == 保险基本信息
				 */
				in_name = reStrings.get(1);
				Insurance insurance =  new Insurance();
				List<Map<String,Object>> info = questionRepository.getAllInfo(in_name);
				for(Map m:info){
					Iterator<String> iter = m.keySet().iterator();
					while (iter.hasNext()) {
						String key = iter.next();
						if (key.equals("n.insurance_toubaonianling")) {
							insurance.setIn_toubaonianling((String) m.get("n.insurance_toubaonianling"));
						} else if(key.equals("n.insurance_kuanxianqi")){
							insurance.setIn_kuanxianqi((String) m.get("n.insurance_kuanxianqi"));
						} else if(key.equals("n.insurance_youyuqi")){
							insurance.setIn_youyuqi((String) m.get("n.insurance_youyuqi"));
						} else if(key.equals("n.insurance_zerentiaokuan")){
							insurance.setIn_zerentiaokuan((String) m.get("n.insurance_zerentiaokuan"));
						}else if(key.equals("n.insurance_mianzetiaokuan")){
							insurance.setIn_mianzetiaokuan((String) m.get("n.insurance_mianzetiaokuan"));
						}else if(key.equals("n.insurance_jiaofeifangshi")){
							insurance.setIn_jiaofeifangshi((String) m.get("n.insurance_jiaofeifangshi"));
						}else if(key.equals("n.insurance_baoxianqijian")){
							insurance.setIn_baoxianqijian((String) m.get("n.insurance_baoxianqijian"));
						}else if(key.equals("n.insurance_shouyiren")){
							insurance.setIn_shouyiren((String) m.get("n.insurance_shouyiren"));
						}
						else {
							System.out.println(key);
						}
					}
				}

				if (insurance != null) {
					answer = insurance.getIn_baoxianqijian()+"\n"+insurance.getIn_jiaofeifangshi()+"\n"+insurance.getIn_kuanxianqi()+"\n"+insurance.getIn_mianzetiaokuan()+"\n"+insurance.getIn_shouyiren()+"\n"+insurance.getIn_toubaonianling()+"\n"+insurance.getIn_youyuqi()+"\n"+insurance.getIn_zerentiaokuan();
					answer.replace(" ","");
				} else {
					answer = null;
				}
				break;
			case 8:
				/**
				 * in 责任条款 == 保险责任条款
				 */
				in_name = reStrings.get(1);
				System.out.println(in_name);
				String Mianze = null;
				String Zeren = null;

				List<Map<String,Object>> q = questionRepository.getZerentiaokuan(in_name);
				System.out.println("查询结果："+ questionRepository.getZerentiaokuan(in_name));
				for(Map m:q) {
					Iterator<String> iter = m.keySet().iterator();
					while (iter.hasNext()) {
						String key = iter.next();
						if (key.equals("n.insurance_zerentiaokuan")) {
							Zeren = (String) m.get("n.insurance_zerentiaokuan");
							System.out.println(Zeren);
						} else if(key.equals("n.insurance_mianzetiaokuan")){
							Mianze = (String) m.get("n.insurance_mianzetiaokuan");
							System.out.println(Mianze);
						}else{
							System.out.println(key);
						}
					}
				}
				if(Zeren != null || Mianze != null)
				{
					answer = Zeren+Mianze;
					answer.replace(" ","");
				} else {
					answer = null;
				}
				break;
//		case 8:
//				/**
//				 * 犹豫期是什么意思 泛化问题
//				 */
//			in_name = reStrings.get(1);
//			String generalization = Getgeneralization(in_name);
//			if(generalization != null){
//				answer = generalization;
//			}else {
//				answer = null;
//			}
//				break;

			default:
				break;
		}

		System.out.println(answer);
		if (answer != null && !answer.equals("") && !answer.equals("\\N")) {
			return answer;
		} else {
			return "sorry,我没有找到你要的答案";
		}
	}

	/**
	 * 泛化问题答案
	 *
	 */
//	public String Getgeneralization(String in_name)
//	{
//         String ans=null;
//			switch (in_name){
//				case "犹豫期":
//				ans= "“犹豫期”是指投保人在收到保险合同后10天（银行保险渠道为15天）内，如不同意保险合同内容，可将合同退还保险人并申请撤销。在此期间，保险人同意投保人的申请，撤销合同并退还已收全部保费。该10天（银行保险渠道为15天）即通常所说的“犹豫期”。";
//				break;
//
//				case "宽限期":
//				ans= "保险期限亦称“保险期间”，保险单所提供的保障期限。即从保险责任开始到终止的时间。";
//				break;
//
//				default:
//					break;
//
//			}
//			return ans;
//	}
	/**
	 * 加载自定义电影字典
	 *
	 * @param path
	 */
	public void loadMovieDict(String path) {

		File file = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			addCustomDictionary(br, 0);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * 加载自定义电影类别字典
	 *
	 * @param path
	 */
	public void loadGenreDict(String path) {

		File file = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			addCustomDictionary(br, 1);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 加载自定义电影评分字典
	 *
	 * @param path
	 */
	public void loadScoreDict(String path) {

		File file = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			addCustomDictionary(br, 2);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 加载自定义保险名词字典
	 *
	 * @param path
	 */
	public void loadTermDict(String path) {

		File file = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			addCustomDictionary(br, 0);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * 添加自定义分词及其词性，注意数字0表示频率，不能没有
	 *
	 * @param br
	 * @param type
	 */
	public void addCustomDictionary(BufferedReader br, int type) {

		String word;
		try {
			while ((word = br.readLine()) != null) {
				switch (type) {
					/**
					 * 设置电影名词词性 == nm 0
					 */
					case 0:
						CustomDictionary.add(word, "nm 0");
						break;
					/**
					 * 设置电影类型名词 词性 == ng 0
					 */
					case 1:
						CustomDictionary.add(word, "ng 0");
						break;
					/**
					 * 设置电影评分数词 词性 == x 0
					 */
					case 2:
						CustomDictionary.add(word, "x 0");
						break;
					default:
						break;
				}
			}
			br.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
