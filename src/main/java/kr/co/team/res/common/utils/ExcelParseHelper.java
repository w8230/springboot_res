package kr.co.team.res.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExcelParseHelper {
    private static String filepath;
    @Value("${Globals.fileStorePath}")
    private void setValue(String value){
        filepath = value;
    }

    private static String filepathURI;
    @Value("${Globals.fileStoreUriPath}")
    private void setValues(String values){
        filepathURI = values;
    }
/*
    public List<GroupPeople> parseExcelToGourpPeople(Long grpPid, AttachFile attachFile) throws IOException {
        List<GroupPeople> rtnList = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filepath + File.separator + Constants.FOLDERNAME_GROUP + File.separator + attachFile.getFlNm());
            int pos = attachFile.getFlNm().lastIndexOf( "." );
            String ext = attachFile.getFlNm().substring( pos + 1 );
            if ("xls".equals(ext)) {
                rtnList = parseXlsToGroupPeople(grpPid, fis);
            } else if ("xlsx".equals(ext)) {
                rtnList = parseXlsxToGroupPeople(grpPid, fis);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (fis != null) fis.close();
        }
        return rtnList;
    }

    private List<GroupPeople> parseXlsToGroupPeople(Long grpPid, FileInputStream fis) throws IOException {
        List<GroupPeople> rtnList = new ArrayList<GroupPeople>();
        GroupPeople groupPeople = null;
        HSSFWorkbook workbook =new HSSFWorkbook(fis);
        //시트 수 (첫번째에만 존재하므로 0을 준다)
        //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
        HSSFSheet sheet = workbook.getSheetAt(0);
        //행의 수
        int rows = sheet.getPhysicalNumberOfRows();
        for (int rowindex = 1; rowindex < rows; rowindex++) {
            groupPeople = new GroupPeople();
            groupPeople.setGrpPid(grpPid);
            //행을 읽는다
            HSSFRow row = sheet.getRow(rowindex);
            if (row != null){
                //셀의 수
                int cells = row.getPhysicalNumberOfCells();
                for (int columnindex = 0; columnindex <= cells; columnindex++) {
                    //셀값을 읽는다
                    HSSFCell cell = row.getCell(columnindex);
                    String value = "";
                    //셀이 빈값일경우를 위한 널체크
                    if (cell==null) {
                        continue;
                    } else {
                        //타입별로 내용 읽기
                        switch (cell.getCellType()) {
                            case FORMULA:
                                value = cell.getCellFormula();
                                break;
                            case NUMERIC:
                                value = cell.getNumericCellValue()+"";
                                break;
                            case STRING:
                                value = cell.getStringCellValue()+"";
                                break;
                            case BOOLEAN:
                                value = cell.getBooleanCellValue()+"";
                                break;
                            case ERROR:
                                value = cell.getErrorCellValue()+"";
                                break;
                            default:
                                value = "";
                        }
                        groupPeople = setValue(groupPeople, columnindex, value);
                    }
                }
            }
            rtnList.add(groupPeople);
        }
        return rtnList;
    }

    private List<GroupPeople> parseXlsxToGroupPeople(Long grpPid, FileInputStream fis) throws IOException {
        List<GroupPeople> rtnList = new ArrayList<GroupPeople>();
        GroupPeople groupPeople = null;
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        //시트 수 (첫번째에만 존재하므로 0을 준다)
        //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
        XSSFSheet sheet = workbook.getSheetAt(0);
        //행의 수
        int rows = sheet.getPhysicalNumberOfRows();
        for (int rowindex = 1; rowindex < rows; rowindex++) {
            groupPeople = new GroupPeople();
            groupPeople.setGrpPid(grpPid);
            //행을읽는다
            XSSFRow row = sheet.getRow(rowindex);
            if (row != null) {
                //셀의 수
                int cells = row.getPhysicalNumberOfCells();
                for (int columnindex = 0; columnindex <= cells; columnindex++) {
                    //셀값을 읽는다
                    XSSFCell cell = row.getCell(columnindex);
                    String value = "";
                    //셀이 빈값일경우를 위한 널체크
                    if (cell == null) {
                        continue;
                    } else {
                        //타입별로 내용 읽기
                        switch (cell.getCellType()) {
                            case FORMULA:
                                value = cell.getCellFormula();
                                break;
                            case NUMERIC:
                                value = cell.getNumericCellValue()+"";
                                break;
                            case STRING:
                                value = cell.getStringCellValue()+"";
                                break;
                            case BOOLEAN:
                                value = cell.getBooleanCellValue()+"";
                                break;
                            case ERROR:
                                value = cell.getErrorCellValue()+"";
                                break;
                            default:
                                value = "";
                        }
                        groupPeople = setValue(groupPeople, columnindex, value);
                    }
                }
            }
            rtnList.add(groupPeople);
        }
        return rtnList;
    }

    private GroupPeople setValue(GroupPeople groupPeople, int index, String value) {
        switch (index) {
            case 0:
                groupPeople.setNm(value);
                break;
            case 1:
                groupPeople.setHpNum(StringHelper.onlyNumber(value));
                break;
            case 2:
                groupPeople.setEmail(value);
                break;
            case 3:
                groupPeople.setOfficeNm(value);
                break;
            case 4:
                groupPeople.setDeptNm(value);
                break;
            case 5:
                groupPeople.setPositionNm(value);
                break;
            case 6:
                groupPeople.setOfficeTelNum(StringHelper.onlyNumber(value));
                break;
        }
        return groupPeople;
    }*/
}
