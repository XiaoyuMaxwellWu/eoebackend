package com.eoemusic.eoebackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import lombok.Data;

/**
 * @Description
 * @Author Xiaoyu Wu
 * @Email: wu.xiaoyu@northeastern.edu
 * @Date 2023-01-20 01:10:06
 */

@Entity
@Data
@Table(name = "playlist")
public class Playlist implements Serializable {

  private static final long serialVersionUID = 2228111275599692905L;

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "playlist_name")
  private String playlistName;

  @Column(name = "music_ids")
  private String musicIds;

}
