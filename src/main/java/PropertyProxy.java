////
//// all for motivationclass Creature
////{
////    private int agility;
////
////    public Creature() {
////        agility=123; // this isnt recorded as it doesnt use setter, getter
////        // better idea to make it property proxy (in c++, java, c#): replace field with something else
////
////    }
////
////    public int getAgility() {
////        return agility;
////    }
////
////    public void setAgility(int agility) {
////        this.agility = agility;
////    }
////}
//
//import java.util.Objects;
//
//class Property<T>
//{
//     private T value;
//     public Property(T value)
//     {
//         this.value= value;
//     }
//
//    public T getValue() {
//        return value;
//    }
//
//    public void setValue(T value) {
//         //globally configurable dependency injection scenario for logging
//        this.value = value;
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Property<?> property = (Property<?>) o;
//        return value != null
//        ? value.equals(property.value) : property.value ==null;
//
//    }
//
//    /*
//        @Override
//        public boolean equals(Object o)
//        {
//            if (this ==o) return true;
//            if (o ==null || getClass() != o.getClass()) return false;
//            Property<?> property = (Property<?>) o;
//            return value != null ?
//                    value.equals(property.value):
//                    property.value == null;
//        }
//        */
//    @Override
//    public int hashCode() {
//        return value != null? value.hashCode(): 0;
//    }
//}
//class Creature
//{
//    private Property<Integer> agility = new Property<Integer>(Integer(10));
//
//    public int getAgility() {
//
//        return agility.getValue();
//    }
//
//    public void setAgility(int value) {
//        agility.setValue(value);
//    }
//}
//public class PropertyProxy {
//    public static void main(String[] args) {
//        int x=0;
//        //x =5; //how do we log when we assign?
//
//        Creature creature = new Creature();
//        creature.setAgility(12);
//    }
//
//}
