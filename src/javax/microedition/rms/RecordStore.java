package javax.microedition.rms;

import java.io.*;
import java.util.*;

public class RecordStore {
    private static final File STORAGE_DIR = new File(System.getProperty("user.home"), ".local/share/gow_betrayal/rms");
    private final String name;
    private final List<byte[]> records = new ArrayList<>();

    private RecordStore(String name) {
        this.name = name;
        load();
    }

    public static synchronized RecordStore openRecordStore(String recordStoreName, boolean createIfNecessary) {
        STORAGE_DIR.mkdirs();
        File f = new File(STORAGE_DIR, recordStoreName + ".rms");
        if (!f.exists() && !createIfNecessary) {
            return null;
        }
        return new RecordStore(recordStoreName);
    }

    public static synchronized String[] listRecordStores() {
        STORAGE_DIR.mkdirs();
        String[] files = STORAGE_DIR.list((dir, name) -> name.endsWith(".rms"));
        if (files == null || files.length == 0) return null;
        String[] res = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            res[i] = files[i].substring(0, files[i].length() - 4);
        }
        return res;
    }

    public static synchronized void deleteRecordStore(String recordStoreName) {
        File f = new File(STORAGE_DIR, recordStoreName + ".rms");
        if (f.exists()) f.delete();
    }

    public synchronized int getNumRecords() {
        return records.size();
    }

    public synchronized int getRecordSize(int recordId) {
        byte[] rec = getRecord(recordId);
        return rec != null ? rec.length : 0;
    }

    public synchronized byte[] getRecord(int recordId) {
        if (recordId >= 1 && recordId <= records.size()) {
            return records.get(recordId - 1);
        }
        return null;
    }

    public synchronized int getRecord(int recordId, byte[] buffer, int offset) {
        byte[] rec = getRecord(recordId);
        if (rec != null) {
            System.arraycopy(rec, 0, buffer, offset, rec.length);
            return rec.length;
        }
        return 0;
    }

    public synchronized int addRecord(byte[] data, int offset, int numBytes) {
        byte[] copy = new byte[numBytes];
        if (data != null && numBytes > 0) {
            System.arraycopy(data, offset, copy, 0, numBytes);
        }
        records.add(copy);
        save();
        return records.size();
    }

    public synchronized void setRecord(int recordId, byte[] data, int offset, int numBytes) {
        if (recordId >= 1 && recordId <= records.size()) {
            byte[] copy = new byte[numBytes];
            if (data != null && numBytes > 0) {
                System.arraycopy(data, offset, copy, 0, numBytes);
            }
            records.set(recordId - 1, copy);
            save();
        }
    }

    public void closeRecordStore() {
        save();
    }

    private void save() {
        STORAGE_DIR.mkdirs();
        File f = new File(STORAGE_DIR, name + ".rms");
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(f))) {
            dos.writeInt(records.size());
            for (byte[] rec : records) {
                dos.writeInt(rec.length);
                dos.write(rec);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        File f = new File(STORAGE_DIR, name + ".rms");
        if (!f.exists()) return;
        records.clear();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            int count = dis.readInt();
            for (int i = 0; i < count; i++) {
                int len = dis.readInt();
                byte[] rec = new byte[len];
                dis.readFully(rec);
                records.add(rec);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
