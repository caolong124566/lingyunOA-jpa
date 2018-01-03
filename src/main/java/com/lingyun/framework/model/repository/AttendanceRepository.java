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
     * @param day
     * @param EmployeeId
     * @return
     */

    @Modifying(clearAutomatically = true)@Transactional
    @Query("update Attendance a set a.outTime=?1 where a.day=?2 and a.employeeId=?3")
    int AfternoonClock(String outTime, String day, int EmployeeId);

    /**
     * 加载打卡界面，查询用户是否打卡
     * @param EmployeeID
     * @param day
     * @return Attendance
     */
    @Query("select a from Attendance a where a.employeeId=?1 and a.day=?2")
    List<Attendance> byAttendanceList(int EmployeeID, String day);

    /**
     * 查询出勤天数
     * @param year
     * @param moth
     * @param employeeId
     * @return
     */
    @Query("select count(*) from Attendance a where a.year=?1 and a.moth=?2 and a.employeeId=?3")
    int findByWorkDay(String year, String moth, int employeeId);

    /**
     * 查询迟到次数
     * @param sgTime
     * @param year
     * @param moth
     * @param employeeId
     * @return
     */
    @Query("select count(*) from Attendance a where a.signTime>?1 and a.year=?2 and a.moth=?3 and a.employeeId=?4")
    int findByLate(String sgTime, String year, String moth, int employeeId);

    /**
     * 查询早退次数
     * @param outTime
     * @param year
     * @param moth
     * @param employeeId
     * @return
     */
    @Query("select count(*) from Attendance a where a.outTime<?1 and a.year=?2 and a.moth=?3 and a.employeeId=?4")
    int findInstall(String outTime, String year, String moth, int employeeId);


}
