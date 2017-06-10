package com.skyblue.coalapp.server.timeline.repository;

import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.timeline.domain.Timeline_likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineLikesRepository extends JpaRepository<Timeline_likes, Long> {

}
