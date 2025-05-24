package com.bjpowernode.oa.bean;

import java.util.Objects;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-16 16:26
 */
public class Dept {
    private String deptNo;
    private String deptName;
    private String loc;

    public Dept() {
    }

    public Dept(String deptNo, String deptName, String loc) {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return Objects.equals(deptNo, dept.deptNo) && Objects.equals(deptName, dept.deptName) && Objects.equals(loc, dept.loc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptNo, deptName, loc);
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
