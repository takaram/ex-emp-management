package jp.co.sample.form;

/**
 * 管理者の新規登録用のフォームクラス
 * 
 * @author takuya.aramaki
 *
 */
public class InsertAdministratorForm {
    /** 名前 */
    private String name;
    /** メールアドレス */
    private String mailAddress;
    /** パスワード */
    private String password;

    /** 二重サブミット対策用トークン */
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
                + "]";
    }
}
