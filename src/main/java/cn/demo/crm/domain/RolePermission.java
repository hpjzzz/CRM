package cn.demo.crm.domain;

public class RolePermission {
    private Long lId;

    private Long rId;

    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "lId=" + lId +
                ", rId=" + rId +
                '}';
    }
}