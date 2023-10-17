package sus;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVWriter;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class JSONtoCSV {
    public static void main(String[] args) {
        String jsonFilePath = "C:\\Users\\Shurukov\\Desktop\\Новая папка (3)\\import.json";
        String csvFilePath = "C:\\Users\\Shurukov\\Desktop\\Новая папка (3)\\output.csv";
        try {
            // Чтение JSON файла
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(new FileReader(jsonFilePath)).getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("rows");

            // Создание CSV файла с кодировкой Windows-1251 и разделителем ";"
            FileOutputStream fos = new FileOutputStream(csvFilePath);
            Writer csvWriter = new OutputStreamWriter(fos, Charset.forName("Windows-1251"));
            CSVWriter writer = new CSVWriter(csvWriter, ';', CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

         // Запись заголовков
         JsonObject firstObject = jsonArray.get(0).getAsJsonObject();
         Set<Map.Entry<String, JsonElement>> entrySet = firstObject.entrySet();
         String[] headers = new String[entrySet.size()];
         int index = 0;
         for (Map.Entry<String, JsonElement> entry : entrySet) {
             headers[index] = entry.getKey();
             index++;
         }
         writer.writeNext(headers);

      // Запись данных
         for (int i = 0; i < jsonArray.size(); i++) {
             JsonObject dataObject = jsonArray.get(i).getAsJsonObject();
             String[] values = new String[headers.length];
             for (int j = 0; j < headers.length; j++) {
                 // Проверяем, что значение не является null, прежде чем вызывать toString()
                 if (dataObject.has(headers[j]) && !dataObject.get(headers[j]).isJsonNull()) {
                     values[j] = dataObject.get(headers[j]).getAsString();
                 } else {
                     values[j] = ""; // Устанавливаем пустую строку для null-значений
                 }
             }
             writer.writeNext(values);
         }


            writer.close();
            csvWriter.close();
            System.out.println("Преобразование JSON в CSV завершено успешно.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
