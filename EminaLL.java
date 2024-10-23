import java.util.*;
import java.io.*;
import java.text.*;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
public class EminaLL
{
    public static void main(String [] args)
    {
        try
        {
            FileReader fr = new FileReader("CustInfo.txt");
            BufferedReader br = new BufferedReader (fr);
            
            DecimalFormat df = new DecimalFormat("0.00");
            
            //declaration of LinkedList
            LinkedList productLL = new LinkedList();

            //declaration of variables
            String customerName, customerAddress, customerPhoneNum, voucher, paymentMethod, itemCode, itemName, itemDetails;
            int itemQuantity;
            
            String s;
            s = br.readLine();
            
            while(s != null)
            {
                StringTokenizer st = new StringTokenizer(s,";");
                
                customerName = st.nextToken();
                customerAddress = st.nextToken();
                customerPhoneNum = st.nextToken();
                voucher = st.nextToken();
                paymentMethod = st.nextToken();
                itemCode = st.nextToken();
                itemName = st.nextToken();
                itemDetails = st.nextToken();
                itemQuantity = Integer.parseInt(st.nextToken());
                
                Customer data = new Customer(customerName,customerAddress,customerPhoneNum,voucher,paymentMethod,itemCode,itemName,itemDetails,itemQuantity);
                productLL.insertAtFront(data);
                s = br.readLine();
            }
            
            //Display receipt for customer
            
            FileWriter display = new FileWriter("customerList.txt");
            PrintWriter disp = new PrintWriter(display);
            
            disp.println("=========================================================================================================================");
            disp.println("\t\t\t\t\t\tDETAILS OF EMINA'S CUSTOMER");
            disp.println("=========================================================================================================================");
                                
            Customer data = (Customer)productLL.getFirst();
            int x = 1;
            while(data != null)
            {
               double totalItem = data.calcPrice(data.getItemCode(),data.getItemQuantity()) ; 
               double total = totalItem + 8; // Already include shipping RM 8
               double discount = data.voucherChecker(data.getVoucher(), total);
               

               String discDescr = "No Discount";
               double delivCharge = 0.00; //   Determine the delivery charge
               if(data.getVoucher().equalsIgnoreCase("FS045"))
               {
                   delivCharge = 0.00;
                   discDescr = "Free Delivery -RM 8.00";
               }
               else if (data.getVoucher().equalsIgnoreCase("VC010"))
               {
                   discDescr = "Discount 10%";
               }
               else
               {
                   delivCharge = 8.00;
               }
               
               total = total - discount;
               double tax = total*0.05;
               double totalPrice = total+tax;
               disp.println("\n\nORDER                    #"+x+"                                     FROM CUSTOMER "+ data.getCustomerName() + 
                                   "\n----------------------------------------------------------------------------------------------------------------------"+
                                   "\n" + data.toString() +
                                   "\n----------------------------------------------------------------------------------------------------------------------"+
                                   "\n Total Price For Item                                                             RM " + df.format(totalItem) +
                                   "\n Discount                                                                         RM " + df.format(discount)+ " (" +discDescr+")"+
                                   "\n Delivery Charge                                                                  RM " + df.format(delivCharge) +
                                   "\n Tax                                                                              RM " + df.format(tax) +
                                   "\n----------------------------------------------------------------------------------------------------------------------"+
                                   "\n\n Total Order                                                                      RM " + df.format(totalPrice) + 
                                   "\n----------------------------------------------------------------------------------------------------------------------");
                      
               x++;
               data = (Customer)productLL.getNext();
            }
            System.out.println();
            
            
            /** Update quantity of item by search customerName **/
            
            boolean found = false;
            String updateConfirmation = JOptionPane.showInputDialog(null,"Do You want to update quantity of item","UPDATE SYSTEM",JOptionPane.QUESTION_MESSAGE);
            while (updateConfirmation.equalsIgnoreCase("Yes"))
            {
                found = false;
                String searchItem = JOptionPane.showInputDialog(null,"CUSTOMER NAME","UPDATE SYSTEM",JOptionPane.QUESTION_MESSAGE);
                int newQuantity = Integer.parseInt(JOptionPane.showInputDialog(null,"UPDATE QUANTITY","UPDATE SYSTEM",JOptionPane.QUESTION_MESSAGE));
               
                data = (Customer)productLL.getFirst();
                while(data != null)
                {
                   if (data.getCustomerName().equalsIgnoreCase(searchItem))
                   {
                       System.out.println("==================================== Customer Order after update ====================================");
                       data.setItemQuantity(newQuantity);
                       found = true;
                   
                       System.out.println(data.toString());
                   }
                   data = (Customer)productLL.getNext();
                }    
               
                if (!found)
                {
                   JOptionPane.showMessageDialog(null,"The Name you enter is not in our database.","UPDATE SYSTEM",JOptionPane.WARNING_MESSAGE);
                }
               
                updateConfirmation = JOptionPane.showInputDialog(null,"Do You want to update quantity of item","UPDATE SYSTEM",JOptionPane.QUESTION_MESSAGE);
            }
            System.out.println();
            
            /**Remove data of quantity item that wrongly entered**/
            
            //declare linkedList for wrong data entered : quantity <= 0
            LinkedList wrongDataLL = new LinkedList();
            LinkedList tempLL = new LinkedList();
            
            while(!productLL.isEmpty())
            {
                data = (Customer)productLL.removeFromFront();
                if(data.getItemQuantity() <= 0)
                {
                    wrongDataLL.insertAtFront(data);
                }
                else
                {
                    tempLL.insertAtBack(data);
                }
            }
            
            while(!tempLL.isEmpty())
            {
                productLL.insertAtFront(tempLL.removeFromFront());
            }
            
            /** AFTER REMOVE **/
            //   Display receipt for customer
            System.out.println("\n\n\t*******************************  AFTER REMOVE THE WRONG OUTPUT   *******************************");
            double sum = 0.00, avg = 0.00;
            x = 1;
            
            data = (Customer)productLL.getFirst();
            while(data != null)
            {
                double totalItem = data.calcPrice(data.getItemCode(),data.getItemQuantity()) ; 
                double total = totalItem + 8; // Already include shipping RM 8
                double discount = data.voucherChecker(data.getVoucher(), total);
               

                String discDescr = "No Discount";
                double delivCharge = 0.00; //   Determine the delivery charge
                if(data.getVoucher().equalsIgnoreCase("FS045"))
                {
                   delivCharge = 0.00;
                   discDescr = "Free Delivery - RM 8.00";
                }
                else if (data.getVoucher().equalsIgnoreCase("VC010"))
                {
                   discDescr = "Discount 10%";
                }
                else
                {
                   delivCharge = 8.00;
                }
               
                total = total - discount;
                double tax = total*0.05;
                double totalPrice = total+tax;
                System.out.println("\n\nORDER                    #"+x+"                                     FROM CUSTOMER "+ data.getCustomerName() + 
                                   "\n----------------------------------------------------------------------------------------------------------------------"+
                                   "\n" + data.toString() +
                                   "\n----------------------------------------------------------------------------------------------------------------------"+
                                   "\n Total Price For Item                                                             RM " + df.format(totalItem) +
                                   "\n Discount                                                                         RM " + df.format(discount)+ " (" +discDescr+")"+
                                   "\n Delivery Charge                                                                  RM " + df.format(delivCharge) +
                                   "\n Tax                                                                              RM " + df.format(tax) +
                                   "\n----------------------------------------------------------------------------------------------------------------------"+
                                   "\n\n Total Order                                                                      RM " + df.format(totalPrice) + 
                                   "\n----------------------------------------------------------------------------------------------------------------------");
                x++;
                sum = sum + totalPrice;
                data = (Customer)productLL.getNext();
            }
            System.out.println();
            
            
            //declaration LinkedList based on itemDetails - Skincare/Cosmetic/Combo
            LinkedList SkincareLL = new LinkedList();
            LinkedList CosmeticLL = new LinkedList();
            LinkedList ComboLL = new LinkedList();
        
            //copy data followed by itemDetails
            data = (Customer)productLL.getFirst();
            while(data != null)
            {
                if(data.getItemDetails().equalsIgnoreCase("SKINCARE"))
                {
                    SkincareLL.insertAtBack(data);
                }
                else if(data.getItemDetails().equalsIgnoreCase("COSMETIC"))
                {
                    CosmeticLL.insertAtBack(data);
                }
                else
                {
                    ComboLL.insertAtBack(data);
                }
                data = (Customer)productLL.getNext();
            }
            
            
            /** Search and display item based on ItemCode that user key in **/
            
            found = false;
            String searchConfirmation = JOptionPane.showInputDialog(null,"Do you want to use the search system\n[this search system will list all customer that bought the item code that been search] ","SEARCH SYSTEM",JOptionPane.QUESTION_MESSAGE);
            int countS = 1;
            if (searchConfirmation.equalsIgnoreCase("YES"))
            {
                System.out.println("\n\t------------------------------------------------------------------------------------------------------"
                                +"\n\t\t\t\t\t\tSEARCH SYSTEM" + 
                                "\n\t------------------------------------------------------------------------------------------------------");
            }
            while(searchConfirmation.equalsIgnoreCase("YES"))
            {
                String searchItem = JOptionPane.showInputDialog(null,"Enter the Item Code  ","SEARCH SYSTEM",JOptionPane.QUESTION_MESSAGE);
                
                data = (Customer)productLL.getFirst();
                while(data != null)
                {
                    if(data.getItemCode().equalsIgnoreCase(searchItem))
                    {
                       System.out.println("\n NO : " + countS + data.toString());
                       found = true;
                       countS++;
                    }
                    data = (Customer)productLL.getNext();
                }
                
                if(!found)
                {
                    JOptionPane.showMessageDialog(null,"The Item Code does not exist in our database.","SEARCH SYSTEM",JOptionPane.WARNING_MESSAGE);
                }
                
                searchConfirmation = JOptionPane.showInputDialog(null,"Do you want to use the search system ","SEARCH SYSTEM",JOptionPane.QUESTION_MESSAGE);
            }
            
            /**                                                                              STAFF REPORT                                                              */
            avg = sum / productLL.size(); // to calculate the average
            System.out.println("\n\t*************************************  EMINA TIKTOKSHOP STAFF REPORT    *************************************"+
                                "\n\n\tTOTAL PROFITS (INCLUDING ALL CHARGES)        : RM "+ df.format(sum) +
                                "\n\tAVERAGE PROFITS FROM ALL ORDERS              : RM "+ df.format(avg));
           
            /** DISPLAY BEST SELLING ITEM */
            int bestSelling  = ((Customer)productLL.getFirst()).getItemQuantity();
            int lowSelling   = ((Customer)productLL.getFirst()).getItemQuantity(); 
           
            String highestCode = null, lowestCode = null;
            
            //to determine best selling and least selling item
            data = (Customer)productLL.getFirst();
            while(data != null)
            {
                if(data.getItemQuantity() > bestSelling)
                {
                    bestSelling = data.getItemQuantity();
                    highestCode = data.getItemCode();
                }
                
                if(data.getItemQuantity() < lowSelling)
                {
                    lowSelling = data.getItemQuantity();
                    lowestCode = data.getItemCode();
                }
                data = (Customer)productLL.getNext();
            }
            
            System.out.println("\n\t\t\t\t-----------------------------------------------" +
                               "\n\t\t\t\t\t\tBEST SELLING ITEM " + 
                               "\n\t\t\t\t------------------------------------------------");
            
            data = (Customer)productLL.getFirst();
            while(data != null)
            {
                if ((data.getItemCode().equalsIgnoreCase(highestCode)) && (data.getItemQuantity() == bestSelling))
                {
                    System.out.println("\n\t\t\t\tOrder with the highest amount          " +
                                        "\n\t\t\t\tItem Code         : " + data.getItemCode() + 
                                        "\n\t\t\t\tItem Name         : " + data.getItemName() +
                                        "\n\t\t\t\tItem Details      : " + data.getItemDetails() +
                                        "\n\t\t\t\tQuantity          : " + data.getItemQuantity()+
                                        "\n\t\t\t\t------------------------------------------------"+
                                        "\n\t\t\t\t\tORDER BY      : "+ data.getCustomerName()); 
                }
               
                if ((data.getItemCode().equalsIgnoreCase(lowestCode)) && (data.getItemQuantity() == lowSelling))
                {
                    System.out.println("\n\n\t\t\t\tOrder with the lowest amount          " +
                                        "\n\t\t\t\tItem Code         : " + data.getItemCode() + 
                                        "\n\t\t\t\tItem Name         : " + data.getItemName() +
                                        "\n\t\t\t\tItem Details      : " + data.getItemDetails() +
                                        "\n\t\t\t\tQuantity          : " + data.getItemQuantity()+
                                        "\n\t\t\t\t------------------------------------------------"+
                                        "\n\t\t\t\t\tORDER BY      : "+ data.getCustomerName()); 
                }
                data = (Customer)productLL.getNext();
            }
            
            //display the wrong entered data - FOR STAFF 
            FileWriter fw = new FileWriter("InvalidOrder.txt");
            PrintWriter pw = new PrintWriter(fw);
            x = 1;
           
            Customer wrongQ = (Customer)wrongDataLL.getFirst();
            while(wrongQ != null)
            {
                double totalItem = wrongQ.calcPrice(wrongQ.getItemCode(),wrongQ.getItemQuantity()) ; 
                double total = totalItem + 8; // Already include shipping RM 8
                double discount = wrongQ.voucherChecker(wrongQ.getVoucher(), total);
               

                String discDescr = "No Discount";
                double delivCharge = 0.00; //   Determine the delivery charge
                if(wrongQ.getVoucher().equalsIgnoreCase("FS045"))
                {
                   delivCharge = 0.00;
                   discDescr = "Free Delivery -RM 8.00";
                }
                else if (wrongQ.getVoucher().equalsIgnoreCase("VC010"))
                {
                   discDescr = "Discount 10%";
                }
                else
                {
                   delivCharge = 8.00;
                }
               
                total = total - discount;
                double tax = total*0.05;
                double totalPrice = total+tax;
                pw.println("\n\nORDER                    #"+x+"                                     FROM CUSTOMER "+ wrongQ.getCustomerName() + 
                                   "\n----------------------------------------------------------------------------------------------------------------------"+
                                   "\n" + wrongQ.toString() +
                                   "\n----------------------------------------------------------------------------------------------------------------------"+
                                   "\n Total Price For Item                                                             RM " + df.format(totalItem) +
                                   "\n Discount                                                                         RM " + df.format(discount)+ " (" +discDescr+")"+
                                   "\n Delivery Charge                                                                  RM " + df.format(delivCharge) +
                                   "\n Tax                                                                              RM " + df.format(tax) +
                                   "\n----------------------------------------------------------------------------------------------------------------------"+
                                   "\n\n Total Order                                                                      RM " + df.format(totalPrice) + 
                                   "\n----------------------------------------------------------------------------------------------------------------------");
                      
                x++;
                wrongQ = (Customer)wrongDataLL.getNext();
            }
            
            /**To count the total order by ItemType**/
            
            // initialize variable total by each itemDetails
            int totalSC = 0;    //SKINCARE
            int totalC = 0;     //COSMETIC
            int totalCB = 0;    //COMBO
            
            Customer skincare = (Customer)SkincareLL.getFirst();
            while(skincare != null)
            {
                totalSC = SkincareLL.size();
                skincare = (Customer)SkincareLL.getNext();
            }
            
            Customer cosmetic = (Customer)CosmeticLL.getFirst();
            while(cosmetic != null)
            {
                totalC = CosmeticLL.size();
                cosmetic = (Customer)CosmeticLL.getNext();
            }
            
            Customer combo = (Customer)ComboLL.getFirst();
            while(combo != null)
            {
                totalCB = ComboLL.size();
                combo = (Customer)ComboLL.getNext();
            }
            
            //To display the total order by ItemType
            System.out.println("\n\t\t\t\tTotal Order of SKINCARE         :" + totalSC);
            System.out.println("\t\t\t\tTotal Order of COSMETIC         :" + totalC);
            System.out.println("\t\t\t\tTotal Order of COMBO            :" + totalCB);
            
            
            disp.close();
            pw.close();
            br.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
        catch(IOException io)
        {
            System.out.println(io.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
