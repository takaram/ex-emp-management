package jp.co.sample.form;

/**
 * ログイン情報を受け取るフォームクラス.
 * 
 * @author takuya.aramaki
 */
public class LoginForm {
    private String mailAddress;
    private String password;

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

    @Override
    public String toString() {
        return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
    }
}
