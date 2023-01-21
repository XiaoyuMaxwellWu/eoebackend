package com.eoemusic.eoebackend.entity;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import lombok.RequiredArgsConstructor;

/**
 * @Description
 * @Author Xiaoyu Wu
 * @Email: wu.xiaoyu@northeastern.edu
 * @Date 2023-01-20 01:09:48
 */

@Entity
@Data
@Table(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = 2041818266677126425L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "phone_country")
  private String phoneCountry;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "playlist_ids")
  private String playlistIds;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_time")
  private Date createTime;

  @PrePersist
  private void touchCreateTime() {
    Date date = new Date();
    setCreateTime(date);
  }


}
