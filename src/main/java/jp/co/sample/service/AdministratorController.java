package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * フォームインスタンスを準備する.
     * 
     * @return フォームインスタンス
     */
    @ModelAttribute
    public InsertAdministratorForm setUpInsertAdministratorForm() {
        return new InsertAdministratorForm();
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
}
