import java.util.concurrent.locks.Lock;

public class Numbers extends Thread {

    public Numbers(int thrNumb, int id, Calculate calc) {
        lock = new Bakery(thrNumb, id);
        this.calc = calc;
    }

    private Lock lock;
    Calculate calc;

    @Override
    public void run() {
        try {
            calc.PrimePrint(lock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
