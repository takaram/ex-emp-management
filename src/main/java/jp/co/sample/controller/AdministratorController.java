package jp.co.sample.controller;

import java.security.SecureRandom;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者機能のコントローラークラス.
 * 
 * @author takuya.aramaki
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
    /** 管理者情報を操作するサービスインスタンス */
    @Autowired
    private AdministratorService administratorService;

    /** セッションスコープ */
    @Autowired
    private HttpSession session;

    /**
     * 管理者新規登録用のフォームインスタンスを準備する.
     *
     * @return フォームインスタンス
     */
    @ModelAttribute
    public InsertAdministratorForm setUpInsertAdministratorForm() {
        return new InsertAdministratorForm();
    }

    /**
     * ログイン用のフォームインスタンスを準備する.
     *
     * @return フォームインスタンス
     */
    @ModelAttribute
    public LoginForm setUpLoginForm() {
        return new LoginForm();
    }

    /**
     * 管理者登録画面へフォワードする.
     *
     * @return 管理者登録画面のパス
     */
    @RequestMapping("/toInsert")
    public String toInsert() {
        String token = getRandomString();
        session.setAttribute("token", token);
        return "administrator/insert";
    }

    /**
     * 送信された管理者情報を登録し、/へリダイレクトする.
     *
     * @param form 新規管理者の情報
     * @return /へのリダイレクト
     */
    @RequestMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        // 二重サブミット対策
        String token = form.getToken();
        if (token != null && token.equals(session.getAttribute("token"))) {
            session.removeAttribute("token");
        } else {
            return "administrator/error";
        }

        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);

        return "redirect:/";
    }

    /**
     * ランダムな文字列を生成する. 文字列の長さは32文字です。
     *
     * @return 生成した文字列
     */
    private String getRandomString() {
        Random random = new SecureRandom();
        StringBuilder builder = new StringBuilder();
        String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < 32; i++) {
            // sourceからランダムに1文字選んでStringBuilderに入れる
            char c = source.charAt(random.nextInt(source.length()));
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * ログイン画面へフォワードする.
     *
     * @return ログイン画面のパス
     */
    @RequestMapping("/")
    public String toLogin() {
        return "administrator/login";
    }

    /**
     * 送信された値でログインを行う.
     *
     * @param form          リクエストパラメータを受け取るフォーム
     * @param bindingResult エラー情報を保持するオブジェクト
     * @return 従業員一覧画面へのフォワード
     */
    @RequestMapping("/login")
    public String login(LoginForm form, BindingResult bindingResult) {
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
        if (administrator == null) {
            bindingResult.rejectValue(null, null, "メールアドレスまたはパスワードが不正です。");
            return "administrator/login";
        }

        session.setAttribute("administratorName", administrator.getName());
        return "forward:/employee/showList";
    }

    /**
     * ログアウトを行う.
     *
     * @return ログイン画面へのリダイレクト
     */
    @RequestMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
