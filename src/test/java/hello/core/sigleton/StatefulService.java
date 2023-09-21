package hello.core.sigleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    // 상태를 유지할 경우 => 문제점 발생 : StatefulService2 참고
    public void order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}
