package com.eoemusic.eoebackend.repository;

import com.eoemusic.eoebackend.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description: some desc
 * @author: Xiaoyu Wu
 * @email: wu.xiaoyu@northeastern.edu
 * @date: 19/01/23 5:58 AM
 */
public interface MusicRepository extends JpaRepository<Music, String> {

}
