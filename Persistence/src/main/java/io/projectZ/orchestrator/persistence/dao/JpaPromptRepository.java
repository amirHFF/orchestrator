package io.projectZ.orchestrator.persistence.dao;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/23/2026 - 9:45 AM
*/

import io.projectZ.orchestrator.persistence.entity.PromptEntity;
import io.projectZ.orchestrator.persistence.entity.PromptTypeEnum;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface JpaPromptRepository extends JpaRepository<PromptEntity , Long>  {
    PromptEntity findByCode(String code);

    List<PromptEntity> findAllByCodeIn(List<String> codes);

    @NativeQuery(value = "select distinct on (p.prompt_type) P.* from prompt p " +
            " where p.prompt_type in (:promptTypeList) order by p.prompt_type , p.priority desc nulls last")
    List<PromptEntity> findAllByPromptType(List<String> promptTypeList);

    @Query(value = "select e from PromptEntity e where (:code is null or e.code like %:code%)" +
            " and (:title is null or e.title like %:title%) ORDER BY e.insertTime desc ")
    Page<PromptEntity> search(String code , String title , Pageable pageable);
}

