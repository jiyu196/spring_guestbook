package com.kiylab.guestbook.service;

import com.kiylab.guestbook.dto.GuestbookDTO;
import com.kiylab.guestbook.dto.PageRequestDTO;
import com.kiylab.guestbook.dto.PageResponseDTO;
import com.kiylab.guestbook.entity.Guestbook;
import com.kiylab.guestbook.entity.QGuestbook;
import com.kiylab.guestbook.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class GuestbookServiceImpl implements GuestbookService {
  // DDD(= Domain Driven Development)

  private final GuestbookRepository repository;  //final붙인것도 코드 개선하는 방법 중 하나
  private final RestClient.Builder builder;

  public Long write(GuestbookDTO guestbookDTO) {
    return repository.save(toEntity(guestbookDTO)).getGno();
  }

  public GuestbookDTO read(Long gno) {
//    return null;
    return toDto(repository.findById(gno).orElseThrow(null));
  }

  @Transactional(readOnly = true)
  public List<GuestbookDTO> readAll() {
    return repository.findAll(Sort.by(Sort.Direction.DESC,"gno")).stream().map(this :: toDto).toList();
  }  //DML이 끼어들지 않고 읽기 전용이 됨.


  @Override
  public PageResponseDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO) {
//    BooleanBuilder getSearch = getSearch(pageRequestDTO);
    return new PageResponseDTO<>(repository.findAll(getSearch(pageRequestDTO), pageRequestDTO.getPageable(Sort.by(Sort.Direction.DESC, "gno"))),this::toDto);
  }

  public void modify(GuestbookDTO guestbookDTO) {
    repository.save(toEntity(guestbookDTO));
//    return repository.existsById(guestbookDTO.getGno()) ? 1 : 0;
    // 이게 findById보다 경량이고 개수만 가져옴
  }   //값체크, null처크는 도메인에서 하는게 맞음.

  @Override
  public void remove(Long gno) {
//    try {
    repository.deleteById(gno);
//    return 1;
//    } catch(Exception e) {
//        return 0;
//    }
  }

  private BooleanBuilder getSearch(PageRequestDTO dto) {
    String type = dto.getType();
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    QGuestbook guestbook = QGuestbook.guestbook;
    String keyword = dto.getKeyword();

    BooleanExpression expression = guestbook.gno.gt(0L);

    booleanBuilder.and(expression);

    if (type == null || type.trim().isEmpty()) {
      return booleanBuilder;
    }

    BooleanBuilder builder = new BooleanBuilder();

    if (type.contains("t")) {
      builder.or(guestbook.title.contains(keyword));
    }
    if (type.contains("c")) {
      builder.or(guestbook.content.contains(keyword));
    }
    if (type.contains("w")) {
      builder.or(guestbook.writer.contains(keyword));
    }

    booleanBuilder.and(builder);
    return booleanBuilder;
  }
}
