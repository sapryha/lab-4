import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Bakery implements Lock {

    private int threads = 10;
    AtomicIntegerArray ticket = new AtomicIntegerArray(threads); 
    AtomicIntegerArray choosing = new AtomicIntegerArray(threads); 
    private int tid;

    public Bakery(int n, int tid){
        this.tid = tid;
        for(int i = 0; i < threads; ++i)
        {
            ticket.set(i, 0);
            choosing.set(i, 0);
        }
    }

    private int maxValue(AtomicIntegerArray ticket) {
        int max = ticket.get(0);
        for (int ktr = 0; ktr < ticket.length(); ktr++) {
            if (ticket.get(ktr) > max ) {
                max =ticket.get(ktr);
            }
        }
        return max;
    }
    @Override
    public void lock()
    {
        choosing.set(tid, 1);
        ticket.set(tid, maxValue(ticket) + 1);
        choosing.set(tid, 0);
        for(int i = 0; i < ticket.length(); ++i)
        {
            if(i != tid)
            {
                while(choosing.get(i) == 1){} 
                while(ticket.get(i) != 0 &&( ticket.get(tid) > ticket.get(i) || (ticket.get(tid) == ticket.get(i) && tid > i)))
                {}
            }
        }
    }
    @Override
    public void unlock()
    {
        ticket.set(tid, 0);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean tryLock() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
