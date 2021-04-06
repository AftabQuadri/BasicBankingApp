package quadri.aftab.basic_banking_system;

public class CustomerModel {
    private String name,sender;
    private String email,receiver;
    private int amount,sent_amount;
    //constructor
    public CustomerModel(String name_or_sender, String email_or_receiver, int amount_or_sent_amount) {
        this.name = name_or_sender;
        this.email = email_or_receiver;
        this.amount = amount_or_sent_amount;
    }



    //toString to show all the contents of database
    @Override
    public String toString() {
        return "CustomerModel{" +
                " name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    //empty constructor
    public CustomerModel(String name, String email, String amount) {
    }
    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
