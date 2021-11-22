
package Helper;

import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author PC
 */
public class Helper {
    
    public void tabletoexcel (JTable t, String nombre_file) throws IOException{
        
        try{
            
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("reporte");
            Row rowCol = sheet.createRow(0);
            
            for (int i = 0; i < t.getColumnCount(); i++) {
                Cell cell = rowCol.createCell(i);
                cell.setCellValue(t.getColumnName(i));
            }
            
            for (int i = 0; i < t.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < t.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    if (t.getValueAt(i, j) != null) {
                        cell.setCellValue(t.getValueAt(i, j).toString());
                    }
                    
                }
                
            }
            
            FileOutputStream out = new FileOutputStream(new File("d:\\Downloads\\"+nombre_file+".xlsx"));
            wb.write(out);
            wb.close();
            out.close();
            
        }catch(FileNotFoundException e){
            System.out.println(e);  
        }catch(IOException io){
            System.out.println(io);  
        }

    }
    
    
    public byte[] get_imagen(String Ruta) {
        File imagen = new File(Ruta);
        try {
            byte[] icono = new byte[(int) imagen.length()];
            InputStream input = new FileInputStream(imagen);
            input.read(icono);
            return icono;
        } catch (Exception ex) {
            return null;
        }
    }
    
}
