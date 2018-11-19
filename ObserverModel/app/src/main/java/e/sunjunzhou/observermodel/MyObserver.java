package e.sunjunzhou.observermodel;

import java.util.Observable;
import java.util.Observer;

public class MyObserver implements Observer {
    private int i;
    private MyPerson myPerson;
    public MyObserver(int i){
        System.out.println("我是观察者---->"+i);
        this.i=i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    public MyPerson getMyPerson() {
        return myPerson;
    }




    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("观察者---->"+i+"得到更新！");
        this.myPerson=(MyPerson)observable;
        System.out.println(((MyPerson)observable).toString());

    }
}
