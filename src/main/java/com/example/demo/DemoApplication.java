package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com.example.demo.entity")
@MapperScan("com.example.demo.mapper")
//@RestController
public class DemoApplication {

//	@Autowired
//	TypeService typeService;

//	@RequestMapping("/1")
//	public String a(){
//		System.out.println(typeService.getTypeById(1));
//		return "1";
//	}

    public static void main(String[] args) {
        System.out.println();
        SpringApplication.run(DemoApplication.class,args);

    }
}