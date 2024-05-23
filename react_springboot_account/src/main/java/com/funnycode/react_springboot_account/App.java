package com.funnycode.react_springboot_account;

import com.funnycode.react_springboot_account.entity.Account;
import com.funnycode.react_springboot_account.entity.Color;
import com.funnycode.react_springboot_account.entity.Role;
import com.funnycode.react_springboot_account.entity.Size;
import com.funnycode.react_springboot_account.repository.AccountRepository;
import com.funnycode.react_springboot_account.repository.ColorRepository;
import com.funnycode.react_springboot_account.repository.RoleRepository;
import com.funnycode.react_springboot_account.repository.SizeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class App {

	public static void main(String[] args) {

   ApplicationContext context = SpringApplication.run(App.class, args);
//
//
//
//		SizeRepository sizes = context.getBean(SizeRepository.class);
//
//	List<Size> size = Arrays.asList(Size.builder().name("S").build(),
//		Size.builder().name("M").build(),
//		Size.builder().name("L").build(),
//		Size.builder().name("XL").build(),
//		Size.builder().name("XXL").build());
//
//
//		sizes.saveAll(size);
//
//		ColorRepository colors = context.getBean(ColorRepository.class);
//
//	colors.save(Color.builder().name("Blue").build());
//
//		List<Color> color = Arrays.asList(Color.builder().name("Black").build(),
//		Color.builder().name("White").build(),
//		Color.builder().name("Red").build(),
//		Color.builder().name("Yellow").build(),
//		Color.builder().name("Brown").build(),
//		Color.builder().name("Gray").build(),
//	Color.builder().name("Pink").build(),
//	Color.builder().name("Salmon").build(),
// 		Color.builder().name("Purple").build(),
// 		Color.builder().name("Green").build(),
// Color.builder().name("Orange").build());
//		colors.saveAll(color);

//		RoleRepository roleRepository = context.getBean(RoleRepository.class);
//
//		List<Role> roles = Arrays.asList(Role.builder()
//				.name("admin").build(),
//				Role.builder().name("manager").build(),
//				Role.builder().name("user").build());
//
//		roleRepository.saveAll(roles);
	}

}
