package com.lingyun.framework.model.repository;

import com.lingyun.framework.entity.AttendanceCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface AttendanceCountRepository extends JpaRepository<AttendanceCount,String> {


    /**
     * 查询条件为 员工 月份
     * @param moth
     * @param employeeId
     * @return
     */
    @Query("select count(*) from AttendanceCount a where a.timeMoth=?1 and a.employeeId=?2")
    int byCount(String moth,int employeeId);

    /**
     * 查询条件为 员工 月份
     * @param moth
     * @param employeeId
     * @return
     */
    @Query("select a from AttendanceCount a where a.timeMoth=?1 and a.employeeId=?2")
    List<AttendanceCount> byList(String moth, int employeeId);
    /**
     * 修改记录
     * @param moth
     * @param employeeId
     * @return
     */
    @Modifying(clearAutomatically = true)@Transactional
    @Query("update AttendanceCount a set a.field=a.field + ?1,a.missCar=a.missCar+?2,a.late=a.late+?3, a.missWork=a.missWork+?4 where a.timeMoth=?5 and a.employeeId=?6")
    int editRecord( int field, int missCar, int late, double misswork, String moth, int employeeId);


}
