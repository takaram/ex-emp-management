package jp.co.sample.form;

/**
 * 従業員情報の更新用のフォームクラス.
 *
 * @author takuya.aramaki
 */
public class UpdateEmployeeForm {
    /** ID */
    private String id;
    /** 扶養人数 */
    private String dependentsCount;

    public Integer getDependentsCountAsInteger() {
        return Integer.valueOf(dependentsCount);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDependentsCount() {
        return dependentsCount;
    }

    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }

    @Override
    public String toString() {
        return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
    }
}
