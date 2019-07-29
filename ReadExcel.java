import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class ReadExcel {

    public static void main(String[] args) throws IOException {
        File excelFile = new File("C:\\Users\\avikkada\\Desktop\\Title Updates.xls");
        FileInputStream fis = new FileInputStream(excelFile);

        // we create an XSSF Workbook object for our XLSX Excel File
        Workbook workBook = new HSSFWorkbook(fis);

        File tempcsv = new File("Temp_CSV.csv");
        FileWriter csvWriter = new FileWriter(tempcsv);

        // we get first sheet
        Sheet sheet = workBook.getSheetAt(workBook.getNumberOfSheets() - 1);

        // we iterate on rows

        for (Row row : sheet) {
            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.print(cell.toString() + ";");

                if(cell.toString().contains(","))
                {
                    csvWriter.append("\"" + cell.toString() + "\"" + ",");
                }
                else
                {
                    csvWriter.append(cell.toString() + ",");
                }
            }

            System.out.println();
            csvWriter.append("\n");

            csvWriter.flush();
        }

        workBook.close();
        fis.close();
    }

}