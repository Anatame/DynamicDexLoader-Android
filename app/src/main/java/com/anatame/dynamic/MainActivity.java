package com.anatame.dynamic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Plugin myClassInstance = null;
        String jarContainerPath =       Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/DynamicApp/Plugin.jar";
        File dexOutputDir = getDir("dex", MODE_PRIVATE);
        DexClassLoader mDexClassLoader = new DexClassLoader(    jarContainerPath,
                dexOutputDir.getAbsolutePath(),
                null,
                getClass().getClassLoader());

        try {
            Class<?> loadedClass = mDexClassLoader.loadClass("com.anatame.dynamic.PluginOne");
//            myClassInstance = (Plugin)
            Toast.makeText(MainActivity.this, loadedClass.newInstance().getClass().getSimpleName(), Toast.LENGTH_SHORT ).show();
            ;

            // Do something with the loaded object myClassInstance
            // i.e. myClassInstance.doSomething();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

