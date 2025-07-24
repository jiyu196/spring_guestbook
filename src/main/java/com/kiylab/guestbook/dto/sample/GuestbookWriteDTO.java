package com.kiylab.guestbook.dto.sample;

import com.kiylab.guestbook.entity.Guestbook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GuestbookWriteDTO {
  private Long gno;
  private String title;
  private String content;
  private String writer;


public GuestbookWriteDTO(Guestbook guestbook){
  this.gno= guestbook.getGno();
  this.title=guestbook.getTitle();
  this.content=guestbook.getContent();   //이런 생성자는 조회시에만 필요
  this.writer=guestbook.getWriter();
  }
public Guestbook toEntity() {
  return Guestbook.builder().content(content).writer(writer).title(title).build();
  }
}
