package com.lingyun.framework.model.repository;

import com.lingyun.framework.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,String> {


    /**
     * 下班打卡
     * @param outTime
     * @param time
     * @param EmployeeId
     * @return
     */
    @Modifying
    @Query("update Attendance a set a.outTime=?1 where a.time=?2 and a.employeeId=?3")
    public int AfternoonClock(String outTime,String time,int EmployeeId);

    /**
     * 加载打卡界面，查询用户是否打卡
     * @param EmployeeID
     * @param time
     * @return Attendance
     */
    @Query("select a from Attendance a where a.employeeId=?1 and a.time=?2")
    public List<Attendance> byAttendanceList(int EmployeeID,String time);

}
