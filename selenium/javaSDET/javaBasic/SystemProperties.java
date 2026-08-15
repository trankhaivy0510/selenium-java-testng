package javaSDET.javaBasic;

import java.io.File;

public class SystemProperties {
    public static void main(String[] args){
        String uploadFilePath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;
        System.out.println("Đường dẫn: "+ uploadFilePath);
    }

    // Window thì dùng dấu: \\
    // Mac/Linux thì dùng: /
    // => Nên ta dùng File.separator để lấy theo HĐH

}
