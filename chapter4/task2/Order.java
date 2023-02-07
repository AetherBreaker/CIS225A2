package chapter4.task2;

record CustomerData(
        String lastname,
        String firstname,
        String company,
        String address,
        String city,
        String state,
        String zip,
        int phone) {
}

public class Order {
    int ordernumber;
    CustomerData customerdata;
    Design design;
    int featurecount;

}
