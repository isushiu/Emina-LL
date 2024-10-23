public class Customer
{
    private String customerName;
    private String customerAddress;
    private String customerPhoneNum;
    private String voucher;                 //  VC010-Discount 10% | FS045- deduct RM8.00 || NO- doesnt have any voucher
    private String paymentMethod;           //  TRANSFER or COD
    private String itemCode;                //  SKI010/SKI011/SKI012....
    private String itemName;
    private String itemDetails;             //  Combo/Cosmetic/Skincare
    private int itemQuantity;               //  Amount of item
    
    //  Default constr.
    public Customer()
    {
        customerName = null;
        customerAddress = null;
        customerPhoneNum = null;
        voucher = null;
        paymentMethod = null;
        itemCode = null;
        itemName = null;
        itemDetails = null;
        itemQuantity = 0;
    }
    
    
    //  Normal constr.
    public Customer(String customerName,String customerAddress,String customerPhoneNum,String voucher,String paymentMethod,String itemCode,String itemName, String itemDetails,int itemQuantity)
    {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNum = customerPhoneNum;
        this.voucher = voucher;
        this.paymentMethod = paymentMethod;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDetails = itemDetails;
        this.itemQuantity = itemQuantity;
    }
    
    
    //  setter
    public void setCustomerName(String customerName){this.customerName = customerName;}
    public void setCustomerAddress(String customerAddress){this.customerAddress = customerAddress;}
    public void setCustomerPhone(String customerPhoneNum){this.customerPhoneNum = customerPhoneNum;}
    public void setVoucher(String voucher){this.voucher = voucher;}
    public void setPaymentMethod(String paymentMethod){this.paymentMethod = paymentMethod;}
    public void setItemCode(String itemCode){this.itemCode = itemCode;}
    public void setItemName(String itemName){this.itemName = itemName;}
    public void setItemDetails(String itemDetails){this.itemDetails = itemDetails;}
    public void setItemQuantity(int itemQuantity){this.itemQuantity = itemQuantity;}
    
    
    //  accessor (getter)
    public String getCustomerName(){return customerName;}
    public String getCustomerAddress(){return customerAddress;}
    public String getCustomerPhone(){return customerPhoneNum;}
    public String getVoucher(){return voucher;}
    public String getPaymentMethod(){return paymentMethod;}
    public String getItemCode(){return itemCode;}
    public String getItemName(){return itemName;}
    public String getItemDetails(){return itemDetails;}
    public int getItemQuantity(){return itemQuantity;}
    
    
    public double calcPrice(String itemCode,int itemQuantity)
    {
        double calcPrice =0.00;
        
        if(itemCode.equalsIgnoreCase("SKI010"))
        {
            calcPrice = 7.82*itemQuantity;
        }
        else if(itemCode.equalsIgnoreCase("SKI011"))
        {
            calcPrice = 8.62*itemQuantity;
        }
        else if (itemCode.equalsIgnoreCase("SKI012"))
        {
            calcPrice = 7.50*itemQuantity;
        }
        else if (itemCode.equalsIgnoreCase("SKI013"))
        {
            calcPrice = 8.25*itemQuantity;
        }
        else if (itemCode.equalsIgnoreCase("COS101"))
        {
            calcPrice = 19.38*itemQuantity;
        }
        else if (itemCode.equalsIgnoreCase("COS102"))
        {
            calcPrice = 1.38*itemQuantity;
        }
        else if (itemCode.equalsIgnoreCase("COS103"))
        {
            calcPrice = 16.74*itemQuantity;
        }
        else if (itemCode.equalsIgnoreCase("COS105"))
        {
            calcPrice = 13.12*itemQuantity;
        }
        else if (itemCode.equalsIgnoreCase("COM210"))
        {
            calcPrice = 13.23*itemQuantity;
        }
        else if (itemCode.equalsIgnoreCase("COM211"))
        {
            calcPrice = 16.24*itemQuantity;
        }
        else if (itemCode.equalsIgnoreCase("COM212"))
        {
            calcPrice = 22.75*itemQuantity;
        }
        else if (itemCode.equalsIgnoreCase("COM213"))
        {
            calcPrice = 45.64*itemQuantity;
        }
        return calcPrice;
    }

   
    //  voucherChecker determine amount of discount they will get.
    public double voucherChecker(String voucher, double total)
    {
        double discount = 0.00;
        if(voucher.equalsIgnoreCase("VC010"))
        {
            discount = total*0.10;
        }
        else if(voucher.equalsIgnoreCase("FS045"))
        {
            discount = 8.00;
        }
        else
        {
            discount = 0.00;
        }
        
        return discount;
    }
        
    //  printer
    public String toString()
    {
        String printer = "\nCustomer Name           : " + customerName + 
                         "\nCustomer Address        : " + customerAddress+
                         "\nCustomer Phone Number   : " + customerPhoneNum+
                         "\nVoucher                 : " + voucher+
                         "\nPayment Method          : " + paymentMethod+
                         
                         "\n\n----------------------- ITEM -----------------------"+
                         
                         "\nItem Code               : " + itemCode+
                         "\nItem Name               : " + itemName+
                         "\nItem Details            : " + itemDetails+
                         "\nItem Quantity           : " + itemQuantity;
        return printer;
    }
}