package com.kiylab.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageRequestDTO {
  @Builder.Default
  private int page = 1;
  @Builder.Default
  private int size = 10;


  private String type;
  private String keyword;

  public Pageable getPageable(Sort sort) {
    return PageRequest.of(page -1 , size, sort);  //pageable은 0이라고 아고있어야하기 때문에 -1하는거

  }

  public String getQs() {
    UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/guestbook");
    builder.queryParam("size", size);
    builder.queryParam("type", type);
    builder.queryParam("keyword", keyword);
    return builder.build().toUriString();
  }

  public String getQs2() {
    UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/guestbook");
    builder.queryParam("size", size);
    builder.queryParam("page", page);
    builder.queryParam("type", type);
    builder.queryParam("keyword", keyword);
    return builder.build().toUriString();
  }
}
