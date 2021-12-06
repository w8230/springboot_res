package kr.co.team.res.common.utils;

import kr.co.team.res.domain.entity.FileInfo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;

@Component
public class FileUtilHelper {

    public static final int BUFF_SIZE = 2048;
    
    private static Logger log = LoggerFactory.getLogger(FileUtilHelper.class); 

    /*@Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;*/

    //private static final Logger LOG = Logger.getLogger(EgovFileMngUtil.class.getName());

    //@Value("${Globals.fileStorePath}")
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

    /*private static Long fileMaxSize;
    @Value(("${Globals.fileSize}"))
    private void setValues(Long values){
        fileMaxSize = values;
    }*/

    public static String[] zipExt = new String[]{".zip", ".hgx", ".tgz"};
    public static String[] docExt = new String[]{".pdf", ".hwp", ".xls", ".xlsx", ".doc", ".docx", ".ppt", ".pptx"};
    public static String[] imageExt = new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"};
    public static String[] audioExt = new String[]{".mp3", ".ogg", ".wma", ".wav", ".au", ".rm", ".mid"};
    public static String[] videoExt = new String[]{".mkv", ".avi", ".mp4", ".mpg", ".flv", ".wmv", ".asf", ".asx", ".ogm", ".ogv", ".mov"};

    private static String[] notAllowExt = new String[]{".js", ".jsp", ".php", ".php3", ".asp", ".aspx", ".cer", ".cdx", ".asa", ".war", ".jar", ".exe"};

    public static String[] getAllExt() {
        String[] temp = null;
        temp = ArrayUtils.addAll(zipExt, docExt);
        temp = ArrayUtils.addAll(temp, imageExt);
        temp = ArrayUtils.addAll(temp, audioExt);
        temp = ArrayUtils.addAll(temp, videoExt);
        return temp;
    }

    /**
     * 첨부파일을 서버에 저장한다.
     *
     * @param file
     * @param stordFilePath
     * @throws Exception
     */
    public static FileInfo writeUploadedFile(MultipartFile file, String stordFilePath, String[] allowExt) throws Exception {
        //FileDto fileDto = null;
        if (file == null) {
            return null;
        }

        if(file.getName() == ""){
            return null;
        }

        /*Boolean isChecked = checkFile(file);
        if(!isChecked){
            return null;
        }*/

        //String sourceFileName = file.getOriginalFilename();
        //iE has file path
        //sourceFileName = sourceFileName.substring(sourceFileName.lastIndexOf("\\") + 1);
        final String iE_regex = "^.*[\\\\\\/]";
        String sourceFileName = file.getOriginalFilename().replaceAll(iE_regex, "");
        String sourceFileNameExtension = FileUtilHelper.getFileExtension(sourceFileName);

        boolean extPass = false;
        if (allowExt != null && allowExt.length > 0) {
            String ext =  FileUtilHelper.getFileExtension(sourceFileName);
            for (String ae : allowExt) {
                if(ext.contains(ae)){
                    extPass = true;
                    break;
                }
            }
        }
        if (!extPass && allowExt != null) {
            return null;
        }

        String newName = "";
        InputStream stream = null;
        OutputStream bos = null;

        String fileSavePath = stordFilePath;
        if (fileSavePath == "") {
            fileSavePath = filepath;
        } else {
            fileSavePath = filepath + (stordFilePath.charAt(0) == '/' ? "" : File.separator) + fileSavePath;
        }

        try {
            stream = file.getInputStream();
            File dir = new File(fileSavePath);
            if (!dir.isDirectory()) {
                boolean _flag = dir.mkdir();
                if (!_flag) {
                    throw new IOException("Directory creation Failed ");
                }
            }

            String newfilename = sourceFileName;
            boolean bexist = true;
            int cntfilename = 0;
            do {

                File cFile = new File(fileSavePath + "/" + newfilename);
                if (cFile.exists()) {
                    cntfilename++;
                    newfilename = sourceFileName.replace(sourceFileNameExtension, "")
                            + "(" + cntfilename + ")"
                            + sourceFileNameExtension;
                } else {
                    bexist = false;
                }
            } while (bexist);

            bos = new FileOutputStream(fileSavePath + "/" + newfilename);

            int bytesRead = 0;
            byte[] buffer = new byte[BUFF_SIZE];

            while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            FileInfo fileInfo = new FileInfo();
            fileInfo.setFlNm(sourceFileName);
            fileInfo.setChgFlNm(newfilename);
            fileInfo.setFlExtsn(sourceFileNameExtension);
            fileInfo.setFlPth("/" + stordFilePath);
            fileInfo.setFlSz(file.getSize());
            fileInfo.setRegDtm(LocalDateTime.now());

            //return fileService.makeNer(fileDto);

            return fileInfo;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
            }
        }
    }

    public static FileInfo writeUploadedFile(MultipartFile file, String stordFilePath) throws Exception {
        return writeUploadedFile(file, stordFilePath, getAllExt());
    }

    /**
     * 서버의 파일을 다운로드한다.
     *
     * @param request
     * @param response
     * @throws Exception
     */
    /*public static void downFile(HttpServletRequest request, HttpServletResponse response, String storedFilePath) throws Exception {

        //String filepath = "";
        String downFileName = "";
        //String orgFileName = "";

        if (request.getAttribute("downFile") == null) {
            downFileName = "";
        } else {
            downFileName = filepath + "/" + storedFilePath + "/" + request.getAttribute("downFile");
        }

        File file = new File(downFileName);

        if (!file.exists()) {
            throw new FileNotFoundException(downFileName);
        }

        if (!file.isFile()) {
            throw new FileNotFoundException(downFileName);
        }

        byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.

        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition:", "attachment; filename=" + new String(file.getName().getBytes(), StandardCharsets.UTF_8));
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        BufferedInputStream fin = null;
        BufferedOutputStream outs = null;

        try {
            fin = new BufferedInputStream(new FileInputStream(file));
            outs = new BufferedOutputStream(response.getOutputStream());
            int read = 0;

            while ((read = fin.read(b)) != -1) {
                outs.write(b, 0, read);
            }
            outs.flush();
        } finally {
            if (outs != null) {
                try {
                    outs.close();
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
            }
            if (fin != null) {
                try {
                    fin.close();
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
            }
        }
    }*/

    /**
     * 서버의 파일을 다운로드한다.
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, String storedFilePath) throws Exception {
        String downFileName = "";
        String orgFileName = request.getAttribute("orgFileName").toString();

        if (request.getAttribute("downFile") == null) {
            downFileName = "";
        } else {
            if (storedFilePath == null || "".equals(storedFilePath)) {
                downFileName = filepath + request.getAttribute("downFile");
            } else {
                downFileName = filepath + storedFilePath + "/" + request.getAttribute("downFile");
            }
        }
        File downloadFile = new File(downFileName);
        if (!downloadFile.exists()) {
            throw new FileNotFoundException(downFileName);
        }

        if (!downloadFile.isFile()) {
            throw new FileNotFoundException(downFileName);
        }

        byte fileByte[] = readFileToByteArray(downloadFile);
        response.setContentType("application/octet-stream");
        response.setContentLength(fileByte.length);

        String userAgent = request.getHeader("User-Agent");
        boolean ie = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1 || userAgent.indexOf("Edge") > -1);
        String fileName = "";
        if (ie) {
            fileName = URLEncoder.encode(orgFileName, "UTF-8");
            fileName = fileName.replaceAll("\\+", "%20");
        } else {
            fileName = new String(String.valueOf(orgFileName).getBytes("UTF-8"), "iso-8859-1");
        }

        response.setHeader("Content-Disposition", "attachment; fileName=\"" +fileName+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.getOutputStream().write(fileByte);

        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    public static String getFileExtension(String filename) {
        String fileExtension = "";

        if (filename != null && !"".equals((filename))) {
            if (filename.lastIndexOf(".") != -1) {
                fileExtension = filename.toLowerCase().substring(filename.lastIndexOf("."), filename.length());
            }
        }

        return fileExtension;
    }

    /**
     * 서버의 파일을 삭제처리함. 
     * @param stordFilePath
     * @param fileNm
     * @return
     */
    public static boolean removeFile(String stordFilePath, String fileNm) {
        boolean rtnBool = false;
        String path = filepath + File.separator + stordFilePath + File.separator + fileNm;
        File file = new File(path);
        if (file.exists()) {
            rtnBool = file.delete();
            log.debug("파일:{}, 삭제결과:{}", file.getAbsoluteFile(), rtnBool);
        }
        
        return rtnBool;
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        InputStream in = null;
        try {
            in = openInputStream(file);
            return IOUtils.toByteArray(in);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    public static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canRead() == false) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }

    /*private static Boolean checkFile(MultipartFile file) {
        return checkFile(file, fileMaxSize);
    }
    private static Boolean checkFile(MultipartFile file, Long fileMaxSize) {
        boolean result = true;
        try {
            if (file != null && !file.isEmpty()) {
                String fileNm = file.getOriginalFilename();
                *//*for (String e : notAllowExt) {
                    if (fileNm.contains(e)) {
                        result = false;
                        break;
                    }
                }*//*
                String ext = FileUtilHelper.getFileExtension(fileNm);
                for (String notExt : notAllowExt) {
                    if(ext.equals(notExt)){
                        result = false;
                        break;
                    }
                }
                if (file.getSize() > (fileMaxSize * 1024 * 1024)) {
                    result = false;
                }
            } else {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }*/
}
