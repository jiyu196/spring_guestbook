package com.kiylab.guestbook.service;

import com.kiylab.guestbook.dto.GuestbookDTO;
import com.kiylab.guestbook.dto.PageRequestDTO;
import com.kiylab.guestbook.dto.PageResponseDTO;
import com.kiylab.guestbook.entity.Guestbook;

import java.util.List;

public interface GuestbookService { // 구현클래스를 알아서 찾으러감.
  Long write(GuestbookDTO guestbookDTO);  //팀플때 구현이 안된상태여도 인터페이스에 정의만 해놔도 호출할 수 있음. 인터페이스 정의는 일찍 끝나야함.
  GuestbookDTO read(Long gno);  // GuestbookDTO를 가져와야함. 선언문에는 entity가 보이는게 아님
  List<GuestbookDTO> readAll();

  PageResponseDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO);

  void modify(GuestbookDTO guestbookDTO); // int로한 이유는 개수를 알아야해서..  ?
  void remove(Long gno);  // 여기까지가 인터페이스에 정의한 후에 내 서비스 만들러 가면됨.

  default Guestbook toEntity(GuestbookDTO guestbookDTO) {
   return Guestbook.builder()
           .gno(guestbookDTO.getGno())
           .title(guestbookDTO.getTitle())
           .content(guestbookDTO.getContent())
           .writer(guestbookDTO.getWriter())
           .build();
  }

  default GuestbookDTO toDto(Guestbook guestbook) {
    return  guestbook == null ? null : GuestbookDTO.builder()
            .gno(guestbook.getGno())
            .title(guestbook.getTitle())
            .content(guestbook.getContent())
            .writer(guestbook.getWriter())
            .regDate(guestbook.getRegDate())
            .modDate(guestbook.getModDate())
            .build();

  }

}
