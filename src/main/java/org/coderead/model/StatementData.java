package org.coderead.model;

import java.util.List;

/**
 * 详单数据
 *
 * @author kendoziyu
 * @since 2020/10/18 0018
 */
public class StatementData {

    private String customer;
    private List<Performance> performances;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }
}
