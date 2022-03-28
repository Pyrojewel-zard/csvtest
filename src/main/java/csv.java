
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class csv {
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
        arr=str.split("-");
        return Integer.valueOf(arr[0]);
    }
    public static int WeekLength(String str){
        str=str.replace("[周]","");
        String[]arr;
        arr=str.split("-");
        if(arr.length==1)return 1;
        else {
            return Integer.valueOf(arr[1])-Integer.valueOf(arr[0]);
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
    public static void main(String[] args) throws IOException {
        Workbook workbook = null;
        workbook = readExcel("C:\\Users\\Pyrojewel\\Desktop\\hellow.xls");
        Sheet sht0 = workbook.getSheetAt(0);
        ArrayList<CourseModel> courseModels=new ArrayList<>();
        for (int j = 1; j <= 5; j++) {//表示列
            for (int i = 3; i <= 16; i++) {//表示行，下标从0开始，需要减一
                Row r = sht0.getRow(i);
                String[] arr;
                arr = r.getCell(1).toString().trim().split("\n");
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
        System.out.println(courseModels.get(k).name+courseModels.get(k).teacher+courseModels.get(k).weekStart+courseModels.get(k).weekLength+courseModels.get(k).place);
    }}
}