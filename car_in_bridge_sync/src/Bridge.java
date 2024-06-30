import bridge.CarExtends;
import bridge.CarImplements;

import java.util.concurrent.Semaphore;

public class Bridge {
    public static void main(String[] args) throws Exception {

          Semaphore semaphore = new Semaphore(1);

          CarImplements carNew = new CarImplements(semaphore,"esquerda", 0);
          CarImplements carTwo = new CarImplements(semaphore,"direita", 1);
          CarImplements carThree = new CarImplements(semaphore,"esquerda", 2);

          Thread t1 = new Thread(carNew);
          Thread t2 = new Thread(carTwo);
          Thread t3 = new Thread(carThree);

          t1.start();
          t2.start();
          t3.start();


        // Uncomment the code below to see the difference between the two
        // implementations
      /*  for (int i = 0; i < 5; i++) {
            new CarExtends("esquerda", i).start();
            new CarExtends("direita", i).start();
            }
*/
        }

    }

