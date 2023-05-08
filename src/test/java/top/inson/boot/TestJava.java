package top.inson.boot;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author jingjitree
 * @description
 * @date 2022/6/14 18:09
 **/
@Slf4j
public class TestJava {

    @Test
    public void handler(){
        /*
        String tbCompanyPrefix = "JX",
                tbAuthCode = "123456",
                tbAuthIP = "120.25.212.197",
                tbAuthPort = "8000",
                type = "KD8A0";
        String md5Str = tbCompanyPrefix + tbAuthCode + tbAuthIP + tbAuthPort + type + "123456789abcdefg";
        byte[] bytes = DigestUtil.md5(md5Str.getBytes(CharsetUtil.CHARSET_UTF_8));
        String baseMd5 = Base64.encode(bytes);

        String str = tbCompanyPrefix + "-" + tbAuthCode + "-" + tbAuthIP + "-" + tbAuthPort + "-" + type + "-" + baseMd5;
        */

        String key = "ABCDEFGHIJABCDEFGHIJABCDEFGHIJAA";
        String iv = "0123456789000000";
        String enCode = "EsKy6npJrWBlPMW8kkNiJtDC87ERcaP0Ld58vK+8SVNGS+Grh0S7VLeEhMrtZ8cfMiH23J7D94Y+savqKC8BgQ==";

        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(CharsetUtil.CHARSET_UTF_8), iv.getBytes(CharsetUtil.CHARSET_UTF_8));
        String source = aes.decryptStr(enCode);
        log.info("解密原文source: " + source);

        String encryptBase64 = aes.encryptBase64("JX002-CODE-127.0.0.1-8888-GL3A0-8y6HlENPkb/cDUhsHC7DWQ==", CharsetUtil.CHARSET_UTF_8);

        log.info("加密内容：" + encryptBase64);
    }

    @Test
    public void testHex(){



    }


}
