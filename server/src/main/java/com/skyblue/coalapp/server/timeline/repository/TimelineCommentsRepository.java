package com.skyblue.coalapp.server.timeline.repository;

import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.timeline.domain.Timeline_comments;
import com.skyblue.coalapp.server.timeline.domain.Timelines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 张杨 on 2017/5/19.
 */
@Repository
public interface TimelineCommentsRepository extends JpaRepository<Timeline_comments, Long> {

    Timeline_comments findById(Integer id);
}