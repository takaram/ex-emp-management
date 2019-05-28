package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者情報に関するサービスクラス.
 * 
 * @author takuya.aramaki
 *
 */
@Service
@Transactional
public class AdministratorService {
    /** 管理者情報に関するリポジトリ */
    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * 管理者情報を挿入する.
     *
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator) {
        administratorRepository.insert(administrator);
    }

    /**
     * メールアドレスとパスワードを使ってログインする.
     *
     * @param mailAddress メールアドレス
     * @param password    パスワード
     * @return メールアドレスとパスワードが一致するAdministratorオブジェクト
     */
    public Administrator login(String mailAddress, String password) {
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
    }
}
