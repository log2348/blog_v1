package com.example.tencoding.blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tencoding.blog.auth.PrincipalDetail;
import com.example.tencoding.blog.dto.RequestFileDto;
import com.example.tencoding.blog.model.Image;
import com.example.tencoding.blog.service.StoryService;

@Controller
@RequestMapping("/story")
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@GetMapping("/home")
	public String storyHome(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<Image> imagePage = storyService.getImageList(pageable);
		model.addAttribute("imagePage", imagePage);
		return "/story/home";
	}
	
	@GetMapping("/upload")
	public String storyUpload() {
		return "/story/upload";
	}
	
	@PostMapping("/image/upload")
	public String storyImageUpload(RequestFileDto fileDto,
			@AuthenticationPrincipal PrincipalDetail principalDetail) {
		
		storyService.imageUpload(fileDto, principalDetail.getUser());
		System.out.println(fileDto.getFile().getContentType());
		System.out.println(fileDto.getFile().getName());
		System.out.println(fileDto.getFile().getOriginalFilename());
		System.out.println(fileDto.getFile().getSize());
		
		try {
			System.out.println(fileDto.getFile().getBytes().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * 
		 * 1byte -> 1000byte(KB) -> 1,000,000byte (MB) -> 1,000,000,000byte(GB) -> 1,000,000,000,000(TB)
		 * 000 KB, 000,000 MB, 000,000,000 GB, 000,000,000,000 TB
		 * 
		 * 1bit = 2진수 (0과 1)
		 * 1byte = 8bit
		 * 1KB = 2의 10승 byte -> 1024byte
		 * 
		 * 1byte -> 1024(KB) -> 1024KB
		 * 
		 * 1KB가 1024byte다
		 * 왜 컴퓨터는 1000배가 아니라 1024배인가
		 * 컴퓨터가 1000배보다는 1024배를 훨씬 빨리 계산하기 때문이다
		 * 좀 더 빠른 속도를 얻기 위해서 1024배로 약속한 것이다.
		 * 
		 * 컴퓨터는 2진수로 계산하는 것이 가장 편하고 빠르기 때문이다
		 * 그래서 숫자를 2진수 단위로 관리한다.
		 * 그래서 컴퓨터는 2, 4, 6, 8, 16, 32, 64, 128, 256, 512, 1024와 같이 2의 제곱으로 된 단위를 사용한다.
		 */
		
		return "redirect:/story/home";
	}

}
