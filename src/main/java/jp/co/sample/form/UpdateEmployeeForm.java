package jp.co.sample.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 従業員情報の更新用のフォームクラス.
 *
 * @author takuya.aramaki
 */
public class UpdateEmployeeForm {
    /** ID */
    private String id;
    /** 名前 */
    private String name;
    /** 画像 */
    private String image;
    /** 性別 */
    private String gender;
    /** 入社日 */
    private String hireDate;
    /** メールアドレス */
    private String mailAddress;
    /** 郵便番号 */
    private String zipCode;
    /** 住所 */
    private String address;
    /** 電話番号 */
    private String telephone;
    /** 給料 */
    private String salary;
    /** 特性 */
    private String characteristics;
    /** 扶養人数 */
    private String dependentsCount;

    public Integer getIdAsInteger() {
        return Integer.valueOf(id);
    }

    public void setId(Integer id) {
        this.id = id.toString();
    }

    public Date getHireDateAsDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(hireDate);
        } catch (ParseException e) {
            // バリデーションするのでエラーは起きないはず
            throw new RuntimeException(e);
        }
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = new SimpleDateFormat("yyyy-MM-dd").format(hireDate);
    }

    public Integer getSalaryAsInteger() {
        return Integer.valueOf(salary);
    }

    public void setSalary(Integer salary) {
        this.salary = salary.toString();
    }

    public Integer getDependentsCountAsInteger() {
        return Integer.valueOf(dependentsCount);
    }

    public void setDependentsCount(Integer dependentsCount) {
        this.dependentsCount = dependentsCount.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
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
