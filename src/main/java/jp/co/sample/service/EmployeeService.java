package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報に関するサービスクラス.
 *
 * @author takuya.aramaki
 */
@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 全従業員の情報の一覧を返す.
     * 
     * @return 全従業員の情報のList
     */
    public List<Employee> showList() {
        return employeeRepository.findAll();
    }

    /**
     * 指定したIDの従業員情報を返す.
     *
     * @param id ID
     * @return 引数のIDの従業員情報
     */
    public Employee showDetail(Integer id) {
        return employeeRepository.load(id);
    }

    /**
     * 指定したIDの従業員情報を返す. showDetail(Integer)のエイリアス。
     *
     * @param id ID
     * @return 引数のIDの従業員情報
     */
    public Employee load(Integer id) {
        return showDetail(id);
    }

    /**
     * 従業員情報を更新する.
     *
     * @param employee 従業員情報
     */
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }
}
