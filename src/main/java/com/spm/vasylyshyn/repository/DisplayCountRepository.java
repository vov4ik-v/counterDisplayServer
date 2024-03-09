package com.spm.vasylyshyn.repository;

import com.spm.vasylyshyn.dto.DisplayCountDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.DisplayCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DisplayCountRepository extends JpaRepository<DisplayCount,Long> {
    Optional<List<DisplayCount>> findDisplayCountsByDevice(Device device);

    @Query("select new com.spm.vasylyshyn.dto.DisplayCountDto(d.id,d.displayCount) from DisplayCount as d where d.id = max(d.id)")
    Optional<DisplayCountDto>findDto(Device device);

    List<DisplayCount> findDisplayCountsByDeviceAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(Device device, LocalDateTime startRange, LocalDateTime endRange);


    @Query("select new com.spm.vasylyshyn.dto.DisplayCountDto(d.id,d.displayCount) from DisplayCount as d where d.device = :device")
    Optional<List<DisplayCountDto>> findDisplayCountsDtoByDevice(@Param("device") Device device);
}
