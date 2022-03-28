/*
*针对小破邮课表的数据处理！！！！！
* */
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class csv {

    final static int dataStart=3;
    final static int dataEnd=16;//针对具体表格的可用数据的开始与结束

    private static String getType(Object a) {
        return a.getClass().toString();
    }

    //读取Excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    public static int Weeks(String str){
        str=str.replace("[周]","");
        String[]arr;

        if(str.contains(",")){
            arr=str.split(",");
        }else{
            arr=str.split("-");
        }
        return Integer.valueOf(arr[0]);
    }
    public static int WeekLength(String str){
        str=str.replace("[周]","");
        String[]arr;
        if(str.contains(",")){
            arr=str.split(",");
        }else{
            arr=str.split("-");
        }
        if(arr.length==1)return 1;
        else {
            return Integer.valueOf(arr[arr.length-1])-Integer.valueOf(arr[0]);
        }
    }
    public static int Days(String str){
        str=str.replace("[","");
        str=str.replace("节","");
        str=str.replace("[","");
        String[]arr;
        arr=str.split("-");
        return Integer.valueOf(arr[0]);
    }
    public static int DayLength(String str){
        str=str.replace("[","");
        str=str.replace("节","");
        str=str.replace("]","");
        String[]arr;
        arr=str.split("-");
        int a=Integer.valueOf(arr[arr.length-1])-Integer.valueOf(arr[0]);
//        System.out.print(a);
        return a;
    }
    //如何解析？？！！！如何构建？？！！
    public static String Change(String string){
        String str=string.replace(")\n(",")(").trim();
        str=str.replace("）\n（",")(");
        str=str.replace("）\n(",")(");
        str=str.replace(")\n（",")(");
        return str;
    }
    public static void Course(String path){
        Workbook workbook = null;
        workbook = readExcel(path);
        Sheet sht0 = workbook.getSheetAt(0);
        ArrayList<CourseModel> courseModels=new ArrayList<>();
        for (int j = 1; j <= 5; j++) {//表示列从星期一到星期五
            for (int i = dataStart; i <= dataEnd; i++) {//表示行，下标从0开始，需要减一
                Row r = sht0.getRow(i);
                String[] arr;
                //对体育专项突然六行的特殊数据处理……
                arr = Change(r.getCell(j).toString()).split("\n");
                if(arr.length==1)continue;
                int len=0;
                while(len<arr.length) {
                    CourseModel courseModel = new CourseModel();
                    courseModel.setName(arr[len++]);//0
                    courseModel.setTeacher(arr[len++]);//1
                    courseModel.setWeekStart(Weeks(arr[len]));//2
                    courseModel.setWeekLength(WeekLength(arr[len++]));//2
                    courseModel.setDayOfWeek(j);
                    courseModel.setPlace(arr[len++]);
                    courseModel.setTimeStart(Days(arr[len]));
                    courseModel.setTimeLength(DayLength(arr[len++]));
                    if(!courseModels.contains(courseModel)) {
                        courseModels.add(courseModel);
                    }
                }
            }
        }
        for(int k=0;k<courseModels.size();k++){
            courseModels.get(k).printAll();
    }
    }

    public static void main(String[] args) {
        String path="C:\\Users\\Pyrojewel\\Desktop\\hello.xls";
        Course(path);
    }
}