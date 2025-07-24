package com.kiylab.guestbook.dto.sample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GuestbookModifyDTO {
  private Long gno;
  private String title;
  private String content;
  private String writer;
  private LocalDateTime regDate, modDate;
}
