package com.kiylab.guestbook.dto.sample;

import com.kiylab.guestbook.entity.Guestbook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GuestbookListDTO {
  private Long gno;
  private String title;
  private String content;
  private String writer;
  private LocalDateTime regDate, modDate;

  public GuestbookListDTO(Guestbook guestbook) { // 생성시에 코드 초기화
    this.gno = guestbook.getGno();
    this.title=guestbook.getTitle();
    this.content=guestbook.getContent();
    this.writer=guestbook.getWriter();
    this.regDate=guestbook.getRegDate();
    this.modDate=guestbook.getModDate();
  }
}
