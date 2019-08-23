package com.soapsnake.hadoop.client;

import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

public class HadoopConfigTest {
    HadoopConfig hadoopConfig = new HadoopConfig();



    @Test
    public void testGetSomething() {


        Text text = new Text("");
    }

    @Test
    public void testGetConfigByName() throws Exception {
        String result = hadoopConfig.getConfigByName();
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    public void testMain() throws Exception {
        HadoopConfig.main(new String[]{"args"});
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme