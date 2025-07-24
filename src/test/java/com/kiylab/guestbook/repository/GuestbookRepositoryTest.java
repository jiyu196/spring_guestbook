package com.kiylab.guestbook.repository;

import com.kiylab.guestbook.entity.Guestbook;
import com.kiylab.guestbook.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class GuestbookRepositoryTest {

  @Autowired
  private GuestbookRepository repository;

  @Test
  public void testExist() {
    log.info(repository);
  }

  @Test
  public void testInsert() {
//    repository.save(Guestbook.builder().title("제목").content("내용").writer("작성자").build());
    LongStream.range(1, 100).forEach(i -> repository.save(Guestbook.builder().title("제목" + i % 10).content("내용" + (i+5) % 10).writer("작성자").build()));
  }

//  @Test
//  public void testQueryDSL() {
//    QGuestbook qGuestbook = QGuestbook.guestbook;
//    BooleanBuilder builder = new BooleanBuilder();
//    builder.and(qGuestbook.title.contains("테스트"));
//    builder.and(qGuestbook.content.contains("내용"));
//  }

  @Test
  public void testQuerydsl() {
    Pageable pageable = PageRequest.of(0, 10);
    QGuestbook qGuestbook = QGuestbook.guestbook;
    String keyword = "1";
    BooleanBuilder builder = new BooleanBuilder();
    BooleanExpression expression = qGuestbook.title.contains(keyword);
    builder.and(expression);

//    builder.and(qGuestbook.title.contains(keyword));
//
//    builder.or(qGuestbook.content.contains(keyword));

    Page<Guestbook> guestbooks = repository.findAll(builder, pageable);
  }

  @Test
  @DisplayName("복합 조건 테스트")
  public void testQuerydsl2() {
    Pageable pageable = PageRequest.of(0, 10);
    QGuestbook qGuestbook = QGuestbook.guestbook;
    String keyword = "1";
    BooleanBuilder builder = new BooleanBuilder();
    BooleanExpression expression = qGuestbook.title.contains(keyword);
    BooleanExpression expression2 = qGuestbook.content.contains(keyword);
    BooleanExpression be = expression.or(expression2);
    builder.and(be);
    builder.and(qGuestbook.gno.gt(0));


    Page<Guestbook> guestbooks = repository.findAll(builder, pageable);
  }
}
