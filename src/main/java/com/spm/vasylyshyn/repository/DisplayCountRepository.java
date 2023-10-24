package com.spm.vasylyshyn.repository;

import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.DisplayCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplayCountRepository extends JpaRepository<DisplayCount,Long> {

}
