package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報に関するコントローラークラス.
 *
 * @author takuya.aramaki
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 従業員情報更新用のフォームインスタンスを準備する.
     *
     * @return
     */
    @ModelAttribute
    public UpdateEmployeeForm setUpUpdateEmployeeForm() {
        return new UpdateEmployeeForm();
    }

    /**
     * 従業員一覧を表示する.
     *
     * @param model Requestスコープ
     * @return 従業員一覧画面のパス
     */
    @RequestMapping("/showList")
    public String showList(Model model) {
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList", employeeList);

        return "employee/list";
    }

    /**
     * 従業員情報の詳細画面を表示する.
     *
     * @param id    表示する従業員のID
     * @param model リクエストスコープ
     * @return 従業員詳細画面のパス
     */
    @RequestMapping("/showDetail")
    public String showDetail(String id, Model model) {
        Employee employee = employeeService.showDetail(Integer.valueOf(id));
        model.addAttribute("employee", employee);

        return "employee/detail";
    }

    /**
     * 従業員情報を更新する.
     *
     * @param form リクエストパラメータを受け取るフォーム
     * @return 従業員一覧画面へのリダイレクト
     */
    @RequestMapping("/update")
    public String update(UpdateEmployeeForm form) {
        Employee employee = employeeService.load(Integer.valueOf(form.getId()));
        employee.setDependentsCount(form.getDependentsCountAsInteger());
        employeeService.update(employee);

        return "redirect:/employee/showList";
    }
}
