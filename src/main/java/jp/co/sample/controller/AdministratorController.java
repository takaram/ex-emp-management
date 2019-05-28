package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);

        return "redirect:/";
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
}
