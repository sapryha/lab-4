import java.util.concurrent.locks.Lock;


public class Calculate {
    private int i = 0;
    private int limit = (int) Math.pow(10, 4);
    Lock lock;


    public void PrimePrint(Lock lock) throws Exception {
        if (this.lock == null) {
            this.lock = lock;
        }
        while (i < limit) {
            i = getAndIncrement();
            isPrime(i);
        }
    }

    private int getAndIncrement() throws Exception {
        try {
            i += 1;
            return i;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private boolean isPrime(int currentNumber) throws Exception {
        try {
            lock.lock();
            int dividers = 0;
            for (int j = 1; j <= currentNumber; j++) {
                if (currentNumber % j == 0) {
                    dividers++;
                }
            }
            if (dividers <= 2) {
                System.out.println(currentNumber);
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            lock.unlock();
        }
    }
}