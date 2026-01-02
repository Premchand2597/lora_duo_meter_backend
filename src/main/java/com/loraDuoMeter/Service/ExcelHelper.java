
package com.loraDuoMeter.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public static List<BuildingEntity> excelToBuildings(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            List<BuildingEntity> buildings = new ArrayList<>();
            int rowNumber = 0;

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
                        case 10: building.setDateTime(cellValue); break;
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
                        case 13: resident.setDateTime(getCellValueAsString(currentCell)); break;
                        default: break;
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
    }

    // 3. PARSE GATEWAYS (EXCEL)
    public static List<GatewayEntity> excelToGateways(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<GatewayEntity> gateways = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) { rowNumber++; continue; }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                GatewayEntity gateway = new GatewayEntity();
                int cellIdx = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0: gateway.setGatewayId(getCellValueAsString(currentCell)); break;
                        case 1: gateway.setBuildingName(getCellValueAsString(currentCell)); break;
                        case 2: gateway.setBuildingId(getCellValueAsString(currentCell)); break;
                        case 3: gateway.setFloorNo(getCellValueAsString(currentCell)); break;
                        case 4: gateway.setInternetType(getCellValueAsString(currentCell)); break;
                        case 5: gateway.setStatus(getCellValueAsString(currentCell)); break;
                        case 6: gateway.setMaxMeters(getCellValueAsString(currentCell)); break;
                        case 7: gateway.setModel(getCellValueAsString(currentCell)); break;
                        case 8: gateway.setManufacturer(getCellValueAsString(currentCell)); break;
                        case 9: gateway.setMakeYear(getCellValueAsString(currentCell)); break;
                        case 10: gateway.setFirmware(getCellValueAsString(currentCell)); break;
                        case 11: gateway.setLatitude(getCellValueAsString(currentCell)); break;
                        case 12: gateway.setLongitude(getCellValueAsString(currentCell)); break;
                        case 13: gateway.setRssiId(getCellValueAsString(currentCell)); break;
                        case 14: gateway.setChannelRssi(getCellValueAsString(currentCell)); break;
                        case 15: gateway.setChannelIndex(getCellValueAsString(currentCell)); break;
                        case 16: gateway.setBandwidth(getCellValueAsString(currentCell)); break;
                        case 17: gateway.setFrequency(getCellValueAsString(currentCell)); break;
                        case 18: gateway.setNetId(getCellValueAsString(currentCell)); break;
                        case 19: gateway.setDateTime(getCellValueAsString(currentCell)); break;
                        default: break;
                    }
                    cellIdx++;
                }
                gateways.add(gateway);
            }
            workbook.close();
            return gateways;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    // 4. PARSE METERS (EXCEL)
    public static List<MeterEntity> excelToMeters(InputStream is) {
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
    }
}