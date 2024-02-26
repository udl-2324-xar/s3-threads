public class ExampleThread2 {
    public static void main(String[] args){
        System.out.println("Inici Exemple 2 de threads");
        Thread t;
        int i;

        for (i=1; i<=5; i++) {
            t = new Thread(new UnThread(i), "thread-" + i);
            t.start();
        }
        System.out.println("Fi void main");
    }

    private static class UnThread implements Runnable{

        int id;

        public UnThread(int id){
            this.id = id;
        }

        public void run(){
//            String name = Thread.currentThread().getName();
            try {
                for(int j = 1; j<=3; j++){
                    System.out.println("Soc el thread " + id + " i la j val:" + j);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

        }

    }
}

