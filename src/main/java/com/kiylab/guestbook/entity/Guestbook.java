package com.kiylab.guestbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_guestbook")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class Guestbook extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long gno;

  @Column(length = 100, nullable = false)
  private String title;

  @Column(length = 1500, nullable = false)
  private String content;

  @Column(length = 50, nullable = false)
  private String writer;
}
