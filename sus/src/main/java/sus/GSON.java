package sus;
import com.google.gson.Gson;

public class GSON {

	public static void main(String[] args) {
        Gson gson = new Gson();
        
        // Преобразование объекта в JSON
        MyObject obj = new MyObject();
        String json = gson.toJson(obj);
        System.out.println(json);
        
        // Преобразование JSON в объект
        MyObject newObj = gson.fromJson(json, MyObject.class);
        System.out.println(newObj);
    }
    
    static class MyObject {
        String name = "John";
        int age = 30;
    }
}