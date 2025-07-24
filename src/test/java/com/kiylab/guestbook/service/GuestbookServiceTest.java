package com.kiylab.guestbook.service;

import com.kiylab.guestbook.dto.GuestbookDTO;
import com.kiylab.guestbook.dto.PageRequestDTO;
import com.kiylab.guestbook.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Log4j2
public class GuestbookServiceTest {

  @Autowired
  private GuestbookService guestbookService;  //이ㅣㄴ터페이스이지만 찾으러 감.

  @Test
  public void testExist() {
    log.info("service");
  }


  @Test
  public void testWrite() {
    Long gno = guestbookService.write(GuestbookDTO.builder()
            .title("제목테스트12")
            .content("내용테스트213")
            .writer("작성자")
            .build());
//    log.info("작성내용");
//    Long gno = guestbookService.write(guestbookDTO);
    Assertions.assertNotNull(gno);
    log.info(gno);
  }

  @Test
  public void testRead() {  //단일조회

    Long gno = 102L;
    GuestbookDTO dto = guestbookService.read(gno);
    GuestbookDTO expect = GuestbookDTO.builder()
            .title("제목테스트12")
            .content("내용테스트213")
            .writer("작성자")
            .gno(gno).build();

    Assertions.assertEquals(dto.getTitle(), expect.getTitle());
    Assertions.assertEquals(dto.getContent(), expect.getContent());
    Assertions.assertEquals(dto.getWriter(), expect.getWriter());
  }

  @Test
  public void testReadAll() {
    guestbookService.readAll().forEach(log :: info);
  }

  @Test
  @Transactional
  @Commit
  public void tetModify() {
    Long gno = 101L;
    GuestbookDTO dto = guestbookService.read(gno);
    dto.setContent("수정내용");
    guestbookService.modify(dto);
  }

  @Test
  public void testRemove() {
    guestbookService.remove(102L);
  }

  @Test
  public void testList() {
    PageResponseDTO<?,?> dto = guestbookService.getList(PageRequestDTO.builder().page(2).size(5).build());
    log.info(dto);

  }

  @Test
  public void testListQuery() {
    guestbookService.getList(PageRequestDTO.builder().type("tc").keyword("0").build()).getList().forEach(log::info);
  }
}
