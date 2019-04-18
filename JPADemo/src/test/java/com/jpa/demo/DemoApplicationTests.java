package com.jpa.demo;

import com.alibaba.fastjson.JSON;
import com.jpa.demo.entity.Student;
import com.jpa.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private StudentRepository studentRepository;


	@Test
	public void testFindAll() {
		Iterable<Student> students = studentRepository.findAll();
		System.out.println(JSON.toJSON(students));
	}

	@Test
	public void testSave() {
		Student student = new Student();

		student.setName("zek");
		student.setMark("add by jpa");
		student.setBirth(new Date());

		studentRepository.save(student);

		testFindAll();
	}

}
