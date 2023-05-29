package lk.ijse.tfms.dao;

import lk.ijse.tfms.dao.custom.impl.*;

public class FactoryDAO {
    private static FactoryDAO factoryDAO;

    private FactoryDAO(){
    }

    public static FactoryDAO getFactoryDAO(){
        return factoryDAO == null ? factoryDAO = new FactoryDAO() : factoryDAO;
    }

    public enum Types{
        BUYER,
        DAILY_CROP,
        DASHBOARD_NOTES,
        EMPLOYEE,
        OTHER_STOCKS,
        OTHER_SUPPLIERS,
        PAYMENT,
        QUARRY,
        TEA_STOCK,
        TEA_SUPPLIER
    }

    // factory - Object creation logic eka hide krnna.
    // singleton  - object ekak eka parak hadala eka re use karanna

    public SuperDAO getDAO(Types types){
        switch (types){
            case BUYER:
                return new BuyerDAOImpl();
            case DAILY_CROP:
                return new DailyCropDAOImpl();
            case DASHBOARD_NOTES:
                return new DashboardNotesDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case OTHER_STOCKS:
                return new OtherStockItemDAOImpl();
            case OTHER_SUPPLIERS:
                return new OtherSupplierDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case QUARRY:
                return new QuarryDAOImpl();
            case TEA_STOCK:
                return new TeaStockItemDAOImpl();
            case TEA_SUPPLIER:
                return new TeaSupplierDAOImpl();
            default: return null;
        }
    }
}
