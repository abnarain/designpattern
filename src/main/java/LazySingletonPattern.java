//class InnerStaticSingleton
//{
//    private InnerStaticSingleton(){ }
//
//    private static class Impl
//    {
//        private static final InnerStaticSingleton
//        INSTANCE = new InnerStaticSingleton();
//    }
//
//    public InnerStaticSingleton getInstance()
//    {
//        return Impl.INSTANCE; //avoids the problem of synchronization
//        //guarantees that you will get once instance whatever happens
//    }
//}
//class LazySingleton
//{
//    private static LazySingleton instance;
//    private LazySingleton()
//    {
//        System.out.println("initializing lazy singleton");
//    }
//
////    public static synchronized LazySingleton getInstance()
////    {
////        if (instance == null)
////        {
////            instance= new LazySingleton();
////        }
////        return instance;
////
////    }
//
//    //double checked locking: two null checks used
//    public static LazySingleton getInstance()
//    {
//        if (instance == null)
//        {
//            synchronized (LazySingleton.class)
//            {
//                if (instance==null)
//                {
//                    instance = new LazySingleton();
//                }
//            }
//        }
//    }
//}
//
//public class LazySingletonPattern {
//}
