package com.example.assignmen3.Model;

import com.example.assignmen3.R;

import java.io.File;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class FileModel extends File implements IFileModel {

    private int iconID;

    public FileModel(String pathname) {
        super(pathname);
        this.iconID = getIconIDByExtension(this.getExtension());
    }

    public FileModel(File file, String child) {
        super(file, child);
        this.iconID = getIconIDByExtension(this.getExtension());
    }

    @Override
    public int getIconID() {
        return iconID;
    }

    @Override
    public int itemCnt() {
        if (this.isDirectory()) {
            return getFileCnt(this);
        }
        return -1;
    }

    private int getFileCnt(File file) {
        if (!file.exists()) return 0;
        if (file.isFile()) return 1;
        File[] files = file.listFiles();
        int ans = 0;
        for (File childFile : files) {
            ans += getFileCnt(childFile);
        }
        return ans;
    }


    public List<FileModel> listFileModels() {
        ArrayList<FileModel> ans = new ArrayList<>();
        File[] files = this.listFiles();
        for (File file : files) {
            ans.add(new FileModel(file.getAbsolutePath()));
        }

        return ans;
    }


    public static int getIconIDByExtension(String extension) {
        switch (extension) {
            case "doc":
                return R.drawable.doc;
            case "folder":
                return R.drawable.folder;
            case "mp3":
                return R.drawable.mp3;
            case "png":
                return R.drawable.png;
            case "txt":
                return R.drawable.txt;
            case "xls":
                return R.drawable.xls;
            case "zip":
                return R.drawable.zip;
        }
        return R.drawable.ic_launcher_foreground;
    }

    public String getExtension() {
        if (this.isDirectory()) return "folder";
        String path = this.getPath();
        int lastInd = path.lastIndexOf(".");
        if (lastInd != -1)
            return path.substring(lastInd + 1);
        else
            return "";
    }

}
