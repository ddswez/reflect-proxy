package reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 用反射的方式过滤字段操作代码
 * Created by lpf on 17/4/21.
 */
public class ReflectTest4 {
    private static List<Info> sexList = new ArrayList<Info>();
    public static void main(String[] args) throws Exception {
        //随意new出两个对象,将性别为女的改成男的
        Info info1 = new Info();
        info1.setSex("male");
        Info info2 = new Info();
        info2.setSex("female");
        add2List(info1);
        add2List(info2);
        System.out.println(sexList.size()); //1
        System.out.println(sexList.get(0).getSex()); //male
    }

    private static void add2List(Info obj) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        //对所有字段进行遍历操作、提取有用的字段信息
        for (Field f : fields) {
            f.setAccessible(true);
            System.out.println(f.getType());//java.lang.String/java.lang.String
            System.out.println(f.getName()); //username/sex
            if (f.getType() == String.class && f.getName().equals("sex")) {
                if (f.get(obj).equals("female")) { //女性身份
                    f.set(obj, "male"); //改为男性 //add to list
                    sexList.add(obj);
                }
            }
        }
    }
}


class Info {
    private String username; //用户昵称
    private String sex; //性别

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

