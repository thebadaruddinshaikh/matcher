import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Match {
    public HashSet<String> matchEx(String f1, String f2, int sh1, int col1, int sh2, int col2)
    {

        HashSet<String> set = new HashSet<>();
        HashSet<String> ansSet = new HashSet<>();

        Iterator<Row> ritr;

        String masterPath = f1;
        int mastersheetnum = sh1;
        int masterCol = col1;


        String slavePath = f2;
        int slavesheetnum = sh2;
        int slavecol = col2;

        try
        {
            masterPath = masterPath.replace("\\","/");
            File masterfile = new File(masterPath);
            FileInputStream masterfis = new FileInputStream(masterfile);
            XSSFWorkbook masterwb = new XSSFWorkbook(masterfis);
            XSSFSheet mastersheet = masterwb.getSheetAt(mastersheetnum);

            ritr = mastersheet.iterator();
            while (ritr.hasNext())
            {
                Row row = ritr.next();
                Cell cell = row.getCell(masterCol);
                set.add((cell.getStringCellValue()).trim());
            }

            slavePath = slavePath.replace("\\","/");
            File slavefile = new File(slavePath);
            FileInputStream slavefis = new FileInputStream(slavefile);
            XSSFWorkbook slavewb = new XSSFWorkbook(slavefis);
            XSSFSheet slavesheet = slavewb.getSheetAt(slavesheetnum);


            //slaveFile
            ritr = slavesheet.iterator();
            while(ritr.hasNext()) {
                Row row = ritr.next();
                Cell cell = row.getCell(slavecol);
                String val = cell.getStringCellValue().trim();
                if (!val.regionMatches(true, 0, "MSG", 0, 3))
                    continue;
                if (!set.contains(val))
                    ansSet.add(val);
            }
            return ansSet;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new HashSet<>();
    }
}
