package com.simonton.mybatis.mybatisdemo;

import com.alibaba.fastjson.JSON;
import com.simonton.mybatis.mybatisdemo.entity.Student;
import com.simonton.mybatis.mybatisdemo.entity.StudentExample;
import com.simonton.mybatis.mybatisdemo.mapper.StudentMapper;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {MybatisdemoApplication.class})
@ActiveProfiles("dev")
public class MybatisdemoApplicationTests extends AbstractTestNGSpringContextTests{

	@Autowired
	private StudentMapper studentMapper;

	@Test
	private void testSelect() {

		StudentExample studentExample = new StudentExample();
		studentExample.createCriteria().andNameIsNotNull();
		List<Student> list = studentMapper.selectByExample(studentExample);
		System.out.print(JSON.toJSON(list));
	}

	@Test
	public void testGenerator() throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		//指向逆向工程配置文件
		File configFile = new File("generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);
	}

}
