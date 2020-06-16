package tom.jiafei;

public class Car {
    String data;
    String number;
    String carname;

    public String getName() {
        try {
            carname = new String(carname.getBytes("iso-8859-1"),"utf-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return carname;
    }

    public void setName(String name) {
        this.carname = name;
    }

    public String getNumber() {
        try {
            number = new String(number.getBytes("iso-8859-1"),"utf-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getData() {
        try {
           data = new String(data.getBytes("iso-8859-1"),"utf-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
