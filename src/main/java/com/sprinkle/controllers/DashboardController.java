package com.sprinkle.controllers;

import com.sprinkle.dao.DashboardDAO;

public class DashboardController {
    private DashboardDAO dashboardDAO;

    public DashboardController() {
        dashboardDAO = new DashboardDAO();
    }

    public int[] getDashboardMetrics() {
        return dashboardDAO.getDashboardMetrics();
    }
}
