package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class FinalWork {

    public static void main(String[] args) {
        writeData(inputData());
    }


    public static void writeData(HashMap<String, String> data) {
        String fileName = data.get("lastName") + ".txt";
        StringBuilder sb = new StringBuilder();
        for (String s : data.keySet()) {
            sb.append(data.get(s));
            sb.append(" ");
        }
        writeToFile(sb.toString(), fileName);
    }
    public static void writeToFile(String data, String path) {
        try(FileWriter fw = new FileWriter(path, true)) {
            fw.append(data);
            fw.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static HashMap<String, String>  inputData() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите данные через пробел: Фамилия Имя Отчество номер_телефона");
            System.out.println("Разделяйте данные ровно одним пробелом!");
            System.out.println("Номер_телефона вводите цифрами без разделителей");
            String personalData = scanner.nextLine();
            String[] data = personalData.split(" ");
            try{
                return processInput(data);
            }
            catch (MyDataSizeException ex){
                System.out.println(ex);
            }
            catch (MyDataException ex){
                System.out.println(ex);
            }
        }
    }


    private static HashMap<String, String>  processInput(String[] arr) throws MyDataSizeException, MyDataException{
        if (arr == null)
            throw new NullPointerException("Ошибка ввода! Необходимо ввести данные. Введите заново");
        if (arr.length < 4)
            throw new MyDataSizeException("Ошибка ввода! Вы ввели мало данных:" + arr.length + ". Введите данные заново");
        if (arr.length > 4)
            throw new MyDataSizeException("Ошибка ввода! Вы ввели слишком много данных:" + arr.length + ". Введите данные заново");
        try{
            Long number = Long.parseLong(arr[3]);
        }
        catch (NumberFormatException e){
            throw new MyDataException("Некорректный формат номера телефона:" +  arr[3] + ". Введите данные заново");
        }
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("lastName", arr[0]);
        dataMap.put("firstName", arr[1]);
        dataMap.put("patronymic", arr[2]);
        dataMap.put("phone", arr[3]);
        return dataMap;
    }
}




abstract class MyException extends Exception{
    public MyException(String message){
        super(message);
    }
}

class MyDataSizeException extends MyException{
    public MyDataSizeException(String message){
        super(message);
    }
}

class MyDataException extends MyException{
    public MyDataException(String message){
        super(message);
    }
}


