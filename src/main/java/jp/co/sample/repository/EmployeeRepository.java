package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * DB上の従業員情報を操作するクラス.
 * 
 * @author takuya.aramaki
 */
@Repository
public class EmployeeRepository {
    /** EmployeeのRowMapper */
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        return new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("image"), rs.getString("gender"),
                rs.getDate("hire_date"), rs.getString("mail_address"), rs.getString("zip_code"),
                rs.getString("address"), rs.getString("telephone"), rs.getInt("salary"),
                rs.getString("characteristics"), rs.getInt("dependents_count"));
    };

    /** DBアクセス用のテンプレート */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 従業員情報一覧を入社日順で取得する.
     * 
     * @return 従業員情報のリスト
     */
    public List<Employee> findAll() {
        String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count "
                + "FROM employees ORDER BY hire_date;";
        return template.query(sql, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * 指定したIDの従業員情報を取得する.
     * 
     * @param id ID
     * @return 従業員情報
     * @throws DataAccessException 指定したIDの従業員が存在しない場合
     */
    public Employee load(Integer id) {
        String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count "
                + "FROM employees WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, params, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * 従業員情報を更新する.
     * 
     * @param employee 新しい従業員情報
     */
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name=:name,image=:image,gender=:gender,hire_date=:hireDate,"
                + "mail_address=:mailAddress,zip_code=:zipCode,address=:address,telephone=:telephone,salary=:salary,"
                + "characteristics=:characteristics,dependents_count=:dependentsCount WHERE id = :id;";
        SqlParameterSource params = new BeanPropertySqlParameterSource(employee);
        template.update(sql, params);
    }
}
