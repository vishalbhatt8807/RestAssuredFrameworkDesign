package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="Data")
    public Object[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir")+"//testdata//excelData.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);
        int rowCount = excelUtility.getRowCount("Sheet1");
        int cellCount = excelUtility.getCellCount("Sheet1",1);
        String[][] apidata = new String[rowCount][cellCount];
        for(int i =1 ; i <= rowCount;i++){
            for (int j = 0; j< cellCount;j++){
                apidata[i-1][j] = excelUtility.getCellData("Sheet1",i,j);
            }
        }
        return apidata;
    }

    @DataProvider(name="Usernames")
    public Object[][] getUserNames() throws IOException {

        String path = System.getProperty("user.dir")+"//testdata//excelData.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);
        int rowCount = excelUtility.getRowCount("Sheet1");
        System.out.println(excelUtility.getCellData("Sheet1", 1, 1));
        String apidata[][]=new String[rowCount][1];
        for(int i =1 ; i <= rowCount;i++){
            for(int j =1 ; j <= 1;j++) {
                apidata[i-1][j-1] = excelUtility.getCellData("Sheet1", i, j);
            }
        }
        System.out.println(apidata[0][0] + apidata[1][0] + apidata[2][0]);
        return apidata;
    }
}
