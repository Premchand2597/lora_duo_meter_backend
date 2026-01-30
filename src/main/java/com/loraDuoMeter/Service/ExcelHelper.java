
package com.loraDuoMeter.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.loraDuoMeter.Entity.BuildingEntity;
import com.loraDuoMeter.Entity.GatewayEntity;
import com.loraDuoMeter.Entity.MeterEntity;
import com.loraDuoMeter.Entity.ResidentEntity;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String CSV_TYPE = "text/csv";

    // --- CHECK FILE FORMATS ---
  
    public static boolean hasExcelFormat(MultipartFile file) {
        if (file == null) return false;

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        return (contentType != null && TYPE.equals(contentType))
                || (fileName != null && fileName.toLowerCase().endsWith(".xlsx"));
    }

    
    public static boolean hasCSVFormat(MultipartFile file) {
        if (file == null) return false;

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        return (contentType != null && "text/csv".equals(contentType))
                || (fileName != null && fileName.toLowerCase().endsWith(".csv"));
    }


    // --- NEW: CSV PARSING LOGIC (This is what you were missing) ---
    public static List<BuildingEntity> csvToBuildings(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            List<BuildingEntity> buildings = new ArrayList<>();
            String line = "";
            int rowNumber = 0;

            while ((line = fileReader.readLine()) != null) {
                // Skip Header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                // Split by comma, ignoring commas inside quotes (e.g., "MG Road, Bangalore")
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                BuildingEntity building = new BuildingEntity();

                // Map Columns (Matches your Excel order)
                if (data.length > 0) building.setName(cleanCSVValue(data[0]));
                if (data.length > 1) building.setFloors(parseInt(data[1]));
                if (data.length > 2) building.setFlats(parseInt(data[2]));
                if (data.length > 3) building.setArea(cleanCSVValue(data[3]));
                if (data.length > 4) building.setFloorDimension(cleanCSVValue(data[4]));
                if (data.length > 5) building.setAddress(cleanCSVValue(data[5])); 
                if (data.length > 6) building.setState(cleanCSVValue(data[6]));
                if (data.length > 7) building.setCountry(cleanCSVValue(data[7]));
                if (data.length > 8) building.setLatitude(cleanCSVValue(data[8]));
                if (data.length > 9) building.setLongitude(cleanCSVValue(data[9]));
                if (data.length > 10) building.setDateTime(cleanCSVValue(data[10]));

                // Only add valid rows
                if (building.getName() != null && !building.getName().isEmpty()) {
                    buildings.add(building);
                }
            }
            return buildings;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    // Helper: Remove quotes from CSV values
    private static String cleanCSVValue(String value) {
        if (value != null && value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        return value != null ? value.trim() : "";
    }

    // Helper: Parse integers safely
    private static int parseInt(String value) {
        try {
            return Integer.parseInt(cleanCSVValue(value));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // --- EXISTING EXCEL LOGIC BELOW ---
    
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) return cell.getLocalDateTimeCellValue().toString();
                double val = cell.getNumericCellValue();
                return (val == (long) val) ? String.valueOf((long) val) : String.valueOf(val);
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            default: return "";
        }
    }

    // 1. PARSE BUILDINGS (EXCEL)
 // Make sure these imports are at the top of your file:
 // import java.time.LocalDate;
 // import java.time.LocalDateTime;
 // import java.time.LocalTime;
 // import java.time.format.DateTimeFormatter;
 // import org.apache.poi.ss.usermodel.DataFormatter;

 public static List<BuildingEntity> excelToBuildings(InputStream is) {
     try {
         Workbook workbook = new XSSFWorkbook(is);
         Sheet sheet = workbook.getSheetAt(0);
         List<BuildingEntity> buildings = new ArrayList<>();
         int rowNumber = 0;

         // Formatter for standardized DB format
         DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

         for (Row currentRow : sheet) {
             if (rowNumber == 0) { rowNumber++; continue; } 

             BuildingEntity building = new BuildingEntity();
             for (int cellIdx = 0; cellIdx <= 10; cellIdx++) {
                 Cell currentCell = currentRow.getCell(cellIdx, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                 String cellValue = getCellValueAsString(currentCell);

                 switch (cellIdx) {
                     case 0: building.setName(cellValue); break;
                     case 1: try { building.setFloors((int) Double.parseDouble(cellValue)); } catch (Exception e) { building.setFloors(0); } break;
                     case 2: try { building.setFlats((int) Double.parseDouble(cellValue)); } catch (Exception e) { building.setFlats(0); } break;
                     case 3: building.setArea(cellValue); break;
                     case 4: building.setFloorDimension(cellValue); break;
                     case 5: building.setAddress(cellValue); break;
                     case 6: building.setState(cellValue); break;
                     case 7: building.setCountry(cellValue); break;
                     case 8: building.setLatitude(cellValue); break;
                     case 9: building.setLongitude(cellValue); break;
                     
                     // --- UPDATED DATE LOGIC START ---
                     case 10: 
                         String finalDateTime = "";
                         try {
                             LocalDateTime now = LocalDateTime.now();
                             
                             // 1. Get raw string specifically for Date handling (safest way)
                             String dateStr = "";
                             if (currentCell != null) {
                                 DataFormatter dataFormatter = new DataFormatter();
                                 dateStr = dataFormatter.formatCellValue(currentCell).trim();
                             }

                             // 2. Logic to determine format
                             if (dateStr.isEmpty()) {
                                 // CASE A: Empty -> Use Current System Time
                                 finalDateTime = now.format(dbFormatter);
                             } 
                             else if (!dateStr.contains(":")) {
                                 // CASE B: Date only (e.g., "2026-01-01") -> Append Current Time
                                 LocalDate datePart;
                                 try {
                                     // Try standard ISO
                                     datePart = LocalDate.parse(dateStr); 
                                 } catch (Exception e) {
                                     try {
                                         // Try Alternative format
                                         datePart = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                     } catch (Exception ex) {
                                         // Fallback to today if parsing fails
                                         datePart = LocalDate.now(); 
                                     }
                                 }
                                 // Combine Excel Date + Current Clock Time
                                 finalDateTime = datePart.atTime(LocalTime.now()).format(dbFormatter);
                             } 
                             else {
                                 // CASE C: Date & Time exists -> Fix format (remove 'T', etc.)
                                 if(dateStr.contains("T")) dateStr = dateStr.replace("T", " ");
                                 try {
                                     LocalDateTime parsed = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                     finalDateTime = parsed.format(dbFormatter);
                                 } catch (Exception e) {
                                     // If strict parsing fails, keep original or default to NOW
                                     finalDateTime = dateStr; 
                                 }
                             }
                         } catch (Exception e) {
                             // Ultimate Fail-safe
                             finalDateTime = LocalDateTime.now().format(dbFormatter);
                         }
                         building.setDateTime(finalDateTime); 
                         break;
                     // --- UPDATED DATE LOGIC END ---
                         
                     default: break;
                 }
             }
             if (building.getName() != null && !building.getName().isEmpty()) {
                 buildings.add(building);
             }
         }
         workbook.close();
         return buildings;
     } catch (IOException e) {
         throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
     }
 }

    // 2. PARSE RESIDENTS (EXCEL)
   /* public static List<ResidentEntity> excelToResidents(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<ResidentEntity> residents = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) { rowNumber++; continue; }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                ResidentEntity resident = new ResidentEntity();
                int cellIdx = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0: break; // Skip ID
                        case 1: resident.setBuildingName(getCellValueAsString(currentCell)); break;
                        case 2: resident.setBuildingId(getCellValueAsString(currentCell)); break;
                        case 3: resident.setFloorNo(getCellValueAsString(currentCell)); break;
                        case 4: resident.setFlatNo(getCellValueAsString(currentCell)); break;
                        case 5: resident.setFullName(getCellValueAsString(currentCell)); break;
                        case 6: resident.setType(getCellValueAsString(currentCell)); break;
                        case 7: resident.setMobile(getCellValueAsString(currentCell)); break;
                        case 8: resident.setAltPhone(getCellValueAsString(currentCell)); break;
                        case 9: resident.setEmail(getCellValueAsString(currentCell)); break;
                        case 10: resident.setAddress(getCellValueAsString(currentCell)); break;
                        case 11: resident.setState(getCellValueAsString(currentCell)); break;
                        case 12: resident.setCountry(getCellValueAsString(currentCell)); break;
                     // --- UPDATED DATE LOGIC FOR RESIDENTS ---
                        case 13: 
                            String finalDateTime = "";
                            try {
                                // Standard Database Format
                                DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime now = LocalDateTime.now();
                                
                                // 1. Get raw string safely
                                String dateStr = "";
                                if (currentCell != null) {
                                    DataFormatter dataFormatter = new DataFormatter();
                                    dateStr = dataFormatter.formatCellValue(currentCell).trim();
                                }

                                // 2. Logic to determine format
                                if (dateStr.isEmpty()) {
                                    // CASE A: Empty -> Use Current System Time
                                    finalDateTime = now.format(dbFormatter);
                                } 
                                else if (!dateStr.contains(":")) {
                                    // CASE B: Date only (e.g., "2026-01-01") -> Append Current Time
                                    LocalDate datePart;
                                    try {
                                        datePart = LocalDate.parse(dateStr); 
                                    } catch (Exception e) {
                                        try {
                                            datePart = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                        } catch (Exception ex) {
                                            datePart = LocalDate.now(); 
                                        }
                                    }
                                    finalDateTime = datePart.atTime(LocalTime.now()).format(dbFormatter);
                                } 
                                else {
                                    // CASE C: Date & Time exists -> Fix format (remove 'T', clean up)
                                    if(dateStr.contains("T")) dateStr = dateStr.replace("T", " ");
                                    try {
                                        LocalDateTime parsed = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                        finalDateTime = parsed.format(dbFormatter);
                                    } catch (Exception e) {
                                        finalDateTime = dateStr; 
                                    }
                                }
                            } catch (Exception e) {
                                finalDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                            }
                            resident.setDateTime(finalDateTime); 
                            break;
                    }
                    cellIdx++;
                }
                residents.add(resident);
            }
            workbook.close();
            return residents;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }*/

 // Paste this inside ExcelHelper.java, replacing the existing excelToResidents method

//Paste this inside ExcelHelper.java, replacing the previous excelToResidents

public static List<ResidentEntity> excelToResidents(InputStream is) {
  try {
      Workbook workbook = new XSSFWorkbook(is);
      Sheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rows = sheet.iterator();
      List<ResidentEntity> residents = new ArrayList<>();
      int rowNumber = 0;

      while (rows.hasNext()) {
          Row currentRow = rows.next();
          if (rowNumber == 0) { rowNumber++; continue; }

          Iterator<Cell> cellsInRow = currentRow.iterator();
          ResidentEntity resident = new ResidentEntity();
          int cellIdx = 0;

          while (cellsInRow.hasNext()) {
              Cell currentCell = cellsInRow.next();
              
              // --- MAPPING FOR "residents_without_resid_buildid.xlsx" ---
              // WE ADDED .trim() TO FIX THE "Building Not Found" ISSUE
              switch (cellIdx) {
                  case 0: resident.setBuildingName(getCellValueAsString(currentCell).trim()); break; 
                  case 1: resident.setFloorNo(getCellValueAsString(currentCell).trim()); break;      
                  case 2: resident.setFlatNo(getCellValueAsString(currentCell).trim()); break;       
                  case 3: resident.setFullName(getCellValueAsString(currentCell).trim()); break;
                  case 4: resident.setType(getCellValueAsString(currentCell).trim()); break;
                  case 5: resident.setMobile(getCellValueAsString(currentCell).trim()); break;
                  case 6: resident.setAltPhone(getCellValueAsString(currentCell).trim()); break;
                  case 7: resident.setEmail(getCellValueAsString(currentCell).trim()); break;
                  case 8: resident.setAddress(getCellValueAsString(currentCell).trim()); break;
                  case 9: resident.setState(getCellValueAsString(currentCell).trim()); break;
                  case 10: resident.setCountry(getCellValueAsString(currentCell).trim()); break;
                  
                  // --- FIXED DATE TIME LOGIC ---
                  case 11: 
                      String finalDateTime = "";
                      try {
                          DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                          String dateStr = "";
                          
                          // 1. Get String & Trim
                          if (currentCell != null) {
                              DataFormatter dataFormatter = new DataFormatter();
                              dateStr = dataFormatter.formatCellValue(currentCell).trim();
                          }

                          // 2. Logic: If Empty OR contains "00:00" (Default Excel Time) -> Use NOW
                          if (dateStr.isEmpty() || dateStr.contains("00:00")) {
                              
                              // Parse just the Date part (ignore the 00:00:00 time)
                              LocalDate datePart;
                              try {
                                  // Handle "2026-01-01 00:00:00" by splitting or parsing
                                  String cleanDate = dateStr.split(" ")[0]; // Take only "2026-01-01"
                                  datePart = LocalDate.parse(cleanDate); 
                              } catch (Exception e) {
                                  try {
                                      datePart = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                  } catch (Exception ex) {
                                      datePart = LocalDate.now(); // Fallback to Today
                                  }
                              }
                              // Combine Date + Current Backend Time
                              finalDateTime = datePart.atTime(LocalTime.now()).format(dbFormatter);
                              
                          } else {
                              // CASE: It has a specific time (e.g., "10:30:00")
                              if(dateStr.contains("T")) dateStr = dateStr.replace("T", " ");
                              try {
                                  LocalDateTime parsed = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                  finalDateTime = parsed.format(dbFormatter);
                              } catch (Exception e) {
                                  // If parsing failed, just generate new time
                                  finalDateTime = LocalDateTime.now().format(dbFormatter);
                              }
                          }
                      } catch (Exception e) {
                          finalDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                      }
                      resident.setDateTime(finalDateTime); 
                      break;
                      
                  default: break;
              }
              cellIdx++;
          }
          if (resident.getBuildingName() != null && !resident.getBuildingName().isEmpty()) {
              residents.add(resident);
          }
      }
      workbook.close();
      return residents;
  } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
  }
}
    
//--- 1. PARSE GATEWAYS (EXCEL) - FIXED MAPPING ---
public static List<GatewayEntity> excelToGateways(InputStream is) {
    try {
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        List<GatewayEntity> gateways = new ArrayList<>();
        int rowNumber = 0;
        
        DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            if (rowNumber == 0) { rowNumber++; continue; } // Skip Header

            GatewayEntity gateway = new GatewayEntity();
            
            // MAPPING BASED ON YOUR FILE:
            // 0:Gateway ID, 1:Building Name, 2:Floor No (Was wrong before), 3:Internet Type...
            // ... 18:Date Time, 19:Building Address

            for (int cellIdx = 0; cellIdx <= 19; cellIdx++) {
                Cell currentCell = currentRow.getCell(cellIdx, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                String cellValue = getCellValueAsString(currentCell);

                switch (cellIdx) {
                    case 0: gateway.setGatewayId(cellValue); break;
                    case 1: gateway.setBuildingName(cellValue); break;
                    case 2: gateway.setFloorNo(cellValue); break;       // CORRECTED: Was BuildingId
                    case 3: gateway.setInternetType(cellValue); break;  // CORRECTED: Was FloorNo
                    case 4: gateway.setStatus(cellValue); break;
                    case 5: gateway.setMaxMeters(cellValue); break;
                    case 6: gateway.setModel(cellValue); break;
                    case 7: gateway.setManufacturer(cellValue); break;
                    case 8: gateway.setMakeYear(cellValue); break;
                    case 9: gateway.setFirmware(cellValue); break;
                    case 10: gateway.setLatitude(cellValue); break;
                    case 11: gateway.setLongitude(cellValue); break;
                    case 12: gateway.setRssiId(cellValue); break;
                    case 13: gateway.setChannelRssi(cellValue); break;
                    case 14: gateway.setChannelIndex(cellValue); break;
                    case 15: gateway.setBandwidth(cellValue); break;
                    case 16: gateway.setFrequency(cellValue); break;
                    case 17: gateway.setNetId(cellValue); break;
                    
                    // DATE TIME LOGIC
                    case 18: 
                         String finalDateTime = "";
                         try {
                             if (cellValue.isEmpty()) {
                                 finalDateTime = LocalDateTime.now().format(dbFormatter);
                             } else {
                                 if (cellValue.contains("T")) cellValue = cellValue.replace("T", " ");
                                 try {
                                     LocalDateTime parsed = LocalDateTime.parse(cellValue, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                     finalDateTime = parsed.format(dbFormatter);
                                 } catch (Exception e) {
                                     finalDateTime = cellValue; // Keep original if parse fails
                                 }
                             }
                         } catch (Exception e) {
                             finalDateTime = LocalDateTime.now().format(dbFormatter);
                         }
                         gateway.setDateTime(finalDateTime); 
                         break;

                    // CRITICAL: MAPPING ADDRESS TO BUILDING_ID TEMPORARILY
                    case 19: gateway.setBuildingId(cellValue); break; 
                }
            }
            
            if (gateway.getGatewayId() != null && !gateway.getGatewayId().isEmpty()) {
                gateways.add(gateway);
            }
        }
        workbook.close();
        return gateways;
    } catch (IOException e) {
        throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
}

// --- 2. CSV TO GATEWAYS (BACKUP) ---
public static List<GatewayEntity> csvToGateways(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
        List<GatewayEntity> gateways = new ArrayList<>();
        String line = "";
        int rowNumber = 0;
        DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while ((line = fileReader.readLine()) != null) {
            if (rowNumber == 0) { rowNumber++; continue; }
            String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            GatewayEntity gateway = new GatewayEntity();

            if (data.length > 0) gateway.setGatewayId(cleanCSVValue(data[0]));
            if (data.length > 1) gateway.setBuildingName(cleanCSVValue(data[1]));
            if (data.length > 2) gateway.setFloorNo(cleanCSVValue(data[2])); // Matches Excel order
            if (data.length > 3) gateway.setInternetType(cleanCSVValue(data[3]));
            if (data.length > 4) gateway.setStatus(cleanCSVValue(data[4]));
            if (data.length > 5) gateway.setMaxMeters(cleanCSVValue(data[5]));
            if (data.length > 6) gateway.setModel(cleanCSVValue(data[6]));
            if (data.length > 7) gateway.setManufacturer(cleanCSVValue(data[7]));
            if (data.length > 8) gateway.setMakeYear(cleanCSVValue(data[8]));
            if (data.length > 9) gateway.setFirmware(cleanCSVValue(data[9]));
            if (data.length > 10) gateway.setLatitude(cleanCSVValue(data[10]));
            if (data.length > 11) gateway.setLongitude(cleanCSVValue(data[11]));
            if (data.length > 12) gateway.setRssiId(cleanCSVValue(data[12]));
            if (data.length > 13) gateway.setChannelRssi(cleanCSVValue(data[13]));
            if (data.length > 14) gateway.setChannelIndex(cleanCSVValue(data[14]));
            if (data.length > 15) gateway.setBandwidth(cleanCSVValue(data[15]));
            if (data.length > 16) gateway.setFrequency(cleanCSVValue(data[16]));
            if (data.length > 17) gateway.setNetId(cleanCSVValue(data[17]));
            
            // Date Time
            if (data.length > 18) {
                String dateStr = cleanCSVValue(data[18]);
                String finalDateTime = "";
                try {
                     if (dateStr.isEmpty()) finalDateTime = LocalDateTime.now().format(dbFormatter);
                     else finalDateTime = dateStr; 
                } catch(Exception e) { finalDateTime = LocalDateTime.now().format(dbFormatter); }
                gateway.setDateTime(finalDateTime);
            }

            // Address -> BuildingId
            if (data.length > 19) gateway.setBuildingId(cleanCSVValue(data[19])); 

            if (gateway.getGatewayId() != null && !gateway.getGatewayId().isEmpty()) {
                gateways.add(gateway);
            }
        }
        return gateways;
    } catch (IOException e) {
        throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
}
    // 4. PARSE METERS (EXCEL)
  /*  public static List<MeterEntity> excelToMeters(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<MeterEntity> meters = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) { rowNumber++; continue; }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                MeterEntity meter = new MeterEntity();
                int cellIdx = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0: meter.setSerialNo(getCellValueAsString(currentCell)); break;
                        case 1: meter.setMacAddress(getCellValueAsString(currentCell)); break;
                        case 2: meter.setDeviceType(getCellValueAsString(currentCell)); break;
                        case 3: meter.setResidentName(getCellValueAsString(currentCell)); break;
                        case 4: meter.setResidentType(getCellValueAsString(currentCell)); break;
                        case 5: meter.setBuildingName(getCellValueAsString(currentCell)); break;
                        case 6: meter.setBuildingId(getCellValueAsString(currentCell)); break;
                        case 7: meter.setFloorNo(getCellValueAsString(currentCell)); break;
                        case 8: meter.setFlatNo(getCellValueAsString(currentCell)); break;
                        case 9: meter.setNetworkType(getCellValueAsString(currentCell)); break;
                        case 10: meter.setModel(getCellValueAsString(currentCell)); break;
                        case 11: meter.setFirmware(getCellValueAsString(currentCell)); break;
                        case 12: meter.setConnectionType(getCellValueAsString(currentCell)); break;
                        case 13: meter.setValveStatus(getCellValueAsString(currentCell)); break;
                        case 14: meter.setBatteryVoltage(getCellValueAsString(currentCell)); break;
                        case 15: meter.setDeviceRtc(getCellValueAsString(currentCell)); break;
                        case 16: meter.setPulseCount(getCellValueAsString(currentCell)); break;
                        case 17: meter.setRechargeAmount(getCellValueAsString(currentCell)); break;
                        case 18: meter.setRechargeStatus(getCellValueAsString(currentCell)); break;
                        case 19: meter.setManufacturer(getCellValueAsString(currentCell)); break;
                        case 20: meter.setMakeYear(getCellValueAsString(currentCell)); break;
                        case 21: meter.setGatewayId(getCellValueAsString(currentCell)); break;
                        case 22: meter.setDateTime(getCellValueAsString(currentCell)); break;
                        default: break;
                    }
                    cellIdx++;
                }
                meters.add(meter);
            }
            workbook.close();
            return meters;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }*/

//Inside ExcelHelper.java

// --- NEW: CSV TO METERS ---
public static List<MeterEntity> csvToMeters(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
        List<MeterEntity> meters = new ArrayList<>();
        String line = "";
        int rowNumber = 0;
        DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while ((line = fileReader.readLine()) != null) {
            if (rowNumber == 0) { rowNumber++; continue; } // Skip Header

            // Split CSV
            String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            MeterEntity meter = new MeterEntity();

            // MAPPING BASED ON YOUR FILE:
            // 0:Serial, 1:MAC, 2:DevType, 3:ResName, 4:ResType, 5:BuildName, 
            // 6:BuildAddress, 7:Floor, 8:Flat, 9:NetType, 10:Model... 
            // ... 21:GatewayID, 22:DateTime

            if (data.length > 0) meter.setSerialNo(cleanCSVValue(data[0]));
            if (data.length > 1) meter.setMacAddress(cleanCSVValue(data[1]));
            if (data.length > 2) meter.setDeviceType(cleanCSVValue(data[2]));
            if (data.length > 3) meter.setResidentName(cleanCSVValue(data[3]));
            if (data.length > 4) meter.setResidentType(cleanCSVValue(data[4]));
            if (data.length > 5) meter.setBuildingName(cleanCSVValue(data[5]));
            
            // CRITICAL: Store Address in BuildingId temporarily for Lookup
            if (data.length > 6) meter.setBuildingId(cleanCSVValue(data[6])); 

            if (data.length > 7) meter.setFloorNo(cleanCSVValue(data[7]));
            if (data.length > 8) meter.setFlatNo(cleanCSVValue(data[8]));
            if (data.length > 9) meter.setNetworkType(cleanCSVValue(data[9]));
            if (data.length > 10) meter.setModel(cleanCSVValue(data[10]));
            if (data.length > 11) meter.setFirmware(cleanCSVValue(data[11]));
            if (data.length > 12) meter.setConnectionType(cleanCSVValue(data[12]));
            if (data.length > 13) meter.setValveStatus(cleanCSVValue(data[13]));
            if (data.length > 14) meter.setBatteryVoltage(cleanCSVValue(data[14]));
            if (data.length > 15) meter.setDeviceRtc(cleanCSVValue(data[15]));
            // Skip Last Usage (16) if not in Entity
         // --- FIX: Map Index 16 (Last Usage) to Pulse Count ---
            if (data.length > 16) meter.setPulseCount(cleanCSVValue(data[16]));
            if (data.length > 17) meter.setRechargeAmount(cleanCSVValue(data[17]));
            if (data.length > 18) meter.setRechargeStatus(cleanCSVValue(data[18]));
            if (data.length > 19) meter.setManufacturer(cleanCSVValue(data[19]));
            if (data.length > 20) meter.setMakeYear(cleanCSVValue(data[20]));
            if (data.length > 21) meter.setGatewayId(cleanCSVValue(data[21]));
            
            // Date Time Fix (Col 22)
            if (data.length > 22) {
                 String dateStr = cleanCSVValue(data[22]);
                 meter.setDateTime(parseDateSafely(dateStr, dbFormatter));
            }

            if (meter.getSerialNo() != null && !meter.getSerialNo().isEmpty()) {
                meters.add(meter);
            }
        }
        return meters;
    } catch (IOException e) {
        throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
}

// --- UPDATED: EXCEL TO METERS (Matches CSV Structure) ---
public static List<MeterEntity> excelToMeters(InputStream is) {
    try {
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        List<MeterEntity> meters = new ArrayList<>();
        int rowNumber = 0;
        DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            if (rowNumber == 0) { rowNumber++; continue; }

            MeterEntity meter = new MeterEntity();
            
            // Iterate cols 0-22
            for (int cellIdx = 0; cellIdx <= 22; cellIdx++) {
                Cell currentCell = currentRow.getCell(cellIdx, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                String val = getCellValueAsString(currentCell);

                switch (cellIdx) {
                    case 0: meter.setSerialNo(val); break;
                    case 1: meter.setMacAddress(val); break;
                    case 2: meter.setDeviceType(val); break;
                    case 3: meter.setResidentName(val); break;
                    case 4: meter.setResidentType(val); break;
                    case 5: meter.setBuildingName(val); break;
                    case 6: meter.setBuildingId(val); break; // Address stored here temp
                    case 7: meter.setFloorNo(val); break;
                    case 8: meter.setFlatNo(val); break;
                    case 9: meter.setNetworkType(val); break;
                    case 10: meter.setModel(val); break;
                    case 11: meter.setFirmware(val); break;
                    case 12: meter.setConnectionType(val); break;
                    case 13: meter.setValveStatus(val); break;
                    case 14: meter.setBatteryVoltage(val); break;
                    case 15: meter.setDeviceRtc(val); break;
                 // --- FIX: Map Index 16 (Last Usage) to Pulse Count ---
                    case 16: meter.setPulseCount(val); break;
                    // case 16: Last Usage skipped
                    case 17: meter.setRechargeAmount(val); break;
                    case 18: meter.setRechargeStatus(val); break;
                    case 19: meter.setManufacturer(val); break;
                    case 20: meter.setMakeYear(val); break;
                    case 21: meter.setGatewayId(val); break;
                    case 22: meter.setDateTime(parseDateSafely(val, dbFormatter)); break;
                }
            }
            if (meter.getSerialNo() != null && !meter.getSerialNo().isEmpty()) {
                meters.add(meter);
            }
        }
        workbook.close();
        return meters;
    } catch (IOException e) {
        throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
}

// --- Helper: Date Parser with Midnight Fix ---
private static String parseDateSafely(String dateStr, DateTimeFormatter dbFormatter) {
    try {
        if (dateStr == null || dateStr.trim().isEmpty()) return LocalDateTime.now().format(dbFormatter);
        
        dateStr = dateStr.replace("T", " ").trim();
        LocalDateTime parsed = null;
        
        try { parsed = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); }
        catch (Exception e) {
            try { parsed = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); }
            catch (Exception e2) {
                 try { parsed = LocalDate.parse(dateStr).atTime(LocalTime.now()); }
                 catch (Exception e3) { parsed = LocalDateTime.now(); }
            }
        }
        
        // Fix Midnight (00:00:00) -> Current Time
        if (parsed.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            parsed = parsed.toLocalDate().atTime(LocalTime.now());
        }
        return parsed.format(dbFormatter);
    } catch (Exception e) {
        return LocalDateTime.now().format(dbFormatter);
    }
}
}