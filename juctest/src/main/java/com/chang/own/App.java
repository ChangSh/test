/*
public class Singleton {
    private Singleton() {
    }

    public static class Holder {
        // 这里的私有没有什么意义
        */
/* private *//*
static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        // 外围类能直接访问内部类（不管是否是静态的）的私有变量
        return Holder.instance;
    }
}*/
