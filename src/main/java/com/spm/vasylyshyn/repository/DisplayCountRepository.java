package com.spm.vasylyshyn.repository;

import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.DisplayCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DisplayCountRepository extends JpaRepository<DisplayCount,Long> {
    Optional<List<DisplayCount>> findDisplayCountsByDevice(Device device);

}
