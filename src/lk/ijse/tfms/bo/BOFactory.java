package lk.ijse.tfms.bo;

import lk.ijse.tfms.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum Type {
        BUYER,
        DAILY_CROP,
        DASHBOARD_NOTES,
        EMPLOYEE,
        OTHER_STOCKS,
        OTHER_SUPPLIERS,
        PAYMENT,
        TEA_SELLING,
        TEA_STOCK,
        TEA_SUPPLIER
    }

    public SuperBO getBO(Type type) {
        switch (type) {
            case BUYER:
                return new BuyerBOImpl();
            case DAILY_CROP:
                return new DailyCropBOImpl();
            case DASHBOARD_NOTES:
                return new DashboardNotesBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case OTHER_STOCKS:
                return new OtherStocksBOImpl();
            case OTHER_SUPPLIERS:
                return new OtherSupplierBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case TEA_SELLING:
                return new TeaSellingBOImpl();
            case TEA_STOCK:
                return new TeaStockBOImpl();
            case TEA_SUPPLIER:
                return new TeaSupplierBOImpl();
            default: return null;
        }
    }
}
