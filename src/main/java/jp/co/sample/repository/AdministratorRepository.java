package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * DB上の管理者情報を操作するクラス.
 * 
 * @author takuya.aramaki
 */
@Repository
public class AdministratorRepository {
    /** AdministratorのRowMapper */
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
        return new Administrator(
                rs.getInt("id"), rs.getString("name"), rs.getString("mailAddress"), rs.getString("password"));
    };

    /** DBアクセス用のテンプレート */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 管理者情報をDBへ挿入する.
     * 
     * @param administrator 挿入するオブジェクト
     */
    public void insert(Administrator administrator) {
        String sql = "INSERT INTO administrators (name, mail_address, password) VALUES (:name, :mailAddress, :password);";
        SqlParameterSource params = new BeanPropertySqlParameterSource(administrator);
        template.update(sql, params);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する.
     * 
     * @param mailAddess メールアドレス
     * @param password   パスワード
     * @return Administratorオブジェクト。該当するものが存在しない場合はnull。
     */
    public Administrator findByMailAddressAndPassword(String mailAddess, String password) {
        String sql = "SELECT id,name,mail_address,password FROM administrators "
                + "WHERE mail_address = :mailAddress AND password = :password;";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddess)
                .addValue("password", password);
        try {
            return template.queryForObject(sql, params, ADMINISTRATOR_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
