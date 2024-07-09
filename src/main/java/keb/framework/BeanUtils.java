package keb.framework;

public class BeanUtils {


    //빈 이름을 만들 때 첫 글자를 소문자로 바꾸는 메소드
    public static String convertBeanName(String beanName) {
        if (beanName == null || beanName.isEmpty()) {
            return beanName;
        }
        char firstChar = beanName.charAt(0);
        if (Character.isUpperCase(firstChar)) {
            return Character.toLowerCase(firstChar) + beanName.substring(1);
        }
        return beanName;
    }
}
