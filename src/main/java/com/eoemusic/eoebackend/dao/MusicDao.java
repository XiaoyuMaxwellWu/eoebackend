package com.eoemusic.eoebackend.dao;

import com.eoemusic.eoebackend.domain.MusicResponse;
import com.eoemusic.eoebackend.domain.PageInfo;
import com.eoemusic.eoebackend.domain.QueryRequest;
import com.eoemusic.eoebackend.domain.QueryResult;
import com.eoemusic.eoebackend.utils.MysqlPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @description: some desc
 * @author: Xiaoyu Wu
 * @email: wu.xiaoyu@northeastern.edu
 * @date: 20/01/23 3:07 AM
 */
@Repository
@Slf4j
public class MusicDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Value(value = "${alist.ipPort}")
  private String alistUrlPrefix;

  public QueryResult queryMusic(QueryRequest query) {
    Map<String, String> conditionMap = query.getConditionMap();
    List<String> pList = new ArrayList<>();
    StringBuilder sql = new StringBuilder(
        "select id, song_date, singer, song_name, song_name_alias, version_remark, Alist_audio_path,"
            + " Alist_cover_path, partial_url, audio_media_type, cover_media_type, duration, "
            + "song_language, song_status, hit_count from music where 1 = 1 ");

    if (!isEmpty(conditionMap.get("userInput"))) {
      sql.append(" and song_name like '%" + conditionMap.get("userInput") + "%'").
          append(" or song_name_alias like '%" + conditionMap.get("userInput") + "%'");
    }
    if (log.isDebugEnabled()) {
      log.info(sql.toString());
    }
    PageInfo pageable = query.getPageable();
    int currentPage = Integer.valueOf(pageable.getPage());
    int numPerPage = Integer.valueOf(pageable.getSize());
    MysqlPage page = new MysqlPage(sql.toString(), currentPage, numPerPage, jdbcTemplate,
        pList.toArray());
    pageable.setTotal(page.getTotalRows());
    List<Map<String, Object>> list = page.getResultList();
    List<MusicResponse> resList = new ArrayList<>();
    list.forEach(data -> {
      MusicResponse res = new MusicResponse();
      res.setId(String.valueOf(data.get("id")));
      res.setSongDate(String.valueOf(data.get("song_date")));
      res.setSinger(String.valueOf(data.get("singer")));
      res.setSongName(String.valueOf(data.get("song_name")));
      res.setSongNameAlias(
          data.get("song_name_alias") == null ? "" : String.valueOf(data.get("song_name_alias")));
      res.setVersionRemark(String.valueOf(data.get("version_remark")));
      res.setAudioUrl(new StringBuilder(alistUrlPrefix).append((data.get("Alist_audio_path")))
          .append(data.get("partial_url")).toString());
      res.setCoverUrl(new StringBuilder(alistUrlPrefix).append((data.get("Alist_cover_path")))
          .append(data.get("partial_url")).toString());
      res.setDuration(Integer.valueOf(String.valueOf(data.get("duration"))));
      res.setSongLanguage(String.valueOf(data.get("song_language")));
      res.setSongStatus(
          data.get("song_status") == null ? "" : String.valueOf(data.get("song_status")));
      res.setHitCount(Integer.valueOf(String.valueOf(data.get("hit_count"))));
      resList.add(res);
    });
    return new QueryResult(resList, pageable);
  }

  public boolean isEmpty(String str) {
    return str == null || str.length() == 0;
  }

}
