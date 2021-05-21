package sluzba.pretrage;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class BinarnaPretraga {

    public static int find(ArrayList<Integer> array, int target) {
        return binarySearch(array,target,0,array.size()-1);
    }

    public static int binarySearch(ArrayList<Integer> array, int target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high)/2;

        if (array.get(mid) == target) {
            return mid;
        }
        else if (array.get(mid) > target) {
            return binarySearch(array,target,low,mid-1);
        }
        else {
            return binarySearch(array,target,mid+1,high);
        }
    }
//    public static <T> int find(ArrayList<T> array, String target, String attribute) {
//        return binarySearch(array, target, attribute, 0,array.size()-1);
//    }
//
//    public static <T> int binarySearch(ArrayList<T> array, String target, String attribute, int low, int high)  {
//        if (low > high) {
//            return -1;
//        }
//
//        try {
//            Field targetField = array.get(0).getClass().getField(attribute);
//            String targetValue = targetField.get(target).toString();
//
//            int mid = (low + high)/2;
//
//            Field field = array.get(0).getClass().getField(attribute);
//            String value = field.get(array.get(mid)).toString();
//
//            if (value.equals(targetValue)) {
//                return mid;
//            }
//            else if (value.compareTo(targetValue) > 0) {
//                return binarySearch(array,target, attribute, low,mid-1);
//            }
//            else {
//                return binarySearch(array,target, attribute, mid+1,high);
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }

}
