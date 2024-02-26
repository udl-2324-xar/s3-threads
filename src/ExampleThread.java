public class ExampleThread {
    public static void main(String[] args){
        System.out.println("Inici Exemple 1 de threads");
        Thread t;

        for (int i=1; i<=5; i++) {
            t = new Thread(new UnThread(), "thread-" + i);
            t.start();
        }
        System.out.println("Fi void main");
    }

    private static class UnThread implements Runnable{

        public void run(){
            String name = Thread.currentThread().getName();
            try {
                for(int j = 1; j<=3; j++){
                    System.out.println("Soc el thread " + name + " i la j val:" + j);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

        }

    }
}
