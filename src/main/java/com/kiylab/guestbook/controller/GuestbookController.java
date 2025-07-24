package com.kiylab.guestbook.controller;

import com.kiylab.guestbook.dto.GuestbookDTO;
import com.kiylab.guestbook.dto.PageRequestDTO;
import com.kiylab.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("guestbook")
@RequiredArgsConstructor
public class GuestbookController {
  private final GuestbookService service;

  @GetMapping
  public String index() {
    return "redirect:/guestbook/list";
  }

  @GetMapping("list")
  public void list(@ModelAttribute("requestDto") PageRequestDTO dto, Model model) {
    model.addAttribute("dto", service.getList(dto));
  }

  @GetMapping("register")
  public void register(){}

  @PostMapping("register")
  public String register(GuestbookDTO dto, RedirectAttributes rttr) {
    rttr.addFlashAttribute("msg", service.write(dto));
    return "redirect:/guestbook/list";
  }


  //권한 차이 발생
  //read: 비회원도 조회
  //modify: 인증, 본인글 또는 관리자
  @GetMapping("read")
  public void read(@ModelAttribute("pageDto") PageRequestDTO dto, Model model, Long gno) {
    model.addAttribute("dto", service.read(gno));
//    model.addAttribute("pageDto", dto);
  }

  @GetMapping("modify")
  public void modify(@ModelAttribute("pageDto") PageRequestDTO dto, Model model, Long gno) {
    model.addAttribute("dto", service.read(gno));
  }

  @PostMapping("modify")
  public String modify(@ModelAttribute("pageDto") PageRequestDTO dto, GuestbookDTO guestbookDTO, RedirectAttributes rttr) {
    service.modify(guestbookDTO);
    rttr.addAttribute("gno", guestbookDTO.getGno());
    rttr.addAttribute("page", dto.getPage());
    rttr.addAttribute("size", dto.getSize());
    return  "redirect:/guestbook/read";
  }

  @PostMapping("remove")
  public  String remove(PageRequestDTO dto, Long gno, RedirectAttributes rttr) {
    service.remove(gno);

    rttr.addAttribute("page", dto.getPage());
    rttr.addAttribute("size", dto.getSize());
    return "redirect:/guestbook/list";
  }

}