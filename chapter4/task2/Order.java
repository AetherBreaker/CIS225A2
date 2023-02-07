package chapter4.task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;import

record CustomerData(
        String lastname,
        String firstname,
        String company,
        String address,
        String city,
        String state,
        int zip,
        int phone) {
}

public class Order {
    private static enum Design {
        NATURE("Nature"),
        TECH("Tech"),
        BUSINESS("Business"),
        MUSIC("Music"),
        NAUGHTY("Naughty");

        private String text;

        Design(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static Design fromString(String text) {
            for (Design b : Design.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    private static final HashMap<Design, Integer[]> PRICETABLE=new HashMap<Design,Integer[]>(){{put(Design.NATURE,new Integer[]{300,10,15,20,25,30});put(Design.TECH,new Integer[]{350,20,30,40,50,60});put(Design.BUSINESS,new Integer[]{375,30,40,50,60,70});put(Design.MUSIC,new Integer[]{400,85,95,110,130,210});put(Design.NAUGHTY,new Integer[]{500,100,200,300,400,500});}};
    private final int ordernumber;
    private CustomerData customerdata;
    private Design design;
    private int featurecount;
    private int cost;

    public Order() {
        prompt_for_customer_data();
        prompt_for_design_and_features();
        this.ordernumber = generate_order_number();
    }

    private void prompt_for_customer_data() {
        System.out.println("Enter customer data:");
        System.out.println("Last name:");
        String lastname = System.console().readLine();
        System.out.println("First name:");
        String firstname = System.console().readLine();
        System.out.println("Company:");
        String company = System.console().readLine();
        System.out.println("Address:");
        String address = System.console().readLine();
        System.out.println("City:");
        String city = System.console().readLine();
        System.out.println("State:");
        String state = System.console().readLine();
        int zip;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("Zip:");
                zip = Integer.parseInt(System.console().readLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid zip code.");
                System.out.println("Zip:");
            }
        }
        int phone;
        valid = false;
        while (!valid) {
            try {
                System.out.println("Phone:");
                phone = Integer.parseInt(System.console().readLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid phone number");
                System.out.println("Phone:");
            }
        }
        this.customerdata = new CustomerData(lastname, firstname, company, address, city, state, zip, phone);
    }

    private void prompt_for_design_and_features() {
        System.out.println("Nature\nTech\nBusiness\nMusic\nNaughty\nSelect a design from the above options:");
        this.design = null;
        while (this.design == null) {
            this.design = Design.fromString(System.console().readLine());
            if (this.design == null) {
                System.out.println("Invalid design.");
                System.out.println("Nature\nTech\nBusiness\nMusic\nNaughty\nSelect a design from the above options:");
            }
        }
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("Feature count:");
                this.featurecount = Integer.parseInt(System.console().readLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid feature count.");
                System.out.println("Feature count:");
            }
        }
        this.cost = PRICETABLE.get(this.design)[0] + PRICETABLE.get(this.design)[this.featurecount];
    }

    private int generate_order_number() {
        File ordercount = new File("ordersmade.txt");
        try (FileWriter writer = new FileWriter(ordercount)) {
            int ordernum;
            if (ordercount.createNewFile()) {
                writer.write("1");
                writer.close();
                ordernum = 1;
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(ordercount));
                try {
                    ordernum = 1 + Integer.parseInt(reader.readLine());
                    writer.write(Integer.toString(ordernum));
                } catch (NumberFormatException e) {
                    ordernum = 1;
                    writer.write("1");
                }
                reader.close();
                writer.close();
            }
            return ordernum;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 1;
        }
    }

    public void print_order() {
        System.out.println("Order number: " + this.ordernumber);
        System.out.println("Customer data:");
        System.out.println("Last name: " + this.customerdata.lastname());
        System.out.println("First name: " + this.customerdata.firstname());
        System.out.println("Company: " + this.customerdata.company());
        System.out.println("Address: " + this.customerdata.address());
        System.out.println("City: " + this.customerdata.city());
        System.out.println("State: " + this.customerdata.state());
        System.out.println("Zip: " + this.customerdata.zip());
        System.out.println("Phone: " + this.customerdata.phone());
        System.out.println("Design: " + this.design.getText());
        System.out.println("Feature count: " + this.featurecount);
        System.out.println("Cost: " + this.cost);
    }

    private void save_to_file() {
        File orderfile = new File("order" + this.ordernumber + ".txt");
        try (FileWriter writer = new FileWriter(orderfile)) {
            writer.write("Order number: " + this.ordernumber);
    }
}
