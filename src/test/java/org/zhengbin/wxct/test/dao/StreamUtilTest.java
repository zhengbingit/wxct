package org.zhengbin.wxct.test.dao;

import org.junit.Test;
import org.zhengbin.snowflake.framework.util.StreamUtil;

import java.io.*;

/**
 * Created by zhengbinMac on 2017/5/5.
 */
public class StreamUtilTest {

    @Test
    public void testCopyStream() throws FileNotFoundException {
        InputStream in = new BufferedInputStream(new FileInputStream(new File("/Users/zhengbinMac/temp/compile.txt")));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("/Users/zhengbinMac/temp/compile1.txt")));
        StreamUtil.copyStream(in, out);
    }
}
