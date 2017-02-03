package com.nextcont.ecm.fileengine.fastdfs;

/**
 * 代表一个fastdfs的文件
 * Created by wangxudong on 16/1/18.
 */
public class FastdfsFile {

    private static final String SEPARATOR =";;;";
    private final String groupName;
    private final String fid;

    public FastdfsFile(String groupName, String fid) {
        this.groupName = groupName;
        this.fid = fid;
    }

    public static FastdfsFile parse(String[] fileInfo){
        return new FastdfsFile(fileInfo[0],fileInfo[1]);
    }

    public static FastdfsFile parse(String rawValue) { return parse(rawValue.split(SEPARATOR)); }

    public String rawValue(){
        return this.groupName + SEPARATOR + this.fid;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getFid() {
        return fid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FastdfsFile that = (FastdfsFile) o;

        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
        return !(fid != null ? !fid.equals(that.fid) : that.fid != null);

    }

    @Override
    public int hashCode() {
        int result = groupName != null ? groupName.hashCode() : 0;
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FastdfsFile{" +
                "groupName='" + groupName + '\'' +
                ", fid='" + fid + '\'' +
                '}';
    }
}
